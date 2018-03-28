package at.fhv.roomix.ui.views.sidebar;

import javafx.beans.property.*;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.views.sidebar
 * SideBarItem
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SideBarItem {

    private final StringProperty tag = new SimpleStringProperty();
    private final StringProperty glyph = new SimpleStringProperty();
    private final BooleanProperty isSelected = new SimpleBooleanProperty();
    private static ObjectProperty<SideBarItem> selectedItem = new SimpleObjectProperty<>();
    private final Parent parent;

    public SideBarItem(String tag, String glyphName, Parent parent) {
        this.tag.setValue(tag);
        this.parent = parent;
        glyph.setValue(glyphName);
        selectedItem.addListener((observable,oldValue,newValue) -> {
            isSelected.setValue(newValue == this);
        });
    }

    public Parent getParent() {
        return parent;
    }

    public static ObjectProperty<SideBarItem> selectedItemProperty() {
        return selectedItem;
    }

    public BooleanProperty isSelectedProperty() {
        return isSelected;
    }

    public StringProperty glyphProperty() {
        return glyph;
    }

    public StringProperty tagProperty() {
        return tag;
    }

    void selectThis() {
        selectedItem.setValue(this);
    }
}
