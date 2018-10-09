***Print the boundary view of a binary tree
Boundary view = left perimeter

        1
      /   \
     2     3
     \     \
      5     6
      /    / \
     7    8   9
 
 The boundary view of this tree is 1 2 5 7 8 9 6 3 1
 
 We print the left boundary, the leaf nodes and the right boundary separately.

1 2 5 are printed during printing the left boudary.
7 8 9 are printed during printing the leaves.
6 3 1 are printed during printing the right boudary. Notice that we print the right boundary in bottom-to-top fashion.

-> Print Left Boundary Nodes
    While traversing the Left side of Tree, you can meet either of following condition,
    1. Node has Left child.    If Node has left child then print the node and visit its left child.

     2. Node doesn't has Left child.    
        If Node doesn't has left child then its Right child will form a boundary, 
        So print Right child and visit its Left child(as we are interested in Left boundary) if exist or 
        Repeat step 2 if Left child doesn't exist.

    3. Node doesn't has Left child and Right Child present.    
        If Left Node and Right Node is not present for a Node then it is sure that Node is a Leaf Node.
        So, don't print it in current traversal as in current traversal we are printing only Nodes on Left    
        Boundaries.

-> Print Leaf Nodes
    This is simple, when you found any node, whose Left and Right child is not present then it is a Leaf Node.

-> Print Right Boundary Nodes in Bottom Up fashion
    As we need to travel the Right side of a Tree in Bottom Up fashion, So we will start reading the Right side of tree from Top, but will not print the node data as we encounter it because we need node values to be printed in bottom up fashion.
    We will print Node values at last, what I mean by last is, we will print Node when our recursive call stack returns back. 

    While traversing the Right side of Tree, you can meet either of following condition,
    1. Node has Right child.    If Node has Right child then visit its right child. Remember we will not 
        print at this time but this node will be printed at last.

    2. Node doesn't has Right child.    
        If Node doesn't has Right child then its Left child will form a boundary, 
        So visit its Left child and for left child, Check its Right child exist.
        Repeat step 2 if Right child doesn't exist.
        In our example, we don't have this case.

    3. Node doesn't has Left child and Right Child present.    
        If Left Node and Right Node is not present for a Node then it is sure that Node is a Leaf Node.
        So, don't print it in current traversal as in current traversal we are printing only Nodes on Right    
        Boundaries.
        
Code:

/*
This function is non-recursive, this calls the 3 functions to print left, right and leaves separately.
We print the root using a separate function so that it doesn't get printed multiple times.
If the ROOT of the tree doesn't have any left child, then the left boundary will not exist.
If the ROOT of the tree doesn't have any right child, then the right boundary will not exist.

We don't print the leaves node during printing the left and right boundaries (if they are encountered during these
functions).
*/
 private void printBoundary(Node rootNode) {
      if(rootNode!=null){
       printRoot(rootNode);

       if(rootNode.getLeft()!=null)
        printLeft(rootNode.getLeft());

       printLeaf(rootNode);

       if(rootNode.getRight()!=null)
        printRight(rootNode.getRight());   
      }
 }
 
 private void printRoot(Node rootNode) {
      System.out.print(rootNode.getData() + " ");
 }
 
 private void printLeft(Node rootNode){
      if(rootNode==null){
       return;
      }
      if(rootNode.getLeft()==null && rootNode.getRight()==null){        //Not printing the leaf nodes
       return;
      }

      System.out.print(rootNode.getData() + " ");

      if(rootNode.getLeft()==null){
       printLeft(rootNode.getRight());
      }else{
       printLeft(rootNode.getLeft());
      }
 }
 
 private void printLeaf(Node rootNode){
      if(rootNode==null){
       return;
      }

      if(rootNode.getLeft()==null && rootNode.getRight()==null){
       System.out.print(rootNode.getData() + " ");
       return;
      }
      printLeaf(rootNode.getLeft());
      printLeaf(rootNode.getRight());
 }
 
 private void printRight(Node rootNode){
      if(rootNode==null){
       return;
      }
      if(rootNode.getLeft()==null && rootNode.getRight()==null){
       return;
      }

      if(rootNode.getRight()==null){
       printRight(rootNode.getLeft());
       System.out.print(rootNode.getData() + " ");

      }else{
       printRight(rootNode.getRight());
       System.out.print(rootNode.getData() + " ");
  }  
 }
