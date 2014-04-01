package net.acardenas.rest.domain;

import java.util.List;

/**
 * Created by acardenas on 4/1/14.
 */
public class ItemsWrapper<T>
{
    private List<T> items;

    public List<T> getItems()
    {
        return items;
    }

    public void setItems(List<T> items)
    {
        this.items = items;
    }
}
