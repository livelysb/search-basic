package com.example.searchbasic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`search_keyword`")
public class SearchKeyword {
    @Id
    private String keyword;

    private Long searchCnt;

    @Version
    private long version;

    public SearchKeyword(String keyword, Long searchCnt) {
        this.keyword = keyword;
        this.searchCnt = searchCnt;
    }

    public void increaseSearchCnt() {
        searchCnt += 1;
    }
}
