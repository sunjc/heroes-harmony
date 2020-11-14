package io.itrunner.heroes;

import io.itrunner.heroes.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

import static io.itrunner.heroes.data.DBUtils.createDatabase;
import static io.itrunner.heroes.data.DBUtils.initDatabase;

public class MainAbility extends Ability {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

        setAbilitySliceAnimator(null);

        createDatabase(this);
        initDatabase(this);
    }

}
