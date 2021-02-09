package vn.phh.product.service;

public abstract class ClientService<T> {

    protected T client;

    public abstract void init(T clientDTO);

    public abstract T get();
}
