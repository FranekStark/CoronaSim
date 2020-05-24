package GUI;

import java.util.LinkedList;
import java.util.function.Supplier;

import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;


public class ContinousDataRecord<T> {

    private Supplier<T> _valueMethod;
    private XYChart.Series<Long, T> _series;

    public ContinousDataRecord(String name, Supplier<T> valueMethod) {
     _valueMethod = valueMethod;
     _series = new XYChart.Series<Long, T>(name, FXCollections.observableList(new LinkedList<XYChart.Data<Long, T>>()));
    }

    public void tick(long currentTick){
        _series.getData().add(new XYChart.Data<Long, T>(currentTick, _valueMethod.get()));
    }

    public XYChart.Series<Long, T> getSeriers(){
        return _series;
    }

}