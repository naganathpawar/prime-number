package com.primes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primes.api.dao.PrimeDetail;
import com.primes.api.service.PrimeNumberService;

@RestController
@RequestMapping("/primes")
public class PrimeController {

	@Autowired
	private PrimeNumberService primeNumberService;

	@RequestMapping(value = "/getPrimes/{inputNumber}",method = RequestMethod.GET)
	public PrimeDetail primes(@PathVariable("inputNumber") int inputNumber) {
		return primeNumberService.getPrimes(inputNumber);
	}

	@RequestMapping(value = "/{number}",method = RequestMethod.GET)
	ResponseEntity<?> prime(@PathVariable int number,
			@RequestParam(value = "algorithm", required = false, defaultValue = "1") int algorithm) {

		boolean isPrime;
		switch (algorithm) {
		case 2:
			isPrime = primeNumberService.isPrimeFastLoop(number);
			break;
		case 3:
			isPrime = primeNumberService.isPrime(number);
			break;
		default:
			isPrime = primeNumberService.isPrimeSieve(number);
		}

		if (isPrime) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
