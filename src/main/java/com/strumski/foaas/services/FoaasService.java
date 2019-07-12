package com.strumski.foaas.services;

import com.strumski.foaas.entities.FoaasResponse;
import com.strumski.foaas.entities.Operation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
public class FoaasService {
    private final WebClient webClient = WebClient.create("https://foaas.com");

    public Mono<List<Operation>> retrieveAllowedOps() {
        final String path = "/operations";
        return webClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Operation>>() {});
    }

    public Mono<FoaasResponse> retrieveRandomJoke() {
        return retrieveAllowedOps().
                flatMap(list -> this.getJoke(list.get(new Random().nextInt(list.size())).getUrl()));
    }


     private Mono<FoaasResponse> getJoke(String url) {
         List<String> collect = Collections.list(new StringTokenizer(url, "/")).stream()
                 .map(token -> (String) token)
                 .limit(1)
                 .collect(Collectors.toList());

         if (collect.isEmpty()) {
             return Mono.empty();
         }

         return webClient.get()
                .uri(collect.get(0))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FoaasResponse.class);
    }
}
