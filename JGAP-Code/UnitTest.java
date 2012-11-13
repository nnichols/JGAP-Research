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
import org.jgap.impl.*;

public class UnitTest{

	static int testCount;
	static int failures;
	
	public static void main( String[] args ){
		
		testCount = 1; 
		failures = 0;
		
		// Exception handling
		try{
		
			// BLOCK FOR BINARY TO INTEGER CONVERSION
			System.out.println( "Binary to Integer Conversion Test" );
			System.out.println("");
			
			printTestStatus( testBinaryToInt( "000000101101110111", 2 ) );
			printTestStatus( testBinaryToInt( "0101010101", 5 ) );
			printTestStatus( testBinaryToInt( "00000000", 8 ) );
			printTestStatus( testBinaryToInt( "11111111", 8 ) );
			printTestStatus( testBinaryToInt( "00000000000000001000000000000000", 2 ) );
			
			
			
			// BLOCK FOR GREY CODED BINARY TO INTEGER CONVERSION
			System.out.println( "Grey Coded Binary to Integer Conversion Test" );
			System.out.println("");
			
			printTestStatus( testGreyToInt( "000000101101110111", 2 ) );
			printTestStatus( testGreyToInt( "0101010101", 5 ) );
			printTestStatus( testGreyToInt( "00000000", 8 ) );
			printTestStatus( testGreyToInt( "11111111", 8 ) );
			printTestStatus( testGreyToInt( "00000000000000001000000000000000", 2 ) );			
			
			
			
			
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
	// Code is based off of Wikipedia from the following URL:
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
	
}
