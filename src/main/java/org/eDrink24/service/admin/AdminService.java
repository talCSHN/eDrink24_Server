package org.eDrink24.service.admin;

import org.eDrink24.dto.admin.AdminDTO;

import java.util.HashMap;
import java.util.List;

public interface AdminService {
    public void updateStateAfterCompletedPickup(Integer ordersId);
    public List<AdminDTO> showPickupCompletedPage(int storeId);
    public List<AdminDTO> showTodayPickupPage(int storeId);
}
