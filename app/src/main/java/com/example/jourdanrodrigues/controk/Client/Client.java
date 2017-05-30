package com.example.jourdanrodrigues.controk.Client;

class Client {
    private String name;
    private String email;
    private String cpf;
    private String observation;

    Client(String name, String email, String cpf, String observation) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.observation = observation;
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

    String getObservation() {
        return observation;
    }
}
