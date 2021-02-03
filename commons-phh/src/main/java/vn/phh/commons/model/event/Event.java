package vn.phh.commons.model.event;

import java.util.HashMap;
import java.util.Map;

public class Event {

    private Enum eventAction;
    private Map<Class<?>, Object> data;

    public Event(Enum eventAction, Object[] data) {
        this.eventAction = eventAction;
        if (data.length > 0) {
            this.data = new HashMap<>();
            for (Object o : data) {
                this.data.put(o.getClass(), o);
            }
        }
    }

    public Event(Enum eventAction, Map<Class<?>, Object> data) {
        this.eventAction = eventAction;
        this.data = data;
    }

    public Enum getEventAction() {
        return eventAction;
    }

    public void setEventAction(Enum eventAction) {
        this.eventAction = eventAction;
    }

    public Map<Class<?>, Object> getData() {
        return data;
    }

    public void setData(Map<Class<?>, Object> data) {
        this.data = data;
    }
}