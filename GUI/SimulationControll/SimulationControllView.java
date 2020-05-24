package GUI.SimulationControll;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SimulationControllView implements FxmlView<SimulationControllViewModel>, Initializable {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane charsPane;

    @FXML
    private Label currentTickLabel;

    @FXML
    private Button manualSimulateButton;

    @FXML
    private TextField numberOfTicksTextBox;

    @FXML
    private Button continousSimulateButton;

    @FXML
    private ProgressBar continousSumlateProgressBar;

    @FXML
    private ChoiceBox<?> kindOfTerminationCoiceBox;

    @FXML
    private TextField valueOfTerminationTexBox;

    @FXML
    private Button automatedSimulateButton;

    @FXML
    private TableColumn<?, ?> charTableCollumnTitle;

    @FXML
    void handleAutomatedSimulateButtonAction(ActionEvent event) {
        viewModel.Sim
    }

    @FXML
    void handleContinousSimulateButtonAction(ActionEvent event) {
        viewModel.simulateTick(viewModel.);
    }

    @FXML
    void handleManualSimulateButtonAction(ActionEvent event) {

    }


    
    @InjectViewModel
    private SimulationControllViewModel viewModel;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
