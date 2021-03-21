package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "data_row")
public class DataRow {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "column_id", nullable = false)
    DataColumn column;

    @Column(name = "value_txt")
    String valueTxt;

    @Column(name = "value_bool")
    Boolean valueBool;

    @Column(name = "value_int")
    Integer valueInt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DataColumn getColumn() {
        return column;
    }

    public void setColumn(DataColumn column) {
        this.column = column;
    }

    public String getValueTxt() {
        return valueTxt;
    }

    public void setValueTxt(String valueTxt) {
        this.valueTxt = valueTxt;
    }

    public Boolean getValueBool() {
        return valueBool;
    }

    public void setValueBool(Boolean valueBool) {
        this.valueBool = valueBool;
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }
}
