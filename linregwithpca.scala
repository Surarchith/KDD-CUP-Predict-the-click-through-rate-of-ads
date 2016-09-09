import org.apache.spark.mllib.feature.PCA
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionModel
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.feature.PCA
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD

val data1=sc.textFile("hdfs://cshadoop1/user/axk155630/trainsample.txt").map(line=>line.split("\\t").map(_.toDouble)).map(line=>(line(2),line(3),line(4),line(5),line(6),line(7),line(8),line(9),line(10),line(11),(line(0)/line(1))))


val parsedData = data1.map { parts =>
  LabeledPoint(parts._11.toDouble, Vectors.dense(parts._1.toDouble,parts._2.toDouble,parts._3.toDouble,parts._4.toDouble,parts._5.toDouble,parts._6.toDouble,parts._7.toDouble,parts._8.toDouble,parts._9.toDouble,parts._10.toDouble))}.cache()

val pca = new PCA(8).fit(parsedData.map(_.features))

val projected = parsedData.map(p => p.copy(features = pca.transform(p.features)))
  
val splits = projected.randomSplit(Array(0.6, 0.4), seed = 11L)
val training = splits(0).cache()
val test = splits(1)
val numIterations = 20
val stepSize = 1.00E-60
val model = LinearRegressionWithSGD.train(training, numIterations, stepSize)
val valuesAndPredstrain = training.map { point =>
  val prediction = model.predict(point.features)
  (point.label, prediction)
}
val MSE1 = valuesAndPredstrain.map{case(v, p) => math.pow((v - p), 2)}.mean()

