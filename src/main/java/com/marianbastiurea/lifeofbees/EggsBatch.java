package com.marianbastiurea.lifeofbees;
import java.util.Date;

public class EggsBatch {
  private int numberOfEggs;
   private Date creationDate;
   public EggsBatch(int numberOfEggs, Date creationDate) {
       this.numberOfEggs = numberOfEggs;
       this.creationDate = creationDate;
   }

    public int getNumberOfEggs() {
        return numberOfEggs;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "EggsBatch{" +
                "numberOfEggs=" + numberOfEggs +
                ", creationDate=" + creationDate +
                '}';
    }
}
