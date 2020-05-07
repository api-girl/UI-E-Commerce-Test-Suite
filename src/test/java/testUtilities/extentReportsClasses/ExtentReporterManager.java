package testUtilities.extentReportsClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;


public class ExtentManager {

    private static ExtentReports extent;
    private static String reportFileName = "Test-Report" + ".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.home") + fileSeperator + fileSeperator+"TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();

        return extent;
    }

    //Create an extent report instance
    public static ExtentReports createInstance() {

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportFilepath);


        reporter.config().setDocumentTitle("Extent Report"); //the page title
        reporter.config().setReportName(reportFileName); //the name of the report
        reporter.config().setEncoding("utf-8");
        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        reporter.config().setTheme(Theme.STANDARD);




        extent = new ExtentReports();
        extent.attachReporter(reporter);

        return extent;
    }

    //Create the report path
    private static String getReportPath (String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
        return reportFileLocation;
    }
}
