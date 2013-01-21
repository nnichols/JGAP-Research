/* Nick Nichols - NKGenerator.java
 *
 * Class to generate NK-Landscapes
 * 
 * All weights are of the form n x, n is a binary int, 0.0 < x < 1.0
 * Files are loacted at Test-Cases/nk%tn%sk where %t = n, %s = k
 *      e.g Test-Cases/nk10n5k
 *
 */

import java.io.*;
import java.util.*;

public class NKGenerator{
	public static void main( String[] args ){

		String nStr = args[0];
		int n = Integer.parseInt( nStr );
		
		String kStr = args[1];
		int k = Integer.parseInt( kStr );
		
		Random generator = new Random( System.currentTimeMillis() );
		
		// Keep track of all of the Strings that we'll need to print
		// (A kludge, but it makes life easier with the file structure)
		ArrayList<String> printQueue = new ArrayList<String>();
		
		// Add the n and k values
		printQueue.add( nStr );
		printQueue.add( kStr );

		// Find how many values we must generate
		int valuesToGenerate = (int) Math.pow( 2.0, (double) k );
		
		// Iterate through every value to build
		// WARNING: THIS WILL TAKE O(2^n)		
		for( int i = 0; i < valuesToGenerate; i++ ){
			
			double newWeight = generator.nextDouble();

			StringBuilder printLine = new StringBuilder();
			
			// Value must be in binary
			printLine.append( Integer.toString(i, 2) );
			printLine.append( " " );
			printLine.append( newWeight );
					
			printQueue.add( printLine.toString() );
	
		}

		// Print out everything to a file
		try{
			
			BufferedWriter outStream = new BufferedWriter( new FileWriter( "./Test-Cases/nk" + n + "n" + k + "k.txt" ));
			
			// Iterate through printQueue
			for( String line : printQueue ){
				outStream.write( line, 0, line.length() );
				outStream.newLine();
			}
			
			outStream.close();
			
		} catch( Exception e ) {
			System.err.println( "A fatal error occured: " + e.getMessage() );
		}

	}
}