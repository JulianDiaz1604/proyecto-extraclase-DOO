package edu.uco.artdly.crosscutting.helper;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;


public final class DateHelper {
    private static final LocalDate defaultDate = LocalDate.of(0001, 1, 1);

    private DateHelper() {
        
    }
    
    public  static final short getNextYear() {
        return  (short) LocalDate.now().plusYears(1).getYear();
    }
    public static final Date getDeafultDate() { 
        return Date.valueOf(defaultDate);
    }
    public static final Date getNow() {
        return Date.valueOf(LocalDate.now());
    }
    public static final int yearsOld(Date birthdate) {
        Date now = Date.valueOf(LocalDate.now());
        
        Period year = Period.between(birthdate.toLocalDate(), now.toLocalDate());
        return year.getYears();

    }
    public static final boolean isMinor(Date birthdate){
        return yearsOld(birthdate) < 18;
    }
    
}

