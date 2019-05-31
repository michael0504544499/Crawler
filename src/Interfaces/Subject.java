package Interfaces;

public interface Subject {
public void registerObserver(Observer observer);
public void ungisterObserver(Observer observer);
public void notifyObserver();
	
}
