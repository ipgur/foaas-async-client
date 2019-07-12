package com.strumski.foaas.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Operation {
    private String name;
    private String url;
}
