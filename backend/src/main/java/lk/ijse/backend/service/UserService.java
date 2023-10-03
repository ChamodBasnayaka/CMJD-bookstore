package lk.ijse.backend.service;

import org.springframework.stereotype.Service;

import lk.ijse.backend.dto.UserProfileDTO;
import lk.ijse.backend.entity.User;

@Service
public interface UserService {
    User updateUser(UserProfileDTO userProfileDTO, Long id);

    User getUserById(Long id);
}
