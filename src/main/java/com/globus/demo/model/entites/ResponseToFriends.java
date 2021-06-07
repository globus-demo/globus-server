package com.globus.demo.model.entites;

import javax.persistence.*;

@Entity
@Table(name = "response_to_friends")
public class ResponseToFriends {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "emailUserFrom")
    private String emailUserFrom;

    @Column(name = "emailUserTo")
    private String emailUserTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailUserFrom() {
        return emailUserFrom;
    }

    public void setEmailUserFrom(String emailUserFrom) {
        this.emailUserFrom = emailUserFrom;
    }

    public String getEmailUserTo() {
        return emailUserTo;
    }

    public void setEmailUserTo(String emailUserTo) {
        this.emailUserTo = emailUserTo;
    }
}
