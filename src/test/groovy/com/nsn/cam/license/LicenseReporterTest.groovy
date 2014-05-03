package com.nsn.cam.license

class LicenseReporterTest extends GroovyTestCase {
	private LicenseReporter reporter
	private String REPORT_FILE_NAME = 'license_report.txt'

	void setUp() {
		super.setUp()
		File reportFile = new File(REPORT_FILE_NAME)
		reporter = new LicenseReporter(reportFile)
	}

	void testReportMissing() {
		String dep = 'grp:art:1.0'
		reporter.reportMissing(dep, ['project1', 'project2'])
		assert reporter.missingLicenses.keySet().contains(dep)
	}

	void testReportOkay() {
		String dep = 'grp:art:1.0'
		reporter.reportFound(dep, ['project1', 'project2'])
		assert reporter.okayLicenses.keySet().contains(dep)
	}
}