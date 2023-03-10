package com.exchangeinformant.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name implements Serializable {
    @JsonProperty("secur_code")
    private String secureCode;
    @JsonProperty("issuer")
    private String issuer;
    @JsonProperty("currency")
    private String currency;
}
