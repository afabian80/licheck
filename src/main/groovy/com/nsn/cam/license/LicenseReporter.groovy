package com.nsn.cam.license

import groovy.xml.MarkupBuilder

class LicenseReporter {
	File reportFile
	def missingLicenses = [:]
	def okayLicenses = [:]

	LicenseReporter(File reportFile) {
		this.reportFile = reportFile
	}

	void reportMissing(dep, userProjects) {
		missingLicenses[dep] = userProjects
	}

	void reportFound(dep, userProjects) {
		okayLicenses[dep] = userProjects
	}

	String generateReport() {
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		xml.report() {
			'missing'() {
				missingLicenses.keySet().sort().each { key ->
					'dependency'(name: "$key") {
						missingLicenses[key].each { proj ->
							'project'(name: "$proj")
						}
					}
				}
			}
			'found'() {
				okayLicenses.keySet().sort().each { key ->
					'dependency'(name: "$key") {
						okayLicenses[key].each { proj ->
							'project'(name: "$proj")
						}
					}
				}
			}
		}
		return writer.toString()
	}
}