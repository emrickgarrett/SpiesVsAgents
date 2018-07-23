package garyapps.spiesvsagents.Models;

import garyapps.spiesvsagents.Enums.CardStatus;

public class TextCard extends Card<String> {

    public TextCard(String value) {
        this(value, CardStatus.Neutral);
    }

    public TextCard(String value, CardStatus status) {
        this.value = value;
        this.status = status;
    }
}
