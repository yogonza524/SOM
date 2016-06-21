# Kohonen Network
A self-organizing map (SOM) or self-organising feature map (SOFM) is a type of artificial neural network (ANN) that is trained using unsupervised learning to produce a low-dimensional (typically two-dimensional), discretized representation of the input space of the training samples, called a map. Self-organizing maps are different from other artificial neural networks as they apply competitive learning as opposed to error-correction learning (such as backpropagation with gradient descent), and in the sense that they use a neighborhood function to preserve the topological properties of the input space.
<img src="http://www.lohninger.com/helpcsuite/img/kohonen1.gif" />
# Creating the network
```java
SOMCluster som = new SOMCluster.SOMClusterBuilder()
                .clusters(4) //four output classes
                .epochs(10)  //Number of epochs represents the iterations till to stabilize the network
                .dimension(2) //Setup the input dimension
                .neighborhoodFunction(new HyperbolicFunction()) //Create the neighbor function
                .updateNeighborhood(true) //active or desactive neighbor compute
                .build(); //build de Network
```
You can set the initial weigths for the network like this
```java
double[][] weigths = new double[][]{
            {0.5,-0.3},{-0.5,0.8},{-0.9,-0.7},{-0.2,0.8}  
        };

SOMCluster som = new SOMCluster.SOMClusterBuilder()
                .weights(weigths)
                ....
```
# Train the Network
```java
double[][] trainingPatterns = new double[][]{
        {-0.54,0.36},{0.16,0.70},{-0.80,-0.18},{-0.36,-0.52},{-0.64,0.46},{-0.40,0.34},{-0.54,0.36}  
    };
som.train(trainingPatterns); //Train the network
```
# Test an unknown pattern
After training you can test every pattern like this
```java
int classification = som.test(new double[]{0.75,-0.01});
System.out.println("Cluster classification: " + classification + ". Class: " + 
      Arrays.toString(weightsTrain[classification]));
      //Cluster classification: 0. Class: [0.8217283114120794, 0.3856820501664513]
```
# Show the training 
You can check all data training
```java
System.out.println(som.getTrainingLOG()); //Show every data in training mode
```
# Neighborhood functions
You can use the next function includes in the library
* UnitFunction: for avoid the neighbor, by default calcule is 1
* HyperbolicFunction: calcule the inverse of distance between two neurons of networks

You can use your own neighbor function just implmenting the interface NeighborhoodFunction like this
```java
public class MyOwnFunction implements NeighborhoodFunction{
    
    public double calculate(double[] w1, double[] w1){
        //TODO 
        return w1[0] - w2[0];  //for example, modify like you want
    }
}
```
# Import to your project
```xml
<repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
</repository>
<dependency>
      <groupId>com.github.yogonza524</groupId>
      <artifactId>SOM</artifactId>
      <version>1.0.0</version>
</dependency>
```
# Licence
MIT
