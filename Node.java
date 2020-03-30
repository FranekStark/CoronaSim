/**
 * Basetype Node
 * 
 * @author Finn Welzmüller, Franek Stark
 */
public class Node {

    /**
     * The number of Nodes, to count the id
     */
    private static long cnt = 0;

    /**
     * The NodeID
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

    public Node(Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour) {
        _leftNeighbour = leftNeighbour;
        _rightNeighbour = rightNeighbour;
        _bottomNeighbour = bottomNeighbour;
        _topNeighbour = topNeighbour;
        _id = cnt++;
    }

    /**
     * Returns the left Neighbour of that Node
     * 
     * @return left Neighbour
     */
    public Node getLeftNeighbour() {
        return _leftNeighbour;
    }

    /**
     * Returns the right Neighbour of that Node
     * 
     * @return right Neighbour
     */
    public Node getRightNeighbour() {
        return _rightNeighbour;
    }

    /**
     * Returns the top Neighbour of that Node
     * 
     * @return top Neighbour
     */
    public Node getTopNeighbour() {
        return _topNeighbour;
    }

    /**
     * Returns the bottom Neighbour of that Node
     * 
     * @return bottom Neighbour
     */
    public Node getBottomNeighbour() {
        return _bottomNeighbour;
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