package com.xinmei.common.basic.response;

/**
 * mpaas消息实体
 *
 * @Author xbzhu
 * @Mail toby.zhu@trustlife.com
 * @Date 2017年02月14日 10:16
 */
public class MpaasResponse<T> extends AbstractFacadeResp{

    private T data;
    public MpaasResponse(boolean success) {
        super(success);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
