package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.layout.MainLayout;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.application.service.BuchService;

@Route(value = "books", layout = MainLayout.class)
@PageTitle("Buchbestand")
public class BuchbestandView extends VerticalLayout {
    public BuchbestandView(BuchService buchService) {

        centerElements();

        H2 ueberschrift = new H2("Buchbestand");

        Grid<BuchDTO> grid = new Grid<>(BuchDTO.class, false);
        grid.setItems(buchService.findAllBooksNotDeleted());

        grid.addColumn(BuchDTO::getTitel)
                .setHeader("Titel")
                .setSortable(true);

        grid.addColumn(BuchDTO::getAutor)
                .setHeader("Autor");

        grid.addColumn(BuchDTO::getVeroeffentlichungsJahr)
                .setHeader("Veröffentlichungsjahr")
                .setSortable(true);

        grid.addColumn(BuchDTO::getBeschreibung)
                .setHeader("Beschreibung")
                .setAutoWidth(true)
                .setFlexGrow(1);

        grid.addComponentColumn(buch -> {
            Button editButton = new Button(new Icon(VaadinIcon.EDIT));
            editButton.getElement().setProperty("title", "Buch bearbeiten");
            editButton.addClickListener(e ->
                    editButton.getUI().ifPresent(ui ->
                            ui.navigate("books/edit/" + buch.getId()))
            );
            return editButton;
        }).setHeader("Bearbeiten").setAutoWidth(true);

        grid.addComponentColumn(buch -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.getElement().setProperty("title", "Buch entfernen");
            deleteButton.addClickListener(e ->
                    deleteButton.getUI().ifPresent(ui ->
                            ui.navigate("books/delete/" + buch.getId()))
            );
            return deleteButton;
        }).setHeader("Entfernen").setAutoWidth(true);

        add(ueberschrift, grid);
    }

    private void centerElements() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setPadding(true);
        setSpacing(true);
    }
}
