package com.demo.tradebook.controller;

import com.demo.tradebook.dto.Trade;
import com.demo.tradebook.exception.LowerVersionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TradeBookControllerTest {


    TradeBookController controller = new TradeBookController();

    @Test(expected = Exception.class)
    public void insertTrade_input_isEmpty() throws Exception {

        Trade t = null;
        controller.insertTrade(t);
    }

    @Test(expected = Exception.class)
    public void insertTrade_input_isInvalidFields() throws Exception {

        Trade t = new Trade();
        t.setBookId("B2");
        controller.insertTrade(t);
    }

    @Test(expected = LowerVersionException.class)
    public void insertTrade_input_isLowerVersion() throws LowerVersionException, Exception {

        Date maturityDate = new Date();
        maturityDate.setTime(maturityDate.getTime() + 1200);
        Trade t = new Trade("T1", 2, "CP-1", "B1", maturityDate);
        controller.insertTrade(t);

        Trade t1 = new Trade("T1", 1, "CP-1", "B1", maturityDate);
        controller.insertTrade(t1);
    }

    @Test(expected = Exception.class)
    public void insertTrade_isMaturityDate_less_than_Current_date() throws Exception {

        Date maturityDate = new Date();
        maturityDate.setTime(maturityDate.getTime() - 1200);
        Trade t = new Trade("T1", 2, "CP-1", "B1", maturityDate);
        controller.insertTrade(t);
    }

    @Test
    public void insertTrade_good() throws Exception {

        Date maturityDate = new Date();
        maturityDate.setTime(maturityDate.getTime() + 1200);
        Trade t = new Trade("T1", 2, "CP-1", "B1", maturityDate);
        controller.insertTrade(t);
    }

}