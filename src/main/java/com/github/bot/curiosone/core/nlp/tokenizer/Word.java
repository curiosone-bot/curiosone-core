package com.github.bot.curiosone.core.nlp.tokenizer;

import com.github.bot.curiosone.core.nlp.tokenizer.interfaces.*;

import java.util.List;

public class Word {
	/**
	 * Class used in information exchange with class dictionary
	 * @author riva
	 */
	private String value;
	private List<IMeaning> meanings; 
	private boolean known;
	
	public Word(String s, boolean k) {
		this.value = s;
		this.known = k;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the known
	 */
	public boolean isKnown() {
		return known;
	}
	/**
	 * @param known the known to set
	 */
	public void setKnown(boolean known) {
		this.known = known;
	}
	
}
