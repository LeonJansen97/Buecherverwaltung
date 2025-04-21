package de.verwaltung.buch.ui.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "")
@PageTitle("Übersicht")
public class DashboardView extends VerticalLayout {
    public DashboardView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.START);
        setPadding(true);
        setSpacing(true);

        H1 ueberschrift = new H1("Bibliotheksmodul: Bücherverwaltung");
        Paragraph beschreibung = new Paragraph("Verwalten Sie den Buchbestand, Ausleihen und Mahnungen.");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(
                createNavCard("Buchbestand", "Alle Medien ansehen", VaadinIcon.LIST, BuchbestandView.class),
                createNavCard("Neues Medium", "Buch einpflegen", VaadinIcon.PLUS_CIRCLE, BuchFormView.class)
        );

        actions.setSpacing(true);
        actions.setJustifyContentMode(JustifyContentMode.CENTER);


        add(ueberschrift, beschreibung, actions);
    }

    private Component createNavCard(String title, String subtitle, VaadinIcon icon, Class<? extends Component> target) {
        Icon ic = icon.create();
        ic.setSize("40px");

        Span titleSpan = new Span(title);
        titleSpan.getStyle().set("font-weight", "bold");

        Span subtitleSpan = new Span(subtitle);
        subtitleSpan.getStyle().set("color", "gray");

        VerticalLayout content = new VerticalLayout(ic, titleSpan, subtitleSpan);
        content.setAlignItems(Alignment.CENTER);
        content.setPadding(false);
        content.setSpacing(false);

        Div card = new Div(content);

        card.getStyle()
                .set("padding", "1rem")
                .set("border-radius", "8px")
                .set("box-shadow", "var(--lumo-box-shadow-xs)")
                .set("cursor", "pointer")
                .set("width", "200px");

        card.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(target)));

        return card;
    }
}
