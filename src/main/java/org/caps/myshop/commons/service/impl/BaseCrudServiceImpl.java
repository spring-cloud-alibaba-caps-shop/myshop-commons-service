package org.caps.myshop.commons.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.caps.myshop.commons.dto.AbstractBaseDomain;
import org.caps.myshop.commons.service.BaseCrudService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * @author caps
 * @Date 2019/5/25 22:21
 * @Description
 */
public class BaseCrudServiceImpl<T extends AbstractBaseDomain,M extends MyMapper<T>> implements BaseCrudService<T> {

    @Autowired
    protected M mapper;

    private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public boolean unique(String property, String value){
        Example example=new Example(entityClass);
        example.createCriteria().andEqualTo(property,value);
        int count = mapper.selectCountByExample(example);
        if(count>0){
            return false;
        }
        return true;
    }

    @Override
    public T save(T t) {
        int result=0;
        Date currentDate = new Date();
        t.setUpdated(currentDate);
        //新增
        if(t.getId()==null){
            t.setCreated(currentDate);
            //用户id数据回显
            result = mapper.insertUseGeneratedKeys(t);
        }

        // 更新
        else {
            result = mapper.updateByPrimaryKey(t);
        }

        // 保存数据成功
        if (result > 0) {
            return t;
        }

        // 保存数据失败
        return null;
    }

    @Override
    public PageInfo<T> page(T domain, int pageNum, int pageSize) {
        Example example = new Example(entityClass);
        example.createCriteria().andEqualTo(domain);

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        return pageInfo;
    }

}
