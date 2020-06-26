package Controller;

import Model.Unit.Listener;

public interface observer {
    void AddListener(Listener toAdd);
    void Notify();
}
