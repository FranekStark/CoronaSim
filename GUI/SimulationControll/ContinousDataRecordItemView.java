package GUI.SimulationControll;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ContinousDataRecordItemView implements FxmlView<ContinousDataRecordItemViewModel>, Initializable {

    @FXML
    private Label title;

    @InjectViewModel
    private ContinousDataRecordItemViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.textProperty().bind(viewModel.titleProperty);
    }

}
