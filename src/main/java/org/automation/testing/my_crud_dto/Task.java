package org.automation.testing.my_crud_dto;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonCreator;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnore;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Task {
    @JsonIgnore
    @Getter
    @Setter
    private UUID id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private String deadline;
    @Setter
    @Getter
    private String status;
    @Setter
    @Getter
    private Project project;

    @JsonCreator
    public Task(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("deadline") String deadline,
                @JsonProperty("status") String status,
                @JsonProperty("project") Project project) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.project = project;
    }
}
