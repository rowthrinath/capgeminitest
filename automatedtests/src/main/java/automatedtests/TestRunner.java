package automatedtests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by rowthrinathnatarajan on 22/11/2016.
 */

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(loginpage.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
