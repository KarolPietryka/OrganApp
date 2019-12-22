package GUI.AppFramesObserverPattern;

public interface IAppFramesObservable {
    public void addObserver(IAppFramesObserver observer);
    public void removeObserver(IAppFramesObserver observer);
    public void updateObservers();
}
