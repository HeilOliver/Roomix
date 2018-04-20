package at.fhv.roomix.ui.view.reservation.edit;

import at.fhv.roomix.controller.reservation.model.CommentPojo;
import at.fhv.roomix.controller.reservation.model.ReservationOptionPojo;
import at.fhv.roomix.ui.view.reservation.edit.comment.CommentView;
import at.fhv.roomix.ui.view.reservation.edit.item.IContentBuilder;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemControlViewModel;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerList;
import at.fhv.roomix.ui.view.reservation.edit.item.ItemHandlerSingle;
import at.fhv.roomix.ui.view.reservation.edit.option.OptionView;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
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

    @InjectScope
    private ReservationViewScope viewScope;

    public void initialize() {
    }

    //region Option
    private static final IContentBuilder<ReservationOptionPojo> optionBuilder = (pojo -> {
        if (pojo.getDueDate() == null) {
            return "";
        }
        return pojo.getDueDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
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
