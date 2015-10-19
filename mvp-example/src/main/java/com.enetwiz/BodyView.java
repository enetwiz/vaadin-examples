package com.enetwiz;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.vaadin.spring.events.Event;

/**
 * Interface for communication between presenter and view
 */
public interface BodyView extends View {

    String NAME = "body";

    /**
     * Gets the main view component
     */
    Component getMainComponent();

    /**
     * Show the answer
     *
     * @param answer the answer content
     */
    void showAnswer(String answer);

    interface BodyViewListener {

        /**
         * Event was fired after click on the button
         *
         * @param event fired event object
         */
        void onButtonClick(Event<Button.ClickEvent> event);
    }
}
