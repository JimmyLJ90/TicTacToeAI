package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldMouseListener implements MouseListener {
    private int i;
    public FieldMouseListener(int i)
    {
        this.i = i;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        mouseEvent.getComponent();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
