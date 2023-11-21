package com.homestore.user;

import com.homestore.exception.UnsuccessfulOperationException;
import com.homestore.security.auth.request.PasswordRequest;
import com.homestore.security.email.EmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public void changePassword(User user, PasswordRequest request) {
        boolean passwordMatches = checkIfPasswordMatches(user.getPassword(), request.getCurrentPassword());
        boolean checkNewPassword = Objects.equals(request.getCurrentPassword(), request.getNewPassword());

        if (!passwordMatches) {
            throw new UnsuccessfulOperationException("Wrong current password!");
        } else if (checkNewPassword) {
            throw new UnsuccessfulOperationException("New password cant be the old password!");
        }

        userRepository.changePassword(user.getEmail(), request.getNewPassword());
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isEmailValid(String email) {
        return emailValidator.test(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean checkIfPasswordMatches(String password, String currentPassword) {
        return passwordEncoder.matches(currentPassword, password);
    }
}