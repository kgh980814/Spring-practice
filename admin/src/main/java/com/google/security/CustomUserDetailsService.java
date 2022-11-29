package com.google.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.domain.MemberVO;
import com.google.mapper.MemberMapper;
import com.google.security.domain.CustomUser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Setter(onMethod_= {@Autowired})
	private MemberMapper mapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(username);
		
		MemberVO vo = mapper.read(username);
		
		return vo == null? null : new CustomUser(vo);//vo값이 null이면 null로 리턴하고 아니면 CustomUser메소드를 리턴하겠다.
		
		
	}

}
