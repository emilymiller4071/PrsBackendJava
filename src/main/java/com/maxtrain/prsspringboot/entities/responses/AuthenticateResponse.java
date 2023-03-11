package com.maxtrain.prsspringboot.entities.responses;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthenticateResponse {
    private int id;
    private String firstName;
    private String lastName;
    private boolean isReviewer;
    private boolean isAdmin;

    //  Don't need to generate my constructors, getters and setters or toString() because I used Lombok! :)
}
