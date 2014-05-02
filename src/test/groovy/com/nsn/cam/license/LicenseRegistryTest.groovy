package com.nsn.cam.license

import groovy.util.GroovyTestCase

class LicenseRegistryTest extends GroovyTestCase {

	void testContains() {
		URL url = this.class.getResource("/com/nsn/cam/license/sample_license_registry.database")
		File testRegistryFile = new File(url.getFile())
		LicenseRegistry registry = new LicenseRegistry(testRegistryFile)
		assert registry.contains('junit:junit:4') 
	}

	void testContainsMajor() {
		URL url = this.class.getResource("/com/nsn/cam/license/sample_license_registry.database")
		File testRegistryFile = new File(url.getFile())
		LicenseRegistry registry = new LicenseRegistry(testRegistryFile)
		assert registry.containsMajor('junit:junit:4.11') 
	}
}