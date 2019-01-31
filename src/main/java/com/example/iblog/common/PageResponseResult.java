package com.example.iblog.common;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer pages;
    private List<T> results;

    public PageResponseResult(List<T> results) {
        if (results instanceof Page) {
            Page<T> page = (Page<T>)results;
            this.total = page.getTotal();
            this.results = page.getResult();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
        }
    }
}
