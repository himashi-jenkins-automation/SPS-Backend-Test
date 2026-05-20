package com.example.SPSProjectBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SPPEGINFORMATION")
@IdClass(SppegInformationId.class)

public class SppegInformation {

    @Id
    @Column(name = "ID", length = 25, nullable = false)
    private String id;

    @Id
    @Column(name = "PARENT_ID", length = 25, nullable = false)
    private String parentId;

    @Id
    @Column(name = "DEPT_ID", length = 6, nullable = false)
    private String deptId;

    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "DESCRIPTION", length = 150)
    private String description;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}
