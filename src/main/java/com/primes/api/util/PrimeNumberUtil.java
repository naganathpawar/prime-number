package com.primes.api.util;

import java.util.Arrays;

public class PrimeNumberUtil {

	static final boolean[] sieve = new boolean[200];

	static {
		Arrays.fill(sieve, 2, sieve.length, true);

		for (int i = 2; i * i < sieve.length; i++) {
			if (sieve[i]) {
				for (int j = i * i, m = 1; j < sieve.length; j = ((i * i) + i * m++)) {
					sieve[j] = false;
				}
			}
		}
	}

	public static boolean isPrimeByOdds(int n) {
		if (n <= 1) {
			// numbers less than 2 are not considered prime
			return false;
		}

		if (n <= 3) {
			// 2 and 3 are prime numbers
			return true;
		}

		if (!isOdd(n) || n % 3 == 0) {
			// even numbers ( > 2 ) and numbers that are multiples of 3 are non-prime
			return false;
		}

		// The number is odd. Is it prime?
		// Loop through all odd numbers determining if N is divisible by the odd number
		for (int i = 5; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPrimeByOddsExcludingMultiplesOfThree(int n) {

		if (n <= 1) {
			// numbers less than 2 are not considered prime
			return false;
		}

		if (n <= 3) {
			// 2 and 3 are prime numbers
			return true;
		}

		if (!isOdd(n) || n % 3 == 0) {
			// even numbers ( > 2 ) and numbers that are multiples of 3 are non-prime
			return false;
		}

		// The number is odd. Is it prime?
		// Loop through all odd numbers (excluding those divisible by 3) determining if
		// N is divisible by the odd number
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPrimeBySieve(int n) {

		if (n <= 1) {
			return false;
		}

		if (n < sieve.length) {
			// n has been pre-calculated in the sieve
			return sieve[n];
		}

		if (!isOdd(n) || n % 3 == 0) {
			// even numbers ( > 2 ) and numbers that are multiples of 3 are non-prime
			return false;
		}

		// The number is odd. Is it prime?
		// Loop through all odd numbers (excluding those divisible by 3) determining if
		// N is divisible by the odd number
		for (int i = 5; i * i <= n; i += 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}
		return true;
	}

	static boolean isOdd(int n) {
		return (n & 1) == 1;
	}
}
