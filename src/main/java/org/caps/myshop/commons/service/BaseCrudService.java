package org.caps.myshop.commons.service;

import com.github.pagehelper.PageInfo;
import org.caps.myshop.commons.dto.AbstractBaseDomain;

/**
 * @author caps
 * @Date 2019/5/25 22:10
 * @Description
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {
    /**
     * 查询属性值是否唯一
     *
     * @param property
     * @param value
     * @return true/唯一，false/不唯一
     */
    default boolean unique(String property, String value) {
        return false;
    }

    /**
     * 保存
     * @param t
     * @return
     */
    default T save(T t) {
        return null;
    }
    /**
     * 分页查询
     * @param domain
     * @param pageNum
     * @param pageSize
     * @return
     */
    default PageInfo<T> page(T domain, int pageNum, int pageSize) {
        return null;
    }
}
