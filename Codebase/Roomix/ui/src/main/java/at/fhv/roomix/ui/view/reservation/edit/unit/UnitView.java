package at.fhv.roomix.ui.view.reservation.edit.unit;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
    @FXML
    private Label lblSum;
    @FXML
    private ProgressIndicator icoArrangementsLoad;
    @FXML
    private ProgressIndicator icoCategoriesLoad;
    @FXML
    private TextField inputAmount;
    @FXML
    private Label lblPricePerDay;
    @FXML
    private BarChart<Number,String> chartSelectedCategorie;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    @FXML
    NumberAxis xAxis;
    @FXML
    CategoryAxis yAxis;

    public void initialize() {
        pickArrival.setDayCellFactory(arrivalCellFactory);
        pickDeparture.setDayCellFactory(depatureCellFactory);

        arrivalTime.textProperty().bindBidirectional(viewModel.arrivalTimeProperty());
        lblDuration.textProperty().bind(viewModel.durationProperty());
        pickArrival.valueProperty().bindBidirectional(viewModel.arrivalDateProperty());
        pickDeparture.valueProperty().bindBidirectional(viewModel.departureDateProperty());

        listCategories.setItems(viewModel.getRoomCategories());
        listCategories.setCellFactory(CachedViewModelCellFactory.createForFxmlView(CategoryItem.class));

        listPackets.setItems(viewModel.getArrangementList());
        listPackets.setCellFactory(CachedViewModelCellFactory.createForFxmlView(PacketsItem.class));

        icoArrangementsLoad.visibleProperty().bind(viewModel.getArrangementsInLoad());
        listPackets.disableProperty().bind(viewModel.getArrangementsInLoad());

        icoCategoriesLoad.visibleProperty().bind(viewModel.getContactInLoad());
        listCategories.disableProperty().bind(viewModel.getContactInLoad());

        //yAxis.setTickLabelRotation(90);
        xAxis.setLabel("Available Rooms");

        chartSelectedCategorie.setData(viewModel.getAvailableRooms());

        listCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null) {
                viewModel.categoryProperty().setValue(null);
                return;
            }
            viewModel.categoryProperty().setValue(newValue.getPojo());
        });

        lblPricePerDay.textProperty().bind(viewModel.currCategoryPriceProperty());
    }

    @FXML
    private void buttonCommitClick(ActionEvent actionEvent) {
        viewModel.onCommit();
    }

    final Callback<DatePicker, DateCell> depatureCellFactory =
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (pickArrival.getValue() == null) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                                return;
                            }
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
