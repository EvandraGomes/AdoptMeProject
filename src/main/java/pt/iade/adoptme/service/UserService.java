package pt.iade.adoptme.service;


// pra interagir entre Controller e Repository
// validar os dados, criptografar a senha e salvar na BD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.repositories.UserRepository;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Mapa para armazenar tokens gerados
    private final Map<String, User> tokenStorage = new HashMap<>();

    // Registrar um novo usuário
    public void registerUser(User user) throws Exception {
        // Validar campos obrigatórios
        if (user.getName() == null || user.getName().isEmpty()) throw new Exception("O nome é obrigatório.");
        if (user.getEmail() == null || user.getEmail().isEmpty()) throw new Exception("O e-mail é obrigatório.");
        if (user.getPassword() == null || user.getPassword().isEmpty()) throw new Exception("A senha é obrigatória.");
        if (user.getPhone() == null || user.getPhone().isEmpty()) throw new Exception("O telefone é obrigatório.");
        if (user.getAddress() == null || user.getAddress().isEmpty()) throw new Exception("O endereço é obrigatório.");
        if (user.getBirthdate() == null) throw new Exception("A data de nascimento é obrigatória.");

        // Verificar se o e-mail já existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("E-mail já está em uso.");
        }

        // Não criptografar a senha, armazenar diretamente
        user.setDateRegistered(LocalDate.now());

        // Salvar usuário
        userRepository.save(user);
    }

    // Método de login com geração de token
    public String loginUser(String email, String password) throws Exception {
        // Verificar se o e-mail existe
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("E-mail inexistente."));

        // Validar a senha (comparação direta)
        if (!user.getPassword().equals(password)) {
            throw new Exception("Senha incorreta.");
        }

        // Gerar um token simples (UUID) e associar ao usuário
        String token = UUID.randomUUID().toString();
        tokenStorage.put(token, user);

        // Retornar o token
        return token;
    }

    // Método para buscar um usuário pelo token
    public User getUserByToken(String token) throws Exception {
        User user = tokenStorage.get(token);
        if (user == null) {
            throw new Exception("Token inválido ou expirado.");
        }
        return user;
    }

    // Obter todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
