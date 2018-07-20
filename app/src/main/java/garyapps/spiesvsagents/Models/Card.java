package garyapps.spiesvsagents.Models;

import garyapps.spiesvsagents.Enums.CardStatus;

public abstract class Card<T> {
    protected CardStatus status;
    protected T value;

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public CardStatus getStatus() {
        return this.status;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
