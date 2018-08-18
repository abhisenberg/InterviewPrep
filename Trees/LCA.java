import java.util.*;
import java.io.*;

public class LCA {

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

		/*
		Before finding LCA, we have to make sure whether both of the
		nodes are present in the tree or not, if both are present, then we
		proceed to find lca, otherwise return null.

		The idea is to traverse the tree starting from root.
		If any of the given keys (n1 and n2) matches with root,
		then root is LCA (assuming that both keys are present).
		If root doesn’t match with any of the keys,
		we recur for left and right subtree. 
		The node which has one key present in its left subtree and the
		other key present in right subtree is the LCA. 
		if both keys lie in left subtree, then left subtree has LCA also,
		otherwise LCA lies in right subtree.
		*/

		public boolean treeContains(Node node, int target){
			if(node == null)
				return false;

			if(node.data == target)
				return true;

			boolean foundInLeft = treeContains(node.left, target);
			boolean foundInRight = treeContains(node.right, target);

			return foundInRight || foundInLeft;
		}

		public int LCA(int n1, int n2){
			if(!treeContains(this.root, n1) || !treeContains(this.root, n2)){
				return -1;
			}

			Node lca = LCA_Util(root, n1, n2);

			if(lca == null) return -1;
			else return lca.data;
		}

		public Node LCA_Util(Node node, int n1, int n2){
			if(node == null){
				return null;
			}

			if(node.data == n1){
				return node;
			}

			if(node.data == n2){
				return node;
			}

			Node leftlca = LCA_Util(node.left, n1, n2);
			Node rightlca = LCA_Util(node.right, n1, n2);

			if(leftlca != null && rightlca != null){
				return node;
			}

			if(leftlca == null && rightlca == null){
				return null;
			}

			return leftlca != null ? leftlca : rightlca;
		}

		/*
		Find distance between two nodes: The idea is to first find the LCA
		of the two nodes and then dis(n1, lca) + dis(n2, lca).
		Assumption: Both nodes are present in the tree.
		*/
		public int disNodes(int n1, int n2){
			Node lca = LCA_Util(this.root, n1, n2);
			
			if(lca == null) return -1;

			return disFromLCA(lca, n1)+disFromLCA(lca, n2);
		}

		public int disFromLCA(Node node, int target){
			if(node == null){
				return -1;
			}

			if(node.data == target){
				return 0;
			}

			int disLeft = disFromLCA(node.left, target);
			if(disLeft != -1)
				return disLeft +1;

			int disright = disFromLCA(node.right, target);
			if(disright != -1)
				return disright +1;

			return -1;
		}

	}

	// 0 y 1 y 3 n n y 4 n n y 2 y 5 n n y 6 n n
	// 0 y 1 y 3 y 4 n n n y 5 n n y 2 n n
	// 0 y 2 y 3 y 5 n n y 6 y 7 n y 8 n n n y 4 n y 9 y 10 n n y 11 y 12 n n n y 1 n n

/*-------------------------------------------------------------------------*/

	public static void main(String[] args){
		BinaryTree bt = new BinaryTree();
		po(bt);
		po(bt.LCA(10, 9));
		po("*"+ bt.disNodes(10, 9));

		po(bt.LCA(10, 12));
		po("*"+ bt.disNodes(10, 12));

		po(bt.LCA(6, 9));
		po("*"+ bt.disNodes(6, 9));

		po(bt.LCA(3, 1));
		po("*"+ bt.disNodes(3, 1));

		po(bt.LCA(3, 14));
		po("*"+ bt.disNodes(3, 14));

		po(bt.LCA(2, 6));
		po("*"+ bt.disNodes(2, 6));
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