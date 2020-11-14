package io.itrunner.heroes.slice;

import io.itrunner.heroes.ResourceTable;
import io.itrunner.heroes.data.Hero;
import io.itrunner.heroes.data.HeroRepository;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class HeroDetailsAbilitySlice extends AbilitySlice {
    private static final String TAG = "HeroDetailsAbilitySlice";

    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, TAG);

    private HeroRepository repository;

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LOG_LABEL, "onStart");
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_hero_details);

        repository = new HeroRepository(this);

        bindNavListener();
        bindButtonListener();
        showHeroDetails(intent);
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

    private void bindNavListener() {
        // Dashboard Button
        Button dashboardBtn = (Button) findComponentById(ResourceTable.Id_button_dashboard);
        dashboardBtn.setClickedListener(component -> present(new MainAbilitySlice(), new Intent()));

        // Heroes Button
        Button heroesBtn = (Button) findComponentById(ResourceTable.Id_button_heroes);
        heroesBtn.setClickedListener(component -> present(new HeroesAbilitySlice(), new Intent()));
    }

    private void bindButtonListener() {
        // Back Button
        Button backBtn = (Button) findComponentById(ResourceTable.Id_button_back);
        backBtn.setClickedListener(component -> back());

        // Save Button
        Button saveBtn = (Button) findComponentById(ResourceTable.Id_button_save);
        saveBtn.setClickedListener(component -> {
            updateHero();
            back();
        });
    }

    private void showHeroDetails(Intent intent) {
        long id = intent.getLongParam("id", 0);
        Hero hero = repository.getOne(id);
        if (hero != null) {
            Text heroId = (Text) findComponentById(ResourceTable.Id_hero_id);
            heroId.setText(id + "");

            TextField heroName = (TextField) findComponentById(ResourceTable.Id_hero_name);
            heroName.setText(hero.getName());
        }
    }

    private void updateHero() {
        Text idText = (Text) findComponentById(ResourceTable.Id_hero_id);
        TextField nameText = (TextField) findComponentById(ResourceTable.Id_hero_name);

        Hero hero = new Hero();
        hero.setId(Long.parseLong(idText.getText()));
        hero.setName(nameText.getText().trim());

        repository.update(hero);
    }

    private void back() {
        terminate();
    }

}