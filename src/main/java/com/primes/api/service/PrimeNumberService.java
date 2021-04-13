package com.primes.api.service;
import com.primes.api.dao.PrimeDetail;

public interface PrimeNumberService {

	boolean isPrime(int n);

	boolean isPrimeSieve(int n);

	boolean isPrimeFastLoop(int n);

	PrimeDetail getPrimes(int upperBound);
}
