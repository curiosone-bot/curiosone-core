package com.github.bot.curiosone.core.knowledge.Interfaces;

/**
 * Interfaccia per l'esportazione di grafi
 * @author navigli
 *
 */
@FunctionalInterface
public interface GraphExporter{
	/**
	 * Restituisce la stringa che rappresenta il grafo in input
	 * @param g il grafo in input
	 * @return la stringa che rappresenta il grafo
	 */
	String export(Graph g);
}
