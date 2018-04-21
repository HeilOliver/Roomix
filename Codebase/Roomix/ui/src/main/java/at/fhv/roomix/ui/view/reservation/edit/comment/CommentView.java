package at.fhv.roomix.ui.view.reservation.edit.comment;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.comment
 * CommentView
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CommentView implements FxmlView<CommentViewModel> {

    @InjectViewModel
    private CommentViewModel viewModel;

    @FXML
    private TextField txtCommentField;

    @FXML
    private Button btnCommit;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    public void initialize() {
        btnCommit.disableProperty().bind(viewModel.isCommitAble().not());
        txtCommentField.textProperty().bindBidirectional(viewModel.commentProperty());

        validationVisualizer.initVisualization(
                viewModel.commentValidation(), txtCommentField);
    }

    @FXML
    private void buttonCommit_Click(ActionEvent actionEvent) {
        viewModel.commitChange();
    }
}
