import sys, string, array

def main():
    
    # Linecounter
    lc = 0
    
    # Avg Convergence Gen
    conv = 0
    hasConv = False
    
    # runtime
    runtime = 0
    
    # Arrays for best fitness found
    avgBest = [ 0.0 ] * 100
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
        
        lc = lc + 1
        
        # Check if the line is a time or data line
        if( lc % 501 != 0 ):
            
            values = line.split( ',' )
            
            # Find the current generation
            generation = int( values[0] ) - 1
            
            if( lc % 501 == 500 ):
            
            	print( str( values[2] ) )
            
            # Determine convergence generation
            if( not hasConv ):
                if( 'C' in str( values[3] ) ):
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
        
    # print averages for runtime and convergence gen
    print( str( float( conv ) / 100) )
    
main()