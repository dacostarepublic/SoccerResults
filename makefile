.PHONY: all

all: python java go scala

python:
	python3 src/python/soccerResults.py results.txt

python_test:
	cd src/python && python -m unittest soccerResults_tests.py

java: 
	javac -d . src/java/SoccerResults.java
	java SoccerResults results.txt

go:
	go run src/golang/soccerResults.go results.txt

scala:
	scala src/scala/SoccerResults.scala results.txt