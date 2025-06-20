package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.verwaltung.base.ui.view.layout.MainLayout;
import de.verwaltung.buch.dtos.BuchDTO;
import de.verwaltung.buch.application.service.BuchService;

@Route(value = "books/archive", layout = MainLayout.class)
@PageTitle("Bucharchiv")
public class BuchArchivView extends VerticalLayout {
    public BuchArchivView(BuchService buchService) {
        H2 ueberschrift = new H2("Bucharchiv");

        centerElements();

        Grid<BuchDTO> grid = new Grid<>(BuchDTO.class, false);
        grid.setItems(buchService.findAllBooksDeleted());

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

        grid.addColumn(BuchDTO::isGeloescht)
                .setHeader("Gelöscht");

        grid.addComponentColumn(buchDto -> {
            Button undoDeleteButton = new Button(new Icon(VaadinIcon.REPLY));
            undoDeleteButton.getElement().setProperty("title", "Buch wiederherstellen");
            undoDeleteButton.addClickListener(e -> {
                buchService.undoDelete(buchDto.getId());
                getUI().ifPresent(ui -> ui.navigate("books"));
                Notification.show("Das Buch wurde erfolgreich wiederhergestellt.");
            });
            return undoDeleteButton;
        }).setHeader("Wiederherstellen").setAutoWidth(true);

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
