package com.bjpowernode.p2p.vo;

// 分页使用的类
public class Page {
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalRows;
    private Integer totalPage;

    public Page() {
    }

    public Page(Integer pageNo,
                Integer pageSize,
                Integer totalRows,
                Integer totalPage) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        this.totalPage = totalPage;
    }

    public Page(Integer pageNo,
                Integer pageSize,
                Integer totalRows) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPage() {
        try{
            if ( totalRows % pageSize == 0){
                totalPage = totalRows / pageSize;
            } else {
                totalPage = totalRows / pageSize + 1;
            }
        }catch (Exception e) {
            e.printStackTrace();
            totalPage = 0;
        }

        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
