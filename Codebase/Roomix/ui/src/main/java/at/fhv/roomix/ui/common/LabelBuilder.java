package at.fhv.roomix.ui.common;

import at.fhv.roomix.controller.model.ContactPojo;
import at.fhv.roomix.controller.model.ArrangementPojo;
import at.fhv.roomix.controller.model.PersonPojo;
import at.fhv.roomix.ui.view.reservation.edit.unit.ILabelBuilder;

public class LabelBuilder {

    private static ILabelBuilder<ArrangementPojo> arrangementILabelBuilder = new ILabelBuilder<ArrangementPojo>() {
        @Override
        public String build(ArrangementPojo pojo) {
            StringBuilder sb = new StringBuilder();
            sb.append(pojo.getDescription());
            sb.append(" - ");
            if (pojo.getPrice() != null) {
                sb.append(pojo.getPrice().getPrice()/100);
                sb.append("€");
            } else {
                sb.append("?");
            }
            return sb.toString();
        }
    };
    private static ILabelBuilder<PersonPojo> personILabelBuilder = new ILabelBuilder<PersonPojo>() {
        @Override
        public String build(PersonPojo pojo) {
            StringBuilder sb = new StringBuilder();
            sb.append(pojo.getForeName());
            sb.append(" ");
            sb.append(pojo.getLastName());
            return sb.toString();
        }
    };

    public static ILabelBuilder<ArrangementPojo> getArrangementILabelBuilder() {
        return arrangementILabelBuilder;
    }

    public static ILabelBuilder<PersonPojo> getPersonILabelBuilder() {
        return personILabelBuilder;
    }
}
