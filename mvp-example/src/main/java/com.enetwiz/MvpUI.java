package com.enetwiz;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.spring.annotation.EnableVaadin;
import org.vaadin.spring.events.annotation.EnableEventBus;
import com.vaadin.spring.server.SpringVaadinServlet;

@SpringUI(path = "/")
@Theme("valo")
@Title("Welcome on MVP UI example")
public class MvpUI extends UI {

    @Autowired
    BodyPresenter presenter;

    @Configuration
    @EnableVaadin
    @EnableEventBus
    public static class MyConfiguration {
    }

    @WebServlet(urlPatterns = {"/*"}, asyncSupported = true)
    public static class MyUIServlet extends SpringVaadinServlet {
    }

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(presenter.getView().getMainComponent());
    }

}