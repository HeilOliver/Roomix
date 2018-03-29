package at.fhv.roomix.ui.views.main.menuitem;

import at.fhv.roomix.ui.views.main.models.SwitchablePage;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main.menuitem
 * SideBarMenuItemVM
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarMenuItemVM implements ViewModel {
    private BooleanProperty collapsed;
    private StringProperty tag;
    private StringProperty glyph;
    private BooleanProperty enabled;
    private SwitchablePage model;

    public SideBarMenuItemVM(SwitchablePage page, BooleanProperty collapsed) {
        tag = page.tagProperty();
        glyph = page.glyphProperty();
        enabled = page.enableProperty();
        this.collapsed = collapsed;
        model = page;
    }

    public BooleanProperty collapsedProperty() {
        return collapsed;
    }

    public StringProperty tagProperty() {
        return tag;
    }

    public StringProperty glyphProperty() {
        return glyph;
    }

    public void clicked() {
        if (!enabled.get()) return;
        model.selectThis();
    }
}
