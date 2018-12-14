package economysimulation.classes.gui.startup;

import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 *
 * @author Max Carter
 */
public class Tutorial extends javax.swing.JPanel implements ThemeUpdateEvent {

    protected String[] descriptions = new String[]{
        "You can change the tax rates before the simulation starts by moving the slider and then clicking the 'Spend Money' button.<br>You need to gain tax revenue to finance spending, high interest rates will make consumers have more money from savings",
        "You can also change the budget spending before the simulation by moving the slider and then clicking the 'Spend Money'<br>button. Different sectors will boost the economy in different ways when you spend money on them",
        "The 'Corporations' tab in the simulation will give you information regarding businesses and investment",
        "The 'Consumers' tab in the simulation will give you information regarding<br>consumer behaviour, income, savings, population and employment",
        "In the settings tab you can change the theme of the GUI to Dark (Black), Ocean (Blue), Pink, or White (Default)",
        "The slider in the top left of the simulation will allow you to change how quick or slow days in the simulation will be",
        "Hints can appear in the bottom right hand corner of the screen which will tell<br>you something important. The position can bealtered in the 'Settings' tab",
        "In the 'Overview' tab, you can check the GDP trend rate over the course of the game, as well as checking your username<br>and connection status. If you commit bankruptcy it will end the simulation and take you to the game over screen",
        "If you don't spend money on a certain sector you might experience an 'Event'<br>which will keep dropping the political influence until you spend money on it"
    };
    
    protected int selectedImage = 0,
            images = 9;
    
    private ImageIndexer indexer;
    
    /**
     * Creates new form Tutorial
     */
    public Tutorial() {
        initComponents();
        picDesc.setText(String.format("<html>%s.</html>", descriptions[0]));
        
        indexer = new ImageIndexer();
        imageIndexPanel.add(indexer);
        indexer.setSize(342, 70);
        
        ThemeHandler.addThemeUpdateListener(this);
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(new JLabel[]{ title, arrowLeft, arrowRight }, null);
    }

    private class ImageIndexer extends JPanel implements ThemeUpdateEvent {

        private final int Height = 70, Width = 342;
        
        private JLabel[] labels;
        
        public ImageIndexer() {
            labels = new JLabel[images];
            super.setOpaque(false);
            super.setSize(Width, Height);
            initImages();
            ThemeHandler.addThemeUpdateListener(this);
        }
        
        private void initImages() {
            int width = (int) Math.floor(Width/images);
            for (int i = 0; i < images; i++) {
                labels[i] = new JLabel("   " + (i == selectedImage ? "_" : "O") + "   ");
                this.add(labels[i]);
                labels[i].setSize(width, Height);
                labels[i].setLocation(i * width, 0);
                labels[i].setHorizontalAlignment(SwingConstants.CENTER);
                labels[i].setVerticalAlignment(SwingConstants.CENTER);
                labels[i].setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
                labels[i].addMouseListener(new MouseEventClick(i));
            }
        }
        
        class MouseEventClick extends MouseAdapter {
            
            int index;
            
            public MouseEventClick(int index) {
                this.index = index;
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelectedImage(index);
            }
        }
        
        private void setSelectedImage(int index) {
            labels[selectedImage].setText("   O   ");
            selectedImage = index;
            labels[selectedImage].setText("   _   ");
            picHold.setIcon(new ImageIcon(getClass().getResource("/economysimulation/resources/tutorialimages/image" + (selectedImage+1) + ".jpg")));
            picDesc.setText(String.format("<html>%s.</html>", descriptions[index]));
        }
        
        @Override
        public void onThemeUpdate(GraphicUpdater updater) {
            updater.applyTextThemes(labels, null);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        arrowLeft = new javax.swing.JLabel();
        arrowRight = new javax.swing.JLabel();
        imageIndexPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        picHold = new javax.swing.JLabel();
        picDesc = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Agency FB", 0, 90)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Tutorial");

        arrowLeft.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrowLeft.setForeground(new java.awt.Color(204, 0, 0));
        arrowLeft.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        arrowLeft.setText("<");
        arrowLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrowLeftMouseClicked(evt);
            }
        });

        arrowRight.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrowRight.setForeground(new java.awt.Color(204, 0, 0));
        arrowRight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        arrowRight.setText(">");
        arrowRight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrowRightMouseClicked(evt);
            }
        });

        imageIndexPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imageIndexPanelLayout = new javax.swing.GroupLayout(imageIndexPanel);
        imageIndexPanel.setLayout(imageIndexPanelLayout);
        imageIndexPanelLayout.setHorizontalGroup(
            imageIndexPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
        imageIndexPanelLayout.setVerticalGroup(
            imageIndexPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        picHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/tutorialimages/image1.jpg"))); // NOI18N

        picDesc.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        picDesc.setForeground(new java.awt.Color(204, 0, 0));
        picDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picDesc.setText("<description>");
        picDesc.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(arrowLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imageIndexPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arrowRight, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                        .addComponent(picDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(imageIndexPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(arrowRight, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(arrowLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(picDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void arrowRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowRightMouseClicked
        if (selectedImage+1 == images) {
            indexer.setSelectedImage(0);
        } else {
            indexer.setSelectedImage(selectedImage+1);
        }
    }//GEN-LAST:event_arrowRightMouseClicked

    private void arrowLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arrowLeftMouseClicked
        if (selectedImage == 0) {
            indexer.setSelectedImage(images-1);
        } else {
            indexer.setSelectedImage(selectedImage-1);
        }
    }//GEN-LAST:event_arrowLeftMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrowLeft;
    private javax.swing.JLabel arrowRight;
    private javax.swing.JPanel imageIndexPanel;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel picDesc;
    private javax.swing.JLabel picHold;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
