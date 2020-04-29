public class Government
{  
    private int _maxTreeLevel;

    /**
     * gives the amount of humans per 1.5qm cell
     * #cells = #humans * population_density
     * [source:statista.com]
     */
    public static double population_density = 2.25 * 0.000237;

    public Government(int maxTreeLevel){
        _maxTreeLevel = maxTreeLevel;
    }

    public void setMaxTreeLevel(int maxTreeLevel){
        _maxTreeLevel = maxTreeLevel;
    }

    public int getMaxTreeLevel(){
        return _maxTreeLevel;
    }
    
    
}