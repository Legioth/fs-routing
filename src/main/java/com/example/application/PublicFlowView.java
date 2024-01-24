package com.example.application;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@Route
@PageTitle("Public flow view")
@AnonymousAllowed
public class PublicFlowView extends VerticalLayout{
    public PublicFlowView() {
        add(new Text("This is a PUBLIC flow view"));
    }
}
