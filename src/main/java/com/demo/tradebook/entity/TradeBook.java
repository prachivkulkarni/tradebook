package com.demo.tradebook.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trade_book")
public class TradeBook {

    private Integer id;
    private String tradeId;
    private Integer version;
    private String counterPartyId;
    private String bookId;
    private Date maturityDate;
    private Date createdDate;

    private String isExpired;

    public TradeBook() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "trade_id")
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "counter_party_id")
    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    @Column(name = "book_id")
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Column(name = "maturity_date")
    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "is_expired")
    public String getExpired() {
        return isExpired;
    }

    public void setExpired(String expired) {
        isExpired = expired;
    }
}
