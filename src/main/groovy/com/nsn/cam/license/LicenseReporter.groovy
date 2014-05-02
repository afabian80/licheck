package com.nsn.cam.license

class LicenseReporter {
	File reportFile

	LicenseReporter(File reportFile) {
		this.reportFile = reportFile
	}

	void reportMissing(dep, userProjects) {
		//println("Missing: $dep (used by ${userProjects.size()} modules)")
	}

	void reportFound(dep) {
		println("OK: $dep")
	}
}