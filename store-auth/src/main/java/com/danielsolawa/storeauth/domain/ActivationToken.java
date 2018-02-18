package com.danielsolawa.storeauth.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class ActivationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expireDate;
    @OneToOne(mappedBy = "activationToken")
    private User user;
}
