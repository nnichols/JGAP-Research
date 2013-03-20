import sys, string, array

def main():
    
    # Determine the best value found
	maxValue = 0.0
	
	# Array to store final values
	finals = [ 0.0 ] * 1100 
	arrayPtr = 0
	lc = 0
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
            
        # Extra t the values into a list
        values = line.split( ',' )
            
        # Determine if the current value is the max
        if( float( values[2] ) > maxValue ):
       
       		maxValue = float( values[2] ) 
        
        # update line count
        if( lc % 501 == 0 ):
        	
        	# Put last found value in the array
        	finals[ arrayPtr ] = float( values[2] )
        	arrayPtr++
         
        # keep track of our line number    
        lc = lc + 1
        
	# See how often each solution found the best solution
	for i in range( 11 ):
		
		bestFoundCtr = 0
		
		for j in range( 100 ):
    
    		cell = (i * 100) + j
    		
    		# if the best value was found, add 1 to our %
    		if( finals[cell] == maxValue ):
    			bestFoundCtr++
    			
    	print( str( bestFoundCtr ) )
    
main()