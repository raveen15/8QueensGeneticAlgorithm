import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class genetic {

    public static int[][] initializePopulation(){
        int row = 8;
        int column = 8;
        int[][] population = new int[row][column];
        for(int x = 0; x < population.length; x++){
            for(int y = 0; y < population[x].length; y++){
                population[x][y] = (int)(Math.random()*7);
            }
        }
        return population;
    }

    // public static int[][] initializeNextGeneration(int[] childOne, int[] childTwo){
    //     int row = 8;
    //     int column = 8;
    //     int[][] population = new int[row][column];
    //     for(int x = 0; x < population.length - 2; x++){
    //         for(int y = 0; y < population[x].length; y++){
    //             population[x][y] = (int)(Math.random()*7);
    //         }
    //     }

    //     for(int x = 0; x < 8; x++){
    //         population[6][x] = childOne[x];
    //     }
    //     for(int x = 0; x < 8; x++){
    //         population[7][x] = childTwo[x];
    //     }

    //     return population;
    // }

    public static int[] fitness(int[][] pop) {
        int[][] populationArray = pop;
        int[] fitnessArray = new int[8];
        
        for(int x = 0; x < 8; x++){
            int count = 0;
            for(int y = 0; y < 8; y++){
                for(int z = 0; z < 8; z++){
                    if(y != z){  
                        if(populationArray[x][y] == populationArray[x][z]){
                            count++;
                        }
                        if((Math.abs(y-z)) == (Math.abs(populationArray[x][y]-populationArray[x][z]))){
                            count++;
                        }
                    }
                }
                
            }
            fitnessArray[x] = count;
        }
        return fitnessArray;
    }

    // public static float[] rouletteWheelFunction(int[] fitnessArray){
    //     int total = 0;
    //     float[] probabilityArray = new float[8];

    //     for(int x = 0; x < fitnessArray.length; x++){
    //         total += fitnessArray[x];
    //     }

    //     // System.out.println("Total: " + total);

    //     for(int x = 0; x < probabilityArray.length; x++){
    //         probabilityArray[x] = (float) fitnessArray[x] / total * 100;
    //     }

    //     return probabilityArray;
    // }

    public static int parentIndex(int[] fit){
        int sum =0;
        float psum = 0;

        int index = 0;

        float[] prob = new float[8];
        float[] nprob = new float[8];
        float[] xprob = new float[8];

        for (int x = 0; x< 8;x++){
            sum += fit[x];
        }

        for(int x = 0;x < 8;x++){
            prob[x] =  (float)  fit[x] / sum ;
        }

        for(int x = 0;x < 8;x++){
            nprob[x] = (1 - prob[x]) / (8 - 1);
        }

        for(int x = 0; x <8;x++){
            psum += nprob[x];
            xprob[x] = psum;
        }

        float d = (float) Math.random();

        if (d <= xprob[0]){
            index = 0;
        }
        else if (xprob[0] < d & d <= xprob[1]){
            index = 1;
        }
        else if (xprob[1] < d & d <= xprob[2]){
            index = 2;
        }
        else if (xprob[2] < d & d <= xprob[3]){
            index = 3;
        }
        else if (xprob[3] < d & d <= xprob[4]){
            index = 4;
        }
        else if (xprob[4] < d & d <= xprob[5]){
            index = 5;
        }
        else if (xprob[5] < d & d <= xprob[6]){
            index = 6;
        }
        else {
            index = 7;
        }
        return index;
    }

    public static boolean fitnessCheck(int[] fitnessArray){
        boolean check = false;
        for(int x = 0; x < 8; x++){
            if(fitnessArray[x] == 0){
                check = true;
            }
        }
        return check;
    }

    public static int[][] parentSelection(int[] fit, int[][] population){
        int[][] parentsChromosomes = new int[2][8];
        int parentIndexOne = parentIndex(fit);
        int parentIndexTwo = parentIndex(fit);

        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 8; y++){
                parentsChromosomes[x][y] = population[parentIndexOne][y]; 
            }
        }

        for(int x = 1; x < 2; x++){
            for(int y = 0; y < 8; y++){
                parentsChromosomes[x][y] = population[parentIndexTwo][y]; 
            }
        }
        return parentsChromosomes;
    }

    public static int getFitnessIndex(int[] fitnessArray, int fitnessValue){
        int fitnessIndex = 0;

        for(int x = 0; x < 8; x++){
            if(fitnessArray[x] == fitnessValue){
                fitnessIndex = x;
            }
        }
        return fitnessIndex;
    }

    public static int[][] childCreation(int[] p1, int[] p2){

        int[][] childSet = new int[2][8];
        Random r = new Random();

        int m = r.nextInt(((5) + 1));
        int n = r.nextInt(((5) + 1));
        
        int[] child1 = new int[8];
        System.arraycopy(p1, 0, child1, 0, m);
        System.arraycopy(p2, m, child1, m, 8-m);
        int[] child2 = new int[8];
        System.arraycopy(p2, 0, child2, 0, n);
        System.arraycopy(p1, n, child2, n, 8-n);

        
        int a = r.nextInt(((7) + 1));
        int b = r.nextInt(((7) + 1));
        int[] mutationChildOne = child1.clone();
        int[] mutationChildTwo = child2.clone();

        mutationChildOne[a] = b;

        int c = r.nextInt((7) + 1);
        int d = r.nextInt((7) + 1);
        
        mutationChildTwo[c] = d;

        for(int i = 0; i < 8; i++){
            childSet[0][i] = mutationChildOne[i];
        }

        for(int i = 0; i < 8; i++){
            childSet[1][i] = mutationChildTwo[i];
        }
        return childSet;
    }

//     public static int[] crossoverchild1(int[] p1, int[] p2){
//         int[] child1 = new int[8];
//        System.arraycopy(p1, 0, child1, 0, 4);
//        System.arraycopy(p2, 4, child1, 4, 4);
//         return child1;
//    }
//    public static int[] crossoverchild2(int[] p1, int[] p2){
//         int[] child2 = new int[8];
//         System.arraycopy(p2, 0, child2, 0, 4);
//         System.arraycopy(p1, 4, child2, 4, 4);
//         return child2;
//     }

    public static int[] mutation(int[] gene) {
        Random i = new Random();
        int a = i.nextInt(((7) + 1));
        int b = i.nextInt(((7) + 1));
        int[] mut = gene.clone();
        int k = mut[a];
        if (k == b) {
            mutation(gene);
        }
        mut[a] = b;
        return mut;
    }
}