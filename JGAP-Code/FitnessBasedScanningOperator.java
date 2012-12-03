/* Nick Nichols - FitnessBasedScanningOperator.java
 *
 * Genetic Operator to support FB-Scanning
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class FitnessBasedScanningOperator extends BaseGeneticOperator implements Comparable {

	
	// The number of parents to recombine
	private int arity;
	
	// Percentage of population that will be operated upon
	private double operationPercent;
	
	// Indicate whether or not these genetic sequences represent permutations
	private boolean isPermutation;

	
	// Arguementless Constructor that grabs configuration from the Genotype
	public FitnessBasedScanningOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
		initialize();
	}
	
	// Constructor that takes and sets a Configuration
	public FitnessBasedScanningOperator( final Configuration configuration ) throws InvalidConfigurationException {
		super(configuration);
		initialize();
	}
	
	// Set up the defaults
	private void initialize(){
		arity = 2;
		operationPercent = 0.7;
		isPermutation = false;
	}
	
	// Setter Methods
	public void setPermutation( boolean isPerm ){
		this.isPermutation = isPerm;
	}
	
	public void setPercent( double newPercent ){
		this.operationPercent = newPercent;
	}
	
	public void setParents( int newArity ){
		this.arity = newArity;
	}
	
	
	// Perform FB-Scanning as much as needed
	public void operate( final Population population, final List candidateChromosomes ) {
		
		// Dummy operation for compilation
		int i = 0;
		
	}
	
	// Compares the instance of this operator to another
	// Return 0 in all cases since only one instance of this operator should exist at a time
	public int compareTo( final Object target ){
		
		return 0;
		
	}

}
