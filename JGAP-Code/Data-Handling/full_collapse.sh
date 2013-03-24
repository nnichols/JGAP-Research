#!/bin/sh
#
# Request 10 hours for each task 
#PBS -l walltime=10:00:00
#
# Job name: eso_solution_quality
#PBS -N eso_np_collapse
#
# Email address for notifications
#PBS -M nnichols@iwu.edu
 
# change directory (to be certain we start in the right place)
cd /share/data/nnichols/jgap_experimentation/eso_quality

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
	
	# Increment fCtr
	fCtr=$(($fCtr + 1))
	
	echo $fSize
	echo $fCtr
	
done
