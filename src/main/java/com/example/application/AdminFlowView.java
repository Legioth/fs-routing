package com.example.application;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route
@PageTitle("Admin flow view")
@RolesAllowed("ADMIN")
public class AdminFlowView extends VerticalLayout{
    public AdminFlowView() {
        add(new Text("This is a Admin flow view"));
    }
}
