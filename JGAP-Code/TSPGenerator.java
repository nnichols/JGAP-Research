/* Nick Nichols - TSPGenerator.java
 *
 * Class to generate Travelling Salesman Problem instances
 * 
 * All edges are of lenth x, 0.0 < x < 1.0
 * We know a Hamiltonian cycle exists because each node 
 *      will be of at least degree n / 2 (Dirac's Theorem)
 * Files are loacted at Test-Cases/tsp%nc%me%i.txt where %n = cities, %m = edges, %i = instance
 *      e.g Test-Cases/tsp4c5e1.txt
 *
 */

import java.io.*;
import java.util.*;

public class TSPGenerator{
	public static void main( String[] args ){

		String cityStr = args[0];
		int cityCount = Integer.parseInt( cityStr );
		String instStr = args[1];
		int instanceCount = Integer.parseInt( instStr );

		// Build every instance
		for( int currentInstance = 0; currentInstance < instanceCount; currentInstance++ ){

			Random generator = new Random( System.currentTimeMillis() );
		
			// Build an AdjacencyMatrix to keep track of our progress
			AdjacencyMatrix instance = new AdjacencyMatrix( cityCount );
		
			// Keep track of all of the Strings that we'll need to print
			// (A kludge, but it makes life easier with the file structure)
			ArrayList<String> printQueue = new ArrayList<String>();
		
			// Add the number of cities
			printQueue.add( cityStr );
				
			// Build an ArrayList to keep track of the weights for bounds checking
			ArrayList<Double> weights = new ArrayList<Double>();
			
			// Iterate through every city		
			for( int i = 0; i < cityCount; i++ ){
			
				// Make sure we only stop once we hit degree(i) > n / 2
				int edgesLeft = (cityCount / 2) - instance.getDegree( i ) + 1;
						
				for( int j = 0; j < edgesLeft; j++ ){
				
					int to = generator.nextInt( cityCount );
					double newWeight = generator.nextDouble();
				
					// Make sure we don't replace an existing edge
					if( instance.hasEdge( i, to ) || i == to ){
						j--;
					
						// Copy the information to each of our containers
					} else {
					
						weights.add( newWeight );
						instance.addEdge( i, to, newWeight );
					
						StringBuilder printLine = new StringBuilder();
						printLine.append( i );
						printLine.append( " " );
						printLine.append( to );
						printLine.append( " " );
						printLine.append( newWeight );
					
						printQueue.add( printLine.toString() );
					}
				}			
			}
		
			// Finally, add the city count
			printQueue.add( 1, Integer.toString( instance.getEdges() ) );
		
			// Sort our list of weights (ascending)
			Collections.sort( weights );
		
			// Find the sum of the n longest paths (Tour upper bound)
			double upperBound = 0.0;
			for( int i = 0; i < cityCount; i++ ){
				upperBound = upperBound + weights.get( weights.size() - i - 1 );
			}
			printQueue.add( Double.toString( upperBound ) );
		
			// Find the sum of the n shortest paths (Tour lower bound)
			double lowerBound = 0.0;
			for( int i = 0; i < cityCount; i++ ){
				lowerBound = lowerBound + weights.get( i );
			}
			printQueue.add( Double.toString( lowerBound ) );
		
		
			// Print out everything to a file
			try{
			
				BufferedWriter outStream = new BufferedWriter( new FileWriter( "/share/data/nnichols/jgap_instances/tsp" + cityCount + "c" + instance.getEdges() + "e" + currentInstance + ".txt" ));
			
				// Iterate through printQueue
				for( String line : printQueue ){
					outStream.write( line, 0, line.length() );
					outStream.newLine();
				}
				
				outStream.close();
			
			} catch( Exception e ) {
				System.err.println( "A fatal error occured: " + e.getMessage() );
			}
		}
	}
}
