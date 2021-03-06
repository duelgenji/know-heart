package com.qubaopen.survey.controller.interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubaopen.survey.controller.AbstractBaseController;
import com.qubaopen.survey.entity.interest.InterestResultOption;
import com.qubaopen.survey.repository.MyRepository;
import com.qubaopen.survey.repository.interest.InterestResultOptionRepository;

@RestController
@RequestMapping("interestResultOptions")
public class InterestResultOptionController extends AbstractBaseController<InterestResultOption, Long> {

	@Autowired
	private InterestResultOptionRepository interestResultOptionRepository;

	@Override
	protected MyRepository<InterestResultOption, Long> getRepository() {
		return interestResultOptionRepository;
	}

}
