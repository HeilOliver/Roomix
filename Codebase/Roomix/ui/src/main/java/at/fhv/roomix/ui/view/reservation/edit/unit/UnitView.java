package at.fhv.roomix.ui.view.reservation.edit.unit;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * UnitView
 * 21/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class UnitView implements FxmlView<UnitViewModel> {
    @InjectViewModel
    private UnitViewModel viewModel;

    @FXML
    private Button btnCommit;
    @FXML
    private Label lblDuration;
    @FXML
    private TextField arrivalTime;
    @FXML
    private DatePicker pickArrival;
    @FXML
    private DatePicker pickDeparture;
    @FXML
    private ListView<CategoryItemViewModel> listCategories;
    @FXML
    private ListView<PacketsItemViewModel> listPackets;


    public void initialize() {
        pickArrival.setDayCellFactory(arrivalCellFactory);
        pickDeparture.setDayCellFactory(depatureCellFactory);

        arrivalTime.textProperty().bindBidirectional(viewModel.arrivalTimeProperty());
        lblDuration.textProperty().bind(viewModel.durationProperty());
        pickArrival.valueProperty().bindBidirectional(viewModel.arrivalDateProperty());
        pickDeparture.valueProperty().bindBidirectional(viewModel.departureDateProperty());

        listCategories.setItems(viewModel.getRoomCategories());
        listCategories.setCellFactory(CachedViewModelCellFactory.createForFxmlView(CategoryItem.class));

        listPackets.setItems(viewModel.getArticleList());
        listPackets.setCellFactory(CachedViewModelCellFactory.createForFxmlView(PacketsItem.class));
    }

    @FXML
    private void buttonCommitClick(ActionEvent actionEvent) {
    }

    final Callback<DatePicker, DateCell> depatureCellFactory =
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(
                                    pickArrival.getValue().plusDays(1))
                                    ) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };

    final Callback<DatePicker, DateCell> arrivalCellFactory =
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(
                                    LocalDate.now().plusDays(1))
                                    ) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
}
