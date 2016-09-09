

Step1: Place the given data file on cluster or you can use the same directory that has been put in .scala file if it is accessible

Step2: If the data file placed some other place on the cluster, update the directory in all .scala files 

step3: put all .scala files in your local directory on the server.

Step4: Go to spark shell by command 'spark-shell'

step5: Execute the .scala files by following commands

:load linregwithpca.scala
:load linregwithoutpca.scala
:load decisiontreewithpca.scala
:load decisiontreewithoutpca.scala
:load randomforestwithpca.scala
:load randomforestwithoutpca.scala
:load boostingwithpca.scala
:load boostingwithoutpca.scala

