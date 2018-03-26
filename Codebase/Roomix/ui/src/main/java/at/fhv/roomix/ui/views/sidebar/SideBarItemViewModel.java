package at.fhv.roomix.ui.views.sidebar;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarItemViewModel
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarItemViewModel implements ViewModel {

    private final BooleanProperty collapsed = new SimpleBooleanProperty();
    private final StringProperty tag = new SimpleStringProperty();
    private final StringProperty glyph = new SimpleStringProperty();
    private SideBarItem sideBarItem;


    public StringProperty glyphProperty() {
        return glyph;
    }

    public SideBarItemViewModel(SideBarItem sideBarItem, BooleanProperty collapsed) {
        this.sideBarItem = sideBarItem;
        this.collapsed.bindBidirectional(collapsed);

        tag.bind(sideBarItem.tagProperty());
        glyph.bind(sideBarItem.glyphProperty());
    }

    public BooleanProperty collapsedProperty() {
        return collapsed;
    }

    public StringProperty tagProperty() {
        return tag;
    }

    void selected() {
        sideBarItem.selectThis();
    }
}
