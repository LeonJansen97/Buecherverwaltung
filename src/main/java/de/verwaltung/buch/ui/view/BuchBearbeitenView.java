package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.service.BuchService;

@Route("books/edit/")
@PageTitle("Buch bearbeiten")
public class BuchBearbeitenView extends VerticalLayout implements HasUrlParameter<Long> {
    private final TextField titelField = new TextField("Titel");
    private final TextField autorField = new TextField("Autor");
    private final TextField veroeffentlichungsJahrField = new TextField("Veröffentlichungsjahr");
    private final TextArea beschreibungTextArea = new TextArea("Beschreibung");

    private final BuchService buchService;

    private Long buchId;

    public BuchBearbeitenView(BuchService buchService) {
        this.buchService = buchService;
        H2 ueberschriftSeite = new H2("Buch bearbeiten");

        Div headingWrapper = new Div(ueberschriftSeite);
        headingWrapper.getStyle()
                .set("text-align", "center")
                .set("width", "100%");

        setPadding(true);
        setWidth("100%");
        setAlignItems(Alignment.CENTER);

        Button bearbeitenButton = new Button("Speichern");
        bearbeitenButton.addClickListener(e -> {
            BuchDTO buchDTO = new BuchDTO();
            buchDTO.setId(buchId);
            buchDTO.setTitel(titelField.getValue());
            buchDTO.setAutor(autorField.getValue());
            buchDTO.setVeroeffentlichungsJahr(veroeffentlichungsJahrField.getValue());
            buchDTO.setBeschreibung(beschreibungTextArea.getValue());

            buchService.addBookToInventary(buchDTO);

            Notification.show("Buch gespeichert");
            clearForm();
        });

        add(
                headingWrapper,
                titelField,
                autorField,
                veroeffentlichungsJahrField,
                beschreibungTextArea,
                bearbeitenButton
        );
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long parameter) {
        this.buchId = parameter;

        BuchDTO buchDTO = buchService.findBookById(buchId);

        if (buchDTO != null) {
            titelField.setValue(buchDTO.getTitel());
            autorField.setValue(buchDTO.getAutor());
            veroeffentlichungsJahrField.setValue(buchDTO.getVeroeffentlichungsJahr());
            beschreibungTextArea.setValue(buchDTO.getBeschreibung());
        } else {
            Notification.show("Buch mit ID " + buchId + " nicht gefunden.");
        }
    }

    private void clearForm() {
        titelField.clear();
        autorField.clear();
        veroeffentlichungsJahrField.clear();
        beschreibungTextArea.clear();
    }
}
