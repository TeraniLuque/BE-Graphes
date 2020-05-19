package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.*;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.*;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.*;
import org.junit.Test;



public class ShortestPathTest {

	
	@Test
	public void Test() throws FileNotFoundException {
	ShortestPathData data;
	final String graphHauteGaronne = "/home/luque/Bureau/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps";
	final String graphBretagne = "/home/luque/Bureau/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps";
	//final String graphInsa = "/home/luque/Bureau/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps";
	
	final GraphReader readerBR = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(graphBretagne))));
	final GraphReader readerHG = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(graphHauteGaronne))));
	//final GraphReader readerI = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(graphInsa))));
	
	Graph graphHG = readerHG.read();
	Graph graphBR = readerBR.read();
	//Graph graphI = readerI.read();
	
	
	final ArcInspector allRoads=ArcInspectorFactory.getAllFilters().get(0);
	final ArcInspector carAndLength=ArcInspectorFactory.getAllFilters().get(1);
	final ArcInspector carAndTime=ArcInspectorFactory.getAllFilters().get(2);
	final ArcInspector pedestrian=ArcInspectorFactory.getAllFilters().get(4);
	
	ShortestPathData data[] = new ShortestPathData[5];
    
	data[0]= new ShortestPathData(graphHG, graphHG.get(10991),graphHG.get(10991), allRoads ); //TRajet d'une longueur 0
	data[1]= new ShortestPathData(graphHG, graphHG.get(10991),graphHG.get(63104), carAndTime );
	data[2]= new ShortestPathData(graphHG, graphHG.get(10991),graphHG.get(63104), carAndLength );
	data[3]= new ShortestPathData(graphHG, graphHG.get(10991),graphHG.get(63104), pedestrian );
    
	data[4]= new ShortestPathData(graphBR, graphBR.get(417195),graphBR.get(116100), allRoads );
    
    final BellmanFordAlgorithm BF []= new BellmanFordAlgorithm[5];
    final DijkstraAlgorithm D[] = new DijkstraAlgorithm[5];
    final AStar A[] = new AStar[5];
    

	for(int i=0; i<4; i++) {
		BF[i]= new BellmanFordAlgorithm(data[i]);
		D[i] = new DijkstraAlgorithm(data[i]);
		A[i] = new AStar(data[i]);
	}
	
	
	
	
		
		
		
	}
	
}
