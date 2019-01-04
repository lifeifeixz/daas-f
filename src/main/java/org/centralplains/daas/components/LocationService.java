package org.centralplains.daas.components;

import org.centralplains.daas.beans.Location;

/**
 * Created by flysLi on 2019/1/4.
 */
public interface LocationService {

    /**
     * 通过关键字获取详细地址
     *
     * @param keywords
     * @return
     */
    Location getByKeywords(String keywords);

    /**
     * 保存地址
     *
     * @param location
     * @return
     */
    Location save(Location location);
}
