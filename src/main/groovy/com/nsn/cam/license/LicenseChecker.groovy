package com.nsn.cam.license

class LicenseChecker { 
	private final static String POM_FILE_NAME = 'effective_pom.xml'
	private final static String REGISTRY_FILE_NAME = '/license_registry.database'
	private final static String REPORT_FILE_NAME = 'license_report.xml'

	public static void main(String[] args) {
		File pomFile = new File(POM_FILE_NAME)
		File licenseRegistryFile = new File(this.class.getResource(REGISTRY_FILE_NAME).getFile())
		File reportFile = new File(REPORT_FILE_NAME)

		DependencyCollector dc = new DependencyCollector(pomFile)
		LicenseRegistry registry = new LicenseRegistry(licenseRegistryFile)
		LicenseReporter reporter = new LicenseReporter(reportFile)

		dc.collectDependencies()
		
		dc.dependencies.each { dep, userProjects ->
			if (registry.containsMajor(dep)) {
				reporter.reportFound(dep)
			}
			else {
				reporter.reportMissing(dep, userProjects)
			}
		}
	}
}