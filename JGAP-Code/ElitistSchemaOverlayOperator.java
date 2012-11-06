/* Nick Nichols - ElitistSchemaOverlayOperator.java
 *
 * Genetic Operator to support ESOs
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class ElitistSchemaOverlayOperator extends BaseGeneticOperator implements Comparable {

	// The k-value for the ESO 
	private int kValue;
	
	// An integer switch that determines the method by which k will be changed over time
	// A value of 0 implies that k is a constant
	// A value of 1 implies that k will be chosen randomly
	// A value of 2 implies that k will be chosen by a function of the generations remaining
	// All other values will imply the same as a value of "0" 
	private int kMethod;
	
	// The total number of generations that will be run
	private int totalGenerations;
	
	// The number of generations remaining
	private int generationsLeft;
	
	// The percent of the population to which this operator will be applied
	private double applicationPercent;
	
	// Indicate if this genetic sequence is a permutation or not
	private boolean isPermutation;
	
	// Arguementless Constructor that grabs configuration from the Genotype
	public ElitistSchemaOverlayOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
		initialize();
	}
	
	// Sets up the defaults
	// Static kValue of 5
	// k will remain a constant
	// 100% application
	// Not Permutive
	private void initialize(){
		this.kValue = 5;
		this.kMethod = 0;
		this.applicationPercent = 1.0;
		this.isPermutation = false;
		this.totalGenerations = -1;
		this.generationsLeft = -1;
	}

	// Build and apply the ESO
	public void operate( final Population population, final List candidateChromosomes ) {
		
		// Dummy operation for compilation
		int i = 0;
		
	}
	
	// Compares the instance of this operator to another
	// Return 0 in all cases since only one instance of this operator should exist at a time
	public int compareTo( final Object target ){
		
		return 0;
		
	}
	
	
	// Change the value of k
	// Should be performed before applying the ESO
	private void updateK( final Population population ){
		
		// Switch based upon our current method
		if( kMethod == 1 ){
		
			// The random k method
			// Get the current random generator
			RandomGenerator generator = getConfiguration().getRandomGenerator();
			
			// Choose a random value in the range of: 0 < k < populationSize 
			this.kValue = generator.nextInt( population.size() );
			
		} else if( kMethod == 2 ){
			
			// Determine the percent of the population to use
			// from the generations left
			double kPercentage = ((double) generationsLeft) / ((double) totalGenerations);
			
			// Multiply this by the population size to get our k
			kValue = (int) Math.floor( kPercentage * ((double) population.size()) );
			
			// update the number of generations remaining;
			generationsLeft--;
			
		} else {
			
			// The static k option is the last, so we essentially do nothing
			kValue = kValue;
			
		}
		
	}

}
