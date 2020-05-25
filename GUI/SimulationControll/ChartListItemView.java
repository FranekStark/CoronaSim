package GUI.SimulationControll;

import java.net.URL;
import java.util.ResourceBundle;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ChartListItemView implements FxmlView<ChartListItemViewModel>, Initializable {

    @FXML
    private CheckBox visibleCheckBox;

    @FXML
    private Label titleLabel;

    @InjectViewModel
    private ChartListItemViewModel viewModel;

    @Override
	public void initialize(URL location, ResourceBundle resources) {
        visibleCheckBox.selectedProperty().bindBidirectional(viewModel.visible);
        titleLabel.textProperty().bind(viewModel.title);
	}

}
