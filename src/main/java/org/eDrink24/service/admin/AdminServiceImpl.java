package org.eDrink24.service.admin;

import org.eDrink24.config.AdminMapper;
import org.eDrink24.config.CustomerMapper;
import org.eDrink24.config.ProductMapper;
import org.eDrink24.dto.admin.AdminDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    AdminMapper adminMapper;
    CustomerMapper customerMapper;
    ProductMapper productMapper;
    public AdminServiceImpl(AdminMapper adminMapper, CustomerMapper customerMapper, ProductMapper productMapper) {
        this.adminMapper = adminMapper;
        this.customerMapper = customerMapper;
        this.productMapper = productMapper;
    }

    @Override
    public void updateStateAfterCompletedPickup(Integer ordersId) {
        adminMapper.ChangeStatusAndDate(ordersId);
        adminMapper.changeIsCompleted(ordersId);

        // 픽업완료처리 했을때 수량 바뀜 => 주문 시에 처리해얄듯
        // adminMapper.changeInventoryQuantity(ordersId);
    }

    @Override
    public List<AdminDTO> showPickupCompletedPage(int storeId) {
        List<AdminDTO> adminList = adminMapper.showPickupCompletedPage(storeId);
        for (AdminDTO admin : adminList) {
            Integer userId = admin.getUserId();
            Integer productId = admin.getProductId();
            admin.setUserName(customerMapper.findUserNameByUserId(userId));
            admin.setProductName(productMapper.findProductNameByProductId(productId));
        }
        return adminList;
    }

    @Override
    public List<AdminDTO> showTodayPickupPage(int storeId) {
        List<AdminDTO> adminList = adminMapper.showTodayPickupPage(storeId);
        for (AdminDTO admin : adminList) {
            Integer userId = admin.getUserId();
            Integer productId = admin.getProductId();
            admin.setUserName(customerMapper.findUserNameByUserId(userId));
            admin.setProductName(productMapper.findProductNameByProductId(productId));
        }
        return adminList;
    }
}
