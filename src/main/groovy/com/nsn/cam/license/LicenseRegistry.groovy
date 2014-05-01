package com.nsn.cam.license

class LicenseRegistry {
	File registryFile

	LicenseRegistry(File registryFile) {
		this.registryFile = registryFile
	}

	boolean contains(String dep) {
		return false
	}
}