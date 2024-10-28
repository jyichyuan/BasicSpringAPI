package springapi.springRestApi.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springapi.springRestApi.Model.User;
import springapi.springRestApi.Services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/v1/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/page")
    public String getPage(){
        return "Hello, welcome to user api";
    }

    //GET
    @Operation(summary = "Get all users")
    @GetMapping("/list")
    public List<User> getAllUsers(){
        return service.findAllUser();
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        Optional<User> userOptional = service.findUserById(id);
        return userOptional.isPresent() ? ResponseEntity.ok(userOptional) : ResponseEntity.status(404).body("User not found");
    }

    @Operation(summary = "Greet user by ID")
    @GetMapping("/greet/{id}")
    public ResponseEntity<String> greetUserById(@PathVariable String id){
        Optional<User> userOptional = service.findUserById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            String message = "Hi, welcome " + user.getFirstName() + " " + user.getLastName()
                    + " your occupation is " + user.getOccupation();
            return ResponseEntity.ok(message);
        }
        else{
            return ResponseEntity.status(404).body("User not found");
        }
    }

    //POST
    @Operation(summary = "Create a new user")
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    //UPDATE
    @Operation(summary = "Update user by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User updateUser){
        User user = service.updateUser(id, updateUser);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(404).body("User not found");
    }

    //DELETE
    @Operation(summary = "Delete user by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        service.deleteUser(id);
        return ResponseEntity.ok("User removed");
    }
}
