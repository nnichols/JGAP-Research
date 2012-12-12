/* Nick Nichols - CamelFunction.java
 *
 * Fitness Function for the first Camel Function
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 * This implementation also uses a discretization of the real domain 
 *      over which this problem is defined. Must decode from a Boolean genome
 * This function may only have two dimensions
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class CamelFunction extends FitnessFunction {

	private int dimension, dimensionLength;
	private boolean isGreyCoded;
	private static double xLowerBound = -3.0;
	private static double xUpperBound = 3.0;
	private static double yLowerBound = -2.0;
	private static double yUpperBound = 2.0;
	
	// Constructor that builds an instance of k dimensions
	// each with n bits used to define the dimension
	public CamelFunction( int newDimension, int newDomain, boolean coding ){
		this.dimension = 2;
		this.dimensionLength = newDomain;
		this.isGreyCoded = coding;
	}
	
	// Default constructor
	public CamelFunction(){
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
				
		// Qwunky layout since x,y have different domains
		// but we still need to get their values
		int[] xVal = { translatedValues[0] };
		int[] yVal = { translatedValues[1] };
		
		double[] xDouble = SupportFunctions.intToDoubleDomain( xVal, xLowerBound, xUpperBound, this.dimensionLength );
		double[] yDouble = SupportFunctions.intToDoubleDomain( yVal, yLowerBound, yUpperBound, this.dimensionLength );
		
		double xPart = (4.0 * Math.pow( xDouble[0], 2 )) + (2.1 * Math.pow( xDouble[0], 4 )) + (Math.pow( xDouble[0], 6 ) / 3.0);
		double yPart = xDouble[0] * yDouble[0] + (4.0 * Math.pow( yDouble[0], 2 )) + (4.0 * Math.pow( yDouble[0], 4 ));
		
		double value = xPart + yPart;
		
		return value;
	}
}
