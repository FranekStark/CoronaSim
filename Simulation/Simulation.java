package Simulation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * Main class. Simulation can be started with a fixed amount of simulated days
 * (ticks) or the simulation can be made until everyone is healthy, recovered or
 * dead.
 */
public class Simulation {

    public Government _government;

    public Set<Human> _humans;
    public Set<LowestNode> _lowestNodes;
    public Set<GroupingNode> _groupingNodes;
    public Set<Hospital> _hospitals;
    public GroupingNode _topNode;

    private long _tick;

    public Simulation(long numberOfHumans, int numberOfLevels, double lowestNodePerHuman, int maxSizeOfTreatments,
            boolean sphericShape, int ticksPerDay) {
                _tick=0;
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
                            for (int i = 1; i < level; i++) {
                                levelNode = levelNode.getFatherNode();
                            }
                            sameFartherNodes.add(levelNode);
                            bundleRowNode = bundleColNode.getNeighbourNode(Direction.RIGHT);
                        }
                        bundleRowNode = bundleRowNode.getNeighbourNode(Direction.BOTTOM);
                    }
                    GroupingNode groupingNode = new GroupingNode(null, null);
                    _groupingNodes.add(groupingNode);

                    if (level == (numberOfLevels - 1)) { // TOP Grouping Node
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

        // Add Hospitals
        Hospital hospital = new Hospital(maxSizeOfTreatments);
        _hospitals.add(hospital);
        _topNode.setHospital(hospital);

        // Add Government
        _government = new Government(numberOfLevels);

        // Add Humans
        Map<Integer, MedicalSettings.AgeDistributal> distribution = MedicalSettings.giveAgeDistribution();
        for (Entry<Integer, MedicalSettings.AgeDistributal> entry : distribution.entrySet()) {
            long numberPreDeseased = (long) (numberOfHumans * entry.getValue()._PreDesease);
            long numberNoPreDeseased = (long) (numberOfHumans * entry.getValue()._NoPreDesease);

            for (long i = 0; i < numberPreDeseased; i++) {
                Human human = new Human(_government, entry.getKey(), true, ticksPerDay * MedicalSettings.giveRandomHeavyDuration(),
                ticksPerDay * MedicalSettings.giveRandomMildDuration(),  ticksPerDay * MedicalSettings.giveRandomLatency(),
                ticksPerDay * MedicalSettings.giveRandomIncubation(),  ticksPerDay * MedicalSettings.giveRandomTimeInHospital(),
                        RandomCounts.getRandomElement(_lowestNodes));
                _humans.add(human);
            }
            for (long i = 0; i < numberNoPreDeseased; i++) {
                Human human = new Human(_government, entry.getKey(), false, ticksPerDay * MedicalSettings.giveRandomHeavyDuration(),
                ticksPerDay * MedicalSettings.giveRandomMildDuration(),  ticksPerDay * MedicalSettings.giveRandomLatency(),
                ticksPerDay * MedicalSettings.giveRandomIncubation(),  ticksPerDay * MedicalSettings.giveRandomTimeInHospital(),
                        RandomCounts.getRandomElement(_lowestNodes));
                _humans.add(human);
            }

        }

    }

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

    public void tickAll() {
        _tick++;
        for (Human human : _humans) {
            human.tick();
        }

        for (Hospital hospital : _hospitals) {
            hospital.tick();
        }

        for (LowestNode lowestNode : _lowestNodes) {
            lowestNode.tick();
        }
    }


    public long getCurrentTick(){
        return _tick;
    }

}