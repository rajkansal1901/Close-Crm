package testListners;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started ===> " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("\t ===> Test Passed ");
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("\t\t===> Test Failed ");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("\t\tTest Skipped ===> " + result.getName());
    }

}

