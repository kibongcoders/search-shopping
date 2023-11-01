package com.kibong.searchshopping.shopping.dto;

import lombok.Data;

@Data
public class PageDto {

    private Integer startPage;
    private Integer endPage;
    private Integer realEnd;
    private Boolean prev, next;
    private Integer total;
    public PageDto() {
    }

    public PageDto(Integer total, Integer currentPage) {
        this.total = total;
        this.endPage = (int) ((Math.ceil(currentPage*0.1))*10);
        this.startPage = endPage - 9;
        this.realEnd = total > 1000 ? 100 : (int)Math.ceil((double) total /10);

        if(realEnd < endPage) {
            this.endPage = realEnd;
        }

        this.prev = currentPage > 1;
        this.next = currentPage < realEnd;
    }
}
