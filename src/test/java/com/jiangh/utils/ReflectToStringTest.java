package com.jiangh.utils;

public class ReflectToStringTest {

	private String name ="jiangh";
	private int age = 29;
	private Dog dog= new Dog();
	
	public static void main(String[] args) {
		String string = new ReflectToString().toString(new ReflectToStringTest());
		System.out.println(string);
	}
	
	private static class Dog{
		private String color ="yellow";
		private int age = 5;
	}
}
