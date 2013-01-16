/* Nick Nichols - RastriginFunction.java
 *
 * Fitness Function for the Rastrigin Function
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 * This implementation also uses a discretization of the real domain 
 *      over which this problem is defined. Must decode from a Boolean genome
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class RastriginFunction extends FitnessFunction implements Reportable{

	private int dimension, dimensionLength;
	private boolean isGreyCoded;
	private static double bound = 5.12;
	
	// Constructor that builds an instance of k dimensions
	// each with n bits used to define the dimension
	public RastriginFunction( int newDimension, int newDomain, boolean coding ){
		this.dimension = newDimension;
		this.dimensionLength = newDomain;
		this.isGreyCoded = coding;
	}
	
	// Default constructor
	public RastriginFunction(){
		this.dimension = 1;
		this.dimensionLength = 10;
		this.isGreyCoded = true;
	}
	
	
	
	// Setter methods
	public void setDimension( int newDimension ){
		this.dimension = newDimension;
	}
	
	public void setDimensionLength( int newDimension ){
		this.dimensionLength = newDimension;
	}
	
	public void setCoding( boolean newCoding ){
		this.isGreyCoded = newCoding;
	}
	
	
	
	// Getter methods
	public int getDimension(){
		return dimension;
	}
	
	public int getDimensionLength(){
		return dimensionLength;
	}
	
	public boolean getCoding(){
		return isGreyCoded;
	}
	
	
	// Performs the evaluation of a given individual
	// Genetic sequence must consist of dimension * domianSize BooleanGenes
	public double evaluate( IChromosome target ){
		
		int[] translatedValues;
		
		// Determine our encoding and get the corrct array of ints depending on the situation
		if( isGreyCoded ){
			translatedValues = SupportFunctions.booleanChromosomeToGrayInt( target, this.dimension, this.dimensionLength );
		} else {
			translatedValues = SupportFunctions.booleanChromosomeToInt( target, this.dimension, this.dimensionLength );
		}
				
		// Use this information to build an array of doubles that represents
		// the actual point encoded by our genetic sequence
		double[] dimensionValues = SupportFunctions.intToDoubleDomain( translatedValues, -bound, bound, this.dimensionLength );
		
		double runningSum = 10.0 * dimension;
		
		// Compute the sum of each value squared
		for( int i = 0; i < dimensionValues.length; i++ ){
		
			runningSum = runningSum + Math.pow( dimensionValues[i], 2.0 ) - (10.0 * Math.cos( 2.0 * dimensionValues[i] * Math.PI ));
			
		}
		
		return runningSum;
	}
	
	// Report on a Chromosomes success
	public void report( IChromosome target ){
		System.out.println( evaluate( target ) );
	}
}
