package com.nsn.cam.license

class DependencyCollector {
	File pomFile
	def dependencies = [:]

	DependencyCollector(File pomFile) {
		this.pomFile = pomFile
	}

	void collectDependencies() {
		def projects = new XmlSlurper().parse(this.pomFile)
		projects.project.each { project ->
			String projectStr = "${project.groupId}:${project.artifactId}"
			project.dependencyManagement.dependencies.dependency.each { dep ->
				addDep(projectStr, dep)
			}
			project.dependencies.dependency.each { dep ->
				addDep(projectStr, dep)
			}
		}
		/*
		println "Map contains ${this.dependencies.size()} keys."
		this.dependencies.sort().each { key, value ->
			println "$key (in ${value.size()} modules)"
			for (item in value.sort()) {
				println "\t${item}"
			}
		}
		*/
	}

	void addDep(projectStr, dep) {
		String depStr = "${dep.groupId}:${dep.artifactId}:${dep.version}"
		if (!(this.dependencies.keySet().contains(depStr))) {
			this.dependencies[depStr] = []
		}
		this.dependencies[depStr].add(projectStr)
	}

}