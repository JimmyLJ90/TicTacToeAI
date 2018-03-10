package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class MyMenu extends JPopupMenu {

    private List<MenuChildListener> listeners = new ArrayList<>();

    public MyMenu() {
    }

    public MyMenu(String s) {
        super(s);
    }

    public void addMenuChildListeners(MenuChildListener listener)
    {
        this.listeners.add(listener);
    }

    public void removeMenuChildListener(MenuChildListener listener)
    {
        this.listeners.remove(listener);
    }

    private void notifyListeners(int i , JMenuItem item , JComponent parent)
    {
        for(MenuChildListener listener : listeners)
            listener.onMenuItemClick(i , item ,  parent);
    }


    private class MenuAction implements Action {
        MyMenu listener;


        public MenuAction(MyMenu listener)
        {
            this.listener = listener;
        }
        @Override
        public Object getValue(String s) {
            return null;
        }

        @Override
        public void putValue(String s, Object o) {

        }

        @Override
        public void setEnabled(boolean b) {

        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {

        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JMenuItem item = (JMenuItem) actionEvent.getSource();
            JPopupMenu parent = (JPopupMenu) item.getParent();
            int i = parent.getComponentIndex(item);
            listener.notifyListeners(i , item , parent);
        }
    }

    @Override
    public JMenuItem add(JMenuItem jMenuItem) {
       JMenuItem item = super.add(jMenuItem);
       item.addActionListener(new MenuAction(this));
       return item;
    }

    @Override
    public JMenuItem add(Action action) {
        JMenuItem item = super.add(action);
        item.addActionListener(new MenuAction(this));
        return item;
    }
}
