public class Government
{  
    private int _maxTreeLevel;

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