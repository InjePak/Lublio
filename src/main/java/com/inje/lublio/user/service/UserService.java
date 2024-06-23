package com.inje.lublio.user.service;

import com.inje.lublio.user.dto.UserSignInRequest;
import com.inje.lublio.user.dto.UserSignUpRequest;
import com.inje.lublio.user.dto.UserUpdateRequest;
import com.inje.lublio.user.exception.DuplicatedEmailException;
import com.inje.lublio.user.exception.NotFoundUserException;
import com.inje.lublio.user.exception.UnmatchedPasswordException;
import com.inje.lublio.user.model.User;
import com.inje.lublio.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException(String.format("User not found: %s", userId)));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException(String.format("Email not found: %s", email)));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /***
     * Sign-Up
     */
    @Transactional
    public Long signUp(UserSignUpRequest userSignUpRequest) {
        checkDuplicatedEmail(userSignUpRequest.getEmail());
        User user = User.builder()
                .email(userSignUpRequest.getEmail())
                .password(userSignUpRequest.getPassword())
                .firstName(userSignUpRequest.getFirstName())
                .lastName(userSignUpRequest.getLastName())
                .build();
        User savedUser = userRepository.save(user);
        return savedUser.getUserId();
    }

    /***
     * Sign-In
     */
    @Transactional
    public Long signIn(UserSignInRequest userSignInRequest) {
        User user = this.findByEmail(userSignInRequest.getEmail());
        checkMatchedPassword(userSignInRequest.getPassword(), user.getPassword());
        user.modifyDeviceToken(userSignInRequest.getDeviceToken());
        return user.getUserId();
    }

    /***
     * Prevent duplication of an Email
     */
    private void checkDuplicatedEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicatedEmailException(String.format("[ %s ]" + " already exist.", email));
        }
    }

    /***
     * Check password
     */
    private void checkMatchedPassword(String signinPassword, String userPassword) {
        if (!signinPassword.equals(userPassword)) {
            throw new UnmatchedPasswordException(String.format("Password is not matched."));
        }
    }

    /***
     * Update the user information
     */
    @Transactional
    public Long update(Long userId, UserUpdateRequest userUpdateRequest) {
        User foundUser = this.findById(userId);
        User updatedUser = foundUser.updateUserInfo(
                userUpdateRequest.getFirstName(),
                userUpdateRequest.getLastName(),
                userUpdateRequest.getPassword()
        );
        return updatedUser.getUserId();
    }

    /***
     * Delete an user
     */
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

}
