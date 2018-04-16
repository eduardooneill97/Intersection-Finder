package strategyTimeAndDataUtilities;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;

import interfaces.MySet;
import setIntersectionFinders.AbstractIntersectionFinder;
import setIntersectionFinders.Strategy1;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 * @param <E>
 */
public class StrategiesTimeCollection<E> extends ArrayList<AbstractMap.SimpleEntry<Integer, Float>>{
	
	private long sum;
	private AbstractIntersectionFinder<E> strategy;
	
	public StrategiesTimeCollection (AbstractIntersectionFinder<E> finder){
		strategy = finder;
		sum = 0;
	}
	/**
	 * Returns the name of the strategy that this time collection implements.
	 * @return String of the name of the strategy.
	 */
	public String getStrategyName() {
		return strategy.getName();
	}
	/**
	 * Executes the strategy with the given three dimensional array.
	 * @param dataset  Three dimensional array of type E.
	 * @param n Number of elements in the first dimension.
	 * @param m Number of elements in the second dimension.
	 * @throws FileNotFoundException
	 */
	public void runTrial(E[][][] dataset, int n, int m) throws FileNotFoundException {
		DataConverter<E> dc = new DataConverter<E>();
		MySet<E>[] set;
		if(strategy.getName().equals("P1"))
			set = dc.dataToSet1(dataset, n, m);
		else
			set = dc.dataToSet2(dataset, n, m);
		
		strategy.intersectSets(set);
	}
	/**
	 * Sets the total sum of the times taken to execute the strategy to 0.
	 */
	public void resetSum() {
		sum = 0;
	}
	/**
	 * Adds the given time taken of a trial to the total sum. 
	 * @param estimatedTime
	 */
	public void incSum(long estimatedTime) {
		sum += estimatedTime;
	}
	/**
	 * Returns the current sum of all the times taken to run the trial.
	 * @return
	 */
	public long getSum() {
		return sum;
	}

}
