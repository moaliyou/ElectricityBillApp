package com.example.electricitybillapp.classes;

public class ElectricityBill {

    private long lastMonthReading;
    private long currentMonthReading;
    private double rateCharge;
    private int kilowatt;
    private double billAmount;

    public ElectricityBill() {
        this.lastMonthReading = 0;
        this.currentMonthReading = 0;
        this.rateCharge = 0;
        this.kilowatt = 0;
        this.billAmount = 0;
    }

    public ElectricityBill(long lastMonthReading, long currentMonthReading, double rateCharge) {
        this.lastMonthReading = lastMonthReading;
        this.currentMonthReading = currentMonthReading;
        this.rateCharge = rateCharge;
    }

    public void setLastMonthReading(long lastMonthReading) {
        this.lastMonthReading = lastMonthReading;
    }

    public void setCurrentMonthReading(long currentMonthReading) {
        this.currentMonthReading = currentMonthReading;
    }

    public void setRateCharge(double rateCharge) {
        this.rateCharge = rateCharge;
    }

    public long getLastMonthReading() {
        return lastMonthReading;
    }

    public long getCurrentMonthReading() {
        return currentMonthReading;
    }

    public double getRateCharge() {
        return rateCharge;
    }

    public int getKilowatt() {
        kilowatt = (int) Math.abs(getCurrentMonthReading() - getLastMonthReading());
        return kilowatt;
    }

    public double getBillAmount() {
        billAmount = getKilowatt() * getRateCharge();
        return  billAmount;
    }
}
