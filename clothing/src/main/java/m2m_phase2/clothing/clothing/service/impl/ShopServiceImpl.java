package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.model.ShopM;
import m2m_phase2.clothing.clothing.repository.ShopRepo;
import m2m_phase2.clothing.clothing.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopRepo shopRepo;

    @Override
    public ShopM findShopByUser(String email) {
        return ShopM.convertShopEToShopM(shopRepo.findShopByUser(email)) ;   }

}