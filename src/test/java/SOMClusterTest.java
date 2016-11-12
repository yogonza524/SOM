/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.core.somcluster.HyperbolicFunction;
import com.core.somcluster.Matrix;
import com.core.somcluster.SOMCluster;
import com.core.somcluster.SOMCluster.SOMClusterBuilder;
import com.core.somcluster.UnitFunction;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonza
 */
public class SOMClusterTest {
    
    public SOMClusterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void first() {
         
        double[][] weigths = new double[][]{
            {0.5,-0.3},{-0.5,0.8},{-0.9,-0.7},{-0.2,0.8}  
        };
        
        double[][] trainingPatterns = new double[][]{
             {-0.54,0.36},{0.16,0.70},{-0.80,-0.18},{-0.36,-0.52},{-0.64,0.46},{-0.40,0.34},{-0.54,0.36}  
         };
        
        double[][] test = new double[][]{
            {-0.88,0.82},{-0.38,-0.58},{0.48,0.18},{0.68,-0.7}  
        };
         
        SOMCluster som = new SOMCluster.SOMClusterBuilder()
                .clusters(4)
                .epochs(10)
                .dimension(2)
                .neighborhoodFunction(new HyperbolicFunction())
                .updateNeighborhood(true)
                .weights(weigths)
                .build();
        
        double[][] weightsTrain = som.train(trainingPatterns);
        
        Matrix.showMatrix(weightsTrain);
        int n = 2;
        System.out.println("\nTest example: " + Arrays.toString(test[n]));
        int classification = som.test(test[n]);
        System.out.println("Cluster classification: " + classification + ". Class: " + Arrays.toString(weightsTrain[classification]));
        
//        System.out.println(som.getTrainingLOG());
     }
}
