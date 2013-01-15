/* Nick Nichols - MichaelwiczFunction.java
 *
 * Fitness Function for the first Michaelwicz Function
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 * This implementation also uses a discretization of the real domain 
 *      over which this problem is defined. Must decode from a Boolean genome
 * This function may only have two dimensions
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class MichaelwiczFunction extends FitnessFunction {

	private int dimension, dimensionLength;
	private boolean isGreyCoded;
	private static double lowerBound = 0.0;
	private static double upperBound = 5.0;
	
	// Constructor that builds an instance of k dimensions
	// each with n bits used to define the dimension
	public MichaelwiczFunction( int newDomain, boolean coding ){
		this.dimension = 2;
		this.dimensionLength = newDomain;
		this.isGreyCoded = coding;
	}
	
	// Default constructor
	public MichaelwiczFunction(){
		this.dimension = 2;
		this.dimensionLength = 10;
		this.isGreyCoded = true;
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
		
		double x = dimensionValues[0];
		double y = dimensionValues[1];
		
		double value = (Math.sin( x ) * -1.0) * ( Math.pow( Math.sin( (x * x) / Math.PI ), 20.0));
		value = value + (Math.sin( y ) * -1.0) * ( Math.pow( Math.sin( (2.0 * y * y) / Math.PI ), 20.0));
		
		// Since the minimum is negative, and FitnessFunctions can't return negative values
		// We add an offset of 10.0 to every evaluation
		return value + 10.0;
	}
}
