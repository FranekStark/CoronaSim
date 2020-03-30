import java.util.*;
/**
 * Representates a single human beeing with a specific age and identifier.
 * A possible pre-existing illness and the age can affect the course of desease
 * as well as the age. A person has also a home node to stay in when ill.
 * A person can be healthy, contagious (no symptoms but can infect other people 
 * sitting in the same node), ill (with symptoms and affection of the movement),
 * recovered (healthy and imune to COVID-19) or dead. 
 * The person can have mild symptoms (quarantine at home), heavy symptoms 
 * (quarantine in hospital and age-depending chance to die) or no symptoms at all.
 * 
 * 
 * 
 * @author (Franek Stark, Finn Welzmüller) 
 * @version (30.03.2020)
 */
public class Human
{
    /**
     * The identifier of the person
     */
    private final long _id;

    /**
     * The age of the person determined by an age distribution. 
     * The age affects the course of desease and the likelihood of death.
     */
    private final int _age;

    /**
     * The possible pre-existing lung desease can affect the ouse of desease.
     */
    private boolean _isPreDeseased;

    /**
     * The duration of the COVID-19 desease.
     */
    private int _deseaseDuration;

    /**
     * Home node of the person. Stays there when sick. 
     * Still can infect other people in this node when contagious.
     */
    private LowestNode _homeNode;

    /**
     * Describes the health status of the person. 
     * HEALTHY       - Not infected with COVID-19. No influence on initial movement.
     * CONTAGIOUS    - Infected and contagious but no symptoms, hence no influence on initial movement.
     * ILL           - Infected with symptoms (level given by _symptomsLevel). Initial movement is affected.
     * RECOVERED     - Not infected and sick anymore. Can not be infected again.
     * DEAD          - person will be deleted.
     */
    public enum _healthStatus{HEALTY, CONTAGIOUS, ILL, RECOVERED, DEAD};

    /**
     * Describes the level of the Symptoms from COVID-19.
     * NO - No Symptoms, can be contragious or not, no affection of the movement.
     * MILD - Mild Symptoms, person stays in home node as long as infected.
     * HEAVY - Heavy Symptoms, person goes to a hospital and can die.
     */
    public enum _symptomsLevel{NO, MILD, HEAVY};

    /**
     * Constructor of healthy Human.
     * @param age: Age of the human. Will be given by an external age distribution.
     * @param isPreDeseased: Shows if the person has a pre-existing lung desease.
     */
    public Human(int age, boolean isPreDeseased)
    {
        _age = age;
        _isPreDeseased = isPreDeseased;
        _healthStatus = HEALTHY;

    }

    /**
     * Returns the age of the person.
     * @return the age of the human
     */
    public int getAge()
    {
        return _age;
    }

    /**
     * Returns if the person has a pre-existing lung desease (not COVID-19).
     * @return the person has a pre-existing lung desease or not.
     */
    public boolean isPreDeseased()
    {
        return _isPreDeseased;
    }

    /**
     * Returns the health status of the person in regard to COVID-19.
     * @return the actual health status.
     */
    public _healthStatus getHealthStatus()
    {
        return _healthStatus;
    }
    /**
     * Returns the level of the symptoms of the person.
     * @return the level of the symptoms.
     */
    public _symptomsLevel getSymptomsLevel()
    {
        return _symptomsLevel;
    }

    /**
     * Changes the health status of the person.
     * @param newHealthStatus the new health status.
     */
    public void setHealthStatus(_healthStaus newHealthStatus)
    {
        _healthStatus = newHealthStatus;
    }
    /**
     * Changes the level of symptoms.
     * @param newSymptomsLevel the new level of symptoms.
     */
    public void setSymptomsLevel(_symptomsLevel newSymptomsLevel)
    {
        _symptomsLevel = newSymptomsLevel;
    }
}
