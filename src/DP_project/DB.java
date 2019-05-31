package DP_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JProgressBar;

import Enums.SearchEngineName;
import Interfaces.EngineObserver;
import Interfaces.Observer;
import Interfaces.Subject;
import Interfaces.Visitable;
import Interfaces.Visitor;

/*
 *  I assumed that the class is flooded from methods
 */
public class DB implements Visitable,Subject,EngineObserver{

	Map<SearchEngineName,Boolean>allEngineStatus;
	private HashMap<String,JProgressBar> urls ;
	private ArrayList<Observer> observers;
	private boolean BDIsFull=false;

	private static DB db;
	private static boolean created=false;


	private DB() {
		urls = new HashMap<String, JProgressBar>(); 
		observers=new ArrayList<Observer>();

		allEngineStatus=new HashMap<SearchEngineName,Boolean>();
		allEngineStatus.put(SearchEngineName.GOOGLE, false);
		allEngineStatus.put(SearchEngineName.BING, false);
		allEngineStatus.put(SearchEngineName.YAHOO, false);
		allEngineStatus.put(SearchEngineName.ASK, false);
	}

	public static DB createDB() {

		if(!created) {
			db=new DB();
			created=true;
		}

		return db;
	}


	public void removeAllDB() {
		urls = new HashMap<String, JProgressBar>(); 
	}

	public HashMap<String, JProgressBar> getDB(){
		return urls;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);	
	}

	@Override
	public void registerObserver(Observer newObserver) {
		observers.add(newObserver);

	}

	@Override
	public void ungisterObserver(Observer deleteObserver) {
		observers.remove(deleteObserver);

	}

	@Override
	public void notifyObserver() {

		for(Observer o:observers) {
			o.update(BDIsFull);
		}

	}

	public void setDBIsFull(boolean dbIsFull ) {

		BDIsFull=dbIsFull;

	}

	@Override
	public void update(boolean finishToSearch, SearchEngineName engineName) {

		allEngineStatus.put(engineName, finishToSearch);

	}


	public void waitForAllSearchEngine() {

		Thread waitFor=new Thread() {

			public void run() {

				while(true) {
					try{
						Thread.sleep(50);
					}catch(Exception e) {}

						boolean google=allEngineStatus.containsKey(SearchEngineName.GOOGLE);
						boolean ask=allEngineStatus.containsKey(SearchEngineName.ASK);
						boolean yahoo=allEngineStatus.containsKey(SearchEngineName.YAHOO);
						boolean bing=allEngineStatus.containsKey(SearchEngineName.BING);
						if(google&&ask&&yahoo&&bing) {
							BDIsFull=true;
							notifyObserver();
							this.stop();
							break;
					}
				
				}

			}


		};waitFor.start();


	}



}
