package at.fhv.roomix.ui.view.reservation.edit;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.comment
 * ISubscribeAbleViewModel
 * 20/04/2018 Oliver
 * <p>
 *
 */
public interface ISubscribeAbleViewModel<T> extends ViewModel {

    void subscribe(ObjectProperty<T> property);

    void unSubscribe();
}
