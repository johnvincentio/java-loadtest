
# Usage

To execute app, use `loadtest.command <path-to-loadtest-file.xml>`

`loadtest.properties`; ensure these values are correct for your system.

# Loadtest

At Github, create repository `java-loadtest`

repo: https://github.com/johnvincentio/java-loadtest

```
cd /Users/jv/Desktop/MyDevelopment/github/java/Utilities
create-repo java-loadtest
```

Remove non-relevant files.

## Add `README.md`

```
cd java-loadtest
```

Create `README.md`

```
For details, see Loadtest/README.md
```

## Start Eclipse

in Finder

* select `/Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-loadtest`
* Right click, Services
  * eclipse-jee

## Create Java Project

* File, New, Project
* Java Project

Settings

* Project Name: Loadtest
* Use default location
* JRE; Use an execution environment JRE: JavaSE-1.8
* Create separate folders for sources and class files

* Default output folder: `Loadtest/classes`

## Package

Select `src`

* Right click, New, Package
* `io.johnvincent`

Copy code to this package and change package names.

## Script file

`/Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-loadtest/Loadtest/loadtest.command`

## Create Symbolic Link

```
ln -s /Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-loadtest/Loadtest/loadtest.command /Users/jv/Desktop/MyDevelopment/github/repo_shell_scripts/mac/unix-scripts/bin/java-loadtest
```
