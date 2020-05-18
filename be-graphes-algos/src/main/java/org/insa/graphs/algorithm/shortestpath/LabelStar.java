package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{
    
    private double cout_estime;
    private Node desti;
    
    public LabelStar(Node a,boolean b,double c,Arc d,Node desti ) {
        super(a,b,c,d);
        this.cout_estime=a.getPoint().distanceTo(desti.getPoint());    
    }
    
    public double getTotalCost() {
    	return this.cout_estime + this.getCost();
    }
    
}
