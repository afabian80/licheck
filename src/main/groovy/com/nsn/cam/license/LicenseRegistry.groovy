package com.nsn.cam.license

class LicenseRegistry {
	File registryFile
	def artifactLicenses = [:]

	LicenseRegistry(File registryFile) {
		this.registryFile = registryFile
		parseDb()
	}

	boolean contains(String dep) {
		//TODO: compare based on major version
		return this.artifactLicenses.keySet().contains(dep)
	}

	private void parseDb() {
		registryFile.eachLine { regLine ->
			def (artifact, license) = regLine.split()
			this.artifactLicenses[artifact] = license
		}
	}
}