package com.strumski.foaas.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;


import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoaasServiceTest {

    @Autowired
    private FoaasService foaasService;

    @Test
    public void testRetrievingOperations() {
        StepVerifier.create(foaasService.retrieveAllowedOps())
                .assertNext((l) -> {
                    System.out.println(l);
                    assertFalse(l.isEmpty());
                })
                .verifyComplete();
    }

    @Test
    public void retrieveRandomJoke() {
        StepVerifier.create(foaasService.retrieveRandomJoke())
                .assertNext(System.out::println)
                .verifyComplete();

    }
}