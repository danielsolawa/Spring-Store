package com.danielsolawa.storeauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String subject;
    private String from;
    private String to;
    private String text;


}
