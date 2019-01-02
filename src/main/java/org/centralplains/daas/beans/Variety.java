/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.centralplains.daas.beans;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author flysLi
 * @ClassName Variety
 * @Decription TODO
 * @Date 2018/12/28 16:37
 * @Version 1.0
 */
@Entity
@Table(name = "variety")
public class Variety implements Serializable{


    private static final long serialVersionUID = -7982677541937849157L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String code;

    private Integer parentId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
