package me.storeka.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResponse {

    protected LocalDateTime timestamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String exception;
    protected String clientMessage;
    protected String developerMessage;
    protected Object body;

}
