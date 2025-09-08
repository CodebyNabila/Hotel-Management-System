
package com.example.hotel.model;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contact;
    private String gender;

    public Customer() {}

    public Customer(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getGender() { return gender; }
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    public void setGender(String gender) { this.gender = gender; }
}
