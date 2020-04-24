package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import java.util.ArrayList;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.

import java.util.List;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        Node origin = data.getOrigin();
        Graph graph = data.getGraph();
        List <Node> nodes = graph.getNodes();  //liste des noeuds de notre graphe
        Label[] labels = new Label[graph.size()];
        
        //Initialisation
        for (Node node : nodes) {
        	if (node == origin){
        		labels[node.getId()]=new Label(node, true,0, null);
        	}
        	else {
        		labels[node.getId()]=new Label(node, false, Float.POSITIVE_INFINITY, null);
        	}
        }
        
        
        BinaryHeap <Label> Tas_Label = new BinaryHeap <Label>();
        
        
        
        
        Tas_Label.insert(labels[origin.getId()]);
        
        while(Tas_Label.isEmpty()==0) {
        	Label label_courrant = Tas_Label.deleteMin();
        	label_courrant.Marquer();
        	Node noeud_courrant = label_courrant.getSommet_courant();
        	for (Arc arc : noeud_courrant.getSuccessors()) {
        		Node successeur = arc.getDestination();
        		Label label_successeur = labels[successeur.getId()];
        		if (!(label_successeur.isMarque())) {
        			label_successeur.setCout(label_courrant.getCost() + data.getCost(arc));
        			label_successeur.setPere(arc);
        			Tas_Label.insert(label_successeur);
        		}
        	}
        	
        }
        
        
        
        
        return solution;
    }

}
