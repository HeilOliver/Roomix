package at.fhv.roomix.ui.view.checkin.edit.unit;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class InfoLabel extends Label {

    public InfoLabel(String text){
        super(text);
        setPadding(new Insets(5));
        setStyle("-fx-font-size: 1em; -fx-font-weight: bold;");
    }

}
