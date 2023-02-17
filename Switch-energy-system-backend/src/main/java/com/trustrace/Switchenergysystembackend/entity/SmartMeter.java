package com.trustrace.Switchenergysystembackend.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Smart_meters")
public class SmartMeter {
    @Id
    private String id;
    @NotEmpty(message = "Enter meter id")
    @Field("meter_id")
    private String meterId;
    @Field("email_id")
    @NotEmpty()
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "must be a valid id")
    private String emailId;
    @Field("provider_id")
    @NotEmpty(message = "Enter provider id")
    private String providerId;
    private String status;
    private List<Reading> readings;
}