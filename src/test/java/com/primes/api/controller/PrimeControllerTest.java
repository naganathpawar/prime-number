package com.primes.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.primes.api.util.PrimeNumberUtilTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(PrimeController.class)
@WebIntegrationTest
public class PrimeControllerTest {

    private RestTemplate restTemplate = new TestRestTemplate();

    // Make sure the service is not currently running when executing these tests
    private String baseUrl = "http://localhost:8082";

   
    @Test
    public void testPrimeGeneration() {

        // Test default upper bound
        ResponseEntity<int[]> entity = restTemplate.getForEntity(baseUrl + "/primes/getPrimes", int[].class);
        int[] expected = {2, 3, 5, 7, 11, 13, 17, 19};
        checkBody(entity, expected);

        // Test negative upper bound
        entity = restTemplate.getForEntity(baseUrl + "/primes/getPrimes/-7919", int[].class);
        expected = new int[]{};
        checkBody(entity, expected);

        // Test PrimeNumberUtilTest.KNOWN_PRIMES
        entity = restTemplate.getForEntity(baseUrl + "/primes/getPrimes/7919", int[].class);
        expected = PrimeNumberUtilTest.KNOWN_PRIMES;
        checkBody(entity, expected);
    }

    
    @Test
    public void testPrimeNumber() {

        // Prime number + sieve
        ResponseEntity<Void> entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 17, 1);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        // Prime number + fast loop
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 17, 2);
        assertEquals(HttpStatus.OK, entity.getStatusCode());

        // Prime number + slow loop
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 17, 3);
        assertEquals(HttpStatus.OK, entity.getStatusCode());


        // Composite number + sieve loop
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 125, 1);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

        // Composite number + fast loop
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 125, 2);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

        // Composite number + slow loop
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}?algorithm={1}", Void.class, 125, 3);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

        // Example without supplying algorithm
        entity = restTemplate.getForEntity(baseUrl + "/primes/{0}", Void.class, 125);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

    private void checkBody(ResponseEntity<int[]> entity, int[] expected) {
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        final int[] actual = entity.getBody();
        assertEquals(expected.length, actual.length);
        assertArrayEquals(expected, actual);
    }
}
