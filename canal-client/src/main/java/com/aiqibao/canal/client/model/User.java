package com.aiqibao.canal.client.model;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:49
 * Best wish!
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 测试实体类
 */
@Table(name = "t_user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;


    /**
     * 用户性别
     */

    private Integer gender;

    /**
     * 国家id
     */
    @Column(name = "country_id")
    private Integer countryId;


    /**
     * 用户出生日期
     */
    private Date birthday;


    /**
     * 用户创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
