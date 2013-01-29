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
 *     that a correct value is returned - Finished
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
	static final double EPSILON = 0.0001;
	
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
				testGene.setAllele( new Integer( 9 - i ) );
				fullOverlay[i] = testGene;
				
			}
			
			
			// Build tests for int-double conversions
			int[] intDubTest1 = {1};
			int[] intDubTest2 = {63};
			int[] intDubTest3 = {25};
			int[] intDubTest4 = {1, 1023, 512};
			
			// Build the solutions for the int-double conversions
			// Math was figured out with Wolfram Alpha
			double[] intDubSolutions1 = { (-29.0)/(31.0) };
			double[] intDubSolutions2 = { 1.0 };
			double[] intDubSolutions3 = { (-77.0)/(127.0) };
			double[] intDubSolutions4 = { (-5105.0)/(1023.0), (5.0), (5.0)/(1023.0) };
			
			
			// Build Tests for Fitness Function (Mathematical!)
			Gene[] fitnessTest1 = new Gene[ 64 ];
			Gene[] fitnessTest2 = new Gene[ 64 ];
			Gene[] fitnessTest3 = new Gene[ 64 ];
			Gene[] fitnessTest4 = new Gene[ 64 ];
			Gene[] fitnessTest5 = new Gene[ 64 ];
			Gene[] fitnessTest6 = new Gene[ 64 ];
			Gene[] fitnessTest7 = new Gene[ 64 ];
			Gene[] fitnessTest8 = new Gene[ 64 ];
			Gene[] fitnessTest9 = new Gene[ 64 ];
			Gene[] fitnessTest10 = new Gene[ 64 ];
			Gene[] fitnessTest11 = new Gene[ 64 ];
			Gene[] fitnessTest12 = new Gene[ 64 ];
			Gene[] fitnessTest13 = new Gene[ 64 ];
			Gene[] fitnessTest14 = new Gene[ 64 ];
			Gene[] fitnessTest15 = new Gene[ 64 ];
			Gene[] fitnessTest16 = new Gene[ 64 ];
			Gene[] fitnessTest17 = new Gene[ 64 ];
			Gene[] fitnessTest18 = new Gene[ 64 ];
			
			// 2 Dimensional Fitness Function Genomes
			Gene[] fitnessTest19 = new Gene[ 32 ];
			Gene[] fitnessTest20 = new Gene[ 32 ];
			Gene[] fitnessTest21 = new Gene[ 32 ];
			Gene[] fitnessTest22 = new Gene[ 32 ];
			Gene[] fitnessTest23 = new Gene[ 32 ];
			Gene[] fitnessTest24 = new Gene[ 32 ];
			Gene[] fitnessTest25 = new Gene[ 32 ];
			Gene[] fitnessTest26 = new Gene[ 32 ];
			Gene[] fitnessTest27 = new Gene[ 32 ];

			// NP-Complete Fitness Function Genomes
			Gene[] fitnessTest28 = new Gene[ 4 ];
			Gene[] fitnessTest29 = new Gene[ 4 ];
			Gene[] fitnessTest30 = new Gene[ 4 ];
			Gene[] fitnessTest31 = new Gene[ 7 ];
			Gene[] fitnessTest32 = new Gene[ 7 ];
			Gene[] fitnessTest33 = new Gene[ 7 ];
			Gene[] fitnessTest34 = new Gene[ 7 ];
			Gene[] fitnessTest35 = new Gene[ 7 ];
			Gene[] fitnessTest36 = new Gene[ 7 ];
			
			// Build an all 0 and an all 1 genome
			for( int i = 0; i < fitnessTest1.length; i++ ){
			
				BooleanGene trueGene = new BooleanGene( defConf, true );
				BooleanGene falseGene = new BooleanGene( defConf, false );
				
				fitnessTest1[i] = trueGene;
				fitnessTest2[i] = falseGene;
				
				fitnessTest4[i] = trueGene;
				fitnessTest5[i] = falseGene;
				
				fitnessTest7[i] = trueGene;
				fitnessTest8[i] = falseGene;
				
				fitnessTest10[i] = trueGene;
				fitnessTest11[i] = falseGene;
				
				fitnessTest13[i] = trueGene;
				fitnessTest14[i] = falseGene;
				
				fitnessTest16[i] = trueGene;
				fitnessTest17[i] = falseGene;
				
				// Build 2-dimensional cases
				if( i < 32 ){
					
					fitnessTest19[i] = trueGene;
					fitnessTest20[i] = falseGene;
					
					fitnessTest22[i] = trueGene;
					fitnessTest23[i] = falseGene;
					
					fitnessTest25[i] = trueGene;
					fitnessTest26[i] = falseGene;
					
				}
			}
						
			// Build a genome of values [32767 - 32768 - 32767 - 32768]
			for( int i = 0; i < 4; i++ ){
				for( int j = 0; j < 16; j++ ){
					
					// Make a value of 32767 (0111 1111 1111 1111)
					if( i % 2 == 0 ){
						
						fitnessTest3[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						fitnessTest6[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						fitnessTest9[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						fitnessTest12[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						fitnessTest15[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						fitnessTest18[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
						
					// Make a value of 32768 (1000 0000 0000 0000)
					} else {
						
						fitnessTest3[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
						fitnessTest6[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
						fitnessTest9[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
						fitnessTest12[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
						fitnessTest15[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
						fitnessTest18[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
		
					}
					
					// Build 2-Dimensional Cases
					if( i < 2 ){
						if( i == 0 ){
							
							fitnessTest21[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
							fitnessTest24[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
							fitnessTest27[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, false ) : new BooleanGene( defConf, true );
							
						} else {
							
							fitnessTest21[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
							fitnessTest24[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
							fitnessTest27[(i * 16) + j] = (j == 0) ? new BooleanGene( defConf, true ) : new BooleanGene( defConf, false );
							
						}
					}
				}
			}

			// TSP Genetic Sequences
			// Tour 1 (test28) = 0 1 2 3
			// Tour 2 (test29) = 3 0 1 2
			// Tour 3 (test30) = 1 3 0 2
			// Value of 1
			IntegerGene tspZeroGene = new IntegerGene( defConf );
			tspZeroGene.setAllele( new Integer( 0 ) );

			IntegerGene tspOneGene = new IntegerGene( defConf );
			tspOneGene.setAllele( new Integer( 1 ) );

			IntegerGene tspTwoGene = new IntegerGene( defConf );
			tspTwoGene.setAllele( new Integer( 2 ) );

			IntegerGene tspThreeGene = new IntegerGene( defConf );
			tspThreeGene.setAllele( new Integer( 3 ) );
			
			// Build the tours
			fitnessTest28[0] = tspZeroGene;
			fitnessTest28[1] = tspOneGene;
			fitnessTest28[2] = tspTwoGene;
			fitnessTest28[3] = tspThreeGene;

			fitnessTest29[0] = tspThreeGene;
			fitnessTest29[1] = tspZeroGene;
			fitnessTest29[2] = tspOneGene;
			fitnessTest29[3] = tspTwoGene;

			fitnessTest30[0] = tspOneGene;
			fitnessTest30[1] = tspThreeGene;
			fitnessTest30[2] = tspZeroGene;
			fitnessTest30[3] = tspTwoGene;


			// NK / Knapsack 0000000 / 1111111 sequences
			for( int i = 0; i < 7; i++ ){
			
				BooleanGene trueGene = new BooleanGene( defConf, true );
				BooleanGene falseGene = new BooleanGene( defConf, false );

				fitnessTest31[i] = falseGene;
				fitnessTest34[i] = falseGene;
	
				fitnessTest32[i] = trueGene;
				fitnessTest35[i] = trueGene;

			}

			// Build special Knapsack and NK cases
			BooleanGene npTrueGene = new BooleanGene( defConf, true );
			BooleanGene npFalseGene = new BooleanGene( defConf, false );

			fitnessTest33[0] = npTrueGene;
			fitnessTest33[1] = npTrueGene;
			fitnessTest33[2] = npTrueGene;
			fitnessTest33[3] = npFalseGene;
			fitnessTest33[4] = npTrueGene;
			fitnessTest33[5] = npTrueGene;
			fitnessTest33[6] = npFalseGene;

			fitnessTest36[0] = npFalseGene;
			fitnessTest36[1] = npFalseGene;
			fitnessTest36[2] = npTrueGene;
			fitnessTest36[3] = npFalseGene;
			fitnessTest36[4] = npTrueGene;
			fitnessTest36[5] = npTrueGene;
			fitnessTest36[6] = npFalseGene;


			// Build chromosomes for n-dimensional fitness function testing
			Chromosome fitnessChrom1 = new Chromosome( defConf, fitnessTest1 );
			Chromosome fitnessChrom2 = new Chromosome( defConf, fitnessTest2 );
			Chromosome fitnessChrom3 = new Chromosome( defConf, fitnessTest3 );
			Chromosome fitnessChrom4 = new Chromosome( defConf, fitnessTest4 );
			Chromosome fitnessChrom5 = new Chromosome( defConf, fitnessTest5 );
			Chromosome fitnessChrom6 = new Chromosome( defConf, fitnessTest6 );
			Chromosome fitnessChrom7 = new Chromosome( defConf, fitnessTest7 );
			Chromosome fitnessChrom8 = new Chromosome( defConf, fitnessTest8 );
			Chromosome fitnessChrom9 = new Chromosome( defConf, fitnessTest9 );
			Chromosome fitnessChrom10 = new Chromosome( defConf, fitnessTest10 );
			Chromosome fitnessChrom11 = new Chromosome( defConf, fitnessTest11 );
			Chromosome fitnessChrom12 = new Chromosome( defConf, fitnessTest12 );
			Chromosome fitnessChrom13 = new Chromosome( defConf, fitnessTest13 );
			Chromosome fitnessChrom14 = new Chromosome( defConf, fitnessTest14 );
			Chromosome fitnessChrom15 = new Chromosome( defConf, fitnessTest15 );
			Chromosome fitnessChrom16 = new Chromosome( defConf, fitnessTest16 );
			Chromosome fitnessChrom17 = new Chromosome( defConf, fitnessTest17 );
			Chromosome fitnessChrom18 = new Chromosome( defConf, fitnessTest18 );
			
			// 2-Dimension fitness function chromosomes
			Chromosome fitnessChrom19 = new Chromosome( defConf, fitnessTest19 );
			Chromosome fitnessChrom20 = new Chromosome( defConf, fitnessTest20 );
			Chromosome fitnessChrom21 = new Chromosome( defConf, fitnessTest21 );
			Chromosome fitnessChrom22 = new Chromosome( defConf, fitnessTest22 );
			Chromosome fitnessChrom23 = new Chromosome( defConf, fitnessTest23 );
			Chromosome fitnessChrom24 = new Chromosome( defConf, fitnessTest24 );
			Chromosome fitnessChrom25 = new Chromosome( defConf, fitnessTest25 );
			Chromosome fitnessChrom26 = new Chromosome( defConf, fitnessTest26 );
			Chromosome fitnessChrom27 = new Chromosome( defConf, fitnessTest27 );

			// NP-Complete fitness function chromosomes
			Chromosome fitnessChrom28 = new Chromosome( defConf, fitnessTest28 );
			Chromosome fitnessChrom29 = new Chromosome( defConf, fitnessTest29 );
			Chromosome fitnessChrom30 = new Chromosome( defConf, fitnessTest30 );
			Chromosome fitnessChrom31 = new Chromosome( defConf, fitnessTest31 );
			Chromosome fitnessChrom32 = new Chromosome( defConf, fitnessTest32 );
			Chromosome fitnessChrom33 = new Chromosome( defConf, fitnessTest33 );
			Chromosome fitnessChrom34 = new Chromosome( defConf, fitnessTest34 );
			Chromosome fitnessChrom35 = new Chromosome( defConf, fitnessTest35 );
			Chromosome fitnessChrom36 = new Chromosome( defConf, fitnessTest36 );
			
			// DeJong Sphere Function + results (Evaluated w/ Wolfram Alpha)
			DeJongSphereFunction testDeJongSphere = new DeJongSphereFunction( 4, 16, false );
			double DeJongSphere1 = 104.8576;
			double DeJongSphere2 = 104.8576;
			double DeJongSphere3 = 2.44148125057 * Math.pow( 10.0, -8.0 );
			
			// DeJong Ellipse Function + results (Evaluated w/ Wolfram Alpha)
			DeJongEllipsoidFunction testDeJongEllipsoid = new DeJongEllipsoidFunction( 4, 16, false );
			double DeJongEllipse1 = 262.144;
			double DeJongEllipse2 = 262.144;
			double DeJongEllipse3 = 6.103703 * Math.pow( 10.0, -8.0 );
			
			// Sum of Powers Function + results (Evaluated w/ Wolfram Alpha)
			SumOfPowersFunction testPowersFunction = new SumOfPowersFunction( 4, 16, false );
			double powers1 = 4.0;
			double powers2 = 4.0;
			double powers3 = 2.32834 * Math.pow( 10.0, -10.0 );
			
			// Griewank Function + results (Evaluated w/ Wolfram Alpha)
			GriewankFunction testGriewankFunction = new GriewankFunction( 4, 16, false );
			double griewank1 = 361.01465247;
			double griewank2 = 361.01465247;
			double griewank3 = 8.7395 * Math.pow( 10.0, -5.0 );
			
			// Rastrigin Function + results (Evaluated w/ Wolfram Alpha)
			RastriginFunction testRastriginFunction = new RastriginFunction( 4, 16, false );
			double rastrigin1 = 115.698855;
			double rastrigin2 = 115.698855;
			double rastrigin3 = 4.84371 * Math.pow( 10.0, -6.0 );
			
			// Rosenbrock Function + results (Evaluated w/ Wolfram Alpha)
			RosenbrockFunction testRosenbrockFunction = new RosenbrockFunction( 4, 16, false );
			double rosenbrock1 = 120048.0;
			double rosenbrock2 = 270108.0;
			double rosenbrock3 = 3.000154;
			
			// --- 2-Dimensional Cases --- \\
			// Michaelwicz Function + results (Evaluated w/ Wolfram Alpha)
			// We have calculated the necessary values and included the offset
			MichaelwiczFunction testMichaelwiczFunction = new MichaelwiczFunction( 16, false );
			double michaelwicz1 = 0.8608717 + 10.0;
			double michaelwicz2 = 0.0 + 10.0;
			double michaelwicz3 = -0.099931 + 10.0;
			
			// Camel Function + results (Evaluated w/ Wolfram Alpha)
			// Offset added to account for negative fitness scores
			CamelFunction testCamelFunction = new CamelFunction( 16, false );
			double camel1 = 194.9 + 10.0;
			double camel2 = 194.9 + 10.0;
			double camel3 = 1.07105 * Math.pow( 10.0, -8.0 ) + 10.0;
			
			// Schubert Function + results (Evaluated w/ Wolfram Alpha)
			SchubertFunction testSchubertFunction = new SchubertFunction( 16, false );
			double schubert1 = 0.282154;
			double schubert2 = 0.136144;
			double schubert3 = 34.533745;

			// --- NP-Complete Cases --- \\
			// TSPFunction + results (Evaluated w/ Google Calculator)
			TSPFunction testTSPFunction = new TSPFunction( "./Test-Cases/TSPTest.txt" );
			double tsp1 = 1.5;
			double tsp2 = 3.6;
			double tsp3 = 8.6;

			// KnapsackFunction + results (Evaluated w/ Google Calculator)
			KnapsackFunction testKnapsackFunction = new KnapsackFunction( "./Test-Cases/KnapsackTest.txt" );
			double knapsack1 = (double) 7;
			double knapsack2 = (double) 7 - 3.0 + 2.0;
			double knapsack3 = (double) 7 - 1.6;	

			// NKFunction + results (Evaluated w/ Google Calculator)
			NKFunction testNKFunction = new NKFunction( "./Test-Cases/NKTest.txt" );
			double nk1 = (double) 7 - 3.5;
			double nk2 = (double) 7 - 5.6;
			double nk3 = (double) 7 - 4.4;


			// Configuration and Operators for genetics testing
			// Pulled from MCP.java, which borrows from example JGAP code
			Configuration[] operatorConfigurations = new Configuration[3];

			operatorConfigurations[0] = new Configuration();
			operatorConfigurations[1] = new Configuration();
			operatorConfigurations[2] = new Configuration();

			// Build a sample genetic sequence
			Gene[] evoSample = new Gene[ 10 ];
			for( int i = 0; i < 10;i++ ){
				evoSample[i] = new BooleanGene( operatorConfigurations[0] );
				evoSample[i] = new BooleanGene( operatorConfigurations[1] );
				evoSample[i] = new BooleanGene( operatorConfigurations[2] );
			}

			for( int i = 0; i < 3; i ++ ){
				
				Configuration opConfig = operatorConfigurations[ i ];

				Chromosome sChrom = new Chromosome( opConfig, evoSample );
				opConfig.setSampleChromosome( sChrom );

				DeJongSphereFunction DJSF = new DeJongSphereFunction( 1, 10, false );
				opConfig.setFitnessFunction( DJSF );
				
				opConfig.setEventManager( new EventManager() );
				opConfig.setPopulationSize( 4 );
				opConfig.setBreeder( new GABreeder() );
				opConfig.setRandomGenerator( new StockRandomGenerator() );
				opConfig.setPreservFittestIndividual( true );
				opConfig.setKeepPopulationSizeConstant( false);
				opConfig.setMinimumPopSizePercent( 0 );
				opConfig.setFitnessEvaluator( new DeltaFitnessEvaluator() );
				opConfig.setChromosomePool( new ChromosomePool() );
				opConfig.addNaturalSelector( new StandardPostSelector(), false );
			}
			

			DiagonalizationOperator diag = new DiagonalizationOperator( operatorConfigurations[0] );
			diag.setPermutation( false );
			diag.setPercent( 1.0 );
			diag.setParents( 4 );
			operatorConfigurations[0].addGeneticOperator( diag );

			FitnessBasedScanningOperator fbscan = new FitnessBasedScanningOperator( operatorConfigurations[1] );
			fbscan.setPermutation( false );
			fbscan.setPercent( 1.0 );
			fbscan.setParents( 4 );
			operatorConfigurations[1].addGeneticOperator( fbscan );

			ElitistSchemaOverlayOperator eso = new ElitistSchemaOverlayOperator( operatorConfigurations[2] );
			eso.setPermutation( false );
			eso.setPercent( 1.0 );
			eso.setParents( 1 );
			eso.setMethod( 10 );
			operatorConfigurations[2].addGeneticOperator( eso );

			// Build a genotype to run 1 generation of each operator
			Genotype pop1 = Genotype.randomInitialGenotype( operatorConfigurations[0] );
			Genotype pop2 = Genotype.randomInitialGenotype( operatorConfigurations[1] );
			Genotype pop3 = Genotype.randomInitialGenotype( operatorConfigurations[2] );


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
			
			
			// BLOCK FOR INT-DOUBLE DOMAIN CONVERSION
			System.out.println( "Continuous To Discrete Domain Test" );
			System.out.println("");
			printTestStatus( testIntDouble( intDubTest1, -1.0, 1.0, 5, intDubSolutions1 ) );
			printTestStatus( testIntDouble( intDubTest2, -1.0, 1.0, 6, intDubSolutions2 ) );
			printTestStatus( testIntDouble( intDubTest3, -1.0, 1.0, 7, intDubSolutions3 ) );
			printTestStatus( testIntDouble( intDubTest4, -5.0, 5.0, 10, intDubSolutions4 ) );
			
			
			// BLOCK FOR DeJong SPHERE FUNCTION
			System.out.println( "DeJong HyperSphere Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testDeJongSphere.evaluate( fitnessChrom1 ), DeJongSphere1 ) );
			printTestStatus( testFitnessFunction( testDeJongSphere.evaluate( fitnessChrom2 ), DeJongSphere2 ) );
			printTestStatus( testFitnessFunction( testDeJongSphere.evaluate( fitnessChrom3 ), DeJongSphere3 ) );
			
			
			// BLOCK FOR DeJong ELLIPSOID FUNCTION
			System.out.println( "DeJong Hyper-Ellipsoid Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testDeJongEllipsoid.evaluate( fitnessChrom4 ), DeJongEllipse1 ) );
			printTestStatus( testFitnessFunction( testDeJongEllipsoid.evaluate( fitnessChrom5 ), DeJongEllipse2 ) );
			printTestStatus( testFitnessFunction( testDeJongEllipsoid.evaluate( fitnessChrom6 ), DeJongEllipse3 ) );			
			
			
			// BLOCK FOR SUM OF POWERS FUNCTION
			System.out.println( "Sum Of Increasing Powers Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testPowersFunction.evaluate( fitnessChrom7 ), powers1 ) );
			printTestStatus( testFitnessFunction( testPowersFunction.evaluate( fitnessChrom8 ), powers2 ) );
			printTestStatus( testFitnessFunction( testPowersFunction.evaluate( fitnessChrom9 ), powers3 ) );					
			
			
			// BLOCK FOR GRIEWANK FUNCTION
			System.out.println( "Griewank Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testGriewankFunction.evaluate( fitnessChrom10 ), griewank1 ) );
			printTestStatus( testFitnessFunction( testGriewankFunction.evaluate( fitnessChrom11 ), griewank2 ) );
			printTestStatus( testFitnessFunction( testGriewankFunction.evaluate( fitnessChrom12 ), griewank3 ) );						
			
			
			// BLOCK FOR RASTRIGIN FUNCTION
			System.out.println( "Rastrigin Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testRastriginFunction.evaluate( fitnessChrom13 ), rastrigin1 ) );
			printTestStatus( testFitnessFunction( testRastriginFunction.evaluate( fitnessChrom14 ), rastrigin2 ) );
			printTestStatus( testFitnessFunction( testRastriginFunction.evaluate( fitnessChrom15 ), rastrigin3 ) );						
			
			
			// BLOCK FOR ROSENBROCK FUNCTION
			System.out.println( "Rosenbrock Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testRosenbrockFunction.evaluate( fitnessChrom16 ), rosenbrock1 ) );
			printTestStatus( testFitnessFunction( testRosenbrockFunction.evaluate( fitnessChrom17 ), rosenbrock2 ) );
			printTestStatus( testFitnessFunction( testRosenbrockFunction.evaluate( fitnessChrom18 ), rosenbrock3 ) );						
			
			
			// BLOCK FOR MICHAELWICZ FUNCTION
			System.out.println( "Michaelwicz 2-D Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testMichaelwiczFunction.evaluate( fitnessChrom19 ), michaelwicz1 ) );
			printTestStatus( testFitnessFunction( testMichaelwiczFunction.evaluate( fitnessChrom20 ), michaelwicz2 ) );
			printTestStatus( testFitnessFunction( testMichaelwiczFunction.evaluate( fitnessChrom21 ), michaelwicz3 ) );						
			
			
			// BLOCK FOR CAMEL FUNCTION
			System.out.println( "Camel Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testCamelFunction.evaluate( fitnessChrom22 ), camel1 ) );
			printTestStatus( testFitnessFunction( testCamelFunction.evaluate( fitnessChrom23 ), camel2 ) );
			printTestStatus( testFitnessFunction( testCamelFunction.evaluate( fitnessChrom24 ), camel3 ) );						
			
			
			// BLOCK FOR Schubert FUNCTION
			System.out.println( "Schubert Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testSchubertFunction.evaluate( fitnessChrom25 ), schubert1 ) );
			printTestStatus( testFitnessFunction( testSchubertFunction.evaluate( fitnessChrom26 ), schubert2 ) );
			printTestStatus( testFitnessFunction( testSchubertFunction.evaluate( fitnessChrom27 ), schubert3 ) );


			// BLOCK FOR TSP FUNCTION
			System.out.println( "Traveling Salesman Problem Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testTSPFunction.evaluate( fitnessChrom28 ), tsp1 ) );
			printTestStatus( testFitnessFunction( testTSPFunction.evaluate( fitnessChrom29 ), tsp2 ) );
			printTestStatus( testFitnessFunction( testTSPFunction.evaluate( fitnessChrom30 ), tsp3 ) );		
			

			// BLOCK FOR Knapsack FUNCTION
			System.out.println( "Knapsack Problem Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testKnapsackFunction.evaluate( fitnessChrom31 ), knapsack1 ) );
			printTestStatus( testFitnessFunction( testKnapsackFunction.evaluate( fitnessChrom32 ), knapsack2 ) );
			printTestStatus( testFitnessFunction( testKnapsackFunction.evaluate( fitnessChrom33 ), knapsack3 ) );		
			

			// BLOCK FOR NK-Landscape FUNCTION
			System.out.println( "NK-Landscape Function Test" );
			System.out.println("");
			printTestStatus( testFitnessFunction( testNKFunction.evaluate( fitnessChrom34 ), nk1 ) );
			printTestStatus( testFitnessFunction( testNKFunction.evaluate( fitnessChrom35 ), nk2 ) );
			printTestStatus( testFitnessFunction( testNKFunction.evaluate( fitnessChrom36 ), nk3 ) );


			// BLOCK FOR Genetic Operators
			System.out.println( "Diagonalization Test" );
			System.out.println("");		
			pop1.evolve();
			printPop( pop1 );
			pop1.evolve();
			printPop( pop1 );

			System.out.println( "Fitness-Based Scanning Test" );
			System.out.println("");		
			pop2.evolve();
			printPop( pop2 );
			pop2.evolve();
			printPop( pop2 );

			System.out.println( "Elitist Schema Overlay Test" );
			System.out.println("");		
			pop3.evolve();
			printPop( pop3 );
			pop3.evolve();
			printPop( pop3 );
			
			
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
		
		// Fix the sequence 
		in = SupportFunctions.repairPermutation( in, overlay );
		
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
	
	// Function to test the discretization of reals
	public static boolean testIntDouble( int[] inVals, double lowerBound, double upperBound, int blocks, double[] comparison ){
	
		boolean succeeded = true;
		
		// Get the results of applying our algorithm
		double[] results = SupportFunctions.intToDoubleDomain( inVals, lowerBound, upperBound, blocks );
		
		// Compare the results
		for( int i = 0; i < comparison.length; i++ ){
		
			System.out.printf( "%f was expected and %f was returned %n", results[ i ], comparison[ i ] );
			
			// Note if we have a mismatch (excluding the computational error bound)
			if( !compareDouble( results[ i ], comparison[ i ], EPSILON) ){
				succeeded = false;
			}
			
		}
		
		return succeeded;
		
	}
	
	// Function to test fitness functions
	public static boolean testFitnessFunction( double result, double expected ){
		
		boolean compareResult = compareDouble( result, expected, EPSILON );
		
		System.out.printf( "%f was expected and %f was returned %n", expected, result );
		
		return compareResult;
		
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
	
	// Function to apply an overlay to a chromosome
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
	
	// Function to compare two doubles
	public static boolean compareDouble( double first, double second, double epsilon ){
			
		double ratio = first / second;

		// Make sure our ration is within +- epsilon of 1.0
		if( ratio >= (1.0 - epsilon) && ratio <= (1.0 + epsilon) ){
			return true;
		} else {
			return false;
		}

	}

	// Print out every chromosome in a population
	public static void printPop( Genotype geno ){
		
		Population pop = geno.getPopulation();
		List<IChromosome> chromList = pop.getChromosomes();

		for( IChromosome chrom: chromList ){
			Gene[] sequence = chrom.getGenes();

			for( int i = 0; i < sequence.length; i++ ){
				System.out.print( sequence[i].toString() + " " );
			}
			
			System.out.println("");
		}	
	}
}
