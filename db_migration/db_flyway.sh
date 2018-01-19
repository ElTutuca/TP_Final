#!/bin/bash

var1

if  [ $1 = "m" ]; then
    var1="migrate"

elif [ $1 = "c" ]; then
    var1="clean"

elif [ $1 = "i" ]; then
    var1="info"

elif [ $1 = "v" ]; then
    var1="validate"
    
elif [ $1 = "u" ]; then
    var1="undo"

elif [ $1 = "b" ]; then
    var1="baseline"
    
else
    var1="repair"
    
fi


flyway -configFiles=flyway.conf -locations=filesystem:. $var1
