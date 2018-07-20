package economysimulation.classes.gui.fronter;

import economysimulation.classes.Methods;
import economysimulation.classes.gui.mainpanels.sim.Bankruptcy;
import economysimulation.classes.gui.mainpanels.hold.Budget;
import economysimulation.classes.gui.mainpanels.sim.Consumer;
import economysimulation.classes.gui.mainpanels.sim.Corporation;
import economysimulation.classes.gui.mainpanels.extra.QuitSim;
import economysimulation.classes.gui.mainpanels.hold.Rate;
import economysimulation.classes.gui.mainpanels.extra.Leaderboards;
import economysimulation.classes.gui.mainpanels.extra.Preferences;
import economysimulation.classes.gui.mainpanels.sim.Overview;
import economysimulation.classes.managers.popup.PopUpFrame;
import economysimulation.classes.managers.themes.Theme;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max
 */
public class SideBar extends javax.swing.JPanel {

    public static PopUpFrame[] frames;
    public static boolean[] framed;
    public static JPanel[] backPanels;
    public static JPanel[] colorPanels;
    public static JPanel[] opPanels;
    public static JLabel[] titles;
    public static String[] descriptions = new String[]{
        "Change the tax rates for consumers and firms, and change interest rates.<br>You can also check out the economic quarterly growth pattern",
        "Change how much you want to spend on different sectors,<br>and check it out on a pie chart",
        "View the performance of firms in your economy",
        "View consumer behaviour in your economy",
        "Declare bankcruptcy and end the game",
        "View a basic overview of all the components in your economy",
        "Check out the leaderboards against fellow economists",
        "Alter your personal preferences",
        "Quit the simulation and return to desktop"
    
    };

    public static void selectOption(JPanel backPanel, JLabel title, String description) {
        Methods.addToFrontPanel(GameHold.backadd, backPanel, false);
        GameHold.title.setText("Currently Showing: " + title.getText() );
        GameHold.description.setText("<html>" + description + ". </html>");
    }
    
    //<editor-fold defaultstate="collapsed" desc="Formats the button to open different jPanel."> 
    public static void addButtonFormat(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (id <= 1) {
                    if (framed[id]) {
                        framed[id] = false;
                        frames[id].dispose();
                    }
                }
                try {
                    selectOption(opPanels[id], titles[id], descriptions[id]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        if (id <= 1)
        backPanels[id].addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!framed[id]) {
                    framed[id] = true;
                    try {
                        frames[id] = new PopUpFrame(opPanels[id], titles[id].getText());
                        frames[id].createPopUpFrame();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Updates the theme for the class.">   
    public static void updateTheme() {
        Theme.applyPanelThemes(null, null, backPanels, colorPanels);
        Theme.applyTextThemes(titles, null);
    }//</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Constructor.">   
    public SideBar() {
        initComponents();
        
        titles = new JLabel[]{ titleGov, titleBudget, titleCorp, titleCons, titleBankr, titleOverview, titleLB, titlePreferences, titleExit };
        backPanels = new JPanel[]{ backPanel1, backPanel2, backPanel3, backPanel4, backPanel5, backPanel6, backPanel7, backPanel8, backPanel9 };
        colorPanels = new JPanel[]{ colorPanel1, colorPanel2, colorPanel3, colorPanel4, colorPanel5, colorPanel6, colorPanel7, colorPanel8, colorPanel9 };

        opPanels = new JPanel[]{
            new Rate(), 
            new Budget(),
            new Corporation(),
            new Consumer(),
            new Bankruptcy(),
            new Overview(),
            new Leaderboards(),
            new Preferences(),
            new QuitSim()
        };
        
        frames = new PopUpFrame[2];
        framed = new boolean[2];
        for (int i = 0; i < framed.length; i++) {
            framed[i] = false;
        }
        
        for (int i = 0; i < opPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            addButtonFormat(i);
        }
        
        updateTheme();
    }//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backPanel1 = new javax.swing.JPanel();
        titleGov = new javax.swing.JLabel();
        colorPanel1 = new javax.swing.JPanel();
        backPanel2 = new javax.swing.JPanel();
        titleBudget = new javax.swing.JLabel();
        colorPanel2 = new javax.swing.JPanel();
        backPanel3 = new javax.swing.JPanel();
        titleCorp = new javax.swing.JLabel();
        colorPanel3 = new javax.swing.JPanel();
        backPanel4 = new javax.swing.JPanel();
        titleCons = new javax.swing.JLabel();
        colorPanel4 = new javax.swing.JPanel();
        backPanel5 = new javax.swing.JPanel();
        titleBankr = new javax.swing.JLabel();
        colorPanel5 = new javax.swing.JPanel();
        backPanel6 = new javax.swing.JPanel();
        titleOverview = new javax.swing.JLabel();
        colorPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        backPanel7 = new javax.swing.JPanel();
        titleLB = new javax.swing.JLabel();
        colorPanel7 = new javax.swing.JPanel();
        backPanel8 = new javax.swing.JPanel();
        titlePreferences = new javax.swing.JLabel();
        colorPanel8 = new javax.swing.JPanel();
        backPanel9 = new javax.swing.JPanel();
        titleExit = new javax.swing.JLabel();
        colorPanel9 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(400, 685));

        backPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titleGov.setBackground(new java.awt.Color(255, 255, 255));
        titleGov.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleGov.setForeground(new java.awt.Color(204, 0, 0));
        titleGov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleGov.setText("Government");

        colorPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel1Layout = new javax.swing.GroupLayout(colorPanel1);
        colorPanel1.setLayout(colorPanel1Layout);
        colorPanel1Layout.setHorizontalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel1Layout.setVerticalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel1Layout = new javax.swing.GroupLayout(backPanel1);
        backPanel1.setLayout(backPanel1Layout);
        backPanel1Layout.setHorizontalGroup(
            backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel1Layout.createSequentialGroup()
                .addComponent(colorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleGov)
                .addContainerGap())
        );
        backPanel1Layout.setVerticalGroup(
            backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel1Layout.createSequentialGroup()
                .addGroup(backPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleGov, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel2.setBackground(new java.awt.Color(255, 255, 255));

        titleBudget.setBackground(new java.awt.Color(255, 255, 255));
        titleBudget.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleBudget.setForeground(new java.awt.Color(204, 0, 0));
        titleBudget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBudget.setText("Budget");

        colorPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel2Layout = new javax.swing.GroupLayout(colorPanel2);
        colorPanel2.setLayout(colorPanel2Layout);
        colorPanel2Layout.setHorizontalGroup(
            colorPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel2Layout.setVerticalGroup(
            colorPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel2Layout = new javax.swing.GroupLayout(backPanel2);
        backPanel2.setLayout(backPanel2Layout);
        backPanel2Layout.setHorizontalGroup(
            backPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel2Layout.createSequentialGroup()
                .addComponent(colorPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleBudget)
                .addContainerGap())
        );
        backPanel2Layout.setVerticalGroup(
            backPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel2Layout.createSequentialGroup()
                .addGroup(backPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(colorPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleBudget, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel3.setBackground(new java.awt.Color(255, 255, 255));

        titleCorp.setBackground(new java.awt.Color(255, 255, 255));
        titleCorp.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleCorp.setForeground(new java.awt.Color(204, 0, 0));
        titleCorp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCorp.setText("Corporations");

        colorPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel3Layout = new javax.swing.GroupLayout(colorPanel3);
        colorPanel3.setLayout(colorPanel3Layout);
        colorPanel3Layout.setHorizontalGroup(
            colorPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel3Layout.setVerticalGroup(
            colorPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel3Layout = new javax.swing.GroupLayout(backPanel3);
        backPanel3.setLayout(backPanel3Layout);
        backPanel3Layout.setHorizontalGroup(
            backPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel3Layout.createSequentialGroup()
                .addComponent(colorPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(titleCorp)
                .addContainerGap())
        );
        backPanel3Layout.setVerticalGroup(
            backPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel3Layout.createSequentialGroup()
                .addGroup(backPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleCorp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel4.setBackground(new java.awt.Color(255, 255, 255));

        titleCons.setBackground(new java.awt.Color(255, 255, 255));
        titleCons.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleCons.setForeground(new java.awt.Color(204, 0, 0));
        titleCons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleCons.setText("Consumers");

        colorPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel4Layout = new javax.swing.GroupLayout(colorPanel4);
        colorPanel4.setLayout(colorPanel4Layout);
        colorPanel4Layout.setHorizontalGroup(
            colorPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel4Layout.setVerticalGroup(
            colorPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel4Layout = new javax.swing.GroupLayout(backPanel4);
        backPanel4.setLayout(backPanel4Layout);
        backPanel4Layout.setHorizontalGroup(
            backPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel4Layout.createSequentialGroup()
                .addComponent(colorPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleCons)
                .addContainerGap())
        );
        backPanel4Layout.setVerticalGroup(
            backPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel4Layout.createSequentialGroup()
                .addGroup(backPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleCons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel5.setBackground(new java.awt.Color(255, 255, 255));

        titleBankr.setBackground(new java.awt.Color(255, 255, 255));
        titleBankr.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleBankr.setForeground(new java.awt.Color(204, 0, 0));
        titleBankr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBankr.setText("Bankruptcy");

        colorPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel5Layout = new javax.swing.GroupLayout(colorPanel5);
        colorPanel5.setLayout(colorPanel5Layout);
        colorPanel5Layout.setHorizontalGroup(
            colorPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel5Layout.setVerticalGroup(
            colorPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel5Layout = new javax.swing.GroupLayout(backPanel5);
        backPanel5.setLayout(backPanel5Layout);
        backPanel5Layout.setHorizontalGroup(
            backPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel5Layout.createSequentialGroup()
                .addComponent(colorPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleBankr)
                .addContainerGap())
        );
        backPanel5Layout.setVerticalGroup(
            backPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel5Layout.createSequentialGroup()
                .addGroup(backPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(colorPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleBankr, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel6.setBackground(new java.awt.Color(255, 255, 255));

        titleOverview.setBackground(new java.awt.Color(255, 255, 255));
        titleOverview.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleOverview.setForeground(new java.awt.Color(204, 0, 0));
        titleOverview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleOverview.setText("Overview");

        colorPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel6Layout = new javax.swing.GroupLayout(colorPanel6);
        colorPanel6.setLayout(colorPanel6Layout);
        colorPanel6Layout.setHorizontalGroup(
            colorPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel6Layout.setVerticalGroup(
            colorPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel6Layout = new javax.swing.GroupLayout(backPanel6);
        backPanel6.setLayout(backPanel6Layout);
        backPanel6Layout.setHorizontalGroup(
            backPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel6Layout.createSequentialGroup()
                .addComponent(colorPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleOverview)
                .addContainerGap())
        );
        backPanel6Layout.setVerticalGroup(
            backPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel6Layout.createSequentialGroup()
                .addGroup(backPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleOverview, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel7.setBackground(new java.awt.Color(255, 255, 255));

        titleLB.setBackground(new java.awt.Color(255, 255, 255));
        titleLB.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleLB.setForeground(new java.awt.Color(204, 0, 0));
        titleLB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLB.setText("Leaderboards");

        colorPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel7Layout = new javax.swing.GroupLayout(colorPanel7);
        colorPanel7.setLayout(colorPanel7Layout);
        colorPanel7Layout.setHorizontalGroup(
            colorPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel7Layout.setVerticalGroup(
            colorPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel7Layout = new javax.swing.GroupLayout(backPanel7);
        backPanel7.setLayout(backPanel7Layout);
        backPanel7Layout.setHorizontalGroup(
            backPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel7Layout.createSequentialGroup()
                .addComponent(colorPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLB)
                .addContainerGap())
        );
        backPanel7Layout.setVerticalGroup(
            backPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel7Layout.createSequentialGroup()
                .addGroup(backPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel8.setBackground(new java.awt.Color(255, 255, 255));

        titlePreferences.setBackground(new java.awt.Color(255, 255, 255));
        titlePreferences.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titlePreferences.setForeground(new java.awt.Color(204, 0, 0));
        titlePreferences.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titlePreferences.setText("Preferences");

        colorPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel8Layout = new javax.swing.GroupLayout(colorPanel8);
        colorPanel8.setLayout(colorPanel8Layout);
        colorPanel8Layout.setHorizontalGroup(
            colorPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel8Layout.setVerticalGroup(
            colorPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel8Layout = new javax.swing.GroupLayout(backPanel8);
        backPanel8.setLayout(backPanel8Layout);
        backPanel8Layout.setHorizontalGroup(
            backPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel8Layout.createSequentialGroup()
                .addComponent(colorPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addComponent(titlePreferences)
                .addContainerGap())
        );
        backPanel8Layout.setVerticalGroup(
            backPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel8Layout.createSequentialGroup()
                .addGroup(backPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titlePreferences, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        backPanel9.setBackground(new java.awt.Color(255, 255, 255));

        titleExit.setBackground(new java.awt.Color(255, 255, 255));
        titleExit.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        titleExit.setForeground(new java.awt.Color(204, 0, 0));
        titleExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleExit.setText("Quit to Desktop");

        colorPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout colorPanel9Layout = new javax.swing.GroupLayout(colorPanel9);
        colorPanel9.setLayout(colorPanel9Layout);
        colorPanel9Layout.setHorizontalGroup(
            colorPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        colorPanel9Layout.setVerticalGroup(
            colorPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backPanel9Layout = new javax.swing.GroupLayout(backPanel9);
        backPanel9.setLayout(backPanel9Layout);
        backPanel9Layout.setHorizontalGroup(
            backPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel9Layout.createSequentialGroup()
                .addComponent(colorPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleExit)
                .addContainerGap())
        );
        backPanel9Layout.setVerticalGroup(
            backPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backPanel9Layout.createSequentialGroup()
                .addGroup(backPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleExit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(colorPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(backPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanel1;
    private javax.swing.JPanel backPanel2;
    private javax.swing.JPanel backPanel3;
    private javax.swing.JPanel backPanel4;
    private javax.swing.JPanel backPanel5;
    private javax.swing.JPanel backPanel6;
    private javax.swing.JPanel backPanel7;
    private javax.swing.JPanel backPanel8;
    private javax.swing.JPanel backPanel9;
    private javax.swing.JPanel colorPanel1;
    private javax.swing.JPanel colorPanel2;
    private javax.swing.JPanel colorPanel3;
    private javax.swing.JPanel colorPanel4;
    private javax.swing.JPanel colorPanel5;
    private javax.swing.JPanel colorPanel6;
    private javax.swing.JPanel colorPanel7;
    private javax.swing.JPanel colorPanel8;
    private javax.swing.JPanel colorPanel9;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JLabel titleBankr;
    public static javax.swing.JLabel titleBudget;
    public static javax.swing.JLabel titleCons;
    public static javax.swing.JLabel titleCorp;
    public static javax.swing.JLabel titleExit;
    public static javax.swing.JLabel titleGov;
    public static javax.swing.JLabel titleLB;
    public static javax.swing.JLabel titleOverview;
    public static javax.swing.JLabel titlePreferences;
    // End of variables declaration//GEN-END:variables
}
