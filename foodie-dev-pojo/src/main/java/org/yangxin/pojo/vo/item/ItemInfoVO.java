package org.yangxin.pojo.vo.item;

import lombok.Builder;
import lombok.Data;
import org.yangxin.pojo.Item;
import org.yangxin.pojo.ItemImg;
import org.yangxin.pojo.ItemParam;
import org.yangxin.pojo.ItemSpec;

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
    private Item item;

    /**
     * 商品图片
     */
    private List<ItemImg> itemImgList;

    /**
     * 商品规格
     */
    private List<ItemSpec> itemSpecList;

    /**
     * 商品参数
     */
    private ItemParam itemParams;
}
