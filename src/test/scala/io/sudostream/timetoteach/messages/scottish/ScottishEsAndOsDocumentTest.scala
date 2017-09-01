package io.sudostream.timetoteach.messages.scottish

import org.scalatest.FunSuite

class ScottishEsAndOsDocumentTest extends FunSuite {

  test("Basic ScottishEsAndOsDocumentTest") {
    val introductionText = "This material is for all who contribute to the education of Scotland’s children and young people. The" +
      "experiences and outcomes apply wherever learning is planned. "

    val broadEducationText = "Every child and young person in Scotland is entitled to experience a broad general education. This broad " +
      "general education takes place from the early years to the end of S3 and is represented by learning across " +
      "all of the experiences and outcomes to the third curriculum level together with those selected for study at " +
      "the fourth, as far as is consistent with each child or young person’s needs. Further information on all " +
      "learner entitlements can be found in 'Building the Curriculum 3: A framework for learning and teaching'."

    val introductionParagraphs: List[EsAndOsIntroductionParagraph] = List(
      EsAndOsIntroductionParagraph("Introduction", introductionText),
      EsAndOsIntroductionParagraph("A broad general education", broadEducationText
      )
    )

    val mapOfLevelsToStage: Map[String, String] = Map(
      "EARLY" -> "The pre-school years and P1, or later for some.",
      "FIRST" -> "To the end of P4, but earlier or later for some.",
      "SECOND" -> "To the end of P7, but earlier or later for some.",
      "THIRD" -> "and Fourth S1 to S3, but earlier for some. The fourth level broadly equates to SCQF level 4.",
      "FOURTH" -> "phase S4 to S6, and college or other means of study"
    )

    //// Lets build a small example
    val feelingsStatement = ExperienceAndOutcomeStatement(
      eAndOText = "I am aware of and able to express my feelings and am developing the ability to talk about them.",
      stage = List(
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.EARLY, eAndOCode = "HWB 0-01a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.FIRST, eAndOCode = "HWB 1-01a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.SECOND, eAndOCode = "HWB 2-01a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.THIRD, eAndOCode = "HWB 3-01a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.FOURTH, eAndOCode = "HWB 4-01a")
      )
    )

    val emotionStatement = ExperienceAndOutcomeStatement(
      eAndOText = "I know that we all experience a variety of thoughts and emotions that affect" +
        " how we feel and behave and I am learning ways of managing them.",
      stage = List(
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.EARLY, eAndOCode = "HWB 0-02a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.FIRST, eAndOCode = "HWB 1-02a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.SECOND, eAndOCode = "HWB 2-02a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.THIRD, eAndOCode = "HWB 3-02a"),
        CurriculumCodeAndLevel(curriculumLevel = CurriculumLevel.FOURTH, eAndOCode = "HWB 4-02a")
      )
    )

    val eAndOSetSubSection: EAndOSetSubSection = EAndOSetSubSection(
      eAndOSetSubSectionName = "Mental and emotional wellbeing",
      experienceAndOutcomeStatements = List(feelingsStatement, emotionStatement)
    )

    val theEAndOSetSection: EAndOSetSection = EAndOSetSection(
      eAndOSetSectionName = "Mental, emotional, social and physical wellbeing",
      eAndOSetSubSection = List(eAndOSetSubSection)
    )

    val curriculumAreas = List(
      CurriculumArea(
        curriculumAreaName = "Health And Wellbeing",
        principlesAndPractice = List(
          PrincipleAndPracticeParagraph("DEFAULT",
            "Curriculum for Excellence has an important role to play in promoting the health and wellbeing of" +
              " children and young people and of all of those in the educational communities to which they belong. " +
              "This paper is intended to support discussion and planning between practitioners in all sectors" +
              " and services and in local authorities. "),
          PrincipleAndPracticeParagraph("What are the main purposes of learning in health and wellbeing?",
            "Learning in health and wellbeing ensures that children and young people develop the knowledge" +
              " and understanding, skills, capabilities and attributes which they need for mental, emotional," +
              " social and physical wellbeing now and in the future. ")
        ),
        eAndOSet = List(
          EAndOSet(
            eAndOSetName = "DEFAULT",
            eAndOSetSection = List(theEAndOSetSection)
          )
        )
      )
    )

    val esAndOs = ScottishEsAndOsDocument(
      introductionParagraphs,
      mapOfLevelsToStage,
      curriculumAreas
    )

    assert(esAndOs.introduction.head.text == introductionText)
    assert(esAndOs.mapCurriculumLevelsToStage("SECOND") == "To the end of P7, but earlier or later for some.")
    assert(esAndOs.curriculumAreas.head.curriculumAreaName == "Health And Wellbeing")
    assert(esAndOs.curriculumAreas.head.principlesAndPractice.size == 2)

    // Here we are just checking deep in the structure that the first statement is as expected.
    assert(
      esAndOs.curriculumAreas.head.eAndOSet.head.eAndOSetSection.
        head.eAndOSetSubSection.head.experienceAndOutcomeStatements.head.eAndOText ==
        "I am aware of and able to express my feelings and am developing the ability to talk about them.")
  }

}
