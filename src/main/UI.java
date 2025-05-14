package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40;

    public UI(GamePanel gp) {
        this.gp=gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
    }

    public void draw(Graphics g) {
        g.setFont(arial_40);
        g.setColor(Color.white);
        g.drawString("Moves: "+gp.player.getMoves(), 50, 50);

    }
}
