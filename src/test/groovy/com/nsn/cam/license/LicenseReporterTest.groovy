package com.nsn.cam.license

class LicenseReporterTest extends GroovyTestCase {
	private LicenseReporter reporter
	private final static String REPORT_FILE_NAME = 'license_report.txt'

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
		assert reporter.foundLicenses.keySet().contains(dep)
	}

	void testGenerateReport() {
		String missingDep = 'group1:artifact1:1.0'
		String foundDep = 'group2:artifact2:2.0'
		reporter.reportMissing(missingDep, ['project1', 'project2'])
		reporter.reportFound(foundDep, ['project3', 'project4'])

		String reportStr = reporter.generateReport(false)
		println reportStr
		def report = new XmlSlurper().parseText(reportStr)
		def missing = report.missing
		assert missing.size() == 1
		assert missing.dependency.size() == 1
		def elems = missing.dependency.findAll { it.@name == missingDep }
		assert elems.size() == 1

		def found = report.found
		assert found.size() == 1
		assert found.dependency.size() == 1
		assert found.dependency.findAll { it.@name == foundDep }.size() == 1
	}
}