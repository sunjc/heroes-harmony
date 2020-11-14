package io.itrunner.heroes.wearable.slice;

import io.itrunner.heroes.wearable.ResourceTable;
import io.itrunner.heroes.wearable.slice.slider.HeroDetailsComponent;
import io.itrunner.heroes.wearable.slice.slider.HeroesComponent;
import io.itrunner.heroes.wearable.slice.slider.PageSliderProviderImpl;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.PageSlider;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private static final String TAG = "MainAbilitySlice";

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00102, TAG);

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LOG_LABEL, "onStart");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_main);

        // 添加子页面
        addComponents();
    }

    @Override
    public void onActive() {
        HiLog.info(LOG_LABEL, "onActive");
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        HiLog.info(LOG_LABEL, "onForeground");
        super.onForeground(intent);
    }

    private void addComponents() {
        PageSliderProviderImpl provider = new PageSliderProviderImpl();
        provider.addComponent(new HeroesComponent(this));
        provider.addComponent(new HeroDetailsComponent(this));

        PageSlider slider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);
        slider.setProvider(provider);
    }

}
