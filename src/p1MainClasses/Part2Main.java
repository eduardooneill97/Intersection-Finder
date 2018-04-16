package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;

import dataGenerator.DataGenerator;
import setIntersectionFinders.Strategy1;
import setIntersectionFinders.Strategy2;
import setIntersectionFinders.Strategy3;
import strategyTimeAndDataUtilities.StrategiesTimeCollection;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 */
public class Part2Main {
	
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException{
		int n = 10;
		int m = 50;
		int isize = 1000;
		int fsize = 50000;
		int istep = 1000;
		int rep = 200;
		
		if(args.length <= 6 && args.length>=1) {
			switch(args.length) {
			case 6:
				rep = Integer.parseInt(args[5]);
			case 5:
				istep = Integer.parseInt(args[4]);
			case 4:
				fsize = Integer.parseInt(args[3]);
			case 3:
				isize = Integer.parseInt(args[2]);
			case 2:
				m = Integer.parseInt(args[1]);
			case 1:
				n = Integer.parseInt(args[0]);
				break;
			}
//			n = Integer.parseInt(args[0]);
//			m = Integer.parseInt(args[1]);
//			isize = Integer.parseInt(args[2]);
//			fsize = Integer.parseInt(args[3]);
//			istep = Integer.parseInt(args[4]);
//			rep = Integer.parseInt(args[5]);
		}
		else if(args.length != 0)
			throw new IllegalArgumentException("Number of parameters must be 6. To use default values, use no parameters.");
		
		ArrayList<StrategiesTimeCollection<Integer>> results = generateResults(n, m, isize, fsize, istep, rep);
		
		generateResultFile(results);
		
	}
	/**
	 * Returns an ArrayList of StrategiesTimeCollection objects which contain the results 
	 * for each strategy executed given the specifications of the trials set by the parameters.
	 * @param n The number of companies.
	 * @param m The number of crime events.
	 * @param isize The initial size for experimentations.
	 * @param fsize Final size for experimentations.
	 * @param istep Increment of sizes.
	 * @param rep number of repetitions for a each size.
	 * @return ArrayList of StrategiesTimeCollection objects
	 * @throws FileNotFoundException
	 */
	private static ArrayList<StrategiesTimeCollection<Integer>> generateResults(int n, int m, int isize, int fsize, int istep, int rep) throws FileNotFoundException{
		
		ArrayList<StrategiesTimeCollection<Integer>> resultsPerStrategy = new ArrayList<StrategiesTimeCollection<Integer>>();
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Strategy1<Integer>("P1")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Strategy1<Integer>("P2")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Strategy2<Integer>("P3")));
		resultsPerStrategy.add(new StrategiesTimeCollection<Integer>(new Strategy3<Integer>("P4")));
		
		for (int size=isize; size<=fsize; size+=istep) { 
			
		    for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) 
		        strategy.resetSum();
			
		    for (int r = 0; r<rep; r++) {
		    	DataGenerator dg = new DataGenerator(n, m, size);
		        Integer[][][] dataset = (Integer[][][])dg.generateData();
		        
		        for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {	
		            long startTime = System.nanoTime();
		            strategy.runTrial(dataset, n, m);
		            long endTime = System.nanoTime();

		            int estimatedTime = (int) (endTime-startTime); 
		            strategy.incSum(estimatedTime);    				
		        }
		    }
		    for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy)
		        strategy.add( new AbstractMap.SimpleEntry<Integer, Float>
		                          (size, (strategy.getSum()/((float) rep)))
		                    ); 
		}

		return resultsPerStrategy;
	}
	/**
	 * Generates a text file containing the results of a given ArrayList of StrategiesTimeCollection objects. 
	 * The file format will be a table with the time of execution of all strategies given the size of the input.
	 * @param results ArrayList of StrategiesTimeCollection objects
	 * @throws FileNotFoundException
	 */
	private static void generateResultFile(ArrayList<StrategiesTimeCollection<Integer>> results) throws FileNotFoundException {
		PrintWriter resultsFile = new PrintWriter(new File("experimentalResults", "allResults.txt"));
		resultsFile.print("Size\t");
		for(StrategiesTimeCollection<Integer> strategy: results) {
			resultsFile.print(strategy.getStrategyName() + "\t\t");
		}
		resultsFile.println("");
		
		int sizes[] = new int[results.get(0).size()];
		for(int i = 0; i<results.get(0).size(); i++) {
			sizes[i] = results.get(0).get(i).getKey();
		}
		
		for(int i = 0; i<sizes.length; i++) {
			resultsFile.print(sizes[i] + "\t");
			String times = new String();
			for(int j = 0; j<results.size(); j++) {
				times += results.get(j).get(i).getValue() + "\t";
			}
			resultsFile.println(times);
		}
		resultsFile.close();
	}

}
