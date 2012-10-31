Gradle Jacoco plugin
====================

These are the beginnings of a Gradle Jacoco plugin. [Jacoco](http://www.eclemma.org/jacoco/) is a code coverage library for Java. 

The planned feature set includes but is not limited to:
- Uses the Jacoco Agent to get the coverage information
- Easy setup relying on good default configurations
- Possibility to only collect execution information if the results are analysed by some other tool (e.g. Jenkins)
- Support for multiproject builds by combining results from the subprojects into one
- Support for all configurations provided by the Jacoco Ant tasks
- Possibility to set coverage limits and fail the build if coverage is too low

Currently the plugin does not do much yet, it only adds a convention and a task, which does not do anything.




