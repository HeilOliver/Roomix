package at.fhv.roomix.ui.view.reservation.edit.item;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.item
 * IContentBuilder
 * 18/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public interface IContentBuilder<T> {

    String buildString(T pojo);

}
