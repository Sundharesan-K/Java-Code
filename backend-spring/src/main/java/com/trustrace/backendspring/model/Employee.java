package com.trustrace.backendspring.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "employee")
public class Employee {
    @Id
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private int age;
    @NonNull
    private String salary;
    @NonNull
    private double experience;
    @NonNull
    private List<String> skills;
    @NonNull
    @Field("joined_date")
    private String joinedDate;

}
