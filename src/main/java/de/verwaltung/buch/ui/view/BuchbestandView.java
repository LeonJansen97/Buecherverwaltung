package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.MainLayout;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.service.BuchService;

@Route(value = "books", layout = MainLayout.class)
@PageTitle("Buchbestand")
public class BuchbestandView extends VerticalLayout {
    public BuchbestandView(BuchService buchService) {
        H2 ueberschriftSeite = new H2("Buchbestand");

        Div headingWrapper = new Div(ueberschriftSeite);
        headingWrapper.getStyle()
                .set("text-align", "center")
                .set("width", "100%");


        Grid<BuchDTO> grid = new Grid<>(BuchDTO.class, false);

        grid.setItems(buchService.findAllBooks());

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

        add(headingWrapper, grid);
    }
}
