package com.trustrace.security30.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Readings {
    private Date date;
    private double readings;

    public Readings() {
        date = new Date ();
    }

}
