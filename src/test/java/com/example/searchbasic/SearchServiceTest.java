package com.example.searchbasic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
    @InjectMocks
    private SearchService searchService;

    @Mock
    private SearchKeywordRepository searchKeywordRepository;

    @Test
    @DisplayName("없는 검색 키워드가 검색되는 케이스")
    void not_exist_keyword() {
        String keyword = "없는 검색어";

        //given
        given(searchKeywordRepository.findById(keyword)).willReturn(Optional.empty());
        given(searchKeywordRepository.save(any(SearchKeyword.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto res = searchService.save(keyword);

        //then
        Assertions.assertEquals(1, res.getSearchCnt());

    }

    @Test
    @DisplayName("없는 검색 키워드가 검색되는 케이스")
    void exist_keyword() {
        String keyword = "존재하는 검색어";
        Long searchCnt = 22L;

        //given
        given(searchKeywordRepository.findById(keyword)).willReturn(Optional.of(new SearchKeyword(keyword, searchCnt)));
        given(searchKeywordRepository.save(any(SearchKeyword.class))).willAnswer(invocation -> invocation.getArguments()[0]);

        //when
        SearchKeywordDto res = searchService.save(keyword);

        //then
        Assertions.assertEquals(23, res.getSearchCnt());
    }
}