/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.somcluster;

import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author Gonza
 */
public class Matrix {
    
    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = Math.random();
        return C;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
    }

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return C = A^T
    public static double[][] transpose(double[][] A) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[j][i] = A[i][j];
        return C;
    }

    // return C = A + B
    public static double[][] add(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    // return C = A - B
    public static double[][] subtract(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        double[][] C = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    // return C = A * B
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] A, double[] x) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += A[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] A) {
        int m = A.length;
        int n = A[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += A[i][j] * x[i];
        return y;
    }
    
    //vector-vector multiplication (S = a * b)
    public static double[][] multiply(double[] column, double[] row){
        int columns = column.length;
        int rows = row.length;
        double[][] output = new double[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                output[i][j] = column[i] * row[j];
            }
        }
        return output;
    }
    
    //show all vector components
    public static void showVector(double[] values){
        for (int i = 0; i < values.length - 1; i++) {
            System.out.print(values[i] + "\t");
        }
        System.out.println(values[values.length - 1]);
    }
    
    //show all vector components
    public static String showVectorWithOutput(double[] values){
        return ArrayUtils.toString(values);
    }
    
    //show all matrix components
    public static void showMatrix(double[][] values){
        for (int i = 0; i < values.length; i++) {
            showVector(values[i]);
        }
    }
    
    public static String getVector(double[] values){
        String result = "";
         for (int i = 0; i < values.length - 1; i++) {
            result += values[i] + "\t";
        }
         result += values[values.length - 1];
        return result;
    }
    
    public static String getMatrix(double[][] values){
        String result = "";
         for (int i = 0; i < values.length; i++) {
            result += Matrix.getVector(values[i]) + "\n";
        }
        return result;
    }
    
    public static boolean equals(double[] a, double[] b){
        if (a == null) {
            throw new NullPointerException("first vector is null");
        }
        if (b == null) {
            throw new NullPointerException("second vector is null");
        }
        if (a.length != b.length) {
            throw new NullPointerException("Both vectors have differents dimensions");
        }
        boolean result = true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}



