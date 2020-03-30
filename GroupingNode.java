import java.util.HashSet;
import java.util.Set;

/**
 * The Group-Node derives from the Node and has Subnodes.
 * @author Finn Welzmüller, Franek Stark
 */
public class GroupingNode extends Node {

    /**
     * This Set Contains the Subnodes
     */
    private final Set<Node> _subNodes;

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
     * 
     * @param hospital hospital or Null if it contains no Hospital
     */
    public GroupingNode(Hospital hospital, Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour,
            Node... subNodes) {
        super(leftNeighbour, rightNeighbour, bottomNeighbour, topNeighbour);
        _subNodes = new HashSet<>();
        for (Node node : subNodes) {
            _subNodes.add(node);
        }
        _hospital = hospital;

    }

    /**
     * Returns a new Set which contains all SubNodes.
     * 
     * @return new Set
     */
    public Set<Node> GetSubNodes(){
        return new HashSet<>(_subNodes);
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