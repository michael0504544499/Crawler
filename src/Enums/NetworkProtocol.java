package Enums;

public enum NetworkProtocol {

	SSL("SSL"),
	TLSV("TLSV"),
	TLSV_1("TLSV 1.1"),
	TLSV_2("TLSV 1.2");

	
	

	    private String protocol;

	    NetworkProtocol(String protocol) {
	        this.protocol = protocol;
	    }

	    public String getProtocol() {
	        return protocol.toString();
	    }
	
}
