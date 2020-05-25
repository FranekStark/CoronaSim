package GUI.SimulationControll;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.chart.Chart;

public class ChartListItemViewModel implements ViewModel {
    
    BooleanProperty visible;
    StringProperty title;

    private Chart chartItem;

    public ChartListItemViewModel(Chart chartItem){
        visible = new SimpleBooleanProperty();
        title = new SimpleStringProperty();
        
        this.chartItem = chartItem;

        visible.bindBidirectional(chartItem.visibleProperty());
        title.bind(chartItem.titleProperty());
    }
    
}