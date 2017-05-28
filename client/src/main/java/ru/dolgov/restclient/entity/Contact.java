package ru.dolgov.restclient.entity;

import java.io.Serializable;

/**
 * @author M.Dolgov
 * @date 22.05.2017
 */
public class Contact implements Serializable{

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephoneNumber(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        if (this == obj) return true;
        Contact contact = (Contact) obj;
        if (this.id == contact.id && this.firstName.equals(contact.getFirstName()) &&
                this.lastName.equals(contact.getLastName()) && this.getTelephone().equals(contact.getTelephone()) &&
                this.email.equals(contact.getEmail())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Contact [id= " + id + ", firstName= " + firstName + ", lastName= " + lastName + ", email= " + email
                + ", telephone= " + telephone + "]";
    }
}
