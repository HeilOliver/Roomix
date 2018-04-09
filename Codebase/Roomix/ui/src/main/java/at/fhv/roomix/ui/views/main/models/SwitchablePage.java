package at.fhv.roomix.ui.views.main.models;

import javafx.beans.property.*;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main
 * SwitchablePage
 * 28/03/2018 OliverH
 * <p>
 * Enter Description here
 */
public class SwitchablePage {

    private static ObjectProperty<SwitchablePage> selectedItem = new SimpleObjectProperty<>();
    private final StringProperty tag = new SimpleStringProperty();
    private final StringProperty header = new SimpleStringProperty();
    private final StringProperty glyph = new SimpleStringProperty();
    private final BooleanProperty selected = new SimpleBooleanProperty();
    private final BooleanProperty enable = new SimpleBooleanProperty();
    private final Parent pane;

    public SwitchablePage(String menuEntryTag, String glyphName, Parent correspondingPane) {
        tag.setValue(menuEntryTag);
        header.setValue(menuEntryTag);
        pane = correspondingPane;
        glyph.setValue(glyphName);
        enable.setValue(true);
        selectedItem.addListener((observable, oldValue, newValue) -> {
            selected.setValue(newValue == this);
        });
    }

    public static ObjectProperty<SwitchablePage> selectedItemProperty() {
        return selectedItem;
    }

    public StringProperty headerProperty() {
        return header;
    }

    public BooleanProperty enableProperty() {
        return enable;
    }

    public Parent getCorrespondingPane() {
        return pane;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public StringProperty glyphProperty() {
        return glyph;
    }

    public StringProperty tagProperty() {
        return tag;
    }

    public void selectThis() {
        selectedItem.setValue(this);
    }
}
