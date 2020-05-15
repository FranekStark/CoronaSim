/**
 * The Government class represents the government of the nodes. The government can change the lockdown level of the nodes. 
 * The lockdown level can changed manually or automaticly depending on the reproduction number or the incidence.
 * The boundary values can be changed.
 * @author: Franek Stark, Finn Welzmüller
 */
public class Government implements Tickable
{  
    private int _maxTreeLevel;
    
    private int _maximalTreeLevel;

    /**
     * sets the Lockdown level to auto (true) or to manual (false)
     */
    public boolean _autosetLockdownLevel;

    /**
     * sets the dependence in autolockdownlevel to reproduction number (true) or to incidence (false)
     */
    public boolean _repNumberDependence;

    /**
     * Sets the boundaries of the different lockdown levels in terms of the reproduciton number.
     */
    public static double repNumberLvL1 = 1.0;
    public static double repNumberLvL2 = 1.5;
    public static double repNumberLvL3 = 2.0;
    public static double repNumberLvL4 = 2.5;
    public static double repNumberLvL5 = 3.0;

    /**
     * Sets the boundaries of the different lockdown levels in terms of the incidence.
     */
    public static long incidenceNumberLvL1 = 50;
    public static long incidenceNumberLvL2 = 150;
    public static long incidenceNumberLvL3 = 250;
    public static long incidenceNumberLvL4 = 350;
    public static long incidenceNumberLvL5 = 450;

    /**
     * gives the amount of humans per 1.5qm cell
     * #cells = #humans * population_density
     * [source:statista.com]
     */
    public static double population_density = 2.25 * 0.000237;
     
    /**
     * constructor for the Government class
     * @param maxTreeLevel the maximal Treelevel
     */
    public Government(int maxTreeLevel, boolean autosetLockdownLevel, boolean repNumberDependence){
        _maxTreeLevel = maxTreeLevel;
        _maximalTreeLevel = maxTreeLevel;
        _autosetLockdownLevel = autosetLockdownLevel;
        _repNumberDependence = repNumberDependence;
    }
    /**
     * set another maximal tree level
     * @param maxTreeLevel
     */
    public void setMaxTreeLevel(int maxTreeLevel){
        _maxTreeLevel = maxTreeLevel;
    }

    /**
     * returns the actual maximal treelevel
     * @return the actual maximal treelevel
     */
    public int getMaxTreeLevel(){
        return _maxTreeLevel;
    }

    /** 
     * autosets the LockdownLevel depending on ether the reproduction number or the incidence.
     * inputboolean = true for dependence of the reproduction number
     * inputboolean = false for dependence of the incidence.
     * 
    */
    public void changeLockdownLevel(boolean repNumberTrue){
        if (repNumberTrue)
        {
            // Reproduction Number dependence...
            if(Simulation.giveReproductionNumber() > repNumberLvL1)
            {
                setMaxTreeLevel((int)(0.8 *_maximalTreeLevel));
            }
            if(Simulation.giveReproductionNumber() > repNumberLvL2)
            {
                setMaxTreeLevel((int)(0.6 * _maximalTreeLevel));
            }
            if(Simulation.giveReproductionNumber() > repNumberLvL3)
            {
                setMaxTreeLevel((int)(0.4 * _maximalTreeLevel));
            }
            if(Simulation.giveReproductionNumber() > repNumberLvL4)
            {
                setMaxTreeLevel((int)(0.2 * _maximalTreeLevel));
            }
            if(Simulation.giveReproductionNumber() > repNumberLvL5)
            {
                setMaxTreeLevel(1);
            }
        }
        else
        {
            //Incidence dependence...
            if(Simulation.getIncidence() > incidenceNumberLvL1)
            {
                setMaxTreeLevel((int)(0.8 *_maximalTreeLevel));
            }
            if(Simulation.getIncidence() > incidenceNumberLvL2)
            {
                setMaxTreeLevel((int)(0.6 *_maximalTreeLevel));
            }
            if(Simulation.getIncidence() > incidenceNumberLvL3)
            {
                setMaxTreeLevel((int)(0.4 *_maxTreeLevel));
            }
            if(Simulation.getIncidence() > incidenceNumberLvL4)
            {
                setMaxTreeLevel((int)(0.2 *_maximalTreeLevel));
            }
            if(Simulation.getIncidence() > incidenceNumberLvL5)
            {
                setMaxTreeLevel(1);
            }
        }
    }

    /**
     * sets the lockdown level manually. Level will be decided via parameter.
     * @param level must be between 0 and 5
     */
    public void setLockdownLevel(int level){
        if((level < 0) || (level < 5))
        {
            throw new IllegalArgumentException("Lockdown level must be between 0 and 5!");
        }
        switch(level){
            case 0:
                setMaxTreeLevel(_maximalTreeLevel);
            case 1:
                setMaxTreeLevel((int)(0.8 *_maximalTreeLevel));
            case 2:
                setMaxTreeLevel((int)(0.6 *_maximalTreeLevel));
            case 3: 
                setMaxTreeLevel((int)(0.4 *_maximalTreeLevel));
            case 4:
                setMaxTreeLevel((int)(0.2 *_maximalTreeLevel));
            case 5:
                setMaxTreeLevel(1);
        }
    }
    /**
     * Tick method for the Government. If autosetLockdownLevel is true, check Lockdownlevel
     */
    public void tick()
    {   
        if(_autosetLockdownLevel)
        {
            changeLockdownLevel(_repNumberDependence);
        }
    }
}