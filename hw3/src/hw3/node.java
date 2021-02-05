package hw3;

public class node {
    String name;
    int chinese;
    int english;
    int math;
    int total;
	
    node(String name,int chinese,int english,int math){
	this.name = name;
	this.chinese = chinese;
	this.english = english;
	this.math = math;
	this.total = chinese + english+ math;
    }
	
}