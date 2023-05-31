package doanlehyvong.demo.services;

import doanlehyvong.demo.model.User;
import doanlehyvong.demo.repository.IRoleRepository;
import doanlehyvong.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }
}