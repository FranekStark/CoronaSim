package GUI.SimulationControll;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.management.RuntimeErrorException;

import GUI.ContinousDataRecord;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

public class SimulationControllView implements FxmlView<SimulationControllViewModel>, Initializable {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FlowPane charsPane;//TODO: !!!

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
    private ChoiceBox<ContinousDataRecordItemViewModel<? extends Number>> kindOfTerminationCoiceBox;

    @FXML
    private TextField valueOfTerminationTexBox;

    @FXML
    private Button automatedSimulateButton;

    @FXML
    private ListView<ChartListItemViewModel> charsList;

    @FXML
    void handleAutomatedSimulateButtonAction(ActionEvent event) {
        viewModel.simulateAutomated();
    }

    @FXML
    void handleContinousSimulateButtonAction(ActionEvent event) {
        viewModel.simulateTicks();
    }

    @FXML
    void handleManualSimulateButtonAction(ActionEvent event) {
        viewModel.simulateOneTick();
    }


    
    @InjectViewModel
    private SimulationControllViewModel viewModel;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
        currentTickLabel.textProperty().bind(viewModel._currentTick.asString());
        numberOfTicksTextBox.setTextFormatter(new TextFormatter<Long>(new LongStringConverter()));
        viewModel._numberForContinousSimulation.bind(((TextFormatter<Long>)numberOfTicksTextBox.getTextFormatter()).valueProperty());        
        automatedSimulateButton.disableProperty().bind(viewModel._canSimulateAutomated.not());
        continousSimulateButton.disableProperty().bind(viewModel._canSimulateContinous.not());
        continousSumlateProgressBar.progressProperty().bind(viewModel._progressOfContinousSimulation);
        
        kindOfTerminationCoiceBox.setItems(viewModel._continusRecords);
        kindOfTerminationCoiceBox.set
       
        kindOfTerminationCoiceBox.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if(newValue.getClass().equals(Integer.class)){
                    valueOfTerminationTexBox.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter()));
                    viewModel._typeOfAutomatedSimulationEnd.set(Integer.class);
                }else if(newValue.getClass().equals(Long.class)){
                    valueOfTerminationTexBox.setTextFormatter(new TextFormatter<Long>(new LongStringConverter()));
                    viewModel._typeOfAutomatedSimulationEnd.set(Long.class);
                }else if(newValue.getClass().equals(Float.class)){
                    valueOfTerminationTexBox.setTextFormatter(new TextFormatter<Float>(new FloatStringConverter()));
                    viewModel._typeOfAutomatedSimulationEnd.set(Float.class);
                }else if(newValue.getClass().equals(Double.class)){
                    valueOfTerminationTexBox.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter()));
                    viewModel._typeOfAutomatedSimulationEnd.set(Double.class);
                }else{
                    throw new RuntimeException("Unimplemented or unexpected typeparameter. Thank Java for fckn type erasure & mail Franek Stark <moin@franekstark.de>!");
                }
         
            }
        );  
        viewModel._automatedSimulationEndValue.bind((ObservableValue<? extends Number>)valueOfTerminationTexBox.getTextFormatter().valueProperty());

        charsList.setItems(viewModel._charts);
        charsList.setCellFactory(CachedViewModelCellFactory.createForFxmlView(ChartListItemView.class));

	}
}
