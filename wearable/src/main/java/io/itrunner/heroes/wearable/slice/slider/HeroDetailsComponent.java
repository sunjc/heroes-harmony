package io.itrunner.heroes.wearable.slice.slider;

import io.itrunner.heroes.wearable.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.AbilityContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class HeroDetailsComponent implements ComponentOwner {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    private AbilityContext context;

    private Component root;

    public HeroDetailsComponent(AbilityContext context) {
        this.context = context;
        this.root = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_hero_details, null, false);
    }

    @Override
    public Component getComponent() {
        return root;
    }

    @Override
    public void init() {
        Text date = (Text) root.findComponentById(ResourceTable.Id_detail_date);
        date.setText(LocalDate.now().format(DATE_FORMATTER));

        Text hour = (Text) root.findComponentById(ResourceTable.Id_sleep_hour_text);
        hour.setText("6");

        Text minute = (Text) root.findComponentById(ResourceTable.Id_sleep_minute_text);
        minute.setText("30");

        Text goal = (Text) root.findComponentById(ResourceTable.Id_sleep_goal_text);
        goal.setText("8");
    }
}
