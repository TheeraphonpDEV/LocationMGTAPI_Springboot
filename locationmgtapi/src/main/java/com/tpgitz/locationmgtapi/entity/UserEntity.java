package com.tpgitz.locationmgtapi.entity;


import lombok.*;

import javax.persistence.*;

@Entity(name = "USER_ENTITY_TABLE")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "FULL_NAME")
    private String fullName;
    private String mobileNumber;
    private String email;
    private String password;

}
