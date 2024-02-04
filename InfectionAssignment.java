package gp2;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.io.*;

//comment
//comment
public class InfectionAssignment {
	/**
	 * erases the contents of the file
	 */
	public static void eraseFile()
	{
		try {
			PrintWriter outputFile = new PrintWriter("C:\\Users\\timda\\OneDrive\\Documents\\SCHOOL DOCUMENTS\\TestFile.txt");
			outputFile.println("");
			outputFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * appends the contents of the grid of individuals' statuses into the file
	 * 
	 * @param grid the grid of individuals
	 * @param timeStep the time step the algorithm is
	 */
	public static void appendLog(Individual[][] grid, int timeStep)
    {
        try
        {
            int susceptible = 0;
            int infected = 0;
            int recovered = 0;
            FileWriter fw = new FileWriter("C:\\Users\\timda\\OneDrive\\Documents\\SCHOOL DOCUMENTS\\TestFile.txt", true);
            PrintWriter outputFile = new PrintWriter(fw);
            outputFile.println("Time Step: " + timeStep);
            for(int i = 0; i < grid.length; i++)
            {
                for(int j = 0; j < grid[i].length; j++)
                {
                    outputFile.print(grid[i][j].getStatus() + " ");
                    if(grid[i][j].getStatus() == 'S')
                    {
                        susceptible += 1;
                    }
                    else if(grid[i][j].getStatus() == 'I')
                    {
                        infected += 1;
                    }
                    else
                    {
                        recovered += 1;
                    }
                }
                outputFile.println();
            }
            outputFile.println("Num of Susceptible: " + susceptible + ", Num of Infected: " +
                    infected + ", Num of Recovered: " + recovered + "\n");
            outputFile.close();
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	

   /**
    * checks if the input is a perfect square
    * @param num the user input for number of individuals
    * @return returns the number of individuals as long as its a perfect square
    */
	public static int isPerfectSquare(int num) {
      Scanner scnr = new Scanner(System.in);
      while (num % Math.sqrt(num) != 0) {
          System.out.println("Number nust be a perfect square: ");
          num = scnr.nextInt();
      }
      return num;
   }
	
	/**
	 * checks if the input is within the bounds
	 * @param valueToCheck the value that needs to be checked (infection and recovery rate)
	 * @param lowerBound the lower bound
	 * @param upperBound the upper bound
	 * @return returns the valueToCheck as long as its within the bounds
	 */
	public static double isWithinBounds(double valueToCheck, double lowerBound, double upperBound){
        Scanner scnr = new Scanner(System.in);

        while(valueToCheck < lowerBound || valueToCheck > upperBound){
            System.out.println("Please enter a valid value: ");
            valueToCheck = scnr.nextDouble();
        }
        return valueToCheck;
    }
  
	/**
	 * 
	 * @param num
	 * @return
	 */
	public static int isValidNum(int num) {
        Scanner scnr = new Scanner(System.in);
        while (num <= 0) {
            System.out.println("Number must be greater than 0: ");
            num = scnr.nextInt();
        }
        return num;
    }
	/**
	 * fills grid with susceptible individuals
	 * @param individuals the number of individuals
	 * @param infectionRate the infection rate
	 * @param recoveryRate the recovery rate
	 * @return the grid(array) of individuals
	 */
	public static Individual[][] fillGrid(int individuals, double infectionRate, double recoveryRate)
	{
	  //creates a 2D array of the Individual object
		Individual[][] individualArr = new Individual[(int)Math.sqrt(individuals)][(int)Math.sqrt(individuals)];
	      //fills the 2D array with individual objects
	    for(int i = 0; i < individualArr.length; i++)
	    {
	    	for(int j = 0; j < individualArr[i].length; j++)
	        {
	            //creates each individual as Susceptible 
	           individualArr[i][j] = new Individual('S', infectionRate, recoveryRate);
	        }
	    }
	    return individualArr;
	   }

	/**
	 * randomly chooses an individual in the grid
	 * @param individuals the number of individuals
	 * @return returns an array with a coordinate with two values [x,y] within the grid
	 */
	public static int[] chooseAnInfected(int individuals)
	{
		Random randGen = new Random();
	      // creates an array of size 2 for the ordered pair
	    //fills the array with two values, one for the x coordinate of the grid and another for the y coordinate of the grid
		int[] infectedLocation = {randGen.nextInt((int)Math.sqrt(individuals)), randGen.nextInt((int)Math.sqrt(individuals)) };
		return infectedLocation;
	      
	}
     
   /**
    * checks the number of infected individuals
    * @param grid the grid of individuals
    * @param location the [x,y] of the individual
    * @return returns the number of infected neighbors
    */
   public static int checkNumNeighbors(Individual[][] grid, int[] location)
   {
       //checks for all 4 sides of the person
       int numOfNeighbors = 0;
       if((location[0]-1) >= 0)
       {
           if(grid[location[0]-1][location[1]].getStatus() == 'I')
           {
               numOfNeighbors += 1;
           }
       }
       if((location[0]+1) < grid[0].length)
       {
           if(grid[location[0]+1][location[1]].getStatus() == 'I')
           {
               numOfNeighbors += 1;
           }
       }
       if((location[1]-1) >= 0)
       {
           if(grid[location[0]][location[1]-1].getStatus() == 'I')
           {
               numOfNeighbors += 1;
           }
       }
       if((location[1]+1) < grid.length)
       {
           if(grid[location[0]][location[1]+1].getStatus() == 'I')
           {
               numOfNeighbors += 1;
           }
       }
       return numOfNeighbors;
   }

   /**
    * calculates the infection rate based on number of infected individuals
    * @param numIndividuals the number of infected individuals around the certain individuals
    * @param infectionRate the infection rate
    * @return returns the number of infected individuals surrounding times the base infection rate
    */
   public static double calcInfectRate(int numIndividuals, double infectionRate)
   {
	   return numIndividuals * infectionRate;
   }
   
   /**
    * determines if the susceptible gets infected or infected gets recovered
    * @param prob the infection rate or recovery rate
    * @return returns a true or false if they are infected or recovered
    */
   public static boolean biasedRand(double prob) 
   {
	   double n = ((double)Math.round(Math.random() * 100)) / 100;
	   if(prob == 0)
	   {
		   return false;
	   }
	   else if (n <= prob)
	   {
		   return true;
	   }
	   else
	   {
		   return false;
	   }
   }
   /**
    * copies the grid
    * @param grid the grid that needs to be copied
    * @return returns a copied grid
    */
   public static Individual[][] copyGrid(Individual[][] grid){
       Individual[][] returnGrid = new Individual[grid.length][grid[0].length];

       for(int i = 0; i < grid.length; i++)
       {
           for(int j = 0; j < grid[0].length; j++)
           {
               returnGrid[i][j] = grid[i][j];
           }
       }
       return returnGrid;
   }

   public static void main (String [] args) {
      //variables
      int individuals;
      int timeSteps;
      double infectionRate;
      double recoveryRate;
      Scanner scnr = new Scanner(System.in);

      System.out.println("Enter the number of individuals (Must be a perfect square):");
      individuals = isPerfectSquare(scnr.nextInt());
      //gets input from user for num of time steps
      System.out.println("Enter the number of time steps:");
      timeSteps = isValidNum(scnr.nextInt());;
      
      System.out.println("Enter the infection rate (must be between 0 and 0.25 inclusive): ");
      infectionRate = isWithinBounds(scnr.nextDouble(), 0.00, 0.25);
      System.out.println("Enter the recovery rate (must be between 0 and 1 inclusive): ");
      recoveryRate = isWithinBounds(scnr.nextDouble(), 0.00, 1.00);
      
      eraseFile();
      Individual[][] initialGrid = fillGrid(individuals, infectionRate, recoveryRate);
      int[] patientZeroIndex = chooseAnInfected(individuals);
      initialGrid[patientZeroIndex[0]][patientZeroIndex[1]].setStatus('I');

      Individual[][] updatedGrid = fillGrid(individuals, infectionRate, recoveryRate);
      appendLog(initialGrid, 0);
      int numInfected = 0;
      int numSusceptible = 0;
      int numRecovered = 0;
      //repeated steps at each time step
      for (int i = 0; i < timeSteps; i++)
      {

          //iterating through each person in the Individual Array
          for(int j = 0; j < Math.sqrt(individuals); j++)
          {
              for(int k = 0; k < Math.sqrt(individuals); k++)
              {
                  int[] location = {j,k};
                  if(initialGrid[j][k].getStatus() == 'S')
                  {
                      if(biasedRand(calcInfectRate(checkNumNeighbors(initialGrid, location), infectionRate)))
                      {
                          numInfected++;
                          updatedGrid[j][k].setStatus('I');
                      }
                      else
                      {
                          numSusceptible++;
                          updatedGrid[j][k].setStatus('S');
                      }
                  }
                  else if(initialGrid[j][k].getStatus() == 'I')
                  {
                      if(biasedRand(recoveryRate))
                      {
                          numRecovered++;
                          updatedGrid[j][k].setStatus('R');
                      }
                      else
                      {
                          numInfected++;
                          updatedGrid[j][k].setStatus('I');
                      }
                  }
                  else if(initialGrid[j][k].getStatus() == 'R')
                  {
                      numRecovered++;
                      updatedGrid[j][k].setStatus('R');
                  }
              }
          }
          appendLog(updatedGrid, i+1);
          System.out.println("Num of Susceptible: " + numSusceptible + ", Num of Infected: " + 
       		    numInfected + ", Num of Recovered: " + numRecovered);
          numSusceptible = 0;
          numInfected = 0;
          numRecovered = 0;
           
         //After new grid is appended to file, store it new grid to reference previous time step
          initialGrid = copyGrid(updatedGrid);

          updatedGrid = fillGrid(individuals, infectionRate, recoveryRate);
      }
   }
}