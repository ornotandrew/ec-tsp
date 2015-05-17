default:
	@javac *.java

run: default
	java TSP 100 500 1
	
rungui: default
	java TSP 100 500 1 gui

clean:
	@rm -rf *.class
