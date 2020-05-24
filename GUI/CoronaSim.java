package GUI;

import GUI.SimulationControll.SimulationControllView;
import GUI.SimulationControll.SimulationControllViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoronaSim extends Application {

    public static void main(String[] args){
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CoronaSim");

        ViewTuple<SimulationControllView, SimulationControllViewModel> simControllViewTuple = FluentViewLoader.fxmlView(SimulationControllView.class).load();
        Parent root = simControllViewTuple.getView();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
}