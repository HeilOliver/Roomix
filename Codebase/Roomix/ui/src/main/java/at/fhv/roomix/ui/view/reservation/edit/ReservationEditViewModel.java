package at.fhv.roomix.ui.view.reservation.edit;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.CommentPojo;
import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.comment.CommentView;
import at.fhv.roomix.ui.view.reservation.edit.contact.ContactView;
import at.fhv.roomix.ui.view.reservation.edit.item.IContentBuilder;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerList;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerSingle;
import at.fhv.roomix.ui.view.reservation.edit.option.OptionView;
import at.fhv.roomix.ui.view.reservation.edit.unit.UnitView;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit
 * ReservationEditViewModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ReservationEditViewModel implements ViewModel {
    private static final Logger LOG = LoggerFactory.getLogger(ReservationEditViewModel.class);
    private ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();
    private ObjectProperty<ItemControlViewModel> currentSelection = new SimpleObjectProperty<>();

    @InjectResourceBundle
    private ResourceBundle bundle;

    @InjectScope
    private ReservationViewScope viewScope;

    private final ReservationEditScope editScope = new ReservationEditScope();

    public void initialize() {
        contractingPartyHandler.currentItem().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                editScope.currContractingPartyProperty().setValue(null);
            } else {
                editScope.currContractingPartyProperty().setValue(newValue.getPojo());
            }
        });
    }

    //region ContractingParty
    private final IContentBuilder<ContactPojo> contactBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if ((pojo.getFirstName() == null || pojo.getLastName() == null) || pojo.getCompanyName() == null) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.contact.tag.noname"));
        } else {
            if (pojo.getCompanyName() == null) {
                sb.append(pojo.getFirstName());
                sb.append(" ");
                sb.append(pojo.getLastName());
            } else {
                sb.append(pojo.getCompanyName());
            }
        }
        sb.append(" - ");
        sb.append(pojo.getPlace());
        return sb.toString();
    });

    private final ItemHandlerSingle<ContactPojo> contractingPartyHandler = new ItemHandlerSingle<>(
            ContactView.class, contactBuilder, currentSelection, currentView, ContactPojo::new
    );

    ReadOnlyObjectProperty<ItemControlViewModel<ContactPojo>> getContractingPartyControl(){
        return contractingPartyHandler.currentItem();
    }

    ReadOnlyBooleanProperty isContractingPartyAddAble() {
        return contractingPartyHandler.isAddAble();
    }

    void addContractingParty() {
        contractingPartyHandler.add();
    }
    //endregion

    //region Person
    private final ItemHandlerList<ContactPojo> personHandler = new ItemHandlerList<>(
            ContactView.class, contactBuilder, currentSelection, currentView, ContactPojo::new
    );

    ObservableList<ItemControlViewModel> getPersonControls() {
        return personHandler.currentItems();
    }

    ReadOnlyBooleanProperty isPersonAddAble() {
        return personHandler.isAddAble();
    }

    void addPerson() {
        personHandler.add();
    }
    //endregion

    //region Units
    private final IContentBuilder<ReservationUnitPojo> unitBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if (pojo.getStartDate() == null || pojo.getEndDate() == null) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.unit.tag.nodate"));
        } else {
            sb.append(pojo.getStartDate().format(DateTimeFormatter.ofPattern("dd MMMM")));
            sb.append(" -> ");
            sb.append(pojo.getEndDate().format(DateTimeFormatter.ofPattern("dd MMMM")));
        }
        sb.append(" - ");
        if (pojo.getPrice() <= 0) {
            sb.append("? €");
        } else {
            sb.append(pojo.getPrice());
            sb.append("€");
        }
        return sb.toString();
    });

    private final ItemHandlerList<ReservationUnitPojo> unitHandler = new ItemHandlerList<>(
            UnitView.class, unitBuilder, currentSelection, currentView, ReservationUnitPojo::new, editScope
    );

    ObservableList<ItemControlViewModel> getUnitControls() {
        return unitHandler.currentItems();
    }

    ReadOnlyBooleanProperty isUnitAddAble() {
        return unitHandler.isAddAble();
    }

    void addUnit() {
        unitHandler.add();
    }
    //endregion

    //region Option
    private final IContentBuilder<ReservationOptionPojo> optionBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if (pojo.getDueDate() == null) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.option.tag.nodate"));
        } else {
            sb.append(pojo.getDueDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        }
        sb.append(" - ");
        if (pojo.getPrice() <= 0) {
            sb.append("? €");
        } else {
            sb.append(pojo.getPrice());
            sb.append("€");
        }
        return sb.toString();
    });

    private final ItemHandlerList<ReservationOptionPojo> optionHandler = new ItemHandlerList<>(
            OptionView.class, optionBuilder, currentSelection, currentView, ReservationOptionPojo::new
    );

    ObservableList<ItemControlViewModel> getOptionControls() {
        return optionHandler.currentItems();
    }

    ReadOnlyBooleanProperty isOptionAddAble() {
        return optionHandler.isAddAble();
    }

    void addOption() {
        optionHandler.add();
    }
    //endregion

    //region Reservation Comment
    private static final IContentBuilder<CommentPojo> commentBuilder = (pojo -> {
        if (pojo.getComment() == null) return "-";
        if (pojo.getComment().length() >= 20)
            return String.format("%s...", pojo.getComment().substring(0, 17));
        return pojo.getComment();
    });

    private final ItemHandlerSingle<CommentPojo> commentHandler = new ItemHandlerSingle<>(
            CommentView.class, commentBuilder, currentSelection, currentView, CommentPojo::new);

    ReadOnlyObjectProperty<ItemControlViewModel<CommentPojo>> getCommentControl(){
        return commentHandler.currentItem();
    }

    ReadOnlyBooleanProperty isCommendAddAble() {
        return commentHandler.isAddAble();
    }

    void addComment() {
        commentHandler.add();
    }
    //endregion

    ReadOnlyObjectProperty<Parent> getCurrentDetailView() {
        return currentView;
    }
}
