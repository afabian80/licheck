package com.nsn.cam.license

import groovy.util.GroovyTestCase

class LicenseRegistryTest extends GroovyTestCase {

	private LicenseRegistry registry

	void setUp() {
		URL url = this.class.getResource("/com/nsn/cam/license/sample_license_registry.database")
		File testRegistryFile = new File(url.getFile())
		registry = new LicenseRegistry(testRegistryFile)
	}

	void testContains() {
		assert registry.contains('junit:junit:4') 
	}

	void testContainsMajor() {
		assert registry.containsMajor('junit:junit:4.11') 
	}
}