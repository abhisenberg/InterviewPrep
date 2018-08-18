import java.util.*;
import java.lang.*;

public class Heaps
{
	static class Heap
	{
		private ArrayList<Integer> data;
		private HashMap<Integer, Integer> pos;
		
		public Heap()
		{
			data = new ArrayList<>();
			pos = new HashMap<>();
		}
		
		public Heap(int[] arr)
		{	
			this();
			
			for(int x: arr)
			{
				data.add(x);
				pos.put(x, data.size()-1);
			}
			
			/*
			//Arranging the heap for the first time
			for(int i= (data.size()/2)-1; i>=0; i--)
			{
				downheapify(i);
			}*/

			for(int i= 0; i<(data.size()/2); i++)
			{
				downheapify(i);
			}

		}
		
		//used in creation and deletion of heap
		public void downheapify(int pi)
		{
			int lci = 2*pi + 1;
			int rci = 2*pi + 2;
			
			int mini = pi;
			
			if(lci < data.size() && data.get(lci) < data.get(pi))
				mini = lci;
			else if(rci < data.size() && data.get(rci) < data.get(pi))
				mini = rci;
			
			if(mini != pi)
			{
				swap(mini, pi);
				downheapify(mini);
			}
		}
		
		//generally only used in addition of new element
		public void upheapify(int ci)
		{
			int pi = (ci-1)/2;

			if(pi >= 0 && data.get(ci) < data.get(pi))
			{
				swap(ci, pi);
				upheapify(pi);
			}
		}
		
		public int getHighestPr()
		{
			return data.get(0);
		}
		
		public int removeHighestPr()
		{
			int rv = data.get(0);
			swap(0, data.size()-1);
			data.remove(data.size()-1);
			downheapify(0);
			return rv;
		}
		
		public void add(int ele)
		{
			data.add(ele);
			pos.put(ele, data.size()-1);
			upheapify(data.size()-1);
		}
		
		public boolean isHigherPr(int i, int j)
		{
			//for min-heap
			if(data.get(i) < data.get(j))
				return true;
			else
				return false;
		}
		
		public void swap(int i, int j)
		{
			int ith_data = data.get(i);
			int jth_data = data.get(j);			
			
			pos.put(ith_data, j);
			pos.put(jth_data, i);
			
			data.set(i, jth_data);
			data.set(j, ith_data);
		}
		
		public void show()
		{
			po(this.data);
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		po("Enter no. of elements: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		po("Enter elements: ");
		for(int i=0; i<n; i++)
		{
			arr[i] = sc.nextInt();
		}
		
		Heap h = new Heap(arr);
		h.show();
		
		h.removeHighestPr();
		h.show();
		
		h.add(0);
		h.show();
	}
	
	public static void po(Object o)
	{
		System.out.println(o);
	}
	
	//7 1 8 2 9 7 4 6
}
