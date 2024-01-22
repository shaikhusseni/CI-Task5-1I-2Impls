package com.emp.attend.sal.Mapping.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ATTENDANCE")
@Entity
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTEN_ID")
    private Long attendId;

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    @Column(name = "ATTEN_STATUS")
    private String attendStatus;

    @Column(name = "ATTEN_CREATED_DATE")
    private Date attendCreate;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID")
    private Employee employee;


}
