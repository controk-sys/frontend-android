package com.example.jourdanrodrigues.controk.Contact;

public class Contact {
    private String cellPhone;
    private String phone;
    private String email;

    public Contact(String cellPhone, String phone, String email) {
        this.cellPhone = cellPhone;
        this.phone = phone;
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
