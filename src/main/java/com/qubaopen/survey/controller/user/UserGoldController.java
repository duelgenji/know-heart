package com.qubaopen.survey.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubaopen.survey.controller.AbstractBaseController;
import com.qubaopen.survey.entity.user.UserGold;
import com.qubaopen.survey.repository.MyRepository;
import com.qubaopen.survey.repository.user.UserGoldRepository;

@RestController
@RequestMapping("userGolds")
public class UserGoldController extends AbstractBaseController<UserGold, Long> {

	@Autowired
	private UserGoldRepository userGoldRepository;

	@Override
	protected MyRepository<UserGold, Long> getRepository() {
		return userGoldRepository;
	}

}
