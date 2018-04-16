package setIntersectionFinders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.MySet;
import mySetImplementations.Set2;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 * @param <E>
 */
public class Strategy3<E> extends AbstractIntersectionFinder<E>{

	public Strategy3(String name) {
		super(name);
	}

	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		ArrayList<E> allElements = this.allElements(t);
		HashMap<E, Integer> map = new HashMap<>(); 
		for (E e : allElements) { 
		     Integer c = map.getOrDefault(e, 0); 
		     map.put(e, c+1); 
		}
		MySet<E> intersection = new Set2<>(); 
		for (Map.Entry<E, Integer> entry : map.entrySet())
		     if (entry.getValue() == t.length) 
		        intersection.add(entry.getKey());

		return intersection;
	}
	/**
	 * Takes all of the elements from each set of a family of 
	 * sets and returns an ArrayList containing those elements. 
	 * Duplicate elements across sets are also added.
	 * @param t array of elements of type MySet.
	 * @return ArrayList containing all elements of all sets.
	 */
	private ArrayList<E> allElements(MySet<E>[] t){
		ArrayList<E> list = new ArrayList<E>();
		for(int i = 0; i<t.length; i++) {
			for(E element: t[i]) {
				list.add(element);
			}
		}
		return list;
	}

}
