package economysimulation.classes.gui.endgame;

import economysimulation.classes.economy.sectors.Sector;
import economysimulation.classes.economy.simulation.reset.ResetSimulation;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ThemeManager;
import economysimulation.classes.gui.startup.Tutorial;
import economysimulation.classes.gui.startup.WelcomePanel;
import economysimulation.classes.gui.subpanels.TaxRevenueList;
import economysimulation.classes.managers.animation.StockGraph;
import economysimulation.classes.managers.theme.GraphicUpdater;
import economysimulation.classes.managers.theme.ThemeUpdateEvent;
import economysimulation.classes.managers.ui.Format;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Max Carter
 */
public class EndScreen extends javax.swing.JPanel implements ThemeUpdateEvent {

    private DecimalFormat f = new DecimalFormat("0");
    
    private JPanel[] backPanels, colorPanels, opBackPanels, opColorPanels;
    private JLabel[] opTitles;
    private final String[]
        ComponentList = new String[]{
            "GDP",
            "Consumption",
            "Savings",
            "Wages",
            "Unemployment",
            "People Support"
        },
            
        Scores = new String[]{
            "£" + f.format(Component.GrossDomesticProduct*1000) + "m",
            "£" + f.format(Component.TotalConsumption*1000) + "m",
            "£" + f.format(Component.TotalSavings*1000) + "m",
            "£" + f.format(Component.Wages*1000) + "m",
            (Component.Unemployment*100) + "%",
            f.format(Component.ConsumerConfidence*100) + "%"
    
        };
    
    /**
     * Creates new form SimulationOver
     */
    public EndScreen() {
        initComponents();
        Methods.AnimationGraph = new StockGraph(animBack);
        
        backPanels = new JPanel[]{ back1, back2, back3, back4 };
        colorPanels = new JPanel[]{ color1, color2, color3, color4,  };
        
        opColorPanels = new JPanel[]{ opCol1, opCol2, opCol3, opCol4, opCol5, opCol6 };
        opBackPanels = new JPanel[]{ opBack1, opBack2, opBack3, opBack4, opBack5, opBack6 };
        opTitles = new JLabel[]{ opTitle1, opTitle2, opTitle3, opTitle4, opTitle5, opTitle6 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            applyButtonListener(i);
        }
        
        for (int i = 0; i < opBackPanels.length; i++) {
            Format.addButtonFormat(opBackPanels[i], opColorPanels[i]);
            displayRelativeScore(i);
        }
        
        usernameDisplay.setText(Methods.Username);
        ThemeManager.addThemeUpdateListener(this);
    }

    @Override
    public void updateThemeEvent(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back, back1, back2, back3, back4, color1, color2, color3, color4 }, new JPanel[]{ animBack });
        updater.applyTextThemes(new JLabel[]{ title, title1, title2, title3, title4 }, null);
    }
    
    private void displayRelativeScore(int id) {
        opBackPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                opTitles[id].setText(Scores[id] + "");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                opTitles[id].setText(ComponentList[id]);
            }
        });
    }
    
    private void applyButtonListener(int id) {
        backPanels[id].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (id) {
                    case 0:
                        optionMenu(true);
                        break;
                    case 1:
                        optionMenu(false);
                        break;
                    case 2:
                        optionOpenLeaderboards();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
            }
        });
    }

    private void optionMenu(boolean replay) {
        Methods.SimulationInProgress = false;
        Methods.AnimationGraph.stop();
        ResetSimulation.resetSimulation();
        
        if (replay) {
            Methods.SectorInstance = new Sector();
            Methods.TaxRevenueDisplay = new TaxRevenueList();
            Methods.FrameDisplay.addToMainFrame(new Tutorial());
        } else {
            Methods.FrameDisplay.addToMainFrame(new WelcomePanel());
        }    
        
    }
    
    private void optionOpenLeaderboards() {
        Methods.AnimationGraph.stop();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        animBack = new javax.swing.JPanel();
        back = new javax.swing.JPanel();
        back1 = new javax.swing.JPanel();
        color1 = new javax.swing.JPanel();
        title1 = new javax.swing.JLabel();
        back2 = new javax.swing.JPanel();
        color2 = new javax.swing.JPanel();
        title2 = new javax.swing.JLabel();
        back3 = new javax.swing.JPanel();
        color3 = new javax.swing.JPanel();
        title3 = new javax.swing.JLabel();
        back4 = new javax.swing.JPanel();
        color4 = new javax.swing.JPanel();
        title4 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usernameDisplay = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        opBack1 = new javax.swing.JPanel();
        opCol1 = new javax.swing.JPanel();
        opTitle1 = new javax.swing.JLabel();
        opBack2 = new javax.swing.JPanel();
        opCol2 = new javax.swing.JPanel();
        opTitle2 = new javax.swing.JLabel();
        opBack3 = new javax.swing.JPanel();
        opCol3 = new javax.swing.JPanel();
        opTitle3 = new javax.swing.JLabel();
        opBack4 = new javax.swing.JPanel();
        opCol4 = new javax.swing.JPanel();
        opTitle4 = new javax.swing.JLabel();
        opBack5 = new javax.swing.JPanel();
        opCol5 = new javax.swing.JPanel();
        opTitle5 = new javax.swing.JLabel();
        opBack6 = new javax.swing.JPanel();
        opCol6 = new javax.swing.JPanel();
        opTitle6 = new javax.swing.JLabel();

        animBack.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout animBackLayout = new javax.swing.GroupLayout(animBack);
        animBack.setLayout(animBackLayout);
        animBackLayout.setHorizontalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1300, Short.MAX_VALUE)
        );
        animBackLayout.setVerticalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        back.setBackground(new java.awt.Color(255, 255, 255));

        back1.setBackground(new java.awt.Color(255, 255, 255));
        back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color1Layout = new javax.swing.GroupLayout(color1);
        color1.setLayout(color1Layout);
        color1Layout.setHorizontalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color1Layout.setVerticalGroup(
            color1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        title1.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title1.setForeground(new java.awt.Color(204, 0, 0));
        title1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title1.setText("Replay Simulation");

        javax.swing.GroupLayout back1Layout = new javax.swing.GroupLayout(back1);
        back1.setLayout(back1Layout);
        back1Layout.setHorizontalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back1Layout.createSequentialGroup()
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        back1Layout.setVerticalGroup(
            back1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(back1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        back2.setBackground(new java.awt.Color(255, 255, 255));
        back2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color2Layout = new javax.swing.GroupLayout(color2);
        color2.setLayout(color2Layout);
        color2Layout.setHorizontalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color2Layout.setVerticalGroup(
            color2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        title2.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title2.setForeground(new java.awt.Color(204, 0, 0));
        title2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title2.setText("Main Menu");

        javax.swing.GroupLayout back2Layout = new javax.swing.GroupLayout(back2);
        back2.setLayout(back2Layout);
        back2Layout.setHorizontalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back2Layout.createSequentialGroup()
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        back2Layout.setVerticalGroup(
            back2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(back2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        back3.setBackground(new java.awt.Color(255, 255, 255));
        back3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color3Layout = new javax.swing.GroupLayout(color3);
        color3.setLayout(color3Layout);
        color3Layout.setHorizontalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color3Layout.setVerticalGroup(
            color3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        title3.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title3.setForeground(new java.awt.Color(204, 0, 0));
        title3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title3.setText("Leaderboards");

        javax.swing.GroupLayout back3Layout = new javax.swing.GroupLayout(back3);
        back3.setLayout(back3Layout);
        back3Layout.setHorizontalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back3Layout.createSequentialGroup()
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        back3Layout.setVerticalGroup(
            back3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(back3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        back4.setBackground(new java.awt.Color(255, 255, 255));
        back4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        color4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout color4Layout = new javax.swing.GroupLayout(color4);
        color4.setLayout(color4Layout);
        color4Layout.setHorizontalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        color4Layout.setVerticalGroup(
            color4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        title4.setFont(new java.awt.Font("Agency FB", 0, 48)); // NOI18N
        title4.setForeground(new java.awt.Color(204, 0, 0));
        title4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title4.setText("Quit To Desktop");

        javax.swing.GroupLayout back4Layout = new javax.swing.GroupLayout(back4);
        back4.setLayout(back4Layout);
        back4Layout.setHorizontalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(back4Layout.createSequentialGroup()
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        back4Layout.setVerticalGroup(
            back4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, back4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(color4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(back4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/economysimulation/resources/logos/border280.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Username:");

        usernameDisplay.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        usernameDisplay.setForeground(new java.awt.Color(204, 0, 0));
        usernameDisplay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        usernameDisplay.setText("n/a");

        opBack1.setBackground(new java.awt.Color(255, 255, 255));

        opCol1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol1Layout = new javax.swing.GroupLayout(opCol1);
        opCol1.setLayout(opCol1Layout);
        opCol1Layout.setHorizontalGroup(
            opCol1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol1Layout.setVerticalGroup(
            opCol1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle1.setForeground(new java.awt.Color(204, 0, 0));
        opTitle1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle1.setText("GDP");

        javax.swing.GroupLayout opBack1Layout = new javax.swing.GroupLayout(opBack1);
        opBack1.setLayout(opBack1Layout);
        opBack1Layout.setHorizontalGroup(
            opBack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack1Layout.createSequentialGroup()
                .addComponent(opCol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack1Layout.setVerticalGroup(
            opBack1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opBack2.setBackground(new java.awt.Color(255, 255, 255));

        opCol2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol2Layout = new javax.swing.GroupLayout(opCol2);
        opCol2.setLayout(opCol2Layout);
        opCol2Layout.setHorizontalGroup(
            opCol2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol2Layout.setVerticalGroup(
            opCol2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle2.setForeground(new java.awt.Color(204, 0, 0));
        opTitle2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle2.setText("Consumption");

        javax.swing.GroupLayout opBack2Layout = new javax.swing.GroupLayout(opBack2);
        opBack2.setLayout(opBack2Layout);
        opBack2Layout.setHorizontalGroup(
            opBack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack2Layout.createSequentialGroup()
                .addComponent(opCol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack2Layout.setVerticalGroup(
            opBack2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opBack3.setBackground(new java.awt.Color(255, 255, 255));

        opCol3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol3Layout = new javax.swing.GroupLayout(opCol3);
        opCol3.setLayout(opCol3Layout);
        opCol3Layout.setHorizontalGroup(
            opCol3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol3Layout.setVerticalGroup(
            opCol3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle3.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle3.setForeground(new java.awt.Color(204, 0, 0));
        opTitle3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle3.setText("Savings");

        javax.swing.GroupLayout opBack3Layout = new javax.swing.GroupLayout(opBack3);
        opBack3.setLayout(opBack3Layout);
        opBack3Layout.setHorizontalGroup(
            opBack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack3Layout.createSequentialGroup()
                .addComponent(opCol3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack3Layout.setVerticalGroup(
            opBack3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opBack4.setBackground(new java.awt.Color(255, 255, 255));

        opCol4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol4Layout = new javax.swing.GroupLayout(opCol4);
        opCol4.setLayout(opCol4Layout);
        opCol4Layout.setHorizontalGroup(
            opCol4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol4Layout.setVerticalGroup(
            opCol4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle4.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle4.setForeground(new java.awt.Color(204, 0, 0));
        opTitle4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle4.setText("Wages");

        javax.swing.GroupLayout opBack4Layout = new javax.swing.GroupLayout(opBack4);
        opBack4.setLayout(opBack4Layout);
        opBack4Layout.setHorizontalGroup(
            opBack4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack4Layout.createSequentialGroup()
                .addComponent(opCol4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack4Layout.setVerticalGroup(
            opBack4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opBack5.setBackground(new java.awt.Color(255, 255, 255));

        opCol5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol5Layout = new javax.swing.GroupLayout(opCol5);
        opCol5.setLayout(opCol5Layout);
        opCol5Layout.setHorizontalGroup(
            opCol5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol5Layout.setVerticalGroup(
            opCol5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle5.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle5.setForeground(new java.awt.Color(204, 0, 0));
        opTitle5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle5.setText("Unemployment");

        javax.swing.GroupLayout opBack5Layout = new javax.swing.GroupLayout(opBack5);
        opBack5.setLayout(opBack5Layout);
        opBack5Layout.setHorizontalGroup(
            opBack5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack5Layout.createSequentialGroup()
                .addComponent(opCol5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack5Layout.setVerticalGroup(
            opBack5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        opBack6.setBackground(new java.awt.Color(255, 255, 255));

        opCol6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol6Layout = new javax.swing.GroupLayout(opCol6);
        opCol6.setLayout(opCol6Layout);
        opCol6Layout.setHorizontalGroup(
            opCol6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol6Layout.setVerticalGroup(
            opCol6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle6.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        opTitle6.setForeground(new java.awt.Color(204, 0, 0));
        opTitle6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle6.setText("People Support");

        javax.swing.GroupLayout opBack6Layout = new javax.swing.GroupLayout(opBack6);
        opBack6.setLayout(opBack6Layout);
        opBack6Layout.setHorizontalGroup(
            opBack6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack6Layout.createSequentialGroup()
                .addComponent(opCol6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opTitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opBack6Layout.setVerticalGroup(
            opBack6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGap(0, 94, Short.MAX_VALUE)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backLayout.createSequentialGroup()
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(usernameDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(opBack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opBack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opBack3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opBack4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opBack5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opBack6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(31, 31, 31)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernameDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opBack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opBack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(opBack3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(opBack4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(opBack5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(opBack6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(animBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(animBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel animBack;
    private javax.swing.JPanel back;
    private javax.swing.JPanel back1;
    private javax.swing.JPanel back2;
    private javax.swing.JPanel back3;
    private javax.swing.JPanel back4;
    private javax.swing.JPanel color1;
    private javax.swing.JPanel color2;
    private javax.swing.JPanel color3;
    private javax.swing.JPanel color4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel opBack1;
    private javax.swing.JPanel opBack2;
    private javax.swing.JPanel opBack3;
    private javax.swing.JPanel opBack4;
    private javax.swing.JPanel opBack5;
    private javax.swing.JPanel opBack6;
    private javax.swing.JPanel opCol1;
    private javax.swing.JPanel opCol2;
    private javax.swing.JPanel opCol3;
    private javax.swing.JPanel opCol4;
    private javax.swing.JPanel opCol5;
    private javax.swing.JPanel opCol6;
    private javax.swing.JLabel opTitle1;
    private javax.swing.JLabel opTitle2;
    private javax.swing.JLabel opTitle3;
    private javax.swing.JLabel opTitle4;
    private javax.swing.JLabel opTitle5;
    private javax.swing.JLabel opTitle6;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    private javax.swing.JLabel usernameDisplay;
    // End of variables declaration//GEN-END:variables
}
