import sys, string, array

def main():
    
    # Line counter
    lc = 0
    
    # average average and average best fitness 
    Avg = [ 0.0 ] * 100 
    Best = [ 0.0 ] * 100
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
            
        # Extra t the values into a list
        values = line.split( ',' )
            
        # Store fitness values
        Avg[ lc ] = float( values[1] )
        Best[ lc ] = float( values[2] ) 
        
        lc += 1
            
        
    # print values
    for i in range( 100 ):
        print( str( Best[i] ) )
    
main()
