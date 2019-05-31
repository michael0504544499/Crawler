
package SearchEngines;

import java.util.ArrayList;

import Enums.SearchEngineName;
import Enums.URLRequest;
import Interfaces.EngineObserver;
import Interfaces.EngineSubject;
import Interfaces.Observer;
import Interfaces.Subject;

/**
 *
 * @author Michael
 */
public class GoogleEngine extends SearshEngine implements EngineSubject{

	ArrayList<EngineObserver>observers =new  ArrayList<EngineObserver>();
	boolean finishToSearch=false;
	
    public GoogleEngine(String wordTosearch,String suffix){
    	super();
        this.extensions=suffix;
        this.wordTosearch=wordTosearch;
    }
    //AIzaSyDGLIYNkOD4POlTlX19WHYWOvh0zuuFV9E
    //AIzaSyClzxz_Twdw66G3VG75U2xbSEkep3toZ_A
   
    public void sendRequest(){  
    
          String query=URLRequest.GOOGLE_REQUEST.getURLRequest()+extensions+"+"+wordTosearch;
      
        super.getURLs(true,query, extensions);
        
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
  			o.update(finishToSearch,SearchEngineName.GOOGLE);
  		}
  		
  	}
}
