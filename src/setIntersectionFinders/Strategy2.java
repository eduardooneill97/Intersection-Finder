package setIntersectionFinders;

import java.util.ArrayList;

import interfaces.MySet;
import mySetImplementations.Set2;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 * @param <E>
 */
public class Strategy2<E> extends AbstractIntersectionFinder<E>{

	public Strategy2(String name) {
		super(name);
	}

	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		ArrayList<E> allElements = this.allElements(t);
		allElements.sort(null);
		MySet<E> intersection = new Set2();  
		E e = allElements.get(0); 
		Integer c = 1;
		for (int i=1; i < allElements.size(); i++) {
		    if (allElements.get(i).equals(e)) 
		       c++;
		    else { 
		       if (c == t.length) 
		    	   intersection.add(e);    
		       e = allElements.get(i); 
		       c = 1; 
		    } 
		}
		if (c == t.length)
			intersection.add(allElements.get(allElements.size()-1));

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
