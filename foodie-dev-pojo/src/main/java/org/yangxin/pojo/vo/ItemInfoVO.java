package org.yangxin.pojo.vo;

import lombok.Builder;
import lombok.Data;
import org.yangxin.pojo.Items;
import org.yangxin.pojo.ItemsImg;
import org.yangxin.pojo.ItemsParam;
import org.yangxin.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品详情
 *
 * @author yangxin
 * 2019/11/27 22:24
 */
@Builder
@Data
public class ItemInfoVO {
    /**
     * 商品
     */
    private Items item;

    /**
     * 商品图片
     */
    private List<ItemsImg> itemImgList;

    /**
     * 商品规格
     */
    private List<ItemsSpec> itemSpecList;

    /**
     * 商品参数
     */
    private ItemsParam itemParams;
}
