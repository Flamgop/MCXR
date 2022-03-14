package net.sorenon.mcxr.play.input.actionsets;

import net.sorenon.mcxr.play.MCXRPlayClient;
import net.sorenon.mcxr.play.input.XrInput;
import net.sorenon.mcxr.play.input.actions.Action;
import net.sorenon.mcxr.play.input.actions.BoolAction;
import net.sorenon.mcxr.play.input.actions.Vec2fAction;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiActionSet extends ActionSet {

    public BoolAction pickup = new BoolAction("pickup"); //Pickup Stack, Swap Stack, Divide Drag, Pickup ALL
    public BoolAction split = new BoolAction("split"); //Split Stack, Swap Stack, Single Drag, Drop one
    public BoolAction quickMove = new BoolAction("quick_move"); //Shift + Click
    public BoolAction exit = new BoolAction("close");
    public Vec2fAction scroll = new Vec2fAction("scroll");


    public GuiActionSet() {
        super("gui", 1);
    }

    @Override
    public List<Action> actions() {
        return List.of(
                pickup,
                split,
                quickMove,
                exit,
                scroll
        );
    }

    @Override
    public boolean shouldSync() {
        return (MCXRPlayClient.INSTANCE.MCXRGuiManager.isScreenOpen() | exit.currentState | pickup.currentState) && !XrInput.vanillaGameplayActionSet.inventory.currentState;
    }

    @Override
    public void getDefaultBindings(HashMap<String, List<Pair<Action, String>>> map) {
        map.computeIfAbsent("/interaction_profiles/oculus/touch_controller", aLong -> new ArrayList<>()).addAll(
                List.of(
                        new Pair<>(pickup, "/user/hand/right/input/a/click"),
                        new Pair<>(split, "/user/hand/right/input/b/click"),
                        new Pair<>(quickMove, "/user/hand/left/input/x/click"),
                        new Pair<>(exit, "/user/hand/left/input/y/click"),
                        new Pair<>(scroll, "/user/hand/right/input/thumbstick")
                )
        );
    }
}
