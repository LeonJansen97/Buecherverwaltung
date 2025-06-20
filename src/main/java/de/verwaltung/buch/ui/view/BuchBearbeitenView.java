package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.MainLayout;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.application.service.BuchService;

import java.util.Objects;

@Route(value="books/edit/", layout = MainLayout.class)
@PageTitle("Buch bearbeiten")
public class BuchBearbeitenView extends VerticalLayout implements HasUrlParameter<Long> {
    private BuchDTO buchDTO;
    private final TextField titelField = new TextField("Titel");
    private final TextField autorField = new TextField("Autor");
    private final TextField veroeffentlichungsJahrField = new TextField("Veröffentlichungsjahr");
    private final TextArea beschreibungTextArea = new TextArea("Beschreibung");
    private final Button bearbeitenButton = new Button("Speichern");

    private final BuchService buchService;

    private Long buchId;

    public BuchBearbeitenView(BuchService buchService) {
        this.buchService = buchService;

        centerElements();

        H2 ueberschrift = new H2("Buch bearbeiten");


        titelField.addValueChangeListener(e -> {
            titelField.setInvalid(e.getValue().isEmpty()
            );
            updateBearbeitenButtonState();
        });

        autorField.addValueChangeListener(e -> {
            autorField.setInvalid(
                    e.getValue().isEmpty() ||
                            e.getValue().matches(".*\\d.*")
            );
            updateBearbeitenButtonState();
        });

        veroeffentlichungsJahrField.addValueChangeListener(e -> {
            veroeffentlichungsJahrField.setInvalid(
                    e.getValue().isEmpty() ||
                            !e.getValue().matches("\\d{4}")
            );
            updateBearbeitenButtonState();
        });

        beschreibungTextArea.addValueChangeListener(e -> {
            beschreibungTextArea.setInvalid(
                    e.getValue().isEmpty()
            );
            updateBearbeitenButtonState();
        });

        bearbeitenButton.addClickListener(e -> {
            BuchDTO editedBuchDTO = new BuchDTO();
            editedBuchDTO.setId(buchId);
            editedBuchDTO.setTitel(titelField.getValue());
            editedBuchDTO.setAutor(autorField.getValue());
            editedBuchDTO.setVeroeffentlichungsJahr(veroeffentlichungsJahrField.getValue());
            editedBuchDTO.setBeschreibung(beschreibungTextArea.getValue());

            if (!isValueChanged(buchDTO, editedBuchDTO)) {
                Notification.show("Das Buch wurde noch nicht bearbeitet.");
            } else {
                buchService.addBookToInventary(editedBuchDTO);

                getUI().ifPresent(ui -> ui.navigate("books"));
                Notification.show("Das Buch wurde erfolgreich bearbeitet.");
            }
        });

        add(
                ueberschrift,
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

        buchDTO = buchService.findBookById(buchId);

        if (buchDTO != null) {
            titelField.setValue(buchDTO.getTitel());
            autorField.setValue(buchDTO.getAutor());
            veroeffentlichungsJahrField.setValue(buchDTO.getVeroeffentlichungsJahr());
            beschreibungTextArea.setValue(buchDTO.getBeschreibung());
        } else {
            Notification.show("Das Buch mit der ID " + buchId + " wurde nicht gefunden.");
        }
    }

    private boolean isValueChanged(BuchDTO original, BuchDTO edited) {
        return !Objects.equals(original.getTitel(), edited.getTitel())
                || !Objects.equals(original.getAutor(), edited.getAutor())
                || !Objects.equals(original.getVeroeffentlichungsJahr(), edited.getVeroeffentlichungsJahr())
                || !Objects.equals(original.getBeschreibung(), edited.getBeschreibung());
    }

    private void centerElements() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(true);
        setSpacing(true);
    }

    private void updateBearbeitenButtonState() {
        boolean allValid = !titelField.isInvalid()
                && !autorField.isInvalid()
                && !veroeffentlichungsJahrField.isInvalid()
                && !beschreibungTextArea.isInvalid();

        bearbeitenButton.setEnabled(allValid);
    }
}