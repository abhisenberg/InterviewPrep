static int eggDrop(int eggs, int floors){
	    int[][] ed = new int[eggs+1][floors+1];
	    
	    /*
	    For number of eggs = 0, number of trials = 0
	    For number of eggs = 1, number of trials = number of floors.
	    */
	    for(int f=0; f<=floors; f++){
	        ed[1][f] = f;
	    }
	    
	    /*
	    For number of floors = 1, no. of trials = 1, not matter how
	    many eggs are there.
	    */
	    for(int e=0; e<=eggs; e++){
	        ed[e][1] = 1;
	    }
	    
	    
	    for(int e=2; e<=eggs; e++){
	        for(int f=2; f<=floors; f++){
	            
	            //cf = concerned floor
	            ed[e][f] = Integer.MAX_VALUE;
	            for(int cf=1; cf <= f; cf++){
	                int temp = 1 + Math.max( ed[e-1][cf-1], ed[e][f-cf]);
	                ed[e][f] = Math.min(temp, ed[e][f]);
	            }
	            
	        }
	    }
	    
	    return ed[eggs][floors];
	}
