package main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class basically provides for a method which parses the string containing
 * a Forex Factory Web page, extract the corresponding economic events and save
 * them to a file
 *
 * @author Nicolas11
 * @ BMT
 */
public class ParseStringAndSave {

    final private static int HOUR_OFFSET = 0; // compared to GMT-5 (=6 pour GMT+1)
    final private static Pattern DATE_PATTERN = Pattern.compile(">([\\w\\s]*)</span>");
    //final private static Pattern HOUR_PATTERN_1 = Pattern.compile("center\\\">([\\w\\s:-]*)</td>");
    //final private static Pattern HOUR_PATTERN_1 = Pattern.compile("td class=\\\"time\\\">([\\w\\s:-]*)</td>");
    final private static Pattern HOUR_PATTERN_1 = Pattern.compile("td class=\\\"calendar__cell calendar__time time\\\">([\\w\\s:-]*)</td>");
    //final private static Pattern HOUR_PATTERN_2 = Pattern.compile("upnext\\\">([\\w\\s:-]*)</span>"); // if "upnext"
    final private static Pattern HOUR_PATTERN_2 = Pattern.compile("a><span class=\\\"upnext\\\">([\\w\\s:-]*)</span>"); // if "upnext"
    final private static Pattern COUNTRY_PATTERN = Pattern.compile(">([\\w\\s]*)");
    // final private static Pattern IMPACT_PATTERN = Pattern.compile("=\\\"([\\w\\s-]*)\\\"></div>");
    // final private static Pattern EVENT_PATTERN = Pattern.compile(">([\\w\\s\\.\\*&;(),/-]*)</div>");
    final private static Pattern EVENT_PATTERN = Pattern.compile(">([\\w\\s\\.\\*&;(),/-]*)</span>");
    final private static DateFormat DATE_FORMAT_IN = new SimpleDateFormat("yyyy MMM d", Locale.ENGLISH);
    final private static DateFormat DATE_FORMAT_OUT = new SimpleDateFormat("MMdd");
    final private static DateFormat HOUR_FORMAT_IN = new SimpleDateFormat("h:mma");
    final private static DateFormat HOUR_FORMAT_OUT = new SimpleDateFormat("HHmm");
    //
    private static String currentDate;

    private static boolean containsDayOfWeek(String line) {
        return line.contains(">Sun<")
                || line.contains(">Mon<")
                || line.contains(">Tue<")
                || line.contains(">Wed<")
                || line.contains(">Thu<")
                || line.contains(">Fri<")
                || line.contains(">Sat<");
    }

    /**
     * Parses the string containing a Forex Factory Web page, extract the
     * corresponding economic events and save them to a file
     *
     * @param inputString string containing the Web page from Forex Factory
     * @param eventsCsvFileWriter writer to file
     * @param yearOfFile year of the considered month
     * @param monthOfFile month
     * @param firstDayOfMonthToBeConsidered only consider days greater or equal
     * compared to this parameter (1 for the whole beginning of the month)
     * @param lastDayOfMonthToBeConsidered only consider days less or equal
     * compared to this parameter (31 for the whole end of the month)
     * @throws IOException ... in case of problem in writing in the output file
     * @throws Exception ... in case of problem in parsing
     */
    public static void parseStringAndSaveToFile(String inputString, FileWriter eventsCsvFileWriter, int yearOfFile, int monthOfFile, int firstDayOfMonthToBeConsidered, int lastDayOfMonthToBeConsidered) throws IOException, Exception {

        String[] elements = inputString.split("</td>");

        String line;
        String dateString, hourString0, country, impact, event;
        String hourString = "";
        int impactDigit;
        Pattern p;
        Matcher m, m1, m2;
        boolean m1Found, m2Found;
        boolean emptyTime, noSpecificTime;

        String outputLine;

        int month = 0;
        int day = 0;
        int hour, minute;
        Date MonthAndDayDate = new Date();
        Date hourDate;
        GregorianCalendar monthAndDayGc = new GregorianCalendar();
        GregorianCalendar hourGc = new GregorianCalendar();
        GregorianCalendar fullDateGc = new GregorianCalendar();

        int index = 0;
        while (index < elements.length) {

            /*if (!elements[index].contains("eventid")) {
                index++;
                continue;
            }*/
            // 2016 Apr fixes
            if (!elements[index].contains("data-eventid") ||
            	(elements[index].contains("calendar__row") && (
        			elements[index].contains("calendar__details") ||
        			elements[index].contains("calendar__expand")
            	))
            ) {
                index++;
                continue;
            }

            // We have a new event...

            // Element with new possible new date; otherwise, same date as before 
            //
            line = elements[index];
            if (containsDayOfWeek(line)) { // it is a new date
                m = DATE_PATTERN.matcher(line);
                if (m.find()) {
                    dateString = m.group(1);
                    MonthAndDayDate = DATE_FORMAT_IN.parse(yearOfFile + " " + dateString);
                    // (we have to add the year in order that Feb.29 not to parsed as Mar.01)
                    monthAndDayGc.setTime(MonthAndDayDate);
                    month = monthAndDayGc.get(Calendar.MONTH);
                    day = monthAndDayGc.get(Calendar.DAY_OF_MONTH);
                    currentDate = "" + (yearOfFile - 1900) + DATE_FORMAT_OUT.format(MonthAndDayDate);
                } else {
                    throw new ParseException("Problem for identification of date in: " + line, 0);
                }
            } else {
                // no new date; same date as before
            }
            if (Constants.VERBOSE) {
                System.out.println("Date: " + currentDate + " ** " + elements[index]);
            }

            index++;
            // Elements with hour
            //
            line = elements[index] + "</td>";
            m1 = HOUR_PATTERN_1.matcher(line);
            m2 = HOUR_PATTERN_2.matcher(line);
            m1Found = m1.find();
            m2Found = m2.find();
            if (m1Found || m2Found) {
                if (m1Found && m2Found) {
                    throw new ParseException("Hour has been idenfied in both normal [* " + m1.group(1)
                            + " *] and upnext [* " + m2.group(1) + " *] patterns in: " + line, 0);
                }
                if (m1Found) {
                    hourString0 = m1.group(1);
                } else { // m2Found
                    hourString0 = m2.group(1);
                }
                emptyTime = "".equals(hourString0); // v5
                noSpecificTime = (!emptyTime) && (!(hourString0.contains("am") || hourString0.contains("pm"))); // v5

                if (emptyTime) {
                    // same hour as before // v5
                } else {
                    hourString = hourString0; // v5
                    if (!noSpecificTime) {
                        hourDate = HOUR_FORMAT_IN.parse(hourString);
                        hourGc.setTime(hourDate);
                        hour = hourGc.get(Calendar.HOUR_OF_DAY);
                        minute = hourGc.get(Calendar.MINUTE);
                        fullDateGc.set(yearOfFile, month, day, hour, minute);
                        fullDateGc.add(Calendar.HOUR_OF_DAY, HOUR_OFFSET);
                        currentDate = "" + (yearOfFile - 1900) + DATE_FORMAT_OUT.format(fullDateGc.getTime());
                        hourString = HOUR_FORMAT_OUT.format(fullDateGc.getTime());
                    } else {
                        currentDate = "" + (yearOfFile - 1900) + DATE_FORMAT_OUT.format(MonthAndDayDate);
                        // otherwise, we could have gone to the day after due to an offset in the previous field, whereas the previous field was at the same date
                        continue;
                    }
                }
            } else {
                throw new ParseException("Problem for identification of hour in: " + line, 0);
            }
            if (Constants.VERBOSE) {
                System.out.println("Hour: " + hourString + " ** " + elements[index]);
            }

            index++;
            // Element with country
            //
            m = COUNTRY_PATTERN.matcher(elements[index]);
            if (m.find()) {
                country = m.group(1);
            } else {
                country = "???";
                throw new ParseException("Problem for identification of country in: " + elements[index], 0);
            }
            if (Constants.VERBOSE) {
                System.out.println("Country: " + country);
            }

            index++;
            // Element with importance
            // 
            if (elements[index].contains("High")) {
                impactDigit = 3;
            } else if (elements[index].contains("Medium")) {
                impactDigit = 2;
            } else if (elements[index].contains("Low")) {
                impactDigit = 1;
            } else {
                impactDigit = 0;
            }
            if (Constants.VERBOSE) {
                System.out.println("Importance: " + impactDigit + " ** " + elements[index]);
            }

            index++;
            // Elements with title
            m = EVENT_PATTERN.matcher(elements[index]);
            if (m.find()) {
                event = m.group(1);
                event = event.replace(",", " -");
                event = event.replace("&amp;", "&");
            } else {
                event = "???";
                throw new ParseException("Problem in identification of event title in: " + elements[index], 0);
            }
            if (Constants.VERBOSE) {
                System.out.println("Title: " + event + " ** " + elements[index]);
            }

            if (noSpecificTime) {
                // nothing to do
            } else {
                outputLine = currentDate
                        + "," + hourString
                        + "," + impactDigit
                        + "," + country
                        + "," + event
                        + System.getProperty("line.separator");
                int dayOfMonth = Integer.parseInt(currentDate.substring(currentDate.length() - 2));
                if ((firstDayOfMonthToBeConsidered <= dayOfMonth) && (dayOfMonth <= lastDayOfMonthToBeConsidered)) {
                    eventsCsvFileWriter.write(outputLine, 0, outputLine.length());
                }
            }
        }
    }
}
