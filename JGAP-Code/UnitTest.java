/* Nick Nichols - UnitTest.java
 *
 * Tests created to ensure all created components function correctly,
 *     including the Genetic Operators, Fitness Functions, and Support Functions.
 *
 * Genetic Operators will manipulate test sequences and output the results
 *     for both visual and computational verification
 *
 * Fitness Functions will have various genetic sequences passed to them
 *     and ensure that the correct double value is returned
 *
 * Support Functions will have genetic sequences passed to them and ensure
 *     that a correct value is returned
 *
 */

import java.util.*;
import org.jgap.*;
import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.event.*;
import org.jgap.util.*;

public class UnitTest{

	static int testCount;
	static int failures;
	
	public static void main( String[] args ){
		
		// Exception handling 
		try{
		
			testCount = 1; 
			failures = 0;
			Configuration defConf = new DefaultConfiguration();
		
		
			// Construct all objects needed for testing
			// Permutation Repair Algorithm Sequences
			Gene[] sample1 = new Gene[ 10 ];
			Gene[] sample2 = new Gene[ 10 ];
			Gene[] sample3 = new Gene[ 10 ];
		
			Gene[] overlaySample1 = new Gene[ 10 ];
			Gene[] overlaySample2 = new Gene[ 10 ];
			Gene[] overlaySample3 = new Gene[ 10 ];
			Gene[] overlaySample4 = new Gene[ 10 ];
			Gene[] overlaySample5 = new Gene[ 10 ];
			Gene[] overlaySample6 = new Gene[ 10 ];
			Gene[] overlaySample7 = new Gene[ 10 ];
			Gene[] overlaySample8 = new Gene[ 10 ];
			Gene[] overlaySample9 = new Gene[ 10 ];
		
		
			// Build a correct permutation for first test of each type
			for( int i = 0; i < 10; i++ ){
			
				sample1[i] = new IntegerGene( defConf, 0, 10 );
				sample1[i].setAllele( new Integer( i ) );
			
				overlaySample1[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample1[i].setAllele( new Integer( i ) );
			
				overlaySample2[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample2[i].setAllele( new Integer( i ) );

				overlaySample3[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample3[i].setAllele( new Integer( i ) );
			
			}
		
			// Build a sequence with one repeated value (0) for the second test
			for( int i = 0; i < 10; i++ ){
			
				int j = i % 9;
				
				sample2[i] = new IntegerGene( defConf, 0, 9 );
				sample2[i].setAllele( new Integer( j ) );

				overlaySample4[i] = new IntegerGene( defConf, 0, 9 );
				overlaySample4[i].setAllele( new Integer( j ) );

				overlaySample5[i] = new IntegerGene( defConf, 0, 9 );
				overlaySample5[i].setAllele( new Integer( j ) );

				overlaySample6[i] = new IntegerGene( defConf, 0, 9 );
				overlaySample6[i].setAllele( new Integer( j ) );
			}
		
			// Build a sequence of all repeated values (1) for the third test
			for( int i = 0; i < 10; i++ ){

				sample3[i] = new IntegerGene( defConf, 0, 10 );
				sample3[i].setAllele( new Integer( 1 ) );
			
				overlaySample7[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample7[i].setAllele( new Integer( 1 ) );

				overlaySample8[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample8[i].setAllele( new Integer( 1 ) );

				overlaySample9[i] = new IntegerGene( defConf, 0, 10 );
				overlaySample9[i].setAllele( new Integer( 1 ) );
			
			}
		
			// Move values into their respective Chromosomes
			Chromosome firstChrom = new Chromosome( defConf, sample1 );
			Chromosome secondChrom = new Chromosome( defConf, sample2 );
			Chromosome thirdChrom = new Chromosome( defConf, sample3 );
		
			Chromosome overlayChrom1 = new Chromosome( defConf, overlaySample1 );
			Chromosome overlayChrom2 = new Chromosome( defConf, overlaySample2 );
			Chromosome overlayChrom3 = new Chromosome( defConf, overlaySample3 );
			Chromosome overlayChrom4 = new Chromosome( defConf, overlaySample4 );
			Chromosome overlayChrom5 = new Chromosome( defConf, overlaySample5 );
			Chromosome overlayChrom6 = new Chromosome( defConf, overlaySample6 );
			Chromosome overlayChrom7 = new Chromosome( defConf, overlaySample7 );
			Chromosome overlayChrom8 = new Chromosome( defConf, overlaySample8 );
			Chromosome overlayChrom9 = new Chromosome( defConf, overlaySample9 );

			// Build an empty genetic overlay
			IntegerGene[] emptyOverlay = new IntegerGene[ 10 ];
			
			for( int i = 0; i < emptyOverlay.length; i++ ){
				emptyOverlay[i] = null;
			}
			
			
			// Build a partially defined overlay
			// 
			// Position 2 = 5
			// Position 3 = 9
			// Position 8 = 3
			IntegerGene[] partialOverlay = new IntegerGene[ 10 ];
			
			for( int i = 0; i < partialOverlay.length; i++ ){
				if( i == 2 ){
					
					IntegerGene testGene = new IntegerGene( defConf, 0, 10 );
					testGene.setAllele( new Integer( 5 ) );
					partialOverlay[i] = testGene;
					
				} else if( i == 3 ){
					
					IntegerGene testGene = new IntegerGene( defConf, 0, 10 );
					testGene.setAllele( new Integer( 9 ) );
					partialOverlay[i] = testGene;
					
				} else if( i == 7 ){
					
					IntegerGene testGene = new IntegerGene( defConf, 0, 10 );
					testGene.setAllele( new Integer( 3 ) );
					partialOverlay[i] = testGene;
					
				} else {
					partialOverlay[i] = null;
				}
			}
			
			
			// Build a completely defined overlay
			IntegerGene[] fullOverlay = new IntegerGene[ 10 ];
			
			for( int i = 0; i < fullOverlay.length; i++ ){
			
				IntegerGene testGene = new IntegerGene( defConf, 0, 10 );
				testGene.setAllele( new Integer( 10 - i ) );
				fullOverlay[i] = testGene;
				
			}
			
						
		
			// ---------- TESTING SEGMENT ----------
			// BLOCK FOR BINARY TO INTEGER CONVERSION
			// MAXIMUM SEQUENCE LENTH IS 31 PLACES
			System.out.println( "Binary to Integer Conversion Test" );
			System.out.println("");
			
			printTestStatus( testBinaryToInt( "000000101101110111", 2 ) );
			printTestStatus( testBinaryToInt( "01100011", 4 ) );
			printTestStatus( testBinaryToInt( "00000000", 8 ) );
			printTestStatus( testBinaryToInt( "11111111", 8 ) );
			printTestStatus( testBinaryToInt( "1111111111111111111111111111111", 1 ) );	
			printTestStatus( testBinaryToInt( "00000000000000000000000000000000", 1 ) );
			
			
			// BLOCK FOR GREY CODED BINARY TO INTEGER CONVERSION
			// MAXIMUM SEQUENCE LENTH IS 31 PLACES
			System.out.println( "Grey Coded Binary to Integer Conversion Test" );
			System.out.println("");
			
			printTestStatus( testGreyToInt( "000000101101110111", 2 ) );
			printTestStatus( testGreyToInt( "01100011", 4 ) );
			printTestStatus( testGreyToInt( "00000000", 8 ) );
			printTestStatus( testGreyToInt( "11111111", 8 ) );
			printTestStatus( testGreyToInt( "1111111111111111111111111111111", 1 ) );	
			printTestStatus( testGreyToInt( "00000000000000000000000000000000", 1 ) );
			
			
			// BLOCK FOR PERMUTATION REPAIR ALGORITHM TESTING (SANS OVERLAY)
			System.out.println( "Permutation Repair Algorithm Test" );
			System.out.println("");
			
			printTestStatus( testRepairAlg( firstChrom, null ) );
			printTestStatus( testRepairAlg( secondChrom, null ) );
			printTestStatus( testRepairAlg( thirdChrom, null ) );
			
			
			// BLOCK FOR PERMUTATION REPAIR ALGORITHM TESTING WITH OVERLAYS
			System.out.println( "Permutation Repair Algorithm Test With Overlays" );
			System.out.println("");
			
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom1, emptyOverlay ), emptyOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom2, partialOverlay ), partialOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom3, fullOverlay ), fullOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom4, emptyOverlay ), emptyOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom5, partialOverlay ), partialOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom6, fullOverlay ), fullOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom7, emptyOverlay ), emptyOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom8, partialOverlay ), partialOverlay ) );
			printTestStatus( testRepairAlg( applyOverlay( overlayChrom9, fullOverlay ), fullOverlay ) );
			
			
			// FINAL REPORTING BLOCK
			if( failures == 0 ){
				System.out.println( "All tests were successful" );
			} else {
				System.out.printf( "%d errors were found %n", failures );
			}
		
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
		}

	}
	
	// Function to convert Strings of '0' and '1's to a binary Chromosome
	public static Chromosome stringToBinaryGenes( Configuration config, String binarySequence ){
		
		// Exception handling
		try{
		
			// The Gene[] for use
			Gene[] binaryToInt = new Gene[ binarySequence.length() ];
		
			// Iterate through the string
			for( int i = 0; i < binarySequence.length(); i++ ){
			
				// If the next char is '1', we set the value to true
				boolean placeValue = ( binarySequence.charAt(i) == '1' ) ? true : false;
			
				binaryToInt[ i ] = new BooleanGene( config, placeValue );
			}
		
			// Build a new Chromosome to return
			Chromosome retVal = new Chromosome( config, binaryToInt );
		
			return retVal;
			
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			
			return null;
		}
	}
	
	// Function to decode Grey Code sequences to their int value
	//
	// Conversion uses Wikipedia's code as a source from the following URL:
	// http://en.wikipedia.org/wiki/Gray_code#Converting_to_and_from_Gray_code
	public static int greyConvertor( String greySequence ){
		
		// Convert the string to "binary"
		int binaryValue = Integer.parseInt( greySequence, 2 );
		
		// Iterate through the binary sequence
		for( int i = 1; i < greySequence.length(); i = i * 2 ){
			
			binaryValue = binaryValue ^ ( binaryValue >> i );
			
		}
		
		return binaryValue;
	}
	
	// Function to compare the binary string compared to our decoding of it
	public static boolean testBinaryToInt( String binarySequence, int dimensions ){
		
		boolean hasNotFailed = true;
		
		try {
			
			// Build a configuration
			Configuration uTest = new Configuration( "BtoIntTest" );
			
			// Build the genetic sequence for testing
			Chromosome bToIntTest = stringToBinaryGenes( uTest, binarySequence );
			
			// Get the length of each dimension
			int dimensionLength = binarySequence.length() / dimensions;
			
			// Send it to the function
			int[] bToIntSolutions = SupportFunctions.booleanChromosomeToInt( bToIntTest, dimensions, dimensionLength );
			
			// Print out the results and compare them to actual values
			for( int i = 0; i < bToIntSolutions.length; i++ ){
				
				// Get the substring that we're looking at
				String test = binarySequence.substring( i * dimensionLength, (i + 1) * dimensionLength );
				
				// Parse the actual value out in binary
				int comparisonValue = Integer.parseInt( test, 2 );
				
				// Print the value we found and the value we should have
				// If there is a mismatch anywhere, we will return false
				if( comparisonValue == bToIntSolutions[ i ] ) {
					
					System.out.printf( "%d was expected and %d was returned: Success %n", comparisonValue,  bToIntSolutions[ i ] );
			
				} else {
					
					System.out.printf( "%d was expected and %d was returned: Failure %n", comparisonValue,  bToIntSolutions[ i ] );
					hasNotFailed = false;
				}
				
			}
			
			return hasNotFailed;
			
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			
			// Something messed up, so we reutn false
			return false;
		}
		
	}
	
	// Function to compare the Grey Coded Binary string to the actual value
	public static boolean testGreyToInt( String greySequence, int dimensions ){
		
		boolean hasNotFailed = true;
		
		try {
	
			// Build a configuration
			Configuration uTest = new Configuration( "gToIntTest" );
			
			// Build the genetic sequence for testing
			Chromosome gToIntTest = stringToBinaryGenes( uTest, greySequence );
			
			// Get the length of each dimension
			int dimensionLength = greySequence.length() / dimensions;
			
			// Send it to the function
			int[] gToIntSolutions = SupportFunctions.booleanChromosomeToGrayInt( gToIntTest, dimensions, dimensionLength );
			
			// Print out the results and compare them to actual values
			for( int i = 0; i < gToIntSolutions.length; i++ ){
				
				// Get the substring that we're looking at
				String test = greySequence.substring( i * dimensionLength, (i + 1) * dimensionLength );
				
				// Get the int value represented by this grey code
				int trueGreyValue = greyConvertor( test );
				
				// Print the value we found and the value we should have
				// If there is a mismatch anywhere, we will return false
				if( trueGreyValue == gToIntSolutions[ i ] ) {
					
					System.out.printf( "%d was expected and %d was returned: Success %n", trueGreyValue,  gToIntSolutions[ i ] );
					
				} else {
					
					System.out.printf( "%d was expected and %d was returned: Failure %n", trueGreyValue,  gToIntSolutions[ i ] );
					hasNotFailed = false;
				}
			}	
		
			
			// Return
			return hasNotFailed;
		
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			
			// Something messed up, so we reutn false
			return false;
		}	
			
	}
	
	// Function to test the permutation repair algorithm
	public static boolean testRepairAlg( IChromosome in, Gene[] overlay ){
		
		// Fix the sequence using the correct method
		if( overlay == null ){
			in = SupportFunctions.repairPermutation( in );
		} else {
			in = SupportFunctions.repairPermutation( in, overlay );
		}
		
		boolean didWork = true;
		
		// Use a HashSet to see if the integer values in the chromosome are unique
		HashSet<Integer> permChecker = new HashSet<Integer>();
		
		// Get the genetic sequence
		Gene[] permutation = in.getGenes();
		
		// Iterate through the sequence
		for( int i = 0; i < permutation.length; i++ ){
			
			// Ensure that we do not continue once we find a problem
			if( didWork ){
				
				// Get the next value
				IntegerGene current = (IntegerGene) permutation[ i ];
				
				// Add the value to the hash
				didWork = permChecker.add( current.intValue() );
				System.out.println( current.intValue() );
				
			} else {
				break;
			}
		}
		
		return didWork;
		
	}
	
	// Function to print result of each test
	public static void printTestStatus( boolean passMarker ){
	
		if( passMarker ){
			System.out.printf( "Test %d was successful %n", testCount );
		} else { 
			System.out.printf( "Test %d failed %n", testCount );
			
			// Record that a test has failed
			failures++;
		}
		
		// Leave a blank line and increment the testCount
		System.out.println("");
		testCount++;
		
	}
	
	// Functio to apply an overlay to a chromosome
	public static IChromosome applyOverlay( IChromosome in, IntegerGene[] overlay ){
		
		// Errors may be thrown
		try{
		
			Gene[] currentGenes = in.getGenes();
		
			// Iterate through the overlay
			for( int i = 0; i < overlay.length; i++ ){
			
				// Operate only on defined values
				if( overlay[ i ] != null ){
				
					// Pass along the allele
					IntegerGene currentGene = (IntegerGene) currentGenes[ i ];
					
					Integer newVal = new Integer( overlay[ i ].intValue() );
					
					currentGene.setAllele( newVal );
				
				}
			}
		
			in.setGenes( currentGenes );
		
		} catch ( Exception e ) {
			System.err.println( "The overlay could not be applied. " + e.getMessage() );
			System.exit( 0 );
		}
		
		return in;
		
	}
	
}
