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
     * @param fatherNode father Node
     * 
     * @param hospital hospital or Null if it contains no Hospital
     */
    public GroupingNode(GroupingNode fatherNode, Hospital hospital) {
        super(fatherNode);
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