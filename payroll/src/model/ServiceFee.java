package model;

public final class ServiceFee implements IValidatable {

    private String name;
    private double fee;

    public ServiceFee(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean validate() {
        return !name.trim().isEmpty() && fee > 0;
    }

}
