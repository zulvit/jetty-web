package ru.zulvit.entity;

public class Invoice {
    private final String name;
    private final String firm;
    private final String amount;

    public Invoice(String name, String firm, String amount) {
        this.name = name;
        this.firm = firm;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getFirm() {
        return firm;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", firm='" + firm + '\'' +
                ", amount='" + amount + '\'' +
                '}' + "\n";
    }
}
