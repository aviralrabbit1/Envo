package com.example.theapp;

public class userData {
    String name, country, residence, diet, transport;
    int numberOfMembers,flight[],noOfFoodDelivery,distanceTravelled;
    int noOfClothes,noOfGadgets;
    public userData(String name, String country, String residence, String diet, String transport, int numberOfMembers,
                    int[] flight, int noOfFoodDelivery, int distanceTravelled, int noOfClothes, int noOfGadgets) {
        this.name = name;
        this.country = country;
        this.residence = residence;
        this.diet = diet;
        this.transport = transport;
        this.numberOfMembers = numberOfMembers;
        this.flight = flight;
        this.noOfFoodDelivery = noOfFoodDelivery;
        this.distanceTravelled = distanceTravelled;
        this.noOfClothes = noOfClothes;
        this.noOfGadgets = noOfGadgets;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getResidence() {
        return residence;
    }
    public void setResidence(String residence) {
        this.residence = residence;
    }
    public String getDiet() {
        return diet;
    }
    public void setDiet(String diet) {
        this.diet = diet;
    }
    public String getTransport() {
        return transport;
    }
    public void setTransport(String transport) {
        this.transport = transport;
    }
    public int getNumberOfMembers() {
        return numberOfMembers;
    }
    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
    public int[] getFlight() {
        return flight;
    }
    public void setFlight(int[] flight) {
        this.flight = flight;
    }
    public int getNoOfFoodDelivery() {
        return noOfFoodDelivery;
    }
    public void setNoOfFoodDelivery(int noOfFoodDelivery) {
        this.noOfFoodDelivery = noOfFoodDelivery;
    }
    public int getDistanceTravelled() {
        return distanceTravelled;
    }
    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
    public int getNoOfClothes() {
        return noOfClothes;
    }
    public void setNoOfClothes(int noOfClothes) {
        this.noOfClothes = noOfClothes;
    }
    public int getNoOfGadgets() {
        return noOfGadgets;
    }
    public void setNoOfGadgets(int noOfGadgets) {
        this.noOfGadgets = noOfGadgets;
    }
}

