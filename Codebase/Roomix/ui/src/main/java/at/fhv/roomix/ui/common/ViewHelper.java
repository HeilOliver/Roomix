package at.fhv.roomix.ui.common;

import de.saxsys.mvvmfx.ViewModel;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * ViewHelper
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ViewHelper {
    private ViewHelper(){
    }

    public static void setChildren(AnchorPane pane, Parent parent) {
        pane.getChildren().clear();
        if (parent == null) return;
        AnchorPane.setTopAnchor(parent, 0.0);
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
        pane.getChildren().add(parent);
    }

    public static void setChildren(HBox pane, Parent parent) {
        pane.getChildren().clear();
        if (parent == null) return;
        pane.getChildren().add(parent);
    }
}
