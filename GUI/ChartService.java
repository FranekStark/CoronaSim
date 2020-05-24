package GUI;

import javafx.collections.ObservableList;
import javafx.scene.chart.Chart;

public class ChartService {
    private ObservableList<Chart> _charts;

    public ObservableList<Chart> getCharts(){
        return _charts;
    }

    public void addChart(Chart chart){
        _charts.add(chart);
    }
}