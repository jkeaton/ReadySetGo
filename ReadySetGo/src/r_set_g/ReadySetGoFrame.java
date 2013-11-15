/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package r_set_g;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Jestin
 */
public class ReadySetGoFrame extends javax.swing.JFrame{

    Dealer sam;
    Validator joe;
    Game game;
    ArrayList<Integer> currentSet;
    Map<Integer,JButton> buttonMap = new HashMap<Integer, JButton>();
    
    boolean[][] selections = {{false,false,false,false,false},
                              {false,false,false,false,false},
                              {false,false,false,false,false}};
    ArrayList<JButton> buttonsSelected;
    Border selected = new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true);
    Border badSet = new javax.swing.border.LineBorder(java.awt.Color.red, 6, true);
    
    /**
     * Creates new form ReadySetGoFrame
     */
    public ReadySetGoFrame() {
        initComponents();
        // Set the button positions
        buttonMap.put(0, jButton2);
        buttonMap.put(1, jButton17);
        buttonMap.put(2, jButton18);
        buttonMap.put(3, jButton19);
        buttonMap.put(4, jButton20);
        buttonMap.put(5, jButton21);
        buttonMap.put(6, jButton22);
        buttonMap.put(7, jButton23);
        buttonMap.put(8, jButton24);
        buttonMap.put(9, jButton25);
        buttonMap.put(10, jButton26);
        buttonMap.put(11, jButton27);
        buttonMap.put(12, jButton28);
        buttonMap.put(13, jButton29);
        buttonMap.put(14, jButton30);
        currentSet = new ArrayList<Integer>();
        buttonsSelected = new ArrayList<JButton>();
        sam = new Dealer();
        joe = new Validator();
        game = new Game(sam,joe);
        game.setInitialTable();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                System.out.print("["+i+"]["+j+"] "+game.getCurrentTable()[i][j]);
            }
            System.out.println();
        }
        setButtonImages();
        System.out.println();
    }
    
    private void resetSelections(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                selections[i][j] = false;
            }
        }
    }
    
    private void setButtonImages(){
        /* This is to be called to set the button images for the user */
        /* It's buggy right now -> I'm in the middle of fixing it.    */
        Iterator it = buttonMap.entrySet().iterator();
        for(Integer i = 0; i < 15; i++){
            if(game.getCardAtPos(i) != null)
                buttonMap.get(i).setIcon((Icon)game.getCardAtPos(i).getIconImage());
            else
                buttonMap.get(i).setIcon(null);
        }
    }
    
    public void testSet() throws InterruptedException{
        // TODO: If the 3 selected cards are a set, clear the correct button Icons,
        // set the images of the left labels to the Icons from the valid set.
        // If 3 selected are not a set, indicate them in red, Thread.sleep(300),
        // reset the borders to be hidden, and reset them to be yellow.
        int pos1 = currentSet.get(0);
        int pos2 = currentSet.get(1);
        int pos3 = currentSet.get(2);
        Icon[] icons;
        if(game.positionsAreSet(pos1,pos2,pos3)){
            icons = game.getSetIcons(pos1, pos3, pos3);
            game.removeSelection(pos3, pos3, pos3);
            game.reorganizeCards();
            if(game.setAvailable()){
                if(game.getTableCardCount() >= 12){
                    // Do nothing, don't add a row
                }
                else{
                    if(!sam.currentIsEmpty())
                        game.addColumn();
                    else{
                        // Do not add a row, no more cards in deck to deal from
                    }
                }
            }
            else{ // no sets remain
                if(game.getTableCardCount() <= 12){
                    game.addColumn();
                }
                else{
                    // It would be better to provide a game over screen. 
                    // That can happen next
                    System.err.println("Game Over");
                }
            }
            /**
            for(Integer a: currentSet)
                System.err.println(a);*/
            
            for(Integer a: currentSet)
                System.err.println(game.getCardAtPos(a));
        }
        else{ // Selected cards aren't a set
            for(JButton b: buttonsSelected){
                b.setBorderPainted(false);
                b.setBorder(badSet);
                b.setBorderPainted(rootPaneCheckingEnabled);
            }
            Thread.sleep(500);
            for(JButton b: buttonsSelected){
                b.setBorderPainted(false);   
                b.setBorder(selected);
                b.setBorderPainted(rootPaneCheckingEnabled);
            }
            /**
            for(Integer a: currentSet)
                System.err.println(a);*/
            
            for(Integer a: currentSet)
                System.err.println(game.getCardAtPos(a));
            System.err.println("card count: " + game.getTableCardCount());
        }
        for(JButton b: buttonsSelected){
            b.setBorderPainted(false);
        }
        buttonsSelected.clear();
        currentSet.clear();
        resetSelections();
    }
    
    private void buttonAction(JButton button, int row, int col, int pos){
        //System.out.println("Called buttonAction");
        if(selections[row][col]==false){
            button.setBorderPainted(rootPaneCheckingEnabled);
            selections[row][col]=true;
            currentSet.add((Integer)(pos));
            buttonsSelected.add(button);
        }
        else{
            button.setBorderPainted(false);
            selections[row][col]=false;
            currentSet.remove((Integer)(pos));
            buttonsSelected.remove(button);
        }
        if(currentSet.size()==3){
            try {
                testSet();
            } catch (InterruptedException ex) {
                Logger.getLogger(ReadySetGoFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 227, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ready-SET-Go!");
        setBackground(new java.awt.Color(255, 204, 204));
        setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), new java.awt.Color(0, 102, 204)));
        jPanel1.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Your Last Set");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator1.setBackground(new java.awt.Color(0, 255, 204));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 255));

        jPanel5.setBackground(new java.awt.Color(0, 102, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 6, 6));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(119, 508));
        jPanel5.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jPanel5.add(jLabel4);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jPanel5.add(jLabel5);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jPanel5.add(jLabel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 164, -1, 540));

        jPanel2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), new java.awt.Color(0, 102, 204)));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(650, 530));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 66, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Available Cards");
        jLabel3.setToolTipText("");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator3.setBackground(new java.awt.Color(51, 255, 0));
        jSeparator3.setForeground(new java.awt.Color(0, 102, 0));

        jPanel4.setBackground(new java.awt.Color(0, 153, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(3, 5, 9, 5));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setName("1"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setFocusPainted(false);
        jButton17.setName("2"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton17);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton18.setBorderPainted(false);
        jButton18.setContentAreaFilled(false);
        jButton18.setFocusPainted(false);
        jButton18.setName("3"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton18);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setFocusPainted(false);
        jButton19.setName("4"); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton19);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setFocusPainted(false);
        jButton20.setName("5"); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton20);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setFocusPainted(false);
        jButton21.setName("6"); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton21);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton22.setBorderPainted(false);
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusPainted(false);
        jButton22.setName("7"); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton22);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton23.setBorderPainted(false);
        jButton23.setContentAreaFilled(false);
        jButton23.setFocusPainted(false);
        jButton23.setName("8"); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton23);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton24.setBorderPainted(false);
        jButton24.setContentAreaFilled(false);
        jButton24.setFocusPainted(false);
        jButton24.setName("9"); // NOI18N
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton24);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton25.setBorderPainted(false);
        jButton25.setContentAreaFilled(false);
        jButton25.setFocusPainted(false);
        jButton25.setName("10"); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton25);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.setFocusPainted(false);
        jButton26.setName("11"); // NOI18N
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton26);

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.setFocusPainted(false);
        jButton27.setName("12"); // NOI18N
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton27);

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setFocusPainted(false);
        jButton28.setName("13"); // NOI18N
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton28);

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setFocusPainted(false);
        jButton29.setName("14"); // NOI18N
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton29);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/blankCard_150.png"))); // NOI18N
        jButton30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 51), 6, true));
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.setFocusPainted(false);
        jButton30.setName("15"); // NOI18N
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton30);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 164, 630, 540));

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/Set Game5.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 255), new java.awt.Color(0, 102, 204)));
        jLabel1.setPreferredSize(new java.awt.Dimension(788, 147));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/setbg.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 710));

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/GreenButton.png"))); // NOI18N
        jMenuItem1.setText("New Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/pauseButton.png"))); // NOI18N
        jMenuItem2.setText("Pause");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/r_set_g/red_x_button.png"))); // NOI18N
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
         new ReadySetGoFrame().setVisible(true);
        ReadySetGoFrame.this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        buttonAction(jButton2, 0, 0, 0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton17, 0, 1, 1);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton18, 0, 2, 2);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton19, 0, 3, 3);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton20, 0, 4, 4);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton21, 1, 0, 5);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton22, 1, 1, 6);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton23, 1, 2, 7);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton24, 1, 3, 8);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton25, 1, 4, 9);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton26, 2, 0, 10);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton27, 2, 1, 11);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton28, 2, 2, 12);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton29, 2, 3, 13);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        buttonAction(jButton30, 2, 4, 14);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReadySetGoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReadySetGoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReadySetGoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReadySetGoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReadySetGoFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
