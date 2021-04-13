package com.primes.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static com.primes.api.util.PrimeNumberUtilTest.KNOWN_PRIMES;
import com.primes.api.controller.PrimeController;
import com.primes.api.dao.PrimeDetail;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PrimeController.class)
public class PrimeNumberServiceImplTest {

    @Autowired
    private PrimeNumberService service;

    @Test
    public void testPrimeGenerator() {

        PrimeDetail primes = service.getPrimes(7919);
        assertEquals("'primes' and 'KNOWN_PRIMES' should be equal in size", KNOWN_PRIMES.length, primes.getPrimes().size());

        for (int i = 0; i < KNOWN_PRIMES.length; i++) {
            assertEquals("'primes.get(i)' should equal 'KNOWN_PRIMES[i]'", KNOWN_PRIMES[i], primes.getPrimes().get(i).intValue());
        }
    }
}
