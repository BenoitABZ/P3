package main.java;

public enum Config {

    MAX_VAL (9),

    MIN_VAL (0),

    MAX_ATTEMPT (15),

    TAB_DIM (6);

    private int number;

    Config(int numb) {

        this.number = numb;
    }

    public int getNumber () {

        return this.number;
    }

}
