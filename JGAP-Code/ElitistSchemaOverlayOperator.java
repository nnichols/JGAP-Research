/* Nick Nichols - ElitistSchemaOverlayOperator.java
 *
 * Genetic Operator to support ESOs
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class ElitistSchemaOverlayOperator extends BaseGeneticOperator implements Comparable {

	
	// Arguementless Constructor that grabs configuration from the Genotype
	public ElitistSchemaOverlayOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
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

}
