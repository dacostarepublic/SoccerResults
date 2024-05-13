# Soccer League Ranking Table

This is the coding challenge for Span Digital. 

## Overview

Create a command-line application that will calculate the ranking table for a league. The solution is written in the following languages:
- Java
- Python
- Go
- Scala

Only 1 language is needed, but for fun I wrote it in all four as a learning experience... 
I also would've split the commit up as I was working, but opted to do 1 commit due to the lack of time I had available.

## Usage

I've included a makefile so everything can be run with `$ make`.

### Go

The source files are located in `src/golang/`. 
To execute, run the following from the `src/golang/` directory:
```
$ go run soccerResults.go results.txt
```
or you can run it from the makefile in the root dir with:
```
$ make go
```

I used go version go1.22.3 darwin/arm64 for this challenge.

#### Caveats

I'm not familiar with unit testing in Go, but this is something I'd like to explore later.

### Python

The source files are located in `src/python/`. 
To execute, run the following from the `src/python/` directory:
```
$ python3 soccerResults.py results.txt
```
or you can run it from the makefile in the root dir with:
```
$ make python
```

To run the unit tests: `$ make python_test`

### Java

The source files are located in `src/java/`. 
To execute, run the following from the `src/java/` directory:
```
$ javac SoccerResults.java
$ java SoccerResults results.txt
```
or you can run it from the makefile in the root dir with:
```
$ make java
```

#### Caveats

Due to the lack of time, I couldn't set the project up in the traditional project structure with a `pom.xml` file and include some JUnit tests

### Scala

The source files are located in `src/scala/`. 
To execute, run the following from the `src/scala/` directory:
```
$ scala SoccerResults.scala results.txt
```
or you can run it from the makefile in the root dir with:
```
$ make scala
```

I used Scala code runner version 3.4.1 for this challenge.

#### Caveats

I'm not very familiar with Scala and had a little trouble making use of the command-line args 
