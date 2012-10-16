/* Nick Nichols - DiagonalizationOperator.java
 *
 * Genetic Operator to support Diagonalization
 * Based upon the CrossoverOperator in JGAP, but extended to k-arity
 *
 * 10/16 - Initialized
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class DiagonalizationOperator extends BaseGeneticOperator implements Comparable{
	
	// The number of parents to recombine
	private int arity;
	
	// Percentage of population that will undergo Diagonalization
	private double diagonalizationPercent;

	
	// Arguementless Constructor that grabs configuration from the Genotype
	public DiagonalizationOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
		initialize();
	}
	
	
	// Constructor that takes and sets a Configuration
	public DiagonalizationOperator( final Configuration configuration ) throws InvalidConfigurationException {
		super(configuration);
		initialize();
	}
	
	
	// Constructor that takes and sets a Configuration along with an arity
	public DiagonalizationOperator( final Configuration configuration, int newArity ) throws InvalidConfigurationException {
		super(configuration);
		this.arity = newArity;
	}
	
	
	// Constructor that takes and sets the Configuration, arity, and diagonalizationPercent
	public DiagonalizationOperator( final Configuration configuration, int newArity, double newPercent ) throws InvalidConfigurationException {
		super(configuration);
		this.arity = newArity;
		this.diagonalizationPercent = newPercent;
	}
	
	
	// Set up the Diagonalization operator with some defaults
	// We automatically choose to use traditional crossover
	protected void initialize(){
		arity = 2;
		diagonalizationPercent = 0.7;
	}
	
	
	// Perform Diagonalization
	public void operate( final Population population, final List candidateChromosomes) {
		int i = 1;
	}
	
	
	// Compares the instance of this operator to another
	// Return 0 if settings are identical
	public int compareTo( final Object target ){
	
		
		return 0;
	}
	
}
