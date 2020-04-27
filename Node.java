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
     * Left Neighbournode
     */
    private final Node _leftNeighbour;
    /**
     * Right Neighbournode
     */
    private final Node _rightNeighbour;
    /**
     * Bottom Neighbournode
     */
    private final Node _bottomNeighbour;
    /**
     * Top Neighbournode
     */
    private final Node _topNeighbour;

    /**
     * The fathe of that Node
     */
    private final GroupingNode _fartherNode;

    /**
     * Constructor of a node. Every node needs for neighbors
     */
    public Node(Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour, GroupingNode fatherNode) {
        _leftNeighbour = leftNeighbour;
        _rightNeighbour = rightNeighbour;
        _bottomNeighbour = bottomNeighbour;
        _topNeighbour = topNeighbour;
        _fartherNode = fatherNode;
        _id = cnt++;
    }

    /**
     * Returns the father of that Node
     * @return father of the Node
     */
    public Node getFatherNode(){
        return _fartherNode;
    }

    public Node getNeighbourNode(Direction direction){
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