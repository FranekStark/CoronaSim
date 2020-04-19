public class LowestNode extends Node {
    

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
    public LowestNode(Node leftNeighbour, Node rightNeighbour, Node bottomNeighbour, Node topNeighbour, GroupingNode fatherNode) {
        super(leftNeighbour, rightNeighbour, bottomNeighbour, topNeighbour, fatherNode);
    }
}