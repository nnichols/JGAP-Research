/* Nick Nichols - NKGenerator.java
 *
 * Class to generate NK-Landscapes
 * 
 * All weights are of the form n x, n is a binary int, 0.0 < x < 1.0
 * Files are loacted at Test-Cases/nk%tn%sk%i.txt where %t = n, %s = k, %i = instance
 *      e.g Test-Cases/nk10n5k7.txt
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

		String instStr = args[2];
		int instCount = Integer.parseInt( instStr );
		
		Random generator = new Random( System.currentTimeMillis() );

		// Build every instance
		for( int inst = 0; inst < instCount; inst++ ){
		
			// Keep track of all of the Strings that we'll need to print
			// (A kludge, but it makes life easier with the file structure)
			ArrayList<String> printQueue = new ArrayList<String>();
			
			// Generate seed
			long seed = generator.nextLong();
		
			// Add the n, k, and seed values
			printQueue.add( nStr );
			printQueue.add( kStr );
			printQueue.add( Long.toString( seed ) );
	

			// Print out everything to a file
			try{
			
				BufferedWriter outStream = new BufferedWriter( new FileWriter( "./Test-Cases/nk" + n + "n" + k + "k" + inst + ".txt" ));
			
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
}
