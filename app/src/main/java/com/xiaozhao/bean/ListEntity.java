package com.xiaozhao.bean;

import java.io.Serializable;
import java.util.List;

public interface ListEntity<T extends Serializable> extends Serializable {

	
    public List<T> getList();
}
