package com.enetwiz;

import com.enetwiz.BodyView.BodyViewListener;
import com.vaadin.ui.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

@VaadinPresenter(viewName = BodyView.NAME)
public class BodyPresenter extends Presenter<BodyView> implements BodyViewListener {

    @Autowired
    private MyService myService;

    @EventBusListenerMethod
    @Override
    public void onButtonClick(Event<Button.ClickEvent> event) {
        getView().showAnswer(myService.helloVaadin());
    }
}