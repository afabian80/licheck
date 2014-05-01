package com.nsn.cam.license

class LicenseReporter {
	File reportFile

	LicenseReporter(File reportFile) {
		this.reportFile = reportFile
	}

	void reportMissing(dep, userProjects) {
		println("Missing entry for $dep")
	}

	void reportFound(dep) {
		println("OK: $dep")
	}
}