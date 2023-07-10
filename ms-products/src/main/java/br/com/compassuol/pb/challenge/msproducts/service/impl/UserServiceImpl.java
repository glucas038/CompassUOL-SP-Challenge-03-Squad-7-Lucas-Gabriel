package br.com.compassuol.pb.challenge.msproducts.service.impl;

import br.com.compassuol.pb.challenge.msproducts.exception.ProductsAPIException;
import br.com.compassuol.pb.challenge.msproducts.exception.ResourceNotFoundException;
import br.com.compassuol.pb.challenge.msproducts.payload.UserDTO;
import br.com.compassuol.pb.challenge.msproducts.entity.Role;
import br.com.compassuol.pb.challenge.msproducts.entity.User;
import br.com.compassuol.pb.challenge.msproducts.repository.RoleRepository;
import br.com.compassuol.pb.challenge.msproducts.repository.UserRepository;
import br.com.compassuol.pb.challenge.msproducts.service.UserService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           ModelMapper mapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        if (!userRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("User", "email", email);
        }

        User user = userRepository.findByEmail(email).get();
        UserDTO userDTO = mapToDto(user);
        userDTO.setPassword(user.getPassword());

        return Optional.ofNullable(userDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new ProductsAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        Set<Role> roles = userDTO.getRoles().stream()
                .map(role -> {
                    Role foundRole = roleRepository.findById(role.getId()).orElseThrow(() ->
                            new ResourceNotFoundException("Role", "id", role.getId()));
                    return foundRole;
                })
                .collect(Collectors.toSet());

        User user = mapToEntity(userDTO);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User newUser = userRepository.saveAndFlush(user);

        return mapToDto(newUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("User", "id", userId));
        return mapToDto(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User currentUser = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("User", "id", userId));
        Optional<List<User>> userList = userRepository.findAllByEmail(userDTO.getEmail());

        userList.get().remove(currentUser);

        if ((userList.get().size()) >= 1){
            throw new ProductsAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = mapToEntity(userDTO);

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        currentUser.setRoles(user.getRoles());

        User updateUser = userRepository.save(currentUser);

        return mapToDto(updateUser);
    }

    private UserDTO mapToDto(User user){
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.typeMap(User.class, UserDTO.class).addMappings(mapper ->
                mapper.skip(UserDTO::setPassword));
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    private User mapToEntity(UserDTO userDTO){
        User user = mapper.map(userDTO, User.class);
        return user;
    }

}
