import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestLinkApi {

	public static void main(String[] args) {
		// String url =
		// "http://au03smbcqa02.dev.smbc.nasdaqomx.com/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
		String url = "http://127.0.0.1/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
		String devKey = "6ab4b64c40af9cb23d72a1f25885b9f2";
		String projectName = "LDAP Config";
		String planName = "Release_2.0.0_4";
		String buildName = "2.0.0_4_1";
		String customFieldForClass = "Automation Class";
		String customFieldForData = "Test Data";

		TestLinkAPI api = null;

		URL testlinkURL = null;

		try {
			testlinkURL = new URL(url);
		} catch (MalformedURLException mue) {
			mue.printStackTrace(System.err);
			System.exit(-1);
		}
		// connect to test link
		try {
			api = new TestLinkAPI(testlinkURL, devKey);
		} catch (TestLinkAPIException te) {
			te.printStackTrace(System.err);
			System.exit(-1);
		}
		TestPlan testPlan = api.getTestPlanByName(planName, projectName);
		TestProject testProject = api.getTestProjectByName(projectName);
		TestCase[] testCases = api.getTestCasesForTestPlan(testPlan.getId(),
				null, null, null, null, null, null, null,
				ExecutionType.AUTOMATED, true, TestCaseDetails.FULL);

		// create build if not exist
		boolean hasBuild = false;
		Build[] builds = api.getBuildsForTestPlan(testPlan.getId());
		for (int i = 0; i < builds.length; i++) {
			if (buildName.equals(builds[i].getName())) {
				hasBuild = true;
				break;
			}
		}
		if (!hasBuild) {
			api.createBuild(testPlan.getId(), buildName, null);
		}
		// get test case info
		for (int i = 0; i < testCases.length; i++) {
			TestCase testcase = api.getTestCase(testCases[i].getId(), null,
					null);
			CustomField automationClass = api
					.getTestCaseCustomFieldDesignValue(testCases[i].getId(),
							Integer.valueOf(testCases[i].getFullExternalId()),
							testCases[i].getVersion(), testProject.getId(),
							customFieldForClass, null);
			CustomField testData = api.getTestCaseCustomFieldDesignValue(
					testCases[i].getId(),
					Integer.valueOf(testCases[i].getFullExternalId()),
					testCases[i].getVersion(), testProject.getId(),
					customFieldForData, null);

			System.out.println(testcase.getVersion());
			System.out.println(automationClass.getValue());
			System.out.println(testData.getValue());

			// update test results
			api.reportTCResult(testCases[i].getId(), null, testPlan.getId(),
					ExecutionStatus.FAILED, null, buildName, null, false, null,
					null, null, null, true);
		}
	}
}
