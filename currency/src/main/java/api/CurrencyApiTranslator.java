package api;


import api.nbp.CurrencyDetails;
import api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyApiTranslator {
    private List<Currency> dateTable = new ArrayList<>();
    public List<Currency> dateTableSingle = new ArrayList<>();
    DataValidator dataValidator = new DataValidator();


    public List<Currency> parseApiTableToCurrencyTable(LocalDate startDate) {
        HistoryLoaderNbp historyLoaderNbp = new HistoryLoaderNbp();
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi(startDate);
        for (int iterator = 0; iterator < historyLoaderNbp.historyListNbp.size(); iterator++) {
            for (CurrencyRates currencyRates : historyLoaderNbp.historyListNbp.get(iterator)) {
                for (CurrencyDetails currencyDetails : currencyRates.getRates()) {
                    LocalDate dateExchange = dataValidator.dataParse(currencyRates.getEffectiveDate().replace("-", ""));
                    dateTable.add(new Currency(currencyDetails.getCode(), dateExchange, currencyDetails.getRate(), currencyDetails.getRate(), currencyDetails.getRate(), currencyDetails.getRate(), 0));
                }
            }
        }
        return dateTableSingle = dateTable.stream().distinct().collect(Collectors.toList());
    }
}