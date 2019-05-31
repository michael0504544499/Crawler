package Enums;

public enum SearchEngineName {

	
	GOOGLE("Google"),
	YAHOO("Yahoo"),
	BING("Bing"),
	ASK("Ask");

	    private String engineName;

	    SearchEngineName(String engineName) {
	        this.engineName = engineName;
	    }

	    public String getProtocol() {
	        return engineName.toString();
	    }
	
}
