package com.enetwiz;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.events.EventBus;

interface BodyView extends View {

    String NAME = "body";

    /**
     * Gets the view main component
     */
    Component getMainComponent();

    /**
     * Sets caption for button
     *
     * @param caption the new caption
     */
    void setButtonCaption(String caption);

    /**
     * Show the answer after click on the button
     *
     * @param answer the answer content
     */
    void showAnswer(String answer);
}

@UIScope
@SpringView(name = BodyView.NAME)
public class BodyViewImpl extends VerticalLayout implements BodyView {

    @Autowired
    private EventBus.SessionEventBus eventBus;

    private Button button;

    @PostConstruct
    private void init() {
        setMargin(true);
        button = new Button("Welcome in the MVP world!");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                eventBus.publish(this, event);
            }
        });
        addComponent(button);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    @Override
    public Component getMainComponent() {
        return this;
    }

    @Override
    public void setButtonCaption(String caption) {
        button.setCaption(caption);
    }

    @Override
    public void showAnswer(String answer) {
        addComponent(new Label(answer));
    }

}