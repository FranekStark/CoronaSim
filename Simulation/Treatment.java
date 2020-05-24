package Simulation;

/**
 * Implements a Treatment of an Human which is since a duration.
 * 
 * @author Finn Welzmüller, Franek Stark
 */
public class Treatment{
    
    /**
     * The Patient 
     */
    private final Human _patient;
    
    /**
     * The Duration since begin of Treatment
     */
    private int _duration;

    /**
     * Contructs a new Turation whith a specific Patient
     * 
     * @param patient the patient
     */
    public Treatment(Human patient){
        _patient = patient;
        _duration = 0;
    }

    /**
     * Returns the pateint of this treatment
     * 
     * @return the patient
     */
    public Human getPatient(){
        return _patient;
    }

    /**
     * Returns the duration since treatment begin
     * 
     * @return the duration
     */
    public int GetDuration(){
        return _duration;
    }

    /**
     * Increments the duration since treatment begin by a value
     * 
     * @param duration value to increment
     */
    public void incrementDuration(int duration){
        _duration += duration;
    }
    
}