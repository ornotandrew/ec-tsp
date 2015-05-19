default:
	@javac *.java

run: default
	java TSP 1000 500 1
	
rungui: default
	java TSP 1000 500 1 gui

clean:
	@rm -rf *.class
