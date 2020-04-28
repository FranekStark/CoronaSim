import java.util.HashSet;
public class LowestNode extends Node implements Tickable{

     /**
     * Left Neighbournode
     */
    private final LowestNode _leftNeighbour;
    /**
     * Right Neighbournode
     */
    private final LowestNode _rightNeighbour;
    /**
     * Bottom Neighbournode
     */
    private final LowestNode _bottomNeighbour;
    /**
     * Top Neighbournode
     */
    private final LowestNode _topNeighbour;
    
    HashSet<Human> humans = new HashSet<Human>();
    /**
     * Constructs a new LowestNode with its Neighbours, SubNodes and a Hospital.
     * 
     * @param leftNeighbour left Neighbour
     * @param rightNeighbour right Neighbour
     * @param topNeighbour top Neighbour
     * @param bottomNeighbour bottom Neighbour
     * @param fatherNode father Node
     * 
     * @param hospital hospital or Null if it contains no Hospital
     */
    public LowestNode(Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour, GroupingNode fartherNode) {
        super(fartherNode);
        _leftNeighbour = leftNeighbour;
        _rightNeighbour = rightNeighbour;
        _bottomNeighbour = bottomNeighbour;
        _topNeighbour = topNeighbour;  
    }

    public LowestNode getNeighbourNode(Direction direction){
        switch (direction){
            case LEFT:
                return _leftNeighbour;
            case RIGHT:
                return _rightNeighbour;
            case TOP:
                return _topNeighbour;
            case BOTTOM:  
            default:
                return _bottomNeighbour;   
        }
    }

    /**
     * returns the relationship level of two nodes.
     * @param otherNode: the partner node
     * @return the relationship level 
     */
    public int getLevelRelation(Node otherNode)
    {
        int level = 0;

        Node thisFather = getFatherNode();
        Node otherFather = otherNode.getFatherNode();

        while(!thisFather.equals(otherFather)){
            level++;
            thisFather = thisFather.getFatherNode();
            otherFather = otherFather.getFatherNode();
        }

        return level;
    }
    /**
     * Let a human enter the node
     * @param human 
     */
    public void enterNode(Human human)
    {
        humans.add(human);
    }

    /**
     * Let a human leave the node
     * @param human
     */
    public void leaveNode(Human human)
    {
        humans.remove(human);
    }
    /**
     * returns the set of humans.
     */
    public HashSet<Human> getHumans()
    {
        return humans;
    }

    /**
     * tick method for the LowestNode class
     */
    @Override
    public void tick()
    {
        boolean infected = false;
        //loop every person in the cell and check if they're contagious. (ILL != CONTAGIOUS?).
        for (Human human : humans)
        {
            if((human.getHealthStatus() == HealthStatus.CONTAGIOUS))
            {
                infected = true;
            }
        }
        //if someone in the cell is contagious, everyone else will be infected. Here with a likelihood of 70 %
        if (infected)
        {   
            for (Human human : humans)
            {
                if (RandomCounts.giveStatement(0, MedicalSettings.infection_probability))
                {
                    human.infect();
                }
            }
        }
    }
}