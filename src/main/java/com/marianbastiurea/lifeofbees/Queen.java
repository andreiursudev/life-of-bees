package com.marianbastiurea.lifeofbees;

/*
When the honeybee colony senses that it needs a new queen, perhaps because the queen is ailing or is preparing to
swarm, the worker bees will begin the process of raising new queen bees.
Feed with royal jelly.
Queen cell is place where new queen develops.
When the queen bee larva is ready to pupate (metamorphosize) into a queen, the worker bees will cap the cell, and
she will transform inside it.  At day 15, when she is fully developed, she will chew her way out of the cell with
the help of a few worker bees.
After the queen chews her way out of her queen cell, she has some dirty work to do...she instinctively sets out to
kill her sisters....the other new queens.
Once the battling is done, the queen will take a mating flight...a one-time flight from the hive to mate
with male drones.
After mating, she will return to the hive.  If her mother (the old queen) is still in the hive and nearing the end
of her life, the new queen will kill her (called 'supercedure').  If the old queen has left with a swarm, the new
queen will take over laying eggs.
In the summer, she can lay up to 2000 eggs a day!
A drone is a male bee. Unlike the female worker bee, a drone has no stinger.
 */

public class Queen {
    private int ageOfQueen;

    public Queen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }


    public Queen() {
    }

    public int getAgeOfQueen() {
        return ageOfQueen;
    }

    public void setAgeOfQueen(int ageOfQueen) {
        this.ageOfQueen = ageOfQueen;
    }


    public int makeEggs(Honey honey, double whetherIndex) {
        /*
        this method will calculate number of daily laying eggs by queen. will used two factors for this:
        - first will be index about age of queen and honey productivity;
        - second will be an whether index connected with wind speed, precipitation and temperature
         */

        int numberOfEggs = (int) (2900 * this.ageOfQueenIndex()*honey.honeyProductivity() * whetherIndex);
        return numberOfEggs;

    }

    public double ageOfQueenIndex() {
        /* a queen lives 3-5 years. When will build first 10 hives in apiary will use random to generate ageOfQueen
         between 1  and 5 years old for our queen. At age 5, will have to replace this queen with new one.
         Depending on age of queen will choose an fertility index between 0.25 and 1. When index is 0.25, queen is too old
         to lay eggs and she have to be replaced. When index is 1,fertility of queen is at maximum and she can lay
         upon 2000 eggs daily
         */

        int ageOfQueen = this.getAgeOfQueen();

        switch (ageOfQueen) {
            case 0, 1, 2, 3:
                return 1;
            case 4:
                    return 0.75;
            default:
                break;
        }
        return 0;
    }
}
