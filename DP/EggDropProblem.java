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
	    
	    /*
	    When we drop an egg from a floor cf, there can be two cases (1) The egg breaks (2) The egg doesn’t break.

	    1) If the egg breaks after dropping from cf'th floor, then we only need to check for floors
	     lower than cf with remaining eggs; so the problem reduces to cf-1 floors and e-1 eggs
	    2) If the egg doesn’t break after dropping from the cf'th floor, then we only need to
	     check for floors higher than cf; so the problem reduces to f-cf floors and e eggs.
	    */
	    
	    for(int e=2; e<=eggs; e++){
	        for(int f=2; f<=floors; f++){
	            
		    /*
		    For a floor f, with eggs e, we have to make use of the data of all the floors from 1 to f.
		    cf : 1 to f 		//cf = concerned floor
		    and we add 1 to add the attempt for this floor also, to the attempts of cf'th floors.
		    */
	            ed[e][f] = Integer.MAX_VALUE;
	            for(int cf=1; cf <= f; cf++){
	                int temp = 1 + Math.max( ed[e-1][cf-1], ed[e][f-cf]);
	                ed[e][f] = Math.min(temp, ed[e][f]);
	            }
	            
	        }
	    }
	    
	    return ed[eggs][floors];
	}
