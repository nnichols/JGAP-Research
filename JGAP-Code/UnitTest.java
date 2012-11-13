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

	public static void main( String[] args ){
		
		// Exception handling
		try{
		
			// Configuration for testing support functions
			Configuration uTest = new Configuration( "TestSupportFunctions" );

			// Build a sequence for a two-dimensional binary to integer mapping
			// The values as integers will be [42 : 32 + 8 + 2 , 213: 128 + 64 + 16 + 4 + 1 ]
			// And both will be in a sequence of length 8:	0010 1010 , 1101 0101
			Gene[] binaryToInt = new Gene[ 16 ];
		
			// Initialize values for the first number
			binaryToInt[ 0 ] = new BooleanGene( uTest, false );
			binaryToInt[ 1 ] = new BooleanGene( uTest, false);
			binaryToInt[ 2 ] = new BooleanGene( uTest, true );
			binaryToInt[ 3 ] = new BooleanGene( uTest, false );
			binaryToInt[ 4 ] = new BooleanGene( uTest, true );
			binaryToInt[ 5 ] = new BooleanGene( uTest, false );
			binaryToInt[ 6 ] = new BooleanGene( uTest, true );
			binaryToInt[ 7 ] = new BooleanGene( uTest, false );
		
			// Initialize values for the second number
			binaryToInt[ 8  ] = new BooleanGene( uTest, true );
			binaryToInt[ 9  ] = new BooleanGene( uTest, true );
			binaryToInt[ 10 ] = new BooleanGene( uTest, false );
			binaryToInt[ 11 ] = new BooleanGene( uTest, true );
			binaryToInt[ 12 ] = new BooleanGene( uTest, false );
			binaryToInt[ 13 ] = new BooleanGene( uTest, true );
			binaryToInt[ 14 ] = new BooleanGene( uTest, false );
			binaryToInt[ 15 ] = new BooleanGene( uTest, true );
		
		
			// Build a chromosome to test
			Chromosome bToIntTest = new Chromosome( uTest, binaryToInt );
		
			// Send it to the function
			int[] bToIntSolutions = SupportFunctions.booleanChromosomeToInt( bToIntTest, 2, 8 );
		
			// Print the solutions
			System.out.println( "The first binary to int conversion output: " + bToIntSolutions[ 0 ] );
			System.out.println( "The second binary to int conversion output: " + bToIntSolutions[ 1 ] );
		
			// Verify the solutions
			if( bToIntSolutions[ 0 ] == 42 && bToIntSolutions[ 1 ] == 213 ){
				System.out.println( "Binary to int conversion passed" );
			} else { 
				System.out.println( "Binary to int conversion failed" );
			}

		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
		}

	}
}
