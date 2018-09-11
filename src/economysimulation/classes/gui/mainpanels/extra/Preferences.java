package economysimulation.classes.gui.mainpanels.extra;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.exception.InvalidThemeSetupException;
import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.managers.themes.ThemeTypes;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Max Carter
 */
public class Preferences extends javax.swing.JPanel {

    private String[] order;
    private final String[] themes = new String[]{ "White", "Dark", "Ocean", "Nature" };
    public static final Color[][] colors = new Color[][]{ ThemeTypes.WHITE, ThemeTypes.DARK, ThemeTypes.OCEAN, ThemeTypes.NATURE };
    
    private  JLabel[] labels;

    private void moveSpinner(boolean up) {
        if (applyBtn.getText().equals("Applied")) applyBtn.setText("Apply");
        if (up) {
            String firstOrder = order[0];
            for (int i = 0; i < order.length-1; i++) {
                order[i] = order[i+1];
            }
            order[order.length-1] = firstOrder;
            
        } else {
            String lastOrder = order[order.length-1];
            for (int i = order.length-1; i > 0; i--) {
                order[i] = order[i-1];
            }
            order[0] = lastOrder;
            
        }
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(order[i]);
        }
    }
    
    private void formatSpinner() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(themes[i]);
        }
        
        order = new String[themes.length];
        for (int i = 0; i < themes.length; i++) {
            order[i] = themes[i];
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Formats the button to open different jPanel."> 
    public void addButtonFunction(JLabel button, boolean up, boolean apply) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (apply) {
                    applyBtn.setText("Applied");
                    for (int i = 0; i < themes.length; i++) {
                        if (middle.getText().equals(themes[i])) {
                            Methods.theme = colors[i];
                            Theme.applySelectedTheme(Methods.theme);
                            Theme.updateAllPanelThemes();
                            break;
                        }
                    }
                    
                } else {
                    moveSpinner(up);
                }
            }
        });
    }//</editor-fold>
    
    public Preferences() throws InvalidThemeSetupException {
        initComponents();

        labels = new JLabel[]{ top, middle, bottom };
        
        if (colors.length != themes.length) {
            throw new InvalidThemeSetupException(themes.length, colors.length);
        }
        
        addButtonFunction(up, true, false);
        addButtonFunction(down, false, false);
        addButtonFunction(applyBtn, false, true);
        
        formatSpinner();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        themeSwapperPanel = new javax.swing.JPanel();
        top = new javax.swing.JLabel();
        middle = new javax.swing.JLabel();
        bottom = new javax.swing.JLabel();
        down = new javax.swing.JLabel();
        up = new javax.swing.JLabel();
        applyBtn = new javax.swing.JLabel();

        setOpaque(false);

        themeSwapperPanel.setBackground(new java.awt.Color(255, 255, 255));

        top.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        top.setForeground(new java.awt.Color(204, 204, 204));
        top.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        top.setText("<first>");

        middle.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        middle.setForeground(new java.awt.Color(204, 0, 0));
        middle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        middle.setText("<first>");

        bottom.setBackground(new java.awt.Color(204, 204, 204));
        bottom.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        bottom.setForeground(new java.awt.Color(204, 204, 204));
        bottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bottom.setText("<first>");

        down.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        down.setForeground(new java.awt.Color(204, 0, 0));
        down.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        down.setText("\\/");
        down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        up.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        up.setForeground(new java.awt.Color(204, 0, 0));
        up.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        up.setText("/\\");
            up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            applyBtn.setFont(new java.awt.Font("Agency FB", 0, 30)); // NOI18N
            applyBtn.setForeground(new java.awt.Color(204, 0, 0));
            applyBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            applyBtn.setText("Apply");
            applyBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            javax.swing.GroupLayout themeSwapperPanelLayout = new javax.swing.GroupLayout(themeSwapperPanel);
            themeSwapperPanel.setLayout(themeSwapperPanelLayout);
            themeSwapperPanelLayout.setHorizontalGroup(
                themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(themeSwapperPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(middle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(top, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(0, 0, 0)
                    .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(up, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(applyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)))
            );
            themeSwapperPanelLayout.setVerticalGroup(
                themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, themeSwapperPanelLayout.createSequentialGroup()
                    .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(top, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                    .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(middle, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(applyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(themeSwapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(bottom, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(themeSwapperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(714, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(themeSwapperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(568, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel applyBtn;
    public static javax.swing.JLabel bottom;
    public static javax.swing.JLabel down;
    public static javax.swing.JLabel middle;
    private javax.swing.JPanel themeSwapperPanel;
    public static javax.swing.JLabel top;
    public static javax.swing.JLabel up;
    // End of variables declaration//GEN-END:variables
}
