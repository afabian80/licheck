package com.nsn.cam.license

class PomParser {
	File pomFile

	PomParser(File pomFile) {
		this.pomFile = pomFile
	}

	void parse() {
		println 'parsed'
	}

}