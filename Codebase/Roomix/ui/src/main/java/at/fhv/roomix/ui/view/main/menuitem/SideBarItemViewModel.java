package at.fhv.roomix.ui.view.main.menuitem;

import at.fhv.roomix.ui.view.main.models.SwitchablePage;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.menuitem
 * SideBarItemViewModel
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarItemViewModel implements ViewModel {
    private ReadOnlyBooleanProperty collapsed;
    private ReadOnlyBooleanProperty enabled;
    private ReadOnlyBooleanProperty selected;

    private ReadOnlyStringProperty tag;
    private ReadOnlyStringProperty glyph;
    private SwitchablePage model;

    public SideBarItemViewModel(SwitchablePage page, ReadOnlyBooleanProperty collapsed) {
        tag = page.tagProperty();
        glyph = page.glyphProperty();
        enabled = page.enableProperty();
        selected = page.selectedProperty();
        this.collapsed = collapsed;
        model = page;
    }

    ReadOnlyBooleanProperty collapsedProperty() {
        return collapsed;
    }

    ReadOnlyStringProperty tagProperty() {
        return tag;
    }

    ReadOnlyStringProperty glyphProperty() {
        return glyph;
    }

    ReadOnlyBooleanProperty selectedProperty() {
        return selected;
    }

    void clicked() {
        if (!enabled.get()) return;
        model.selectThis();
    }
}
