package org.yangxin.converter;

import com.github.pagehelper.PageInfo;
import org.yangxin.result.PagingGridResult;

import java.util.List;

/**
 * PageInfo转PagingGridResultConverter
 *
 * @author yangxin
 * 2019/11/28 21:29
 */
public class PageInfo2PagingGridResultConverter {
    /**
     * 转换
     */
    public static PagingGridResult convert(List<?> list, int page) {
        PageInfo<?> pageInfo = new PageInfo<>(list);

        return PagingGridResult.builder()
                .page(page)
                .rows(list)
                .total(pageInfo.getPages())
                .record(pageInfo.getTotal())
                .build();
    }
}
