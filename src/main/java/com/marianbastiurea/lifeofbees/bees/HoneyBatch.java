package com.marianbastiurea.lifeofbees.bees;


//TODO Clasa asta este doar o structura intermediara dintre HoneyFrames si HarvestedHoney. Sterge clasa HoneyBatch si transforma direct din HoneyFrames in HarvestedHoney
public class HoneyBatch {
    private final int hiveId;
    private final double kgOfHoney;
    private final HoneyType honeyType;
    private boolean isProcessed;

    public HoneyBatch(int hiveId, double kgOfHoney, HoneyType honeyType, boolean isProcessed) {
        this.hiveId = hiveId;
        this.kgOfHoney = kgOfHoney;
        this.honeyType = honeyType;
        this.isProcessed = isProcessed;
    }

    public int getHiveId() {
        return hiveId;
    }

    public double getKgOfHoney() {
        return kgOfHoney;
    }

    public HoneyType getHoneyType() {
        return honeyType;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    @Override
    public String toString() {
        return "HoneyBatch{" +
                "hiveId=" + hiveId +
                ", kgOfHoney=" + kgOfHoney +
                ", honeyType='" + honeyType + '\'' +
                ", isProcessed=" + isProcessed +
                '}';
    }
}