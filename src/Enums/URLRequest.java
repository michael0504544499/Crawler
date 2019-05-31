package Enums;

public enum URLRequest {
	
	
	
	GOOGLE_REQUEST("https://www.googleapis.com/customsearch/v1?key=AIzaSyDGLIYNkOD4POlTlX19WHYWOvh0zuuFV9E&cx=015817165331662398615:tzhl8b0obok&q=filetype%3A"),
	YAHOO_REQUEST("https://search.yahoo.com/search?p=filetype%3A"),
	ASK_REQUEST("https://www.ask.com/web?q=filetype%3A"),
	BING_REQUEST("https://www.bing.com/search?q=filetype%3A");

	    private String urlRequest;

	    URLRequest(String urlRequest) {
	        this.urlRequest = urlRequest;
	    }

	    public String getURLRequest() {
	        return urlRequest.toString();
	    }

}
