/* Nick Nichols - NKFunction.java
 *
 * Fitness Function for NK-Landscapes
 * We treat this as a minimization task, so we can use DeltaFitnessEvaluator
 *
 */

import org.jgap.*;
import org.jgap.impl.*;
import java.io.*;
import java.util.*;

public class NKFunction extends FitnessFunction implements Reportable{

	private int n,k;
	private HashMap<Integer, Double> values; 

	
	// Constructor that uses a random seed from a file, and values for n and k
	public NKFunction( String filepath ){
	
		// Catch any exceptions for file i/o
		try{

			// Build a scanner to read the given file
			Scanner inFile = new Scanner( new BufferedReader( new FileReader( filepath )));
			
			// Read the length of each string
			this.n = inFile.nextInt();

			// Read the interpolation value
			this.k = inFile.nextInt();
			
			// Get the random seed and set up the generator
			long seed = inFile.nextLong();
			Random generator = new Random( seed );
		
			// Build the HashMap
			values = new HashMap<Integer, Double>();
		
			// Add each evaluation (bitstring, result) to the Map
			for( int i = 0; i < (int) Math.pow( 2.0, (double) k); i++ ){
					values.put( new Integer(i), new Double( generator.nextDouble() ) );
			}
			
		} catch( Exception e ){
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			System.exit(0);
		}
	}
	
	// Getter methods
	public int getN(){
		return n;
	}

	public int getK(){
		return k;
	}

	
	
	// Performs the evaluation of a given individual
	// Genetic sequence must consist of n BooleanGenes
	public double evaluate( IChromosome target ){
		
		double score = (double) n;

		// Get the genetic sequence
		Gene[] sequence = target.getGenes();

		// Iterate through the genetic sequence
		for( int i = 0; i < this.n; i++ ){
	
			// Build each substring
			StringBuilder currentPortion = new StringBuilder( k );

			for( int j = 0; j < this.k; j++ ){
	
				// See if the gene is true or false
				BooleanGene currentGene = (BooleanGene) sequence[i];

				if( currentGene.booleanValue() ){
					currentPortion.append(1);				
				} else {
					currentPortion.append(0);
				}
			}

			// Convert to a string
			String strValue = currentPortion.toString();

			// Convert to Int (base 2)
			int value = Integer.parseInt( strValue, 2 );

			// Perform the look up
			double kValue = values.get( value );

			score = score - kValue;
		}
		
		return score;
	}
	
	// Report on a Chromosomes success
	public void report( IChromosome target ){
		System.out.println( evaluate( target ) );
	}
}
