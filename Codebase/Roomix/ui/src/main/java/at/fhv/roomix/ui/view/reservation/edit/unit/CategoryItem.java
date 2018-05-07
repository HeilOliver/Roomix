package at.fhv.roomix.ui.view.reservation.edit.unit;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.SegmentedBar;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * CategoryItem
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CategoryItem implements FxmlView<CategoryItemViewModel> {

    @InjectViewModel
    private CategoryItemViewModel viewModel;

    @FXML
    private Label lblContent;


    public void initialize() {
        lblContent.textProperty().bind(viewModel.contentProperty());

//        SegmentedBar<MySegment> bar = new SegmentedBar<>();
//        bar.getSegments().addAll(
//                new MySegment(10, "10"),
//                new MySegment(10, "10"),
//                new MySegment(10, "10"),
//                new MySegment(10, "10"),
//                new MySegment(10, "10"),
//                new MySegment(50, "50"));


    }


    public class MySegment extends SegmentedBar.Segment {


        public MySegment(double value, String text) {
            super(value, text);
        }
    }
}
