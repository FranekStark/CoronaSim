import java.util.Random;
/**
 * Gives random ints to find random direction and probability of infection.
 * Methods of this class will give random directions or a random boolean weighted by a 
 * specific probability.
 * 
    * Find a way to create only one object of this class?
    * (Now: every method call gives a new dice.)
 *
 * @author Franek Stark, Finn Welzmüller
 * @version 01.04.2020
 */
public class RandomCounts{
    /**
     * Constructor of the RandomCounts class. 
     */
    public RandomCounts()
    {
    }

    /**
     * Gives a random int beween 0 and 3 for a humans decision while moving to the next neighbor node.
     * 0 = moving to the top neighbor;
     * 1 = moving to the bottom neighbor;
     * 2 = moving to the left neighbor;
     * 3 = moving to the right neighbor;
     * @return a random int between 0 and 3
     */
    public static int giveDirection()
    {
        Random dice = new Random();
        int direction = dice.nextInt(4);
        return direction;
    }

    /**
     * Gives a statement e.g. whether a person is infected or not weighted by a specific likelihood.
     * The boundaries will determine the probability.
     * I.E. lowerBound = 0; upperbound = 50 gives a probability of 50% as well as well as lowerBound = 25; upperBound = 75.
     * @param lowerBound the lower bound of the interval for the random int to give a true statement.
     * @param upperBoundthe the upper bound of the interval for the random int to give a true statement.
     * @return the statement if the random number is between the boundaries or not.
     */
    public static boolean giveStatement(int lowerBound, int upperBound)
    {
        Random dice = new Random();
        boolean state = false;
        if(lowerBound < 0 || lowerBound > 100 || upperBound < 0 || upperBound > 100)
        {
            throw new IllegalArgumentException("Borders must be beween 0 and 100");
        }
        if(lowerBound > upperBound)
        {
            throw new IllegalArgumentException("Lower border must be smaller than upper border");
        }        
        int count = dice.nextInt(101);
        if(lowerBound <= count && count <= upperBound)
        {
            state = true;
        }
        return state;
    }
}