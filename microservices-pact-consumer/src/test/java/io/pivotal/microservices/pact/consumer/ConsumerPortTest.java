package io.pivotal.microservices.pact.consumer;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.model.PactFragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ConsumerPortTest extends ConsumerPactTest {
    @Override
    protected PactFragment createFragment(ConsumerPactBuilder.PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request for Foos")
                .path("/foos")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"value\":42}, {\"value\":100}]").toFragment();
    }

    @Override
    protected String providerName() {
        return "Foo_Provider";
    }

    @Override
    protected String consumerName() {
        return "Foo_Consumer";
    }

    @Override
    protected void runTest(String url) {
        assertEquals(new ConsumerPort(url).foos(), Arrays.asList(new Foo(42), new Foo(100)));
    }
}
