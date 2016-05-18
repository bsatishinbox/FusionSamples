package com.samples.transactionflows.beans;

import oracle.adf.model.BindingContext;

public class DCNameBackingBean {
    public DCNameBackingBean() {
        super();
    }
    
    public String getParentDCName(){
     return BindingContext.getCurrent().getCurrentDataControlFrame();
    }
}
