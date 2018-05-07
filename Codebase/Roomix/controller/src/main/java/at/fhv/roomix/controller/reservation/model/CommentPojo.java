package at.fhv.roomix.controller.reservation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Roomix
 * at.fhv.roomix.controller.reservation.contact
 * CommentPojo
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CommentPojo {

    @NotNull(message = "comment cannot be null")
    @Size(min = 1, max = 500, message = "Comment must be between 1 and 500 characters")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
