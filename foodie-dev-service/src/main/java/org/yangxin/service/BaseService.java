package org.yangxin.service;

import com.github.pagehelper.PageInfo;
import org.yangxin.pojo.vo.order.MyOrderVO;
import org.yangxin.utils.PagedGridResult;

import java.util.List;

/**
 * @author yangxin
 * 2020/11/27 17:43
 */
public class BaseService {

    public PagedGridResult setPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(page);
        pagedGridResult.setRows(list);
        pagedGridResult.setTotal(pageList.getPages());
        pagedGridResult.setRecords(pageList.getTotal());
        return pagedGridResult;
    }
}
