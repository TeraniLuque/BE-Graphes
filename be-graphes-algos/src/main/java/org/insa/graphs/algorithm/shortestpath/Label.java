package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label{
	
	private Node sommet_courant;
	
	private boolean marque;
	
	private float cout;
	
	private Arc pere;
	


	public float getCost() {
		return this.cout;
	}
	
	public Label(Node sommet, boolean marque, float cout, Arc pere) {
		this.sommet_courant = sommet;
		this.marque = marque;
		this.cout = cout;
		this.pere = pere;
	}

}