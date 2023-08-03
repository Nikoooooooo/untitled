package org.ycframework.context;

public class YcBeanDefinition {
    private boolean isLazy;
    private String scope="singleton";
    private boolean isPrimary;
    private String classInfo;//类的实例信息

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public void setLazy(boolean lazy) {

        isLazy = lazy;
    }

}
