/* Nick Nichols - KnapsackFunction.java
 *
 * Fitness Function for the Knapsack Problem
 * We treat this as a minimization task, so we must use DeltaFitnessEvaluator
 *
 * We use items.0 to calculate the benefit
 * Selections over the weight limit have 2* (weight - limit) added on
 *
 */

import org.jgap.*;
import org.jgap.impl.*;
import java.io.*;
import java.util.Scanner;

public class KnapsackFunction extends FitnessFunction {

	private int items;
	private double weightLimit;
	private double[] weights;
	private double[] benefits;		
	private static final double PENALTYWEIGHT = 2.0;

	// Constructor that builds an instance of given a file path
	public KnapsackFunction( String filepath ){
		
		// Catch any exceptions for file i/o
		try{

			// Build a scanner to read the given file
			Scanner inFile = new Scanner( new BufferedReader( new FileReader( filepath )));

			// Read the number of items
			this.items = inFile.nextInt();

			// Read the weight limit
			this.weightLimit = inFile.nextDouble();

			weights = new double[ items ];
			benefits = new double [ items ];

			// Add each item in the file
			for( int i = 0; i < items; i++ ){
				double nextWeight = inFile.nextDouble();
				double nextBenefit = inFile.nextDouble();
			
				weights[i] = nextWeight;
				benefits[i] = nextBenefit;
			}

		// Exception handling and exiting
		} catch ( Exception e ) {
			System.err.println( "A fatal error occured" );
			System.err.println( e.getMessage() );
			System.exit(0);
		}
	}
	
	
	// Getter methods
	public int getItems(){
		return items;
	}
	

	
	
	// Performs the evaluation of a given individual
	// Genetic sequence must consist of dimension BooleanGenes
	public double evaluate( IChromosome target ){
		
		double runningWeight = 0.0;
		double runningBenefit = 0.0;

		// Get the genetic sequence
		Gene[] selection = target.getGenes();

		// Iterate through the genetic sequence
		for( int i = 0; i < this.items; i++ ){
	
			// See if the gene is true or false
			BooleanGene currentGene = (BooleanGene) selection[i];

			if( currentGene.booleanValue() ){
				runningWeight = runningWeight + weights[i];
				runningBenefit = runningBenefit + benefits[i];
			}
		}
	
		// Change this to a minimization task to standardize use of DeltaFitnessEvaluator
		double score = (double) items - runningBenefit;

		// Adjust our score if we surpased the weight limit
		if( runningWeight > weightLimit ){
			score = score + 2.0 * ( runningWeight - weightLimit );
		}
		
		return score;
	}
}
