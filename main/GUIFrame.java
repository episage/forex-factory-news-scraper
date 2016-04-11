package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * GUI of the application
 * @author Nicolas11 @ BMT
 */
public class GUIFrame extends JFrame {

    final private static Color FOREST_GREEN = new Color(34, 139, 34);
    final private static int SMALL_MARGIN = 5;
    //
    // Frame:
    final private static Dimension FRAME_DIMENSION = new Dimension(1000, 600);
    //
    // Status label:
    private static String STATUS_LABEL_TEXT = "Overall status:";
    final private static Color STATUS_LABEL_TEXT_COLOR = Color.black;
    final private static int STATUS_LABEL_FONT_STYLE = Font.BOLD;
    final private static int STATUS_LABEL_FONT_SIZE = 20;
    //
    // Result label:
    final private static Color RESULT_LABEL_BACKGROUND_COLOR = Color.WHITE;
    //
    private static String RESULT_LABEL_PROCESSING_TEXT = "Processing...";
    final private static Color RESULT_LABEL_PROCESSING_TEXT_COLOR = Color.blue;
    final private static int RESULT_LABEL_PROCESSING_FONT_STYLE = Font.BOLD;
    final private static int RESULT_LABEL_PROCESSING_FONT_SIZE = 20;
    //
    private static String RESULT_LABEL_OK_TEXT = "Finished... OK... Application can be closed";
    final private static Color RESULT_LABEL_OK_TEXT_COLOR = FOREST_GREEN;
    final private static int RESULT_LABEL_OK_FONT_STYLE = Font.BOLD;
    final private static int RESULT_LABEL_OK_FONT_SIZE = 20;
    //
    private static String RESULT_LABEL_PROBLEM_TEXT = "Finished... PROBLEM... Error message below";
    final private static Color RESULT_LABEL_PROBLEM_TEXT_COLOR = Color.red;
    final private static int RESULT_LABEL_PROBLEM_FONT_STYLE = Font.BOLD;
    final private static int RESULT_LABEL_PROBLEM_FONT_SIZE = 20;
    //
    // Future Economic Events label:
    private static String FUTURE_ECONOMIC_EVENTS_LABEL_TEXT = "1. Future Economic Events:";
    final private static int FUTURE_ECONOMIC_EVENTS_LABEL_FONT_STYLE = Font.BOLD;
    final private static int FUTURE_ECONOMIC_EVENTS_LABEL_FONT_SIZE = 20;
    //
    // Future Economic Events text area:
    final private static int FUTURE_ECONOMIC_EVENTS_TEXT_AREA_FONT_SIZE = 20;
    //
    // Future Economic Events result label:
    private static JLabel futureEconomicEventsResultLabel;
    //
    private static String FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT = "Waiting...";
    final private static Color FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT_COLOR = Color.black;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_STYLE = Font.BOLD;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_SIZE = 20;
    //
    private static String FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT = "Processing...";
    final private static Color FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT_COLOR = Color.blue;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_STYLE = Font.BOLD;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_SIZE = 20;
    //
    private static String FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT = "OK";
    final private static Color FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT_COLOR = FOREST_GREEN;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_STYLE = Font.BOLD;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_SIZE = 20;
    //
    private static String FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT = "PROBLEM";
    final private static Color FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT_COLOR = Color.red;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_STYLE = Font.BOLD;
    final private static int FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_SIZE = 20;
    //
    // Historical Economic Events label:
    private static String HISTORICAL_ECONOMIC_EVENTS_LABEL_TEXT = "2. Historical Economic Events:";
    final private static int HISTORICAL_ECONOMIC_EVENTS_LABEL_FONT_STYLE = Font.BOLD;
    final private static int HISTORICAL_ECONOMIC_EVENTS_LABEL_FONT_SIZE = 20;
    //
    // Historical Economic Events text area:
    final private static int HISTORICAL_ECONOMIC_EVENTS_TEXT_AREA_FONT_SIZE = 20;
    //
    // Historical Economic Events result label:
    private static JLabel historicalEconomicEventsResultLabel;
    //
    private static String HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT = "Waiting...";
    final private static Color HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT_COLOR = Color.black;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_STYLE = Font.BOLD;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_SIZE = 20;
    //
    private static String HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT = "Processing...";
    final private static Color HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT_COLOR = Color.blue;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_STYLE = Font.BOLD;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_SIZE = 20;
    //
    private static String HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT = "OK";
    final private static Color HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT_COLOR = FOREST_GREEN;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_STYLE = Font.BOLD;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_SIZE = 20;
    //
    private static String HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT = "PROBLEM";
    final private static Color HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT_COLOR = Color.red;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_STYLE = Font.BOLD;
    final private static int HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_SIZE = 20;
    //
    // Close button:
    private static String CLOSE_BUTTON_TEXT = "Close";
    final private static Color CLOSE_BUTTON_TEXT_COLOR = Color.black;
    final private static int CLOSE_BUTTON_FONT_STYLE = Font.BOLD;
    final private static int CLOSE_BUTTON_FONT_SIZE = 20;
    //
    // Fields:
    private JLabel resultLabel;
    private JProgressBar progressBar;
    private JTextArea futureEconomicEventsTextArea;
    private JTextArea historicalEconomicEventsTextArea;
    private JButton closeButton;

    /**
     * Constructor
     */
    public GUIFrame() {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        int gridx, gridy;
        int gridwidth, gridheight;
        double weightx, weighty;
        int anchor;
        // GridBagConstraints.xxx
        // The absolute values are: CENTER, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, and NORTHWEST.
        // The relative values are: PAGE_START, PAGE_END, LINE_START, LINE_END, FIRST_LINE_START, FIRST_LINE_END, LAST_LINE_START and LAST_LINE_END.
        // The default value is CENTER.
        int fill;
        // NONE: Do not resize the component.
        // HORIZONTAL: Make the component wide enough to fill its display area horizontally, but do not change its height.
        // VERTICAL: Make the component tall enough to fill its display area vertically, but do not change its width.
        // BOTH: Make the component fill its display area entirely.
        // The default value is NONE.
        Insets insets;
        int top, left, bottom, right;
        int ipadx, ipady;

        //
        // Status label
        //
        JLabel statusLabel = new JLabel(STATUS_LABEL_TEXT);
        statusLabel.setForeground(STATUS_LABEL_TEXT_COLOR);
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        statusLabel.setFont(statusLabel.getFont().deriveFont(STATUS_LABEL_FONT_STYLE, STATUS_LABEL_FONT_SIZE));
        contentPanel.add(statusLabel, new GridBagConstraints(gridx = 0, gridy = 0,
                gridwidth = 1, gridheight = 2,
                weightx = 0, weighty = 0,
                anchor = GridBagConstraints.EAST, fill = GridBagConstraints.HORIZONTAL,
                new Insets(top = 2 * SMALL_MARGIN, left = SMALL_MARGIN, bottom = 2 * SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Result label
        //
        resultLabel = new JLabel(RESULT_LABEL_PROCESSING_TEXT);
        resultLabel.setForeground(RESULT_LABEL_PROCESSING_TEXT_COLOR);
        resultLabel.setBackground(RESULT_LABEL_BACKGROUND_COLOR);
        resultLabel.setOpaque(true);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(RESULT_LABEL_PROCESSING_FONT_STYLE, RESULT_LABEL_PROCESSING_FONT_SIZE));
        contentPanel.add(resultLabel, new GridBagConstraints(gridx = 1, gridy = 0,
                gridwidth = 2, gridheight = 1,
                weightx = 1, weighty = 0,
                anchor = GridBagConstraints.CENTER, fill = GridBagConstraints.BOTH,
                new Insets(top = SMALL_MARGIN, left = 0, bottom = SMALL_MARGIN, right = 0),
                ipadx = 0, ipady = 0));

        //
        // Progress bar
        //
        progressBar = new JProgressBar(0, 100); // 100 is temporary
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        contentPanel.add(progressBar, new GridBagConstraints(gridx = 1, ++gridy,
                gridwidth = 2, gridheight = 1,
                weightx = 1, weighty = 0,
                anchor = GridBagConstraints.CENTER, fill = GridBagConstraints.BOTH,
                new Insets(top = SMALL_MARGIN, left = 0, bottom = SMALL_MARGIN, right = 0),
                ipadx = 0, ipady = 0));

        //
        // Future Economic Events label
        //
        JLabel futureEconomicEventsLabel = new JLabel(FUTURE_ECONOMIC_EVENTS_LABEL_TEXT);
        futureEconomicEventsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        futureEconomicEventsLabel.setFont(futureEconomicEventsLabel.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_LABEL_FONT_STYLE, FUTURE_ECONOMIC_EVENTS_LABEL_FONT_SIZE));
        contentPanel.add(futureEconomicEventsLabel, new GridBagConstraints(gridx = 0, ++gridy,
                gridwidth = 3, gridheight = 1,
                weightx = 0, weighty = 0,
                anchor = GridBagConstraints.WEST, fill = GridBagConstraints.HORIZONTAL,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Future Economic Events result label
        //
        futureEconomicEventsResultLabel = new JLabel(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT);
        futureEconomicEventsResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        futureEconomicEventsResultLabel.setText(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT);
        futureEconomicEventsResultLabel.setForeground(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT_COLOR);
        futureEconomicEventsResultLabel.setFont(futureEconomicEventsResultLabel.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_STYLE, FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_SIZE));
        contentPanel.add(futureEconomicEventsResultLabel, new GridBagConstraints(gridx = 2, gridy,
                gridwidth = 1, gridheight = 1,
                weightx = 0, weighty = 0,
                anchor = GridBagConstraints.EAST, fill = GridBagConstraints.HORIZONTAL,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Future Economic Events text area
        //
        futureEconomicEventsTextArea = new JTextArea();
        futureEconomicEventsTextArea.setEditable(false);
        futureEconomicEventsTextArea.setFont(futureEconomicEventsTextArea.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_TEXT_AREA_FONT_SIZE));
        JScrollPane futureEconomicEventsTextAreaScrollPane = new JScrollPane(futureEconomicEventsTextArea);

        contentPanel.add(futureEconomicEventsTextAreaScrollPane, new GridBagConstraints(gridx = 0, ++gridy,
                gridwidth = 3, gridheight = 1,
                weightx = 1, weighty = 1,
                anchor = GridBagConstraints.CENTER, fill = GridBagConstraints.BOTH,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Historical Economic Events label
        //
        JLabel historicalEconomicEventsLabel = new JLabel(HISTORICAL_ECONOMIC_EVENTS_LABEL_TEXT);
        historicalEconomicEventsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        historicalEconomicEventsLabel.setFont(historicalEconomicEventsLabel.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_LABEL_FONT_STYLE, HISTORICAL_ECONOMIC_EVENTS_LABEL_FONT_SIZE));
        contentPanel.add(historicalEconomicEventsLabel, new GridBagConstraints(gridx = 0, ++gridy,
                gridwidth = 3, gridheight = 1,
                weightx = 0, weighty = 0,
                anchor = GridBagConstraints.WEST, fill = GridBagConstraints.HORIZONTAL,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Historical Economic Events result label
        //
        historicalEconomicEventsResultLabel = new JLabel(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT);
        historicalEconomicEventsResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        historicalEconomicEventsResultLabel.setText(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT);
        historicalEconomicEventsResultLabel.setForeground(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_TEXT_COLOR);
        historicalEconomicEventsResultLabel.setFont(historicalEconomicEventsResultLabel.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_STYLE, HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_WAITING_FONT_SIZE));
        contentPanel.add(historicalEconomicEventsResultLabel, new GridBagConstraints(gridx = 2, gridy,
                gridwidth = 1, gridheight = 1,
                weightx = 0, weighty = 0,
                anchor = GridBagConstraints.EAST, fill = GridBagConstraints.HORIZONTAL,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Historical Economic Events text area
        //
        historicalEconomicEventsTextArea = new JTextArea();
        historicalEconomicEventsTextArea.setEditable(false);
        historicalEconomicEventsTextArea.setFont(historicalEconomicEventsTextArea.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_TEXT_AREA_FONT_SIZE));
        JScrollPane historicalEconomicEventsTextAreaScrollPane = new JScrollPane(historicalEconomicEventsTextArea);

        contentPanel.add(historicalEconomicEventsTextAreaScrollPane, new GridBagConstraints(gridx = 0, ++gridy,
                gridwidth = 3, gridheight = 1,
                weightx = 1, weighty = 1,
                anchor = GridBagConstraints.CENTER, fill = GridBagConstraints.BOTH,
                new Insets(top = SMALL_MARGIN, left = SMALL_MARGIN, bottom = SMALL_MARGIN, right = SMALL_MARGIN),
                ipadx = 0, ipady = 0));

        //
        // Close button
        //
        closeButton = new JButton(CLOSE_BUTTON_TEXT);
        closeButton.setForeground(CLOSE_BUTTON_TEXT_COLOR);
        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        closeButton.setFont(closeButton.getFont().deriveFont(CLOSE_BUTTON_FONT_STYLE, CLOSE_BUTTON_FONT_SIZE));
        closeButton.setEnabled(false);
        contentPanel.add(closeButton, new GridBagConstraints(gridx, ++gridy,
                gridwidth = 3, gridheight = 1,
                weightx = 1, weighty = 0,
                anchor = GridBagConstraints.CENTER, fill = GridBagConstraints.NONE,
                new Insets(top = SMALL_MARGIN, left = 0, bottom = SMALL_MARGIN, right = 0),
                ipadx = 0, ipady = 0));

        //
        // end of initialization of the JFrame
        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Constants.APP_NAME + " - " + Constants.VERSION + " - by " + Constants.AUTHOR);
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setContentPane(contentPanel);
    }

    /**
     * Set the maximum value of the progress bar
     * @param max maximum value
     */
    public void setProgressBarMaximum(int max) {
        progressBar.setMaximum(max);
    }

    /**
     * Set the current value of the progress bar
     * @param value current value
     */
    public void setProgressBarValue(int value) {
        progressBar.setValue(value);
    }

    /**
     * Sets result label to "OK" (after "processing", and versus "Problem")
     */
    public void setResultLabelToOK() {
        resultLabel.setText(RESULT_LABEL_OK_TEXT);
        resultLabel.setForeground(RESULT_LABEL_OK_TEXT_COLOR);
        resultLabel.setFont(resultLabel.getFont().deriveFont(RESULT_LABEL_OK_FONT_STYLE, RESULT_LABEL_OK_FONT_SIZE));
    }

    /**
     * Sets result label to "Problem" (after "processing", and versus "OK")
     */
    public void setResultLabelToProblem() {
        resultLabel.setText(RESULT_LABEL_PROBLEM_TEXT);
        resultLabel.setForeground(RESULT_LABEL_PROBLEM_TEXT_COLOR);
        resultLabel.setFont(resultLabel.getFont().deriveFont(RESULT_LABEL_PROBLEM_FONT_STYLE, RESULT_LABEL_PROBLEM_FONT_SIZE));
    }

    /**
     * Print text in the Future Economic Events text area
     * @param s string to be printed
     */
    public void printlnInFutureEconomicEventsTextArea(String s) {
        futureEconomicEventsTextArea.append(s + System.getProperty("line.separator"));
        futureEconomicEventsTextArea.setCaretPosition((int) (futureEconomicEventsTextArea.getDocument().getLength()));
    }

    /**
     * Print text in the Historical Economic Events text area
     * @param s string to be printed
     */
    public void printlnInHistoricalEconomicEventsTextArea(String s) {
        historicalEconomicEventsTextArea.append(s + System.getProperty("line.separator"));
        historicalEconomicEventsTextArea.setCaretPosition((int) (historicalEconomicEventsTextArea.getDocument().getLength()));
    }

    /**
     * Print exception in the Future Economic Events text area
     * @param e exception to be printed
     */
    public void printExceptionInFutureEconomicEventsTextArea(Throwable e) {
        printlnInFutureEconomicEventsTextArea("");
        printlnInFutureEconomicEventsTextArea(e.getMessage());
        for (StackTraceElement ste : e.getStackTrace()) {
            printlnInFutureEconomicEventsTextArea(ste.toString());
        }
    }

    /**
     * Print exception in the Historical Economic Events text area
     * @param e exception to be printed
     */
    public void printExceptionInHistoricalEconomicEventsTextArea(Throwable e) {
        printlnInHistoricalEconomicEventsTextArea("");
        printlnInHistoricalEconomicEventsTextArea(e.getMessage());
        for (StackTraceElement ste : e.getStackTrace()) {
            printlnInHistoricalEconomicEventsTextArea(ste.toString());
        }
    }
    private int closeButtonCounter = Constants.CLOSE_BUTTON_TIMER_DURATION + 1;

    private int decreaseCloseButtonCounterAndReturnCounter() {
        closeButtonCounter--;
        return closeButtonCounter;
    }

    private void updateCloseButtonTextWithCounter(int counterValue) {
        closeButton.setText(CLOSE_BUTTON_TEXT + " (application will automatically close in " + counterValue + " seconds)");
    }

    /**
     * Enables close button
     * @param outcomeType true if outcome is "OK", false if outcome is "problem"
     */
    public void enableCloseButton(boolean outcomeType) {
        closeButton.setEnabled(true);
        if (outcomeType) {
            Timer closeButtonTimer = new Timer(1000, new ActionListener() { // 1000 ms = 1 s

                @Override
                public void actionPerformed(ActionEvent e) {
                    int counterValue = decreaseCloseButtonCounterAndReturnCounter();
                    updateCloseButtonTextWithCounter(counterValue);
                    if (counterValue == 0) {
                        System.exit(0);
                    }
                }
            });
            closeButtonTimer.start();
        }
    }

    /**
     * Sets the result label for Future Economic Events to "Processing" status
     */
    public void setFutureEconomicEventsResultLabelToProcessing() {
        futureEconomicEventsResultLabel.setText(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT);
        futureEconomicEventsResultLabel.setForeground(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT_COLOR);
        futureEconomicEventsResultLabel.setFont(futureEconomicEventsResultLabel.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_STYLE, FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_SIZE));
    }

    /**
     * Sets the result label for Future Economic Events to "OK" status
     */
    public void setFutureEconomicEventsResultLabelToOK() {
        futureEconomicEventsResultLabel.setText(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT);
        futureEconomicEventsResultLabel.setForeground(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT_COLOR);
        futureEconomicEventsResultLabel.setFont(futureEconomicEventsResultLabel.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_STYLE, FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_SIZE));
    }

    /**
     * Sets the result label for Future Economic Events to "Problem" status
     */
    public void setFutureEconomicEventsResultLabelToProblem() {
        futureEconomicEventsResultLabel.setText(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT);
        futureEconomicEventsResultLabel.setForeground(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT_COLOR);
        futureEconomicEventsResultLabel.setFont(futureEconomicEventsResultLabel.getFont().deriveFont(FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_STYLE, FUTURE_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_SIZE));
    }

    /**
     * Sets the result label for Historical Economic Events to "Processing" status
     */
    public void setHistoricalEconomicEventsResultLabelToProcessing() {
        historicalEconomicEventsResultLabel.setText(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT);
        historicalEconomicEventsResultLabel.setForeground(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_TEXT_COLOR);
        historicalEconomicEventsResultLabel.setFont(historicalEconomicEventsResultLabel.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_STYLE, HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROCESSING_FONT_SIZE));
    }

    /**
     * Sets the result label for Historical Economic Events to "OK" status
     */
    public void setHistoricalEconomicEventsResultLabelToOK() {
        historicalEconomicEventsResultLabel.setText(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT);
        historicalEconomicEventsResultLabel.setForeground(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_TEXT_COLOR);
        historicalEconomicEventsResultLabel.setFont(historicalEconomicEventsResultLabel.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_STYLE, HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_OK_FONT_SIZE));
    }

    /**
     * Sets the result label for Historical Economic Events to "Problem" status
     */
    public void setHistoricalEconomicEventsResultLabelToProblem() {
        historicalEconomicEventsResultLabel.setText(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT);
        historicalEconomicEventsResultLabel.setForeground(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_TEXT_COLOR);
        historicalEconomicEventsResultLabel.setFont(historicalEconomicEventsResultLabel.getFont().deriveFont(HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_STYLE, HISTORICAL_ECONOMIC_EVENTS_RESULT_LABEL_PROBLEM_FONT_SIZE));
    }
}