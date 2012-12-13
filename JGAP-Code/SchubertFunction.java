/* Nick Nichols - SchubertFunction.java
 *
 * Fitness Function for the first Schubert Function
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 * This implementation also uses a discretization of the real domain 
 *      over which this problem is defined. Must decode from a Boolean genome
 * This function may only have two dimensions
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class SchubertFunction extends FitnessFunction {

	private int dimension, dimensionLength;
	private boolean isGreyCoded;
	private static double lowerBound = -10.0;
	private static double upperBound = 10.0;
	
	// Constructor that builds an instance of k dimensions
	// each with n bits used to define the dimension
	public SchubertFunction( int newDomain, boolean coding ){
		this.dimension = 2;
		this.dimensionLength = newDomain;
		this.isGreyCoded = coding;
	}
	
	// Default constructor
	public SchubertFunction(){
		this.dimension = 2;
		this.dimensionLength = 10;
	}
	
	
	
	// Setter methods
	public void setDimensionLength( int newDimension ){
		this.dimensionLength = newDimension;
	}
	
	public void setCoding( boolean newCoding ){
		this.isGreyCoded = newCoding;
	}
	
	
	
	// Getter methods
	public int getDimension(){
		return 2;
	}
	
	public int getDimensionLength(){
		return dimensionLength;
	}
	
	public boolean getCoding(){
		return isGreyCoded;
	}
	
	
	// Performs the evaluation of a given individual
	// Genetic sequence will always be split in half
	public double evaluate( IChromosome target ){
		
		int[] translatedValues;
		
		// Determine our encoding and get the corrct array of ints depending on the situation
		if( isGreyCoded ){
			translatedValues = SupportFunctions.booleanChromosomeToGrayInt( target, 2, target.size() / 2 );
		} else {
			translatedValues = SupportFunctions.booleanChromosomeToInt( target, 2, target.size() / 2 );
		}
				
		// Use this information to build an array of doubles that represents
		// the actual point encoded by our genetic sequence
		double[] dimensionValues = SupportFunctions.intToDoubleDomain( translatedValues, lowerBound, upperBound, this.dimensionLength );
		
		double value = computeForValue( dimensionValues[0] ) * computeForValue( dimensionValues[1] );
		
		return value;
	}
	
	
	// Compute the function value for one variable
	private static double computeForValue( double in ){
		
		double outValue = 0;
		
		
		// Iterate through the function
		for( int i = 0; i < 5; i++ ){
		
			double j = (double) i;
			
			outValue = outValue + (j * Math.cos( j + (j * in) + in ));
			
		}
		
		
		return outValue;
	}
}
