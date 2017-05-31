package com.example.jourdanrodrigues.controk.Contact;

public class Contact {
    private String cellPhone;
    private String phone;

    public Contact(String cellPhone, String phone) {
        this.cellPhone = cellPhone;
        this.phone = phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getPhone() {
        return phone;
    }
}
