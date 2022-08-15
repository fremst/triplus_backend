package com.triplus.reservation.dto;

import com.triplus.reservation.utils.HashUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.lang.System.currentTimeMillis;

@ToString
@Getter
@Setter
public class PcPayRequestData {

    private String gopaymethod;
    private String mid;
    private String oid;

    private int price;

    private long timestamp;
    private String signature;
    private String mKey;

    private String goodname;
    private String buyername;
    private String buyertel;
    private String buyeremail;

    public PcPayRequestData(String gopaymethod, int price, String goodname, String buyername, String buyertel, String buyeremail) {
        this.gopaymethod = gopaymethod;
        this.mid = "INIpayTest";
        this.price = price;
        this.timestamp = currentTimeMillis();
        this.oid = this.mid + "_" + this.timestamp;

        this.signature = HashUtils.getSignature(this.oid, this.price, this.timestamp);
        this.mKey = HashUtils.getMkey(mid);
        this.goodname = goodname;
        this.buyername = buyername;
        this.buyertel = buyertel;
        this.buyeremail = buyeremail;
    }
}
