package com.example.application;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route
@PageTitle("Flow view")
@RolesAllowed("USER")
public class FlowView extends VerticalLayout{
    public FlowView() {
        add(new Text("This is a Flow view"));
    }
}
