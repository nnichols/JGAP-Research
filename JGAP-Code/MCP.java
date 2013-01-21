/* Nick Nichols - MCP.java
 *
 * Master Control Program for all JGAP testing
 *
 * 10/16 - Set up to test default settings and get a working configuration
 *         Using all defaults for a provided problem/function
 *
 * 1/20 - Works for all currently defined problems
 */
 
import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.event.*;
import org.jgap.util.*;
 
public class MCP{

	/* Here we define the size of the test population and generations
	 * to evolve for. These should be constant across trials for
	 * fairness and to reduce the amount of tested variables.
	 *
	 * These are consistent with the upper bounds used by Eiben's work
	 */
	private static final int POPULATION_SIZE = 200;
	private static final int GENERATIONS = 500;
	
	// Problem characteristics
	private static int diagCheck;
	private static int fbCheck;
	private static int esoCheck;
	private static int mutCheck;
	private static int problemNumber;
	private static int dimensions;
	private static int dimensionLength;
	private static String filePath;
		
		
	public static void main(String[] args) throws Exception{
			
		// Set up a Configuration, which drives evolution
		Configuration config = configurationSetup();
		
		// PULL DESCRIPTION FROM COMMAND LINE
		getOperators( args );
		
		/* Add the GeneticOperators
		 * For testing, we will add the default set. Later, this function will
		 * take a switching parameter to choose which operators we want
		 */
		
		// Add diagonalization operator based on CLA
		if( diagCheck > 0 ){
			DiagonalizationOperator xOver = new DiagonalizationOperator( config );
			xOver.setParents( diagCheck );
			
			if( problemNumber == 11 ){
				xOver.setPermutation( true );
			}
			
			config.addGeneticOperator( xOver );
		}
		
		// Add scanning operator based on CLA
		if( fbCheck > 0 ){
			FitnessBasedScanningOperator fbScan = new FitnessBasedScanningOperator( config );
			fbScan.setParents( fbCheck );
			
			if( problemNumber == 11 ){
				fbScan.setPermutation( true );
			}
			
			config.addGeneticOperator( fbScan );
		}
		
		// Add ESOs based on CLA
		if( esoCheck != 0 ){
			ElitistSchemaOverlayOperator eso = new ElitistSchemaOverlayOperator( config );
			eso.setGenerations( GENERATIONS );
			eso.setParents( esoCheck );
			
			// -1 means set to random k
			if( esoCheck == -1 ){
				eso.setMethod(1);
			
			// -2 means k is chosen by linear descent 
			} else if( esoCheck == -2 ){
				eso.setMethod(2);
				
			} else {
				eso.setMethod(0);
			}
			
			if( problemNumber == 11 ){
				eso.setPermutation( true );
			}
				
			config.addGeneticOperator( eso );
		}
		
		// Add mutation operator based on CLA
		// Enables dynamic mutation rate, which is roughly 1 / CHROM LENGTH
		// This matches theoretical results
		if( mutCheck > 0 ){
			MutationOperator mOperator = new MutationOperator( config );
			config.addGeneticOperator( mOperator );
		}

		// Set up the fitness function
		FitnessFunction currentProblem = getProblem( args, config );
		config.setFitnessFunction( currentProblem );
		

		// Now we build a Genotype, which takes our sample Chromosome in the
		// Configuration and builds a full, randomized population of the indicated size
		Genotype population = Genotype.randomInitialGenotype( config );
		
		
		/* Now we iterate for through our generations, or we can use the
		 * getFittestChromosome method on our population to check and
		 * determine when we have a value that passes our quality
		 * threshold. We will time this to see how expensive evaluating
		 * each operator is over the entire simulation.
		 *
		 * NOTE: Fitness values are smaller for better solutions
		 */
		long startTime = System.currentTimeMillis();
		
		for( int i = 0; i < GENERATIONS; i++ ){
			population.evolve();
		}
		
		long endTime = System.currentTimeMillis();
		
		
		// We'll grab the fittest individual to report upon
		IChromosome fittest = population.getFittestChromosome();
		
		
		// Report runtime values
		reportResult(fittest, (Reportable) currentProblem, endTime - startTime);
	}
	
	
	// Output information about the run
	public static void reportResult( IChromosome fittest, Reportable problem, long runTime ){
		
		problem.report( fittest );
  			      
  		System.out.println( "Runtime in Milliseconds: " + runTime );
	}
	
	
	// Set up the Configuration
	public static Configuration configurationSetup() throws Exception {
		
		Configuration config = new Configuration();
				
		// Population size to evolve
		config.setPopulationSize( POPULATION_SIZE );
		
		// Set up a breeder to run the evolutions
		config.setBreeder( new GABreeder() );
		
		// Set up a random value calculator
		config.setRandomGenerator( new StockRandomGenerator() );
		
		// Set up an EventManager for error reporting
		config.setEventManager( new EventManager() );
		
		// Enable elitism
		config.setPreservFittestIndividual( true );
		
		// Allow for population shrinkage/growth
		config.setKeepPopulationSizeConstant( false );
		
		// Set minimum thershold on population size
		config.setMinimumPopSizePercent( 0 );
		
		// Set up low-positive evaluation on fitness functions
		// (Minimization tasks)
		config.setFitnessEvaluator( new DeltaFitnessEvaluator() );
		
		//Build the Chromosome Pool to store the population
		config.setChromosomePool( new ChromosomePool() );
		
		// Add natural selector
		// boolean switch means this is processed after genetic operators
		StandardPostSelector nSelector = new StandardPostSelector( config );
		config.addNaturalSelector( nSelector, false);
				
		return config;
	}
	
	
	// Get problem and setup information
	public static void getOperators( String[] arguments ){
		
		// Is Diagonalization utilized?
		// 0 - FALSE
		// k - TRUE W/ K PARENTS
		diagCheck = Integer.parseInt( arguments[0] );
		
		// Is FB-Scanning utilized?
		// 0 - FALSE
		// k - TRUE W/ K PARENTS
		fbCheck = Integer.parseInt( arguments[1] );
		
		// Are Elitist Schema Overlays utilized?
		// 0 - FALSE
		// k - TRUE W/ K AS THE METHOD
		//     -1 - RANDOM
		//     -2 - LINEAR DESCENT
		//     +k - CONSTANT K
		esoCheck = Integer.parseInt( arguments[2] );
		
		// Is Mutation utilized?
		// 0 - FALSE
		// k - TRUE 
		mutCheck = Integer.parseInt( arguments[3] );
	}
	
	public static FitnessFunction getProblem( String[] arguments, Configuration config ){
		
		// PROBLEM # GUIDE 
		// 1 - DeJong's Hyper-Sphere Function
		// 2 - DeJong's Hyper-Ellipsoid Function
		// 3 - Sum Of Powers Function
		// 4 - Griewank Function
		// 5 - Rastrigin Function
		// 6 - Rosenbrock Function
		// 7 - Michaelwicz Function
		// 8 - Six-Hump Camel Function
		// 9 - Schubert Function
		// 10 - 1-0 Knapsack Problem
		// 11 - Travelling Salesperson Problem
		// 12 - NK-Landscape Minimization
		problemNumber = Integer.parseInt( arguments[4] );
		
		
		// Problem characteristics
		// 1 - 6: int dimensions, int dimensionLength
		// 7 - 9: int dimensionLength
		// 10 - 11: String filePath, int dimension
		// Extra parm for NP-Complete problems needed for generating appropriate
		// sample chromosomes
		if( problemNumber >= 1 && problemNumber <= 6 ){
			
			dimensions = Integer.parseInt( arguments[5] );
			dimensionLength = Integer.parseInt( arguments[6] );
			
		} else if( problemNumber >= 7 && problemNumber <= 9 ){
			
			dimensions = 2;
			dimensionLength = Integer.parseInt( arguments[5] );
			
		} else if(  problemNumber >= 10 && problemNumber <= 12 ){
			
			filePath = arguments[5];
			dimensions = Integer.parseInt( arguments[6] );
			dimensionLength = 1;
			
		} else {
			
			System.err.println( "Invalid command line arguments" );
			System.exit(0);
			
		}
		
		// Build the necessary fitness function for the problem
		FitnessFunction currentProblem = null;
		boolean isBooleanChrom = true;
		
		switch( problemNumber ){
			case 1:
				currentProblem = new DeJongSphereFunction( dimensions, dimensionLength, true );
				break;
			
			case 2:
				currentProblem = new DeJongEllipsoidFunction( dimensions, dimensionLength, true );
				break;
				
			case 3:
				currentProblem = new SumOfPowersFunction( dimensions, dimensionLength, true );
				break;
				
			case 4:
				currentProblem = new GriewankFunction( dimensions, dimensionLength, true );
				break;
				
			case 5:
				currentProblem = new RastriginFunction( dimensions, dimensionLength, true );
				break;
				
			case 6:
				currentProblem = new RosenbrockFunction( dimensions, dimensionLength, true );
				break;
				
			case 7:
				currentProblem = new MichaelwiczFunction( dimensionLength, true );
				break;
				
			case 8:
				currentProblem = new CamelFunction( dimensionLength, true );
				break;
				
			case 9:
				currentProblem = new SchubertFunction( dimensionLength, true );
				break;
				
			case 10:
				currentProblem = new KnapsackFunction( filePath );
				break;
				
			case 11:
				currentProblem = new TSPFunction( filePath );
				isBooleanChrom = false;
				break;
				
			case 12:
				currentProblem = new NKFunction( filePath );
				break;
				
			default:
				System.err.println("Invalid argument");
				System.exit(0);
				
		}

		// Build the matching Chromosome for our problem
		buildSampleChromosome( config, isBooleanChrom );
		
		return currentProblem;
	}


	// Build a sample genome so the configuration can produce a random
	// population for evolution for both Integer and Boolean Genomes
	public static void buildSampleChromosome( Configuration config, boolean areBooleans ){

		try{

			int size = dimensions * dimensionLength;
		  
			 /* The sample Chromosome needs a Gene[] to know how we
			  * want to split our genetic sequence up, and what types
			  * of genes will be used.
			  */
			Gene[] sampleSequence = new Gene[ size ];
			
			// Put the needed sample gene at every point in the sequence 
			for( int i = 0; i < size; i++ ){
				if( areBooleans ){
					sampleSequence[i] = new BooleanGene( config ); 
				} else {
					sampleSequence[i] = new IntegerGene( config, 0, size );
				}
			}
 		
 			Chromosome sampleChromosome = new Chromosome( config, sampleSequence );
 		
 			config.setSampleChromosome( sampleChromosome );

		} catch( Exception e ){
			System.err.println("Invalid argument");
			System.exit(0);
		}
 		
	}


}
