package io.sudostream.timetoteach.messages.scottish

import org.scalatest.FunSuite

class ScottishEsAndOsDataTest extends FunSuite {

  test("Basic ScottishEsAndOsDataTest") {

    val experiencesAndOutcomes: List[ScottishEsAndOsBySubSection] = List(
      ScottishEsAndOsBySubSection(
        experienceAndOutcomes = List(
          SingleScottishExperienceAndOutcome(
            code = "EXA 0-01a",
            eAndOLines = List(
              ScottishExperienceAndOutcomeLine(
                sentence = "I have experienced the energy and excitement of presenting/performing for " +
                  "audiences and being part of an audience for other people’s presentations/performances.",
                bulletPoints = Nil
              )
            )
          )
        ),
        scottishCurriculumLevel = ScottishCurriculumLevel.EARLY,
        associatedBenchmarks = List(),
        curriculumAreaName = ScottishCurriculumAreaName.EXPRESSIVE_ARTS,
        eAndOSetSectionName = "Participation in performances and presentations",
        eAndOSetSubSectionName = Option.empty,
        eAndOSetSubSectionAuxiliaryText = Option.empty,
        responsibilityOfAllPractitioners = true
      ),
      ScottishEsAndOsBySubSection(
        experienceAndOutcomes = List(
          SingleScottishExperienceAndOutcome(
            code = "EXA 1-01a",
            eAndOLines = List(
              ScottishExperienceAndOutcomeLine(
                sentence = "I have experienced the energy and excitement of presenting/performing for " +
                  "audiences and being part of an audience for other people’s presentations/performances.",
                bulletPoints = Nil
              )
            )
          )
        ),
        scottishCurriculumLevel = ScottishCurriculumLevel.FIRST,
        associatedBenchmarks = List(),
        curriculumAreaName = ScottishCurriculumAreaName.EXPRESSIVE_ARTS,
        eAndOSetSectionName = "Participation in performances and presentations",
        eAndOSetSubSectionName = Option.empty,
        eAndOSetSubSectionAuxiliaryText = Option.empty,
        responsibilityOfAllPractitioners = true
      ),ScottishEsAndOsBySubSection(
        experienceAndOutcomes = List(
          SingleScottishExperienceAndOutcome(
            code = "EXA 2-01a",
            eAndOLines = List(
              ScottishExperienceAndOutcomeLine(
                sentence = "I have experienced the energy and excitement of presenting/performing for " +
                  "audiences and being part of an audience for other people’s presentations/performances.",
                bulletPoints = Nil
              )
            )
          )
        ),
        scottishCurriculumLevel = ScottishCurriculumLevel.SECOND,
        associatedBenchmarks = List(),
        curriculumAreaName = ScottishCurriculumAreaName.EXPRESSIVE_ARTS,
        eAndOSetSectionName = "Participation in performances and presentations",
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
      (metadata) => metadata.eAndOSetSectionName == "Participation in performances and presentations")
    assert(eAndOsSatisfyingFilter.size == 3)

    println(esAndOsData.toString)

  }

}
