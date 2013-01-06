/* Nick Nichols - TSPFunction.java
 *
 * Fitness Function for the Travelling Salesman Problem
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 *
 * This will utilize the adjacency matrix class to store the problem instance
 *
 */

import org.jgap.*;
import org.jgap.impl.*;
import java.io.*;
import java.util.Scanner;

public class TSPFunction extends FitnessFunction {

	private int cities;
	private AdjacencyMatrix instance;
	private static final double PENALTYWEIGHT = 2.0;
	
	// Constructor that builds an instance of given a file path
	public TSPFunction( String filepath ){
		
		// Catch any exceptions for file i/o
		try{

			// Build a scanner to read the given file
			Scanner inFile = new Scanner( new BufferedReader( new FileReader( filepath )));

			// Read the number of cities
			this.cities = inFile.nextInt();

			// Read the number of edges
			int edgeCount = inFile.nextInt();

			// Build the AdjacencyMatrix
			instance = new AdjacencyMatrix( cities );

			// Add each edge in the file
			for( int i = 0; i < edgeCount; i++ ){
				int city1 = inFile.nextInt();
				int city2 = inFile.nextInt();
				double weight = inFile.nextDouble();

				instance.addEdge( city1, city2, weight );
			}

		// Exception handling and exiting
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			System.exit(0);
		}
	}
	
	
	
	// Setter methods
	public void setCities( int newCities ){
		this.cities = newCities;
	}
	
	
	
	// Getter methods
	public int getCities(){
		return cities;
	}

	public AdjacencyMatrix getInstance(){
		return instance;
	}	

	
	
	// Performs the evaluation of a given individual
	// Genetic sequence must consist of dimension IntegerGenes
	public double evaluate( IChromosome target ){
		
		// Build an array of the path
		int[] path = new int[ this.cities + 1 ];
		
		// Include 0 as first position to account for circularity of permuttions
		path[0] = 0;

		// Get the genetic sequence
		Gene[] tour = target.getGenes();

		// Put the tour into the path array
		// we use the value + 1 to account for the zero we added earlier
		for( int i = 0; i < cities; i++ ){
			IntegerGene currentGene = (IntegerGene) tour[i];
			path[i + 1] = currentGene.intValue() + 1;
		}


		double fitness = 0.0;
		
		// Score our cycle by summing paths
		// Valid: 0 <= Path weight <= 1
		// Invlaid: PENALTYWEIGHT
		for( int i = 0; i < cities + 1; i++ ){
			double edgeWeight = instance.getWeight( path[i], path[(i + 1) % (cities + 1)] );

			// Check if edge exists
			if( edgeWeight > 0.0 ){
				fitness = fitness + edgeWeight;
			} else {
				fitness = fitness + PENALTYWEIGHT;
			}
		}

		return fitness;	

	}
}
