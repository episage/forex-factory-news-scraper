package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This class basically provides for a method which downloads a Forex Factory
 * Web page corresponding to a full month and returns the corresponding string
 *
 * @author Nicolas11 @ BMT
 */
public class DownloadToString {

    /**
     * Returns the URL of the Forex Factory Web page containing the econmomic
     * events for a given month
     *
     * @param year year
     * @param month month
     * @return url
     */
    public static String forexFactoryURL(int year, int month) {
        String monthString;
        switch (month) {
            case 1:
                monthString = "jan";
                break;
            case 2:
                monthString = "feb";
                break;
            case 3:
                monthString = "mar";
                break;
            case 4:
                monthString = "apr";
                break;
            case 5:
                monthString = "may";
                break;
            case 6:
                monthString = "jun";
                break;
            case 7:
                monthString = "jul";
                break;
            case 8:
                monthString = "aug";
                break;
            case 9:
                monthString = "sep";
                break;
            case 10:
                monthString = "oct";
                break;
            case 11:
                monthString = "nov";
                break;
            case 12:
                monthString = "dec";
                break;
            default:
                monthString = "Invalid month";
                throw new IllegalStateException("Should not happen (forexFactoryURL)");
        }
        // return:
        return "http://www.forexfactory.com/calendar.php?month=" + monthString + "." + year;
        // typically: http://www.forexfactory.com/calendar.php?month=jan.2009
        // previously: http://www.forexfactory.com/calendar.php?month=02&year=2012&fullmonth=1
    }

    /**
     * Downloads a Forex Factory Web page corresponding to a full month and
     * returns the corresponding string
     *
     * @param year year
     * @param month month
     * @return string containing the content of the Forex Factory Web page
     * @throws IOException in case of problem in the downloading of the Web page
     */
    public static String downloadOneMonthToString(final int year, final int month) throws IOException {
        InputStream is = null;

        try {
            StringBuilder bf = new StringBuilder();
            URL url = new URL(forexFactoryURL(year, month));
            is = url.openStream();  // throws an IOException
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                line = line + System.getProperty("line.separator");
                bf.append(line);
            }
            return bf.toString();
        } catch (IOException ioe) {
            throw new IOException("Problem in downloading the Web page corresponding to " + month + "/" + year, ioe);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }
}
