package com.app.shukdash.Presenters;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by danielT on 11/09/2015.
 */
public interface IOnTaskSave {
    void register(Observer o);
    void unRegister(Observer o);
    void notifyObserver();
}
