package gym;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.time.temporal.*;

public class MembershipPlan {
    private int monthlyPlan;
    private int monthsRegistered;
    private float price;
    private LocalDate startDate;
    private DateTimeFormatter DateFormat;

    public void SetmonthlyPlan(int monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public int GetmonthlyPlan() {
        return monthlyPlan;
    }

    public void SetmonthsRegistered(int monthsRegistered) {
        this.monthsRegistered = monthsRegistered;
    }

    public int GetmonthsRegistered() {
        return monthsRegistered;
    }

    public void price(float price) {
        this.price = price;
    }

    public float Getprice() {
        return price;
    }

    public void SetstartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate GetstartDate() {
        return startDate;
    }

    public void SetDateFormat(DateTimeFormatter DateFormat) {
        this.DateFormat = DateFormat;
    }

    public DateTimeFormatter GetDateFormat() {
        return DateFormat;
    }

    public int SubtractingDateByDays(LocalDate D1, LocalDate D2) {
        if (D1 == null || D2 == null) {
            throw new IllegalArgumentException("Date Can't To Be null ");
        }
        long Diff = ChronoUnit.DAYS.between(D1, D2);
        return (int) Diff;
    }

    public int SubtractingDateByMonth(LocalDate D1, LocalDate D2) {
        if (D1 == null || D2 == null) {
            throw new IllegalArgumentException("Date Can't To Be null ");
        }
        long Diff = ChronoUnit.MONTHS.between(D1, D2);
        return (int) Diff;
    }

    public MembershipPlan() {

    }

    public MembershipPlan(int monthlyPlan, int monthsRegistered, float price) {
        this.monthlyPlan = monthlyPlan;
        this.monthsRegistered = monthsRegistered;
        this.price = price;
    }

}
