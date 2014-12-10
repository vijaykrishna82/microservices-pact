package io.pivotal.microservices.pact.consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ConsumerPort {

    private String url;
    private RestTemplate restTemplate;

    public ConsumerPort(String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    public List<Foo> foos() {
        ParameterizedTypeReference<List<Foo>> responseType = new ParameterizedTypeReference<List<Foo>>() {};
        return restTemplate.exchange(url + "/foos", HttpMethod.GET, null, responseType).getBody();
    }
}
