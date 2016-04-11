package main;

import java.io.File;

/**
 * Constants for the application
 * @author Nicolas11 @ BMT
 */
public class Constants {

    /**
     * Name of the application
     */
    public static String APP_NAME = "Economic Events Downloader";
    /**
     * Version of the application
     */
    public static String VERSION = "v5.1"; 
    /**
     * Author of the application
     */
    public static String AUTHOR = "Nicolas11 @ BMT";
    /**
     * Name of the output file for Future Economic Events
     */
    final public static String FUTURE_ECONOMIC_EVENTS_OUTPUT_FILENAME = "FutureEconomicEvents.txt";
    /**
     * Output file for Future Economic Events
     */
    final public static File FUTURE_ECONOMIC_EVENTS_OUTPUT_FILE = new File(FUTURE_ECONOMIC_EVENTS_OUTPUT_FILENAME);
    /**
     * Name of the output file for Historical Economic Events
     */
    final public static String HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME = "HistoricalEconomicEvents.txt";
    /**
     * Output file for Historical Economic Events
     */
    final public static File HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILE = new File(HISTORICAL_ECONOMIC_EVENTS_OUTPUT_FILENAME);
    /**
     * Duration the timer for automatic close
     */
    final public static int CLOSE_BUTTON_TIMER_DURATION = 180; // in seconds 
    /**
     * Verbose (true only in development phase)
     */
    final public static boolean VERBOSE = false;
}