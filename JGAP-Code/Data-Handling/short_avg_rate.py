import sys, string, array

def main():
    
    # Line and config counter
    lc = 0
    config = 0
    
    # success percents 
    successRate = [ 0.0 ] * 15
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
        
        # Sum up success rates
        successRate[ (config % 15) ] += float( line )
        config += 1
        lc += 1
            
	# Print data
    for i in range( 15 ):
        print( str( successRate[i] / 10 ))      
     
main()
