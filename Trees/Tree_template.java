import java.util.*;
import java.io.*;

public class Tree_template {

	static class BinaryTree {

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

	}

	// 0 y 1 y 3 n n y 4 n n y 2 y 5 n n y 6 n n
	// 0 y 1 y 3 y 4 n n n y 5 n n y 2 n n
	// 0 y 2 y 3 y 5 n n y 6 y 7 n y 8 n n n y 4 n y 9 y 10 n n y 11 y 12 n n n y 1 n n

/*-------------------------------------------------------------------------*/

	public static void main(String[] args){
		BinaryTree bt = new BinaryTree();
		po(bt);
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