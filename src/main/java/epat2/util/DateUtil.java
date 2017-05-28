package epat2.util;

/**
 * Created by ap50864 on 12/31/14.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static final String DATE_FORMAT = "YYYY_MM_dd";

    public static void main(String args[]) throws ParseException {

       // System.out.println("---"+ getWeekNemberByDate(2015, Calendar.JANUARY, 1));
      //  System.out.println("---" + getWeekNemberByDate(2015, Calendar.JANUARY, 31));

      //  getWeekStartEndDatesbyWeekNumber(1, 2015, TimeZone.getDefault());


       /* Integer[] weeksofMonth = getWeeksOfMonth(1, 2015);
        for (Integer week : weeksofMonth) {
            System.out.println(week);
        }*/

        System.out.println(getCurrentDateTimeForImage());

   /*     String[] currentYearWeekDate = getCurrentYearWeekDate(TimeZone.getDefault());

        System.out.println("current Year: "+currentYearWeekDate[0]);
        System.out.println("current week number: "+currentYearWeekDate[1]);
        System.out.println("current date : "+currentYearWeekDate[2]);

        Integer[] yearWeek= getYearWeekByDate(2015, Calendar.JANUARY, 20);
        System.out.println(yearWeek[0]);
        System.out.println(yearWeek[1]);

        yearWeek= getYearWeekByDate(2015, Calendar.JANUARY, 20);
        System.out.println(yearWeek[0]);
        System.out.println(yearWeek[1]);


        getWeekStartEndDates();

        TimeZone tz = TimeZone.getTimeZone("CST");
        getWeekStartEndDatesbyWeekNumber(1,2015,tz);

        tz = TimeZone.getTimeZone("GMT");
        getWeekStartEndDatesbyWeekNumber(1,2015,tz);*/

       // System.out.println(getCurrentDate());
        //System.out.println(getCurrentDateTime());
        /*

        getCurrentMonthWeek(2014, Calendar.DECEMBER, 31);

        getCurrentYearDay(2015, Calendar.JANUARY, 1); // To store images and system reported hours*/

    }

    public static void getWeekStartEndDatesbyWeekNumber(int weekNumber,int year,TimeZone tz) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_YYYY");

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();

        cal.setTimeZone(tz);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        System.out.println(cal.get(Calendar.DAY_OF_WEEK) + "----" + sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_YEAR, 6);
        System.out.println(cal.get(Calendar.DAY_OF_WEEK)+"----"+sdf.format(cal.getTime()));
    }

    public static void getWeekStartEndDates() {
        // set the date
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 1 - 1, 13);

        // "calculate" the start date of the week
        Calendar first = (Calendar) cal.clone();
        first.add(Calendar.DAY_OF_WEEK,
                first.getFirstDayOfWeek() - first.get(Calendar.DAY_OF_WEEK));

        // and add six days to the end date
        Calendar last = (Calendar) first.clone();
        last.add(Calendar.DAY_OF_YEAR, 6);

        // print the result
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        System.out.println(df.format(first.getTime()) + " -> " +
                df.format(last.getTime()));
    }

    public static void getCurrentMonthWeek(int Year,int month, int date) {
        Calendar ca1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        ca1.set(Year, month, date);
        ca1.setMinimalDaysInFirstWeek(1);
        int wk = ca1.get(Calendar.WEEK_OF_MONTH);
        System.out.println("Week of Month :" + wk);
/*        wk = ca1.get(Calendar.DAY_OF_WEEK);
        System.out.println("Week of DAY_OF_WEEK_IN_MONTH  :" + wk);*/
    }

    public static Integer[] getYearWeekByDate(int Year, int month, int date) {
        Calendar ca1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        Integer yearWeek[] = new Integer[2];
        ca1.set(Year, month, date);
        ca1.setMinimalDaysInFirstWeek(1);
        int wk = ca1.get(Calendar.WEEK_OF_YEAR);
        System.out.println(ca1.getWeekYear()+"Week of Year :" + wk);
        yearWeek[0]=ca1.getWeekYear();
        yearWeek[1]=wk;
        return yearWeek;
    }

    public static int getWeekNemberByDate(int Year, int month, int date) {
        Calendar ca1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        ca1.set(Year, month, date);
        ca1.setMinimalDaysInFirstWeek(1);
        System.out.println(ca1.getWeekYear()+"Week of Year :" + ca1.get(Calendar.WEEK_OF_YEAR));
        return ca1.get(Calendar.WEEK_OF_YEAR);
    }

    public static String[] getCurrentYearWeekDate(TimeZone tz){

        String yearWeekDate[] = new String[3];

        Calendar ca1 = Calendar.getInstance(tz);

        ca1.setMinimalDaysInFirstWeek(1);
        int wk = ca1.get(Calendar.WEEK_OF_YEAR);

        yearWeekDate[0]=String.valueOf(ca1.getWeekYear());
        yearWeekDate[1]=String.valueOf(wk);

        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT+" HH:mm:ss z");

        yearWeekDate[2]=String.valueOf(df.format(ca1.getTime()));

        return yearWeekDate;
    }
    public static int getCurrentYearDay(int Year,int month, int date) {
        Calendar ca1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        ca1.set(Year, month, date);
        ca1.setMinimalDaysInFirstWeek(1);
        int wk = ca1.get(Calendar.DAY_OF_YEAR);
        System.out.println("day of Year :" + wk);
        return wk;
    }
    public static String getCurrentDate() {

        Calendar ca1 = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT+" HH_mm_ss z");
       return df.format(ca1.getTime());

    }

    public static String getCurrentDateTime(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        // Use Madrid's time zone to format the date in
        // df.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

        return TimeZone.getDefault().getDisplayName()+"("+ TimeZone.getDefault().getID()+") : "+df.format(date);
    }
    public static String getCurrentDateTimeForImage(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss z");
        // Use Madrid's time zone to format the date in
        // df.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

        return df.format(date);
    }
    public static Integer[] getWeeksOfMonth(int month, int year)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Set<Integer> weeks = new HashSet<Integer>();
        int ndays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < ndays; i++)
        {
            weeks.add(cal.get(Calendar.WEEK_OF_YEAR));
            cal.add(Calendar.DATE, 1);
        }

        return weeks.toArray(new Integer[0]);
    }
}

