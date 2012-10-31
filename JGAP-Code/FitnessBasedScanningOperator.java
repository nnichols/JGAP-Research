/* Nick Nichols - FitnessBasedScanningOperator.java
 *
 * Genetic Operator to support FB-Scanning
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class FitnessBasedScanningOperator extends BaseGeneticOperator implements Comparable {

	
	// Arguementless Constructor that grabs configuration from the Genotype
	public FitnessBasedScanningOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
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
