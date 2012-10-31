package jacoco

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class Jacoco extends DefaultTask {

  def jacocoClasspath

  @TaskAction
  def run() {
    project.logger.debug 'Starting jacoco task'
  }

}
