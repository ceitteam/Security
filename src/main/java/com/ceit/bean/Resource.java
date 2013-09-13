package com.ceit.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-7
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
/*对应资源*/
@Entity
@Table(name="resource_tb")
public class Resource {
    @Id
    private String resource_id;//资源编码
    private String resource_name;//资源名称
    private String resource_url;//资源的url
    private String resource_description;//资源描述

}
