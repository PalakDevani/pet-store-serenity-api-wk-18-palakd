package com.petstore.models;

import java.util.HashMap;
import java.util.List;

public class UserPojoWithArray {

    List<HashMap<Object, Object>> list;

    public List<HashMap<Object, Object>> getList() {
        return list;
    }

    public void setList(List<HashMap<Object, Object>> list) {
        this.list = list;
    }
}
