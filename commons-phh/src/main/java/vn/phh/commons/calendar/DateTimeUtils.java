package vn.phh.commons.calendar;

import vn.phh.commons.constants.CommonConstants;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public static LocalDateTime now() {
        return Instant.now().atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toLocalDateTime();
    }

    public static LocalDateTime parse(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT);
        return LocalDateTime.parse(dateTime, formatter).atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toLocalDateTime();
    }

    public static String toString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT);
        return localDateTime.format(formatter);
    }

    public static LocalDateTime plusTimes(LocalDateTime ldt, long l){
        Long plus = ldt.atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toInstant().toEpochMilli() + l;
        return Instant.ofEpochMilli(plus).atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toLocalDateTime();
    }

    public static LocalDateTime minusTimes(LocalDateTime ldt, long l){
        Long plus = ldt.atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toInstant().toEpochMilli() - l;
        return Instant.ofEpochMilli(plus).atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toLocalDateTime();
    }

    public static Long minusTimes(LocalDateTime ldt, LocalDateTime ldt1){
        Long plus = ldt.atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toInstant().toEpochMilli() - ldt1.atZone(ZoneId.of(CommonConstants.VIETNAM_TIME_ZONE)).toInstant().toEpochMilli();
        return plus;
    }

}
