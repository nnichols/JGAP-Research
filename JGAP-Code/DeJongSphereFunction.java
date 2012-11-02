/* Nick Nichols - DeJongSphereFunction.java
 *
 * Fitness Function for the first DeJong Function
 * Since this is a minimization task, we must use DeltaFitnessEvaluator
 * This implementation also uses a discretization of the real domain 
 *      over which this problem is defined. Must decode from a Boolean genome
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class DeJongSphereFunction extends FitnessFunction {

	private int dimension, domainSize;
	
	// Constructor that builds an instance of k dimensions
	// each with n bits used to define the domain
	public DeJongSphereFunction( int newDimension, int newDomain ){
		this.dimension = newDimension;
		this.domainSize = newDomain;
	}
	
	// Default constructor
	public DeJongSphereFunction(){
		this.dimension = 1;
		this.domainSize = 10;
	}
	
	
	
	// Setter methods
	public void setDimension( int newDimension ){
		this.dimension = newDimension;
	}
	
	public void setDomainSize( int newDomain ){
		this.domainSize = newDomain;
	}
	
	
	
	// Getter methods
	public int getDimension(){
		return dimension;
	}
	
	public int getDomainSize(){
		return domainSize;
	}
	
	
	// Performs the evaluation of a given individual
	// Genetic sequence must consist of dimension * domianSize BooleanGenes
	public double evaluate( IChromosome target ){
		return 0.0;
	}
}
