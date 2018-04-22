package at.fhv.roomix.ui.view.reservation.edit.unit;

import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
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
    private final ArrangementPojo pojo;

    public PacketsItemViewModel(ArrangementPojo pojo) {
        this.pojo = pojo;
        StringBuilder sb = new StringBuilder();
        sb.append(pojo.getDescription());
        sb.append(" - ");
        if (pojo.getDiscount() != null) {
            sb.append(pojo.getDiscount().getDiscount());
            sb.append("%");
        } else if (pojo.getPrice() != null) {
            sb.append(pojo.getPrice().getPrice());
            sb.append("€");
        } else {
            sb.append("?");
        }
        content.setValue(sb.toString());
    }

    private StringProperty content = new SimpleStringProperty();
    private BooleanProperty checked = new SimpleBooleanProperty();

    StringProperty contentProperty() {
        return content;
    }

    public boolean isChecked() {
        return checked.get();
    }

    BooleanProperty checkedProperty() {
        return checked;
    }
}
