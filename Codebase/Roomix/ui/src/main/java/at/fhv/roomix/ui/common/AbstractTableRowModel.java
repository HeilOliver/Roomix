package at.fhv.roomix.ui.common;

/**
 * Roomix
 * at.fhv.roomix.ui.common
 * AbstractTableRowModel
 * 17/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public abstract class AbstractTableRowModel<T> {

    protected T pojo;

    public AbstractTableRowModel(T pojo) {
        this.pojo = pojo;
    }

    public T getPojo() {
        return pojo;
    }
}
