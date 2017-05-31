package com.example.jourdanrodrigues.controk.Address;

public class Address {
    private Integer number;
    private String placeName;
    private Integer placeOption;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;

    public Address (Integer placeOption, String placeName, Integer number, String complement, String neighborhood, String city, String state, String cep) {
        this.placeOption = placeOption;
        this.placeName = placeName;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPlaceOption() {
        return placeOption;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }
}
