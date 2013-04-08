import sys, string, array

def main():
    
    # Line and array counters
    lc = 0
    cCtr = 0
    arrayIndex = 0

	# Looking for best solution thus far (minimum)
    bestFitness = 1000000.0
    
    # Arrays for fitness values, success rate, and convergence
    fitnessValues = [ 0.0 ] * 1100
    percents = [ 0.0 ] * 11
    conv = [ 0.0 ] * 11
    
    # Read lines that have been piped in to find best fitness
    for line in sys.stdin.readlines():
        
        lc = lc + 1
        
        # Check if the line is a time or data line
        if( lc % 101 != 0 ):
            
            fitness = float( line )
            
            # store fitness for later processing
            fitnessValues[ arrayIndex ] = fitness
            arrayIndex += 1 
            
            # Update fitness if a new minimum has been found
            if( fitness < bestFitness ):
            	bestFitness = fitness

        else:
        
            # Store generations to convergence
            conv[ cCtr ] = float(line ) 
            cCtr += 1
    
    # Go through each config for each line
    for i in range(11):
    	for j in range(100):
    	
    		curCell = (100 * i) + j
    		
    		
    		# Find percent difference of found solution to best so far solution
    		percents[i] += fitnessValues[ curCell ] / bestFitness
        
        # Print success percent
        percents[i] = percents[i] / 100.0
        
        print( str( percents[i] ) )
        
   		# print avg convergence gen
        print( str( conv[i] ) )
    
main()
