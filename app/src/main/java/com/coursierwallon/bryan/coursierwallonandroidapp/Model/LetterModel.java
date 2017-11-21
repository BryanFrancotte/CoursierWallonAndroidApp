package com.coursierwallon.bryan.coursierwallonandroidapp.Model;

/**
 * Created by franc on 21-11-17.
 */

public class LetterModel {

    private long LetterId;
    private boolean isImportant;
    private long orderNumberLetter;
    private OrderModel orderNumberLetterNavigation;

    public LetterModel(long letterId, boolean isImportant, long orderNumberLetter, OrderModel orderNumberLetterNavigation) {
        LetterId = letterId;
        this.isImportant = isImportant;
        this.orderNumberLetter = orderNumberLetter;
        this.orderNumberLetterNavigation = orderNumberLetterNavigation;
    }

    public long getLetterId() {
        return LetterId;
    }

    public void setLetterId(long letterId) {
        LetterId = letterId;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public long getOrderNumberLetter() {
        return orderNumberLetter;
    }

    public void setOrderNumberLetter(long orderNumberLetter) {
        this.orderNumberLetter = orderNumberLetter;
    }

    public OrderModel getOrderNumberLetterNavigation() {
        return orderNumberLetterNavigation;
    }

    public void setOrderNumberLetterNavigation(OrderModel orderNumberLetterNavigation) {
        this.orderNumberLetterNavigation = orderNumberLetterNavigation;
    }
}
