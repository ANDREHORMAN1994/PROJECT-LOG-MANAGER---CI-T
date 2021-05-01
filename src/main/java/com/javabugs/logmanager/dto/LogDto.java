package com.javabugs.logmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
public class LogDto {

    private Long id;

    private Date date;

    private String description;

    private String event;

    private Integer quantity;

    @NotEmpty
    private String level;

    @NotEmpty
    private String origin;

}
