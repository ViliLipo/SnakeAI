/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.algorithms;

import java.util.Comparator;

/**
 *
 * @author vili
 */
public class AStarComparator implements Comparator{
    
    public AStarComparator() {
        
    }
    

    @Override
    public int compare(Object t, Object t1) {
        AStarElement i = (AStarElement) t;
        AStarElement i1 = (AStarElement) t1;
        return Integer.compare(i.getFscore(), i1.getFscore());
    }
}
