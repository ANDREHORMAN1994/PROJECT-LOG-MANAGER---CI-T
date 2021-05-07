package com.javabugs.logmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private Long id;
    private Date date;
    private String description;
    private String event;
    private Integer quantity;
    private String level;
    private String origin;

}
