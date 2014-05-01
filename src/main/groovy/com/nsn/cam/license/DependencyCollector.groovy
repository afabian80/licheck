package com.nsn.cam.license

class DependencyCollector {
	File pomFile
	def dependencyMap = [:]	// TODO: rename to dependencies

	DependencyCollector(File pomFile) {
		this.pomFile = pomFile
	}

	void collect() {
		def projects = new XmlSlurper().parse(this.pomFile)
		projects.project.each { project ->
			String projectStr = "${project.groupId}:${project.artifactId}"
			project.dependencyManagement.dependencies.dependency.each { dep ->
				collectDep(projectStr, dep)
			}
			project.dependencies.dependency.each { dep ->
				collectDep(projectStr, dep)
			}
		}
		println "Map contains ${dependencyMap.size()} keys."
		dependencyMap.sort().each { key, value ->
			println "$key (in ${value.size()} modules)"
			for (item in value.sort()) {
				println "\t${item}"
			}
		}
	}

	void collectDep(projectStr, dep) {
		String depStr = "${dep.groupId}:${dep.artifactId}:${dep.version}"
		if (!(this.dependencyMap.keySet().contains(depStr))) {
			this.dependencyMap[depStr] = []
		}
		this.dependencyMap[depStr].add(projectStr)
	}

}