package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.EmailDto;
import com.danielsolawa.storeauth.utils.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
@Slf4j
public class OrderEmailServiceImpl implements OrderEmailService {

    private static final String CUSTOMER_MESSAGE = "Thank you for your order.";
    private static final String OWNER_MESSAGE = "New product/products have been sold.";
    private static final String SUBJECT = "Spring Store Sale";

    private final String storeEmail;
    private final EmailService emailService;
    private final EmailTemplate emailTemplate;

    public OrderEmailServiceImpl(@Value("${spring.mail.username}")String storeEmail, EmailService emailService,
                                 EmailTemplate emailTemplate) {
        this.storeEmail = storeEmail;
        this.emailService = emailService;
        this.emailTemplate = emailTemplate;
    }

    @Override
    public void sendEmails(User user, Order order) throws MessagingException, InterruptedException {
        /*
            to customer
         */
        String customerText = generateMessage(user, order.getProducts(), CUSTOMER_MESSAGE, false );
        emailService.sendEmail
                (
                        EmailDto.builder()
                                .from(storeEmail)
                                .text(customerText)
                                .to(user.getUsername())
                                .subject(SUBJECT)
                                .user(user)
                                .build()
                );

        /*
            to owner
         */

        String ownerText = generateMessage(user, order.getProducts(), OWNER_MESSAGE, false );
        emailService.sendEmail
                (
                        EmailDto.builder()
                                .from(storeEmail)
                                .text(ownerText)
                                .to(user.getUsername())
                                .subject(SUBJECT)
                                .user(user)
                                .build()
                );
    }


    private String generateMessage(User user, List<Product> products, String message, boolean toOwner) {
        StringBuilder builder = new StringBuilder();
        builder.append(emailTemplate.getMessageStart());
        builder.append(emailTemplate.addMessage(message));

        if(toOwner) {
            builder.append(emailTemplate.generateCustomerAddress(user.getAddress(), user.getUsername()));
        }

        builder.append(getProductListAsString(products));

        builder.append(emailTemplate.getMessageEnd());

        return builder.toString();

    }

    private String getProductListAsString(List<Product> products) {
        StringBuilder builder = new StringBuilder();

        builder.append(emailTemplate.generateProductHeaders());

        Product tempProduct = null;
        int productAmount = 0;
        double productSum = 0.0;

        for(Product p : products){
            if(tempProduct == null){
                tempProduct = p;
            }

            if(p.getId().equals(tempProduct.getId())){
                productAmount++;
                continue;
            }


            builder.append(emailTemplate.generateProductRow(tempProduct, productAmount));
            productSum += productAmount * tempProduct.getPrice();
            productAmount = 1;
            tempProduct = p;

        }
        builder.append(emailTemplate.generateProductRow(tempProduct, productAmount));
        productSum += productAmount * tempProduct.getPrice();

        builder.append(emailTemplate.generateProductTotalPrice(productSum));
        log.info(builder.toString());

        return builder.toString();
    }

}
