package com.ceit.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-7
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
/*对应操作*/
@Entity
@Table(name="operate_tb")
public class Operate implements Serializable {
    @Id
    private String operate_name;
}
