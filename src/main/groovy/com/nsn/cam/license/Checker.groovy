package com.nsn.cam.license

import com.nsn.cam.license.DependencyCollector

class Checker {
	public static void main(String[] args) {
		DependencyCollector dc = new DependencyCollector(new File('effective-pom.xml'))
		dc.collect()
	}
}