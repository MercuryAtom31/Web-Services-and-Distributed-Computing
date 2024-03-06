package com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Inventory findInventoryByInventoryIdentifier_InventoryId(String inventoryId);
}
