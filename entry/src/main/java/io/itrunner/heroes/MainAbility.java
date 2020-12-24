package io.itrunner.heroes;

import io.itrunner.heroes.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static io.itrunner.heroes.data.DBUtils.*;

public class MainAbility extends Ability {
    private static final String TAG = "MainAbility";
    private static final HiLogLabel LOG_LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00101, TAG);

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LOG_LABEL, "onStart");
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());

        setAbilitySliceAnimator(null);

        if (!existsDatabase(this)) {
            createDatabase(this);
            initDatabase(this);
        }
    }

    @Override
    protected void onStop() {
        HiLog.info(LOG_LABEL, "onStop");
        super.onStop();
    }
}
