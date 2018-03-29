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
    private Text tex_firstname;
    @FXML
    private Text tex_lastname;
    @FXML
    private Text tex_companyname;
    @FXML
    private Text tex_street;
    @FXML
    private Text tex_city;
    @FXML
    private Text tex_postalcode;
    @FXML
    private GridPane pane_content;
    @FXML
    private VBox pane_nothing;

    public void initialize(){
        tex_firstname.textProperty().bind(viewModel.firstnameProperty());
        tex_lastname.textProperty().bind(viewModel.lastnameProperty());
        tex_companyname.textProperty().bind(viewModel.companynameProperty());
        tex_street.textProperty().bind(viewModel.streetProperty());
        tex_city.textProperty().bind(viewModel.placeProperty());
        tex_postalcode.textProperty().bind(viewModel.postcodeProperty());

        pane_content.visibleProperty().bind(viewModel.detailAvailableProperty());
        pane_nothing.visibleProperty().bind(viewModel.detailAvailableProperty().not());
    }
}
