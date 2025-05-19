package org.example.yugong.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qiaobao
 * @since 2025/5/19
 */
@Data
public class User implements Serializable {
    private String name;
    private Integer age;
}
