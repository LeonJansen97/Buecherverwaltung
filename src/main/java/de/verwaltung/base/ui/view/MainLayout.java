package de.verwaltung.base.ui.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;
import de.verwaltung.buch.ui.view.BuchFormView;
import de.verwaltung.buch.ui.view.BuchbestandView;
import de.verwaltung.buch.ui.view.DashboardView;

import static com.vaadin.flow.theme.lumo.LumoUtility.*;

@Layout
public final class MainLayout extends AppLayout {

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToDrawer(
                createHeader(),
                new Scroller(createSideNav())
        );
    }

    private Div createHeader() {
        Icon buchIcon = VaadinIcon.BOOK.create();
        buchIcon.addClassNames(TextColor.PRIMARY, IconSize.LARGE);

        Span appName = new Span("Bibliotheksmodul: Bücherverwaltung");
        appName.addClassNames(FontWeight.SEMIBOLD, FontSize.LARGE);

        Div header = new Div(buchIcon, appName);
        header.addClassNames(Display.FLEX, Padding.MEDIUM, Gap.MEDIUM, AlignItems.CENTER);
        return header;
    }

    private SideNav createSideNav() {
        SideNav nav = new SideNav();
        nav.addClassNames(Margin.Horizontal.MEDIUM);
        MenuConfiguration.getMenuEntries().forEach(
                entry -> nav.addItem(createSideNavItem(entry))
        );
        nav.addItem(new SideNavItem("Übersicht", DashboardView.class, VaadinIcon.HOME.create()));
        nav.addItem(new SideNavItem("Buch hinzufügen", BuchFormView.class, VaadinIcon.PLUS_CIRCLE.create()));
        nav.addItem(new SideNavItem("Buchübersicht", BuchbestandView.class, VaadinIcon.LIST.create()));
        return nav;
    }

    private SideNavItem createSideNavItem(MenuEntry menuEntry) {
        if (menuEntry.icon() != null) {
            return new SideNavItem(menuEntry.title(), menuEntry.path(), new Icon(menuEntry.icon()));
        } else {
            return new SideNavItem(menuEntry.title(), menuEntry.path());
        }
    }
}