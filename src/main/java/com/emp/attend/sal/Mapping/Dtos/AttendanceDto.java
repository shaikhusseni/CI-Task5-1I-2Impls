package com.emp.attend.sal.Mapping.Dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class AttendanceDto {


    private Long attendId;

    private String attendStatus;

    private Date attendCreate;


}
