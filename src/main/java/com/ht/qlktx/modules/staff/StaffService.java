package com.ht.qlktx.modules.staff;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Account;
import com.ht.qlktx.entities.Staff;
import com.ht.qlktx.enums.Role;
import com.ht.qlktx.modules.account.AccountRepository;
import com.ht.qlktx.modules.staff.dtos.CreateStaffDto;
import com.ht.qlktx.modules.staff.dtos.UpdateStaffDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Staff> findAll() {
        return staffRepository.findAllByDeletedIsFalse();
    }

    public Staff findById(String staffId) {
        return staffRepository.findByIdAndDeletedIsFalse(staffId).orElseThrow(
                () -> new HttpException("Không tìm thấy người dùng", HttpStatus.BAD_REQUEST)
        );
    }

    @Transactional
    public Staff create(CreateStaffDto createStaffDto) {
        if (staffRepository.existsById(createStaffDto.getId()) || accountRepository.existsByEmail(createStaffDto.getEmail())) {
            throw new HttpException("Mã người dùng hoặc email đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        var account = Account.builder()
                .username(createStaffDto.getId())
                .password(passwordEncoder.encode(createStaffDto.getPassword()))
                .role(Role.STAFF)
                .email(createStaffDto.getEmail())
                .build();

        var staff = Staff.builder()
                .id(createStaffDto.getId())
                .account(account)
                .firstName(createStaffDto.getFirstName())
                .lastName(createStaffDto.getLastName())
                .sex(createStaffDto.getSex())
                .dateOfBirth(createStaffDto.getDateOfBirth())
                .address(createStaffDto.getAddress())
                .phone(createStaffDto.getPhone())
                .build();

        return staffRepository.save(staff);
    }

    public Staff update(String staffId, UpdateStaffDto updateStaffDto) {
        var staff = findById(staffId);

        Optional.ofNullable(updateStaffDto.getPassword()).ifPresent(password -> {
            staff.getAccount().setPassword(passwordEncoder.encode(password));
        });

        Optional.ofNullable(updateStaffDto.getEmail()).ifPresent(email -> {
            if (!email.equals(staff.getAccount().getEmail()) && accountRepository.existsByEmail(email)) {
                throw new HttpException("Email đã tồn tại", HttpStatus.BAD_REQUEST);
            }
            staff.getAccount().setEmail(email);
        });

        Optional.ofNullable(updateStaffDto.getFirstName()).ifPresent(staff::setFirstName);
        Optional.ofNullable(updateStaffDto.getLastName()).ifPresent(staff::setLastName);
        Optional.ofNullable(updateStaffDto.getSex()).ifPresent(staff::setSex);
        Optional.ofNullable(updateStaffDto.getDateOfBirth()).ifPresent(staff::setDateOfBirth);
        Optional.ofNullable(updateStaffDto.getAddress()).ifPresent(staff::setAddress);
        Optional.ofNullable(updateStaffDto.getPhone()).ifPresent(staff::setPhone);

        return staffRepository.save(staff);
    }

    public void delete(String staffId) {
        var staff = findById(staffId);

        if (staff.getAccount().getRole() == Role.ADMIN) {
            throw new HttpException("Không thể xóa người dùng với quyền ADMIN", HttpStatus.FORBIDDEN);
        }

        staff.setDeleted(true);
        staffRepository.save(staff);
    }

    public List<Staff> lookUpByIdOrName(String keyword) {
        return staffRepository.lookUpByIdOrName(keyword);
    }
}
