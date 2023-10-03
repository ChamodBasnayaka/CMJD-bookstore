package lk.ijse.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lk.ijse.backend.dto.UserProfileDTO;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @org.springframework.beans.factory.annotation.Value("${upload.derectory}")
    private String uploadDerectory;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(UserProfileDTO userProfileDTO, Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            MultipartFile file = userProfileDTO.getProfileImage();
            String filename = file.getOriginalFilename();
            String filepath = uploadDerectory + File.separator + filename;
            try {
                file.transferTo(new File(filepath));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            existingUser.setProfileImage(filename);
            return userRepository.save(existingUser);
        }
        return null;

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found by Id:" + id));
    }

}
