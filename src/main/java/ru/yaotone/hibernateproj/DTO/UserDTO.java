package ru.yaotone.hibernateproj.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotEmpty(message = "Строка не должна быть пустой")
    @Size(min = 2, message = "Имя должно быть длиннее 2 символов")
    @Size(max = 24, message = "Имя должно быть короче 24 символов")
    private String name;

    @NotEmpty(message = "Строка не должна быть пустой")
    @Size(min = 2, message = "Фамилия должна быть длиннее 2 символов")
    @Size(max = 30, message = "Фамилия должна быть короче 30 символов")
    private String surname;

    @Min(value = 12, message = "Вы должны быть старше 12 лет")
    @Max(value = 100, message = "Возраст должен быть меньше 101")
    private int age;

}
