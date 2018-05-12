package at.fhv.roomix.ui.view.checkin.edit;

import at.fhv.roomix.controller.model.*;
import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.checkin.edit.contracting_party.ContractingPartyView;
import at.fhv.roomix.ui.view.checkin.edit.person.PersonView;
import at.fhv.roomix.ui.view.checkin.edit.unit.UnitView;
import at.fhv.roomix.ui.view.reservation.edit.comment.CommentView;
import at.fhv.roomix.ui.view.reservation.edit.item.IContentBuilder;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerList;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerSingle;
import at.fhv.roomix.ui.view.reservation.edit.option.OptionView;
import at.fhv.roomix.ui.view.reservation.scope.EDataProvider;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CheckInEditViewModel implements ViewModel {

    @InjectScope
    private ReservationViewScope viewScope;

    private ObjectProperty<ItemControlViewModel<ContactPojo>> contractingPartyProperty;
    private ObjectProperty<Parent> currentView = new SimpleObjectProperty<>();
    private ObjectProperty<ItemControlViewModel> currentSelection = new SimpleObjectProperty<>();

    @InjectResourceBundle
    private ResourceBundle bundle;

    private final CheckInEditScope editScope = new CheckInEditScope();


    /**
     * Region Contracting Party
     */
    private final IContentBuilder<ContactPojo> contactBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if ((pojo.getFirstName() == null || pojo.getLastName() == null)) {
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
    /* created in init method */
    private ItemHandlerSingle<ContactPojo> contractingPartyHandler;

    ReadOnlyObjectProperty<ItemControlViewModel<ContactPojo>> getContractingPartyControl(){
        return contractingPartyHandler.currentItem();
    }

    ReadOnlyBooleanProperty isContractingPartyAddAble() {
        return contractingPartyHandler.isAddAble();
    }


    /**
     * Region Persons
     */

    private final IContentBuilder<PersonPojo> personBuilder = pojo -> {
        StringBuilder sb = new StringBuilder();

        if ((pojo.getForeName() == null || pojo.getLastName() == null)) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.contact.tag.noname"));
        } else {
            sb.append(pojo.getForeName());
            sb.append(" ");
            sb.append(pojo.getLastName());
        }
        return sb.toString();
    };

    private ItemHandlerList<PersonPojo> personHandler;

    ObservableList<ItemControlViewModel> getPersonControls() {
        return personHandler.currentItems();
    }

    ReadOnlyBooleanProperty isPersonAddAble() {
        return personHandler.isAddAble();
    }

    void addPerson() {
        personHandler.add();
    }

    /**
     * Region Unit
     */
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
        if (pojo.getPrice() == null || pojo.getPrice().getPrice() <= 0) {
            sb.append("? €");
        } else {
            sb.append(pojo.getPrice().getPrice());
            sb.append("€");
        }
        return sb.toString();
    });

    private ItemHandlerList<ReservationUnitPojo> unitHandler;

    ObservableList<ItemControlViewModel> getUnitControls() {
        return unitHandler.currentItems();
    }

    ReadOnlyBooleanProperty isUnitAddAble() {
        return unitHandler.isAddAble();
    }

    /**
     * Region Option
     */
    private final IContentBuilder<ReservationOptionPojo> optionBuilder = (pojo -> {
        StringBuilder sb = new StringBuilder();

        if (pojo.getOptionDueDate() == null) {
            sb.append(StringResourceResolver.getStaticResolve(bundle, "reservation.edit.option.tag.nodate"));
        } else {
            sb.append(pojo.getOptionDueDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        }
        sb.append(" - ");
        if (pojo.getOptionFee() == null) {
            sb.append("? €");
        } else {
            sb.append((float) pojo.getOptionFee().getPrice()/100);
            sb.append("€");
        }
        return sb.toString();
    });

    private final ItemHandlerSingle<ReservationOptionPojo> optionHandler = new ItemHandlerSingle<>(
            OptionView.class, optionBuilder, currentSelection, currentView, ReservationOptionPojo::new
    );

    ReadOnlyObjectProperty<ItemControlViewModel<ReservationOptionPojo>> getOptionControl(){
        return optionHandler.currentItem();
    }

    /**
     * Region Comment
     */
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

    /** End region */

    public void initialize(){
        viewScope.init(EDataProvider.ReservationProvider);

        /*
           Due to the new view scope init method, the ItemHandler can only be created
           after initializing the correct view scope, otherwise the view scope would be null and
           the DetailViewTuple invokes the FluentLoader without a given scope which results in an invalid
           Scope hierarchy exception
        */
        contractingPartyHandler = new ItemHandlerSingle<>(
                ContractingPartyView.class, contactBuilder, currentSelection, currentView, ContactPojo::new, viewScope);

        personHandler = new ItemHandlerList<>(
                PersonView.class, personBuilder, currentSelection, currentView, PersonPojo::new, viewScope
        );

        unitHandler = new ItemHandlerList<>(
                UnitView.class, unitBuilder, currentSelection, currentView, ReservationUnitPojo::new, viewScope
        );


        viewScope.selectedPojoProperty().addListener((observable, oldValue, newValue) -> {
            if(viewScope.selectedPojoProperty() != null) {
                ReservationPojo reservationPojo = viewScope.selectedPojoProperty().get();
                if(reservationPojo != null && reservationPojo.getPersons() != null) {
                    personHandler.setObjects(reservationPojo.getPersons());
                    personHandler.hideDeleteButton();
                }
                if(reservationPojo != null && reservationPojo.getUnits() != null) {
                    unitHandler.setObjects(reservationPojo.getUnits());
                    unitHandler.hideDeleteButton();
                }
                if(reservationPojo != null && reservationPojo.getContractingParty() != null) {
                    contractingPartyHandler.setObject(reservationPojo.getContractingParty());
                    contractingPartyHandler.hideDeleteButton();
                }
                if(reservationPojo != null &&  reservationPojo.getOption() != null) {
                    optionHandler.setObject(reservationPojo.getOption());
                    optionHandler.hideDeleteButton();
                }
                if(reservationPojo != null && reservationPojo.getComment() != null){
                    commentHandler.setObject(reservationPojo.getComment());
                    commentHandler.hideDeleteButton();
                }

            }
        });

        viewScope.subscribe(ReservationViewScope.commandOnCommit, (s, objects) -> {
            unitHandler.setCheckMarkVisible(true);
        });
    }
    public ObjectProperty<ItemControlViewModel<ContactPojo>> contractingPartyPropertyProperty() {
        return contractingPartyProperty;
    }

    ReadOnlyObjectProperty<Parent> getCurrentDetailView() {
        return currentView;
    }

}
