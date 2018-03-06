package com.danielsolawa.storeauth.utils;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.domain.Contact;
import com.danielsolawa.storeauth.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {


    private final String messageStart = "<html><head>" +
            "<style>" +
            ".links{" +
            "text-decoration: none;" +
            "text-align: center;" +
            "font-size: 150%;" +
            "}" +
            "table {" +
            "border-collapse: collapse;" +
            "width: 100%;" +
            "}" +
            "th, td {" +
            "text-align: left;" +
            "padding: 8px;" +
            "}" +
            "tr:nth-child(even) {background-color: #f2f2f2;}" +
            "</style>" +
            "</head>" +
            "<body>";

    private final String messageEnd = "</body>" +
            "<a class='links' href='http://localhost:9000'>Spring Store</a>" +
            "</html>";

    public String getMessageStart(){
        return messageStart;
    }

    public String getMessageEnd(){
        return messageEnd;
    }

    public String addMessage(String message){
        return "<h2>" + message + "</h2>";
    }


    /*
        Products
     */

    public final String generateProductHeaders(){
        return  "<h3>Products List: </h3>" +
                "<table>" +
                    "<tr style='background-color: #e7e7e7;'>" +
                    "<th>id</<th>" +
                    "<th>name</<th>" +
                    "<th>price</<th>" +
                    "<th>amount</<th>" +
                    "</tr>";

    }



    public String generateProductRow(Product product, int amount){

        return  "<tr style='background-color: #f4f4f4;'>"+
                "<td>" + product.getId() + "</td> " +
                "<td>" + product.getName() + "</td>" +
                "<td>" + product.getPrice() + "</td>" +
                "<td>" + amount + "</td>"
                +"</tr>";
    }


    public String generateProductTotalPrice(double totalPrice){
        return "</table>" +
                "<h2>Total price: " + totalPrice + " PLN</h2>";
    }



    /*
        Customer
     */
    public String generateCustomerAddress(Address address, String email) {
        return  "<h3>Customer information: </h3>" +
                "<ul>" +
                "<li>Name: "+ address.getFirstName() + " " + address.getLastName() +"</li>" +
                "<li>Street: "+ address.getStreet() +"</li>" +
                "<li>City: "+ address.getCity() +"</li>" +
                "<li>Zip Code: " + address.getZipCode() + "</li>" +
                "<li>Country: "+ address.getCountry() + "</li>" +
                "<li>Email: "+ email + "</li>" +
                "<li>Phone: "+ address.getPhoneNumber() + "</li>" +
                "</ul>";

    }

    /*
       Owner
     */

    public String generateContactMessageBodyForCustomer(Contact contact){

        return  "<h4>Hello "+ contact.getUsers().get(0).getUsername() + "!</h4>" +
                "<p>" + "Response to your question" +"</p>" +
                "<p><a class='links' href='http://localhost:9000/#!/users/"+ contact.getUserId() +"/contact/"
                + contact.getConversationId()+
                "'>Respond to message</a></p>" +
                "</body>" +
                "</html>";

    }

    public String generateContactMessageBodyForOwner(Contact contact){

        return  "<h4>A new message has been received from "+ contact.getUsers().get(0).getUsername() + "</h4>" +
                "<p>" + contact.getSubject() +"</p>" +
                "<p>" + contact.getContent() +"</p>" +
                "<p><a class='links' href='http://localhost:9000/#!/admin'>Respond to message</a></p>" +
                "</body>" +
                "</html>";
    }
}
