# curiosone-core
This repo contains part of the source code of the [Curiosone](https://github.com/curiosone-bot/curiosone) chatbot project.

[![travis-ci](https://travis-ci.org/curiosone-bot/curiosone-core.svg?branch=master)](https://travis-ci.org/curiosone-bot/curiosone-core)
[![codecov](https://codecov.io/gh/curiosone-bot/curiosone-core/branch/master/graph/badge.svg)](https://codecov.io/gh/curiosone-bot/curiosone-core)
![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)


## 📕 Table of contents
- [curiosone-core](#curiosone-core)
- [💻 Get Started](##💻-Get-Started)
- [👾 Dependencies](#👾-Dependencies)
- [🍹 Gradle](#---gradle)
- [👔 Code Style](#---code-style)
    + [__Eclipse__:](#--eclipse---)
    + [__IntelliJ__:](#--intellij---)
- [⏳ Basic Workflow](#--basic-workflow)


## 💻 Get Started
Clone the repository to your local machine then cd into
the directory that was created by the cloning.

```
git clone https://github.com/curiosone-bot/curiosone-core.git
cd curiosone-core
```


# 👾 Dependencies
If you don't have them already, you need to install some prerequisites:

* [Git](http://git-scm.com/downloads)
* [Java 8 JDK](http://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)
* [Gradle](https://gradle.org/install)
* [Google Styleguide Settings](https://github.com/HPI-Information-Systems/Metanome/wiki/Installing-the-google-styleguide-settings-in-intellij-and-eclipse) (only for IDE)
* Checkstyle Plugin for [Eclipse](http://eclipse-cs.sourceforge.net/) - [IntelliJ](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) (only for IDE)
* [GitHub Desktop](https://desktop.github.com/) (optional)


# 🍹 Gradle
The project comes with some useful tools that will help you automatize some common tasks:

* `gradle clean` to clean up the files generated by the build process
* `gradle build` to build and compile the entire project
* `gradle test` to run unit tests
* `gradle javadoc` to generate the javadoc

# 👔 Code Style
This project embraces [Google Java Code Style](https://google.github.io/styleguide/javaguide.html).
Make sure to activate the Checkstyle plugin correctly:

### __Eclipse__:
  * Import the cloned repository as Gradle project
  * Right click on the imported Gradle project
  * Click on "Checkstyle", then click on "Activate Checkstyle"

Now, every time you save your work, Eclipse will show a warn where there are code style violations.
Click the warn icon to identify the violation and manually fix it.

### __IntelliJ__:
  * Go to `Settings -> Editor -> Code Style`
  * Set the option `Scheme` to `GoogleStyle`

From now on, `Code -> Reformat Code` to automatically fix code style errors.

__Always double check__ if your code is free from code style errors, using `gradle check`.

# ⏳ Basic Workflow
To start developing you can follow the simple workflow described below.

1. Develop a new feature with unit tests
2. Run `gradle check`
3. If checks fail, fix the code and go back to step 2
4. If you need to add more features, go back to step 1
5. Run `gradle clean build`
6. Open a new [Pull Request](https://github.com/curiosone-bot/curiosone-core/compare)
7. Let others review your code
8. Marge the pull request into the master branch

At the end of this workflow you should have a unit tested version of project that is ready for use.
