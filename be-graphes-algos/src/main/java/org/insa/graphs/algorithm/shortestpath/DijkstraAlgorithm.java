package org.insa.graphs.algorithm.shortestpath;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.AbstractInputData;



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
        
        
        notifyOriginProcessed(origin);
        
        Tas_Label.insert(labels[origin.getId()]);
        
        while(!Tas_Label.isEmpty()) {
            Label label_courrant = Tas_Label.deleteMin();
            label_courrant.Marquer();
            Node noeud_courant = label_courrant.getSommet_courant();
            
            int nb_successeur = 0;
            
            for (Arc arc : noeud_courant.getSuccessors()) {
                
            		nb_successeur ++;
	            	Node noeud_destination = arc.getDestination();
	                Label label_destination = labels[noeud_destination.getId()];
	                
	                double cout = data.getCost(arc) + label_courrant.getCost();
	                notifyNodeReached(noeud_courant);
	                
	                if(!data.isAllowed(arc)) {
	                	continue;
	                }
	                
	                if (!label_destination.isMarque() && label_destination.getCost()>cout ) 
	                {
	                    if (label_destination.getPere()==null) { 
	                        label_destination.setCout(cout);
	                        label_destination.setPere(arc);
	                        Tas_Label.insert(label_destination);
	                        notifyNodeReached(noeud_destination);
	                        System.out.println("cout:" +label_destination.getCout());
	                    }    
	                    else {
	                        Tas_Label.remove(label_destination);
	                        label_destination.setCout(cout);
	                        label_destination.setPere(arc);
	                        Tas_Label.insert(label_destination);
	                        notifyNodeReached(noeud_destination);
	                        System.out.println("cout:" +label_destination.getCout());
	                    }
	                }
                }

    		System.out.println("nombre d'arc:" +nb_successeur); 
            }
       
       
        
        notifyDestinationReached(data.getDestination());
        Node noeud_desti = data.getDestination();
        Label label_desti = labels[noeud_desti.getId()];
            
        if (label_desti.getPere()==null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {
            // The destination has been found, notify the observers.
            notifyDestinationReached(noeud_desti);
            
            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc Arc = label_desti.getPere();
            
            while (Arc != null) {
                arcs.add(Arc);
                label_desti = labels[Arc.getOrigin().getId()];
                Arc = label_desti.getPere();
            }

            // Reverse the path...
            Collections.reverse(arcs);
            
            //Check if the path is valid
            if(arcs.isValid()) {
            	//TODO
            }

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
    
 
        
        return solution;
    }
        
        
        

}

   
        
