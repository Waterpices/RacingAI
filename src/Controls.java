import CarInputControls.InputMethod;
import processing.event.KeyEvent;
import static java.awt.event.KeyEvent.getKeyText;

import java.util.HashMap;
import java.util.HashSet;

import static processing.core.PConstants.CODED;

public class Controls {

    private final HashSet<String> inputs;
    private final HashMap<String, InputMethod> controls;

    public Controls(){
        this.inputs = new HashSet<String>();
        this.controls = new HashMap<String, InputMethod>();
    }

    public void keyPressed(KeyEvent e) {
        registerInput(e, KeyManipulation.KEY_PRESS);
    }

    public void keyReleased(KeyEvent e) {
        registerInput(e, KeyManipulation.KEY_RELEASE);
    }
    private void registerInput(KeyEvent e, KeyManipulation keyManipulation){
        String keyString;
        if (e.getKey() == CODED) {
            int keyCode = e.getKeyCode();
            keyString = "keyCode_"+ getKeyText(keyCode);
        } else {
            char c = e.getKey();
            keyString = String.valueOf(c);
        }
        System.err.println("KeyEvent :" + keyString + " " + keyManipulation );
        switch (keyManipulation){
            case KEY_PRESS -> addKeyToInput(keyString);
            case KEY_RELEASE -> removeKeyFromInput(keyString);
        }
    }

    /**
     * Sets a new control for a key pressed.
     * <p>
     * Note: a key can only perform one action, adding another action will cause the first one to be unmapped.
     *
     * @param key the key doing the action
     * @param action the action
     */
    public void addControl(String key, InputMethod action){
        this.controls.put(key, action);
    }

    public void removeControl(String key){
        this.controls.remove(key);
    }

    private void addKeyToInput(String key){
        this.inputs.add(key);
    }
    private void removeKeyFromInput(String key){
        this.inputs.remove(key);
    }

    public void simulateKeyPress(String key){
        this.inputs.add(key);
    }
    public void simulateKeyRelease(String key){
        this.inputs.add(key);
    }

    public void playControls(){
        System.err.println("input list : " + inputs);
        for(String input: inputs) {
            if(this.controls.containsKey(input)){
                try{
                    this.controls.get(input).run();
                }
                catch (NullPointerException e){
                    System.err.println("The control bind on" + input + " is not defined (was likely not properly define or deleted)");
                }
            }
        }
    }

    public void clearInputs(){
        this.inputs.clear();
    }
}
