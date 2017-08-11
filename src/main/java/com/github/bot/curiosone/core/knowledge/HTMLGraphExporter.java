package com.github.bot.curiosone.core.knowledge;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.github.bot.curiosone.core.knowledge.Interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.Interfaces.GraphExporter;

/**
 * 
 * @author Christian
 *
 */
public class HTMLGraphExporter implements GraphExporter
{
	/**
	 * utilizzo uno stringBuffer per leggere il contenuto del file test.html che poi dovrò andare a modificare
	 * utilizzo un'array per spezzare il file in 3 parti e di queste 3 andrò a sostituire quella al centro con il JSON
	 * da me creato, infine salverò il nuovi file.
	 */
	@Override
	public String export(Graph g) {
		StringBuffer finale = new StringBuffer();
		    try {
		    	Scanner s = new Scanner(new File("resources/test.html")); //
		        while (s.hasNextLine())										  //   leggo il file
		        	finale.append(s.nextLine()+"\n");						  //
		        }
		        catch (Exception e){System.out.println(e);}
		    
		String[] pezzi = finale.toString().split("#END");    //splitto il file 
		finale.delete(0, finale.length());					//svuoto lo stringbuffer
		finale.append(pezzi[0] +"\n");						// aggiungo la prima parte dello split
		JSONGraphExporter text_exporter = new JSONGraphExporter();
		finale.append(text_exporter.export(g)+"\n");               // aggiungo il json da me creato
		finale.append(pezzi[2]); 									// aggiungo la parte finale
		try{
			 PrintWriter writer = new PrintWriter("resources/text3.html", "UTF-8");		//
			    writer.println(finale.toString());											//  salvo il file
			    writer.close();																//
			} 
		catch (IOException e){e.printStackTrace();}
		//return finale.toString();
		return "ciao";
	}
}
