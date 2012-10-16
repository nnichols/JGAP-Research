/* Nick Nichols - MCP.java
 *
 * Master Control Program for all JGAP testing
 *
 * 10/16 - Set up to test default settings and get a working configuration
 *         Using all defaults for a provided problem/function
 */
 
import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.event.*;
import org.jgap.util.*;
 
public class MCP{

	/* Here we define the size of the test population and generations
	 * to evolve for. These should be constant across trials for
	 * fairness and to reduce the amount of tested variables.
	 */
	private static final int POPULATION_SIZE = 500;
	private static final int GENERATIONS = 100;
		
		
	public static void main(String[] args) throws Exception{
	
		// Set up a Configuration, which drives evolution
		Configuration config = configurationSetup();
		
		
		// Population size to evolve
		config.setPopulationSize( POPULATION_SIZE );
		
		
		// Providing an arbitrary test amount for the MinimizingMakeChange problem
		int changeTarget = 37;
		
		
		// Import the fitness function and target it to our amount
		// 		The FitnessFunction sub-class we pass along will contain problem details
		FitnessFunction currentProblem = new MinimizingMakeChangeFitnessFunction( changeTarget );
		
		
		// Pass the fitness function to our configuration
		config.setFitnessFunction( currentProblem );
		
		
		/* To get the random initial population set up, we have to build
		 * a sample Chromosome and give that to the Configuration. 
		 * 
		 * The sample Chromosome needs a Gene[] to know how we
		 * want to split our genetic sequence up, and what types
		 * of genes will be used.
		 */
		Gene[] sampleSequence = new Gene[ 4 ];
		 
		sampleSequence[0] = new IntegerGene( config, 0, 3 );  // Quarters
 		sampleSequence[1] = new IntegerGene( config, 0, 2 );  // Dimes
 		sampleSequence[2] = new IntegerGene( config, 0, 1 );  // Nickels
 		sampleSequence[3] = new IntegerGene( config, 0, 4 );  // Pennies
 		
 		Chromosome sampleChromosome = new Chromosome( config, sampleSequence );
 		
 		config.setSampleChromosome( sampleChromosome );
 		
		
		// Now we build a Genotype, which takes our sample Chromosome in the
		// Configuration and builds a full, randomized population of the indicated size
		Genotype population = Genotype.randomInitialGenotype( config );
		
		
		/* Now we iterate for through our generations, or we can use the
		 * getFittestChromosome method on our population to check and
		 * determine when we have a value that passes our quality
		 * threshold. We will time this to see how expensive evaluating
		 * each operator is over the entire simulation.
		 *
		 * NOTE: Fitness values are larger for better solutions
		 */
		long startTime = System.currentTimeMillis();
		
		for( int i = 0; i < GENERATIONS; i++ ){
			population.evolve();
		}
		
		long endTime = System.currentTimeMillis();
		
		
		// We'll grab the fittest individual to report upon
		IChromosome fittest = population.getFittestChromosome();
		
		
		// Report runtime values
		report(fittest, endTime - startTime);
	}
	
	
	// Output information about the run
	public static void report( IChromosome fittest, long runTime ){
		
		System.out.println( "The best solution contained the following: " );

		System.out.println(
  			  MinimizingMakeChangeFitnessFunction.getNumberOfCoinsAtGene(
  			      fittest, 0 ) + " quarters." );

			System.out.println(
			    MinimizingMakeChangeFitnessFunction.getNumberOfCoinsAtGene(
			        fittest, 1 ) + " dimes." );

			System.out.println(
			    MinimizingMakeChangeFitnessFunction.getNumberOfCoinsAtGene(
			        fittest, 2 ) + " nickels." );

			System.out.println(
			    MinimizingMakeChangeFitnessFunction.getNumberOfCoinsAtGene(
			        fittest, 3 ) + " pennies." );

			System.out.println( "For a total of " +
			    MinimizingMakeChangeFitnessFunction.amountOfChange(
  			      fittest ) + " cents in " +
 			   MinimizingMakeChangeFitnessFunction.getTotalNumberOfCoins(
  			      fittest ) + " coins." );
  			      
  			System.out.println( "Runtime in Milliseconds: " + runTime );
	}
	
	
	// Set up the Configuration
	public static Configuration configurationSetup() throws Exception {
		
		Configuration config = new Configuration();
		
		// Set up a breeder to run the evolutions
		config.setBreeder( new GABreeder() );
		
		// Set up a random value calculator
		config.setRandomGenerator( new StockRandomGenerator() );
		
		// Set up an EventManager for error reporting
		config.setEventManager( new EventManager() );
		
		// Enable elitism
		config.setPreservFittestIndividual( true );
		
		// Allow for population shrinkage/growth
		config.setKeepPopulationSizeConstant( true );
		
		// Set minimum thershold on population size
		config.setMinimumPopSizePercent( 0 );
		
		// Set up the high evaluation on fitness functions
		config.setFitnessEvaluator( new DefaultFitnessEvaluator() );
		
		//Build the Chromosome Pool to store the population
		config.setChromosomePool( new ChromosomePool() );
		
		
		
		/* Add the GeneticOperators and NaturalSelectors.
		 * For testing, we will add the default set. Later, this function will
		 * take a switching parameter to choose which operators we want
		 */
		 
		// Add natural selector
		// boolean switch means this is processed after genetic operators
		StandardPostSelector nSelector = new StandardPostSelector( config );
		config.addNaturalSelector( nSelector, false);
		 
		// Add crossover operator
		CrossoverOperator xOver = new CrossoverOperator( config );
		config.addGeneticOperator( xOver );
		 
		// Add mutation operator
		MutationOperator mOperator = new MutationOperator( config );
		config.addGeneticOperator( mOperator );
		
		
		return config;
	}
}