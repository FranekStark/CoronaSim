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
     * The Counter of all Persons to set the Identifier
     */
    private static long cnt = 0;

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
     * The actual node the person stands on.
     */
    private LowestNode _actualNode;

    /**
     * Home node of the person. Stays there when sick. 
     * Still can infect other people in this node when contagious.
     */
    private LowestNode _homeNode;


    /**
     * Describes the health status of the person. 
     */
    private HealthStatus _healthStatus;

    /**
     * Describes the level of the Symptoms from COVID-19.
     */
    private SymptomLevel _symptomLevel;

    /**
     * Constructor of healthy Human.
     * @param age: Age of the human. Will be given by an external age distribution.
     * @param isPreDeseased: Shows if the person has a pre-existing lung desease.
     */
    public Human(int age, boolean isPreDeseased)
    {
        _age = age;
        _isPreDeseased = isPreDeseased;
        _healthStatus = HealthStatus.HEALTY;
        _id = cnt;
        _actualNode = _homeNode;
        // use random values!
        _deseaseDuration = 6;
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
     * Returns the duration of the COVID-19 desease.
     * @return the duration of the COVID-19 desease.
     */
    public int getDeseaseDuration()
    {
        return _deseaseDuration;
    }

    /**
     * Returns the health status of the person in regard to COVID-19.
     * @return the actual health status.
     */
    public HealthStatus getHealthStatus()
    {
        return _healthStatus;
    }

    /**
     * Returns the level of the symptoms of the person.
     * @return the level of the symptoms.
     */
    public SymptomLevel getSymptomLevel()
    {
        return _symptomLevel;
    }

    /**
     * Returns the actual node the person stands on.
     * @return the actual node.
     */
    public LowestNode getActualNode()
    {
        return _actualNode;
    }

    /**
     * Returns the home node of the person.
     * @return the home node of the person.
     */
    public LowestNode getHomeNode()
    {
        return _homeNode;
    }

    /**
     * Changes the health status of the person.
     * @param newHealthStatus the new health status.
     */
    public void setHealthStatus(HealthStatus newHealthStatus)
    {
        _healthStatus = newHealthStatus;
    }

    /**
     * Changes the level of symptoms.
     * @param newSymptomsLevel the new level of symptoms.
     */
    public void setSymptomsLevel(SymptomLevel newSymptomLevel)
    {
        _symptomLevel = newSymptomLevel;
    }

    /**
     * Sets a new actual Node of the Person. 
     * Must make sure that next Node is a neighbor of _actualNode!
     * @param nextNode the next node the person will stand on.
     */
    public void setActualNode(LowestNode nextNode)
    {
        _actualNode = nextNode;
    }

    /**
     * Increments the desease duration by a value between 0 and 22. 
     * 2 ticks = 1 day
     * The minimal duration is 3 days (6 ticks) which is a increment 
     * of the default value of 0 ticks.
     * The maximal duration value is 14 days (28 ticks) which is a 
     * of the default value of increment of 22 ticks.
     * @param duration the new desease duration.
     */
    public void incrementDeseaseDuration(int duration)
    {
        _deseaseDuration+= duration;
    }
    @Override
    public int hashCode() 
    {
        return (int) _id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Human){
            Human that = (Human) o;
            return this._id == that._id;
        }
        return false;
    }
}
