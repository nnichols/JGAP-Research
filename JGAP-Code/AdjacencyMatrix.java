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
		edges++;
	}

	
	// Get the weight of a given edge
	public double getWeight( int city1, int city2 ){
		city1 = (city1 == cities) ? (city1 - 1) : city1;
		city2 = (city2 == cities) ? (city2 - 1) : city2;
		return weights[city1][city2];
	}
	
	// Get the number of edges
	public int getEdges(){
		return edges;
	}
	
	// Check if an edge between two given nodes exists
	public boolean hasEdge( int city1, int city2 ){
	
		if( weights[city1][city2] != -1 ){
			return true;
		} else {
			return false;
		}
		
	}
	
	// Find the degree of a node
	public int getDegree( int target ){
		
		int degree = 0;
		
		for( int i = 0; i < cities; i++ ){
			if( weights[target][i] != -1.0 ){
				degree++;
			}
		}
		
		return degree;
	}
}
