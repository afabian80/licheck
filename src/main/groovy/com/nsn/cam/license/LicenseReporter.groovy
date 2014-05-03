package com.nsn.cam.license

class LicenseReporter {
	File reportFile
	def missingLicenses = [:]
	def okayLicenses = [:]

	LicenseReporter(File reportFile) {
		this.reportFile = reportFile
	}

	void reportMissing(dep, userProjects) {
		missingLicenses[dep] = userProjects
	}

	void reportFound(dep, userProjects) {
		okayLicenses[dep] = userProjects
	}
}