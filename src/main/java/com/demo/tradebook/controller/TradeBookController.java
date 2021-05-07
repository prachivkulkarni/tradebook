package com.demo.tradebook.controller;

import com.demo.tradebook.dao.TradeBookRepository;
import com.demo.tradebook.dto.Trade;
import com.demo.tradebook.entity.TradeBook;
import com.demo.tradebook.exception.LowerVersionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Controller
@RequestMapping("/trade/")
public class TradeBookController {


    @Autowired
    private TradeBookRepository tradeBookRepository;

    @RequestMapping(method = RequestMethod.POST, value = "insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void insertTrade(@RequestBody Trade t) throws Exception, LowerVersionException {

        if (isValidRequest(t)) {

            TradeBook entity = tradeBookRepository.findByInputs(t.getTradeId(), t.getCounterPartyId(), t.getBookId());

            if (entity != null && t.getVersion() < entity.getVersion()) {
                throw new LowerVersionException("Lower version was send then the existing version");
            }

            if (entity == null) {
                entity = new TradeBook();
                entity.setExpired("N");
            } else {
                entity.setExpired("N");
                if (entity.getMaturityDate().compareTo(new Date()) < 0)
                    entity.setExpired("Y");
            }
            entity.setBookId(t.getBookId());
            entity.setCounterPartyId(t.getCounterPartyId());

            entity.setTradeId(t.getTradeId());
            entity.setVersion(t.getVersion());

            entity.setMaturityDate(t.getMaturityDate());
            entity.setExpired("Y");

            tradeBookRepository.save(entity);
        }
    }

    private boolean isValidRequest(Trade t) throws Exception {

        if (!isCheckRequiredFields(t))
            throw new Exception("Invalid input");

        Date today = new Date();
        if (t.getMaturityDate().compareTo(today) < 0)
            throw new Exception("Invalid maturity date sent");
        return true;
    }

    private boolean isCheckRequiredFields(Trade t) {

        if (t == null || t.getTradeId() == null || t.getCounterPartyId() == null || t.getBookId() == null
                || t.getVersion() == 0 || t.getMaturityDate() == null)
            return false;

        return true;
    }

}