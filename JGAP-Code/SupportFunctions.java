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
	private static int[] booleanChromosomeToInt( IChromosome in, int dimension, int dimensionLength ){
	
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
	private static int[] booleanChromosomeToGrayInt( IChromosome in, int dimension, int dimensionLength ){	
	
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
	
	
}