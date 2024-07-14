import java.util.HashMap;
import java.util.HashSet;

public class Controls {
    private HashSet<String> inputs;
    private HashMap<String,InputMethod> controls;

    public Controls(){
        this.inputs = new HashSet<String>();
        this.controls = new HashMap<String,InputMethod>();
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

    private void updateInputs(){
        //TODO update input set
    }

    public void playControls(){
        this.updateInputs();
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
}
