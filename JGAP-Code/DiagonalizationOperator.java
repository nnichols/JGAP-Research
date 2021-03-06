/* Nick Nichols - DiagonalizationOperator.java
 *
 * Genetic Operator to support Diagonalization
 * Based upon the CrossoverOperator in JGAP, but extended to k-arity
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class DiagonalizationOperator extends BaseGeneticOperator implements Comparable {
		
	// The number of parents to recombine
	private int arity;
	
	// Percentage of population that will undergo Diagonalization
	private double diagonalizationPercent;
	
	// Indicate whether or not these genetic sequences represent permutations
	private boolean isPermutation;

	
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
	
	
	// Set up the Diagonalization operator with some defaults
	// We automatically choose to use traditional crossover
	//
	// 70% Crossover rate consistent with Eiben
	protected void initialize(){
		arity = 2;
		diagonalizationPercent = 0.70;
		isPermutation = false;
	}
	
	// Setter Methods
	public void setPermutation( boolean isPerm ){
		this.isPermutation = isPerm;
	}
	
	public void setPercent( double newPercent ){
		this.diagonalizationPercent = newPercent;
	}
	
	public void setParents( int newArity ){
		this.arity = newArity;
	}
	
	
	// Perform Diagonalization as many times as needed
	public void operate( final Population population, final List candidateChromosomes) {

		// Calculate the number of times to run the operator
		int populationSize = (int) (((double) population.size() * diagonalizationPercent) / (double) arity / 2.0 );
		
		// Get the current random generator
		RandomGenerator generator = getConfiguration().getRandomGenerator();

		// Perform each diagonalization
		for( int i = 0; i < populationSize; i++ ){
			
			// Grab our parent chromosomes and
			// store each of the Gene[]s in an ArrayList
			ArrayList<IChromosome> parents = new ArrayList<IChromosome>();
			
			for( int j = 0; j < this.arity; j++ ){
				
				// Get a random Chromosome
				IChromosome randomParent = population.getChromosome( generator.nextInt( population.size() ) );
				
				// Get the Genetic Sequence and clone to store it
				parents.add( (IChromosome) randomParent.clone() );
				
			}
			
			doDiagonalization( parents, candidateChromosomes, population.getConfiguration() );
		}
	
	}
	
	// Actually perform the diagonalization
	// The swapping loci will be located every ChromosomeLength / arity alleles
	protected void doDiagonalization( ArrayList<IChromosome> parentList, final List candidateChromosomes, Configuration config ){
	
		
		ArrayList<Gene[]> parents = new ArrayList<Gene[]>();		
		
		// Add the values to the parent array
		for( int l = 0; l < parentList.size(); l++ ) {
			IChromosome sampleParent = parentList.get(l);
			
			Gene[] sample = sampleParent.getGenes();
			
			parents.add( sample );
			
		}
		
		// Get a sample Gene[] to determine where to split
		Gene[] sample = parents.get(0);
		
		// Distance between splitting loci
		int step = sample.length / arity;
		
		// Iterate through each position in the Gene[]
		for( int k = 0; k < sample.length; k++ ){
		
			// Offset to rotate forward with
			int offset = k / step;
			
			// Only operate if the offset will cause a change in the
			// genetic sequences
			if( offset > 0 ){
			
				Object[] values = new Object[ parents.size() ];
			
				// Iterate through the parents to get the genes
				for( int l = 0; l < parents.size(); l++ ){
			
					Gene[] currentValues = parents.get( l );
					values[l] = currentValues[ k ].getAllele();
				
				}
			
				// Push the values to the new parents
				for( int l = 0; l < parents.size(); l++ ){
			
					try{
						
						IChromosome modParent = parentList.get( l );
						Gene[] currentParent = modParent.getGenes();
						
						// Get the value to be moved
						Object value =  values[(l + offset) % parents.size()];
						
						currentParent[ k ].setAllele( value );
						modParent.setGenes( currentParent );
						
					} catch( Exception e ){
						System.err.println( e.getMessage() );
					}
				}
			}
		}
		
		
		// Iterate through the new Chromosomes and add them to the population
		Iterator<IChromosome> parentIterator = parentList.iterator();
		while( parentIterator.hasNext() ){
		
			IChromosome newAddition = parentIterator.next();
			
			// If they are permutations, fix them first
			if( isPermutation){
				newAddition = SupportFunctions.repairPermutation( newAddition, null );
			}
			
			candidateChromosomes.add( newAddition );
			
		}

	}
	
	
	// Compares the instance of this operator to another
	// Return 0 in all cases since only one instance of this operator should exist at a time
	public int compareTo( final Object target ){
			
		return 0;
		
	}
	
}
