package org.yangxin.service.converter;

import com.github.pagehelper.PageInfo;
import org.yangxin.pojo.vo.PagingGridVO;

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
    public static PagingGridVO convert(List<?> list, Integer page) {
        PageInfo<?> pageInfo = new PageInfo<>(list);

        return PagingGridVO.builder()
                .page(page)
                .rows(list)
                .total(pageInfo.getPages())
                .records(pageInfo.getTotal())
                .build();
    }
}
