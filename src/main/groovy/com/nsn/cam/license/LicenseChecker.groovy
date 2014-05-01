package com.nsn.cam.license

class LicenseChecker { 
	private final static String POM_FILE = 'effective_pom.xml'
	private final static String REGISTRY_FILE = 'license_registry.xml'
	private final static String REPORT_FILE = 'license_report.xml'

	public static void main(String[] args) {
		DependencyCollector dc = new DependencyCollector(new File(POM_FILE))
		dc.collectDependencies()
		LicenseRegistry registry = new LicenseRegistry(new File(REGISTRY_FILE))
		LicenseReporter reporter = new LicenseReporter(new File(REPORT_FILE))
		dc.dependencies.each { dep, userProjects ->
			if (registry.contains(dep)) {
				reporter.reportFound(dep)
			}
			else {
				reporter.reportMissing(dep, userProjects)
			}
		}
		println "License checking done."
	}
}