package br.com.ottimizza.apiemailsendler.domain.responses;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

}