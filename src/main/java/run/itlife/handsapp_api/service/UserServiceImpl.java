package run.itlife.handsapp_api.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import run.itlife.handsapp_api.entity.User;
import run.itlife.handsapp_api.repository.UserRepository;


// Уровень обслуживания
// Класс, реализующий интерфейс, который отвечает за логику создания пользователей, поиск пользователей
@Service
@Transactional
public class UserServiceImpl implements UserService {

    // сервисы в свою очередь включают репозиторий
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }


}
