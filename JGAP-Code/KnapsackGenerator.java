/* Nick Nichols - KnapsackGenerator.java
 *
 * Class to generate Knapsack Problem instances
 * 
 * All benefits and weights are of the form x y, 0.0 < x,y < 1.0
 * Files are loacted at Test-Cases/knapsack%ni where %i = items
 *      e.g Test-Cases/knapsack7i
 *
 */

import java.io.*;
import java.util.*;

public class KnapsackGenerator{
	public static void main( String[] args ){

		String itemStr = args[0];
		int itemCount = Integer.parseInt( itemStr );
		Random generator = new Random( System.currentTimeMillis() );
		
		// Keep track of all of the Strings that we'll need to print
		// (A kludge, but it makes life easier with the file structure)
		ArrayList<String> printQueue = new ArrayList<String>();
		
		// Add the number of items
		printQueue.add( itemStr );
		
		// Print a randomly selected weight limit W, 0.0 < W < items.0
		double weightLimit = generator.nextDouble() * (double) itemCount;
		printQueue.add( Double.toString(weightLimit) );
		
		// Create each item	
		for( int i = 0; i < itemCount; i++ ){

			// Make a new item	
			double newWeight = generator.nextDouble();
			double newBenefit = generator.nextDouble();
				
			StringBuilder printLine = new StringBuilder();
			printLine.append( newWeight );
			printLine.append( " " );
			printLine.append( newBenefit );
				
			printQueue.add( printLine.toString() );
					
		}


		// Print out everything to a file
		try{
			
			BufferedWriter outStream = new BufferedWriter( new FileWriter( "./Test-Cases/knapsack" + itemCount + "i.txt" ));
			
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