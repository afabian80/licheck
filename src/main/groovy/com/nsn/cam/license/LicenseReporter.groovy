package com.nsn.cam.license

import groovy.xml.MarkupBuilder

class LicenseReporter {
	File reportFile
	def missingLicenses = [:]
	def foundLicenses = [:]
	//TODO: add logic to exclude com.nsn.* artifacts from reports

	LicenseReporter(File reportFile) {
		this.reportFile = reportFile
	}

	void reportMissing(dep, userProjects) {
		missingLicenses[dep] = userProjects
	}

	void reportFound(dep, userProjects) {
		foundLicenses[dep] = userProjects
	}

	// NOSONAR
	String generateReport(boolean verbose) {
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)
		xml.report {
			'missing' {
				missingLicenses.keySet().sort().each { key ->
					'dependency'(name: "$key") {
						if (verbose) {
							missingLicenses[key].each { proj -> // NOSONAR
								'project'(name: "$proj")		// NOSONAR
							}
						}
					}
				}
			}
			'found' {
				foundLicenses.keySet().sort().each { key ->
					'dependency'(name: "$key") {
						if (verbose) {
							foundLicenses[key].each { proj ->	// NOSONAR
								'project'(name: "$proj")		// NOSONAR
							}
						}
					}
				}
			}
		}
		return writer.toString()
	}

	void saveReport() {
		reportFile.withWriter { out ->
			out.writeLine(this.generateReport(false))
		}
	}
}