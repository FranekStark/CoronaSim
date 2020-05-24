package Simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.lang.Math;
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

    private static Random dice = new Random();

    /**
     * Constructor of the RandomCounts class. 
     */
    public RandomCounts()
    {
    }
    /**
     * gives a random number under a given boundary
     */
    public static int giveRandomNumber(int bound)
    {
        int direction = dice.nextInt(bound);
        return direction;
    }
    /**
     * gives a random direction for moving.
     */
    static Direction getRandomDirection(){
        return Direction.values()[giveRandomNumber(4)];
    }
    /**
     * gives a random element of a set with elements.
    */
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

    /**
     * returns gaussian random number with
     * @param mean 
     * @param std
     * @return gaussian distributed value
     */
    public static double giveGaussianValue(double mean, double std)
    {
        return mean + std*dice.nextGaussian();
    }
    /**
     * Gives the likelihood of death when having heavy symptoms. depends iter alia of the overall medical situation.
     * @param age age of the person.
     * @param isPreDeseased gives information if the person is predeseased.
     * @param hasITS gives informaiton if the person has a medical treatment in a hospital,
     * @return probability of death.
     */
    public static double death_probability(int age, boolean isPreDeseased, boolean hasITS){
        int predeseased = 0;
        int ITS = 0;
        if(isPreDeseased)
        {
            predeseased = 1;
        }
        if(hasITS)
        {
            ITS = 1;
        }
        return (MedicalSettings.medical_factor/0.9) * (8/646) * 1/(Math.exp((-age - 50 - 25 * predeseased)/10) + 1) - ITS * (1.28/646);
        
    }

    public static double symptoms_probability(int age, boolean isPreDeseased){
        int predeseased = 0;
        if (isPreDeseased)
        {
            predeseased = 1;
        }
        return (32/4893) * 1/(Math.exp((- age - 50 - 25 * predeseased)/20) + 1);
    }

  


}