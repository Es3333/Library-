package com.Libraries.Library.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Long ID;

    @Column
    private   String Name;
    @Column
    private   String Phone_number;

    public Patron(Long ID, String name, String phone_number) {
        this.ID = ID;
        Name = name;
        Phone_number = phone_number;
    }

    public  Patron(){

    }

    @Override
    public String toString() {
        return "Patron{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }


}
