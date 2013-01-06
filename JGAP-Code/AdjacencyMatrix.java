/* Nick Nichols - AdjacencyMatrix.java
 *
 * Class to store Travelling Salesman Problem instances
 * Note: We assume that all instances are symmetric 
 *       Also, a value of -1.0 means that no edge exists
 *
 */

public class AdjacencyMatrix {

	int cities, edges;
	double[][] weights;

	// Constructor
	public AdjacencyMatrix( int problemSize ){
		this.cities = problemSize;
		this.edges = 0;
		
		weights = new double[cities][cities];

		// Fill each weight with -1.0
		for( int i = 0; i < cities; i++ ){
			for( int j = 0; j < cities; j++ ){

				weights[i][j] = -1.0;
			}
		}
	}

	
	// Adds an edge between two cities (symmetric)
	public void addEdge( int city1, int city2, double pathWeight ){
		this.weights[city1][city2] = pathWeight;
		this.weights[city2][city1] = pathWeight;
	}

	
	// Get the weight of a given edge
	public double getWeight( int city1, int city2 ){
		return weights[city1][city2];
	}
}
