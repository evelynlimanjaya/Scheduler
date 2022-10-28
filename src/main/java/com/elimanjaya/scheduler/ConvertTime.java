package com.elimanjaya.scheduler;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ConvertTime {
    //Getting the LocalDateTime Objects from String values
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.S");

    //Showing how to parse the Date/Time String
    DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm:ss.S");

    //Getting the day of the week
//		System.out.println(ldtStart.getDayOfWeek());
//
//    //Convert to a ZonedDate Time in UTC
//    ZoneId zid = ZoneId.systemDefault();
//
//    ZonedDateTime zdtStart = ldtStart.atZone(zid);
//		System.out.println("Local Time: " + zdtStart);
//    ZonedDateTime utcStart = zdtStart.withZoneSameInstant(ZoneId.of("UTC"));
//		System.out.println("Zoned time: " + utcStart);
//    ldtStart = utcStart.toLocalDateTime();
//		System.out.println("Zoned time with zone stripped:" + ldtStart);
//    //Create Timestamp values from Instants to update database
//    Timestamp startsqlts = Timestamp.valueOf(ldtStart); //this value can be inserted into database
//		System.out.println("Timestamp to be inserted: " +startsqlts);

        public LocalDate getLocalDate(LocalDateTime ldt){
            LocalDate localDate = LocalDate.parse(ldt.toString().substring(0, 10), dFormatter);
            return localDate;
        }

        public LocalTime getLocalTime(LocalDateTime ldt){
            LocalTime localTime = LocalTime.parse(ldt.toString().substring(11), tFormatter);
            return localTime;
        }

        public String getDay(LocalDateTime ldt){
            String dayOfTheWeek = String.valueOf(ldt.getDayOfWeek());
            return dayOfTheWeek;
        }

//        public ZonedDateTime ESTtoLocal(LocalDateTime ldt){
//
////            ZonedDateTime ESTTime = ZonedDateTime.of(LocalDateTime, ZoneId.of("EST"));
//            return ESTTime;
//        }
}
