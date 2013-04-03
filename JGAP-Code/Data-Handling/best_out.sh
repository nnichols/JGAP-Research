#!/bin/sh
#
# Request 1 hour for each task 
#PBS -l walltime=1:00:00
#
# Job name: best_quality
#PBS -N best_quality
#
# Email address for notifications
#PBS -M nnichols@iwu.edu
 
# change directory (to be certain we start in the right place)
cd /share/data/nnichols/jgap_experimentation/best_eso_quality

# Condense all data to 1 file per problem instance 
for i in {1..4635}
do 

	problem=$((($i - 1) % 309))

	# run the right python script
	if [[ $problem -lt 9 ]] 
	then
		less solution_$i.out | python ~/JGAP-Research/JGAP-Code/Data-Handling/plain_scanner.py >> problem_$problem.csv
	else
		less solution_$i.out | python ~/JGAP-Research/JGAP-Code/Data-Handling/plain_np_scanner.py >> problem_$problem.csv
	fi
done
