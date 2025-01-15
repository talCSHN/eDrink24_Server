package org.eDrink24.service.point;

import org.eDrink24.config.PointMapper;
import org.eDrink24.dto.point.PointDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    PointMapper pointMapper;

   public PointServiceImpl(PointMapper pointMapper) {
      this.pointMapper = pointMapper;
   }

    @Override
    public Integer showTotalPoint(Integer userId) {
        return pointMapper.showTotalPoint(userId);
    }

    @Override
    public List<PointDTO> showPoint(Integer userId) {
       return pointMapper.showPoint(userId);
    }

}
