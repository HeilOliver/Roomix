package at.fhv.roomix.ui.view.reservation.content;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.content
 * ReservationDetailView
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationDetailView implements FxmlView<ReservationDetailViewModel> {

    @InjectViewModel
    private ReservationDetailViewModel viewModel;

    @FXML
    private Text txtReservationID, txtReservationId;
    @FXML
    private Text txtContractingPartyName;
    @FXML
    private Text txtReservationComment;
    @FXML
    private Text txtPaymentType;
    @FXML
    private Text txtContractingPartyFname;
    @FXML
    private Text txtContractingPartyLname;
    @FXML
    private Text txtContractingCompany;
    @FXML
    private Text txtContractingPartyPhone;
    @FXML
    private ListView<String> lstViewPersons, lstViewUnits;


    @FXML
    private GridPane detailContent;
    @FXML
    private VBox emptyContent;

    public void initialize(){
        txtReservationID.textProperty().bind(viewModel.reservationIDProperty());
        txtReservationId.textProperty().bind(viewModel.reservationIDProperty());
        txtContractingPartyName.textProperty().bind(viewModel.contractingPartyNameProperty());
        txtReservationComment.textProperty().bind(viewModel.reservationCommentProperty());
        txtContractingPartyFname.textProperty().bind(viewModel.contractingPartyFnameProperty());
        txtContractingPartyLname.textProperty().bind(viewModel.contractingPartyLnameProperty());
        txtContractingCompany.textProperty().bind(viewModel.contractingPartyCompanyProperty());
        txtContractingPartyPhone.textProperty().bind(viewModel.contractingPartyPhoneProperty());
        lstViewPersons.setItems(viewModel.getPersons());
        lstViewUnits.setItems(viewModel.getUnits());

        detailContent.visibleProperty().bind(viewModel.detailAvailableProperty());
        emptyContent.visibleProperty().bind(viewModel.detailAvailableProperty().not());
    }
}
