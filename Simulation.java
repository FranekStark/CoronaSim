import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.HashMap;

/**
 * Main class. Simulation can be started with a fixed amount of simulated days
 * (ticks) or the simulation can be made until everyone is healthy, recovered or
 * dead.
 */
public class Simulation {

    public Government _government;

    public static Set<Human> _humans;
    public static Set<LowestNode> _lowestNodes;
    public Set<GroupingNode> _groupingNodes;
    public Set<Hospital> _hospitals;
    public GroupingNode _topNode;

    public Simulation(long numberOfHumans, int numberOfLevels, double lowestNodePerHuman, int maxSizeOfTreatments,
            boolean sphericShape, int ticksPerDay) {
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

        // Add Government with autosetLockdownLevel is off.
        _government = new Government(numberOfLevels, false, false);

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
        for (Human human : _humans) {
            human.tick();
        }

        for (Hospital hospital : _hospitals) {
            hospital.tick();
        }

        for (LowestNode lowestNode : _lowestNodes) {
            lowestNode.tick();
        }
        _government.tick();
    }
    /**
     * returns the incidence (#infected per 100000 humans).
     * @return incidence
     */
    public static double getIncidence(){
        long ills = 0;
        for (Human human : _humans){
            if (human.getHealthStatus() == HealthStatus.ILL)
            {
                ills += 1;
            }
        }
        return (double)(ills * 100000/_humans.size());
    }
    /**
     * returns the mortality (deaths of COVID-19 per 100000 humans).
     * @return mortality
     */
    public double getMortality(){
        long deads = 0;
        for (Human human : _humans){
            if (human.getHealthStatus() == HealthStatus.DEAD)
            {
                deads += 1;
            }
        }
        return (double)(deads * 100000/_humans.size());

    }
    /**
     * gives the actual reproduction number R0 = infection probability * #contacts of an infected per tick * mean time of infection
     * @return basic reproduction time
     */
    public static double giveReproductionNumber()
    {   
        double meanInfectiontime = 0.5*(MedicalSettings.desease_heavy_mean + MedicalSettings.desease_mild_mean) + MedicalSettings.incubation_mean;
        long humans = 0;

        for (LowestNode lowestNode : _lowestNodes)
        {
            humans += (lowestNode.humans.size() - 1);
        }
        return meanInfectiontime * (double)MedicalSettings.infection_probability * 0.01 * (humans/_lowestNodes.size());
    }

    /**
     * returns the number of ill people in a specific agegroup. MUST BE 0; 8; 18; 30; 40; 50; 60; 70; 80; 90;
     */
    public long giveIllInAge(int agegroup){
        long ill = 0;
        for (Human human : _humans)
        {
            if ((human.getAge() == agegroup) && (human.getHealthStatus() == HealthStatus.ILL))
            {
                ill += 1;
            }
        }
        return ill;
    }

    /**
     * gives the amount of Ill humans depending on their age listed in a map.
     * @return the map . Key=Agegroup, Value=Amount of ill humans
     */
    public HashMap<Integer, Long> giveIllPerAge()
    {
        HashMap<Integer, Long> IllPerAge = new HashMap<Integer, Long>();
        IllPerAge.replace(0, giveIllInAge(0));
        IllPerAge.replace(8, giveIllInAge(8));
        IllPerAge.replace(18, giveIllInAge(18));
        IllPerAge.replace(30, giveIllInAge(30));
        IllPerAge.replace(40, giveIllInAge(40));
        IllPerAge.replace(50, giveIllInAge(50));
        IllPerAge.replace(60, giveIllInAge(60));
        IllPerAge.replace(70, giveIllInAge(70));
        IllPerAge.replace(80, giveIllInAge(80));
        IllPerAge.replace(90, giveIllInAge(90));

        return IllPerAge;
    }
    /**
     * returns the number of dead people in a specific agegroup. MUST BE 0; 8; 18; 30; 40; 50; 60; 70; 80; 90;
     */
    public long giveDeadInAge(int agegroup){
        long dead = 0;
        for (Human human : _humans)
        {
            if((human.getAge() == agegroup) && (human.getHealthStatus() == HealthStatus.DEAD))
            {
                dead += 1;           
            }
        }
        return dead;
    }
    /**
     * gives the amount of dead humans depending on their age listed in a map.
     * @return the map . Key=Agegroup, Value=Amount of dead humans
     */
    public HashMap<Integer, Long> giveDeadPerAge()
    {
        HashMap<Integer, Long> DeadPerAge = new HashMap<Integer, Long>();
        DeadPerAge.replace(0, giveIllInAge(0));
        DeadPerAge.replace(8, giveIllInAge(8));
        DeadPerAge.replace(18, giveIllInAge(18));
        DeadPerAge.replace(30, giveIllInAge(30));
        DeadPerAge.replace(40, giveIllInAge(40));
        DeadPerAge.replace(50, giveIllInAge(50));
        DeadPerAge.replace(60, giveIllInAge(60));
        DeadPerAge.replace(70, giveIllInAge(70));
        DeadPerAge.replace(80, giveIllInAge(80));
        DeadPerAge.replace(90, giveIllInAge(90));

        return DeadPerAge;
    }
}