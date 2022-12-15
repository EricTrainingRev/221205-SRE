numOne=10
numTwo=10

if [ $numOne -gt $numTwo ]
then
        echo "numOne is greater than numTwo"
elif [ $numOne -eq $numTwo ]
then
        echo "numOne and numTwo are the same"
else
        echo "numTwo is greater than numOne"
fi
