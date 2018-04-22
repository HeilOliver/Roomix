package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.reservation.model.ArticlePojo;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.unit
 * PacketsItemViewModel
 * 22/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PacketsItemViewModel implements ViewModel {
    private final ArticlePojo pojo;

    public PacketsItemViewModel(ArticlePojo pojo) {
        this.pojo = pojo;
        content.setValue(pojo.getDiscription() + " - " + pojo.getAmount() + "€");
    }

    private StringProperty content = new SimpleStringProperty();
    private BooleanProperty checked = new SimpleBooleanProperty();

    StringProperty contentProperty() {
        return content;
    }

    public boolean isChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }
}
