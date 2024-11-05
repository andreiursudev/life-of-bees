package com.marianbastiurea.lifeofbees;

import java.time.LocalDate;


class HoneyBatch {
    private final int hiveId;
    private final double kgOfHoney;
    private final String honeyType;
    private boolean isProcessed;

    public HoneyBatch(int hiveId, double kgOfHoney, String honeyType, boolean isProcessed) {
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

    public String getHoneyType() {
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