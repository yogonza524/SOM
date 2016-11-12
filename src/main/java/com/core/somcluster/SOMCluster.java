/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.somcluster;

import java.util.Arrays;
import java.util.Random;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Gonza
 */
public class SOMCluster {

    private final int EPOCHS;
    private final NeighborhoodFunction h;
    private final double[][] W;
    private final int COUNT_CLUSTERS;
    private final int DIMENSION;
    private boolean UPDATE_NEIGHBORHOOD;
    private String trainingLOG;

    public String getTrainingLOG() {
        return trainingLOG;
    }

    public SOMCluster(int EPOCHS, int DIMENSION, int COUNT_CLUSTERS, NeighborhoodFunction h) {
        this.EPOCHS = EPOCHS;
        this.h = h;
        this.DIMENSION = DIMENSION;
        this.COUNT_CLUSTERS = COUNT_CLUSTERS;
        this.W = new double[COUNT_CLUSTERS][DIMENSION];
        this.initRandomWeights();
        UPDATE_NEIGHBORHOOD = true;
    }

    public SOMCluster(int EPOCHS, NeighborhoodFunction h, double[][] W) {
        this.EPOCHS = EPOCHS;
        this.h = h;
        this.W = W;
        this.COUNT_CLUSTERS = W.length;
        this.DIMENSION = W[0].length;
        UPDATE_NEIGHBORHOOD = true;
    }
    
    private void initRandomWeights(){
        Random r = new Random();
        for (int i = 0; i < COUNT_CLUSTERS; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                this.W[i][j] = r.nextDouble();
            }
        }
    }
    
    public double[][] train(double[][] patterns){
        int COUNT_EPOCH = 1;
        this.trainingLOG = "Entrenando la Red";
        this.trainingLOG += "\nPesos iniciales de la red: ";
        
        for (int i = 0; i < this.W.length; i++) {
            this.trainingLOG += "\nPeso " + i + ": " + ArrayUtils.toString(this.W[i]); 
        }
//        
//        this.trainingLOG += "\n---------------------------------------------------------";
        
        while(COUNT_EPOCH < this.EPOCHS + 1){
            this.trainingLOG += "\n-----------------------------------------------------";
            this.trainingLOG += "\nEpoca: " + COUNT_EPOCH;
            for (int i = 0; i < patterns.length; i++) {
                double[] distances = new double[this.W.length];
                
                this.trainingLOG += "\n-----------------------------------------------------";
                this.trainingLOG += "\nPatron: " + Arrays.toString(patterns[i]);
                this.trainingLOG += "\n";
                
                for (int j = 0; j < this.COUNT_CLUSTERS; j++) {
                    distances[j] = this.distance(W[j], patterns[i]);
                    this.trainingLOG += "\nDistancia a peso " + j + ": " + distances[j];
                }
                int small = this.smallest(distances);
                
                this.trainingLOG += "\n\nMatriz de Pesos\n\n";
                this.trainingLOG += Matrix.getMatrix(this.W);
                this.trainingLOG += "\n";
                //Update the winner
                for (int j = 0; j < this.DIMENSION; j++) {
                    this.W[small][j] = this.W[small][j] + (1 / COUNT_EPOCH)* 
                            (patterns[i][j] - this.W[small][j]);
                }
                
                this.trainingLOG += "\nActualizado: " + small + " Peso: " + Arrays.toString(W[small]);
                
                //Update losers
                if (this.UPDATE_NEIGHBORHOOD) {
                    for (int j = 0; j < patterns.length; j++) {
                        for (int k = 0; k < this.W.length; k++) {
                            if (k != small) {
                                for (int l = 0; l < this.DIMENSION; l++) {

                                    double d = this.h.calculate(this.W[k], this.W[small]);
                                    double error = (patterns[j][l] - this.W[small][l]);

                                    this.W[k][l] = this.W[k][l] + (1 / COUNT_EPOCH)*
                                           d * error;
                                }
                            }
                        }
                    }
                }
            }
            
            COUNT_EPOCH++;
        }
        this.trainingLOG += "\n---------------------------------------------------------";
        this.trainingLOG += "\nPesos estabilizados";
        for (int i = 0; i < this.W.length; i++) {
            this.trainingLOG += "\nPeso " + i + ": " + ArrayUtils.toString(this.W[i]); 
        }
        return this.W;
    }
    
    public int test(double[] pattern){
        double[] distances = new double[this.W.length];
        for (int i = 0; i < this.W.length; i++) {
            distances[i] = this.distance(this.W[i], pattern);
        }
        return this.smallest(distances);
    }
    
    public int smallest(double[] distances){
         int output = -1;
         double minor = Double.MAX_VALUE;
         for (int i = 0; i < distances.length; i++) {
             if (distances[i] < minor) {
                 minor = distances[i];
                 output = i;
             }
         }
         return output;
     }
    
    public double distance(double[] w, double[] point){
         double sum = 0.0;
         for (int i = 0; i < w.length; i++) {
             sum += Math.pow(w[i] - point[i], 2);
         }
         return Math.sqrt(sum);
     }
    
    public static class SOMClusterBuilder{
        private int EPOCHS;
        private NeighborhoodFunction h;
        private double[][] W;
        private int COUNT_CLUSTERS;
        private int DIMENSION;
        private boolean UPDATE_NEIGHBORHOOD;
        
        public SOMClusterBuilder epochs(int epochs){
            this.EPOCHS = epochs;
            return this;
        }
        public SOMClusterBuilder neighborhoodFunction(NeighborhoodFunction h){
            this.h = h;
            return this;
        }
        public SOMClusterBuilder weights(double[][] W){
            this.W = W;
            return this;
        }
        public SOMClusterBuilder clusters(int COUNT_CLUSTERS){
            this.COUNT_CLUSTERS = COUNT_CLUSTERS;
            return this;
        }
        public SOMClusterBuilder dimension(int dimension){
            this.DIMENSION = dimension;
            return this;
        }
        public SOMClusterBuilder updateNeighborhood(boolean UPDATE_NEIGHBORHOOD){
            this.UPDATE_NEIGHBORHOOD = UPDATE_NEIGHBORHOOD;
            return this;
        }
        public SOMCluster build(){
            SOMCluster output;
            if (this.W != null) {
                output = new SOMCluster(EPOCHS, h, W);
            }
            else{
                output = new SOMCluster(EPOCHS, DIMENSION, COUNT_CLUSTERS, h);
            }
            output.UPDATE_NEIGHBORHOOD = this.UPDATE_NEIGHBORHOOD;
            return output;
        }
        
    }
    
}
