package com.github.bot.curiosone.core.knowledge;

<<<<<<< HEAD
=======
import com.github.bot.curiosone.core.knowledge.interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.interfaces.Vertex;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.BabelSynsetSource;
import it.uniroma1.lcl.babelnet.iterators.BabelSynsetIterator;
import it.uniroma1.lcl.jlt.util.Language;

>>>>>>> master
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

<<<<<<< HEAD
import com.github.bot.curiosone.core.knowledge.Interfaces.Edge;
import com.github.bot.curiosone.core.knowledge.Interfaces.Graph;
import com.github.bot.curiosone.core.knowledge.Interfaces.Vertex;

import it.uniroma1.lcl.babelnet.BabelNet;
import it.uniroma1.lcl.babelnet.BabelSense;
import it.uniroma1.lcl.babelnet.BabelSynset;
import it.uniroma1.lcl.babelnet.BabelSynsetIDRelation;
import it.uniroma1.lcl.babelnet.BabelSynsetSource;
import it.uniroma1.lcl.babelnet.iterators.BabelSynsetIterator;
import it.uniroma1.lcl.jlt.util.Language;
/**
 * 
 * @author Christian
 *
 */
public class SemanticNetwork implements Graph
{
private Map<Vertex,Set<Edge>> grafo;
	/**
	 *  creazione della mappa di un Grafo
	 */
	static private SemanticNetwork curiosoneSemanticNetwork;
	
	static public SemanticNetwork getInstance() throws IOException
	{
		if (curiosoneSemanticNetwork == null) curiosoneSemanticNetwork = new SemanticNetwork();
		return curiosoneSemanticNetwork;
	}
	private SemanticNetwork() throws IOException
	{
		this.grafo = new HashMap<>();
		/**------------------- FIRST VERSION-------------------//
		BabelSynsetIterator bni = BabelNet.getInstance().getSynsetIterator();
		while(bni.hasNext())
		{
			BabelSynset synset = bni.next();
			BabelSense source = synset.getMainSense((Language.EN));
			if(source.getSource()==BabelSenseSource.WN)
			{
				List<BabelSynsetIDRelation> edges = synset.getEdges();
				for ( BabelSynsetIDRelation relazione : edges)
				{
					if (relazione.getLanguage()==Language.EN)
					{
						BabelSense target = BabelNet.getInstance()
													.getSynset(relazione.getBabelSynsetIDTarget())
													.getMainSense(Language.EN);
						if (target.getSource()== BabelSenseSource.WN)
						{
							SemanticRelationType relation = null;
							BabelPointer pointer = relazione.getPointer();
							Set<String> relazioni = Arrays.stream(SemanticRelationType.values())
														  .map(SemanticRelationType::toString)
														  .collect(Collectors.toSet());
							if (relazioni.contains(pointer.toString().toUpperCase()))
							{
								Vertex v1 = new Concept(source.getLemma());
								Vertex v2 = new Concept(target.getLemma());
								this.addEdge(v1, v2, SemanticRelationType.valueOf(pointer.toString().toUpperCase()));
							}
						}
					}
				}
			}
		}*/
		//------------------------------SECOND VERSION----------------------
		int k= 0;
		BabelNet bn = BabelNet.getInstance();
		Set<String> ourSemanticRType = Arrays.stream(SemanticRelationType.values())
				  .map(SemanticRelationType::toString)
				  .collect(Collectors.toSet());
		BabelSynsetIterator bsi = bn.getSynsetIterator();
		while(bsi.hasNext())
		{
			BabelSynset bs = bsi.next();
			if(bs.getSynsetSource() == BabelSynsetSource.WN)
			{
				BabelSense source = bs.getMainSense(Language.EN);
				List<BabelSynsetIDRelation> edges = bs.getEdges();
				
				for (BabelSynsetIDRelation edge : edges)
				{
					BabelSynset bst = bn.getSynset(edge.getBabelSynsetIDTarget());
					if(edge.getLanguage() == Language.EN && bst.getSynsetSource() == BabelSynsetSource.WN)
					{
						BabelSense target = bst.getMainSense(Language.EN);
						String pointer = edge.getPointer().toString().toUpperCase(); 
						
						if (ourSemanticRType.contains(pointer.toString().toUpperCase()))
						{
							Vertex v1 = new Concept(source.getLemma());
							Vertex v2 = new Concept(target.getLemma());
							this.addEdge(v1, v2, SemanticRelationType.valueOf(pointer.toString().toUpperCase()));
							System.out.println(k);
							k+=1;
						}
					}
				}
			}
		}
		
	}
	public Map<Vertex,Set<Edge>> getGrafo()
	{
		return grafo; // Getter
	}

	@Override
	public void add(Edge e) {addEdge(e.getSource(), e.getTarget(), e.getType());}  // richiamo di un metodo con spacchettamento
	/**																				// del parametro
	 * aggiungo un vertice senza archi se non e' gia presente
	 */
	@Override
	public void add(Vertex v) {
		if (!grafo.containsKey(v))
		{
			Set<Edge> lista = new HashSet<>();
			grafo.put(v,lista);
		}
	}
	/**
	 * aggiungo un'arco e se i vertici dell'arco non sono presenti li crea
	 */
	@Override
	public void addEdge(Vertex v1, Vertex v2, SemanticRelationType type) {
		if (!grafo.containsKey(v1))
			add(v1);
		if (!grafo.containsKey(v2))
			add(v2);
		SemanticRelation arco = new SemanticRelation(v1,v2,type);
		grafo.get(v1).remove(arco);
		grafo.get(v2).remove(arco);
		grafo.get(v1).add(arco);
		grafo.get(v2).add(arco);
		
	}

	@Override
	public boolean containsEdge(Edge e) {
		for(Set<Edge> archi_temp : grafo.values())
			{if (archi_temp.contains(e))return true;}
		return false;
		}
	@Override
	public boolean containsVertex(Vertex v) {
		return grafo.keySet().contains(v);}
	/**
	 * ritorno tutti gli edge del grafo
	 */
	@Override
	public Set<Edge> edgeSet() {
		Set<Edge> archi = new HashSet<>();
		for(Set<Edge> archi_temp : grafo.values())
			archi.addAll(archi_temp);
		return archi;
	}
	/**
	 * ritorno tutti i vertici
	 */
	@Override
	public Set<Vertex> vertexSet() {
		return grafo.keySet();
	}
	
	/**
	 * prendo tutti gli archi che hanno come sorgente il vertice v controllando il getSource di ogni arco
	 */
	@Override
	public Set<Edge> outgoingEdges(Vertex v) {
		if (containsVertex(v))
		{
		Set<Edge> outgoingEdges = new HashSet<>();
		for (Edge arco : grafo.get(v))
		{
			if (arco.getSource().equals(v))
				outgoingEdges.add(arco);
		}
		return outgoingEdges;
		}
		return new HashSet<Edge>();
	}
	
	@Override
	public void addVertices(Collection<? extends Vertex> vertexSet) {
		for ( Vertex nodo : vertexSet)
			add(nodo);
	/**
	* riuso dei metodi soprastanti
	*/
	}
	@Override
	public void addEdges(Collection<? extends Edge> edgeSet) {
		for ( Edge arco : edgeSet)
			add(arco);
		
	}
	@Override
	public String toString()
	{
		return grafo.toString(); // metodo toString utilizzato per varie prove di debug
	}
	
	@Override
	public boolean equals(Object o)
	{
		return this.hashCode() == ((SemanticNetwork)o).hashCode();
	}
	/**
	 * Hashcode basato su gli hascode di tutti i grafi e tutti i vertici
	 */
	@Override
	public int hashCode()
	{
		int result = 42;
        result = 31 * result + edgeSet().hashCode();
        result = 31 * result + vertexSet().hashCode();
        return result;
		
	}
	@Override
	public Vertex search(SemanticRelationType tipo, String token) {
		for(Edge e : this.edgeSet())
		{
			if (e.getSource().getId().equals(token))
				return e.getTarget(); break;
		}
		return null;
	}
=======
/**
 * This class is used to implement a Semantic Network.
 * @author Christian Sordi
 */
public class SemanticNetwork implements Graph {

  private Map<Vertex,Set<Edge>> grafo;

  private static SemanticNetwork curiosoneSemanticNetwork;

  /**
   * Returns the Semantic Network.
   * @throws IOException if there is any problem.
   */
  public static SemanticNetwork getInstance() throws IOException {
    if (curiosoneSemanticNetwork == null) {
      curiosoneSemanticNetwork = new SemanticNetwork();
    }
    return curiosoneSemanticNetwork;
  }

  private SemanticNetwork() throws IOException {
    this.grafo = new HashMap<>();
    /*
    BabelSynsetIterator bni = BabelNet.getInstance().getSynsetIterator();
    while(bni.hasNext())
    {
      BabelSynset synset = bni.next();
      BabelSense source = synset.getMainSense((Language.EN));
      if(source.getSource()==BabelSenseSource.WN)
      {
        List<BabelSynsetIDRelation> edges = synset.getEdges();
        for ( BabelSynsetIDRelation relazione : edges)
        {
          if (relazione.getLanguage()==Language.EN)
          {
            BabelSense target = BabelNet.getInstance()
              .getSynset(relazione.getBabelSynsetIDTarget())
              .getMainSense(Language.EN);
            if (target.getSource()== BabelSenseSource.WN)
            {
              SemanticRelationType relation = null;
              BabelPointer pointer = relazione.getPointer();
              Set<String> relazioni = Arrays.stream(SemanticRelationType
                .values())
                .map(SemanticRelationType::toString)
                .collect(Collectors.toSet());
              if (relazioni.contains(pointer.toString().toUpperCase()))
              {
                Vertex v1 = new Concept(source.getLemma());
                Vertex v2 = new Concept(target.getLemma());
                this.addEdge(v1, v2, SemanticRelationType.valueOf(pointer
                  .toString().toUpperCase()));
              }
            }
          }
        }
      }
    }*/
    //------------------------------SECOND VERSION----------------------
    int k = 0;
    BabelNet bn = BabelNet.getInstance();
    Set<String> ourSemanticRType = Arrays.stream(SemanticRelationType.values())
          .map(SemanticRelationType::toString)
          .collect(Collectors.toSet());
    BabelSynsetIterator bsi = bn.getSynsetIterator();
    while (bsi.hasNext()) {
      BabelSynset bs = bsi.next();
      if (bs.getSynsetSource() == BabelSynsetSource.WN) {
        BabelSense source = bs.getMainSense(Language.EN);
        List<BabelSynsetIDRelation> edges = bs.getEdges();
        for (BabelSynsetIDRelation edge : edges) {
          BabelSynset bst = bn.getSynset(edge.getBabelSynsetIDTarget());
          if (edge.getLanguage() == Language.EN && bst.getSynsetSource() == BabelSynsetSource.WN) {
            BabelSense target = bst.getMainSense(Language.EN);
            String pointer = edge.getPointer().toString().toUpperCase();
            if (ourSemanticRType.contains(pointer.toString().toUpperCase())) {
              Vertex v1 = new Concept(source.getLemma());
              Vertex v2 = new Concept(target.getLemma());
              this.addEdge(v1, v2, SemanticRelationType.valueOf(pointer.toString().toUpperCase()));
              System.out.println(k);
              k++;
            }
          }
        }
      }
    }
  }

  public Map<Vertex,Set<Edge>> getGrafo() {
    return grafo;
  }

  @Override
  public void add(Edge e) {
    addEdge(e.getSource(), e.getTarget(), e.getType());
  }

  @Override
  public void add(Vertex v) {
    if (!grafo.containsKey(v)) {
      Set<Edge> lista = new HashSet<>();
      grafo.put(v,lista);
    }
  }

  @Override
  public void addEdge(Vertex v1, Vertex v2, SemanticRelationType type) {
    if (!grafo.containsKey(v1)) {
      add(v1);
    }
    if (!grafo.containsKey(v2)) {
      add(v2);
    }
    SemanticRelation arco = new SemanticRelation(v1,v2,type);
    grafo.get(v1).remove(arco);
    grafo.get(v2).remove(arco);
    grafo.get(v1).add(arco);
    grafo.get(v2).add(arco);
  }

  @Override
  public boolean containsEdge(Edge e) {
    for (Set<Edge> archiTemp : grafo.values()) {
      if (archiTemp.contains(e)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean containsVertex(Vertex v) {
    return grafo.keySet().contains(v);
  }

  /**
   * Ritorno tutti gli edge del grafo.
   */
  @Override
  public Set<Edge> edgeSet() {
    Set<Edge> archi = new HashSet<>();
    for (Set<Edge> archiTemp : grafo.values()) {
      archi.addAll(archiTemp);
    }
    return archi;
  }

  /**
   * Ritorno tutti i vertici.
   */
  @Override
  public Set<Vertex> vertexSet() {
    return grafo.keySet();
  }

  /**
   * Prendo tutti gli archi che hanno come sorgente il vertice v
   * controllando il getSource di ogni arco.
   */
  @Override
  public Set<Edge> outgoingEdges(Vertex v) {
    if (containsVertex(v)) {
      Set<Edge> outgoingEdges = new HashSet<>();
      for (Edge arco : grafo.get(v)) {
        if (arco.getSource().equals(v)) {
          outgoingEdges.add(arco);
        }
      }
      return outgoingEdges;
    }
    return new HashSet<Edge>();
  }

  @Override
  public void addVertices(Collection<? extends Vertex> vertexSet) {
    for (Vertex nodo : vertexSet) {
      add(nodo);
    }
  }

  /**
  * Riuso dei metodi soprastanti.
  */
  @Override
  public void addEdges(Collection<? extends Edge> edgeSet) {
    for (Edge arco : edgeSet) {
      add(arco);
    }

  }

  @Override
  public String toString() {
    return grafo.toString(); // metodo toString utilizzato per prove di debug
  }

  @Override
  public boolean equals(Object o) {
    return this.hashCode() == ((SemanticNetwork)o).hashCode();
  }

  /**
   * Hashcode basato su gli hascode di tutti i grafi e tutti i vertici.
   */
  @Override
  public int hashCode() {
    int result = 42;
    result = 31 * result + edgeSet().hashCode();
    result = 31 * result + vertexSet().hashCode();
    return result;
  }

  @Override
  public Vertex search(SemanticRelationType tipo, String token) {
    for (Edge e : this.edgeSet()) {
      if (e.getSource().getId().equals(token)) {
        return e.getTarget();
      }
    }
    return null;
  }
>>>>>>> master
}
