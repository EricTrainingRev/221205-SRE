package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernamePasswordAuthentication {

    private String username;
    private String password;
}
