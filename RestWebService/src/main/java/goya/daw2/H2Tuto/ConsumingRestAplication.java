package goya.daw2.H2Tuto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ConsumingRestAplication {

  private static final Logger log = LoggerFactory.getLogger(ConsumingRestAplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ConsumingRestAplication.class, args);
  }

  @Bean
  @Profile("!test")
  public ApplicationRunner run(RestClient.Builder builder) {
    RestClient restClient = builder.baseUrl("http://official-joke-api.appspot.com").build();
    return args -> {
      Quote quote = restClient
          .get().uri("/random_joke")
          .retrieve()
          .body(Quote.class);
      log.info(quote.toString());
    };
  }
}