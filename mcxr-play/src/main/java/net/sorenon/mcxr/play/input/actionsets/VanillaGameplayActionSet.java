package net.sorenon.mcxr.play.input.actionsets;

import net.sorenon.mcxr.play.MCXRPlayClient;
import net.sorenon.mcxr.play.input.actions.Action;
import net.sorenon.mcxr.play.input.actions.BoolAction;
import net.sorenon.mcxr.play.input.actions.FloatAction;
import net.sorenon.mcxr.play.input.actions.Vec2fAction;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VanillaGameplayActionSet extends ActionSet {

    public final BoolAction jump = new BoolAction("jump");
    public final BoolAction inventory = new BoolAction("inventory");
    public final BoolAction attack = new BoolAction("attack");
    public final BoolAction use = new BoolAction("use");
    public final BoolAction sprint = new BoolAction("sprint");
    public final BoolAction sneak = new BoolAction("sneak");
    public final BoolAction resetPos = new BoolAction("reset_pos");
    public final BoolAction stand = new BoolAction("stand");
    public final BoolAction menu = new BoolAction("menu");
    public final BoolAction chat = new BoolAction("chat");

    public final FloatAction turn = new FloatAction("turn");
    //TODO remove this with new input system
    public final FloatAction hotbar = new FloatAction("hotbar");
    public final Vec2fAction move = new Vec2fAction("move");

    public final BoolAction hotbarLeft = new BoolAction("hotbar_left");
    public final BoolAction hotbarRight = new BoolAction("hotbar_right");
    //TODO remove these with new input system
    public final BoolAction turnLeft = new BoolAction("turn_left");
    public final BoolAction turnRight = new BoolAction("turn_right");

    public boolean turnActivated = false;
    public boolean hotbarActivated;

    public final List<Action> actions = List.of(
            jump,
            inventory,
            attack,
            use,
            sprint,
            sneak,
            resetPos,
            turn,
            hotbar,
            move,
            stand,
            menu,
            chat,
            hotbarLeft,
            hotbarRight,
            turnLeft,
            turnRight
            );

    public VanillaGameplayActionSet() {
        super("vanilla_gameplay", 0);
    }

    @Override
    public List<Action> actions() {
        return actions;
    }

    @Override
    public boolean shouldSync() {
        return !MCXRPlayClient.INSTANCE.MCXRGuiManager.isScreenOpen();
    }

    public void getDefaultBindings(HashMap<String, List<Pair<Action, String>>> map) {
        map.computeIfAbsent("/interaction_profiles/oculus/touch_controller", aLong -> new ArrayList<>()).addAll(
                List.of(
                        new Pair<>(use, "/user/hand/left/input/trigger/value"),
                        new Pair<>(attack, "/user/hand/right/input/trigger/value"),
                        new Pair<>(move, "/user/hand/left/input/thumbstick"),
                        new Pair<>(hotbar, "/user/hand/right/input/thumbstick/y"),
                        new Pair<>(turn, "/user/hand/right/input/thumbstick/x"),
                        new Pair<>(inventory, "/user/hand/left/input/y/click"),
                        new Pair<>(jump, "/user/hand/right/input/a/click"),
                        new Pair<>(sprint, "/user/hand/right/input/squeeze/value"),
                        new Pair<>(sneak, "/user/hand/left/input/squeeze/value"),
                        new Pair<>(resetPos, "/user/hand/right/input/thumbstick/click"),
                        new Pair<>(stand, "/user/hand/left/input/thumbstick/click"),
                        new Pair<>(menu, "/user/hand/left/input/menu/click"),
                        new Pair<>(chat, "/user/hand/left/input/x/click")
                )
        );
    }
}
