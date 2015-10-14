package com.enetwiz;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String helloSpring() {
        return "Hello Spring!";
    }
    public String helloVaadin() {
        return "Hello Vaadin ;)";
    }
}
