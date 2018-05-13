package at.fhv.roomix.ui.view.reservation.edit.comment;

import at.fhv.roomix.ui.common.StringResourceResolver;
import at.fhv.roomix.ui.view.reservation.edit.SubscribeAbleViewModel;
import de.saxsys.mvvmfx.InjectResourceBundle;
import de.saxsys.mvvmfx.utils.validation.FunctionBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import de.saxsys.mvvmfx.utils.validation.Validator;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.StringProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.reservation.edit.comment
 * CommentViewModel
 * 20/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CommentViewModel extends SubscribeAbleViewModel<CommentPojo> {
    private static final Logger LOG = LoggerFactory.getLogger(CommentViewModel.class);

    @InjectResourceBundle
    private ResourceBundle resourceBundle;
    private Validator commentValidator;

    ValidationStatus commentValidation() {
        return commentValidator.getValidationStatus();
    }

    public void initialize() {
        commentValidator = new FunctionBasedValidator<>(
                commentProperty(),
                string -> string != null && !string.trim().isEmpty(),
                ValidationMessage.error(StringResourceResolver.getStaticResolve(resourceBundle,
                        "reservation.edit.option.descriptionEmpty")));

    }

    void commitChange() {
        commit();
    }

    ReadOnlyBooleanProperty isCommitAble() {
        return pojoWrapper.dirtyProperty();
    }

    StringProperty commentProperty() {
        return pojoWrapper.field("comment",
                CommentPojo::getComment, CommentPojo::setComment);
    }

}
