package Enums;

public enum UserAgent {
	
	

	OPERA_USER_AGENT("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52"),
	MOZILLA_USER_AGENT_5("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"),
	MOZILLA_USER_AGENT_4("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

	    private String function;

	    UserAgent(String function) {
	        this.function = function;
	    }

	    public String getAgent() {
	        return function.toString();
	    }

}
