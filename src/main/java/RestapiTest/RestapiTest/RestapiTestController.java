package RestapiTest.RestapiTest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestapiTestController {

    @GetMapping("/users")
    public CreateForm getUser(@RequestParam(value = "userName") String userName,
                                 @RequestParam(value = "birthdate") String birthdate,
                                 @RequestParam(value = "pin") int pin) {
        // 取得処理は省略
        return new CreateForm(userName, birthdate, pin);
    }

    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody @Validated CreateForm form, BindingResult result,
                                                          UriComponentsBuilder builder) {

        //バリデーションでエラーがでたら、エラーメッセージをレスポンスする
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(400).body(errors);
        }

        // 登録処理は省略
        URI url = builder.path("/users/id") // id部分は実際に登録された際に発⾏したidを設定する
                .build().toUri();

        return ResponseEntity.created(url).body(Map.of("message", "user successfully created"));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable("id") int id, @RequestBody @Validated UpdateForm form,
                                                          BindingResult result) {

        //バリデーションでエラーがでたら、エラーメッセージをレスポンスする
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(400).body(errors);
        }

        // 更新処理は省略
        return ResponseEntity.ok(Map.of("message", "user successfully updated"));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("id") int id) {
        // 削除処理は省略
        return ResponseEntity.ok(Map.of("message", "user successfully deleted"));
    }
}
