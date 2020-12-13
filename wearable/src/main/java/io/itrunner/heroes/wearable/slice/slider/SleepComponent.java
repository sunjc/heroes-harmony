package io.itrunner.heroes.wearable.slice.slider;

import io.itrunner.heroes.wearable.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.AbilityContext;

public class SleepComponent implements ComponentOwner {
    private Component root;

    public SleepComponent(AbilityContext context) {
        this.root = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_sleep, null, false);
    }

    @Override
    public Component getComponent() {
        return root;
    }

    @Override
    public void init() {
        Text hour = (Text) root.findComponentById(ResourceTable.Id_sleep_hour_text);
        hour.setText("6");

        Text minute = (Text) root.findComponentById(ResourceTable.Id_sleep_minute_text);
        minute.setText("30");

        Text goal = (Text) root.findComponentById(ResourceTable.Id_sleep_goal_text);
        goal.setText("8");
    }
}
