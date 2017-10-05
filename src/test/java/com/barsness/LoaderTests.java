package com.barsness;

import com.barsness.loader.domain.TransactionLoadResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by matt.barsness on 10/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoaderTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testTransactionLoader(){
        ResponseEntity<TransactionLoadResponse> forEntity = restTemplate.getForEntity("/load/transactions?fileName={fileName}", TransactionLoadResponse.class, "test.csv");
        TransactionLoadResponse response = forEntity.getBody();
        Assert.assertEquals(0, response.getTransLoaded());

    }

}
