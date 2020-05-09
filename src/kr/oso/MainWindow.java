package kr.oso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    private static Font font;
    private static Font headerFont;
    private static final JPanel makersPanel = new JPanel();
    private static final JPanel fileContentPanel = new JPanel();
    private static final JPanel consumersPanel = new JPanel();
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuRun;
    private JMenuItem menuStop;
    private JMenuItem menuExit;

    private static JLabel makersHeader;
    private static JLabel fileHeader;
    private static JLabel consumersHeader;

    private static JList<String> jListMakers;
    private static String[] makersNames;

    private static JList<String> jListConsumers;
    private static String[] consumersNames;

    private Manager man;

    public MainWindow() {
        super("Производитель / потребитель");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setMaximumSize(new Dimension(960, 650));
        setMinimumSize(new Dimension(960, 600));
        setLocationRelativeTo(null);

        font = new Font("Courier", Font.ITALIC, 18);
        headerFont = new Font("Courier", Font.BOLD, 23);

        initComponents();
        setPropertiesToComponents();
        constructionOfHierarchy();
        initActionOfComponents();

        setVisible(true);
    }

    private void initComponents() {
        this.menuBar = new JMenuBar();
        this.menuFile = new JMenu("File");
        this.menuRun = new JMenuItem("Run");
        this.menuStop = new JMenuItem("Stop");
        this.menuExit = new JMenuItem("Exit");
        consumersHeader = new JLabel("Consumers");
        fileHeader = new JLabel("File content");
        makersHeader = new JLabel("Makers");
    }

    private void setPropertiesToComponents() {
        /////////////////////////////////MenuBar
        this.menuBar.setName("MenuBar");
        this.menuBar.setFont(font);
        /////////////////////////////////MenuFile
        this.menuFile.setName("MenuFile");
        this.menuFile.setMnemonic(KeyEvent.VK_F);
        this.menuFile.setFont(font);
        this.menuRun.setName("Run");
        this.menuRun.setFont(font);
        this.menuStop.setName("MenuSave");
        this.menuStop.setFont(font);
        this.menuExit.setName("MenuExit");
        this.menuExit.setFont(font);
        /////////////////////////////////markersPanel
        makersPanel.setName("MakersPanel");
        makersPanel.setPreferredSize(new Dimension(getWidth()/3,
                getHeight()));
        makersPanel.setLayout(new BoxLayout(
                makersPanel, BoxLayout.Y_AXIS));
        makersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////fileContentPanel
        fileContentPanel.setName("FileContentPanel");
        fileContentPanel.setPreferredSize(new Dimension(getWidth()/4,
                getHeight()));
        fileContentPanel.setLayout(new BoxLayout(
               fileContentPanel, BoxLayout.Y_AXIS));
        fileContentPanel.setBackground(Color.lightGray);
        fileContentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////consumersPanel
        consumersPanel.setName("FileContentPanel");
        consumersPanel.setPreferredSize(new Dimension(getWidth()/3,
                getHeight()));
        consumersPanel.setLayout(new BoxLayout(
                consumersPanel, BoxLayout.Y_AXIS));
        consumersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////consumersHeader
        consumersHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        consumersHeader.setFont(headerFont);
        /////////////////////////////////fileHeader
        fileHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileHeader.setFont(headerFont);
        /////////////////////////////////makersHeader
        makersHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        makersHeader.setFont(headerFont);
    }

    private void constructionOfHierarchy() {
        /////////////////////////////////into MenuFile
        this.menuFile.add(this.menuRun);
        this.menuFile.add(this.menuStop);
        this.menuFile.addSeparator();
        this.menuFile.add(this.menuExit);
        /////////////////////////////////into MenuBar
        this.menuBar.add(this.menuFile);
        /////////////////////////////////set MenuBar
        setJMenuBar(this.menuBar);
        /////////////////////////////////into Panels
        makersPanel.add(makersHeader);
        makersPanel.add(makersHeader);
        fileContentPanel.add(fileHeader);
        consumersPanel.add(consumersHeader);
        /////////////////////////////////into RootPanel
        getContentPane().setLayout(new FlowLayout());//Layout RootPanel
        getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        getContentPane().add(makersPanel);
        getContentPane().add(fileContentPanel);
        getContentPane().add(consumersPanel);
    }

    private void initActionOfComponents() {
        this.menuExit.addActionListener(actionEvent -> System.exit(0));
        this.menuRun.addActionListener(actionEvent -> run());
    }

    static synchronized void addMakerToWindow() {
        for (int i = 0; i < makersNames.length; i++) {
            Maker maker = Manager.makers.get(i);
            makersNames[i] = String.format("%s(id %s, пост %d",
                    maker.getName(), maker.getId(), 0);
        }
        jListMakers = new JList<>(makersNames);
        jListMakers.setBorder(new EmptyBorder(10,10, 10, 10));
        jListMakers.setFixedCellWidth(makersPanel.getWidth());
        jListMakers.setFont(font);
        jListMakers.setSelectionBackground(Color.green);
        jListMakers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListMakers.setAlignmentX(Component.CENTER_ALIGNMENT);
        makersPanel.add(jListMakers);
        makersPanel.updateUI();
    }

    static synchronized void addConsumerToWindow() {
        for (int i = 0; i < consumersNames.length; i++) {
            Consumer consumer = Manager.consumers.get(i);
            consumersNames[i] = String.format("%s(id %s, куп %d)",
                    consumer.getName(), consumer.getId(), 0);
        }
        jListConsumers = new JList<>(consumersNames);
        jListConsumers.setBorder(new EmptyBorder(10,10, 10, 10));
        jListConsumers.setFixedCellWidth(makersPanel.getWidth());
        jListConsumers.setFont(font);
        jListConsumers.setSelectionBackground(Color.green);
        jListConsumers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListConsumers.setAlignmentX(Component.CENTER_ALIGNMENT);
        consumersPanel.add(jListConsumers);
        consumersPanel.updateUI();
    }

    static synchronized void currentMakerSupply(long id, int records) {
        int needSelectedId = 0;
        for (int i = 0; i < makersNames.length; i++) {
            Maker maker = Manager.makers.get(i);
            if (maker.getId() == id) {
                needSelectedId = i;
                makersNames[i] = String.format("%s(id %s, пост %d)",
                        maker.getName(), maker.getId(), records);
            }
        }
        jListMakers.setSelectedIndex(needSelectedId);
    }

    static synchronized void currentConsumerBuy(long id, int readings) {
        int needSelectedId = 0;
        for (int i = 0; i < consumersNames.length; i++) {
            Consumer consumer = Manager.consumers.get(i);
            if (consumer.getId() == id) {
                needSelectedId = i;
                consumersNames[i] = String.format("%s(id %s, куп %d)",
                        consumer.getName(), consumer.getId(), readings);
            }
        }
        jListConsumers.setSelectedIndex(needSelectedId);
    }

    void run() {
        this.menuRun.setEnabled(false);
        this.man = new Manager();
        makersNames = new String[this.man.getMakersNumber()];
        consumersNames = new String[this.man.getConsumersNumber()];
        this.man.start();
    }
    void stop() {

    }
}