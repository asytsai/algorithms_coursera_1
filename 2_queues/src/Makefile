all: build run
	@echo "Make all"

build:
    javac -cp /home/pandabaka/workspace/Algorithms_coursera1/algs4.jar:. Deque.java
    javac -cp /home/pandabaka/workspace/Algorithms_coursera1/algs4.jar:. RandomizedQueue.java
	javac -cp /home/pandabaka/workspace/Algorithms_coursera1/algs4.jar:. Permutation.java

run:
	java -cp /home/pandabaka/workspace/Algorithms_coursera1/algs4.jar:. Permutation 3 < distinct.txt

zip:
	zip queues Deque.java RandomizedQueue.java Permutation.java


