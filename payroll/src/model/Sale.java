package model;

import java.util.Date;

public final class Sale implements IValidatable {

    private Date date;
    private double price;

    public Sale(Date date, double price) {
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean validate() {
        return price > 0;
    }

}
