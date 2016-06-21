/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.somcluster;

/**
 *
 * @author Gonza
 */
public class HyperbolicFunction implements NeighborhoodFunction{

    @Override
    public double calculate(double[] w1, double[] w2) {
        double dist = 0.0;
        for (int i = 0; i < w1.length; i++) {
            dist += Math.pow(w1[i] - w2[i], 2);
        }
        dist = Math.sqrt(dist);
        return (1 / (1 + dist));
    }
    
}
