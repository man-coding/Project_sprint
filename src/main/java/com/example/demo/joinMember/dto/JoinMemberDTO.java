package com.example.demo.joinMember.dto;

import com.example.demo.member.entity.Member;
import com.example.demo.runningBoard.entity.Running;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class JoinMemberDTO {

	Running runningNo;
	int joinNo;
	Member runner;
}
