numOne=10
numTwo=15

echo $numOne + $numTwo # this will print "10 + 15" to the console, it won't actually do our math

echo $((numOne + numTwo)) # wrap the variables in double (()) and put the $ outside of it if you want to perform basic calculations and return the results

sum=$((numOne + numTwo)) # this saves the result of the operation to the variable sum

echo $sum # don't forget to add the $

echo ""
echo "floating point content below"
echo ""

floatingValueOne=10.2
floatingValueTwo=3.7

# echo $((floatingValueOne + floatingValueTwo)) this will fail because bash does not support floating point values
# An easy way to get floating point values is to use the bc command alongside an echo command to pass the relevant data into the bc command
# scale tells bc how many decimal places to calculate, and then you provide the equation you want calculated
result=$(echo "scale=1; $floatingValueOne + $floatingValueTwo" | bc)
echo $result

echo
echo "looping through range below"
echo

for num in {0..10..2} # {starting number inclusive..ending number inclusive..increment amount}
do
        echo $num
done

echo
echo "looping through text content in a file below"
echo

story=$(cat story-file)

IFS=$'\n' # surround the value you want to use as a delineator with $''
for line in $story
do
        echo $line
done

echo
echo "looping via a while loop"
echo

controlVariable=0
while [ $controlVariable -lt 11 ]
do
        echo $controlVariable
        ((controlVariable++)) # notice I don't need a dollar sign for this
done

echo
echo "showing off until loop"
echo

until [ $controlVariable -eq 21 ]
do
        echo $controlVariable
        ((controlVariable++))
done
