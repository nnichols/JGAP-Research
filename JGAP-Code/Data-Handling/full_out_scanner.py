import sys, string, array

def main():
    
    # Linecounter
    lc = 0
    
    # Avg Convergence Gen
    conv = 0
    hasConv = False
    
    # runtime
    runtime = 0
    
    # Arrays for avg fitness / generation
    avgAvg = [ 0.0 ] * 500 
    avgBest = [ 0.0 ] * 500
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
        
        lc = lc + 1
        
        # Check if the line is a time or data line
        if( lc % 501 != 0 ):
            
            # Extra t the values into a list
            values = line.split( ',' )
            
            # Find the current generation
            generation = int( values[0] ) - 1
            
            # Create a running sum of the fitness values
            avgAvg[ generation ] += float( values[1] )
            avgBest[ generation ] += float( values[2] ) 
            
            # Determine convergence generation
            if( not hasConv and values[3] == "C" ):
                conv += generation
                hasConv = True
                
        else:
        
            # Get the runtime
            runtime += int( line )
            
            # Manipulate conv if the population never converged
            if( not hasConv ):
                conv += 500
            
            # Reset convergence checker
            hasConv = False
            
    for i in range(500):
        
        # Print averages for avgAvg and avgBest fitness
        avgAvg[ i ] = avgAvg[i] / 100.0
        avgBest[ i ] = avgBest[i] / 100.0
        
        # Build a printable line
        printLine = str(i) + ',' + str(avgAvg[i]) + ',' + str(avgBest[i])
        
        # Send info to stdout
        print( printLine )
        
    # print averages for runtime and convergence gen
    print( str(runtime / 100) )
    print( str(conv / 100) )
    
main()
