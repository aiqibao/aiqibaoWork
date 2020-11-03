package com.aiqibaowork.entity;

import lombok.Data;

import java.util.Date;
@Data
public class PassengerWallet {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.passenger_info_id
     *
     * @mbg.generated
     */
    private Integer passengerInfoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.capital
     *
     * @mbg.generated
     */
    private Double capital;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.give_fee
     *
     * @mbg.generated
     */
    private Double giveFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.freeze_capital
     *
     * @mbg.generated
     */
    private Double freezeCapital;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.freeze_give_fee
     *
     * @mbg.generated
     */
    private Double freezeGiveFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_passenger_wallet.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.id
     *
     * @return the value of tbl_passenger_wallet.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.passenger_info_id
     *
     * @return the value of tbl_passenger_wallet.passenger_info_id
     *
     * @mbg.generated
     */
    public Integer getPassengerInfoId() {
        return passengerInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.capital
     *
     * @return the value of tbl_passenger_wallet.capital
     *
     * @mbg.generated
     */
    public Double getCapital() {
        return capital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.give_fee
     *
     * @return the value of tbl_passenger_wallet.give_fee
     *
     * @mbg.generated
     */
    public Double getGiveFee() {
        return giveFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.freeze_capital
     *
     * @return the value of tbl_passenger_wallet.freeze_capital
     *
     * @mbg.generated
     */
    public Double getFreezeCapital() {
        return freezeCapital;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.freeze_give_fee
     *
     * @return the value of tbl_passenger_wallet.freeze_give_fee
     *
     * @mbg.generated
     */
    public Double getFreezeGiveFee() {
        return freezeGiveFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.create_time
     *
     * @return the value of tbl_passenger_wallet.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_passenger_wallet.update_time
     *
     * @return the value of tbl_passenger_wallet.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}