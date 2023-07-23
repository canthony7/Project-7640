package com.hkbu.project7640.exception;

import com.hkbu.project7640.dto.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Chet
 * @date 11/2/2023 3:41 pm
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GlobalException extends RuntimeException {

    private ResponseEnum responseEnum;

}
