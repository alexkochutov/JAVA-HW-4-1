package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

import java.rmi.AlreadyBoundException;

public class Repository {
    private Product[] dataStorage = new Product[0];

    public Product[] getDataStorage() {
        return dataStorage;
    }

    public void setDataStorage(Product[] dataStorage) {
        this.dataStorage = dataStorage;
    }

    public void save(Product item) throws RuntimeException {
        if (findByID(item.getId()) != null) {
            throw new AlreadyExistsException("Element with id: " + item.getId() + " already exists");
        } else {
            Product[] tempStorage = new Product[dataStorage.length + 1];
            System.arraycopy(dataStorage, 0, tempStorage, 0, dataStorage.length);
            tempStorage[tempStorage.length - 1] = item;
            dataStorage = tempStorage;
        }
    }

    public Product[] findAll() {
        return getDataStorage();
    }

    public Product findByID(int id) {
        for (Product product : this.getDataStorage()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeByID(int id) throws RuntimeException {
        if ((dataStorage.length == 0) || (findByID(id) == null)) {
                throw new NotFoundException("Element with id: " + id + " not found");
            } else {
            Product[] tempStorage = new Product[dataStorage.length - 1];
            int tempIndex = 0;

            for (int i = 0; i < dataStorage.length; i++) {
                if (dataStorage[i].getId() != id) {
                    tempStorage[tempIndex] = dataStorage[i];
                    tempIndex++;
                }
            }
            setDataStorage(tempStorage);
        }
    }
}