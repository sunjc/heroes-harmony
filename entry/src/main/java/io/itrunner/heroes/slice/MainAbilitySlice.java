package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import io.itrunner.heroes.data.Hero;
import io.itrunner.heroes.data.HeroRepository;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.components.ComponentContainer.LayoutConfig;
import ohos.agp.components.element.ShapeElement;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    private static final String TAG = "MainAbilitySlice";
    private static final String ACTION_COMPONENTS = "action.hero.components";

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, TAG);

    private HeroRepository repository;
    private TextField searchText;

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LOG_LABEL, "onStart");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_main);

        repository = new HeroRepository(this);

        bindNavListener();
        bindSearchListener();
    }

    @Override
    public void onActive() {
        HiLog.info(LOG_LABEL, "onActive");
        super.onActive();

        queryTopHeroes();
    }

    @Override
    protected void onInactive() {
        HiLog.info(LOG_LABEL, "onInactive");
        super.onInactive();
    }

    @Override
    protected void onBackground() {
        HiLog.info(LOG_LABEL, "onBackground");
        super.onBackground();
    }

    @Override
    public void onForeground(Intent intent) {
        HiLog.info(LOG_LABEL, "onForeground");
        super.onForeground(intent);
    }

    @Override
    protected void onStop() {
        HiLog.info(LOG_LABEL, "onStop");
        super.onStop();
    }

    private void bindNavListener() {
        // to heroes slice
        Button heroesBtn = (Button) findComponentById(ResourceTable.Id_button_heroes);
        heroesBtn.setClickedListener(component -> present(new HeroesAbilitySlice(), new Intent()));

        // to components page
        Image componentsImg = (Image) findComponentById(ResourceTable.Id_image_components);
        componentsImg.setClickedListener(component -> gotoComponents());
    }

    private void bindSearchListener() {
        searchText = (TextField) findComponentById(ResourceTable.Id_search);
        searchText.setEditorActionListener(action -> {
            if (action == 3) {
                fillSearchList(searchText.getText());
                return true;
            }
            return false;
        });
    }

    private void fillSearchList(String name) {
        List<Hero> heroes = repository.queryByName(name);

        ListContainer container = (ListContainer) findComponentById(ResourceTable.Id_search_list);
        ListItemProvider itemProvider = new ListItemProvider(this, heroes);
        container.setItemProvider(itemProvider);
        container.setItemClickedListener((listContainer, component, position, id) -> {
            gotoHeroDetails(itemProvider.getItemId(position));
            clear();
        });
    }

    private void queryTopHeroes() {
        TableLayout tableLayout = (TableLayout) findComponentById(ResourceTable.Id_top_heroes);
        ShapeElement background = new ShapeElement(this, ResourceTable.Graphic_blue_button_element);
        LayoutConfig config = new LayoutConfig(400, 100);
        config.setMargins(0, 0, 40, 0);

        tableLayout.removeAllComponents();

        List<Hero> heroes = repository.queryTop4();
        for (Hero hero : heroes) {
            Button heroBtn = new Button(this);
            heroBtn.setText(hero.getName());
            heroBtn.setTextSize(40);
            heroBtn.setBackground(background);
            heroBtn.setLayoutConfig(config);
            heroBtn.setClickedListener(component -> gotoHeroDetails(hero.getId()));

            tableLayout.addComponent(heroBtn);
        }
    }

    private void gotoHeroDetails(Long id) {
        Intent intent = new Intent();
        intent.setParam("id", id);
        present(new HeroDetailsAbilitySlice(), intent);
    }

    private void gotoComponents() {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withAction(ACTION_COMPONENTS)
                .build();
        intent.setOperation(operation);

        startAbility(intent);
    }

    private void clear() {
        searchText.setText("");
    }

}