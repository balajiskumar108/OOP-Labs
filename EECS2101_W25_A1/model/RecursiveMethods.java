package model;

import java.util.ArrayList;
import java.util.HashSet;

public class RecursiveMethods {

	/*
	 * Starter tests related to Task 1.
	 * 
	 * (The starter tests are not meant to cover all scenarios: you are responsible
	 * for writing additional tests to assess your software as thoroughly as
	 * possible before the submission deadline.)
	 * 
	 * Input: A string `in_string` Output: A string `out_string` Problem: Given
	 * `in_string` which contains a single pair of parentheses, compute recursively
	 * and return `out_string` made of only the pair of parentheses and their
	 * enclosed characters.
	 */
	public String task1(String in_string) {
		// Base case: If the string is empty, return an empty string.
		if (in_string.isEmpty()) {
			return "";
		}

		// Check the first character
		if (in_string.charAt(0) != '(') {
			return task1(in_string.substring(1));//Recursive step! Creates a substring
		}

		// Check the last character
		if (in_string.charAt(in_string.length() - 1) != ')') {
			return task1(in_string.substring(0, in_string.length() - 1)); // Recursively remove from the end
		}
		return in_string;
	}

	
	public boolean task2(int[] a, int target) {
		// Call helper method with the array, target, and index
		return sumHelper(a, target, 0);
	}

	private boolean sumHelper(int[] a, int target, int index) {
		// base case: if the target is achieved return true
		if (target == 0) {
			return true;
		}
		//base case 2 : if we've gone past the end of the array
		if (index >= a.length || target < 0) {
			return false;
		}
		// Recursive case: include or exclude the current element
		if (sumHelper(a, target - a[index], index + 1)) {
			return true;
		}
		return sumHelper(a, target, index + 1);
	}

	public int task3(int h, int n) {
		// Base case: if height is zero, do nothing
		if (h == 0) {
			return 1;
		}
		// base case 2: negative height is invalid
		if (h < 0) {
			return 0;
		}
		// recursive case: sum the ways for each possible step
		int ways = 0;
		for (int i = 1; i <= n; i++) {
			ways += task3(h - i, n);
		}
		
		//return ways to sum the possible steps
		return ways;
	}

	/**
	 * Task 4: Generate all climbing strategies. Inputs: h (height of the staircase)
	 * and n (max steps for each climb) Output: HashSet with each element as an
	 * ArrayList (representing a strategy).
	 */
	public HashSet<ArrayList<Integer>> task4(int h, int n) {
		
		HashSet<ArrayList<Integer>> climb = new HashSet<>();
		
		//Instantiate helper method 
		climbHelper(h, n, new ArrayList<>(), climb);
		
		return climb;
	}

	private void climbHelper(int h, int n, ArrayList<Integer> curr, HashSet<ArrayList<Integer>> climb) {
		// Base case: height is reached, add the current strategy
		if (h == 0) {
			climb.add(new ArrayList<>(curr));
			return;
		}
		// base case 2: Negative height is invalid
		if (h < 0) {
			return;
		}
		// Recursive case: try each step from 1 to n
		for (int i = 1; i <= n; i++) {
			curr.add(i);
			climbHelper(h - i, n, curr, climb);
			curr.remove(curr.size() - 1); // Backtrack
		}
	}
}
