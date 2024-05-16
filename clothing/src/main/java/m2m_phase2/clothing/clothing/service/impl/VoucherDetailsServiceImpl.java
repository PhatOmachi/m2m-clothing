package m2m_phase2.clothing.clothing.service.impl;

import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherDetailsE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.repository.VoucherDetailsRepo;
import m2m_phase2.clothing.clothing.repository.VoucherRepo;
import m2m_phase2.clothing.clothing.service.VoucherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

import static m2m_phase2.clothing.clothing.data.variable.StaticVariable.sessionEmail;

@Service
public class VoucherDetailsServiceImpl implements VoucherDetailsService {
    @Autowired
    private VoucherDetailsRepo voucherDetailsRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public byte saveVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        voucherDetailsRepo.insertVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return 1;
    }

    @Override
    public byte deleteVoucherDetails(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        voucherDetailsRepo.deleteVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return 1;
    }

    @Override
    public boolean isVoucherDetailsExist(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        var voucherDetails = voucherDetailsRepo.getVoucherDetailsByVoucherIDAndUserID(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
        return Objects.nonNull(voucherDetails);
    }

    @Override
    public byte saveVoucherForUser(VoucherDetailsDto voucherDetailsDto) throws SQLException {
        if (sessionEmail == null) {
            // Xử lý khi người dùng chưa đăng nhập
            return -1;
        }
        // Kiểm tra nếu chuỗi loggedInUser không rỗng
        if (sessionEmail != null) {
            // Tìm người dùng bằng email
            var user = userRepo.findByEmail(sessionEmail);
            if (user != null) {
                voucherDetailsRepo.insertVoucherDetails(voucherDetailsDto.getVoucher().getVoucherID(),voucherDetailsDto.getUser().getId());
                return 1;
            }
        }

        // Xử lý khi không tìm thấy người dùng hoặc có lỗi xảy ra
        return -1;
    }

}
