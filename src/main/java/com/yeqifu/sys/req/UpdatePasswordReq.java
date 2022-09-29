package com.yeqifu.sys.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 更改用户密码req
 */
@Data
public class UpdatePasswordReq {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    /**
     * 确认新密码
     */
    @NotBlank(message = "确认新密码不能为空")
    private String confirmPassword;
}
