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
		
		// Calculate the number of times to run the operator
		int populationSize = (int) ((double) population.size() * operationPercent) ;
		
		// Get the current random generator
		RandomGenerator generator = getConfiguration().getRandomGenerator();
		
		// Perform each operation
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
			
			doFBScan( parents, candidateChromosomes );
		}
		
	}
	
	// Actually perform the genetic operation
	private void doFBScan(  ArrayList<IChromosome> parentList, final List candidateChromosomes ){
		
		// Exception handling
		try{
		
			double[] rouletteWheel = new double[ parentList.size() ];
			double totalFitness = 0.0;
			
			// Build a random generator
			Random rouletteBall = new Random( System.currentTimeMillis() );
		
			// Find the totalFitnessValue
			Iterator<IChromosome> fitnessIterator = parentList.iterator();
		
			while( fitnessIterator.hasNext() ){
			
				// Get the next parent
				IChromosome nextParent = fitnessIterator.next();
			
				// Update the total fitness
				totalFitness += nextParent.getFitnessValue();
			
			}
		
			// Build the roulette wheel
			Iterator<IChromosome> rouletteIterator = parentList.iterator();
			int index = 0;
		
			while( rouletteIterator.hasNext() ){
			
				// Get the next parent
				IChromosome nextParent = rouletteIterator.next();
			
				// Update the next cell with respect to the previous one
				if( index > 0 ){
					rouletteWheel[ index ] = rouletteWheel[ index - 1 ] + ( nextParent.getFitnessValue() / totalFitness );
				} else {
					rouletteWheel[ index ] = nextParent.getFitnessValue() / totalFitness;
				}
						  
				// Update the index
				index++;
			
			}	
		


			// Copy over a Gene[]
			Gene[] child = parentList.get(0).getGenes();
	
			// Iterate through the sequences to build the child
			for( int i = 0; i < child.length; i++ ){
		
				// Get a new random number
				double select = rouletteBall.nextDouble();
			
				// Find out what parent this corresponds to
				IChromosome nextParent = parentList.get(0);
				Iterator<IChromosome> selectIterator = parentList.iterator();
				int selectionIndex = 0;
			
				while( selectIterator.hasNext() ){
			
					// Grab the next parent
					nextParent = selectIterator.next();
				
					// Escape the loop if this is the parent we selected
					if( rouletteWheel[ selectionIndex ] >= select ){
						break;
					}
				
					selectionIndex++;
				
				}
			
				// Update the child's value at the current position
				Gene newVal = nextParent.getGene(i);

				child[i].setAllele( newVal.getAllele() );
			 
			}
		

			// Add the new individual to the population
			IChromosome newIndividual = parentList.get( 0 );
			newIndividual.setGenes( child );
			candidateChromosomes.add( newIndividual );
			
		} catch ( Exception e ) {
			System.err.println( e.getMessage() );
			System.exit(0);
		}
				
	}
	
	// Compares the instance of this operator to another
	// Return 0 in all cases since only one instance of this operator should exist at a time
	public int compareTo( final Object target ){
		
		return 0;
		
	}

}
