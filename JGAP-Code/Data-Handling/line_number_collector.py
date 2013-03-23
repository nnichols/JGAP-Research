import sys, string, array

def main():
    
    # Read lines that have been piped in
    for line in sys.stdin.readlines():
            
        # Split after the line number 
        values = line.split( ':' )
        
        # print line number portion    
        print( str( values[0] ) )
    
main()