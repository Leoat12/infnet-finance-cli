package br.edu.infnet.financecli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {

    private String currency;
    private String shortName;
    private Double regularMarketPrice;
    private Double regularMarketPreviousClose;
    private String symbol;
    private Double regularMarketChange;
    private Double regularMarketChangePercent;

}
