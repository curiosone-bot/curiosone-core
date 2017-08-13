package com.github.bot.curiosone.core.knowledge;

import com.github.bot.curiosone.core.knowledge.Interfaces.Vertex;

/**
 * 
 * @author Christian
 *
 */
public class Concept implements Vertex{
	
	private final String ID;
	/**
	 * 
	 * @param id nome del vertice
	 */
	public Concept(String id){
	    this.ID = id;
	}

	@Override
	public String getId(){    // Getter dell'ID
	    return ID;
	}
	
	public String toString(){
	    return getId();
	}
	
	@Override
	public boolean equals(Object o){
	    return this.ID.equals(((Concept)o).getId());
	}
	/**
	 * Hashcode calcolato in base al nome dell'ID
	 */
	@Override
	public int hashCode(){
	    int conta = 0;
	    for (int x = 0; x < ID.length();x++)
		conta += ID.charAt(x)*31;
	    return conta;
	}
}
