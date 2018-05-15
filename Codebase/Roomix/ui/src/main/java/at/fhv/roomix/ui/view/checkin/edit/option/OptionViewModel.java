package at.fhv.roomix.ui.view.checkin.edit.option;

import at.fhv.roomix.controller.model.ReservationOptionPojo;
import at.fhv.roomix.controller.model.ReservationPojo;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import at.fhv.roomix.ui.view.reservation.scope.ReservationViewScope;
import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;
import de.saxsys.mvvmfx.utils.mapping.accessorfunctions.StringGetter;
import de.saxsys.mvvmfx.utils.mapping.accessorfunctions.StringSetter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

import java.time.format.DateTimeFormatter;

public class OptionViewModel extends SubscribeAbleViewModel<ReservationOptionPojo> {

    @InjectScope
    private ReservationViewScope viewScope;

    private BooleanProperty paidFlag = new SimpleBooleanProperty(false);
    private ModelWrapper<ReservationOptionPojo> modelWrapper = new ModelWrapper<>();

    public void initialize(){}

    @Override
    protected void afterSubscribe(boolean isNew) {
        ReservationOptionPojo option = currModel.get();
        if(option != null){
            modelWrapper.set(option);
            if(option.getOptionStatus() == 1){
                paidFlag.setValue(true);
            }
        }
    }

    public BooleanProperty paidFlagProperty() {
        return paidFlag;
    }

    public StringProperty optionDescriptionProperty(){
        return modelWrapper.field("optionDescription", ReservationOptionPojo::getOptionDescription, ReservationOptionPojo::setOptionDescription, "");
    }

    public StringProperty optionDueDateProperty(){
        return modelWrapper.field("optionDueDate",
                reservationOptionPojo -> {
                    if(reservationOptionPojo.getOptionDueDate() == null) {
                        return "";
                    }
                    return reservationOptionPojo.getOptionDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
                },
                (StringSetter<ReservationOptionPojo>) (reservationOptionPojo, s) -> {
                }, "");
    }

    public StringProperty optionFeeProperty(){
        return modelWrapper.field("optionFee",
                (StringGetter<ReservationOptionPojo>) reservationOptionPojo -> {
                    int price = 0;
                    if(reservationOptionPojo.getOptionFee() != null){
                        price = reservationOptionPojo.getOptionFee().getPrice();
                    }
                    return Integer.toString(price);
                },
                (reservationOptionPojo, s) -> {}, "0");
    }


}
