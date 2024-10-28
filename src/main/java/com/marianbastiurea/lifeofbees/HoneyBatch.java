package com.marianbastiurea.lifeofbees;
import java.time.LocalDate;
import java.util.Date;


class HoneyBatch {
    private final int hiveId;
    private final double kgOfHoney;
    private final String  honeyType;

    public HoneyBatch(int hiveId, double kgOfHoney, String honeyType) {
        this.hiveId = hiveId;
        this.kgOfHoney = kgOfHoney;
        this.honeyType = honeyType;
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

    @Override
    public String toString() {
        return "HoneyBatch{" +
                "hiveId=" + hiveId +
                ", kgOfHoney=" + kgOfHoney +
                ", honeyType='" + honeyType + '\'' +
                '}';
    }
}
