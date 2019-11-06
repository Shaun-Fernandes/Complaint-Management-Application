package com.example.login;

public class Complaint {
    public String complaint;
    public boolean hotel;
    public boolean flight;
    public boolean tour;
    public int id;
    public String active;
    public String email;

    public Complaint(){

    }

    public Complaint(String complaint, boolean hotel, boolean flight, boolean tour, int id, String email){
        this.complaint = complaint;
        this.hotel = hotel;
        this.flight = flight;
        this.tour = tour;
        this.id = id;
        this.active = "Pending Resolution";
        this.email = email;
    }
}
