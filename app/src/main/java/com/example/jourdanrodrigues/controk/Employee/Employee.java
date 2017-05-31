package com.example.jourdanrodrigues.controk.Employee;

public class Employee {
    private String name;
    private String email;
    private String cpf;
    private String role;
    private String observation;

    Employee(String name, String email, String cpf, String role, String observation) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    String getObservation() {
        return observation;
    }
}
