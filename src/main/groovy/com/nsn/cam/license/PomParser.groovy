package com.nsn.cam.license

class PomParser {
	File pomFile

	PomParser(File pomFile) {
		this.pomFile = pomFile
	}

	void parse() {
		def projects = new XmlSlurper().parse(this.pomFile)
		projects.project.each { p ->
			println "Processing ${p.artifactId}..."
		}
	}

}