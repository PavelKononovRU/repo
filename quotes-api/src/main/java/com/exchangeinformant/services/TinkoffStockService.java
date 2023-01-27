package com.exchangeinformant.services;

import com.exchangeinformant.dto.NameDTO;
import com.exchangeinformant.dto.StockDTO;
import com.exchangeinformant.model.Info;
import com.exchangeinformant.model.Stock;
import com.exchangeinformant.repository.InfoRepository;
import com.exchangeinformant.repository.StockRepository;
import com.exchangeinformant.util.Tinkoff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Tinkoff
public class TinkoffStockService implements StockService {
    private final InfoRepository infoRepository;

    private final StockRepository stockRepository;
    private final OpenApi openApi;

    @Override
    public void updateAllStocks() {
        List<Stock> companies = stockRepository.findAll();
        for (Stock stock : companies) {
            Info updatedStock = getStockByTicker(stock.getSecureCode());
            infoRepository.save(updatedStock);
        }
        System.out.printf("%s: Updated Successfully%n", LocalDateTime.now());
    }

    @Override
    public List<String> getAllStocks() {
        return null;
    }


    private Info getStockByTicker(String ticker) {

        MarketContext context = openApi.getMarketContext();
        var list = context.searchMarketInstrumentsByTicker(ticker);
        List<MarketInstrument> miList =list.join().getInstruments();
        if (miList.isEmpty()) {
            throw new RuntimeException("Stock not found");
        }
        MarketInstrument item = miList.get(0);

        var lastPrice= context.getMarketOrderbook(item.getFigi(),0).join().get().getLastPrice();

        return new Info(
                lastPrice.doubleValue(),
                LocalDateTime.now(),
                item.getTicker()
        );
    }

}
