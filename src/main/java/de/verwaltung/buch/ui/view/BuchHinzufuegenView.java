package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.service.BuchService;

@Route("books/new")
@PageTitle("Buch hinzufügen")
public class BuchHinzufuegenView extends VerticalLayout {
    private final TextField titelField = new TextField("Titel");
    private final TextField autorField = new TextField("Autor");
    private final TextField veroeffentlichungsJahrField = new TextField("Veröffentlichungsjahr");
    private final TextArea beschreibungTextArea = new TextArea("Beschreibung");

    public BuchHinzufuegenView(BuchService buchService) {

        H2 ueberschriftSeite = new H2("Buch hinzufügen");

        Div headingWrapper = new Div(ueberschriftSeite);
        headingWrapper.getStyle()
                .set("text-align", "center")
                .set("width", "100%");

        setPadding(true);
        setWidth("100%");
        setAlignItems(Alignment.CENTER);

        Button speichernButton = new Button("Speichern");
        speichernButton.addClickListener(e -> {
            BuchDTO buchDTO = new BuchDTO();
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
                speichernButton
        );
    }

    private void clearForm() {
        titelField.clear();
        autorField.clear();
        veroeffentlichungsJahrField.clear();
        beschreibungTextArea.clear();
    }
}
