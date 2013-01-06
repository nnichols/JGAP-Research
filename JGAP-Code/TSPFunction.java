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
		
		return 0.0;	

	}
}
