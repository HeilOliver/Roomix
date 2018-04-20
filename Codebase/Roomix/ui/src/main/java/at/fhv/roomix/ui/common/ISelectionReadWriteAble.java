package at.fhv.roomix.ui.common;

import javafx.beans.property.ReadOnlyObjectProperty;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * ISelectionReadWriteAble
 * 18/04/2018 Oliver
 * <p>
 * An Interface for Read and Write into an ViewModel throw Java Code.
 */
public interface ISelectionReadWriteAble<T> {

    ReadOnlyObjectProperty<T> getSelectionProperty();

    void setSelection(T value);
}
