package io.itrunner.heroes;

import io.itrunner.heroes.slice.ComponentsAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ComponentsAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ComponentsAbilitySlice.class.getName());

        setAbilitySliceAnimator(null);
    }
}
