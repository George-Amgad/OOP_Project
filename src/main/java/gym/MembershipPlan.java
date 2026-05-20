package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MembershipPlan {
    private int monthlyPlan;
    private int monthsRegistered;
    private double price;
    private LocalDate startDate;
    private static final int PRICE_3_TIMES_PER_WEEK = 300;
    private static final int PRICE_5_TIMES_PER_WEEK = 450;
    private static final double DISCOUNT_3_TO_5_MONTHS = 0.05;
    private static final double DISCOUNT_6_TO_9_MONTHS = 0.10;
    private static final double DISCOUNT_10_TO_12_MONTHS = 0.18;

    @JsonCreator
    public MembershipPlan(@JsonProperty("monthlyPlan") int monthlyPlan,
                          @JsonProperty("monthsRegistered") int monthsRegistered) {
        setMonthlyPlan(monthlyPlan);
        setMonthsRegistered(monthsRegistered);
        calculatePrice();
    }

    public void setMonthlyPlan(int monthlyPlan) {
        this.monthlyPlan = monthlyPlan;
    }

    public int getMonthlyPlan() {
        return monthlyPlan;
    }

    public void setMonthsRegistered(int monthsRegistered) {
        this.monthsRegistered = monthsRegistered;
    }

    public int getMonthsRegistered() {
        return monthsRegistered;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double getDiscountRate() {
        if (monthsRegistered >= 3 && monthsRegistered <= 5) {
            return DISCOUNT_3_TO_5_MONTHS;
        } else if (monthsRegistered >= 6 && monthsRegistered <= 9) {
            return DISCOUNT_6_TO_9_MONTHS;
        } else if (monthsRegistered >= 10 && monthsRegistered <= 12) {
            return DISCOUNT_10_TO_12_MONTHS;
        }
        return 0;
    }

    public double calculatePrice() {
        int monthPrice;
        if (monthlyPlan == 3) {
            monthPrice = PRICE_3_TIMES_PER_WEEK;
        } else if (monthlyPlan == 5) {
            monthPrice = PRICE_5_TIMES_PER_WEEK;
        } else {
            throw new IllegalArgumentException("Monthly plan must be 3 or 5 times per week");
        }
        double totalPrice = monthPrice * monthsRegistered;
        double discountRate = getDiscountRate();
        price = totalPrice - (totalPrice * discountRate);
        return price;
    }

    public int subtractDateByDays(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return (int) ChronoUnit.DAYS.between(date1, date2);
    }

    public int subtractDateByMonths(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return (int) ChronoUnit.MONTHS.between(date1, date2);
    }

    public String toString(){
        String result = "Membership Plan:\n\tMonthly Plan: " + monthlyPlan
        + "\n\tMonths Registered: " + monthsRegistered + "\n\tPrice: "
        + price + "Start Date: " + startDate;
        return result;
    }
}
