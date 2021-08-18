package model;

public final class Syndicate implements IValidatable {

    private int id;
    private String name;
    private double fee;

    public Syndicate(String name, double fee) {
        this.id = -1;
        this.name = name;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
