package model.employee;

import model.IValidatable;
import model.ServiceFee;
import model.common.IdObject;
import model.payment.PaymentMethod;
import model.payment.PaymentSchedule;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee extends IdObject implements IValidatable {

    public enum Type {
        HOURLY, SALARIED, COMMISSIONED;
    }

    private String name;
    private String address;
    private PaymentMethod paymentMethod;
    private PaymentSchedule paymentSchedule;
    private final Type type;
    private final List<ServiceFee> serviceFees;
    private boolean isOnSyndicate;
    private double syndicateFee;
    private String syndicateId;

    public Employee(
        String name,
        String address,
        PaymentMethod paymentMethod,
        PaymentSchedule paymentSchedule,
        Type type
    ) {
        super();
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.paymentSchedule = paymentSchedule;
        this.type = type;
        this.serviceFees = new ArrayList<>();
        this.isOnSyndicate = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public Type getType() {
        return type;
    }

    public List<ServiceFee> getServiceFees() {
        return serviceFees;
    }

    public void addServiceFee(ServiceFee serviceFee) {
        serviceFees.add(serviceFee);
    }

    public boolean isOnSyndicate() {
        return isOnSyndicate;
    }

    public void setOnSyndicate(boolean onSyndicate) {
        this.isOnSyndicate = onSyndicate;
    }

    public double getSyndicateFee() {
        return syndicateFee;
    }

    public void setSyndicateFee(double syndicateFee) {
        this.syndicateFee = syndicateFee;
    }

    public String getSyndicateId() {
        return syndicateId;
    }

    public void setSyndicateId(String syndicateId) {
        this.syndicateId = syndicateId;
    }

    @Override
    public boolean validate() {
        return !name.trim().isEmpty() && !address.trim().isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name=%s, address=%s, payment_method=%s, type=%s}",
            getId(), name, address, paymentMethod, type);
    }

}
