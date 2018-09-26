import java.util.*;
import java.io.*;

public class Template {

	/*
	To find continuous median from a stream of integers, the following algo is used:
	
	-> Make two heaps, a max-heap to store the no.s less than the current median
	   and a min-heap to store the no.s greater than the current median.
	   Max-heap is called Lowers and min-heap is called Highers.
	-> One by one, insert the numbers in any one of the two heaps, by comparing it with
	   the top element of both heaps (max of lowers or min of highers).
	-> For ex, if it is less than max of lowers, then it belongs to the lowers itself, else highers.
	-> The second step is to rebalance the heaps. Which means that if the size difference of 
	   both the heaps is > 1, then one element needs to be moved from the bigger sized heap
	   to the smaller sized heap.
	-> Third step is to find out the median. The median is found as follows:
	   If the size of both the heaps is equal, then median is the average of two numbers
	   i.e. the max of lowers and the min of highers
	   (lowers.peek()+highers.peek())/2
	   
	   If the size is unequal, then the median is the top element of that heap which has
	   bigger size.
	*/
	
	public static void main(String[] args){
		ShortScan sc = new ShortScan();
		int n = sc.ni();
		int[] arr = sc.narr(n);
		
		PriorityQueue<Integer> highers = new PriorityQueue<>();
		PriorityQueue<Integer> lowers =
		    new PriorityQueue<>(Collections.reverseOrder());
		
		/*
		Three steps:
		-> Add element to either of the heaps
		-> Rebalance the heaps
		-> Get median
		*/
		
		for(int x: arr){
		    addElement(x, highers, lowers);
		    rebalanceHeaps(highers, lowers);
		    po(getMedian(highers, lowers));
		}
	}
	
	public static void addElement(int ele, PriorityQueue<Integer> highers,
	            PriorityQueue<Integer> lowers){
	    
	    if(lowers.size()==0 || ele < lowers.peek()){
	        lowers.add(ele);
	    } else {
	        highers.add(ele);
	    }
	}
	
	public static void rebalanceHeaps(PriorityQueue<Integer> highers,
	            PriorityQueue<Integer> lowers){
	    /*
	    Determine which heap has more elements, and whether the diff.
	    between the size of the heaps is more than 1.
	    */
        PriorityQueue<Integer> moreSize = 
            (highers.size() > lowers.size()) ? highers : lowers;
        PriorityQueue<Integer> lessSize = 
            (highers.size() < lowers.size()) ? highers : lowers;
            
        if(moreSize.size() - lessSize.size() > 1){
            // po("REb");
            lessSize.add(moreSize.poll());
        }
	}
	
	public static int getMedian(PriorityQueue<Integer> highers,
	            PriorityQueue<Integer> lowers){
	    /*
	    If size of both heaps is same, then the median is the average 
	    of the values of top values of both the heaps.
	    
	    Otherwise the median is either the top value of highers
	    or lowers, whichever is bigger.
	    */
	    if(lowers.size()==0) return highers.peek();
	    if(highers.size()==0) return lowers.peek();
	    
	    /*
	    if(highers.size() == lowers.size()){
	        return (highers.peek()+lowers.peek())/2;
	    } else {
	        return (highers.peek() < lowers.peek())? highers.peek()
	                :  lowers.peek();
	    }*/
	    
	    if(highers.size() == lowers.size()){
	        return (highers.peek()+lowers.peek())/2;
	    } else {
	        return (highers.size() > lowers.size()) ? highers.peek() : lowers.peek();
	    }
	}


//**********************************************************************//	

	public static void po(Object o){
		System.out.println(o);
	}

	public static void p(Object o){
		System.out.print(o);
	}

	static class ShortScan {
			Scanner sc;

			public ShortScan(){
				sc = new Scanner (System.in);
			}

			public int ni(){
				return sc.nextInt();
			}

			public long nl(){
				return sc.nextLong();
			}

			public String n(){
				return sc.next();
			}

			public String ns(){
				return sc.nextLine();
			}

			public int[] narr(int n){
				int[] arr = new int[n];
				for(int i=0; i<arr.length; i++){
					arr[i] = sc.nextInt();
				}
				return arr;
			}
	}

	public static <T> void showArr(T[] arr){
		for(T x: arr){
			p(x+" ");
		}
		po("");
	}

	public static void show2d(int[][] arr){
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr.length; j++){
				p(arr[i][j]+" ");
			}
			po("");
		}
	}
}	
