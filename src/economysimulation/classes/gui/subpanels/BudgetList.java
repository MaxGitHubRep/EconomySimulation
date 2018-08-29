package economysimulation.classes.gui.subpanels;

import economysimulation.classes.economy.Budget;
import economysimulation.classes.economy.Component;
import economysimulation.classes.economy.Formula;
import economysimulation.classes.managers.animation.NumberIncrementer;
import economysimulation.classes.managers.exception.InvalidPanelSizeException;
import economysimulation.classes.managers.exception.InvalidSectorException;
import economysimulation.classes.managers.exception.InvalidTimeException;
import economysimulation.classes.managers.popup.hint.HintManager;
import economysimulation.classes.managers.popup.hint.Hints;
import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Max Carter
 */
public class BudgetList extends javax.swing.JPanel {

    public static DecimalFormat format = new DecimalFormat("0");
    private static int selectedType = 0;
    
    private static JPanel[]
            backPanels, colorPanels;
    private static JLabel[] arrowLabels;
    public static final String[]
            titles = new String[]{
                "NHS", "Education", "Housing", "Food", "Infrastructure", "Defence", "Science", "Benefits" },
            saveTexts = new String[]{
                "Spend Money", "Money Spent", "Insufficient Funds" }; 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor."> 
    public BudgetList() throws InvalidSectorException {
        initComponents();
        
        backPanels = new JPanel[]{ panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8 };
        colorPanels = new JPanel[]{ color1, color2, color3, color4, color5, color6, color7, color8 };
        arrowLabels = new JLabel[]{ arrow1, arrow2, arrow3, arrow4, arrow5, arrow6, arrow7, arrow8 };
        
        addSaveChangesFormat(picPanel, saveChangesPanel);
        Format.addButtonFormat(saveChangesPanel, picPanel);

        for (int i = 0; i < backPanels.length; i++) {
            addButtonFormat(i);
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
        }

        addSliderListener(slider);
        applySelectedType(0);
        
        updateTheme();
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Updates content when a button is clicked."> 
    private static void applySelectedType(int id) throws InvalidSectorException {
        selectedType = id;
        title.setText(titles[id]);
        slider.setValue(0);
        saveChanges.setText(saveTexts[0]);
        spendings.setText("£" + Formula.getSectorSpending(selectedType) + "bn");
        updatePercent(false);
        
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Formats the button to change slider type."> 
    public static void addButtonFormat(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                arrowLabels[selectedType].setIcon(null);
                arrowLabels[id].setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/misc/arrow" + (id > 3 ? 2 : 1) + "40.png")));
                try {
                    applySelectedType(id);
                } catch (InvalidSectorException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Formats the save changes button."> 
    public static void addSaveChangesFormat(JPanel picPanel, JPanel backPanel) {
        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (slider.getValue() <= Component.ANNUAL_BUDGET) { 
                    int spending = 0;
                    try {
                        spending = Formula.getSectorSpending(selectedType);
                    } catch (InvalidSectorException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        new NumberIncrementer(spendings, "£%sbn", spending, (spending + slider.getValue()), 500).startIncrementer();
                    } catch (InvalidTimeException ex) {
                        ex.printStackTrace();
                    }
                    Budget.spendMoney(selectedType, slider.getValue());
                    updatePercent(true);
                    saveChanges.setText(saveTexts[1]);
                    budget.setText("£" + format.format(Component.ANNUAL_BUDGET) + "bn");
                    slider.setValue(0);

                } else {
                    saveChanges.setText(saveTexts[2]);
                    try {
                        HintManager.createNewHint(Hints.HINT_INSUFFICIENT_FUNDS);
                    } catch (InvalidPanelSizeException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Slider Event">   
    private void addSliderListener(JSlider slider) { 
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!saveChanges.getText().equals(saveTexts[0])) saveChanges.setText(saveTexts[0]);
                updatePercent(true);
            }
        });
        
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Updates the value of money displayed."> 
    private static void updatePercent(boolean animate) {
        if (!animate) budget.setText("£" + format.format(Component.ANNUAL_BUDGET) + "bn");
        spending.setText("£" + slider.getValue() + "bn");
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Updates the theme for the class.">   
    public static void updateTheme() {
        Theme.applyPanelThemes(new JPanel[]{ subBack, saveChangesPanel, picPanel }, null, backPanels, colorPanels);
        Theme.applyTextThemes(new JLabel[]{ max, min, saveChanges, spending, budget, bud, title, tot, spendings, title1, title2, title3, title4, title5, title6, title7, title8 }, null);
    }//</editor-fold> 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        color1 = new javax.swing.JPanel();
        arrow1 = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        color2 = new javax.swing.JPanel();
        arrow2 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        mainBack = new javax.swing.JPanel();
        subBack = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        spending = new javax.swing.JLabel();
        min = new javax.swing.JLabel();
        max = new javax.swing.JLabel();
        bud = new javax.swing.JLabel();
        budget = new javax.swing.JLabel();
        saveChangesPanel = new javax.swing.JPanel();
        saveChanges = new javax.swing.JLabel();
        picPanel = new javax.swing.JPanel();
        picHold = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        spendings = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        arrow3 = new javax.swing.JLabel();
        title3 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        color4 = new javax.swing.JPanel();
        arrow4 = new javax.swing.JLabel();
        title4 = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        color5 = new javax.swing.JPanel();
        arrow5 = new javax.swing.JLabel();
        title5 = new javax.swing.JLabel();
        panel6 = new javax.swing.JPanel();
        color6 = new javax.swing.JPanel();
        arrow6 = new javax.swing.JLabel();
        title6 = new javax.swing.JLabel();
        panel7 = new javax.swing.JPanel();
        color7 = new javax.swing.JPanel();
        arrow7 = new javax.swing.JLabel();
        title7 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        color8 = new javax.swing.JPanel();
        arrow8 = new javax.swing.JLabel();
        title8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color1.setBackground(new java.awt.Color(255, 255, 255));
        color1.setMinimumSize(new java.awt.Dimension(40, 80));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        title1.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title1.setForeground(new java.awt.Color(204, 0, 0));
        title1.setText("NHS");
        title1.setPreferredSize(new java.awt.Dimension(243, 80));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(color1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panel2.setBackground(new java.awt.Color(255, 255, 255));
        panel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setText("Education");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(color2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
        );

        mainBack.setBackground(new java.awt.Color(204, 0, 0));

        subBack.setBackground(new java.awt.Color(255, 255, 255));

        title.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(204, 0, 0));
        title.setText("Infrastructure");

        slider.setMaximum(300);
        slider.setFocusable(false);
        slider.setOpaque(false);
        slider.setPreferredSize(new java.awt.Dimension(282, 23));

        spending.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        spending.setForeground(new java.awt.Color(204, 0, 0));
        spending.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        spending.setText("£0bn");

        min.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        min.setForeground(new java.awt.Color(204, 0, 0));
        min.setText("£0bn");

        max.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        max.setForeground(new java.awt.Color(204, 0, 0));
        max.setText("£300bn");

        bud.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        bud.setForeground(new java.awt.Color(204, 0, 0));
        bud.setText("Spending Budget:");

        budget.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        budget.setForeground(new java.awt.Color(204, 0, 0));
        budget.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        budget.setText("£250bn");

        saveChangesPanel.setBackground(new java.awt.Color(255, 255, 255));
        saveChangesPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        saveChanges.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        saveChanges.setForeground(new java.awt.Color(204, 0, 0));
        saveChanges.setText("Spend Money");

        picPanel.setBackground(new java.awt.Color(255, 255, 255));

        picHold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/misc/wtick50.png"))); // NOI18N

        javax.swing.GroupLayout picPanelLayout = new javax.swing.GroupLayout(picPanel);
        picPanel.setLayout(picPanelLayout);
        picPanelLayout.setHorizontalGroup(
            picPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        picPanelLayout.setVerticalGroup(
            picPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picHold, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout saveChangesPanelLayout = new javax.swing.GroupLayout(saveChangesPanel);
        saveChangesPanel.setLayout(saveChangesPanelLayout);
        saveChangesPanelLayout.setHorizontalGroup(
            saveChangesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saveChangesPanelLayout.createSequentialGroup()
                .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        saveChangesPanelLayout.setVerticalGroup(
            saveChangesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(saveChanges, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        spendings.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        spendings.setForeground(new java.awt.Color(204, 0, 0));
        spendings.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        spendings.setText("£0bn");

        tot.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        tot.setForeground(new java.awt.Color(204, 0, 0));
        tot.setText("Relative Spending:");

        javax.swing.GroupLayout subBackLayout = new javax.swing.GroupLayout(subBack);
        subBack.setLayout(subBackLayout);
        subBackLayout.setHorizontalGroup(
            subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subBackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subBackLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveChangesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subBackLayout.createSequentialGroup()
                        .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subBackLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bud)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(budget, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(subBackLayout.createSequentialGroup()
                                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator2)))
                            .addGroup(subBackLayout.createSequentialGroup()
                                .addComponent(min)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(max)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(subBackLayout.createSequentialGroup()
                                .addComponent(tot)
                                .addGap(23, 23, 23)
                                .addComponent(spendings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        subBackLayout.setVerticalGroup(
            subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subBackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(spending))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(min, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(max, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveChangesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(budget)
                    .addComponent(bud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(subBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tot)
                    .addComponent(spendings)))
        );

        javax.swing.GroupLayout mainBackLayout = new javax.swing.GroupLayout(mainBack);
        mainBack.setLayout(mainBackLayout);
        mainBackLayout.setHorizontalGroup(
            mainBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainBackLayout.setVerticalGroup(
            mainBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel3.setBackground(new java.awt.Color(255, 255, 255));
        panel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(204, 0, 0));
        title3.setText("Housing");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(color3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
        );

        panel4.setBackground(new java.awt.Color(255, 255, 255));
        panel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color4Layout = new javax.swing.GroupLayout(color4);
        color4.setLayout(color4Layout);
        color4Layout.setHorizontalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color4Layout.setVerticalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        title4.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title4.setForeground(new java.awt.Color(204, 0, 0));
        title4.setText("Food");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(color4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
        );

        panel5.setBackground(new java.awt.Color(255, 255, 255));
        panel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color5Layout = new javax.swing.GroupLayout(color5);
        color5.setLayout(color5Layout);
        color5Layout.setHorizontalGroup(
            color5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color5Layout.setVerticalGroup(
            color5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        title5.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title5.setForeground(new java.awt.Color(204, 0, 0));
        title5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title5.setText("Infrastructure");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                .addComponent(color5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title5, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel6.setBackground(new java.awt.Color(255, 255, 255));
        panel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color6Layout = new javax.swing.GroupLayout(color6);
        color6.setLayout(color6Layout);
        color6Layout.setHorizontalGroup(
            color6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color6Layout.setVerticalGroup(
            color6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        title6.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title6.setForeground(new java.awt.Color(204, 0, 0));
        title6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title6.setText("Defence");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                .addComponent(color6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title6, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel7.setBackground(new java.awt.Color(255, 255, 255));
        panel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color7Layout = new javax.swing.GroupLayout(color7);
        color7.setLayout(color7Layout);
        color7Layout.setHorizontalGroup(
            color7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color7Layout.setVerticalGroup(
            color7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        title7.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title7.setForeground(new java.awt.Color(204, 0, 0));
        title7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title7.setText("Science");

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel7Layout.createSequentialGroup()
                .addComponent(color7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title7, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panel8.setBackground(new java.awt.Color(255, 255, 255));
        panel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color8Layout = new javax.swing.GroupLayout(color8);
        color8.setLayout(color8Layout);
        color8Layout.setHorizontalGroup(
            color8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        color8Layout.setVerticalGroup(
            color8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arrow8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        title8.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title8.setForeground(new java.awt.Color(204, 0, 0));
        title8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title8.setText("Benefits");

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel8Layout.createSequentialGroup()
                .addComponent(color8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title8, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(color8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(mainBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel arrow1;
    public static javax.swing.JLabel arrow2;
    public static javax.swing.JLabel arrow3;
    public static javax.swing.JLabel arrow4;
    public static javax.swing.JLabel arrow5;
    public static javax.swing.JLabel arrow6;
    public static javax.swing.JLabel arrow7;
    public static javax.swing.JLabel arrow8;
    public static javax.swing.JLabel bud;
    public static javax.swing.JLabel budget;
    private static javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JPanel color3;
    private javax.swing.JPanel color4;
    private javax.swing.JPanel color5;
    private javax.swing.JPanel color6;
    private javax.swing.JPanel color7;
    private javax.swing.JPanel color8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel mainBack;
    public static javax.swing.JLabel max;
    public static javax.swing.JLabel min;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JLabel picHold;
    public static javax.swing.JPanel picPanel;
    public static javax.swing.JLabel saveChanges;
    public static javax.swing.JPanel saveChangesPanel;
    public static javax.swing.JSlider slider;
    public static javax.swing.JLabel spending;
    public static javax.swing.JLabel spendings;
    public static javax.swing.JPanel subBack;
    public static javax.swing.JLabel title;
    public static javax.swing.JLabel title1;
    public static javax.swing.JLabel title2;
    public static javax.swing.JLabel title3;
    public static javax.swing.JLabel title4;
    public static javax.swing.JLabel title5;
    public static javax.swing.JLabel title6;
    public static javax.swing.JLabel title7;
    public static javax.swing.JLabel title8;
    private static javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
