package com.maxtrain.prsspringboot.entities.requests;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthenticateRequest {
    private String userName;
    private String password;

    //  Don't need to generate my constructors, getters and setters or toString() because I used Lombok! :)
}
