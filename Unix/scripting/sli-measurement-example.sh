# the goal of this script is to simulate getting the necessary content of the log file and measuring our SLIs based on the relevant information
# NOTE: as is, this will not work for your log files: you will need to change the variables/files you perform these actions on to match with your
# own files


# To do this, we need to filter out the unneeded information and then loop through the proper info to check our metrics


# first you need to parse out the relevant data from your logs
httpCodes=$(grep Status fake-logs.log | cut -f 4 -d , | cut -f 2 -d :)

# then create any necessary variables to measure your SLI
httpRequestTotal=0
httpFailures=0


# then loop through the log data and perform any necessary operations to initialize your variables
for code in $httpCodes
do
        ((httpRequestTotal++))
        if [ $code -eq 500 ]
        then
                ((httpFailures++))
        fi
done

# save the SLI value and return it

httpSuccess=$(($httpRequestTotal - $httpFailures))

result=$(echo "scale=2; $httpSuccess / $httpRequestTotal" | bc) # this option might not be available via gitbash on your local computer

result=$(awk "BEGIN {print $httpSuccess / $httpRequestTotal * 100; exit}") # this is an alternative way to get the same result as above if bc does not work

echo "HTTP success rate: $result%"
