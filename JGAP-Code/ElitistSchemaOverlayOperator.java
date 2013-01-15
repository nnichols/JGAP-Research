/* Nick Nichols - ElitistSchemaOverlayOperator.java
 *
 * Genetic Operator to support ESOs
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.impl.*;

public class ElitistSchemaOverlayOperator extends BaseGeneticOperator implements Comparable{

	// The k-value for the ESO 
	private static int kValue;
	
	// An integer switch that determines the method by which k will be changed over time
	// A value of 0 implies that k is a constant
	// A value of 1 implies that k will be chosen randomly
	// A value of 2 implies that k will be chosen by a function of the generations remaining
	// All other values will imply the same as a value of "0" 
	private static int kMethod;
	
	// The total number of generations that will be run
	private static int totalGenerations;
	
	// The number of generations remaining
	private static int generationsLeft;
	
	// The percent of the population to which this operator will be applied
	private static double applicationPercent;
	
	// Indicate if this genetic sequence is a permutation or not
	private static boolean isPermutation;
	
	// Arguementless Constructor that grabs configuration from the Genotype
	public ElitistSchemaOverlayOperator() throws InvalidConfigurationException {
		super(Genotype.getStaticConfiguration());
		initialize();
	}
	
	// Constructor that takes and sets a Configuration
	public ElitistSchemaOverlayOperator( final Configuration configuration ) throws InvalidConfigurationException {
		super(configuration);
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
	
	// Setter Methods
	public void setPermutation( boolean isPerm ){
		this.isPermutation = isPerm;
	}
	
	public void setPercent( double newPercent ){
		this.applicationPercent = newPercent;
	}
	
	public void setParents( int newK ){
		this.kValue = newK;
	}
	
	public void setMethod( int newMethod ){
		this.kMethod = newMethod;
	}
	
	public void setGenerations( int genCount ){
		this.generationsLeft = genCount;
	}
	

	// Build and apply the ESO
	public void operate( final Population population, final List candidateChromosomes ) {

		// Udpate the value of k
		this.updateK( population );
		
		// setGenes can throw exception, so we'll catch it if we can
		try{
		
			// Get the top k individuals from the population
			// (P.S. Thank Dijkstra that this operation exists)
			List<IChromosome> fittestIndividuals = (List<IChromosome>) population.determineFittestChromosomes( kValue );
		
			// Build the ESO
			Gene[] overlay = buildOverlay( fittestIndividuals );
		
			// Iterate through the first applicationPercent of the population
			int applicationCount = (int) ((double) population.size() * applicationPercent);
		
			for( int i = 0; i < applicationCount; i++ ){
		
				// Grab the current applicand
				// Note that we use the candidateChromosomes
				// This allows us to modify the preprocessed individuals
				IChromosome nextParent = (IChromosome) candidateChromosomes.remove( i );
			
				// Grab its genetic sequence
				Gene[] currentSequence = nextParent.getGenes();
			
				// Iterate over its genetic sequence to apply the overlay
				for( int j = 0; j < nextParent.size(); j++ ){
			
					// Only modify specified alleles
					if( overlay[j] != null ){
				
						// Modify the current allele
						currentSequence[j].setAllele( overlay[j] );
					
					}
				
				}
			
				// Give the parent its new sequence
				nextParent.setGenes( currentSequence );
			
				// Add the modified individual to the population
				candidateChromosomes.add( nextParent );
				
			}
			
		} catch(Exception e) {
			System.err.println( e.getMessage() );
		}
		
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
			
			// The static k option is the last, so we do nothing
			kValue = kValue;
			
		}
		
	}
	
	
	// Build an genetic overlay from the List of the fittest individuals
	private Gene[] buildOverlay( List<IChromosome> fittestIndividuals ){
		
		// Pull an example Chromosome out
		IChromosome sample = fittestIndividuals.get(0);
		
		Gene[] runningOverlay = new Gene[ sample.size() ];
		
		// Flood the array with nulls
		for( int i = 0; i < runningOverlay.length; i++ ){
			runningOverlay[i] = null;
		}
		
		// Iterate through each position in the genome
		for( int i = 0; i < runningOverlay.length; i++ ){
		
			boolean noMismatches = true;
			Iterator<IChromosome> parentItr = fittestIndividuals.iterator();
			
			// Get the first parent's value for comparison
			IChromosome firstParent = parentItr.next();
			
			Gene firstValue = firstParent.getGene(i);
			
			// Iterate through the Chromosomes until we hit a mismatch or we
			// reach the last parent's value
			while( noMismatches && parentItr.hasNext() ){
				
				// Get the next parent
				IChromosome currentParent = parentItr.next();
				
				// Get the gene we are currently looking at
				Gene currentValue = currentParent.getGene(i);
				
				// If these values are not equal, exit the loop and print a null
				// in this slot
				if( currentValue.compareTo( firstValue ) != 0 ){
					noMismatches = false;
				}
				
			}
			
			// Add the matched gene to the array if no mismatches occured
			if( noMismatches ){
				runningOverlay[i] = firstValue;
			}
			
		}
		
		return runningOverlay;
	}

}
