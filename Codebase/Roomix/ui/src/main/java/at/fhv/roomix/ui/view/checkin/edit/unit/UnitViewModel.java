package at.fhv.roomix.ui.view.checkin.edit.unit;

import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.controller.reservation.model.ReservationUnitPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItem;
import at.fhv.roomix.ui.view.reservation.edit.unit.PacketsItemViewModel;
import de.saxsys.mvvmfx.utils.itemlist.ListTransformation;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UnitViewModel extends SubscribeAbleViewModel<ReservationUnitPojo> {

    private ObservableList<PacketsItemViewModel> arrangementList = FXCollections.emptyObservableList();
    private BooleanProperty listChanged = new SimpleBooleanProperty();

    public UnitViewModel(){

    }

    public ObservableList<PacketsItemViewModel> getArrangementList() {
        return arrangementList;
    }

    @Override
    protected void afterSubscribe(boolean isNew) {
        Collection<ArrangementPojo> arrangementPojos = currModel.get().getArrangements();
        if(arrangementPojos != null) {
            LinkedList<ArrangementPojo> arrangements = new LinkedList<>(arrangementPojos);
            ObservableList<ArrangementPojo> list = FXCollections.observableList(arrangements);
            ListTransformation<ArrangementPojo, PacketsItemViewModel> transArticle
                    = new ListTransformation<>(list, PacketsItemViewModel::new);
            ObservableList<PacketsItemViewModel> targetList = transArticle.getTargetList();
            arrangementList = targetList;
            listChanged.setValue(true);
        }
    }

    public BooleanProperty listChangedProperty() {
        return listChanged;
    }
}
