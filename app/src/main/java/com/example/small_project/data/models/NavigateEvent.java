package com.example.small_project.data.models;

public class NavigateEvent {
    private final long amount;

    public NavigateEvent(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}
