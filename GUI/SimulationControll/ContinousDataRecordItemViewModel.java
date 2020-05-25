package GUI.SimulationControll;

import GUI.ContinousDataRecord;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.StringProperty;

public class ContinousDataRecordItemViewModel<T> implements ViewModel {
    
    StringProperty titleProperty;

    private ContinousDataRecord<T> continousDataRecordItem;

    public ContinousDataRecordItemViewModel(ContinousDataRecord<T> continousDataRecordItem){
        this.continousDataRecordItem = continousDataRecordItem;
        titleProperty.bind(continousDataRecordItem.getTitleProperty());
    }

}