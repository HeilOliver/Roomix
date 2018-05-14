package at.fhv.roomix.ui.view.reservation.edit.unit;

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
public class PacketsItemViewModel<T> implements ViewModel {
    private final T pojo;
    private StringProperty content = new SimpleStringProperty();
    private BooleanProperty checked = new SimpleBooleanProperty();

    public PacketsItemViewModel(T pojo, ILabelBuilder<T> builder) {
        this.pojo = pojo;
        content.setValue(builder.build(pojo));
    }

    StringProperty contentProperty() {
        return content;
    }

    public boolean isChecked() {
        return checked.get();
    }

    BooleanProperty checkedProperty() {
        return checked;
    }

    public T getPojo() {
        return pojo;
    }

    public void check() {
        checkedProperty().setValue(true);
    }
}
