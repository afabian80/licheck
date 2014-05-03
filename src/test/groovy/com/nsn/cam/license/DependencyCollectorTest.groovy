package com.nsn.cam.license

class DependencyCollectorTest extends GroovyTestCase {
	void testCollectDependencies() {
		URL url = this.class.getResource('/com/nsn/cam/license/test-pom.xml')
		File testPomFile = new File(url.getFile())
		DependencyCollector dc = new DependencyCollector(testPomFile)
		dc.collectDependencies()
		assert dc.dependencies.size() == 10 
	}
}