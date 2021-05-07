package com.demo.tradebook.dao;

import com.demo.tradebook.entity.TradeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TradeBookRepository extends JpaRepository<TradeBook, Serializable> {

    @Query("select t from TradeBook t where t.tradeId =?1 and t.counterPartyId =?2 and t.bookId=?3")
    TradeBook findByInputs(String tradeId, String counterPartyId, String bookId);

}
