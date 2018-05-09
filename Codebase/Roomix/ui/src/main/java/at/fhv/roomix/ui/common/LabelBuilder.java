package at.fhv.roomix.ui.common;

import at.fhv.roomix.controller.contact.model.ContactPojo;
import at.fhv.roomix.controller.reservation.model.ArrangementPojo;
import at.fhv.roomix.ui.view.reservation.edit.unit.ILabelBuilder;

public class LabelBuilder {

    private static ILabelBuilder<ArrangementPojo> arrangementILabelBuilder = new ILabelBuilder<ArrangementPojo>() {
        @Override
        public String build(ArrangementPojo pojo) {
            StringBuilder sb = new StringBuilder();
            sb.append(pojo.getDescription());
            sb.append(" - ");
            if (pojo.getDiscount() != null) {
                sb.append(pojo.getDiscount().getDiscount());
                sb.append("%");
            } else if (pojo.getPrice() != null) {
                sb.append(pojo.getPrice().getPrice());
                sb.append("€");
            } else {
                sb.append("?");
            }
            return sb.toString();
        }
    };
    private static ILabelBuilder<ContactPojo> personILabelBuilder = new ILabelBuilder<ContactPojo>() {
        @Override
        public String build(ContactPojo pojo) {
            StringBuilder sb = new StringBuilder();
            sb.append(pojo.getFirstName());
            sb.append(" ");
            sb.append(pojo.getLastName());
            if(pojo.getCompanyName() != null){
                sb.append(" - ").append(pojo.getCompanyName());
            }
            return sb.toString();
        }
    };

    public static ILabelBuilder<ArrangementPojo> getArrangementILabelBuilder() {
        return arrangementILabelBuilder;
    }

    public static ILabelBuilder<ContactPojo> getPersonILabelBuilder() {
        return personILabelBuilder;
    }
}
