package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.MainLayout;
import de.verwaltung.buch.service.BuchService;
import de.verwaltung.buch.ui.component.ConfirmDialog;

@Route(value = "books/delete/", layout = MainLayout.class)
@PageTitle("Buch entfernen")
public class BuchEntfernenView extends VerticalLayout implements HasUrlParameter<Long> {
    private static final String MESSAGE_DELETE_QUESTION = "Bist du sicher, dass du dieses Buch entfernen möchtest?";

    private Long buchId;

    public BuchEntfernenView(BuchService buchService) {

        H2 ueberschrift = new H2("Buch entfernen");

        centerElements();

        Button entfernenButton = new Button("Entfernen");
        entfernenButton.addClickListener(e -> {
           ConfirmDialog dialog = new ConfirmDialog(MESSAGE_DELETE_QUESTION,
                   () -> {
               buchService.setDeleteFlag(buchId);
               getUI().ifPresent(ui -> ui.navigate("books"));
               Notification.show("Das Buch wurde erfolgreich entfernt.");
           });

           dialog.open();
        });

        add(ueberschrift, entfernenButton);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long parameter) {
        this.buchId = parameter;
    }

    private void centerElements() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(true);
        setSpacing(true);
    }
}
