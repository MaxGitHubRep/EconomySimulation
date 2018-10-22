package economysimulation.classes.gui.startup;

import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Max Carter
 */
public class Tutorial extends javax.swing.JPanel implements ThemeUpdateEvent {

    //pic hold: 1300x720
    
    protected int selectedImage = 0,
            images = 6;
    
    private ImageIndexer indexer;
    
    /**
     * Creates new form Tutorial
     */
    public Tutorial() {
        initComponents();
        indexer = new ImageIndexer();
        imageIndexPanel.add(indexer);
        indexer.setSize(342, 70);
        
        ThemeManager.addThemeUpdateListener(this);
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ this }, null);
        updater.applyTextThemes(new JLabel[]{ title, arrowLeft, arrowRight }, null);
    }

    private class ImageIndexer extends JPanel implements ThemeUpdateEvent {

        private final int Height = 70, Width = 342;
        private int localIndex;
        
        private JLabel[] labels;
        
        public ImageIndexer() {
            labels = new JLabel[images];
            super.setOpaque(false);
            super.setSize(Width, Height);
            initImages();
            ThemeManager.addThemeUpdateListener(this);
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
                localIndex = i;
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
            //Methods.TutorialDisplay.picHold.setIcon(new ImageIcon(getClass().getResource("/economysimulation/resources/tutorialimages/image" + selectedImage + ".png")));
        }
        
        @Override
        public void updateThemeEvent(GraphicUpdater updater) {
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
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        picHold = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Agency FB", 0, 90)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Tutorial");

        arrowLeft.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrowLeft.setForeground(new java.awt.Color(204, 0, 0));
        arrowLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arrowLeft.setText("<");
        arrowLeft.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        arrowLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                arrowLeftMouseClicked(evt);
            }
        });

        arrowRight.setFont(new java.awt.Font("Agency FB", 0, 72)); // NOI18N
        arrowRight.setForeground(new java.awt.Color(204, 0, 0));
        arrowRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 45, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addContainerGap())
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(arrowLeft)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imageIndexPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(arrowRight)
                .addGap(441, 441, 441))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(534, 534, 534)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrowRight, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrowLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(imageIndexPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(picHold, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel picHold;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
