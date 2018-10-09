/*
TopView is like the vertical view of the tree but in this, the lower level nodes of the same horizontal distance
can't be seen. (From the top, only one node of a particular dis can be seen).

        1
       / \
      2   3
     / \ / \  
    4  5 6  7
    
    Output: 4 2 1 3 7 (5 and 6 get obstructed from the view becz of 1).
    
        1
      /   \
     2     3
       \
        4
          \
           5
            \
             6
    
    Output: 2 1 3 6 (4 and 5 get obstructed from the view bcz of 1 and 3).
*/

Algo:
  The recursion is almost the same as that of vertical traversal, however, here we keep track of current level also
  while storing the node at a particular distance. (We store the node as well as it's level for a particular dis.)
  If another node of same distance is encountered, we check if the current level is less or more than that of prev
  stored node at that dist. If the level is less, then we replace it. Otherwise not.
    
    class Pair {
          int data;
          int level;

          public Pair(int data, int level){
              this.data = data;
              this.level = level;
          }
    }
    
    void topview(TreeNode node, TreeMap<Integer, Pair> hm,
        int dis, int level){
        
        if(node == null)
            return;
        
        if(!hm.containsKey(dis)){
            hm.put(dis, new Pair(node.key, level));
        } else {
            if(level < hm.get(dis).level){
                hm.put(dis, new Pair(node.key, level));
            }
        }
        
        topview(node.left, hm, dis-1, level+1);
        topview(node.right, hm, dis+1, level+1);
    }
