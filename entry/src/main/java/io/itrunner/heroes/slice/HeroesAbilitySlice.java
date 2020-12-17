package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import io.itrunner.heroes.data.Hero;
import io.itrunner.heroes.data.HeroRepository;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.components.TableLayout.LayoutConfig;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.TextAlignment;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;

public class HeroesAbilitySlice extends AbilitySlice {
    private static final String TAG = "HeroesAbilitySlice";

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, TAG);

    private HeroRepository repository;
    private TextField heroText;

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LOG_LABEL, "onStart");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_heroes);

        repository = new HeroRepository(this);

        bindNavListener();
        bindAddListener();
    }

    @Override
    public void onActive() {
        HiLog.info(LOG_LABEL, "onActive");
        super.onActive();

        queryHeroes();
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
        // goto dashboard page
        Button dashboardBtn = (Button) findComponentById(ResourceTable.Id_button_dashboard);
        dashboardBtn.setClickedListener(component -> present(new MainAbilitySlice(), new Intent()));
    }

    private void bindAddListener() {
        heroText = (TextField) findComponentById(ResourceTable.Id_hero_name);
        Button addBtn = (Button) findComponentById(ResourceTable.Id_button_add);
        addBtn.setClickedListener(component -> addHero());
    }

    private void addHero() {
        String heroName = heroText.getText().trim();
        if (heroName.length() > 2) {
            repository.insert(new Hero(heroName));
            heroText.setText("");
            queryHeroes();
        }
    }

    private void queryHeroes() {
        TableLayout heroesTable = (TableLayout) LayoutScatter.getInstance(this).parse(ResourceTable.Layout_hero_table, null, false);

        LayoutConfig columnConfig = new LayoutConfig(160, 60);
        LayoutConfig buttonConfig = new LayoutConfig(58, LayoutConfig.MATCH_CONTENT);
        buttonConfig.setMargins(50, 4, 0, 4);

        ShapeElement grayButtonElement = new ShapeElement(this, ResourceTable.Graphic_gray_button_element);
        ShapeElement columnElement = new ShapeElement(this, ResourceTable.Graphic_white_column_element);

        List<Hero> heroes = repository.queryAll();

        int i = 1;
        for (Hero hero : heroes) {

            Text no = new Text(this);
            no.setText(i++ + "");
            no.setTextSize(30);
            no.setWidth(80);
            no.setHeight(58);
            no.setPadding(4, 4, 4, 4);
            no.setBackground(columnElement);

            Button heroNameBtn = new Button(this);
            heroNameBtn.setText(hero.getName());
            heroNameBtn.setTextSize(30);
            heroNameBtn.setTextAlignment(TextAlignment.CENTER);
            heroNameBtn.setWidth(800);
            heroNameBtn.setHeight(58);
            heroNameBtn.setPadding(4, 4, 4, 4);
            heroNameBtn.setBackground(columnElement);
            heroNameBtn.setClickedListener(component -> gotoHeroDetails(hero.getId()));

            Button deleteBtn = new Button(this);
            deleteBtn.setText("X");
            deleteBtn.setTextSize(30);
            deleteBtn.setPadding(4, 4, 4, 4);
            deleteBtn.setLayoutConfig(buttonConfig);
            deleteBtn.setBackground(grayButtonElement);
            deleteBtn.setClickedListener(component -> {
                repository.delete(hero.getId());
                queryHeroes();
            });
            DirectionalLayout deleteCol = new DirectionalLayout(this);
            deleteCol.setLayoutConfig(columnConfig);
            deleteCol.setBackground(columnElement);
            deleteCol.addComponent(deleteBtn);

            heroesTable.addComponent(no);
            heroesTable.addComponent(heroNameBtn);
            heroesTable.addComponent(deleteCol);
        }

        ScrollView scrollView = (ScrollView) findComponentById(ResourceTable.Id_scroll_view);
        scrollView.removeAllComponents();
        scrollView.addComponent(heroesTable);
    }

    private void gotoHeroDetails(Long id) {
        Intent intent = new Intent();
        intent.setParam("id", id);
        present(new HeroDetailsAbilitySlice(), intent);
    }

}