package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConfirmDialog extends Dialog {
    public ConfirmDialog(String message, Runnable confirm) {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);

        Text messageText = new Text(message);

        Button confirmButton = new Button("Ja", event -> {
            confirm.run();
            close();
        });

        Button cancelButton = new Button("Abbrechen", event -> close());

        HorizontalLayout buttons = new HorizontalLayout(confirmButton, cancelButton);
        buttons.setSpacing(true);

        add(new VerticalLayout(messageText, buttons));
    }
}
