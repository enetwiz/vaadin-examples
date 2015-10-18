package com.enetwiz;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.ui.Button;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;
import org.vaadin.spring.navigator.Presenter;

@VaadinPresenter(viewName = BodyView.NAME)
public class BodyPresenter extends Presenter<BodyView> {

    @Autowired
    private MyService myService;

    @Override
    protected void init() {
        super.init();
        getView().setButtonCaption(myService.helloSpring());
    }

    @EventBusListenerMethod
    public void onButtonClick(Event<Button.ClickEvent> event) {
        getView().showAnswer(myService.helloVaadin());
    }

}