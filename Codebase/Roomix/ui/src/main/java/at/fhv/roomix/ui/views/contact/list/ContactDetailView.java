package at.fhv.roomix.ui.views.contact.list;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Roomix
 * at.fhv.roomix.ui.views.contact.list
 * ContactDetailView
 * 27/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class ContactDetailView implements FxmlView<ContactDetailViewModel> {

    @InjectViewModel
    private ContactDetailViewModel viewModel;
    @FXML
    private Text txtFirstname;
    @FXML
    private Text txtLastname;
    @FXML
    private Text txtCompanyname;
    @FXML
    private Text txtPhonenumber;
    @FXML
    private Text txtStreet;
    @FXML
    private Text txtCity;
    @FXML
    private Text txtPostalcode;
    @FXML
    private Text txtCountry;
    @FXML
    private Text txtEmail;
    @FXML
    private GridPane pnlContent;
    @FXML
    private VBox pnlNothing;

    public void initialize() {
        txtFirstname.textProperty().bind(viewModel.firstnameProperty());
        txtLastname.textProperty().bind(viewModel.lastnameProperty());
        txtCompanyname.textProperty().bind(viewModel.companynameProperty());
        txtPhonenumber.textProperty().bind(viewModel.phonenumberProperty());
        txtStreet.textProperty().bind(viewModel.streetProperty());
        txtCity.textProperty().bind(viewModel.placeProperty());
        txtPostalcode.textProperty().bind(viewModel.postcodeProperty());
        txtCountry.textProperty().bind(viewModel.countryProperty());
        txtEmail.textProperty().bind(viewModel.emailProperty());

        pnlContent.visibleProperty().bind(viewModel.detailAvailableProperty());
        pnlNothing.visibleProperty().bind(viewModel.detailAvailableProperty().not());
    }
}
