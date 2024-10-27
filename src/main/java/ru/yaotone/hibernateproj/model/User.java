package ru.yaotone.hibernateproj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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
