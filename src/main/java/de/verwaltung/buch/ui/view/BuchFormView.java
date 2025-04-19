package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.buch.service.BuchDTO;
import de.verwaltung.buch.service.BuchService;

@Route("books/new")
@PageTitle("Buch hinzufügen")
public class BuchFormView extends VerticalLayout {
    private final TextField titelField = new TextField("Titel");
    private final TextField autorField = new TextField("Autor");
    private final TextField veroeffentlichungsJahrField = new TextField("Veröffentlichungsjahr");
    private final TextArea beschreibungTextArea = new TextArea("Beschreibung");

    public BuchFormView(BuchService buchService) {
        setPadding(true);
        setWidth("400px");
        setAlignItems(Alignment.START);

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
