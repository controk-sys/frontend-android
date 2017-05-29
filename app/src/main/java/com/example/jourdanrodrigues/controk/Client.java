package com.example.jourdanrodrigues.controk;

class Client {
    private String name;
    private String email;
    private String cpf;

    Client(String name, String email, String cpf) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getCpf() {
        return cpf;
    }
}
