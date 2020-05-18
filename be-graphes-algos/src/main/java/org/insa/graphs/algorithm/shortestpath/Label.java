package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{
	
	private Node sommet_courant;
	
	private boolean marque;
	
	private double cout;
	
	private Arc pere;
	


	public float getCost() {
		return (float) this.cout;
	}
	
	public Label(Node sommet, boolean marque, double cout, Arc pere) {
		this.sommet_courant = sommet;
		this.marque = marque;
		this.cout = cout;
		this.pere = pere;
	}
	
	public int compareTo(Label other) {
		return Double.compare(this.getTotalCost(), other.getTotalCost());
	}
	
	public Node getNode() {
		return sommet_courant;
	}

	public void setNode(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
	}

	public void marquer() {
		this.marque= true;
	}
	

	public double getCout() {
		return cout;
	}
	
	public double getTotalCost() {
		return cout;
	}

	public Arc getPere() {
		return pere;
	}

	public void setPere(Arc pere) {
		this.pere = pere;
	}

	public void setCout(double cout) {
		this.cout = cout;
	}

	public boolean isMarque() {
		return marque;
	}

	
	

}