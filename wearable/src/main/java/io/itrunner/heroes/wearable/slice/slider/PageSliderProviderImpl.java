package io.itrunner.heroes.wearable.slice.slider;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.PageSliderProvider;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PageSliderProviderImpl extends PageSliderProvider {
    private static final String TAG = "PageSliderProvider";

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00102, TAG);

    private List<ComponentOwner> components = new ArrayList<>();

    public void addComponent(ComponentOwner component) {
        components.add(component);
    }

    public int getCount() {
        return components.size();
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int index) {
        HiLog.info(LOG_LABEL, "create page in container, the index is %{public}d", index);
        if (componentContainer == null || index >= components.size()) {
            return Optional.empty();
        }

        components.get(index).init();
        Component component = components.get(index).getComponent();
        componentContainer.addComponent(component);

        return component;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int index, Object object) {
        HiLog.info(LOG_LABEL, "destroy page from container, the index is %{public}d", index);
        if (componentContainer == null || index >= components.size()) {
            return;
        }
        Component component = components.get(index).getComponent();
        componentContainer.removeComponent(component);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object object) {
        return component == object;
    }

}
