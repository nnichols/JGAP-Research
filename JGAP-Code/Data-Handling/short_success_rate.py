import sys, string, array

def main():
    
    # Line and array counters
    lc = 0
    cCtr = 0
    arrayIndex = 0

	# Looking for best solution thus far (minimum)
    bestFitness = 1000000.0
    
    # Arrays for fitness values, success rate, and convergence
    fitnessValues = [ 0.0 ] * 3000
    percents = [ 0.0 ] * 30
    
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
    for i in range(30):
    	for j in range(100):
    	
    		curCell = (100 * i) + j
    		
    		# Find percent difference of found solution to best so far solution
    		percents[i] += fitnessValues[ curCell ] / bestFitness
        
        # Print success percent
        percents[i] = percents[i] / 100.0
        print( str( percents[i] ) )
    

main()
