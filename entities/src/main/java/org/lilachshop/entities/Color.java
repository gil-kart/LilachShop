package org.lilachshop.entities;

import java.io.Serializable;

public enum Color implements Serializable{
    RED("אדום"),
    WHITE("אדום"),
    ORANGE("כתום"),
    BLUE("כחול"),
    GREY("אפור"),
    YELLOW("צהוב"),
    GREEN("ירוק"),
    PURPLE("סגול"),
    PINK("ורוד");

    private String string;

    Color(String name) {
        {string = name;}
    }

    @Override
    public String toString() {
        return string;
    }
}


