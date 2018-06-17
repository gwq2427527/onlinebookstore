package  util;

import java.util.LinkedList;

 
public class SearchParamBean {

	private LinkedList<String> parmnames; 
	private LinkedList<Object> parmvalues; 

	public LinkedList<String> getParmnames() {
		return parmnames;
	}

	public void setParmnames(LinkedList<String> parmnames) {
		this.parmnames = parmnames;
	}

	public LinkedList<Object> getParmvalues() {
		return parmvalues;
	}

	public void setParmvalues(LinkedList<Object> parmvalues) {
		this.parmvalues = parmvalues;
	}

}
