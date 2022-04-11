import java.lang.*;
import java.util.*;

public class population {
    public static void main(String[] args){

        // System.out.println("The initial population is: ");
        int[][] initalPop = genetic.initializePopulation();
        // System.out.println(Arrays.deepToString(initalPop));

        geneticAlgorithm(initalPop, 1, false);
        // System.out.println(Arrays.deepToString(finalGeneration));

        // System.out.println("The fitness value for each chromosome is: ");
        // int[] calculatedFitnessArray = genetic.fitness(finalGeneration);
        // System.out.println(Arrays.toString(calculatedFitnessArray));

    }

    public static int[][] geneticAlgorithm(int[][] population, int counter, boolean fitnessCheckBoolean){

        int[][] newPopulation = new int[8][8];

        // System.out.println("Iteration: " + counter);
        counter++;
        // System.out.println(Arrays.deepToString(population));
        
        // System.out.println("The fitness value for each chromosome is: ");
        int[] calculatedFitnessArray = genetic.fitness(population);
        // System.out.println(Arrays.toString(calculatedFitnessArray));

        boolean fitnessValueZero = genetic.fitnessCheck(calculatedFitnessArray);

        // System.out.println("The selected parents are: ");
        int[][] parentSelection = genetic.parentSelection(calculatedFitnessArray, population);
        // System.out.println(Arrays.deepToString(parentSelection));

        int[] parentOne = new int[8];
        int[] parentTwo = new int[8];

        for(int x = 0; x < 8; x++){
            parentOne[x] = parentSelection[0][x];
        }

        for(int x = 0; x < 8; x++){
            parentTwo[x] = parentSelection[1][x];
        }

        int[][] childSet = genetic.childCreation(parentOne,parentTwo);
        // System.out.println("The two pair of childs are: ");
        // System.out.println(Arrays.deepToString(childSet));

        for(int x = 0; x < 2; x++){
            for(int y = 0; y < 8; y++){
                newPopulation[x][y] = childSet[x][y]; 
            }
        }

        int[] parentThree = new int[8];
        int[] parentFour = new int[8];
        int[][] parentSelectionTwo = genetic.parentSelection(calculatedFitnessArray, population);

        for(int x = 0; x < 8; x++){
            parentThree[x] = parentSelectionTwo[0][x];
        }

        for(int x = 0; x < 8; x++){
            parentFour[x] = parentSelectionTwo[1][x];
        }

        int[][] childSetTwo = genetic.childCreation(parentThree,parentFour);
        // System.out.println("The two pair of childs are: ");
        // System.out.println(Arrays.deepToString(childSetTwo));

        for(int x = 2; x < 4; x++){
            for(int y = 0; y < 8; y++){
                newPopulation[x][y] = childSetTwo[x-2][y]; 
            }
        }

        int[] parentFive = new int[8];
        int[] parentSix = new int[8];
        int[][] parentSelectionThree = genetic.parentSelection(calculatedFitnessArray, population);

        for(int x = 0; x < 8; x++){
            parentFive[x] = parentSelectionThree[0][x];
        }

        for(int x = 0; x < 8; x++){
            parentSix[x] = parentSelectionThree[1][x];
        }

        int[][] childSetThree = genetic.childCreation(parentFive,parentSix);
        // System.out.println("The two pair of childs are: ");
        // System.out.println(Arrays.deepToString(childSetThree));

        for(int x = 4; x < 6; x++){
            for(int y = 0; y < 8; y++){
                newPopulation[x][y] = childSetThree[x-4][y]; 
            }
        }

        int[] parentSeven = new int[8];
        int[] parentEight = new int[8];
        int[][] parentSelectionFour = genetic.parentSelection(calculatedFitnessArray, population);

        for(int x = 0; x < 8; x++){
            parentSeven[x] = parentSelectionFour[0][x];
        }

        for(int x = 0; x < 8; x++){
            parentEight[x] = parentSelectionFour[1][x];
        }

        int[][] childSetFour = genetic.childCreation(parentSeven,parentEight);
        // System.out.println("The two pair of childs are: ");
        // System.out.println(Arrays.deepToString(childSetFour));

        for(int x = 6; x < 8; x++){
            for(int y = 0; y < 8; y++){
                newPopulation[x][y] = childSetFour[x-6][y]; 
            }
        }
        if(fitnessValueZero != true){
            geneticAlgorithm(newPopulation, counter, fitnessValueZero);
        }
        else{
            // System.out.println("Iteration: " + counter);
            System.out.println(Arrays.deepToString(population));
            System.out.println("Calculated fitness array: " + Arrays.toString(calculatedFitnessArray));
        }
        
        return newPopulation;
    }
}
