package com.nsn.cam.license

import com.nsn.cam.license.DependencyCollector

// TODO: rename to LicenseChecker
class LicenseChecker { 
	private final static String POM_FILE = 'effective-pom.xml' // TODO: use underscore instead
	private final static String REGISTRY_FILE = 'license_registry.xml'
	private final static String REPORT_FILE = 'license_report.xml'

	public static void main(String[] args) {
		DependencyCollector dc = new DependencyCollector(new File(POM_FILE))
		dc.collectDependencies()
		LicenseRegistry registry = new LicenseRegistry(new File(REGISTRY_FILE))
		LicenseReporter reporter = new LicenseReporter(new File(REPORT_FILE))
		dc.dependencies.each { dep, userProjects ->
			if (!registry.contains(dep)) {
				reporter.reportMissing(dep, userProjects)
			}
			else {
				reporter.reportFound(dep)
			}
		}
		println "License checking done."
	}
}