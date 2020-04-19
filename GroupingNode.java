/**
 * The Group-Node derives from the Node and has Subnodes.
 * @author Finn Welzmüller, Franek Stark
 */
public class GroupingNode extends Node {

     /**
     * The Hospital of that Node or Null;
     */
    private final Hospital _hospital;

    /**
     * Constructs a new GroupingNode with its Neighbours, SubNodes and a Hospital.
     * 
     * @param leftNeighbour left Neighbour
     * @param rightNeighbour right Neighbour
     * @param topNeighbour top Neighbour
     * @param bottomNeighbour botoom Neighbour
     * @param fatherNode father Node
     * 
     * @param hospital hospital or Null if it contains no Hospital
     */
    public GroupingNode(Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour, GroupingNode fatherNode, Hospital hospital) {
        super(leftNeighbour, rightNeighbour, bottomNeighbour, topNeighbour, fatherNode);
        _hospital = hospital;
    }

    /**
     * Determines wether this Node has a Hospital
     * 
     * @return it has a hospital
     */
    public boolean hasHospital(){
        return _hospital != null;
    }

    /**
     * Returns the Hospital of that Node
     * 
     * @return the hospital
     */
    public Hospital getHospital()
    {
        return _hospital;
    }


}