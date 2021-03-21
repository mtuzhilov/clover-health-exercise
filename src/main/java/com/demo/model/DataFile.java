package com.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "data_file")
public class DataFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "imported_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date importedAt;

    @OneToMany(
            mappedBy = "file", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<DataColumn> columns = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getImportedAt() {
        return importedAt;
    }

    public void setImportedAt(Date importedAt) {
        this.importedAt = importedAt;
    }

    public List<DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
    }
}
