package ru.dolgov.webservice.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Михалыч on 22.05.2017.
 */
@Entity
@Table(name = "CONTACTS")
public class Contact implements Serializable{
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEPHONE")
    private String telephoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        Contact contact = (Contact) obj;
        if (this.id == contact.id && this.firstName.equals(contact.getFirstName()) &&
                this.lastName.equals(contact.getLastName()) && this.getTelephoneNumber().equals(contact.getTelephoneNumber()) &&
                this.email.equals(contact.getEmail())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contact [id= " + id + ", firstName= " + firstName + ", lastName= " + lastName + ", email= " + email
                + ", telephone= " + telephoneNumber + "]";
    }
}
