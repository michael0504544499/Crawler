/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP_project;

import java.util.Map;
import javax.swing.JProgressBar;
import SearchEngines.AskEngine;
import SearchEngines.BingEngine;
import SearchEngines.GoogleEngine;
import SearchEngines.SearshEngine;
import SearchEngines.YahooEngine;

/**
 *
 * @author Michael
 * singleton class  
 */
public class Crawler {

	public static Crawler crawler;
	private static boolean created=false;
	private final static int numberOfEngines=4;
	private final int bingEngine=0,askEngine=1,yahooEngine=2,googleEngine=3;
	private SearshEngine engines[];
	private static boolean _google=true, _bing=false, _yahoo=false, _ask=false;

	public static void main(String s[]) {
		//getURLs("math", "xls");
	}

	private Crawler(boolean google, boolean bing, boolean yahoo, boolean ask) {
		_google = google;
		_bing = bing;
		_yahoo = yahoo;
		_ask = ask;
		engines=new SearshEngine[numberOfEngines];
	}

	public static Crawler createCrawler(boolean google, boolean bing, boolean yahoo, boolean ask) {

		if(!created) {
			crawler=new Crawler(google, bing, yahoo, ask);
			created=true;
		}

		return crawler;
	}




	//the method open connection and send request for search url by words 

	public void getURLs(String words, String suffix) {

		String wordsForRequest = words.replaceAll(" ", "%20");

		if (_bing) {
			engines[bingEngine]=new BingEngine(wordsForRequest, suffix);
			try {
				((BingEngine)engines[bingEngine]).sendRequest();
			}catch(Exception e) {}
			finally {
				((BingEngine)engines[bingEngine]).notifyObserver();
			}
		}
		if (_ask) {
			engines[askEngine]= new AskEngine(wordsForRequest, suffix);
			try {
				((AskEngine)engines[askEngine]).sendRequest();
			}catch(Exception e) {}
			finally {
				((AskEngine)engines[askEngine]).notifyObserver();
			}
		}
		if (_yahoo) {
			engines[yahooEngine] = new YahooEngine(wordsForRequest, suffix);
			try {
				((YahooEngine)engines[yahooEngine]).sendRequest();
			}catch(Exception e) {}
			finally{
				((YahooEngine)engines[yahooEngine]).notifyObserver();
			}
		}
		if(_google){
			engines[googleEngine]=new GoogleEngine(wordsForRequest, suffix);
			try {
				((GoogleEngine)engines[googleEngine]).sendRequest();
			}catch(Exception e) {}
			finally {
				((GoogleEngine)engines[googleEngine]).notifyObserver();
			}
		}
		for (Map.Entry<String, JProgressBar> e : DB.createDB().getDB().entrySet()) {
			System.out.println(e.getKey());
		}

	}

}
