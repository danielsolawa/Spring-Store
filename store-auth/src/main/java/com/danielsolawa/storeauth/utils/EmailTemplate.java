package com.danielsolawa.storeauth.utils;

import com.danielsolawa.storeauth.domain.Address;
import com.danielsolawa.storeauth.domain.Product;

public class EmailTemplate {


    public static final String MESSAGE_START = "<html><head>" +
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

    public static final String MESSAGE_END = "</body>" +
            "<a class='links' href='http://localhost:9000'>Spring Store</a>" +
            "</html>";

    public static String addMessage(String message){
        return "<h2>" + message + "</h2>";
    }


    /*
        Products
     */

    public static final String generateProductHeaders(){
        return  "<h3>Products List: </h3>" +
                "<table>" +
                    "<tr style='background-color: #e7e7e7;'>" +
                    "<th>id</<th>" +
                    "<th>name</<th>" +
                    "<th>price</<th>" +
                    "<th>amount</<th>" +
                    "</tr>";

    }



    public static String generateProductRow(Product product, int amount){

        return  "<tr style='background-color: #f4f4f4;'>"+
                "<td>" + product.getId() + "</td> " +
                "<td>" + product.getName() + "</td>" +
                "<td>" + product.getPrice() + "</td>" +
                "<td>" + amount + "</td>"
                +"</tr>";
    }


    public static String generateProductTotalPrice(double totalPrice){
        return "</table>" +
                "<h2>Total price: " + totalPrice + " PLN</h2>";
    }



    /*
        Customer
     */
    public static String generateCustomerAddress(Address address, String email) {
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
}
