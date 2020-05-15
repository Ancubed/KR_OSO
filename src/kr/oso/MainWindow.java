package kr.oso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private static Font font;
    private static Font headerFont;
    private static final JPanel makersPanel = new JPanel();
    private static final JPanel fileContentPanel = new JPanel();
    private static final JPanel consumersPanel = new JPanel();

    private final JPanel makersPanelToCreate = new JPanel();
    private final JTextField makersCountTextField = new JTextField();
    private final JTextField makersTimeTextField = new JTextField();
    private final JPanel fileContentPanelToCreate = new JPanel();
    private final JTextField fileContentCountTextField = new JTextField();
    private final JPanel consumersPanelToCreate = new JPanel();
    private final JTextField consumersCountTextField = new JTextField();
    private final JTextField consumersTimeTextField = new JTextField();

    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu menuFile = new JMenu("File");
    private final JMenuItem menuRun = new JMenuItem("Run");
    private final JMenuItem menuStop = new JMenuItem("Stop");
    private final JMenuItem menuResume = new JMenuItem("Resume");
    private final JMenuItem menuExit = new JMenuItem("Exit");

    private static final JLabel makersHeader = new JLabel("Consumers");
    private static final JLabel fileHeader = new JLabel("File content");
    private static final JLabel consumersHeader = new JLabel("Makers");

    private static JList<String> jListMakers;
    private static String[] makersNames;

    private static JList<String> jListConsumers;
    private static String[] consumersNames;

    private static JList<String> jListFileContent;
    private static String[] fileContent;

    public MainWindow() {
        super("Производитель / потребитель");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setMinimumSize(new Dimension(960, 600));
        setMaximumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);

        font = new Font("Courier", Font.ITALIC, 18);
        headerFont = new Font("Courier", Font.BOLD, 23);

        setPropertiesToComponents();
        constructionOfHierarchy();
        initActionOfComponents();

        setVisible(true);
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
        this.menuStop.setName("MenuStop");
        this.menuStop.setFont(font);
        this.menuStop.setEnabled(false);
        this.menuResume.setName("MenuResume");
        this.menuResume.setFont(font);
        this.menuResume.setEnabled(false);
        this.menuExit.setName("MenuExit");
        this.menuExit.setFont(font);
        /////////////////////////////////markersPanel
        makersPanel.setName("MakersPanel");
        makersPanel.setPreferredSize(new Dimension(getWidth()/3,
                getHeight()));
        makersPanel.setLayout(new BoxLayout(
                makersPanel, BoxLayout.Y_AXIS));
        makersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.makersPanelToCreate.setName("MakersPanelToCreate");
        this.makersPanelToCreate.setMaximumSize(new Dimension(getWidth()/3,
                55));
        this.makersPanelToCreate.setLayout(new BoxLayout(
                this.makersPanelToCreate, BoxLayout.Y_AXIS));
        this.makersPanelToCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////fileContentPanel
        fileContentPanel.setName("FileContentPanel");
        fileContentPanel.setPreferredSize(new Dimension(getWidth()/4,
                getHeight()));
        fileContentPanel.setLayout(new BoxLayout(
               fileContentPanel, BoxLayout.Y_AXIS));
        fileContentPanel.setBackground(Color.lightGray);
        fileContentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.fileContentPanelToCreate.setName("FileContentPanelToCreate");
        this.fileContentPanelToCreate.setMaximumSize(new Dimension(getWidth()/3,
                55));
        this.fileContentPanelToCreate.setLayout(new BoxLayout(
                this.fileContentPanelToCreate, BoxLayout.Y_AXIS));
        this.fileContentPanelToCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////consumersPanel
        consumersPanel.setName("FileContentPanel");
        consumersPanel.setPreferredSize(new Dimension(getWidth()/3,
                getHeight()));
        consumersPanel.setLayout(new BoxLayout(
                consumersPanel, BoxLayout.Y_AXIS));
        consumersPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.consumersPanelToCreate.setName("ConsumersContentPanelToCreate");
        this.consumersPanelToCreate.setMaximumSize(new Dimension(getWidth()/3,
                55));
        this.consumersPanelToCreate.setLayout(new BoxLayout(
                this.consumersPanelToCreate, BoxLayout.Y_AXIS));
        this.consumersPanelToCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        /////////////////////////////////consumersHeader
        consumersHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        consumersHeader.setFont(headerFont);
        /////////////////////////////////fileHeader
        fileHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileHeader.setFont(headerFont);
        /////////////////////////////////makersHeader
        makersHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        makersHeader.setFont(headerFont);
        this.makersCountTextField.setColumns(5);
        this.makersCountTextField.setToolTipText("Число поставщиков");
        this.makersCountTextField.setFont(font);
        this.makersTimeTextField.setColumns(5);
        this.makersTimeTextField.setToolTipText("Время доступа в буфер");
        this.makersTimeTextField.setFont(font);
        this.consumersCountTextField.setColumns(5);
        this.consumersCountTextField.setToolTipText("Число поставщиков");
        this.consumersCountTextField.setFont(font);
        this.consumersTimeTextField.setColumns(5);
        this.consumersTimeTextField.setToolTipText("Время доступа в буфер");
        this.consumersTimeTextField.setFont(font);
        this.fileContentCountTextField.setToolTipText("Размер буфера");
        this.fileContentCountTextField.setFont(font);
    }

    private void constructionOfHierarchy() {
        /////////////////////////////////into MenuFile
        this.menuFile.add(this.menuRun);
        this.menuFile.add(this.menuStop);
        this.menuFile.add(this.menuResume);
        this.menuFile.addSeparator();
        this.menuFile.add(this.menuExit);
        /////////////////////////////////into MenuBar
        this.menuBar.add(this.menuFile);
        /////////////////////////////////set MenuBar
        setJMenuBar(this.menuBar);
        /////////////////////////////////into Panels
        this.makersPanelToCreate.add(this.makersCountTextField);
        this.makersPanelToCreate.add(this.makersTimeTextField);
        makersPanel.add(this.makersPanelToCreate);
        makersPanel.add(makersHeader);
        this.consumersPanelToCreate.add(this.consumersCountTextField);
        this.consumersPanelToCreate.add(this.consumersTimeTextField);
        consumersPanel.add(this.consumersPanelToCreate);
        consumersPanel.add(consumersHeader);
        this.fileContentPanelToCreate.add(this.fileContentCountTextField);
        fileContentPanel.add(this.fileContentPanelToCreate);
        fileContentPanel.add(fileHeader);
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
        this.menuStop.addActionListener(actionEvent -> pauseAndResume());
        this.menuResume.addActionListener(actionEvent -> pauseAndResume());
    }

    static synchronized void addMakerToWindow() {
        for (int i = 0; i < makersNames.length; i++) {
            Maker maker = Manager.makers.get(i);
            makersNames[i] = String.format("%s(id %s, пост %d)",
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

    static synchronized void addFileContentToWindow() {
        Arrays.fill(fileContent, "null");
        jListFileContent = new JList<>(fileContent);
        jListFileContent.setBorder(new EmptyBorder(10,10, 10, 10));
        jListFileContent.setFixedCellWidth(makersPanel.getWidth());
        jListFileContent.setFont(font);
        jListFileContent.setSelectionBackground(Color.white);
        jListFileContent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileContentPanel.add(jListFileContent);
        fileContentPanel.updateUI();
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

    static void currentFileContent(String product, int productCount) {
        for(int i = fileContent.length - 1; i - 1 >= 0; i--) {
            if (i > productCount - 1) {
                fileContent[i] = "null";
            } else {
                fileContent[i] = fileContent[i - 1];
            }
            jListFileContent.setSelectedIndex(i);
        }
        MainWindow.fileContent[0] = String.format("товар %s", product);
        jListFileContent.setSelectedIndex(0);
    }

    static void currentFileContent(int productCount) {
        for(int i = fileContent.length - 1; i >= 0; i--) {
            if (i + 1 > productCount) {
                fileContent[i] = "null";
            }
            jListFileContent.setSelectedIndex(i);
        }
        jListFileContent.setSelectedIndex(0);
    }

    private void run() {
        MyFile.clearFile();
        this.menuRun.setEnabled(false);
        this.menuStop.setEnabled(true);
        String makersCount = this.makersCountTextField.getText();
        String consumersCount = this.consumersCountTextField.getText();
        String fileContentCount = this.fileContentCountTextField.getText();
        String makersTime = this.makersTimeTextField.getText();
        String consumersTime  =this.consumersTimeTextField.getText();
        Manager man = new Manager();
        try {
            man = new Manager(Integer.parseInt(makersCount), Integer.parseInt(consumersCount),
                    Float.parseFloat(makersTime), Float.parseFloat(consumersTime), Integer.parseInt(fileContentCount));
        } catch (NumberFormatException e) {
            getContentPane().setBackground(Color.BLUE);
        }
        makersNames = new String[man.getMakersNumber()];
        consumersNames = new String[man.getConsumersNumber()];
        fileContent = new String[man.getFileSize()];
        man.start();
        this.makersCountTextField.setEnabled(false);
        this.makersTimeTextField.setEnabled(false);
        this.consumersCountTextField.setEnabled(false);
        this.consumersTimeTextField.setEnabled(false);
        this.fileContentCountTextField.setEnabled(false);
    }

    private void pauseAndResume() {
        for (int i = 0; i < Manager.makers.size(); i++) {
            Manager.makers.get(i).interrupt();
        }
        for (int i = 0; i < Manager.consumers.size(); i++) {
            Manager.consumers.get(i).interrupt();
        }
        this.menuResume.setEnabled(!this.menuResume.isEnabled());
        this.menuStop.setEnabled(!this.menuStop.isEnabled());
    }
}
