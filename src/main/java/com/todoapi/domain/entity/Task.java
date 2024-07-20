package com.todoapi.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int status;

    //Establish the ManyToOne relation with the User table. Especifiying that Many Task can be own by one User.
    @ManyToOne
    private User user;
}
