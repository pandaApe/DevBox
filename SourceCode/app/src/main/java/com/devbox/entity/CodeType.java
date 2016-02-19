package com.devbox.Entity;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

/**
 * Created by whailong on 20/1/16.
 */
@AVClassName("OSCodeType")
public class CodeType extends AVObject {

    //    private int numCount;
//    private String typeCNDescription;
//    private String typeENDescription;

//    private String parentType;


    public String getParentType() {
        return getString("parentType");
    }

    public void setParentType(String parentType) {
        this.put("parentType", parentType);
    }

    public CodeType() {
        super();
        this.put("numCount", 0);
    }

    public int getNumCount() {
        return getInt("numCount");
    }

    public int increase() {
        increment("numCount");
        return this.getNumCount();
    }

    public String getTypeCNDescription() {
        return getString("typeCNDescription");
    }

    public void setTypeCNDescription(String nameCN) {
        this.put("typeCNDescription", nameCN);
    }

    public String getTypeENDescription() {
        return getString("typeENDescription");
    }

    public void setTypeENDescription(String nameEn) {
        this.put("typeENDescription", nameEn);
    }

    public void addCodeLib(CodeLib codeLib) {
        getRelation("codeLibs").add(codeLib);
    }

    public AVRelation<CodeLib> getCodeLibsRelation() {
        return getRelation("codeLibs");
    }

    public String getFullTitle() {
        return this.getTypeCNDescription();
    }


}
