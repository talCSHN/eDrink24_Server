package org.eDrink24.service.dibs;

import org.eDrink24.config.CouponMapper;
import org.eDrink24.config.DibMapper;
import org.eDrink24.dto.coupon.CouponDTO;
import org.eDrink24.dto.dibs.DibDTO;
import org.eDrink24.service.coupon.CouponService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DibServiceImpl implements DibService {

    DibMapper dibMapper;

   public DibServiceImpl(DibMapper dibMapper) {
      this.dibMapper = dibMapper;
   }

    @Override
    public void addDibs(DibDTO dibDTO) {
        dibMapper.addDibs(dibDTO);
    }

    @Override
    public void addCountDibs(Integer productId) {
        dibMapper.addCountDibs(productId);
    }

    @Override
    public void cancelDIb(Integer userId, Integer productId) {
        dibMapper.cancelDIb(userId, productId);
    }

    @Override
    public void deleteCountDibs(Integer productId) {
        dibMapper.deleteCountDibs(productId);
    }

    @Override
    public List<DibDTO> showAllDibs(Integer userId) {
        return dibMapper.showAllDibs(userId);
    }
}
