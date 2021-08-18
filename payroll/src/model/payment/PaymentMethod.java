package model.payment;

public enum PaymentMethod {

    CHECK_CORREIOS(0, "Cheque Correios"),
    CHECK_HAND(1, "Cheque Em Mãos"),
    BANK_DEPOSIT(2, "Depósito Bancário"),
    ;

    public final int code;
    public final String name;

    PaymentMethod(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PaymentMethod valueOfCode(int code) {
        for (PaymentMethod m : values()) {
            if (code == m.code) return m;
        }
        throw new IllegalArgumentException("Invalid code");
    }

}
