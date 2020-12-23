package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.*;
import ohos.agp.components.element.*;
import ohos.sysappcomponents.settings.SystemSettings;

public class ComponentsAbilitySlice extends AbilitySlice {
    private boolean isRunning = false;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_component_examples);

        // 自动调整字体
        Text autoFontText = (Text) findComponentById(ResourceTable.Id_text_auto_font);
        autoFontText.setAutoFontSizeRule(30, 100, 1);
        autoFontText.setClickedListener(component -> autoFontText.setText(autoFontText.getText() + "!"));

        // 启动跑马灯效果
        Text autoScrollingText = (Text) findComponentById(ResourceTable.Id_text_auto_scrolling);
        autoScrollingText.setAutoScrollingCount(6);
        autoScrollingText.startAutoScrolling();

        // 设置Rating样式
        // 全填充样式
        VectorElement filledElement = new VectorElement(this, ResourceTable.Graphic_star_full);
        // 半填充样式
        VectorElement halfFilledElement = new VectorElement(this, ResourceTable.Graphic_star_half);
        // 未填充样式
        VectorElement unfilledElement = new VectorElement(this, ResourceTable.Graphic_star_gray);
        // 设置样式
        Rating rating = (Rating) findComponentById(ResourceTable.Id_rating);
        rating.setFilledElement(filledElement);
        rating.setHalfFilledElement(halfFilledElement);
        rating.setUnfilledElement(unfilledElement);
        // 设置步长
        rating.setGrainSize(0.5f);
        // 设置星级
        rating.setScore(3.5f);

        // 设置Switch的样式
        // 开启状态下滑块的样式
        ShapeElement elementThumbOn = new ShapeElement();
        elementThumbOn.setShape(ShapeElement.OVAL);
        elementThumbOn.setRgbColor(RgbColor.fromArgbInt(0xFFFF90FF));
        // 关闭状态下滑块的样式
        ShapeElement elementThumbOff = new ShapeElement();
        elementThumbOff.setShape(ShapeElement.OVAL);
        elementThumbOff.setRgbColor(RgbColor.fromArgbInt(0xFF0000FF));
        // 开启状态下轨迹样式
        ShapeElement elementTrackOn = new ShapeElement();
        elementTrackOn.setShape(ShapeElement.RECTANGLE);
        elementTrackOn.setRgbColor(RgbColor.fromArgbInt(0xFF87CEFA));
        elementTrackOn.setCornerRadius(50);
        // 关闭状态下轨迹样式
        ShapeElement elementTrackOff = new ShapeElement();
        elementTrackOff.setShape(ShapeElement.RECTANGLE);
        elementTrackOff.setRgbColor(RgbColor.fromArgbInt(0xFF808080));
        elementTrackOff.setCornerRadius(50);
        // 设置样式
        Switch btnSwitch = (Switch) findComponentById(ResourceTable.Id_btn_switch);
        btnSwitch.setTrackElement(stateElement(elementTrackOn, elementTrackOff));
        btnSwitch.setThumbElement(stateElement(elementThumbOn, elementThumbOff));
        btnSwitch.setChecked(true);

        // 设置复选框样式
        // 选中样式
        VectorElement checkboxOn = new VectorElement(this, ResourceTable.Graphic_checkbox_checked);
        // 未选样式
        VectorElement checkboxOff = new VectorElement(this, ResourceTable.Graphic_checkbox_unchecked);
        // 设置样式
        Checkbox checkbox = (Checkbox) findComponentById(ResourceTable.Id_check_box);
        checkbox.setButtonElement(stateElement(checkboxOn, checkboxOff));
        checkbox.setChecked(true);

        // 设置单选钮样式
        // 选中样式
        ShapeElement radioOn = new ShapeElement();
        radioOn.setShape(ShapeElement.OVAL);
        radioOn.setRgbColor(RgbPalette.BLUE);
        // 未选样式
        ShapeElement radioOff = new ShapeElement();
        radioOff.setShape(ShapeElement.OVAL);
        radioOff.setStroke(3, RgbPalette.BLUE);
        // 设置样式
        RadioButton radioButton1 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_1);
        RadioButton radioButton2 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_2);
        radioButton1.setButtonElement(stateElement(radioOn, radioOff));
        radioButton2.setButtonElement(stateElement(radioOn, radioOff));
        radioButton2.setChecked(true);

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

        // 添加动画
        FrameAnimationElement frameAnimationElement = new FrameAnimationElement(this, ResourceTable.Graphic_animation_pandas);
        Component animation = findComponentById(ResourceTable.Id_animation_pandas);
        animation.setBackground(frameAnimationElement);
        frameAnimationElement.start();

        // Hero Button
        Button heroBtn = (Button) findComponentById(ResourceTable.Id_button_hero);
        heroBtn.setClickedListener(component -> terminateAbility());

        // 读取设备名称
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(this);
        String deviceName = SystemSettings.getValue(dataAbilityHelper, SystemSettings.General.DEVICE_NAME);
        Text deviceNameText = (Text) findComponentById(ResourceTable.Id_device_name);
        deviceNameText.setText(deviceName);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private StateElement stateElement(Element on, Element off) {
        StateElement stateElement = new StateElement();
        stateElement.addState(new int[]{ComponentState.COMPONENT_STATE_CHECKED}, on);
        stateElement.addState(new int[]{ComponentState.COMPONENT_STATE_EMPTY}, off);
        return stateElement;
    }
}
