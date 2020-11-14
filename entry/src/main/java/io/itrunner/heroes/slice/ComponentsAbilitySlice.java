package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

public class ComponentsAbilitySlice extends AbilitySlice {
    private boolean isRunning = false;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_component_examples);

        // 自动调整字体
        Text autoFontText = (Text) findComponentById(ResourceTable.Id_text_auto_font);
        autoFontText.setAutoFontSize(true);
        autoFontText.setAutoFontSizeRule(30, 100, 1);
        autoFontText.setTruncationMode(Text.TruncationMode.ELLIPSIS_AT_END);
        autoFontText.setClickedListener(component -> autoFontText.setText(autoFontText.getText() + "!"));

        // 跑马灯效果
        Text autoScrollingText = (Text) findComponentById(ResourceTable.Id_text_auto_scrolling);
        autoScrollingText.setTruncationMode(Text.TruncationMode.AUTO_SCROLLING);
        // autoScrollingText.setAutoScrollingCount(Text.AUTO_SCROLLING_FOREVER);
        autoScrollingText.startAutoScrolling();

        // 缩放Image
        Image componentsImg = (Image) findComponentById(ResourceTable.Id_image_components);
        componentsImg.setScaleMode(Image.ScaleMode.INSIDE);

        // 复选框
        Checkbox checkbox = (Checkbox) findComponentById(ResourceTable.Id_check_box);
        checkbox.setChecked(true);

        // 单选钮
        RadioButton radioButton = (RadioButton) findComponentById(ResourceTable.Id_radio_button_1);
        radioButton.setChecked(true);

        // 启停计时器
        TickTimer tickTimer = (TickTimer) findComponentById(ResourceTable.Id_tick_timer);
        tickTimer.start();
        isRunning = true;
        tickTimer.setClickedListener(component -> {
            if (isRunning) {
                tickTimer.stop();
            } else {
                tickTimer.start();
            }

            isRunning = !isRunning;
        });

        // 后退
        Button backBtn = (Button) findComponentById(ResourceTable.Id_button_back);
        backBtn.setClickedListener(component -> terminateAbility());
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
