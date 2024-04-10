package org.automation.testing.my_crud_dto;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonCreator;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class User {
    @JsonIgnore
    private String userId;
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String password;


    @JsonCreator
    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

}

