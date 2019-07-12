package com.strumski.foaas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoaasResponse {
    private String message;
    private String subtitle;
}
