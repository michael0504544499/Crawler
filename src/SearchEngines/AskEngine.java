package SearchEngines;

import java.util.ArrayList;

import Enums.SearchEngineName;
import Enums.URLRequest;
import Interfaces.EngineObserver;
import Interfaces.EngineSubject;



/**
 *
 * @author Michael
 */
public class AskEngine  extends SearshEngine implements EngineSubject{

	ArrayList<EngineObserver>observers =new  ArrayList<EngineObserver>();
	boolean finishToSearch=false;
	public AskEngine(String wordTosearch,String suffix){
		super();
		this.extensions=suffix;
		this.wordTosearch=wordTosearch;
	}

	public void sendRequest(){  

		String query=URLRequest.ASK_REQUEST.getURLRequest()+extensions+"+"+wordTosearch;

		super.getURLs(query, extensions);

	}

	@Override
	public void registerObserver(EngineObserver observer) {
		observers.add(observer);
		
	}

	@Override
	public void ungisterObserver(EngineObserver observer) {
		observers.remove(observer);
		
	}

	@Override
	public void notifyObserver() {
		for(EngineObserver o:observers) {
			o.update(finishToSearch,SearchEngineName.ASK);
		}
		
	}




}
