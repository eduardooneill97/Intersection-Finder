package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

import dataGenerator.DataGenerator;
import dataGenerator.DataReader;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinders.Strategy1;
import setIntersectionFinders.Strategy2;
import setIntersectionFinders.Strategy3;
import strategyTimeAndDataUtilities.DataConverter;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 */
public class Part1Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		DataReader reader = new DataReader();
		Integer[][][] dataset = (Integer[][][])reader.readDataFiles();
		Scanner scan = new Scanner(new File("inputFiles", "parameters.txt"));
		int n = scan.nextInt();
		int m = scan.nextInt();
		scan.close();
		DataConverter<Integer> dc = new DataConverter<Integer>();
		
		Strategy1<Integer> p1  = new Strategy1<Integer>("P1");
		Strategy1<Integer> p2  = new Strategy1<Integer>("P2");
		Strategy2<Integer> p3 = new Strategy2<Integer>("P3");
		Strategy3<Integer> p4 = new Strategy3<Integer>("P4");
		
		if(args.length == 1) {
			
			switch(args[0]) {
			
			case "1":
				Set1<Integer>[] set1 = dc.dataToSet1(dataset, n, m);
				Set1<Integer> intersection = (Set1<Integer>) p1.intersectSets(set1);
				System.out.println("Final set by P1: " + intersection);
				break;
			case "2":
				Set2<Integer>[] set2 = dc.dataToSet2(dataset, n, m);
				Set2<Integer> intersection2 = (Set2<Integer>) p2.intersectSets(set2);
				System.out.println("Final set by P2: " + intersection2);
				break;
			case "3":
				Set2<Integer>[] set3 = dc.dataToSet2(dataset, n, m);
				Set2<Integer> intersection3 = (Set2<Integer>) p3.intersectSets(set3);
				System.out.println("Final set by P3: " + intersection3);
				break;
			case "4":
				Set2<Integer>[] set4 = dc.dataToSet2(dataset, n, m);
				Set2<Integer> intersection4 = (Set2<Integer>) p4.intersectSets(set4);
				System.out.println("Final set by P4: " + intersection4);
				break;
			default:
				System.out.println("No strategy associated with parameter. Parameter must be and integer from 1 to 4.");
				break;
			}
			
		} 
		else if(args.length == 0) {
			Set1<Integer>[] set1 = dc.dataToSet1(dataset, n, m);
			Set2<Integer>[] set2 = dc.dataToSet2(dataset, n, m);
			
			Set1<Integer> intersection = (Set1<Integer>) p1.intersectSets(set1);
			Set2<Integer> intersection2 = (Set2<Integer>) p2.intersectSets(set2);
			Set2<Integer> intersection3 = (Set2<Integer>) p3.intersectSets(set2);
			Set2<Integer> intersection4 = (Set2<Integer>) p4.intersectSets(set2);
			System.out.println("Final set by P1: " + intersection +
							 "\nFinal set by P2: " + intersection2 +
							 "\nFinal set by P3: " + intersection3 +
							 "\nFinal set by P4: " + intersection4);
		}
		else
			System.out.println("Invalid number of parameters. Must be <= 1.");
	}

}
