/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.openorpg.tileengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Raymond Cox
 */
public class Main {

    public static class MainFrame extends JFrame {
        private BufferedImage tileset = null;
        private final JPanel viewport;
        public MainFrame() {
            try {
                tileset = ImageIO.read(new File("tileset.png"));
            } catch(Exception ex) {
                System.err.println("Unable to find tileset.png!");
            }

            viewport = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    g.setColor(Color.black);
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    int tileNum = 9;
                    int mapWidth = 10;
                    int mapHeight = 10;

                    if (tileset != null) {
                        int offX = this.getWidth()/2-32*mapWidth/2;
                        int offY = this.getHeight()/2-32*mapHeight/2;
                        for (int x=0; x<mapWidth; x++) {
                            for (int y=0; y<mapHeight; y++) {
                                int sx = tileNum % 8 * 32;
                                int sy = tileNum / 8 * 32;
                                int dx = x*32+offX;
                                int dy = y*32+offY;
                                g.drawImage(tileset, dx, dy, dx+32, dy+32, sx, sy, sx + 32, sy + 32, null);
                            }
                        }
                        
                    }
                }
            };
            this.setTitle("Tile Engine");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setPreferredSize(new Dimension(400,400));
            this.add(viewport);
            this.pack();
            this.setLocationRelativeTo(null);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

}
