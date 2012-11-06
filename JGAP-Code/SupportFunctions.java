/* Nick Nichols - SupportFunctions.java
 *
 * A collection of functions whose capacities are needed across 
 * several different classes. To minimize code duplication, these
 * static methods should be used as often as possible
 *
 */

import org.jgap.*;
import org.jgap.impl.*;

public class SupportFunctions{

	// Maps a genetic sequence of BooleanGenes to the integer
	// it represents in traditional binary.
	public static int[] booleanChromosomeToInt( IChromosome in, int dimension, int dimensionLength ){
	
		// Get the genetic sequence
		Gene[] genome = in.getGenes();
		
		// Build an array to store each of the values
		// for each dimension
		int[] integerValues = new int[ dimension ];
		
		// Keep track of our position within the genome
		int genomePosition = 0;
		
		// Iterate through each dimension
		for( int i = 0; i < dimension; i++ ){
		
			int dimensionValue = 0;
			
			// Make the currentPowerValue set to the highest power of 2
			// for the given dimensionLength and subtract 1 to account
			// for 2 ^ 0
			int currentPowerValue = (int)  Math.pow( 2.0 , (double) (dimensionLength - 1) );
			
			// Iterate through each position within that dimension
			for( int j = 0; j < dimensionLength; j++ ){
			
			
				// Get the current Gene
				BooleanGene currentGene = (BooleanGene) genome[ genomePosition ];
			
				// If our current gene is true, add currentPowerValue to dimensionValue
				if( currentGene.booleanValue() ){
					
					dimensionValue = dimensionValue + currentPowerValue;
					
				}
				
				// Update the currentPowerValue
				currentPowerValue = currentPowerValue / 2;
				
				// Update our position
				genomePosition++;
			
			}
			
			// Add the value created to our array
			integerValues[i] = dimensionValue;
		
		}
		
		return integerValues;
	}
	
	
	
	// Maps a genetic sequence of BooleanGenes to the integer
	// it represents in grqy coded binary.
	public static int[] booleanChromosomeToGrayInt( IChromosome in, int dimension, int dimensionLength ){	
	
		// Get the genetic sequence
		Gene[] genome = in.getGenes();
		
		// Build an array to store each of the values
		// for each dimension
		int[] integerValues = new int[ dimension ];
		
		// Keep track of our position within the genome
		int genomePosition = 0;
		
		// Iterate through each dimension
		for( int i = 0; i < dimension; i++ ){
		
			// Build a binary string using the rules of Gray coding
			// that will be parsed to an integer
			StringBuilder dimensionValue = new StringBuilder();
			
			// Iterate through each position within that dimension
			for( int j = 0; j < dimensionLength; j++ ){
			
			
				// Get the current Gene
				BooleanGene currentGene = (BooleanGene) genome[ genomePosition ];
			
				// Drop the Most Significant Bit straight down
				if( j == 0 ){
					
					// Copy the MSB
					String firstBit = currentGene.booleanValue() ? "1" : "0";
					
					dimensionValue.append( firstBit );
					
				// Else we need to XOR the previous bit in out copied sequence
				// with the current boolean in the genetic sequence
				} else {
				
					// True iff the binary value of the bit in the previous position
					// in the stringBuilder has the opposite boolean value from the current
					// BooleanGene's value
					if( (dimensionValue.charAt( j - 1 ) == '1' && !currentGene.booleanValue())
							|| (dimensionValue.charAt( j - 1 ) == '0' && currentGene.booleanValue())){
						
						// Append a 1 for this position
						dimensionValue.append( "1" );
					
					} else {
					
						// Ptherwise append a 0
						dimensionValue.append( "0" );
					
					}
				}
				
				// Update our position
				genomePosition++;
			
			}
			
			// Get the string storing the binary sequence of the dimension value
			String binaryString = dimensionValue.toString();
			
			// Parse the base 2 value and add it to the array
			integerValues[i] = Integer.parseInt( binaryString, 2 );
		
		}
		
		return integerValues;	
	
	}
	
	
	// Takes an integer array and transforms it into an array of doubles
	// for the values those ints represent
	public static double[] intToDoubleDomain( int[] in, double lowerBound, double upperBound, int blocks ){
		
		// Calculate the denominator for all calculations since it will not change	
		// (2 ^ blocks) - 1
		double denominator = Math.pow( 2, (double) blocks ) - 1.0;
		
		// Now calculate the value of the fractional end
		// (lower - upper) / denominator
		double fraction = (upperBound - lowerBound) / denominator;
		
		// Create an array to store the values as they are generated
		double[] evaluations = new double[ in.length ];
		
		// Iterate through all of the values
		for( int i = 0; i < in.length; i++ ){
		
			// Compute the value the current position
			evaluations[i] = lowerBound + ( (double) in[i] ) * fraction; 
			
		}
		
		return evaluations;
		
	}
	
	// Takes a permutation and corrects is 
	// This version DOES NOT handle genetic overlays
	public static IChromosome repairPermutation( IChromosome given, int permutableValues ){
		
		// Extract the genetic sequence
		Gene[] permutation = given.getGenes();
		
		// Assume we have a permutation until we know otherwise
		boolean isPermutation = true;
		
		// Build an array to store how often each value is used
		int[] valuesUsed = new int[ permutation.length ];
		
		// Flood with 0s
		for( int i = 0; i < valuesUsed.length; i++ ){
			valuesUsed[i] = 0;
		}
		
		
		// Fill the array with how often a given index has been used
		for( int i = 0; i < permutation.length; i++ ){
		
			// Get the current Gene
			IntegerGene currentGene = (IntegerGene) permutation[i];
			
			// Get that gene's value
			int currentValue = currentGene.intValue();
			
			// Make a note if the sequence is not a permutation
			if( valuesUsed[ currentValue ] != 0 ){
				isPermutation = false;
			}
			
			// Update how much this value has been used
			valuesUsed[ currentValue ] = valuesUsed[ currentValue ] + 1;
			
		}
		
		
		// Return the sequence unaltered if it is a permutation
		// Else, repair it first
		if( isPermutation ){
		
			return given;
		
		} else {
			
			// Iterate through the entire genetic sequence
			for( int i = 0; i < permutation.length; i++ ){
				
				// Get the current Gene
				IntegerGene currentGene = (IntegerGene) permutation[i];
				
				// Get that gene's value
				int currentValue = currentGene.intValue();
			
				// Test and see if this gene value has been used multiple times
				// If it has, we need to change it
				if( valuesUsed[ currentValue ] > 1 ) {
				
					boolean noValueFound = true;
					int startPosition = (currentValue + 1) % valuesUsed.length;
					
					// Iterate through the remaining possible values
					// utill we find one that is unused
					while( noValueFound ){
						
						// We have found an unused value
						if( valuesUsed[ startPosition ] == 0 ) {
							
							// Create a new Integer to store
							Integer newVal = new Integer( valuesUsed[ startPosition ] );
							
							// Update the current gene
							currentGene.setAllele( newVal );
							
							// Exit the loop					  
							 noValueFound = false;
						}
						
						// Go to the next value
						startPosition = (startPosition + 1) % valuesUsed.length;
						
					}
					
				}
				
			}
			
			return given;
			
		}
		
	}
	
}