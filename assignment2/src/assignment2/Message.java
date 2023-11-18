package assignment2;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String content;
	
	Message(String content){
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}

}
