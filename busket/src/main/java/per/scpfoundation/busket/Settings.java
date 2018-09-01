package per.scpfoundation.busket;

public class Settings {

    private boolean isListenClick = true;
    private boolean isListenTouch = false;
    private boolean isObserveBattery = true;

    public Settings(Object source){
        if (source == null) {

        }
    }
    public boolean isListenClick(){
        return isListenClick;
    }
    public boolean isListenTouch(){
        return isListenTouch;
    }
}
