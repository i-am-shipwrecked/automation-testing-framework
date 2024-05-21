package org.automation.testing.my_crud_dto;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonCreator;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Project {
    @JsonIgnore
    @Setter
    @Getter
    private UUID id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private String beginning;
    @Setter
    @Getter
    private String ending;
    @Setter
    @Getter
    private List<Task> tasksList;
    @Setter
    @Getter
    private List<User> users;

    @JsonCreator
    public Project(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("beginning") String beginning,
                   @JsonProperty("ending") String ending,
                   @JsonProperty("tasksList") List<Task> tasksList,
                   @JsonProperty("users") List<User> users) {
        this.name = name;
        this.description = description;
        this.beginning = beginning;
        this.ending = ending;
        this.tasksList = tasksList;
        this.users = users;
    }
}
