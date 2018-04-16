package setIntersectionFinders;

import java.util.ArrayList;

import interfaces.MySet;
/**
 * 
 * @author Eduardo O'Neill 801155476
 *
 * @param <E>
 */
public class Strategy1<E> extends AbstractIntersectionFinder<E>{
	
	public Strategy1(String name){
		super(name);
	}

	@Override
	public MySet<E> intersectSets(MySet<E>[] t){
		MySet<E> set;
		try {
			set = (MySet<E>) t[0].clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		
		for(int i = 1; i<t.length; i++) {
			for(E element: t[0]) {
				if(!t[i].contains(element))
					set.remove(element);
			}
		}
		return set;
	}

}
