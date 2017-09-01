package io.sudostream.timetoteach.messages.scottish

import org.scalatest.FunSuite

class ScottishEsAndOsDataTest extends FunSuite {

  test("Basic ScottishEsAndOsDocumentTest") {

    val experienceAndOutcomeToMetadataMap: Map[String, ScottishEsAndOsMetadata] = Map(

      "I have experienced the energy and excitement of presenting/performing for " +
        "audiences and being part of an audience for other people’s presentations/performances."
        ->
        ScottishEsAndOsMetadata(
          codes = List("EXA 0-01a", "EXA 1-01a", "EXA 2-01a"),
          curriculumLevels = List(CurriculumLevel.EARLY, CurriculumLevel.FIRST, CurriculumLevel.SECOND),
          curriculumAreaName = CurriculumAreaName.EXPRESSIVE_ARTS,
          eAndOSetName = Option.empty,
          eAndOSetSectionName = "Participation in performances and presentations",
          eAndOSetSubSectionName = Option.empty),

      "I have used the skills I have developed in the expressive arts to contribute to a " +
        "public presentation/performance."
        ->
        ScottishEsAndOsMetadata(
          codes = List("EXA 3-01a"),
          curriculumLevels = List(CurriculumLevel.THIRD),
          curriculumAreaName = CurriculumAreaName.EXPRESSIVE_ARTS,
          eAndOSetName = Option.empty,
          eAndOSetSectionName = "Participation in performances and presentations",
          eAndOSetSubSectionName = Option.empty),

      "In everyday activity and play, I explore and make choices to develop my learning and interests. " +
        "I am encouraged to use and share my experiences."
      ->
        ScottishEsAndOsMetadata(
          codes = List("HWB 0-19a"),
          curriculumLevels = List(CurriculumLevel.EARLY),
          curriculumAreaName = CurriculumAreaName.HEALTH_AND_WELLBEING,
          eAndOSetName = Option.empty,
          eAndOSetSectionName = "Planning for choices and changes",
          eAndOSetSubSectionName = Option.empty)
    )

    val esAndOsData = ScottishEsAndOsData(experienceAndOutcomeToMetadataMap)

    assert(esAndOsData.experiencesAndOutcomeMap.size == 3)

    val eAndOsSatisfyingFilter = esAndOsData.experiencesAndOutcomeMap.values.filter(
      (metadata) => metadata.eAndOSetSectionName == "Planning for choices and changes")
    assert(eAndOsSatisfyingFilter.size == 1)

  }

}
