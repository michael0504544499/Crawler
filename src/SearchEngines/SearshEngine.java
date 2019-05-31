
package SearchEngines;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DP_project.DB;
import Enums.NetworkProtocol;
import Enums.SearchEngineName;
import Enums.UserAgent;
import Interfaces.EngineObserver;
import Interfaces.Observer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JProgressBar;

import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michael
 */
public  class SearshEngine  {
	
	protected String wordTosearch;
	protected String extensions;
	private DB db = DB.createDB();
	private static boolean changeUser=false;



	SearshEngine() {}


	static Semaphore lock = new Semaphore(1);
	
	//this method send request to any search engine to get URLs that we looking for   
	public void getURLs(String request, String suffix) {

		String url = request;
		Document doc=null;
		try {
			if(changeUser) {
				doc = Jsoup.connect(url).userAgent(UserAgent.OPERA_USER_AGENT.getAgent()).get();
				changeUser=false;
			}else {
				doc = Jsoup.connect(url).userAgent(UserAgent.OPERA_USER_AGENT.getAgent()).get();
				changeUser=true;
			}
			Elements links = doc.select("a[href$=." + suffix);

			lock.acquire();
			for (Element link : links) {
				String s = link.toString().substring(link.toString().indexOf("http"), link.toString().lastIndexOf(suffix) + suffix.length());
				db.getDB().put(s, new JProgressBar());

			}
			lock.release();
		} catch (Exception e) {
			lock.release();
		}  
	}

	public void getURLs(boolean google, String request, String suffix) {
		try {
			ignoreSSL();
			String url = request;
			URL obj = new URL(url);

			String pattern = "http[\\S]+\\."+suffix;
			Pattern r = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m;

			URLConnection con = obj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line;

			while ((line = in.readLine()) != null) {
				if(!line.contains("...")){
					m = r.matcher(line);
					if (m.find()) {
						db.getDB().put(line.substring(m.start(), m.end()), new JProgressBar());
					}
				}
			}

		} catch (Exception e) {

		}

	}

	public void ignoreSSL() throws Exception {

		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}

				}
		};

		SSLContext sc = SSLContext.getInstance(NetworkProtocol.SSL.getProtocol());
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}

	

	

	

	
}
