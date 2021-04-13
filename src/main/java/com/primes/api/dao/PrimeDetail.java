package com.primes.api.dao;

import java.util.List;

public class PrimeDetail {

	private int initial;
	List<Integer> primes;
	public PrimeDetail(int initial, List<Integer> primes) {
		this.initial = initial;
		this.primes = primes;
	}
	public long getInitial() {
		return initial;
	}
	public void setInitial(int initial) {
		this.initial = initial;
	}
	public List<Integer> getPrimes() {
		return primes;
	}
	public void setPrimes(List<Integer> primes) {
		this.primes = primes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (initial ^ (initial >>> 32));
		result = prime * result + ((primes == null) ? 0 : primes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrimeDetail other = (PrimeDetail) obj;
		if (initial != other.initial)
			return false;
		if (primes == null) {
			if (other.primes != null)
				return false;
		} else if (!primes.equals(other.primes))
			return false;
		return true;
	}
	
}
