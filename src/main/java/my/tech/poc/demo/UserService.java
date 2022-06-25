package my.tech.poc.demo;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private CovidAPI covidAPI;
    private AddressAPI addressAPI;
    private UserRepository userRepository;

    public List<UserResponseDTO> findAllWithCovidInfo() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> {
                    UserResponseDTO dto = UserMapper.toDTO(user);
                    dto.setCovidInfo(this.covidAPI.getInfoByUF(user.getState()));
                    return dto;
                }).collect(Collectors.toList());
    }

    public UserResponseDTO findByIdWithCovidInfo(String id) {
        User user = this.userRepository.findById(id).orElseThrow();
        UserResponseDTO dto = UserMapper.toDTO(user);
        dto.setCovidInfo(this.covidAPI.getInfoByUF(user.getState()));
        return dto;
    }

    public User create(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreatedAt(LocalDateTime.now());

        AddressDTO address = this.addressAPI.findByCep(user.getZipCode());
        BeanUtils.copyProperties(address, user);

        return this.userRepository.save(user);
    }
}
