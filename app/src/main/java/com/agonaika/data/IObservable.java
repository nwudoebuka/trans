package com.agonaika.data;

public interface IObservable {
    <L> void addListener(L listener);

    <L> void removeListener(L listener);

    <L> void removeListeners(Class<L> clazz);

    void removeAllListeners();
}
