#!/bin/sh
#
# Request 1 hour for each task
#PBS -l walltime=01:00:00
#
# Job name: solution_quality
#PBS -N solution_data_reduction
#
# Email address for notifications
#PBS -M nnichols@iwu.edu
#
# Set up a job array with 4635 jobs
#PBS -t 1-4635
 
# change directory (to be certain we start in the right place)
cd /share/data/nnichols/jgap_experimentation/solution_quality
 
# Build a file that lists each of the ".out"s line by line
ls -a *.out > ~/JGAP-Research/solution_filelist.txt
 
# extract a single benchmark from a file containing benchmark paths (based on our array id)
instance=$(sed -n "${PBS_ARRAYID}"p ~/JGAP-Research/solution_filelist.txt)

# run the python script
less $instance | python ~/JGAP-Research/JGAP-Code/Data-Handling/plain_scanner.py > $instance.csv
