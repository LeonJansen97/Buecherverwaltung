package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.MainLayout;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.service.BuchService;

@Route(value="books/add", layout = MainLayout.class)
@PageTitle("Buch hinzufügen")
public class BuchHinzufuegenView extends VerticalLayout {
    private final TextField titelField = new TextField("Titel");
    private final TextField autorField = new TextField("Autor");
    private final TextField veroeffentlichungsJahrField = new TextField("Veröffentlichungsjahr");
    private final TextArea beschreibungTextArea = new TextArea("Beschreibung");
    private final Button speichernButton = new Button("Speichern");

    public BuchHinzufuegenView(BuchService buchService) {

        H2 ueberschrift = new H2("Buch hinzufügen");

        centerElements();

        setInputFieldStyles();

        speichernButton.setEnabled(false);

        titelField.addValueChangeListener(e -> {
            titelField.setInvalid(e.getValue().isEmpty()
            );
            updateSaveButtonState();
        });

        autorField.addValueChangeListener(e -> {
            autorField.setInvalid(
                    e.getValue().isEmpty() ||
                            e.getValue().matches(".*\\d.*")
            );
            updateSaveButtonState();
        });

        veroeffentlichungsJahrField.addValueChangeListener(e -> {
            veroeffentlichungsJahrField.setInvalid(
                    e.getValue().isEmpty() ||
                            !e.getValue().matches("\\d{4}")
            );
            updateSaveButtonState();
        });

        beschreibungTextArea.addValueChangeListener(e -> {
            beschreibungTextArea.setInvalid(
                    e.getValue().isEmpty()
            );
            updateSaveButtonState();
        });

        speichernButton.addClickListener(e -> {

            if (isBookExisting(buchService, titelField.getValue().trim())) {
                Notification.show("Das Buch existiert bereits.");
            }else {
                BuchDTO buchDTO = new BuchDTO();
                buchDTO.setTitel(titelField.getValue());
                buchDTO.setAutor(autorField.getValue());
                buchDTO.setVeroeffentlichungsJahr(veroeffentlichungsJahrField.getValue());
                buchDTO.setBeschreibung(beschreibungTextArea.getValue());

                buchService.addBookToInventary(buchDTO);
                Notification.show("Das Buch wurde erfolgreich hinzugefügt.");
                clearForm();
            }
        });

        add(
                ueberschrift,
                titelField,
                autorField,
                veroeffentlichungsJahrField,
                beschreibungTextArea,
                speichernButton
        );
    }

    private boolean isBookExisting(BuchService buchService, String titel) {
        return buchService.findBuchByTitel(titel) != null;
    }

    private void centerElements() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(true);
        setSpacing(true);
    }


    private void setInputFieldStyles() {
        veroeffentlichungsJahrField.setPattern("\\d{4}");
        veroeffentlichungsJahrField.setInvalid(true);
        veroeffentlichungsJahrField.setMaxLength(4);
        veroeffentlichungsJahrField.setPlaceholder("z.B. 2020");
    }

    private void updateSaveButtonState() {
        boolean allValid = !titelField.isInvalid()
                && !autorField.isInvalid()
                && !veroeffentlichungsJahrField.isInvalid()
                && !beschreibungTextArea.isInvalid();

        speichernButton.setEnabled(allValid);
    }

    private void clearForm() {
        titelField.clear();
        titelField.setInvalid(false);

        autorField.clear();
        autorField.setInvalid(false);

        veroeffentlichungsJahrField.clear();

        beschreibungTextArea.clear();
        beschreibungTextArea.setInvalid(false);
    }
}
