import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    public static int giveRandomNumber(int bound)
    {
        Random dice = new Random();
        int direction = dice.nextInt(bound);
        return direction;
    }

    static Direction getRandomDirection(){
        return Direction.values()[giveRandomNumber(4)];
    }

    static <T> T getRandomElement(Set<T> element){
        List<T> elementlist = new ArrayList<T>(element);
        Collections.shuffle(elementlist);
        return elementlist.get(0);
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