package economysimulation.classes.gui.endgame;

import economysimulation.classes.economy.sectors.SectorManager;
import economysimulation.classes.economy.simulation.reset.ResetSimulation;
import economysimulation.classes.economy.structure.Component;
import economysimulation.classes.global.Methods;
import static economysimulation.classes.global.Methods.ModeHandler;
import economysimulation.classes.gui.mainpanels.extra.leaderboard.Leaderboard;
import economysimulation.classes.gui.startup.PreSetup;
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
import static economysimulation.classes.global.Methods.ThemeHandler;
import economysimulation.classes.mode.Mode;

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
            "Population",
            "Unemployment",
            "People Support",
            "Production Costs",
            "Investment",
            "Total Taxation",
            "Budget",
            "Firm Profits",
            "Firm Support",
            "Username"
        },
            
        Scores = new String[]{
            "£" + f.format(Component.GrossDomesticProduct*1000) + "m",
            "£" + f.format(Component.TotalConsumption*1000) + "m",
            "£" + f.format(Component.TotalSavings*1000) + "m",
            f.format(Component.Population),
            f.format(Component.Unemployment) + "%",
            f.format(Component.ConsumerConfidence*100) + "%",
            "£" + f.format(Component.CostOfProduction*1000) + "m",
            "£" + f.format(Component.Investment*1000) + "m",
            "£" + f.format(Component.TotalCorporationTax + Component.TotalIncomeTax) + "m",
            "£" + f.format(Component.SpendingBudget) + "bn",
            "£" + f.format(Component.TotalCorporationProfits*1000) + "m",
            f.format(Component.CorporationConfidence*100) + "%",
            Methods.getUser().getFullName()
    
        };
    
    /**
     * Creates new form SimulationOver
     */
    public EndScreen() {
        initComponents();
        Methods.AnimationGraph = new StockGraph(animBack);
        
        backPanels = new JPanel[]{ back1, back2, back3, back4 };
        colorPanels = new JPanel[]{ color1, color2, color3, color4,  };
        
        opColorPanels = new JPanel[]{ opCol1, opCol2, opCol3, opCol4, opCol5, opCol6, opCol7, opCol8, opCol9, opCol10, opCol11, opCol12, null };
        opBackPanels = new JPanel[]{ opBack1, opBack2, opBack3, opBack4, opBack5, opBack6, opBack7, opBack8, opBack9, opBack10, opBack11, opBack12, opBack13 };
        opTitles = new JLabel[]{ opTitle1, opTitle2, opTitle3, opTitle4, opTitle5, opTitle6, opTitle7, opTitle8, opTitle9, opTitle10, opTitle11, opTitle12, opTitle13 };
        
        for (int i = 0; i < backPanels.length; i++) {
            Format.addButtonFormat(backPanels[i], colorPanels[i]);
            applyButtonListener(i);
        }
        
        for (int i = 0; i < opBackPanels.length; i++) {
            Format.addButtonFormat(opBackPanels[i], opColorPanels[i]);
            displayRelativeScore(i);
        }
        
        ThemeHandler.addThemeUpdateListener(this);
    }

    @Override
    public void onThemeUpdate(GraphicUpdater updater) {
        updater.applyPanelThemes(new JPanel[]{ back, back1, back2, back3, back4,
            color1, color2, color3, color4, opCol1, opCol2, opCol3, opCol4, opCol5, opCol6, opCol7, opCol8, opCol9, opCol10, opCol11, opCol12,
            opBack1, opBack2, opBack3, opBack4, opBack5, opBack6, opBack7, opBack8, opBack9, opBack10, opBack11, opBack12, opBack13
        }, new JPanel[]{ animBack });
        updater.applyTextThemes(new JLabel[]{ title, title1, title2, title3, title4,
            opTitle1, opTitle2, opTitle3, opTitle4, opTitle5, opTitle6, opTitle7, opTitle8, opTitle9, opTitle10, opTitle11, opTitle12, opTitle13
        }, null);
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
                        Methods.quitSystem();
                        break;
                }
            }
        });
    }

    private void optionMenu(boolean replay) {
        Methods.SimulationInProgress = false;
        Methods.AnimationGraph.stop();
        ResetSimulation.resetSimulation();
        
        if (replay && !ModeHandler.isMode(Mode.MULTI_PLAYER)) {
            Methods.SectorInstance = new SectorManager();
            Methods.TaxRevenueDisplay = new TaxRevenueList();
            Methods.FrameDisplay.addToMainFrame(new PreSetup());
        } else {
            Methods.FrameDisplay.addToMainFrame(new WelcomePanel());
        }    
        
    }
    
    private void optionOpenLeaderboards() {
        Methods.AnimationGraph.stop();
        if (Methods.LBDisplay == null) Methods.LBDisplay = new Leaderboard();
        Methods.addToFrontPanel(animBack, Methods.LBDisplay, false);
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
        opBack7 = new javax.swing.JPanel();
        opTitle7 = new javax.swing.JLabel();
        opCol7 = new javax.swing.JPanel();
        opBack8 = new javax.swing.JPanel();
        opCol8 = new javax.swing.JPanel();
        opTitle8 = new javax.swing.JLabel();
        opBack9 = new javax.swing.JPanel();
        opCol9 = new javax.swing.JPanel();
        opTitle9 = new javax.swing.JLabel();
        opBack10 = new javax.swing.JPanel();
        opCol10 = new javax.swing.JPanel();
        opTitle10 = new javax.swing.JLabel();
        opBack11 = new javax.swing.JPanel();
        opCol11 = new javax.swing.JPanel();
        opTitle11 = new javax.swing.JLabel();
        opBack12 = new javax.swing.JPanel();
        opCol12 = new javax.swing.JPanel();
        opTitle12 = new javax.swing.JLabel();
        opBack13 = new javax.swing.JPanel();
        opTitle13 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1800, 977));

        animBack.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout animBackLayout = new javax.swing.GroupLayout(animBack);
        animBack.setLayout(animBackLayout);
        animBackLayout.setHorizontalGroup(
            animBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1294, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(title2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(title3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(title4, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        opTitle1.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
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
                .addComponent(opTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(7, 7, 7))
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

        opTitle2.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
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
                .addGap(7, 7, 7))
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

        opTitle3.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
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
                .addGap(7, 7, 7))
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

        opTitle4.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle4.setForeground(new java.awt.Color(204, 0, 0));
        opTitle4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        opTitle4.setText("Population");

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
                .addGap(7, 7, 7))
        );

        opBack5.setBackground(new java.awt.Color(255, 255, 255));
        opBack5.setMaximumSize(new java.awt.Dimension(220, 60));

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

        opTitle5.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
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
            .addGroup(opBack5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
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

        opTitle6.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
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
                .addGap(7, 7, 7))
        );

        opBack7.setBackground(new java.awt.Color(255, 255, 255));

        opTitle7.setFont(new java.awt.Font("Agency FB", 0, 28)); // NOI18N
        opTitle7.setForeground(new java.awt.Color(204, 0, 0));
        opTitle7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle7.setText("Production Cost");

        opCol7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol7Layout = new javax.swing.GroupLayout(opCol7);
        opCol7.setLayout(opCol7Layout);
        opCol7Layout.setHorizontalGroup(
            opCol7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol7Layout.setVerticalGroup(
            opCol7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout opBack7Layout = new javax.swing.GroupLayout(opBack7);
        opBack7.setLayout(opBack7Layout);
        opBack7Layout.setHorizontalGroup(
            opBack7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        opBack7Layout.setVerticalGroup(
            opBack7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        opBack8.setBackground(new java.awt.Color(255, 255, 255));

        opCol8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol8Layout = new javax.swing.GroupLayout(opCol8);
        opCol8.setLayout(opCol8Layout);
        opCol8Layout.setHorizontalGroup(
            opCol8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol8Layout.setVerticalGroup(
            opCol8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle8.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle8.setForeground(new java.awt.Color(204, 0, 0));
        opTitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle8.setText("Investment");

        javax.swing.GroupLayout opBack8Layout = new javax.swing.GroupLayout(opBack8);
        opBack8.setLayout(opBack8Layout);
        opBack8Layout.setHorizontalGroup(
            opBack8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        opBack8Layout.setVerticalGroup(
            opBack8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        opBack9.setBackground(new java.awt.Color(255, 255, 255));

        opCol9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol9Layout = new javax.swing.GroupLayout(opCol9);
        opCol9.setLayout(opCol9Layout);
        opCol9Layout.setHorizontalGroup(
            opCol9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol9Layout.setVerticalGroup(
            opCol9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle9.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle9.setForeground(new java.awt.Color(204, 0, 0));
        opTitle9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle9.setText("Total Taxation");

        javax.swing.GroupLayout opBack9Layout = new javax.swing.GroupLayout(opBack9);
        opBack9.setLayout(opBack9Layout);
        opBack9Layout.setHorizontalGroup(
            opBack9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle9, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        opBack9Layout.setVerticalGroup(
            opBack9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        opBack10.setBackground(new java.awt.Color(255, 255, 255));

        opCol10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol10Layout = new javax.swing.GroupLayout(opCol10);
        opCol10.setLayout(opCol10Layout);
        opCol10Layout.setHorizontalGroup(
            opCol10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol10Layout.setVerticalGroup(
            opCol10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle10.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle10.setForeground(new java.awt.Color(204, 0, 0));
        opTitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle10.setText("Budget");

        javax.swing.GroupLayout opBack10Layout = new javax.swing.GroupLayout(opBack10);
        opBack10.setLayout(opBack10Layout);
        opBack10Layout.setHorizontalGroup(
            opBack10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle10, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        opBack10Layout.setVerticalGroup(
            opBack10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        opBack11.setBackground(new java.awt.Color(255, 255, 255));

        opCol11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol11Layout = new javax.swing.GroupLayout(opCol11);
        opCol11.setLayout(opCol11Layout);
        opCol11Layout.setHorizontalGroup(
            opCol11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol11Layout.setVerticalGroup(
            opCol11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle11.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle11.setForeground(new java.awt.Color(204, 0, 0));
        opTitle11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle11.setText("Firm Profits");

        javax.swing.GroupLayout opBack11Layout = new javax.swing.GroupLayout(opBack11);
        opBack11.setLayout(opBack11Layout);
        opBack11Layout.setHorizontalGroup(
            opBack11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle11, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        opBack11Layout.setVerticalGroup(
            opBack11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        opBack12.setBackground(new java.awt.Color(255, 255, 255));

        opCol12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout opCol12Layout = new javax.swing.GroupLayout(opCol12);
        opCol12.setLayout(opCol12Layout);
        opCol12Layout.setHorizontalGroup(
            opCol12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        opCol12Layout.setVerticalGroup(
            opCol12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        opTitle12.setFont(new java.awt.Font("Agency FB", 0, 32)); // NOI18N
        opTitle12.setForeground(new java.awt.Color(204, 0, 0));
        opTitle12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTitle12.setText("Firm Support");

        javax.swing.GroupLayout opBack12Layout = new javax.swing.GroupLayout(opBack12);
        opBack12.setLayout(opBack12Layout);
        opBack12Layout.setHorizontalGroup(
            opBack12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle12, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCol12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        opBack12Layout.setVerticalGroup(
            opBack12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(opCol12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        opBack13.setBackground(new java.awt.Color(255, 255, 255));

        opTitle13.setFont(new java.awt.Font("Agency FB", 0, 34)); // NOI18N
        opTitle13.setForeground(new java.awt.Color(204, 0, 0));
        opTitle13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opTitle13.setText("Username");

        javax.swing.GroupLayout opBack13Layout = new javax.swing.GroupLayout(opBack13);
        opBack13.setLayout(opBack13Layout);
        opBack13Layout.setHorizontalGroup(
            opBack13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opBack13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle13, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        opBack13Layout.setVerticalGroup(
            opBack13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opBack13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opTitle13, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backLayout.createSequentialGroup()
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backLayout.createSequentialGroup()
                                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(backLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(opBack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(backLayout.createSequentialGroup()
                                                .addComponent(opBack6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(opBack12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(backLayout.createSequentialGroup()
                                                .addComponent(opBack5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(opBack11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(backLayout.createSequentialGroup()
                                                .addComponent(opBack4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(opBack10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(backLayout.createSequentialGroup()
                                                .addComponent(opBack3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(opBack9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                                                .addComponent(opBack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(opBack7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(opBack8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(jSeparator2)))
                                    .addGroup(backLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(opBack13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 20, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(opBack13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opBack1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opBack7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(opBack2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(opBack8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(opBack3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(opBack9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(opBack4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(opBack10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(opBack5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(opBack11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opBack6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opBack12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
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
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel opBack1;
    private javax.swing.JPanel opBack10;
    private javax.swing.JPanel opBack11;
    private javax.swing.JPanel opBack12;
    private javax.swing.JPanel opBack13;
    private javax.swing.JPanel opBack2;
    private javax.swing.JPanel opBack3;
    private javax.swing.JPanel opBack4;
    private javax.swing.JPanel opBack5;
    private javax.swing.JPanel opBack6;
    private javax.swing.JPanel opBack7;
    private javax.swing.JPanel opBack8;
    private javax.swing.JPanel opBack9;
    private javax.swing.JPanel opCol1;
    private javax.swing.JPanel opCol10;
    private javax.swing.JPanel opCol11;
    private javax.swing.JPanel opCol12;
    private javax.swing.JPanel opCol2;
    private javax.swing.JPanel opCol3;
    private javax.swing.JPanel opCol4;
    private javax.swing.JPanel opCol5;
    private javax.swing.JPanel opCol6;
    private javax.swing.JPanel opCol7;
    private javax.swing.JPanel opCol8;
    private javax.swing.JPanel opCol9;
    private javax.swing.JLabel opTitle1;
    private javax.swing.JLabel opTitle10;
    private javax.swing.JLabel opTitle11;
    private javax.swing.JLabel opTitle12;
    private javax.swing.JLabel opTitle13;
    private javax.swing.JLabel opTitle2;
    private javax.swing.JLabel opTitle3;
    private javax.swing.JLabel opTitle4;
    private javax.swing.JLabel opTitle5;
    private javax.swing.JLabel opTitle6;
    private javax.swing.JLabel opTitle7;
    private javax.swing.JLabel opTitle8;
    private javax.swing.JLabel opTitle9;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title3;
    private javax.swing.JLabel title4;
    // End of variables declaration//GEN-END:variables
}
