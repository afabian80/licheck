package com.nsn.cam.license

import com.nsn.cam.license.PomParser

class Checker {
	public static void main(String[] args) {
		PomParser parser = new PomParser(new File('effective-pom.xml'))
		parser.parse()
	}
}