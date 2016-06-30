package com.samples.scrolltofetch.beans;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.util.ComponentReference;

// ComponentReference is used to get the latest component 
// refernce in tree.
public class ScrollToFetchBindingsBean {
    private ComponentReference afGroupBinding;
    private RichPanelGroupLayout pglBinding;

    public ScrollToFetchBindingsBean() {
    }

    public void setAfGroupBinding(UIXGroup afGroupBinding) {
        this.afGroupBinding = ComponentReference.newUIComponentReference(afGroupBinding);
    }

    public UIXGroup getAfGroupBinding() {
        if (afGroupBinding != null) {
            return (UIXGroup)afGroupBinding.getComponent();
        }
        return null;
    }

    public void setPglBinding(RichPanelGroupLayout pglBinding) {
        this.pglBinding = pglBinding;
    }

    public RichPanelGroupLayout getPglBinding() {
        return pglBinding;
    }
}
