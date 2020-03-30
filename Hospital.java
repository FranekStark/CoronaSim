import java.util.Set;

/**
 * Implements a Hospital with a Maximum Size.
 * 
 * @author Finn Welzmüller, Franek Stark
 */
public class Hospital {

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
        int currentPatients = _treatments.size()
        int  place = _maxSize - currentPatients;
        return numberOfPatients <= place;
    }

    public void treatSickHuman(){

    }

    public void retreiveHealedHuman(){

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

}