package gmap;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

//	public static AtomicInteger counter = new AtomicInteger(0);

	public static void main(String[] args) {
		List<Distance> list = DistanceCalculator.distanceCalculate( "34.4656639099121", "-118.405334472656");
		
		int avail = Runtime.getRuntime().availableProcessors();
		System.out.println("코어의 갯수"+avail);
		System.out.println("count of distances: "+list.size());
//		System.out.println(list.contains(0));
		
		for(int i=0;i<list.size();i++) {
			
			System.out.println(list.get(i));
		}
		System.out.println(list.get(0));
		
		
		
		

		
		
	}

}
