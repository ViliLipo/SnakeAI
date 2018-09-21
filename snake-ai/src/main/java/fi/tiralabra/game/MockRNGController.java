/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author vili
 */
public class MockRNGController implements Controller {
    private final String method = "Random";
    
    public MockRNGController() {
        
    }

    @Override
    public int getDirection() {
        int seed = ThreadLocalRandom.current().nextInt(1, 1001);
        if (seed < 250) {
            return -2;
        } else if (seed < 500) {
            return -1;
        } else if (seed < 750) {
            return 1;
        } else {
            return 2;
        }
    }
    @Override
    public String getMethod() {
        return this.method;
    }
    @Override
    public int getTimePassed() {
        return 0;
    }
    
}
