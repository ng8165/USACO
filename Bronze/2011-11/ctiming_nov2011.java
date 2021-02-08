// Contest Timing - USACO Bronze November 2011 (http://www.usaco.org/index.php?page=viewproblem2&cpid=84)

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class L1_ctiming {
    public static int ctiming(int endDate, int endHour, int endMinute) {
        if (endDate<11 || endDate>14) return -1;
        if (endHour<0 || endHour>23) return -1;
        if (endMinute<0 || endMinute>59) return -1;

        // Bessie starts at 11 11 11
        int dateDiff=0;
        int hourDiff;
        int minuteDiff;

        if (endDate == 11 && endHour < 11) return -1;
        else if (endDate == 11 && endHour == 11 && endMinute < 11) return -1;

        if (endHour<11) {
            if (endMinute >= 11) {
                minuteDiff = endMinute - 11;
                hourDiff = 24 - 11 + endHour;
            }
            else {
                minuteDiff = 60 - 11 + endMinute;
                hourDiff = 24 - 11 + endHour - 1;
            }
            dateDiff = endDate - 11 - 1;
            return dateDiff*24*60 + hourDiff*60 + minuteDiff;
        }
        else {
            if (endMinute >= 11) {
                minuteDiff = endMinute - 11;
                hourDiff = endHour-11;
            }
            else {
                minuteDiff = 60 - 11 + endMinute;
                hourDiff = endHour - 11 - 1;
            }
            dateDiff = endDate - 11;
            return dateDiff*24*60 + hourDiff*60 + minuteDiff;
        }
    }
    public static int ctiming2(int endDate, int endHour, int endMinute) {
        if (endDate<11 || endDate>14) return -1;
        if (endHour<0 || endHour>23) return -1;
        if (endMinute<0 || endMinute>59) return -1;

        // Plan: Calculate the end time from 11 0 0, then subtract the 11 11 11 start time
        int startDate = 11;
        int startHour = 11;
        int startMinute = 11;

        int dateDiff = endDate - 11;
        int hourDiff = endHour;
        int minuteDiff = endMinute;

        if (endDate == 11 && endHour<11) return -1;
        else if (endDate == 11 && endHour == 11 && endMinute < 11) return -1;

        int time = dateDiff*24*60 + hourDiff*60 + minuteDiff;
        return time - (11*60 + 11);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("ctiming.in"));
        int date = sc.nextInt();
        int hour = sc.nextInt();
        int minute = sc.nextInt();

        int time = ctiming(date, hour, minute);
        //int time2 = ctiming2(date, hour, minute);

        PrintWriter out = new PrintWriter(new FileWriter("ctiming.out"));
        out.println(time);
        //out.println(time2);
        out.close();
    }
}
