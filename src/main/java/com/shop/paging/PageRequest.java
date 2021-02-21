package com.shop.paging;

public class PageRequest implements PageAble {
    private int pageIndex;
    private int pageSize;

    private PageRequest(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public static PageRequest of(final int pageIndex, final int pageSize){
        return new PageRequest(pageIndex, pageSize);
    }

    @Override
    public int getIndex() {
        return pageIndex;
    }

    @Override
    public int getSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        if(pageIndex == 1)
            return 0;
        return (pageIndex-1)*pageSize+1;
    }
}
