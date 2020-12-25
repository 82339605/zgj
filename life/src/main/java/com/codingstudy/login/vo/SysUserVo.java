package com.codingstudy.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo {

    String id;
    String username;
    String password;
    String des;
    String phone;


}
