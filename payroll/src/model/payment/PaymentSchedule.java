package model.payment;

import model.IValidatable;
import model.common.IdObject;

import java.util.Date;

public abstract class PaymentSchedule extends IdObject implements IValidatable {

    public enum Type {
        MONTHLY(0), WEEKLY(1);

        public final int code;

        Type(int code) {
            this.code = code;
        }

        public static Type fromCode(int code) throws IllegalArgumentException {
            for (Type t : values()) {
                if (t.code == code) return t;
            }
            throw new IllegalArgumentException("Invalid code");
        }
    }

    private final Type type;

    public PaymentSchedule(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public abstract boolean isPayDay(Date targetDate);

}
