package gym;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MembershipPlan {
    private int monthlyPlan;
    private int monthsRegistered;
    private float price;
    private LocalDate startDate;
    private DateTimeFormatter DateFormat;
    private final int Price_3_Times_Per_Week = 300;
    private final int Price_5_Times_Per_Week = 450;
    private final double Discount_3_to_5_months = 0.05;
    private final double Discount_6_to_9_months = 0.10;
    private final double Discount_10_to_12_months = 0.18;

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

    public MembershipPlan() {

        this.DateFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    }

    public MembershipPlan(int monthlyPlan, int monthsRegistered) {
        this.monthlyPlan = monthlyPlan;
        this.monthsRegistered = monthsRegistered;
        Calculate_Price();
    }

    public double getDiscountRate() {
        if (monthsRegistered >= 3 && monthsRegistered <= 5) {
            return Discount_3_to_5_months;
        } else if (monthsRegistered >= 6 && monthsRegistered <= 9) {
            return Discount_6_to_9_months;
        } else if (monthsRegistered >= 10 && monthsRegistered <= 12) {
            return Discount_10_to_12_months;

        }
        return 0;
    }

    public float Calculate_Price() {
        int Month_Price;
        if (monthlyPlan == 3) {
            Month_Price = Price_3_Times_Per_Week;
        } else if (monthlyPlan == 5) {
            Month_Price = Price_5_Times_Per_Week;
        } else {
            throw new IllegalArgumentException("Monthly plan must be 3 or 5 times per week");
        }

        double Total_price = Month_Price * monthsRegistered;
        double Discount_Rate = getDiscountRate();
        double final_Price = Total_price - (Total_price * Discount_Rate);
        this.price = (float) final_Price;
        return this.price;
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

}
