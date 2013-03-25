#!/bin/sh
#
# Request 10 hours for each task 
#PBS -l walltime=10:00:00
#
# Job name:
#PBS -N solution_np_collapse
#
# Email address for notifications
#PBS -M nnichols@iwu.edu
 
# change directory (to be certain we start in the right place)
cd /share/data/nnichols/jgap_experimentation/solution_quality

# Keep track of files processed to collapse by size
fCtr=0
fSize=0

# Condense all data to 1 file per problem instance 
for i in {9..309}
do 

	problem=$(($fCtr % 10))

	# run the right python script
	if [[ $problem -eq 0 ]] 
	then
		fSize=$(($fSize + 1))
	fi
	
	less problem_$i.csv | python ~/JGAP-Research/JGAP-Code/Data-Handling/short_success_rate.py >> np_$fSize.csv
	
	# Increment fCtr
	fCtr=$(($fCtr + 1))
	
	
done

# Collapse down into 1 record per configuration
for j in {1..30}
do

	less np_$j.csv | python ~/JGAP-Research/JGAP-Code/Data-Handling/short_avg_rate.py >> np_condensed_$j.csv

done