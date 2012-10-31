package jacoco

import spock.lang.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder


class JacocoPluginSpecification extends Specification {

  private static final String PLUGIN_NAME = 'jacoco'

  Project project

  def setup() {
    project = ProjectBuilder.builder().build()
    applyPlugin()
  }

  def "configuration and dependencies are added to project"() {
    expect:
    resolveConfiguration()
    def configuration = project.configurations.jacoco
    configuration != null
    configuration.dependencies.each {
      assert it.group == 'org.jacoco'
      assert (it.name == 'org.jacoco.ant' || it.name == 'org.jacoco.agent')
      assert it.version == JacocoPlugin.JACOCO_VERSION
    }
  }

  def "task is added"() {
    expect:
    project.tasks['jacoco'] != null
  }

  def "configuration dependencies are not overridden, if they already exist"() {
    setup:
    project.dependencies { jacoco 'dummy:dependency:1.0' }
    resolveConfiguration()

    expect:
    def dependencies = project.configurations.jacoco.dependencies
    dependencies.size() == 1
    dependencies.each {
      assert it.group == 'dummy'
      assert it.name == 'dependency'
      assert it.version == '1.0'
    }
  }

  def "jacoco version can be changed"() {
    setup:
    project.jacoco {
      jacocoVersion '1.0'
    }
    resolveConfiguration()

    expect:
    project.configurations.jacoco.dependencies.each {
      assert it.version == '1.0'
    }
  }

  def applyPlugin() {
    project.apply plugin: PLUGIN_NAME
  }

  def resolveConfiguration() {
    project.tasks[PLUGIN_NAME].jacocoClasspath
  }

}
