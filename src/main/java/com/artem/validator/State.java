package com.artem.validator;

public class State {

    private int state;
    private int bufState;

    public State(int state){
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public int getBufState() {return bufState;}

    public void setBufState() {
        bufState = state;
    }

    public void setState(int state){
        this.state = state;
    }
}
