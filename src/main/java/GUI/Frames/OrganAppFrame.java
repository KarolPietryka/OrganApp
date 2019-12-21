package GUI.Frames;

import GUI.AppFramesObserverPattern.IAppFramesObservable;
import GUI.AppFramesObserverPattern.IAppFramesObserver;

import javax.swing.*;
import java.util.ArrayList;

public abstract class OrganAppFrame extends JFrame implements IAppFramesObservable {

    protected ArrayList<IAppFramesObserver> observers = new ArrayList<>();

    public OrganAppFrame() {
        super("Organ");
    }


    public void startFrame()
    {
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addObserver(IAppFramesObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IAppFramesObserver observer){
        observers.remove(observer);
    }
}
