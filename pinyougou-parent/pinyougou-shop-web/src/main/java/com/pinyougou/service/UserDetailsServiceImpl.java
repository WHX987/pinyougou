package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	private SellerService sellerService;

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("经过了UserDetailsServiceImpl=" +username);
		// 创建角色列表
		List<GrantedAuthority> authorities = new ArrayList<>();
		// 添加角色
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		// 根据username到数据查询数据
		TbSeller seller = sellerService.findOne(username);
		System.out.println(seller.getName()+"ttt"+seller.getPassword());
		// 用户状态为1的，代表审核通过。
		if (seller != null &&  seller.getStatus().equals("1")) {
			return new User(username, seller.getPassword(), authorities);
		}else {
			return null;
		}
	}

}
