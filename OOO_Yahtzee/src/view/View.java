package view;

import java.awt.event.KeyListener;

import controller.Controller;

public interface View {
    void setController(Controller controller);

    void addIsListener(KeyListener isListener);

    void start();
}