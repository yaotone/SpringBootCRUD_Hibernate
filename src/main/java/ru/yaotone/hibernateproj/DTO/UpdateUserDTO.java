package ru.yaotone.hibernateproj.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private Long id;

    @NotEmpty(message = "Строка не должна быть пустой")
    @Size(min = 2, message = "Имя должно быть длиннее 2 символов")
    @Size(max = 24, message = "Имя должно быть короче 24 символов")
    private String name;

    @NotEmpty(message = "Строка не должна быть пустой")
    @Size(min = 2, message = "Фамилия должна быть длиннее 2 символов")
    @Size(max = 30, message = "Фамилия должна быть короче 30 символов")
    private String surname;

    @Email
    @NotEmpty(message = "Строка не должна быть пустой")
    @Size(min = 4, message = "Почта должна быть длиннее 4 символов")
    @Size(max = 30, message = "Почта должна быть короче 30 символов")
    private String email;
}
