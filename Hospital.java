import java.util.HashSet;
import java.util.Set;

/**
 * Implements a Hospital with a Maximum Size.
 * 
 * @author Finn Welzmüller, Franek Stark
 */
public class Hospital implements Tickable {

    /**
     * The number of Hospitals, to count the id
     */
    private static long cnt = 0;

    /**
     * The HospitalID
     */
    private final long _id;

    /**
     * Maximum size of Treatments
     */
    private final int _maxSize;

    /**
     * Set of current Treatments
     */
    private Set<Treatment> _treatments;

    /**
     * Constructs a new Hospital
     * 
     * @param maxSize Maximum Size of Treatments per Time
     */
    public Hospital(int maxSize){
        _maxSize = maxSize;
        _id = cnt++;
    }

    /**
     * Checks where there is enogh place for a number of patients
     * 
     * @param numberOfPatients specific number of patients
     * @return enough place
     */
    public boolean hasEnoughPlace(int numberOfPatients){
        int currentPatients = _treatments.size();
        int  place = _maxSize - currentPatients;
        return numberOfPatients <= place;
    }

    /**
     * Treats a sick human if there is enough place
     * 
     * @param sickHuman the human to treat
     * @throws IllegalStateException if there is not enough place
     */
    public void treatSickHuman(Human sickHuman){
        if(!hasEnoughPlace(1)){
            throw new IllegalStateException("Not enough place in Hospital");
        }
        _treatments.add(new Treatment(sickHuman));
    }


    @Override
    public int hashCode() {
        return (int) _id;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Hospital){
            Hospital that = (Hospital) o;
            return this._id == that._id;
        }
        return false;
    }

    @Override
    public void tick() {
        Set<Treatment> toRemove = new HashSet<>();

        for (Treatment treatment : _treatments) {
            treatment.incrementDuration(1);
            if(treatment.GetDuration() >= treatment.getPatient().getTimeInHospital() || treatment.getPatient().getHealthStatus() == HealthStatus.RECOVERED){
                toRemove.add(treatment);
            }
        }

        for (Treatment treatment : toRemove) {
            _treatments.remove(treatment);
            treatment.getPatient().goHome();
        }
    }

}