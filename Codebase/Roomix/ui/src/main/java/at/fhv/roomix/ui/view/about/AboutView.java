package at.fhv.roomix.ui.view.about;

import at.fhv.roomix.ui.common.ResourceProvider;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

import javax.inject.Inject;
import java.util.ResourceBundle;

/**
 * Roomix
 * at.fhv.roomix.ui.view.about
 * AboutView
 * 14/04/2018 Oliver
 * <p>
 * The Controller Class for the {@code AboutView}
 */
public class AboutView implements FxmlView<AboutViewModel> {

    @InjectViewModel
    private AboutViewModel viewModel;

    @Inject
    private ResourceBundle resourceBundle;



}
