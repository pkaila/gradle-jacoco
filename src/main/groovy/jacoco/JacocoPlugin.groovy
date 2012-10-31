package jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project

class JacocoPlugin implements Plugin<Project> {
  
  public static final String JACOCO_VERSION = '0.6.0.201210061924'
  private static final String TASK_NAME = 'jacoco'
  private static final String EXTENSION_NAME = TASK_NAME
  private static final String CONFIGURATION_NAME = TASK_NAME

  private Project project
  private JacocoPluginExtension extension

  void apply(Project project) {
    this.project = project
    this.extension = setupExtension()

    addConfiguration()
    addTask()
  }
  
  private JacocoPluginExtension setupExtension() {
    JacocoPluginExtension extension = project.extensions.create(EXTENSION_NAME, JacocoPluginExtension)
    extension.with {
      jacocoVersion = JACOCO_VERSION
    }
    extension
  }

  private void addConfiguration() {
    project.configurations.add(CONFIGURATION_NAME).with {
      visible = false
      description = 'The jacoco libraries required for recording execution data and creating reports.'
    }
  }

  private void addTask() {
    def task = project.tasks.add(TASK_NAME, Jacoco)
    task.conventionMapping.with {
      jacocoClasspath = {
        def configuration = project.configurations[CONFIGURATION_NAME]
        if (configuration.dependencies.empty) {
          project.dependencies {
            jacoco "org.jacoco:org.jacoco.agent:$extension.jacocoVersion",
                   "org.jacoco:org.jacoco.ant:$extension.jacocoVersion"
          }
        }
        configuration
      }
    }
  }

}
