package com.bmuschko;

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class MyPluginFunctionalTest extends Specification {

    @Rule
    TemporaryFolder temporaryFolder = new TemporaryFolder()
    File buildFile

    def setup() {
        buildFile = temporaryFolder.newFile('build.gradle')
        buildFile << """
            plugins {
                id 'com.bmuschko.myplugin'
            }
        """
    }
    
    def "can apply plugin"() {
        when:
        def result = GradleRunner.create().withProjectDir(temporaryFolder.root).withArguments('tasks').withPluginClasspath().build()

        then:
        result.output.contains('Test')
    }
}