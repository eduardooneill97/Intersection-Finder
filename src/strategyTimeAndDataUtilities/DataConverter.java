package strategyTimeAndDataUtilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

import mySetImplementations.Set1;
import mySetImplementations.Set2;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 * @param <E>
 */
public class DataConverter<E> {
	/**
	 * Takes a three dimensional array of type E and converts it to 
	 * a one dimensional array of type Set1 where each element in the 
	 * array contains a set representing all elements of type E from 
	 * the second dimension in the original array.
	 * @param data Three dimensional array of type E
	 * @param n Number of elements in the first dimension
	 * @param m Number of elements in the second dimension
	 * @return Array of elements of type MySet
	 * @throws FileNotFoundException
	 */
	public Set1<E>[] dataToSet1(E[][][] data, int n, int m) throws FileNotFoundException{
		
		Set1<E>[] set = (Set1<E>[]) Array.newInstance((new Set1<E>()).getClass(), m);
		
		for(int j = 0; j<m; j++) {
			set[j] = new Set1<E>();
			for(int i = 0; i<n; i++) {
				for(int k = 0; k<data[i][j].length; k++) {
					set[j].add(data[i][j][k]);
				}
			}
		}
		return set;
	}
	/**
	 * Takes a three dimensional array of type E and converts it to 
	 * a one dimensional array of type Set2 where each element in the 
	 * array contains a set representing all elements of type E from 
	 * the second dimension in the original array.
	 * @param data Three dimensional array of type E.
	 * @param n Number of elements in the first dimension.
	 * @param m Number of elements in the second dimension.
	 * @return Array of elements of type MySet.
	 * @throws FileNotFoundException
	 */
	public Set2<E>[] dataToSet2(E[][][] data, int n, int m) throws FileNotFoundException{
		
		Set2<E>[] set = (Set2<E>[]) Array.newInstance((new Set2<E>()).getClass(), m);
		
		for(int j = 0; j<m; j++) {
			set[j] = new Set2<E>();
			for(int i = 0; i<n; i++) {
				for(int k = 0; k<data[i][j].length; k++) {
					set[j].add(data[i][j][k]);
				}
			}
		}
		return set;
	}

}
