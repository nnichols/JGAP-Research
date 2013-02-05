/*
 * Nick Nichols - FBTest.java
 *
 * This class will test the functionality of the Fitness-Based Scanning Operator
 *
 */
 
import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.event.*;
import org.jgap.util.*;
 
public class FBTest{
	public static void main( String[] args ) throws Exception{
	
		// number of parents to use
		int fbCheck = Integer.parseInt( args[0] );
	
		try{
		
			// Set up a small test population and configuration	
			// Taken directly from MCP.java	
			Configuration config = new Configuration();
			config.setPopulationSize( 5 );
			config.setBreeder( new GABreeder() );
			config.setRandomGenerator( new StockRandomGenerator() );
			config.setEventManager( new EventManager() );
			config.setPreservFittestIndividual( true );
			config.setKeepPopulationSizeConstant( false );
			config.setMinimumPopSizePercent( 0 );
			config.setFitnessEvaluator( new DeltaFitnessEvaluator() );
			config.setChromosomePool( new ChromosomePool() );
			StandardPostSelector nSelector = new StandardPostSelector( config );
			config.addNaturalSelector( nSelector, false);
		
		
		
			// Add our FBScan operator as the only operator
			FitnessBasedScanningOperator fbScan = new FitnessBasedScanningOperator( config );
			fbScan.setParents( fbCheck );
			config.addGeneticOperator( fbScan );
			
			
			
			// Test via the first DeJong Function (1 x 10 case)
			FitnessFunction test = new DeJongSphereFunction( 1, 10, true );
			config.setFitnessFunction( test );
			
			// Build a sample chromosome
			Gene[] sampleSequence = new Gene[ 10 ];
			
			// Put the needed sample gene at every point in the sequence 
			for( int i = 0; i < 10; i++ ){
				sampleSequence[i] = new BooleanGene( config ); 
			}
 		
 			Chromosome sampleChromosome = new Chromosome( config, sampleSequence );
 			config.setSampleChromosome( sampleChromosome );
		
		
		
			// Now we build a Genotype, which takes our sample Chromosome in the
			// Configuration and builds a full, randomized population of the indicated size
			Genotype population = Genotype.randomInitialGenotype( config );
			
			
			
			// Now evolve and print after each population
			for( int i = 0; i < 10; i++ ){
				UnitTest.printPop( population );
				population.evolve();
			}
	
		} catch( Exception e ){
			System.err.println( e.getMessage());
		}
		
	}
}