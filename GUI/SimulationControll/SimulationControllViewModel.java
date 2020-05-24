package GUI.SimulationControll;

import GUI.ContinousDataRecord;
import Simulation.Simulation;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;

public class SimulationControllViewModel implements ViewModel {

    private LongProperty _numberForContinousSimulation;
    private BooleanProperty _canSimulateContinous;
    private DoubleProperty _progressOfContinousSimulation;
    private ObservableList<Chart> _charts;
    private ObjectProperty<ContinousDataRecord> _kindOfAutomatedSimulationEnd;
    private ObjectProperty<String> _automatedSimulationEndValue;
    private BooleanProperty _canSimulateAutomated;

    private LongProperty _currentTick;

    private Simulation _simulation;


    public SimulationControllViewModel(){

    } 

    public void simulateTick(int numberOfTicks){

    }

    public <T> void simulateUntil(ContinousDataRecord<T> dataRecord, T stopMark){

    }
    
}