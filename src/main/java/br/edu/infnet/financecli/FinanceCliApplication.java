package br.edu.infnet.financecli;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Scanner;

@Slf4j
@SpringBootApplication
public class FinanceCliApplication implements CommandLineRunner {

    private static final String BASE_URL = "https://query1.finance.yahoo.com/v7/finance";

    public static void main(String[] args) {
        SpringApplication.run(FinanceCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Informações de Ações");
        System.out.println("Insira os símbolos das ações, separe com vírgula: ");

        String symbol = scanner.next();

        RestTemplate restTemplate = new RestTemplate();

        YahooFinanceResponse response = restTemplate.getForObject(BASE_URL + "/quote?symbols={symbol}", YahooFinanceResponse.class, symbol);

        if (Objects.nonNull(response) && Objects.nonNull(response.getQuoteResponse()) && !response.getQuoteResponse().getResult().isEmpty()) {
            for (StockQuote quote : response.getQuoteResponse().getResult()) {
                String output = String.format("Nome: %s - Valor de Mercado: %.2f - Último fechamento: %.2f - Variação: %.2f%%",
                        quote.getShortName(), quote.getRegularMarketPrice(), quote.getRegularMarketPreviousClose(), quote.getRegularMarketChangePercent());
                System.out.println(output);
            }
        } else {
            System.out.println("Nenhuma ação encontrada com os símbolos especificados.");
        }

    }
}
