/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author battistutta.lorenzo
 */
public class Intro extends Space {

    private int i = 0;

    private BufferStrategy strategy;

    public void run() throws InterruptedException {
        setBackground(Color.BLACK);
        setSize(800, 600);

        createBufferStrategy(4);
        strategy = getBufferStrategy();
        for (i = 100; i >= 16; i--) {
            System.out.println("i: " + i);
            disegna();
            Thread.sleep(10);
        }
        for (i = 0; i < 16; i += 1) {
            System.out.println("i: " + i);
            disegna();
        }
        Graphics g = getGraphics();
        this.paintStars(g);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.setFont(new Font("Bold", Font.PLAIN, 40 + i));
        g.drawString("Space Invaders", 100 + i, 100 + i);
        g.setFont(new Font("Bold", Font.PLAIN, 10 + i));
        g.drawString("          by Lorenzo Battistutta", 110 + i + i, 110 + i + i);
    }

    public void paintStars(Graphics g) throws InterruptedException {
        g.setColor(Color.white);
        int u1;
        int u2;
        Random r = new Random();
        int n1 = (int) (r.nextInt(800));
        int n2 = (int) (r.nextInt(600));
        for (int j = 0; j < 70; j++) {
            do {
                u1 = (int) (r.nextInt(800));
                u2 = (int) (r.nextInt(600));
            } while (!checkDistance(n1, n2, u1, u2));
            n1 = u1;
            n2 = u2;
            g.drawRect(u1, u2, 1, 1);
            g.fillRect(u1, u2, 1, 1);
            Thread.sleep(70);
        }
    }

    public boolean checkDistance(int n1, int n2, int u1, int u2) {
        boolean wide = true;
        double c = Math.abs(Math.sqrt((double) (n1 - u1) * (n1 - u1) + (n2 - u2) * (n2 - u2)));
        if (c < 400) {
            wide = false;
        }
        return wide;
    }

    void disegna() {
        //chiediamo il buffer in cui comporre la nuova immagine
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        //cancelliamo l'immagine precedente coprendola con un rettangolo nero
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);

        // scrivi nel buffer la scritta in Verde 
        g.setColor(Color.green);
        g.setFont(new Font("Bold", Font.PLAIN, 40 + i));
        g.drawString("Space Invaders", 100 + i, 100 + i);
        g.setFont(new Font("Bold", Font.PLAIN, 10 + i));
        g.drawString("          by Lorenzo Battistutta", 110 + i + i, 110 + i + i);
        //rilascia risorse create per la composizione di questa immagine
        g.dispose();
        //visualizza la nuova immagine sullo schermo
        strategy.show();
    }

}
