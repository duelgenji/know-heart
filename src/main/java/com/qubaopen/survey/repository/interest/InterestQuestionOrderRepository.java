package com.qubaopen.survey.repository.interest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qubaopen.survey.entity.interest.InterestQuestion;
import com.qubaopen.survey.entity.interest.InterestQuestionOrder;
import com.qubaopen.survey.repository.MyRepository;

public interface InterestQuestionOrderRepository extends MyRepository<InterestQuestionOrder, Long> {

	@Query("from InterestQuestionOrder iqo where iqo.interestQuestion in (:interestQuestions)")
	List<InterestQuestionOrder> findByInterestQuestion(@Param("interestQuestions") List<InterestQuestion> interestQuestions);
}
