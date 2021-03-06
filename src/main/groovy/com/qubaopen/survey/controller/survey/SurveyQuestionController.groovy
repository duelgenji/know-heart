package com.qubaopen.survey.controller.survey

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import com.qubaopen.survey.controller.AbstractBaseController
import com.qubaopen.survey.entity.survey.Survey
import com.qubaopen.survey.entity.survey.SurveyQuestion
import com.qubaopen.survey.entity.survey.SurveyUserAnswer
import com.qubaopen.survey.entity.survey.SurveyUserQuestionnaire
import com.qubaopen.survey.entity.user.User
import com.qubaopen.survey.repository.MyRepository
import com.qubaopen.survey.repository.survey.SurveyLogicRepository
import com.qubaopen.survey.repository.survey.SurveyQuestionRepository
import com.qubaopen.survey.repository.survey.SurveyUserAnswerRepository
import com.qubaopen.survey.repository.survey.SurveyUserQuestionnaireRepository
import com.qubaopen.survey.repository.survey.SurveyUserQuestionnaireTypeRepository

@RestController
@RequestMapping("surveyQuestions")
public class SurveyQuestionController extends AbstractBaseController<SurveyQuestion, Long> {

	@Autowired
	SurveyQuestionRepository surveyQuestionRepository

	@Autowired
	SurveyLogicRepository surveyLogicRepository

	@Autowired
	SurveyUserQuestionnaireRepository surveyUserQuestionnaireRepository

	@Autowired
	SurveyUserQuestionnaireTypeRepository surveyUserQuestionnaireTypeRepository

	@Autowired
	SurveyUserAnswerRepository surveyUserAnswerRepository

	@Override
	protected MyRepository<SurveyQuestion, Long> getRepository() {
		surveyQuestionRepository
	}


	/**
	 * 查询问卷选项以及逻辑
	 * @param surveyId
	 * @return
	 */
	@RequestMapping(value = 'findBySurvey/{surveyId}', method = RequestMethod.GET)
	findBySurvey(@PathVariable long surveyId) {

		def survey = new Survey(id : surveyId),
			surveyQuestions = surveyQuestionRepository.findAllBySurvey(survey)

		def surveyLogics = []

		if (surveyQuestions) {
			surveyLogics = surveyLogicRepository.findAlLBySurveyQuestions(surveyQuestions)
		}

		def result = [
			'surveyQuestions' : surveyQuestions,
			'surveyLogics' : surveyLogics
		]
	}

	/**
	 *
	 * 保存调研问卷结果
	 * @param userId
	 * @param surveyId
	 * @param questionOptionIds
	 * @return
	 */
	@RequestMapping(value = 'saveSurveyResult', method = RequestMethod.GET)
	saveSurveyResult(@RequestParam long userId, @RequestParam long surveyId, @RequestParam String questionOptionIds) {

		def user = new User(id : userId),
			survey = new Survey(id : surveyId)


		def surveyUserQuestionnaire = new SurveyUserQuestionnaire(
			survey : survey,
			user : user,
			createDate : new Date(),
			status : SurveyUserQuestionnaire.status.ADUITING,
			transmit : SurveyUserQuestionnaire.Transmit.NOTRANSMIT
		)

		surveyUserQuestionnaire = surveyUserQuestionnaireRepository.save(surveyUserQuestionnaire)

		def ids = [],
			optionIds = questionOptionIds.split(',')

		optionIds.each {
			ids << (long)it
		}

		def userAnswers = [],
			surveyQuestionOptions = surveyQuestionRepository.findAll(ids)

		surveyQuestionOptions.each {
			def answer = new SurveyUserAnswer(
				surveyUserQuestionnaire : surveyUserQuestionnaire,
				surveyQuestion : it.surveyQuestion,
				surveyQuestionOption : it
			)
			userAnswers << answer
		}

		surveyUserAnswerRepository.save(userAnswers)

		return '{"success" : 1}'
	}

}
