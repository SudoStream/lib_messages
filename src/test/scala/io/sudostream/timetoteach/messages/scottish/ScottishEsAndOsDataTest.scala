package io.sudostream.timetoteach.messages.scottish

import io.sudostream.timetoteach.messages.systemwide.model.ScottishCurriculumLevelWrapper
import org.scalatest.FunSuite

class ScottishEsAndOsDataTest extends FunSuite {

  test("Basic ScottishEsAndOsDataTest") {

    val experiencesAndOutcomes: List[ScottishEsAndOsMetadata] = List(
      ScottishEsAndOsMetadata(
        experienceAndOutcome = List(
          ScottishExperienceAndOutcome(
            sentence = "I have experienced the energy and excitement of presenting/performing for " +
              "audiences and being part of an audience for other people’s presentations/performances.",
            bulletPoints = List()
          )
        ),
        codes = List("EXA 0-01a", "EXA 1-01a", "EXA 2-01a"),
        curriculumLevels = List(
          ScottishCurriculumLevelWrapper(ScottishCurriculumLevel.EARLY),
          ScottishCurriculumLevelWrapper(ScottishCurriculumLevel.FIRST),
            ScottishCurriculumLevelWrapper(ScottishCurriculumLevel.SECOND)
        ),
        curriculumAreaName = ScottishCurriculumAreaName.EXPRESSIVE_ARTS,
        eAndOSetName = Option.empty,
        eAndOSetSectionName = "Participation in performances and presentations",
        eAndOSetSubSectionName = Option.empty,
        eAndOSetSubSectionAuxiliaryText = Option.empty,
        responsibilityOfAllPractitioners = true
      ),
      ScottishEsAndOsMetadata(
        experienceAndOutcome = List(
          ScottishExperienceAndOutcome(
            sentence = "I have used the skills I have developed in the expressive arts to contribute to a " +
              "public presentation/performance.",
            bulletPoints = List()
          )
        ),
        codes = List("EXA 3-01a"),
        curriculumLevels = List(
          ScottishCurriculumLevelWrapper(ScottishCurriculumLevel.THIRD)
        ),
        curriculumAreaName = ScottishCurriculumAreaName.EXPRESSIVE_ARTS,
        eAndOSetName = Option.empty,
        eAndOSetSectionName = "Participation in performances and presentations",
        eAndOSetSubSectionName = Option.empty,
        eAndOSetSubSectionAuxiliaryText = Option.empty,
        responsibilityOfAllPractitioners = true
      ),
      ScottishEsAndOsMetadata(
        experienceAndOutcome = List(
          ScottishExperienceAndOutcome(
            sentence = "In everyday activity and play, I explore and make choices to develop my learning and interests. " +
              "I am encouraged to use and share my experiences.",
            bulletPoints = List()
          )
        ),
        codes = List("HWB 0-19a"),
        curriculumLevels = List(
          ScottishCurriculumLevelWrapper(ScottishCurriculumLevel.EARLY)
        ),
        curriculumAreaName = ScottishCurriculumAreaName.HEALTH_AND_WELLBEING,
        eAndOSetName = Option.empty,
        eAndOSetSectionName = "Planning for choices and changes",
        eAndOSetSubSectionName = Option.empty,
        eAndOSetSubSectionAuxiliaryText = Option.empty,
        responsibilityOfAllPractitioners = true
      )
    )

    val esAndOsData = ScottishEsAndOsData(
      allExperiencesAndOutcomes = experiencesAndOutcomes
    )

    assert(esAndOsData.allExperiencesAndOutcomes.size == 3)

    val eAndOsSatisfyingFilter = esAndOsData.allExperiencesAndOutcomes.filter(
      (metadata) => metadata.eAndOSetSectionName == "Planning for choices and changes")
    assert(eAndOsSatisfyingFilter.size == 1)

    println(esAndOsData.toString)

  }

}
