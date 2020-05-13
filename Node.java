/**
 * Class for a base-type node. Nodes are connected to other nodes of the same type.
 * Humans on a node can walk to one of the neighbors (left, right, top and bottom) in a time-step. 
 * 
 * @version 30.03.2020
 * @author Finn Welzmüller, Franek Stark
 */
public class Node {

    /**
     * The number of Nodes, to count the id
     */
    private static long cnt = 0;

    /**
     * The identifier of a node
     */
    private final long _id;

   
    /**
     * The fathe of that Node
     */
    private GroupingNode _fartherNode;

    /**
     * Constructor of a node. Every node needs for neighbors
     */
    public Node( GroupingNode fatherNode) {
        _fartherNode = fatherNode;
        _id = cnt++;
    }

    /**
     * Returns the father of that Node
     * @return father of the Node
     */
    public GroupingNode getFatherNode(){
        return _fartherNode;
    }

    public void setFartherNode(GroupingNode fartherNode){
        _fartherNode = fartherNode;
    }
 

    @Override
    public int hashCode() {
        return (int) _id;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Node){
            Node that = (Node) o;
            return this._id == that._id;
        }
        return false;
    }
}