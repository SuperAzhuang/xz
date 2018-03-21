package com.chad.library.adapter.base.entity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class SectionEntity<T> {

    public boolean isHeader;
    public boolean isStern;
    public T t;
    public String header;

    public SectionEntity(boolean isHeader, boolean isStern, String header) {
        this.isHeader = isHeader;
        this.isStern = isStern;
        this.header = header;
        this.t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.isStern = false;
        this.header = null;
        this.t = t;
    }
}
