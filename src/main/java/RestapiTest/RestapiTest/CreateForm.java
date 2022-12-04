package RestapiTest.RestapiTest;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CreateForm {
    @NotBlank
    @Length(max = 20)
    private final String userName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$")//YYYY/mm/ddの形式。mmは01〜12、ddは01〜31でないとエラー
    private final String birthdate;

    @Positive//数値が正でないとエラー
    private final int pin;

    public CreateForm(String userName, String birthdate, int pin) {
        this.userName = userName;
        this.birthdate = birthdate;
        this.pin = pin;
    }

    public String getUserName() {
        return userName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public int getPin() {
        return pin;
    }
}
