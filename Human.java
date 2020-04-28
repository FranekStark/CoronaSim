import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Representates a single human beeing with a specific age and identifier. A
 * possible pre-existing illness and the age can affect the course of desease as
 * well as the age. A person has also a home node to stay in when ill. A person
 * can be healthy, contagious (no symptoms but can infect other people sitting
 * in the same node), ill (with symptoms and affection of the movement),
 * recovered (healthy and imune to COVID-19) or dead. The person can have mild
 * symptoms (quarantine at home), heavy symptoms (quarantine in hospital and
 * age-depending chance to die) or no symptoms at all.
 * 
 * 
 * 
 * @author (Franek Stark, Finn Welzmüller)
 * @version (30.03.2020)
 */
public class Human implements Tickable {

    /**
     * The Government which this Human relates to
     */
    private final Government _government;

    /**
     * The Counter of all Persons to set the Identifier
     */
    private static long cnt = 0;

    /**
     * The identifier of the person
     */
    private final long _id;

    /**
     * The age of the person determined by an age distribution. The age affects the
     * course of desease and the likelihood of death.
     */
    private final int _age;

    /**
     * The possible pre-existing lung desease can affect the ouse of desease.
     */
    private final boolean _isPreDeseased;

    /**
     * The duration of the COVID-19 desease.
     */
    private final int  _deseaseDuration;

    private final int _latentTime;

    private final int _incubationTime;

    private  int _deseaseCounter;

    private final int _timeInHospital;



    /**
     * The actual node the person stands on.
     */
    private LowestNode _actualNode;

    /**
     * Home node of the person. Stays there when sick. Still can infect other people
     * in this node when contagious.
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
     * 
     * @param age:           Age of the human. Will be given by an external age
     *                       distribution.
     * @param isPreDeseased: Shows if the person has a pre-existing lung desease.
     */
    public Human(final int age, final boolean isPreDeseased, final Government government) {
        _age = age;
        _isPreDeseased = isPreDeseased;
        _healthStatus = HealthStatus.HEALTY;
        _id = cnt;
        _actualNode = _homeNode;
        _government = government;

    }

    /**
     * Returns the age of the person.
     * 
     * @return the age of the human
     */
    public int getAge() {
        return _age;
    }


    public int getTimeInHospital(){
        return _timeInHospital;
    }

    /**
     * Returns if the person has a pre-existing lung desease (not COVID-19).
     * 
     * @return the person has a pre-existing lung desease or not.
     */
    public boolean isPreDeseased() {
        return _isPreDeseased;
    }

    /**
     * Returns the health status of the person in regard to COVID-19.
     * 
     * @return the actual health status.
     */
    public HealthStatus getHealthStatus() {
        return _healthStatus;
    }

    /**
     * Returns the level of the symptoms of the person.
     * 
     * @return the level of the symptoms.
     */
    public SymptomLevel getSymptomLevel() {
        return _symptomLevel;
    }

    /**
     * Returns the actual node the person stands on.
     * 
     * @return the actual node.
     */
    public LowestNode getActualNode() {
        return _actualNode;
    }

    /**
     * Returns the home node of the person.
     * 
     * @return the home node of the person.
     */
    public LowestNode getHomeNode() {
        return _homeNode;
    }

    /**
     * Changes the health status of the person.
     * 
     * @param newHealthStatus the new health status.
     */
    public void setHealthStatus(final HealthStatus newHealthStatus) {
        _healthStatus = newHealthStatus;
    }

    /**
     * Changes the level of symptoms.
     * 
     * @param newSymptomsLevel the new level of symptoms.
     */
    public void setSymptomsLevel(final SymptomLevel newSymptomLevel) {
        _symptomLevel = newSymptomLevel;
    }

    /**
     * Sets a new actual Node of the Person. Must make sure that next Node is a
     * neighbor of _actualNode!
     * 
     * @param nextNode the next node the person will stand on.
     */
    public void setActualNode(final LowestNode nextNode) {
        _actualNode = nextNode;
    }

    /**
     * Overrides the actual node of the human by using a random direction to move.
     * No exceptions must be made for the edges or corners because of cyclic map.
     */
    public boolean moveToNode(final LowestNode nextNode) {
        final int levelRelation = _actualNode.getLevelRelation(nextNode);

        if (levelRelation <= _government.getMaxTreeLevel()) {
            _actualNode.leaveNode(this);
            nextNode.enterNode(this);
            _actualNode = nextNode;
            return true;
        } else {
            return false;
        }
    }


    private boolean moveToHospital(){
        GroupingNode fartherNode = _actualNode.getFatherNode();

        while(!fartherNode.hasHospital() && !fartherNode.getHospital().hasEnoughPlace(1)){
            fartherNode = fartherNode.getFatherNode();
            
            if (fartherNode == null) {
                return false;
            }
        }
        //Hospital found
        fartherNode.getHospital().treatSickHuman(this);
        _actualNode = null;
        
        return true;
        
    }



    public void updateHealth() {
        switch (_healthStatus){
            case HEALTY: case DEAD: case RECOVERED: {
                //Nothing
            }break;
            case INFECTED:{
                _deseaseCounter++;
                if(_deseaseCounter >= _latentTime){
                    _healthStatus = HealthStatus.CONTAGIOUS;
                }
            }break;
            case CONTAGIOUS:{
                _deseaseCounter++;
                if(_deseaseCounter >= (_incubationTime)){
                    _healthStatus = HealthStatus.ILL;
                    //TODO: SELECT Symptoms
                }
            }break;
            case ILL:{
                _deseaseCounter++;
                if(_deseaseCounter >= (_deseaseDuration)){
                    //TODO: SELECT DEad or Recovered
                }
            }break;
            
        }
    }

    /**
     * Let the person walk to his home node.
     */
    public void goHome() {
        _actualNode.leaveNode(this);
        _homeNode.enterNode(this);
        _actualNode = _homeNode;
    }

    @Override
    public int hashCode() {
        return (int) _id;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Human) {
            final Human that = (Human) o;
            return this._id == that._id;
        }
        return false;
    }

    @Override
    public void tick() {
        updateHealth();
        switch(_symptomLevel){
            case HEAVY:
            if(moveToHospital()){

            }else{
                goHome();
            }
            break;
            case MILD:
                goHome();
            break;
            case NO:
                Direction direction;
                do{
                    final Set<Direction> directions = new HashSet<Direction>(Arrays.asList(Direction.values()));
                    if(directions.isEmpty()){
                        throw new IllegalStateException("No direction to move");
                    }
                    direction = RandomCounts.getRandomElement(directions);
                    directions.remove(direction);
                }while(moveToNode(_actualNode.getNeighbourNode(direction))); 
            break;
        }

    }
    
    public void infect() {
       if (_healthStatus == HealthStatus.HEALTY)
       {
            _deseaseCounter = 0;
            setHealthStatus(HealthStatus.CONTAGIOUS);
       }

    }
}
