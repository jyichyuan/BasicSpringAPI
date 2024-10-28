package springapi.springRestApi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springapi.springRestApi.Model.User;
import springapi.springRestApi.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    //GET all users
    public List<User> findAllUser(){
        return repository.findAll();
    }

    //Get user by id
    public Optional<User> findUserById(String id){
        return repository.findById(id);
    }

    //POST create a new user
    public User createUser(User user){
        return repository.save(user);
    }

    //UPDATE a user by id
    public User updateUser(String id,User userUpdate){
        Optional<User> optional = repository.findById(id);

        if(optional.isPresent()){
            User user = optional.get();
            user.setFirstName(userUpdate.getFirstName());
            user.setLastName(userUpdate.getLastName());
            user.setOccupation(userUpdate.getOccupation());
            return repository.save(user);
        }
        else{
            return null;
        }
    }

    //DELETE a user by id
    public void deleteUser(String id){
        repository.deleteById(id);
    }
}
