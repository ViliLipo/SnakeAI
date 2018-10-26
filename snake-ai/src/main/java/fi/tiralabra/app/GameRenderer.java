/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tiralabra.app;

import fi.tiralabra.game.GameArea;

/**
 *
 * @author vili
 */
public interface GameRenderer {

    /**
     * Render the game area to output the view
     *
     * @param area the GameArea to be rendered
     */
    public void renderGame(GameArea area);

    /**
     * Reset view
     */
    public void reset();

}
