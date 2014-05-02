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

	boolean containsMajor(String dep) {
		String version = dep.split(':')[2]
		println "Looking for major version $version"
	}

	private void parseDb() {
		registryFile.eachLine { regLine ->
			def (artifact, license) = regLine.split()
			this.artifactLicenses[artifact] = license
		}
	}
}