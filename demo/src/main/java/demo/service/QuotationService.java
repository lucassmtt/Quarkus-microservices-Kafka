package demo.service;

import demo.client.CurrencyPriceClient;
import demo.dto.CurrencyPriceDTO;
import demo.dto.QuotationDTO;
import demo.entity.QuotationEntity;
import demo.message.KafkaEvents;
import demo.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.ext.DefaultClientHeadersFactoryImpl;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hibernate.service.spi.InjectService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ApplicationScoped
public class QuotationService  {


    @RestClient
    @Inject
    CurrencyPriceClient currencyPriceClient;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    public void getCurrencyPrice() {
        CurrencyPriceDTO currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");

        if (updateCurrentPrice(currencyPriceInfo)) {
            kafkaEvents.sendNewKafkaEvent(
                    QuotationDTO
                            .builder()
                            .currencyPrice(new BigDecimal(currencyPriceInfo.getUSDBRL().getBid()))
                            .date(new Date())
                            .build()
            );
        }
    }

    private boolean updateCurrentPrice(CurrencyPriceDTO currencyPriceDTO) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceDTO.getUSDBRL().getBid());
        AtomicBoolean updatePrice = new AtomicBoolean(false);

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();

        if (quotationEntities.isEmpty()) {
            saveQuotation(currencyPriceDTO);
            updatePrice.set(true);
        }
        else {
            int last = quotationEntities.size();

            if (last > 1) {
                
                QuotationEntity lastDollarPrice = quotationEntities.get(last);

                if (currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {
                    saveQuotation(currencyPriceDTO);
                    updatePrice.set(true);
                }
            } else {
                throw new RuntimeException("Error when load last dollar price");
            }

        }

        return updatePrice.get();
    }

    private void saveQuotation(CurrencyPriceDTO currencyPriceDTO) {

        QuotationEntity quotationEntity = new QuotationEntity();

        quotationEntity.setDate(new Date());
        quotationEntity.setCurrencyPrice(new BigDecimal(currencyPriceDTO.getUSDBRL().getBid()));
        quotationEntity.setPctChange(currencyPriceDTO.getUSDBRL().getPctChange());
        quotationEntity.setPair("USD-BRL");

        quotationRepository.persist(quotationEntity);
    }
}
