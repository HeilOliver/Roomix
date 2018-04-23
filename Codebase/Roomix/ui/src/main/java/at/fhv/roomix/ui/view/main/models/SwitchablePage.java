package at.fhv.roomix.ui.view.main.models;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.*;
import javafx.scene.Parent;

/**
 * Roomix
 * at.fhv.roomix.ui.views.main
 * SwitchablePage
 * 28/03/2018 OliverH
 * <p>
 * A Page that is been shown in the main view. A individual scope is provided to the
 * underlying pages in the visual tree
 */
public class SwitchablePage implements Scope {

    private static ObjectProperty<SwitchablePage> selectedItem = new SimpleObjectProperty<>();

    private final StringProperty tag = new SimpleStringProperty();
    private final StringProperty header = new SimpleStringProperty();
    private final StringProperty glyph = new SimpleStringProperty();

    private final BooleanProperty selected = new SimpleBooleanProperty();
    private final BooleanProperty enable = new SimpleBooleanProperty();

    private final Parent pane;

    public <ViewType extends FxmlView<? extends ViewModelType>, ViewModelType extends ViewModel>
    SwitchablePage(String menuEntryTag, String glyphName, Class<? extends ViewType> viewType) {

        tag.setValue(menuEntryTag);
        header.setValue(menuEntryTag);

        FluentViewLoader.FxmlViewStep<ViewType, ViewModelType> view
                = FluentViewLoader.fxmlView(viewType);
        view.providedScopes(this);
        pane = view.load().getView();

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

    public ReadOnlyBooleanProperty enableProperty() {
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
