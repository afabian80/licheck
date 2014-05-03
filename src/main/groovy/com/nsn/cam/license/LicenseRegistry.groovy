package com.nsn.cam.license

class LicenseRegistry {
	File registryFile
	def artifactLicenses = [:]

	LicenseRegistry(File registryFile) {
		this.registryFile = registryFile
		parseDb()

	}

	boolean contains(String dep) {
		return this.artifactLicenses.keySet().contains(dep)
	}

	boolean containsMajor(String dep) {
		def (group, artifact, version) = dep.split(':')
		// Matching: '3', '4.11', '5.12.2', '6.1.4.3', '7.3.5-RC3'
		def match = ( version =~ /(\d+)(\..*)?/)
  		String majorVersion = match[0][1]
		return this.contains("$group:$artifact:$majorVersion")
	}

	private void parseDb() {
		//TODO enable comment and empty lines in the database
		registryFile.eachLine { regLine ->
			def (artifact, license) = regLine.split()
			this.artifactLicenses[artifact] = license
		}
	}
}