package org.eDrink24.repository.store;

import org.eDrink24.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    public List<Store> findAll();
    public Store findByStoreId(int storeId);
}
