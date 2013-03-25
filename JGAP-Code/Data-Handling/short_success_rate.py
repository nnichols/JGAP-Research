import sys, string, array

def main():
    
    # Line and array counters
    lc = 0
    cCtr = 0
    arrayIndex = 0

	# Looking for best solution thus far (minimum)
    bestFitness = 1000000.0
    
    # Arrays for fitness values, success rate, and convergence
    fitnessValues = [ 0.0 ] * 1500
    percents = [ 0 ] * 15
    
    # Read lines that have been piped in to find best fitness
    for line in sys.stdin.readlines():
            
        fitness = float( line )
            
        # store fitness for later processing
        fitnessValues[ arrayIndex ] = fitness
        arrayIndex += 1 
            
        # Update fitness if a new minimum has been found
        if( fitness < bestFitness ):
        	bestFitness = fitness

   
    # Go through each config for each line
    for i in range(15):
    	for j in range(100):
    	
    		curCell = (100 * i) + j
    		
    		# Current value matches best
    		if( fitnessValues[ curCell ] == bestFitness ):
    			percents[i] += 1
        
        # Print success percent
        print( str( percents[i] ) )
    
main()
