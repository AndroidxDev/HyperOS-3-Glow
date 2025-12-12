package xdev.hyperglow.helpers;

import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import fan.animation.Folme;
import fan.animation.FolmeEase;
import fan.animation.IStateStyle;
import fan.animation.base.AnimConfig;
import fan.animation.controller.AnimState;
import fan.animation.property.ViewProperty;

public class AnimHelper {
    public static void startPageLogoAnim(View view) {
        if (view != null) {
            AnimState animState = new AnimState("start");
            ViewProperty viewProperty = ViewProperty.SCALE_X;
            animState = animState.add(viewProperty, 0.5d);
            ViewProperty viewProperty2 = ViewProperty.SCALE_Y;
            animState = animState.add(viewProperty2, 0.5d);
            AnimState add = new AnimState("middle").add(viewProperty, 0.95d).add(viewProperty2, 0.95d);
            Folme.use(view).state().setTo(animState).to(add, new AnimConfig().setEase(FolmeEase.sinOut(440))).then(new AnimState("end").add(viewProperty, 1.0d).add(viewProperty2, 1.0d), new AnimConfig().setEase(FolmeEase.cubicOut(700)));
            AnimConfig delay = new AnimConfig().setDelay(60);
            IStateStyle state = Folme.use(view).state();
            viewProperty = ViewProperty.ALPHA;
            state.setTo(viewProperty, 0.0f).to(viewProperty, 1.0f, delay);

        }
    }

    public static void startPageBtnAnim(View view) {
        if (view != null) {
            AnimState animState = new AnimState("start");
            ViewProperty viewProperty = ViewProperty.ALPHA;
            animState = animState.add(viewProperty, 0.0d);
            ViewProperty viewProperty2 = ViewProperty.SCALE_X;
            animState = animState.add(viewProperty2, 0.9d);
            ViewProperty viewProperty3 = ViewProperty.SCALE_Y;
            animState = animState.add(viewProperty3, 0.9d);
            Folme.use(view).state().setTo(animState).to(new AnimState("end").add(viewProperty, 1.0d).add(viewProperty2, 1.0d).add(viewProperty3, 1.0d), new AnimConfig().setEase(FolmeEase.cubicOut(450)).setDelay(1340));

        }
    }
}