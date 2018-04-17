package at.fhv.roomix.ui.view.contact.edit;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import de.saxsys.mvvmfx.utils.validation.visualization.ValidationVisualizer;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Roomix
 * at.fhv.roomix.ui.view.contact.edit
 * ContactEditView
 * 15/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContactEditView implements FxmlView<ContactEditViewModel> {

    @InjectViewModel
    private ContactEditViewModel viewModel;

    @FXML
    private TextField firstnameInput;
    @FXML
    private TextField lastnameInput;
    @FXML
    private TextField companynameInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField streetInput;
    @FXML
    private TextField placeInput;
    @FXML
    private TextField postcodeInput;
    @FXML
    private TextField countryInput;
    @FXML
    private TextField emailInput;

    private ValidationVisualizer validationVisualizer = new ControlsFxVisualizer();

    @FXML
    private TextField houseNumberInput;

    public void initialize() {
        firstnameInput.textProperty()
                .bindBidirectional(viewModel.firstNameProperty());
        lastnameInput.textProperty()
                .bindBidirectional(viewModel.lastNameProperty());
        companynameInput.textProperty()
                .bindBidirectional(viewModel.companyNameProperty());
        phoneNumberInput.textProperty()
                .bindBidirectional(viewModel.phoneNumberProperty());
        streetInput.textProperty()
                .bindBidirectional(viewModel.streetProperty());
        placeInput.textProperty()
                .bindBidirectional(viewModel.placeProperty());
        postcodeInput.textProperty()
                .bindBidirectional(viewModel.postcodeProperty());
        countryInput.textProperty()
                .bindBidirectional(viewModel.countryProperty());
        emailInput.textProperty()
                .bindBidirectional(viewModel.emailProperty());
        houseNumberInput.textProperty()
                .bindBidirectional(viewModel.houseNumberProperty());

        IntegerProperty property = viewModel.idProperty();

        validationVisualizer.initVisualization(
                viewModel.getFirstNameValidator(), firstnameInput);

        validationVisualizer.initVisualization(
                viewModel.getLastNameValidator(), lastnameInput);

        validationVisualizer.initVisualization(
                viewModel.getPhoneNumberValidator(), phoneNumberInput);

        validationVisualizer.initVisualization(
                viewModel.getStreetValidator(), streetInput);

        validationVisualizer.initVisualization(
                viewModel.getPlaceValidator(), placeInput);

        validationVisualizer.initVisualization(
                viewModel.getPostcodeValidator(), postcodeInput);

        validationVisualizer.initVisualization(
                viewModel.getCountryValidator(), countryInput);

        validationVisualizer.initVisualization(
                viewModel.getEmailValidator(), emailInput);

        validationVisualizer.initVisualization(
                viewModel.getHouseNumberValidator(), houseNumberInput);
    }
}
