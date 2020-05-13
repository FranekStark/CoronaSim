<<<<<<< HEAD
import java.util.Random;
=======
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;
>>>>>>> master

/**
 * Main class. Simulation can be started with a fixed amount of simulated days
 * (ticks) or the simulation can be made until everyone is healthy, recovered or
 * dead.
 */
public class Main {

    Government _government;

    Set<Human> _humans;
    Set<LowestNode> _lowestNodes;
    Set<GroupingNode> _groupingNodes;
    Set<Hospital> _hospitals;
    GroupingNode _topNode;

    public void createSimulation(long numberOfHumans, int numberOfLevels, double lowestNodePerHuman,
         int maxSizeOfTreatments, boolean sphericShape) {
        long numberOfLowestNodes = (long) (numberOfHumans * lowestNodePerHuman);
        if (numberOfLowestNodes <= 0) {
            throw new IllegalArgumentException("Overflow in number of lowest Nodes");
        }
        int numberOfSonsPerGroupingNode = (int) (Math.log(numberOfLowestNodes) / Math.log(numberOfLevels));

        // Create Lowest Nodes
        long cols = calculateBestEdge(numberOfLowestNodes);
        long rows = numberOfLowestNodes / cols;

        List<LowestNode> lefterNodes = new ArrayList<>();

        {
            LowestNode leftNode = null;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    LowestNode newNode = new LowestNode();
                    _lowestNodes.add(newNode);
                    LowestNode topNode = null;

                    if (row == 0) {
                        if (col == 0) {
                            topNode = null;
                            leftNode = null;
                            lefterNodes.add(newNode);
                        } else {
                            topNode = null;
                            leftNode.setNeighbourNode(Direction.RIGHT, newNode);
                        }
                    } else {
                        if (col == 0) { // The leftest node of this row
                            lefterNodes.add(newNode);
                            topNode = lefterNodes.get(row - 1);
                        } else {
                            topNode = leftNode.getNeighbourNode(Direction.TOP).getNeighbourNode(Direction.RIGHT);
                            leftNode.setNeighbourNode(Direction.RIGHT, newNode);
                        }
                        topNode.setNeighbourNode(Direction.BOTTOM, newNode);
                    }
                    newNode.setNeighbourNode(Direction.LEFT, leftNode);
                    newNode.setNeighbourNode(Direction.TOP, topNode);

                    leftNode = newNode;
                }
                leftNode = null;
            }
        }

        if (sphericShape) {
            // Set Spheric shape
            LowestNode topLeft = lefterNodes.get(0);
            LowestNode bottomLeft = lefterNodes.get(lefterNodes.size() - 1);

            // --> Top and Bottm Edge
            LowestNode topNode = topLeft;
            LowestNode bottomNode = bottomLeft;

            for (int col = 0; col < cols; col++) {
                topNode.setNeighbourNode(Direction.TOP, bottomNode);
                bottomNode.setNeighbourNode(Direction.BOTTOM, bottomNode);
                topNode = topNode.getNeighbourNode(Direction.RIGHT);
                bottomNode = bottomNode.getNeighbourNode(Direction.RIGHT);
            }

            // --> Left and Right Edge
            LowestNode leftNode = topLeft;
            LowestNode rightNode = topLeft;
            while (rightNode.getNeighbourNode(Direction.RIGHT) != null) {
                rightNode = rightNode.getNeighbourNode(Direction.RIGHT);
            }

            for (int row = 0; row < rows; row++) {
                leftNode.setNeighbourNode(Direction.LEFT, rightNode);
                rightNode.setNeighbourNode(Direction.RIGHT, leftNode);
            }

        }

        // Create GroupingNodes for each Level (A bundle of Nodes are those Nodes who
        // have the same Farthernode)

        long bundleCols = calculateBestEdge(numberOfSonsPerGroupingNode);
        long bundleRows = numberOfSonsPerGroupingNode / bundleCols;

        if ((double) rows / bundleRows % 1.0 != 0) {
            throw new RuntimeException("Error: Nundlerows do fit into rows");
        }
        if ((double) cols / bundleCols % 1.0 != 0) {
            throw new RuntimeException("Error: Nundlerows do fit into rows");
        }

       
        for (int level = 1; level < (numberOfLevels); level++) {
            LowestNode rowNode = lefterNodes.get(0);
            for (int row = 0; row < rows; row += Math.pow(bundleRows, level)) {
                LowestNode colNode = rowNode;
                for (int col = 0; col < cols; col += Math.pow(bundleCols, level)) {
                    Set<Node> sameFartherNodes = new HashSet<>();
                    LowestNode bundleRowNode = colNode;
                    for (int bundleRow = 0; bundleRow < bundleRows; bundleRow++) {
                        LowestNode bundleColNode = bundleRowNode;
                        for (int bundleCol = 0; bundleCol < bundleCols; bundleCol++) {
                            Node levelNode = bundleColNode;
                            for(int i = 1; i < level; i++){
                                levelNode = levelNode.getFatherNode();
                            }
                            sameFartherNodes.add(levelNode);
                            bundleRowNode = bundleColNode.getNeighbourNode(Direction.RIGHT);
                        }
                        bundleRowNode = bundleRowNode.getNeighbourNode(Direction.BOTTOM);
                    }
                    GroupingNode groupingNode = new GroupingNode(null, null);
                    _groupingNodes.add(groupingNode);

                    if(level == (numberOfLevels - 1)){ //TOP Grouping Node
                        _topNode = groupingNode;
                    }

                    for (Node node : sameFartherNodes) {
                        node.setFartherNode(groupingNode);
                    }

                }
                for (int i = 0; i < bundleCols; i++) {
                    colNode = colNode.getNeighbourNode(Direction.RIGHT);
                }
            }

            for (int i = 0; i < bundleRows; i++) {
                rowNode = rowNode.getNeighbourNode(Direction.BOTTOM);
            }
        }

        //Add Hospitals
        Hospital hospital = new Hospital(maxSizeOfTreatments);
        _hospitals.add(hospital);
        _topNode.setHospital(hospital);

        //Add Government
        _government = new Government(numberOfLevels);

        //Add Humans
        for(long i = 0; i < numberOfHumans; i++){
            int randomNumber = RandomCounts.giveRandomNumber(101);
          

            int age = 0;
            boolean isPreDeseased = false;
            int desaeaseDuration = 0;
            int latentTime = 0;
            int incubationTime = 0;
            int timeInHospital = 0;
            LowestNode homeNode = null;

            Human newHuman = new Human(_government, age, isPreDeseased, desaeaseDuration, latentTime, incubationTime, timeInHospital, homeNode);
            _humans.add(newHuman);
        }

        for (Human human : _humans) {
            
        }



    }

<<<<<<< HEAD
public static double giveRandomLatency(){
    return RandomCounts.giveGaussianValue(MedicalSettings.latency_mean, MedicalSettings.latency_std);
    }

public static double giveRandomIncubation(){
    return RandomCounts.giveGaussianValue(MedicalSettings.incubation_mean, MedicalSettings.incubation_std);
    }

public static double giveRandomMildDuration(){
    return RandomCounts.giveGaussianValue(MedicalSettings.desease_mild_mean, MedicalSettings.desease_mild_std);
    }

public static double giveRandomHeavyDuration(){
    return RandomCounts.giveGaussianValue(MedicalSettings.desease_heavy_mean, MedicalSettings.desease_heavy_std);
    }

/**
 * Gives the amount of humans (weighted by 10000) with a specific age and a predesease
*/
public static void giveAgeAndDeseaseAmount(){
    double Age0PreDesease = (1/10000)*MedicalSettings.age_prob_0_to_7*MedicalSettings.predesease_prob_0_to_7;
    double Age0NoPreDesease = (1/10000)*MedicalSettings.age_prob_0_to_7*(1 - MedicalSettings.predesease_prob_0_to_7);

    double Age8PreDesease = (1/10000)*MedicalSettings.age_prob_8_to_17*MedicalSettings.predesease_prob_8_to_17;
    double Age8NoPreDesease = (1/10000)*MedicalSettings.age_prob_8_to_17*(1 - MedicalSettings.predesease_prob_8_to_17);

    double Age18PreDesease = (1/10000)*MedicalSettings.age_prob_18_to_29*MedicalSettings.predesease_prob_18_to_29;
    double Age18NoPreDesease = (1/10000)*MedicalSettings.age_prob_18_to_29*(1 - MedicalSettings.predesease_prob_18_to_29);

    double Age30PreDesease = (1/10000)*MedicalSettings.age_prob_30_to_39*MedicalSettings.predesease_prob_30_to_39;
    double Age30NoPreDesease = (1/10000)*MedicalSettings.age_prob_30_to_39*(1 - MedicalSettings.predesease_prob_30_to_39);

    double Age40PreDesease = (1/10000)*MedicalSettings.age_prob_40_to_49*MedicalSettings.predesease_prob_40_to_49;
    double Age40NoPreDesease = (1/10000)*MedicalSettings.age_prob_40_to_49*(1 - MedicalSettings.predesease_prob_40_to_49);

    double Age50PreDesease = (1/10000)*MedicalSettings.age_prob_50_to_59*MedicalSettings.predesease_prob_50_to_59;
    double Age50NoPredesease = (1/10000)*MedicalSettings.age_prob_50_to_59*(1 - MedicalSettings.predesease_prob_50_to_59);

    double Age60PreDesease = (1/10000)*MedicalSettings.age_prob_60_to_69*MedicalSettings.predesease_prob_60_to_69;
    double Age60NoPreDesease = (1/10000)*MedicalSettings.age_prob_60_to_69*(1 - MedicalSettings.predesease_prob_60_to_69);

    double Age70PreDesease = (1/10000)*MedicalSettings.age_prob_70_to_79*MedicalSettings.predesease_prob_70_to_79;
    double Age70NoPreDesease = (1/10000)*MedicalSettings.age_prob_70_to_79*(1 - MedicalSettings.predesease_prob_70_to_79);

    double Age80PreDesease = (1/10000)*MedicalSettings.age_prob_80_to_89*MedicalSettings.predesease_prob_80_to_89;
    double Age80NoPreDesease = (1/10000)*MedicalSettings.age_prob_80_to_89*(1 - MedicalSettings.predesease_prob_80_to_89);

    double Age90PreDesease = (1/10000)*MedicalSettings.age_prob_90_to_100*MedicalSettings.predesease_prob_90_to_100;
    double Age90NoPreDesease = (1/10000)*MedicalSettings.age_prob_90_to_100*(1 - MedicalSettings.predesease_prob_90_to_100);
}
=======
    private long calculateBestEdge(long area) {
        long x = (long) Math.sqrt(area);

        while (((double) area / (double) x) % 1.0 != 0.0) {
            x = x + 1;
            if (x >= area) {
                throw new IllegalArgumentException("Failed to calculate best Edge, maybe area was prime");
            }
        }

        return x;
    }

    public void Main() {
        // Create
    }

    public void tickAll(){
        for (Human human : _humans) {
            human.tick();
        }
>>>>>>> master

        for(Hospital hospital : _hospitals){
            hospital.tick();
        }

        for (LowestNode lowestNode : _lowestNodes) {
            lowestNode.tick();
        }
    }

    public static void main(String[] aStrings) {
        Main main = new Main();
    }

}
