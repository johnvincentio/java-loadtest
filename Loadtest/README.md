
# Usage

To execute app, use `diffall.command`

Provide two directories you wish to compare.

Cleaning options are for removing files that are not of interest. Thins out the forest so you can see the trees.

The compare options perform the actual compare. Delete if identical allows for more cleaning out of files that are not of interest.

The aim is to end up with a detailed compare log of all differences between the two directories.

# Diffall

At Github, create repository `java-diffall`

repo: https://github.com/johnvincentio/java-diffall

```
cd /Users/jv/Desktop/MyDevelopment/github/java/Utilities
create-repo java-diffall
```

Remove non-relevant files.

## Add `README.md`

```
cd java-diffall
```

Create `README.md`

```
For details, see Diffall/README.md
```

## Start Eclipse

in Finder

* select `/Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-diffall`
* Right click, Services
  * eclipse-jee

## Create Java Project

* File, New, Project
* Java Project

Settings

* Project Name: Diffall
* Use default location
* JRE; Use an execution environment JRE: JavaSE-1.8
* Create separate folders for sources and class files

* Default output folder: `Diffall/classes`

## Package

Select `src`

* Right click, New, Package
* `io.johnvincent`

Copy code to this package and change package names.

## Script file

`/Users/jv/Desktop/MyDevelopment/github/java/Utilities/java-diffall/Diffall/diffall.command`


## Create Alias

In Finder

* select `diffall.command`
* right click, Make Alias
* move alias to `/Users/jv/Desktop/MyDevelopment/github/repo_shell_scripts/mac/JVTools/`
* rename to `diffall.command`

# END
