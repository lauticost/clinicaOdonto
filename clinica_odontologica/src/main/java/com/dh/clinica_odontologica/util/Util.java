package com.dh.clinica_odontologica.util;

import java.sql.Timestamp;
import java.util.Date;

public class Util {
    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }
}
