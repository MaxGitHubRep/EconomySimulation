package economysimulation.classes.gui.mainpanels.extra.settings;

import economysimulation.classes.global.Methods;
import economysimulation.classes.managers.popup.positions.FramePosition;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import static economysimulation.classes.global.Methods.ThemeHandler;

/**
 * @author Max Carter
 */
public class Settings extends javax.swing.JPanel implements ThemeUpdateEvent {

    /** List of text which defines whether the changes are saved or not. */
    private final String[] States = new String[]{ "Save Changes", "Changes Saved" };
    
    //Variables that control which option is picked.
    public int oldIndex = 0, selectedOption = 0;
    
    //label and button lists.
    private JLabel[] stateLabels;
    private JPanel[] backPanels, settingBack, settingColor;
    
    private JRadioButton[] buttons;
    
    /** The semi-circle that sits in the middle of the theme. */
    private ThemeMiddle middle;
    
    public Settings() {
        initComponents();
        
        //adds memory saver description.
        memSaverOption.setSelected(Methods.MemorySaver);
        memSaverDesc.setText("<html>The memory saver option will remove all animations."
                + "Certain things will not look as nice but it will help performance.</html>");
        
        //adds the custom theme middle to the panel.
        middle = new ThemeMiddle();
        middle.setSize(360, 180);
        mid.add(middle);
        repaint();
        
        //formats the buttons and labels.
        settingColor = new JPanel[]{ color1, color2, color3, color4 };
        settingBack = new JPanel[]{ back6, back7, back8, back9 };
        
        stateLabels = new JLabel[]{ state1, state2, state3, state4 };
        backPanels = new JPanel[]{ back1, back2, back3, back4 };
        
        for (int i = 0; i < settingBack.length; i++) {
            Format.addButtonFormat(settingBack[i], settingColor[i]);
            optionPositionEvent(i);
        }
        
        Format.addButtonFormat(back5, null);
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], null);
            addEventListener(i);
        }
        
        buttons = new JRadioButton[]{ rb1, rb2, rb3, rb4, rb5 };
        int index = 0;
        for (JRadioButton btn : buttons) {
            btn.setActionCommand(index + "");
            index++;
        }
        
        ThemeHandler.addThemeUpdateListener(this);
    }
    
    /**
     * Changes the position of a pop-up.
     * @param id Index of button is list.
     */
    private void optionPositionEvent(int id) {
        settingBack[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedOption = id;
                buttonGroup.clearSelection();
                buttons[FramePosition.getPositionFromId(id)].setSelected(true);
                
            }
        });
    }
    
    /**
     * Adds hover listeners to a label.
     * @param id Index of label in list.
     */
    private void addEventListener(int id) {
        //adds listeners for mouse enter and exit.
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseEntered(MouseEvent e) {
                middle.sector = 3 - id;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                middle.sector = 4;
                repaint();
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                //load new theme when clicked.
                if (stateLabels[id].getText().equals("Unselected")){
                    ThemeHandler.setTheme(ThemeHandler.Themes[id]);
                    stateLabels[id].setText("Selected");
                    stateLabels[oldIndex].setText("Unselected");
                    oldIndex = id;
                }
            }
        });
    }
    
    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ rbbackground, back1, back2, back3, back4, back5, back6, back7, back8, back9, color1, color2, color3, color4 }, new JPanel[]{ top1, top2, top3, top4 });
        updater.applyTextThemes(new JLabel[]{ state1, state2, state3, state4, saveChanges, title6, title7, title8, title9 }, new JLabel[]{ label1, label2, label3, label4 });
        updater.applyRadioButtonThemes(new JRadioButton[]{ memSaverOption, rb1, rb2, rb3, rb4 }, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        mid = new javax.swing.JPanel();
        back1 = new javax.swing.JPanel();
        top1 = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        state1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        top2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        state2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        top3 = new javax.swing.JPanel();
        label3 = new javax.swing.JLabel();
        state3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        top4 = new javax.swing.JPanel();
        label4 = new javax.swing.JLabel();
        state4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbbackground = new javax.swing.JPanel();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        rb4 = new javax.swing.JRadioButton();
        rb5 = new javax.swing.JRadioButton();
        back5 = new javax.swing.JPanel();
        saveChanges = new javax.swing.JLabel();
        back6 = new javax.swing.JPanel();
        title6 = new javax.swing.JLabel();
        color1 = new javax.swing.JPanel();
        back7 = new javax.swing.JPanel();
        title7 = new javax.swing.JLabel();
        color2 = new javax.swing.JPanel();
        back8 = new javax.swing.JPanel();
        title8 = new javax.swing.JLabel();
        color3 = new javax.swing.JPanel();
        back9 = new javax.swing.JPanel();
        title9 = new javax.swing.JLabel();
        color4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        memSaverOption = new javax.swing.JRadioButton();
        memSaverTitle = new javax.swing.JLabel();
        memSaverDesc = new javax.swing.JLabel();

        setOpaque(false);

        mid.setOpaque(false);

        javax.swing.GroupLayout midLayout = new javax.swing.GroupLayout(mid);
        mid.setLayout(midLayout);
        midLayout.setHorizontalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        midLayout.setVerticalGroup(
            midLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        back1.setBackground(new java.awt.Color(255, 255, 255));
        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top1.setBackground(new java.awt.Color(204, 0, 0));

        label1.setBackground(new java.awt.Color(204, 0, 0));
        label1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("WHITE");

        javax.swing.GroupLayout top1Layout = new javax.swing.GroupLayout(top1);
        top1.setLayout(top1Layout);
        top1Layout.setHorizontalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top1Layout.setVerticalGroup(
            top1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state1.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state1.setForeground(new java.awt.Color(204, 0, 0));
        state1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state1.setText("Selected");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state1)
                .addContainerGap())
        );

        back2.setBackground(new java.awt.Color(255, 255, 255));
        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top2.setBackground(new java.awt.Color(204, 0, 0));

        label2.setBackground(new java.awt.Color(204, 0, 0));
        label2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 255));
        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("DARK");

        javax.swing.GroupLayout top2Layout = new javax.swing.GroupLayout(top2);
        top2.setLayout(top2Layout);
        top2Layout.setHorizontalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top2Layout.setVerticalGroup(
            top2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state2.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state2.setForeground(new java.awt.Color(204, 0, 0));
        state2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state2.setText("Unselected");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(top2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state2)
                .addContainerGap())
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));
        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top3.setBackground(new java.awt.Color(204, 0, 0));

        label3.setBackground(new java.awt.Color(204, 0, 0));
        label3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 255, 255));
        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("OCEAN");

        javax.swing.GroupLayout top3Layout = new javax.swing.GroupLayout(top3);
        top3.setLayout(top3Layout);
        top3Layout.setHorizontalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top3Layout.setVerticalGroup(
            top3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state3.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state3.setForeground(new java.awt.Color(204, 0, 0));
        state3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state3.setText("Unselected");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(top3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state3)
                .addContainerGap())
        );

        back4.setBackground(new java.awt.Color(255, 255, 255));
        back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        top4.setBackground(new java.awt.Color(204, 0, 0));

        label4.setBackground(new java.awt.Color(204, 0, 0));
        label4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 255, 255));
        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("PINK");

        javax.swing.GroupLayout top4Layout = new javax.swing.GroupLayout(top4);
        top4.setLayout(top4Layout);
        top4Layout.setHorizontalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );
        top4Layout.setVerticalGroup(
            top4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        state4.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        state4.setForeground(new java.awt.Color(204, 0, 0));
        state4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        state4.setText("Unselected");

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(state4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addComponent(top4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(state4)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        rbbackground.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup.add(rb1);
        rb1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        rb1.setForeground(new java.awt.Color(204, 0, 0));
        rb1.setText("Top Left");
        rb1.setBorder(null);
        rb1.setFocusPainted(false);
        rb1.setOpaque(false);

        buttonGroup.add(rb2);
        rb2.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        rb2.setForeground(new java.awt.Color(204, 0, 0));
        rb2.setText("Top Right");
        rb2.setBorder(null);
        rb2.setFocusPainted(false);
        rb2.setOpaque(false);

        buttonGroup.add(rb3);
        rb3.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        rb3.setForeground(new java.awt.Color(204, 0, 0));
        rb3.setText("Bottom Left");
        rb3.setBorder(null);
        rb3.setFocusPainted(false);
        rb3.setOpaque(false);

        buttonGroup.add(rb4);
        rb4.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        rb4.setForeground(new java.awt.Color(204, 0, 0));
        rb4.setText("Bottom Right");
        rb4.setBorder(null);
        rb4.setFocusPainted(false);
        rb4.setOpaque(false);

        buttonGroup.add(rb5);
        rb5.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        rb5.setForeground(new java.awt.Color(204, 0, 0));
        rb5.setText("Center");
        rb5.setBorder(null);
        rb5.setFocusPainted(false);
        rb5.setOpaque(false);

        javax.swing.GroupLayout rbbackgroundLayout = new javax.swing.GroupLayout(rbbackground);
        rbbackground.setLayout(rbbackgroundLayout);
        rbbackgroundLayout.setHorizontalGroup(
            rbbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rbbackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rbbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rbbackgroundLayout.createSequentialGroup()
                        .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rbbackgroundLayout.createSequentialGroup()
                        .addComponent(rb3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb4)))
                .addContainerGap())
            .addGroup(rbbackgroundLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(rb5)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        rbbackgroundLayout.setVerticalGroup(
            rbbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rbbackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rbbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb1)
                    .addComponent(rb2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rb5)
                .addGap(93, 93, 93)
                .addGroup(rbbackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb3)
                    .addComponent(rb4))
                .addContainerGap())
        );

        back5.setBackground(new java.awt.Color(255, 255, 255));
        back5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back5MouseClicked(evt);
            }
        });

        saveChanges.setFont(new java.awt.Font("Agency FB", 0, 40)); // NOI18N
        saveChanges.setForeground(new java.awt.Color(204, 0, 0));
        saveChanges.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveChanges.setText("Save Changes");

        javax.swing.GroupLayout back5Layout = new javax.swing.GroupLayout(back5);
        back5.setLayout(back5Layout);
        back5Layout.setHorizontalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        back5Layout.setVerticalGroup(
            back5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbbackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbbackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        back6.setBackground(new java.awt.Color(255, 255, 255));
        back6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        title6.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title6.setForeground(new java.awt.Color(204, 0, 0));
        title6.setText("Event");

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout back6Layout = new javax.swing.GroupLayout(back6);
        back6.setLayout(back6Layout);
        back6Layout.setHorizontalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back6Layout.setVerticalGroup(
            back6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        back7.setBackground(new java.awt.Color(255, 255, 255));
        back7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        title7.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title7.setForeground(new java.awt.Color(204, 0, 0));
        title7.setText("Hint (Urgent)");

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout back7Layout = new javax.swing.GroupLayout(back7);
        back7.setLayout(back7Layout);
        back7Layout.setHorizontalGroup(
            back7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title7, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back7Layout.setVerticalGroup(
            back7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        back8.setBackground(new java.awt.Color(255, 255, 255));
        back8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        title8.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title8.setForeground(new java.awt.Color(204, 0, 0));
        title8.setText("Hint (Medium)");

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout back8Layout = new javax.swing.GroupLayout(back8);
        back8.setLayout(back8Layout);
        back8Layout.setHorizontalGroup(
            back8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back8Layout.setVerticalGroup(
            back8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        back9.setBackground(new java.awt.Color(255, 255, 255));
        back9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        title9.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title9.setForeground(new java.awt.Color(204, 0, 0));
        title9.setText("Hint (Low)");

        color4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color4Layout = new javax.swing.GroupLayout(color4);
        color4.setLayout(color4Layout);
        color4Layout.setHorizontalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color4Layout.setVerticalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout back9Layout = new javax.swing.GroupLayout(back9);
        back9.setLayout(back9Layout);
        back9Layout.setHorizontalGroup(
            back9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title9, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        back9Layout.setVerticalGroup(
            back9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 5, 1, 1, new java.awt.Color(204, 0, 0)));

        memSaverOption.setBackground(new java.awt.Color(255, 255, 255));
        memSaverOption.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        memSaverOption.setForeground(new java.awt.Color(204, 0, 0));
        memSaverOption.setText("Memory Saver");
        memSaverOption.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                memSaverOptionStateChanged(evt);
            }
        });

        memSaverTitle.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        memSaverTitle.setForeground(new java.awt.Color(204, 0, 0));
        memSaverTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        memSaverTitle.setText("Memory Saver");

        memSaverDesc.setFont(new java.awt.Font("Agency FB", 0, 20)); // NOI18N
        memSaverDesc.setForeground(new java.awt.Color(204, 0, 0));
        memSaverDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        memSaverDesc.setText("Memory Saver");
        memSaverDesc.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(memSaverOption)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(memSaverTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(memSaverDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(memSaverTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memSaverDesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memSaverOption)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(mid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void back5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back5MouseClicked
        //If an option is not saved, it will save the new position of the specific pop-ip frame.
        int option = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
        if (saveChanges.getText().equals(States[0]) && FramePosition.getPositionFromId(selectedOption) != option) {
            saveChanges.setText(States[1]);
            FramePosition.setPositionById(selectedOption, option);
        } else {
            saveChanges.setText(States[0]);
        }
    }//GEN-LAST:event_back5MouseClicked

    private void memSaverOptionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_memSaverOptionStateChanged
        //updates the memory saver option.
        Methods.MemorySaver = memSaverOption.isSelected();
    }//GEN-LAST:event_memSaverOptionStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JPanel back4;
    private javax.swing.JPanel back5;
    private javax.swing.JPanel back6;
    private javax.swing.JPanel back7;
    private javax.swing.JPanel back8;
    private javax.swing.JPanel back9;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JPanel color3;
    private javax.swing.JPanel color4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel memSaverDesc;
    private javax.swing.JRadioButton memSaverOption;
    private javax.swing.JLabel memSaverTitle;
    private javax.swing.JPanel mid;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JRadioButton rb5;
    private javax.swing.JPanel rbbackground;
    private javax.swing.JLabel saveChanges;
    private javax.swing.JLabel state1;
    private javax.swing.JLabel state2;
    private javax.swing.JLabel state3;
    private javax.swing.JLabel state4;
    private javax.swing.JLabel title6;
    private javax.swing.JLabel title7;
    private javax.swing.JLabel title8;
    private javax.swing.JLabel title9;
    private javax.swing.JPanel top1;
    private javax.swing.JPanel top2;
    private javax.swing.JPanel top3;
    private javax.swing.JPanel top4;
    // End of variables declaration//GEN-END:variables
}
