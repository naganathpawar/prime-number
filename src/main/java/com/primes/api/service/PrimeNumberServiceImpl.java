package com.primes.api.service;

import org.springframework.stereotype.Service;

import com.primes.api.dao.PrimeDetail;
import com.primes.api.util.PrimeNumberUtil;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

	@Override
	public boolean isPrime(int n) {
		return PrimeNumberUtil.isPrimeByOdds(n);
	}

	@Override
	public boolean isPrimeSieve(int n) {
		return PrimeNumberUtil.isPrimeBySieve(n);
	}

	@Override
	public boolean isPrimeFastLoop(int n) {
		return PrimeNumberUtil.isPrimeByOddsExcludingMultiplesOfThree(n);
	}

	@Override
	public PrimeDetail getPrimes(int upperBound) {
		if (upperBound < 2) {
			return new PrimeDetail(upperBound, Collections.emptyList());
		}
		// Naive example using Java 8 Stream API and multi-threading using parallel.
		// Could have more thread control using ForkJoinPool directly
		return new PrimeDetail(upperBound, IntStream.rangeClosed(2, upperBound).parallel().filter(this::isPrimeSieve)
				.boxed().collect(Collectors.toList()));
	}
}
