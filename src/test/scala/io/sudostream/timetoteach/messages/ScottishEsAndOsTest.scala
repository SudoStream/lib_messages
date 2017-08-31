package io.sudostream.timetoteach.messages

import org.scalatest.FunSuite

class ScottishEsAndOsTest extends FunSuite {

  test("Basic ScottishEsAndOsTest") {
    val introductionText = "This material is for all who contribute to the education of Scotland’s children and young people. The" +
      "experiences and outcomes apply wherever learning is planned. "

    val broadEducationText = "Every child and young person in Scotland is entitled to experience a broad general education. This broad " +
      "general education takes place from the early years to the end of S3 and is represented by learning across " +
      "all of the experiences and outcomes to the third curriculum level together with those selected for study at " +
      "the fourth, as far as is consistent with each child or young person’s needs. Further information on all " +
      "learner entitlements can be found in 'Building the Curriculum 3: A framework for learning and teaching'."

    val introductionParagraphs = List(
      EsAndOsIntroductionParagraph("Introduction", introductionText),
      EsAndOsIntroductionParagraph("A broad general education", broadEducationText
      )
    )

    val mapOfLevelsToStage = Map(
      "EEARLY" -> "The pre-school years and P1, or later for some.",
      "FIRST" -> "To the end of P4, but earlier or later for some.",
      "SECOND" -> "To the end of P7, but earlier or later for some.",
      "THIRD" -> "and Fourth S1 to S3, but earlier for some. The fourth level broadly equates to SCQF level 4.",
      "FOURTH" -> "phase S4 to S6, and college or other means of study"
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
        )
      )
    )

    val esAndOs = ScottishEsAndOs(
      introductionParagraphs,
      mapOfLevelsToStage,
      curriculumAreas
    )

    assert(esAndOs.introduction.head.text == introductionText)
    assert(esAndOs.mapCurriculumLevelsToStage("Second") == "To the end of P7, but earlier or later for some.")
    assert(esAndOs.curriculumAreas.head.curriculumAreaName == "Health And Wellbeing")
    assert(esAndOs.curriculumAreas.head.principlesAndPractice.size == 2)
  }

}
