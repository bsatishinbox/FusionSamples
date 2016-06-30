package com.samples.scrolltofetch.beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.render.ClientEvent;

import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class ScrollToFetch {

    private final int LAZY_LOAD_FROM_INDEX = 2;
    private final boolean FALSE = false;
    private final boolean TRUE = true;
    private HashMap<String, Boolean> lazyLoadMap = null;

    public ScrollToFetch() {
        lazyLoadMap = new HashMap<String, Boolean>();
    }

    public void refreshNextTaskflow(ClientEvent clientEvent) {
        ScrollToFetchBindingsBean scrollToFetchBindingsBean =
            (ScrollToFetchBindingsBean)ADFContext.getCurrent().getRequestScope().get("scrollToFetchBindingsBean");
        if (lazyLoadMap == null || lazyLoadMap.size() == 0) {
            RichPanelGroupLayout uiGroup = scrollToFetchBindingsBean.getPglBinding();
            List<UIComponent> uiComponents = uiGroup.getChildren();
            for (int iCount = 0, regionCount = 0; iCount < uiComponents.size(); iCount++) {
                //Check if the UI component is instance of region
                if (uiComponents.get(iCount) instanceof RichRegion) {
                    regionCount++;
                    RichRegion richRegion = (RichRegion)uiComponents.get(iCount);
                    if (regionCount > LAZY_LOAD_FROM_INDEX)
                        lazyLoadMap.put(richRegion.getId(), FALSE);
                    else
                        lazyLoadMap.put(richRegion.getId(), TRUE);
                

                }
            }
        }
    Set<Map.Entry<String,Boolean>> entrySet =  lazyLoadMap.entrySet();
    Iterator <Map.Entry<String,Boolean>> entrySetIter = entrySet.iterator();
        while (entrySetIter.hasNext()) {
            Map.Entry<String, Boolean> entry = entrySetIter.next();
            if (entry.getValue().equals(false)) {
                lazyLoadMap.put(entry.getKey(), TRUE);
                break;
            }
        }
        
        // notify load is complete
        FacesContext context =  FacesContext.getCurrentInstance();
                 ExtendedRenderKitService erks =
                   Service.getRenderKitService(context,
                           ExtendedRenderKitService.class);
               
                
                 StringBuilder builder = new StringBuilder();
                 builder.append(" notifyLoadComplete();");
                
                 erks.addScript(context, builder.toString());
       
    }

    public void setLazyLoadMap(HashMap<String, Boolean> lazyLoadMap) {
        this.lazyLoadMap = lazyLoadMap;
    }

    public HashMap<String, Boolean> getLazyLoadMap() {
        return lazyLoadMap;
    }
}
