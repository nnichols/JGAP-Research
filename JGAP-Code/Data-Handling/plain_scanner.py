import sys, string, array

def main():
    
    # average average and average best fitness 
    avgAvg = 0.0 
    avgBest = 0.0
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
            
        # Extra t the values into a list
        values = line.split( ',' )
            
        # Create a running sum of the fitness values
        avgAvg += float( values[1] )
        avgBest += float( values[2] ) 
            
        
    # print averages for fitness values
    print( str(avgAvg / 100.0) )
    print( str(avgBest / 100.0) )
    
main()
