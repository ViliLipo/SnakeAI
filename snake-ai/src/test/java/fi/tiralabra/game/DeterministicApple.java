/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.game;


/**
 *
 * @author vili
 */
public class DeterministicApple extends Apple {

    private Location[] locations;
    private int index;
    private GameArea area;

    public DeterministicApple(GameArea area) {
        super(area);
        this.index = 0;
        buildLocations();
        this.area = area;
    }

    @Override
    public void placeApple() throws Exception {
        if (this.index <= 25) {
            this.index = 0;
        }
        Location loc = this.locations[this.index];
        while (this.area.checkCollision(loc) && this.index < 25) {
            loc = this.locations[this.index];
            this.index++;
        }
        if (this.index == 25) {
            super.placeApple();
        } else {
            this.area.placeApple(loc);
        }
    }

    private void buildLocations() {
        this.locations = new Location[25];
        int locindex = 0;
        for (int i = 5; i <= 10; i++) {
            this.locations[locindex] = new Location(i, 6, area);
            locindex++;
        }
        for (int i = 5; i <= 20; i++) {
            this.locations[locindex] = new Location(10, i, area);
            locindex++;
        }
        this.locations[locindex] = new Location(8, 23, area);
        locindex++;
        this.locations[locindex] = new Location(12, 22, area);
        locindex++;
        this.locations[locindex] = new Location(7, 21, area);
    }

}
