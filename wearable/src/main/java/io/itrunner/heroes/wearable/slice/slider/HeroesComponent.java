package io.itrunner.heroes.wearable.slice.slider;

import io.itrunner.heroes.wearable.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.app.AbilityContext;
import ohos.global.resource.ResourceManager;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class HeroesComponent implements ComponentOwner {
    private static final String TAG = "HeroesComponent";
    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00102, TAG);

    private final AbilityContext context;
    private final Component root;

    public HeroesComponent(AbilityContext context) {
        this.context = context;
        this.root = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_heroes, null, false);
    }

    @Override
    public Component getComponent() {
        return root;
    }

    @Override
    public void init() {
        fillHeroes();
    }

    private void fillHeroes() {
        DirectionalLayout layout = (DirectionalLayout) root.findComponentById(ResourceTable.Id_heroes);
        layout.removeAllComponents();

        ResourceManager resourceManager = context.getResourceManager();
        try {
            String[] heroes = resourceManager.getElement(ResourceTable.Strarray_heroes).getStringArray();
            for (String heroName : heroes) {
                Text hero = new Text(context);
                hero.setText(heroName);
                hero.setTextSize(40);
                hero.setTextAlignment(TextAlignment.CENTER);
                hero.setTextColor(Color.WHITE);
                hero.setWidth(DirectionalLayout.LayoutConfig.MATCH_PARENT);
                hero.setHeight(70);
                layout.addComponent(hero);
            }
        } catch (Exception e) {
            HiLog.error(LOG_LABEL, e.getMessage());
        }
    }
}
