import java.util.*;
import java.io.*;

public class MiscQues {
	
	static class Node {
			int data;
			Node left;
			Node right;

			public Node(int data){
				this.data = data;
				this.left = null;
				this.right = null;
			}

			public Node(){
				this.data = 0;
				this.left = null;
				this.right = null;
			}
		}

	static class BinaryTree {

		Node root;
		int size;

		public BinaryTree(){
			this.root = takeInput(new ShortScan());
			this.size = 1;
		}

		public Node takeInput(ShortScan sc){
			Node newNode = new Node();

			po("Enter data for this node: ");
			newNode.data = sc.ni();
		
			char c;
			
			po("Left child of "+newNode.data+"? y/n: ");
			c = sc.nc();
			if(c == 'y'){
				newNode.left = takeInput(sc);
			}

			po("Right child of "+newNode.data+"? y/n: ");
			c = sc.nc();
			if(c == 'y'){
				newNode.right = takeInput(sc);
			}

			return newNode;
		}

		public String toString(){
			return print(this.root, "");
		}

		public String print(Node curr, String ans){

			if(curr == null){
				return ans;
			}

			if(curr.left != null){
				ans += curr.left.data + " <- ";
			} else 
				ans += "X <- ";

			ans += curr.data;

			if(curr.right != null){
				ans += " -> " + curr.right.data + "\n";
			} else 
				ans += " -> X \n" ;

			ans = print(curr.left, ans);
			ans = print(curr.right, ans);

			return ans;
		}

		/*
		This method inserts in a level order manner. We traverse
		the levels and find the first empty node, then add the node to it.
		*/
		public void insert(int data){
			Node n = new Node(data);

			Queue<Node> q = new LinkedList<>();
			q.add(this.root);
			
			while(!q.isEmpty()){
				Node cn = q.poll();

				if(cn.left == null){
					cn.left = n;
					break;
				}
				else if(cn.right == null){
					cn.right = n;
					break;
				}

				q.add(cn.left);
				q.add(cn.right);
			}
		}

		/*
		This method deletes the node by replacing it with the deepest
		node present and then deleteing the deepest node.
		*/
		public void delete(int target){
			Queue<Node> q = new LinkedList<>();
			q.add(this.root);

			Node toDelete = null, last = null;
			while(!q.isEmpty()){
				Node cn = q.remove();
				if(cn.data == target){
					toDelete = cn;
				}

				last = cn;

				if(cn.left != null)
					q.add(cn.left);
				if(cn.right != null)
					q.add(cn.right);

			}

			if(toDelete == null){
				po("No such element found!");
				return;
			}

			//Swap data of both nodes
			int temp = toDelete.data;
			toDelete.data = last.data;
			last.data = temp;

			//Search for the last element again and delete it
			q.add(this.root);
			Node cn;
			while(!q.isEmpty()){
				cn = q.poll();

				if(cn.left != null){
					if(cn.left.data == target)
						cn.left = null;
					else 
						q.add(cn.left);
				}

				if(cn.right != null){
					if(cn.right.data == target)
						cn.right = null;
					else 
						q.add(cn.right);
				}

			}
		}

		public Node findNode(int target, Node n){
			if(n == null || n.data == target){
				return n;
			}

			Node leftfind = findNode(target, n.left);
			if(leftfind != null)
				return leftfind;

			Node rightfind = findNode(target, n.right);
			if(rightfind != null)
				return rightfind;

			return null;
		}
	}

	    /*
		Level order traversal in spiral form.
		*/
		public static void levOrdSpiral(Node n){

			/*
			METHOD 1: USING TWO STACKS
			*/
			Stack<Node> s1 = new Stack<>();
			Stack<Node> s2 = new Stack<>();

			s1.push(n);

			Node cn;
			while(!s1.isEmpty() || !s2.isEmpty()){
				while(!s1.isEmpty()){
					cn = s1.pop();
					p(cn.data+" ");

					if(cn.right != null)
						s2.push(cn.right);

					if(cn.left != null)
						s2.push(cn.left);
				}

				while(!s2.isEmpty()){
					cn = s2.pop();
					p(cn.data+" ");

					if(cn.left != null)
						s1.push(cn.left);

					if(cn.right != null)
						s1.push(cn.right);
				}
			}
		}

		/*
		Level order line by line
		*/
		public static void levOrdLine(Node n){
			/*
			METHOD 1: USING TWO QUEUES
			
			Queue<Node> q1 = new LinkedList<>();
			Queue<Node> q2 = new LinkedList<>();

			q1.add(n);

			Node cn;
			while(!q1.isEmpty() || !q2.isEmpty()){
				while(!q1.isEmpty()){
					cn = q1.poll();
					p(cn.data+" ");

					if(cn.left != null)
						q2.add(cn.left);

					if(cn.right != null)
						q2.add(cn.right);
				}
				po("");

				while(!q2.isEmpty()){
					cn = q2.poll();
					p(cn.data+" ");

					if(cn.left != null)
						q1.add(cn.left);

					if(cn.right != null)
						q1.add(cn.right);	
				}
				po("");
			} */

			/*
			METHOD 2: USING ONLY ONE QUEUE
			*/
			Queue<Node> q = new LinkedList<>();
			q.add(n);

			Node cn;
			while(!q.isEmpty()){
				int size = q.size();
				while(size-->0){
					cn = q.poll();

					p(cn.data+" ");

					if(cn.left != null)
						q.add(cn.left);

					if(cn.right != null)
						q.add(cn.right);
				}
				po("");
			}

		}

		/*
		Reverse level order
		*/
		public static void levOrdRev(Node n){
			Queue<Node> q = new LinkedList<>();
			Stack<Node> s = new Stack<>();

			q.add(n);
			Node cn;
			while(!q.isEmpty()){
				cn = q.poll();

				s.push(cn);

				if(cn.right != null)
					q.add(cn.right);

				if(cn.left != null)
					q.add(cn.left);
			}

			while(!s.isEmpty()){
				cn = s.pop();
				p(cn.data+" ");
			}
		}

		/*
		Print all the nodes at a given level
		*/
		public static void printLevel(Node node, int level){
			if(node == null){
				return;
			}

			if(level == 1){
				p(node.data+" ");
			}

			printLevel(node.left, level-1);
			printLevel(node.right, level-1);
		}

		/*
		Print all ancestors of a given node
		*/
		public static boolean ancestors(Node node, int target, ArrayList<Integer> path){
			if(node == null){
				return false;
			}

			if(node.data == target){
				return true;
			}

			/*
			First add the current node in the path, assuming that this node is in the
			path that leads to the target. Then traverse further nodes from here.
			If none of the left or right subtree from this node reach the target (if they do then
			they will return true, otherwise false) then it means that the current node also
			shouldn't be in the ancestral-path.
			Hence, if we get false from both left and right subtree, we will remove the current node,
			and return false.
			*/
			path.add(node.data);

			if(ancestors(node.left, target, path))
				return true;

			if(ancestors(node.right, target, path))
				return true;

			path.remove(path.size()-1);
			return false;
		}

		/*
		Find the k-th ancestor of a given node
		*/
		static int k;

		public static void kthAncestor(Node node, int target, int kth){
			k = kth;
			kthAncestor_Util(node, target);
		}

		public static boolean kthAncestor_Util(Node node, int target){
			if(node == null){
				return false;
			}

			if(node.data == target ||
				kthAncestor_Util(node.left, target) ||
				kthAncestor_Util(node.right, target))  {
				
				if(k > 0)
					k--;

				else if (k == 0) {
					po("kth ancestor is "+node.data);
					return false;
				}

				return true;
			}

			return false;
		}

		/*
		Print all nodes at distance k from a given node
		*/
		
		//First we will try to convert binary tree to an undirected graph
		static class Graph {
			HashMap<Integer, ArrayList<Integer>> vertices;

			public Graph(){
				vertices = new HashMap<>();
			}
		}

		public static void kDisNodes (Node root, int target, int k){
			//First convert the tree into a undirected graph
			Graph g = new Graph();
			g.vertices.put(root.data, new ArrayList<>());

			preorder(root, g);
			po(g.vertices);

			//Do BFS starting with the target node
			Queue<Integer> q = new LinkedList<>();
			Set<Integer> vis = new HashSet<>();
	
			q.add(target);
			vis.add(target);

			int cn, level = 0, size;
			while(!q.isEmpty()){
				
				size = q.size();
				while(size-- > 0){
					cn = q.poll();

					if(level == k){
						p(cn+" ");
					}

					for(int i=0; i<g.vertices.get(cn).size(); i++){
						int nbr = g.vertices.get(cn).get(i);

						if(!vis.contains(nbr)){
							vis.add(nbr);
							q.add(nbr);
						}
					}
				}

				level++;
			}
		}

		public static void preorder(Node node, Graph g){
			
			if(node.left != null){
				g.vertices.get(node.data).add(node.left.data);

				if(!g.vertices.containsKey(node.left.data)){
					g.vertices.put(node.left.data, new ArrayList<Integer>());
				}

				g.vertices.get(node.left.data).add(node.data);

				preorder(node.left, g);

			}

			if(node.right != null){
				g.vertices.get(node.data).add(node.right.data);

				if(!g.vertices.containsKey(node.right.data)){
					g.vertices.put(node.right.data, new ArrayList<Integer>());
				}

				g.vertices.get(node.right.data).add(node.data);			

				preorder(node.right, g);
			}
		}

		/*
		ANOTHER METHOD TO GET UPPER NODES: By storing the ancestor route
		in a stack and then popping them and finding neighbours which are at
		the given distance.
		*/

		
		public static void upperK (Node root, int target, int k){
			Stack<Node> s = new Stack<>();
			getAncestors(root, target, s);

			HashSet<Integer> vis = new HashSet<>();
			vis.add(target);

			while(!s.isEmpty()){
				Node cn = s.pop();

				if(!vis.contains(cn.data)){
					upperK_Util(cn, k-1, vis);
				}

				k--;
			}
		}

		public static boolean getAncestors(Node node, int target, Stack<Node> s){
			if(node == null){
				return false;
			}

			if(node.data == target){
				return true;
			}

			s.push(node);

			if(getAncestors(node.left, target, s))
				return true;

			if(getAncestors(node.right, target, s))
				return true;

			s.pop();

			return false;
		}

		//The target node should be added to this vis set beforehand.
		public static void upperK_Util(Node node, int k, HashSet<Integer> vis){
			
			if(node == null || vis.contains(node.data))
				return;

			vis.add(node.data);

			if(k == 0){
				p(node.data+" ");
				return;
			}

			upperK_Util(node.left, k-1, vis);
			upperK_Util(node.right, k-1, vis);
		}

		/* Checking something
		*/
		static class Nodee {
			Node node;

			public Nodee(Node node){
				this.node = node;
			}

		}
		public static void blah(Node node, Nodee a){
			if(node.left == null && node.right == null){
				po("Setting "+node.data+" to a");
				a.node = node;
				po("Set "+a.node.data);
				return;
			}

			if(node.left != null)
				blah(node.left, a);

			po(a.node.data);

			if(node.right != null)
				blah(node.right, a);
		}

	// 0 y 1 y 3 n n y 4 n n y 2 y 5 n n y 6 n n
	// 0 y 1 y 3 y 4 n n n y 5 n n y 2 n n
	// 20 y 8 y 4 n n y 12 y 10 n n y 14 n n y 22 n n
	// 0 y 2 y 3 y 5 n n y 6 y 7 n y 8 n n n y 4 n y 9 y 10 n n y 11 y 12 n n n y 1 n n

/*-------------------------------------------------------------------------*/

	public static void main(String[] args){
		BinaryTree bt = new BinaryTree();
		po(bt);
		//levOrdSpiral(bt.root);
		//levOrdLine(bt.root);
		//levOrdRev(bt.root);
		//printLevel(bt.root, 3);

		/* To find ancestors
		ArrayList<Integer> path = new ArrayList<>();
		ancestors(bt.root, 10, path);
		for(int i=path.size()-1; i>=0; i--){
			p(path.get(i)+" ");
		}
		*/

		//kthAncestor(bt.root, 10, 3);
		//kDisNodes(bt.root, 8, 2);

		//upperK(bt.root, 6, 2);
		//Node a = new Node();
		blah(bt.root, new Nodee(new Node()));
	}

/*-------------------------------------------------------------------------*/

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

			public String n(){
				return sc.next();
			}

			public String ns(){
				return sc.nextLine();
			}

			public char nc(){
				return sc.next().charAt(0);
			}
	}

	public static void showArr(int[] arr){
		for(int x: arr){
			p(x+" ");
		}
		po("");
	}
}
