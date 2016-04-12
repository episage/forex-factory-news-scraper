package main;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

/**
 * Main class of the application. Launches the GUI and the process
 *
 * @author Nicolas11 @ BMT
 */
public class Main {
    final private static int FOREX_FACTORY_FIRST_YEAR_2007 = 2007; // v.5.1
    final private static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
    //
    private static int progressCounter = 0;
    private static GregorianCalendar yesterday;
    private static int monthOfYesterday;
    private static int yearOfMonthBeforeYesterday;
    private static int monthBeforeYesterday;
    private static int yearOfYesterday;
    private static int dayOfYesterday;
    private static int yearOfLastDate;
    private static int monthOfLastDate;
    private static int dayOfLastDate;
    private static int numberOfMonthsOfHistoricalDataToDownload;

    private static int step1_nbMonthsToDownloadForFutureEconomicEvents() {
        return 2;
    }

    private static int step2_nbMonthsToDownloadForHistoricalEconomicEvents(GUIFrame frame) throws FileNotFoundException {
        // Yesterday:
        yesterday = new GregorianCalendar();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        yearOfYesterday = yesterday.get(Calendar.YEAR);
        monthOfYesterday = yesterday.get(Calendar.MONTH) + 1;
        dayOfYesterday = yesterday.get(Calendar.DAY_OF_MONTH);

        if (!Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE.exists()) {
            return step2_fillNewHistoricalFile(frame);
        } else {
            return step2_appendHistoricalFile(frame);
        }
    }

    private static int step2_fillNewHistoricalFile(GUIFrame frame) {
        // Month before Yesterday:
        monthBeforeYesterday = monthOfYesterday - 1;
        yearOfMonthBeforeYesterday = yearOfYesterday;
        if (monthBeforeYesterday == 0) {
            monthBeforeYesterday = 12;
            yearOfMonthBeforeYesterday = yearOfYesterday - 1;
        }
        //
        frame.printlnInHistoricalEconomicEventsTextArea("File " + Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME + " does not exist, perhaps because this is the first time that you run this application.");
        numberOfMonthsOfHistoricalDataToDownload = (yearOfMonthBeforeYesterday - FOREX_FACTORY_FIRST_YEAR_2007) * 12
                + monthBeforeYesterday
                + 1; // this month
        frame.printlnInHistoricalEconomicEventsTextArea(numberOfMonthsOfHistoricalDataToDownload + " months of economic events shall be downloaded, parsed and stored.");
        return numberOfMonthsOfHistoricalDataToDownload;
    }

    private static int step2_appendHistoricalFile(GUIFrame frame) throws FileNotFoundException {
        frame.printlnInHistoricalEconomicEventsTextArea("File " + Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME + " already exists.");

        // last date of the file?
        Scanner scanner = new Scanner(Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE);
        String lastLine = "";
        while (scanner.hasNextLine()) {
            lastLine = scanner.nextLine();
        }
        scanner.close();

        if (lastLine.length() < 8) {
            return step2_fillNewHistoricalFile(frame);
        }

        String lastDateAsString = lastLine.substring(0, 10);
        yearOfLastDate = Integer.parseInt(lastDateAsString.substring(0, 4));
        monthOfLastDate = Integer.parseInt(lastDateAsString.substring(5, 7));
        dayOfLastDate = Integer.parseInt(lastDateAsString.substring(8, 10));
        GregorianCalendar lastDate = new GregorianCalendar(yearOfLastDate, monthOfLastDate - 1, dayOfLastDate);
        frame.printlnInHistoricalEconomicEventsTextArea("Date of the last event of the file is: " + sdf.format(lastDate.getTime()));
        // last date == yesterday?
        if ((lastDate.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR)) && (lastDate.get(Calendar.MONTH) == yesterday.get(Calendar.MONTH)) && (lastDate.get(Calendar.DAY_OF_MONTH) == yesterday.get(Calendar.DAY_OF_MONTH))) {
            // ****
            // ** HISTORICAL FILE ALREADY EXISTS AND IS UP TO DATE
            // ****
            frame.printlnInHistoricalEconomicEventsTextArea("This date is the date of yesterday. The file is up to date.");
            numberOfMonthsOfHistoricalDataToDownload = 0;
        } else {
            // ****
            // ** HISTORICAL FILE ALREADY EXISTS BUT SHALL BE COMPLETED
            // ****
            numberOfMonthsOfHistoricalDataToDownload = (yearOfYesterday - yearOfLastDate) * 12
                    + (monthOfYesterday - monthOfLastDate)
                    + 1;
        }

        return numberOfMonthsOfHistoricalDataToDownload;
    }

    private static void step3_proceedForFutureEconomicEvents(GUIFrame frame) throws IOException, Exception {
        frame.setFutureEconomicEventsResultLabelToProcessing();

        // what is the current month?
        GregorianCalendar gc = new GregorianCalendar();
        int currentMonth = gc.get(Calendar.MONTH) + 1;
        // what is the current year?
        int yearOfCurrentMonth = gc.get(Calendar.YEAR);
        // What is the next month? its year?
        int nextMonth = currentMonth + 1;
        int yearOfNextMonth = yearOfCurrentMonth;
        if (nextMonth == 13) {
            nextMonth = 1;
            yearOfNextMonth++;
        }

        FileWriter futureEconomicEventsCsvFileWriter = new FileWriter(Constants.FUTURE_ECONOMIC_EVENTS_OUTPUT_FILE);
        // this month:
        frame.printlnInFutureEconomicEventsTextArea("[1/2] Download, parse and store Forex Factory economic events for the current month (" + currentMonth + "/" + yearOfCurrentMonth + ")...");
        String currentMonthWebPage = DownloadToString.downloadOneMonthToString(yearOfCurrentMonth, currentMonth);
FileWriter friter = new FileWriter("dump.html");
friter.write(currentMonthWebPage, 0, currentMonthWebPage.length());
friter.close();
        ParseStringAndSave.parseStringAndSaveToFile(currentMonthWebPage, futureEconomicEventsCsvFileWriter, yearOfCurrentMonth, currentMonth, 1, 31);
        frame.setProgressBarValue(++progressCounter);
        // next month:
        frame.printlnInFutureEconomicEventsTextArea("[2/2] Download, parse and store Forex Factory economic events for next month (" + nextMonth + "/" + yearOfNextMonth + ")...");
        String nextMonthWebPage = DownloadToString.downloadOneMonthToString(yearOfNextMonth, nextMonth);
        ParseStringAndSave.parseStringAndSaveToFile(nextMonthWebPage, futureEconomicEventsCsvFileWriter, yearOfNextMonth, nextMonth, 1, 31);
        frame.setProgressBarValue(++progressCounter);
        //
        futureEconomicEventsCsvFileWriter.close();
        //
        frame.printlnInFutureEconomicEventsTextArea("");
        frame.printlnInFutureEconomicEventsTextArea("Economic events for current and next months are now stored in the following CSV file located near the present executable:");
        frame.printlnInFutureEconomicEventsTextArea("   Name: " + Constants.FUTURE_ECONOMIC_EVENTS_OUTPUT_FILENAME);
        String directory = Constants.FUTURE_ECONOMIC_EVENTS_OUTPUT_FILE.getAbsolutePath();
        directory = directory.substring(0, directory.length() - Constants.FUTURE_ECONOMIC_EVENTS_OUTPUT_FILENAME.length());
        frame.printlnInFutureEconomicEventsTextArea("   Directory: " + directory);
    }

    private static void step4_proceedForHistoricalEconomicEvents(GUIFrame frame) throws IOException, Exception {
        frame.setHistoricalEconomicEventsResultLabelToProcessing();

        if (!Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE.exists() || yearOfLastDate == 0) {
            step4_fillNewHistoricalFile(frame);
        } else {
            step4_appendHistoricalFile(frame);
        }
    }

    private static void step4_fillNewHistoricalFile(GUIFrame frame) throws IOException, Exception {
        FileWriter historicalEconomicEventsCsvFileWriter = new FileWriter(Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE);
        int counter = 0;
        String webPage;
        // Full years until yearOfMonthBeforeYesterday - 1:
        for (int year = FOREX_FACTORY_FIRST_YEAR_2007; year <= yearOfMonthBeforeYesterday - 1; year++) {
            for (int month = 1; month <= 12; month++) {
                counter++;
                frame.printlnInHistoricalEconomicEventsTextArea("[" + counter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + month + "/" + year + "...");
                webPage = DownloadToString.downloadOneMonthToString(year, month);
                ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, year, month, 1, 31);
                frame.setProgressBarValue(++progressCounter);
            }
        }
        // Year "yearOfMonthBeforeYesterday" between month 1 and month "monthBeforeYesterday":
        for (int month = 1; month <= monthBeforeYesterday; month++) {
            counter++;
            frame.printlnInHistoricalEconomicEventsTextArea("[" + counter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + month + "/" + yearOfMonthBeforeYesterday + "...");
            webPage = DownloadToString.downloadOneMonthToString(yearOfMonthBeforeYesterday, month);
            ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, yearOfMonthBeforeYesterday, month, 1, 31);
            frame.setProgressBarValue(++progressCounter);
        }
        // Month of yesterday, but just until day of yesterday:
        counter++;
        frame.printlnInHistoricalEconomicEventsTextArea("[" + counter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + monthOfYesterday + "/" + yearOfYesterday + "...");
        webPage = DownloadToString.downloadOneMonthToString(yearOfYesterday, monthOfYesterday);
        ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, yearOfYesterday, monthOfYesterday, 1, dayOfYesterday);
        frame.setProgressBarValue(++progressCounter);
        //
        historicalEconomicEventsCsvFileWriter.close();

        frame.printlnInHistoricalEconomicEventsTextArea("");
        frame.printlnInHistoricalEconomicEventsTextArea("Historical economic events are now stored in the following CSV file located near the present executable:");
        frame.printlnInHistoricalEconomicEventsTextArea("   Name: " + Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME);
        String directory = Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE.getAbsolutePath();
        directory = directory.substring(0, directory.length() - Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME.length());
        frame.printlnInHistoricalEconomicEventsTextArea("   Directory: " + directory);
    }

    private static void step4_appendHistoricalFile(GUIFrame frame) throws IOException, Exception {
        if (numberOfMonthsOfHistoricalDataToDownload == 0) {
            // ****
            // ** HISTORICAL FILE ALREADY EXISTS AND IS UP TO DATE
            // ****

            frame.printlnInHistoricalEconomicEventsTextArea("");
            frame.printlnInHistoricalEconomicEventsTextArea("This file containing historical economic events is located near the present executable:");
            frame.printlnInHistoricalEconomicEventsTextArea("   Name: " + Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME);
            String directory = Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE.getAbsolutePath();
            directory = directory.substring(0, directory.length() - Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME.length());
            frame.printlnInHistoricalEconomicEventsTextArea("   Directory: " + directory);
        } else {
            // ****
            // ** HISTORICAL FILE ALREADY EXISTS AND SHALL BE COMPLETED
            // ****

            frame.setHistoricalEconomicEventsResultLabelToProcessing();
            frame.printlnInHistoricalEconomicEventsTextArea("The application is going to complete the file with all events between that date and yesterday ("
                    + sdf.format(yesterday.getTime())
                    + ").");
            FileWriter historicalEconomicEventsCsvFileWriter = new FileWriter(Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE, true);

            int historicalCounter = 0;

            // if yesterday and last date are within the same month, just complete with the difference:
            if ((monthOfYesterday == monthOfLastDate) && (yearOfYesterday == yearOfLastDate)) {
                historicalCounter++;
                frame.printlnInHistoricalEconomicEventsTextArea("[" + historicalCounter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + monthOfYesterday + "/" + yearOfYesterday + "...");
                String webPage = DownloadToString.downloadOneMonthToString(yearOfYesterday, monthOfYesterday);
                ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, yearOfYesterday, monthOfYesterday, dayOfLastDate + 1, dayOfYesterday);
                frame.setProgressBarValue(++progressCounter);
            } else { // yesterday and last are not within the same month
                String webPage;
                // First, finish the month:
                historicalCounter++;
                frame.printlnInHistoricalEconomicEventsTextArea("[" + historicalCounter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + monthOfLastDate + "/" + yearOfLastDate + "...");
                webPage = DownloadToString.downloadOneMonthToString(yearOfLastDate, monthOfLastDate);
                ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, yearOfLastDate, monthOfLastDate, dayOfLastDate + 1, 31);
                frame.setProgressBarValue(++progressCounter);
                // Second, next months:
                int currentYear = yearOfLastDate;
                int currentMonth = monthOfLastDate;
                while (!((monthOfYesterday == currentMonth) && (yearOfYesterday == currentYear))) {
                    currentMonth = currentMonth + 1;
                    if (currentMonth == 13) {
                        currentMonth = 1;
                        currentYear = currentYear + 1;
                    }
                    historicalCounter++;
                    frame.printlnInHistoricalEconomicEventsTextArea("[" + historicalCounter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + currentMonth + "/" + currentYear + "...");
                    webPage = DownloadToString.downloadOneMonthToString(currentYear, currentMonth);
                    ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, currentYear, currentMonth, 1, 31);
                    frame.setProgressBarValue(++progressCounter);
                }
                // Third, month of yesterday:
                historicalCounter++;
                frame.printlnInHistoricalEconomicEventsTextArea("[" + historicalCounter + "/" + numberOfMonthsOfHistoricalDataToDownload + "] Download, parse and store Forex Factory economic events for the month " + monthOfYesterday + "/" + yearOfYesterday + "...");
                webPage = DownloadToString.downloadOneMonthToString(yearOfYesterday, monthOfYesterday);
                ParseStringAndSave.parseStringAndSaveToFile(webPage, historicalEconomicEventsCsvFileWriter, yearOfYesterday, monthOfYesterday, 1, dayOfYesterday);
                frame.setProgressBarValue(++progressCounter);
            }


            historicalEconomicEventsCsvFileWriter.close();

            frame.printlnInHistoricalEconomicEventsTextArea("");
            frame.printlnInHistoricalEconomicEventsTextArea("File containing historical economic events is now completed. It is located near the present executable:");
            frame.printlnInHistoricalEconomicEventsTextArea("   Name: " + Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME);
            String directory = Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE.getAbsolutePath();
            directory = directory.substring(0, directory.length() - Constants.HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME.length());
            frame.printlnInHistoricalEconomicEventsTextArea("   Directory: " + directory);
        }
    }

    /**
     * "Main" method of the application. Launches the GUI and the process
     *
     * @param args
     */
    public static void main(String[] args) {
        // Launch the GUI:
        GUIFrame frame = new GUIFrame();
        frame.setVisible(true);
        boolean lastStepOK = true;
        // Step 1 - Nb of months to download for future economic events:
        int nbOfMonthsForFutureEconomicEvents = step1_nbMonthsToDownloadForFutureEconomicEvents();
        // Step 2 - Nb of months to download for historical economic events:
        int nbOfMonthsForHistoricalEconomicEvents = 0;
        try {
            nbOfMonthsForHistoricalEconomicEvents = step2_nbMonthsToDownloadForHistoricalEconomicEvents(frame);
        } catch (Exception ex) {
            lastStepOK = false;
            frame.setResultLabelToProblem();
            frame.enableCloseButton(false);
            frame.printExceptionInHistoricalEconomicEventsTextArea(ex);
            Throwable t = ex;
            while (t.getCause() != null) {
                t = t.getCause();
                frame.printExceptionInHistoricalEconomicEventsTextArea(t);
            }
            if (Constants.VERBOSE) {
                ex.printStackTrace();
            }
        }
        // Set maximum of the progress bar:
        frame.setProgressBarMaximum(nbOfMonthsForFutureEconomicEvents + nbOfMonthsForHistoricalEconomicEvents);
        // Step 3 - Launch the process for future economic events:
        if (lastStepOK) {
            try {
                step3_proceedForFutureEconomicEvents(frame);
                frame.setFutureEconomicEventsResultLabelToOK();
            } catch (Exception ex) {
                lastStepOK = false;
                frame.setFutureEconomicEventsResultLabelToProblem();
                frame.setResultLabelToProblem();
                frame.enableCloseButton(false);
                frame.printExceptionInFutureEconomicEventsTextArea(ex);
                Throwable t = ex;
                while (t.getCause() != null) {
                    t = t.getCause();
                    frame.printExceptionInFutureEconomicEventsTextArea(t);
                }
                if (Constants.VERBOSE) {
                    ex.printStackTrace();
                }
            }
        }
        // Step 4 - Launch the process for historical economic events:
        if (lastStepOK) {
            try {
                step4_proceedForHistoricalEconomicEvents(frame);
                frame.setHistoricalEconomicEventsResultLabelToOK();
                frame.setResultLabelToOK();
                frame.enableCloseButton(true);
            } catch (Exception ex) {
                frame.setHistoricalEconomicEventsResultLabelToProblem();
                frame.setResultLabelToProblem();
                frame.enableCloseButton(false);
                frame.printExceptionInHistoricalEconomicEventsTextArea(ex);
                Throwable t = ex;
                while (t.getCause() != null) {
                    t = t.getCause();
                    frame.printExceptionInHistoricalEconomicEventsTextArea(t);
                }
                if (Constants.VERBOSE) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
