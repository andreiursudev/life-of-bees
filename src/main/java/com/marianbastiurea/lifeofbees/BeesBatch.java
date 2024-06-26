package com.marianbastiurea.lifeofbees;


import java.util.Date;

public class BeesBatch {
    private int numberOfBees;
    private final Date creationDate;

    public BeesBatch(int numberOfBees, Date creationDate) {
        this.numberOfBees = numberOfBees;
        this.creationDate = creationDate;
    }

    public int getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(int numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "BeesBatch{" +
                "numberOfBees=" + numberOfBees +
                ", creationDate=" + creationDate +
                '}'+ "\n";
    }
}
