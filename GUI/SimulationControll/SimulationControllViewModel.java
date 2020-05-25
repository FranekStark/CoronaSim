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

    LongProperty _numberForContinousSimulation;
    BooleanProperty _canSimulateContinous;
    DoubleProperty _progressOfContinousSimulation;
    ObjectProperty<ContinousDataRecord> _kindOfAutomatedSimulationEnd;
    ObjectProperty<Number> _automatedSimulationEndValue;
    ObjectProperty<Class<? extends Number>> _typeOfAutomatedSimulationEnd;
    BooleanProperty _canSimulateAutomated;
    LongProperty _currentTick;

    ObservableList<ChartListItemViewModel> _charts;
    ObservableList<ContinousDataRecordItemViewModel<? extends Number>> _continusRecords;
    
    private Simulation _simulation;


    public SimulationControllViewModel(){

    } 

    public void simulateTicks(){

    }

    public void simulateOneTick(){
        
    }

    public void simulateAutomated(){

    }




    private <T> void simulateUntil(ContinousDataRecord<T> dataRecord, T stopMark){

    }
    
}