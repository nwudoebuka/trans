package com.agonaika.data;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private final List<Object> mListeners = new ArrayList<Object>(4);

    public synchronized <L> List<L> getListeners(Class<L> clazz) {
        List<L> resultListeners = new ArrayList<L>();

        for (Object o : mListeners) {
            if (clazz.isInstance(o)) {
                resultListeners.add(clazz.cast(o));
            }
        }

        return resultListeners;
    }

    public <L> boolean addListener(L listener) {
        if (!mListeners.contains(listener)) {
            return mListeners.add(listener);
        }

        return false;
    }

    public <L> void removeListener(L listener) {
        if (mListeners.contains(listener)) {
            mListeners.remove(listener);
        }
    }

    public <L> void removeListeners(Class<L> clazz) {
        mListeners.removeAll(getListeners(clazz));
    }

    public void removeAll() {
        mListeners.clear();
    }

}
