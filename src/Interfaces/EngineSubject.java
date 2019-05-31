package Interfaces;

public interface EngineSubject {

	public void registerObserver(EngineObserver observer);
	public void ungisterObserver(EngineObserver observer);
	public void notifyObserver();
}
