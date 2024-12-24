package com.marianbastiurea.lifeofbees.view;

public class HivesView {

    int id;
    int ageOfQueen;
    String honeyType;
    int eggsFrame;
    int honeyFrame;
    private boolean itWasSplit;

    public HivesView(int id, int ageOfQueen, int eggsFrame, int honeyFrame, boolean itWasSplit) {
        this.id = id;
        this.ageOfQueen = ageOfQueen;
        this.eggsFrame = eggsFrame;
        this.honeyFrame = honeyFrame;
        this.itWasSplit = itWasSplit;
    }

    public int getId() {
        return id;
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public String getHoneyType() {
        return honeyType;
    }

    public int getEggsFrame() {
        return eggsFrame;
    }

    public int getHoneyFrame() {
        return honeyFrame;
    }

    public boolean isItWasSplit() {
        return itWasSplit;
    }

    public void setItWasSplit(boolean itWasSplit) {
        this.itWasSplit = itWasSplit;
    }

    @Override
    public String toString() {
        return "HivesView{" +
                "id=" + id +
                ", ageOfQueen=" + ageOfQueen +
                ", honeyType='" + honeyType + '\'' +
                ", eggsFrame=" + eggsFrame +
                ", honeyFrame=" + honeyFrame +
                '}';
    }
}
