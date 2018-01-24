package io.sudostream.timetoteach.kafka.serializing.systemwide.classtimetable

import java.io.File

import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.{ClassId, ClassTimetable, TimeToTeachId}
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.sessions._
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.subjectdetail.{SubjectDetail, SubjectDetailAdditionalInfo, SubjectDetailWrapper, SubjectName}
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.time.{ClassTimetableSchoolTimes, DayOfTheWeek, EndTime, StartTime}
import io.sudostream.zzz_old_project_for_reference.api_antagonist.messages.GreenlitFilm
import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.io.{DatumReader, DatumWriter}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter}
import org.scalatest.FunSuite

class ClassTimetableTest extends FunSuite {

  def createAllSessionsOfTheWeek(): scala.List[SessionOfTheDayWrapper] = {
    SessionOfTheDayWrapper(SessionOfTheDay(
      sessionName = SessionName("EarlyMorningSession"),
      dayOfTheWeek = DayOfTheWeek.MONDAY,
      startTime = StartTime("09:00"),
      endTime = EndTime("10:30"),
      subjects = List(SubjectDetailWrapper(SubjectDetail(
        SubjectName.EMPTY,
        StartTime("09:00"),
        EndTime("10:30"),
        SubjectDetailAdditionalInfo("")
      )))
    )) ::
      SessionOfTheDayWrapper(SessionOfTheDay(
        sessionName = SessionName("LateMorningSession"),
        dayOfTheWeek = DayOfTheWeek.MONDAY,
        startTime = StartTime("10:45"),
        endTime = EndTime("12:05"),
        subjects = List(SubjectDetailWrapper(SubjectDetail(
          SubjectName.EMPTY,
          StartTime("10:45"),
          EndTime("12:05"),
          SubjectDetailAdditionalInfo("")
        )))
      )) ::
      SessionOfTheDayWrapper(SessionOfTheDay(
        sessionName = SessionName("AfternoonSession"),
        dayOfTheWeek = DayOfTheWeek.MONDAY,
        startTime = StartTime("13:00"),
        endTime = EndTime("15:00"),
        subjects = List(SubjectDetailWrapper(SubjectDetail(
          SubjectName.EMPTY,
          StartTime("13:00"),
          EndTime("15:00"),
          SubjectDetailAdditionalInfo("")
        )))
      )) :: Nil
  }

  def createSessionBoundaries(): List[SessionBoundaryWrapper] = {
    SessionBoundaryWrapper(SessionBoundary(
      sessionBoundaryName = SessionBoundaryName("SchoolStarts"),
      boundaryStartTime = StartTime("09:00"),
      boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
      sessionName = Some(SessionName("EarlyMorningSession"))
    )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("MorningBreakStarts"),
        boundaryStartTime = StartTime("10:30"),
        boundaryType = SessionBoundaryType.END_OF_TEACHING_SESSION,
        sessionName = None)
      ) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("MorningBreakEnds"),
        boundaryStartTime = StartTime("10:45"),
        boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
        sessionName = Some(SessionName("LateMorningSession"))
      )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("LunchStarts"),
        boundaryStartTime = StartTime("12:05"),
        boundaryType = SessionBoundaryType.END_OF_TEACHING_SESSION,
        sessionName = None)
      ) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("LunchEnds"),
        boundaryStartTime = StartTime("13:00"),
        boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
        sessionName = Some(SessionName("AfternoonSession"))
      )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("SchoolEnds"),
        boundaryStartTime = StartTime("15:00"),
        boundaryType = SessionBoundaryType.END_OF_TEACHING_SESSION,
        sessionName = None)
      ) :: Nil
  }

  def createSchoolTimes(): ClassTimetableSchoolTimes = {
    val sessionBoundaries: List[SessionBoundaryWrapper] = createSessionBoundaries()

    ClassTimetableSchoolTimes(
      schoolSessionBoundaries = sessionBoundaries
    )
  }

  test("Basic ClassTimetable Happy Test") {

    val theSchoolTimes: ClassTimetableSchoolTimes = createSchoolTimes()
    val theAllSessionsOfTheWeek: List[SessionOfTheDayWrapper] = createAllSessionsOfTheWeek()

    val timetable: ClassTimetable = ClassTimetable(
      TimeToTeachId("1234"),
      ClassId("P2AB"),
      schoolTimes = theSchoolTimes,
      allSessionsOfTheWeek = theAllSessionsOfTheWeek
    )

    // serialize
    val classTimetableAvroFile = new File("classTimetable.avro")
    classTimetableAvroFile.delete()

    val classTimetableDatumWriter: DatumWriter[ClassTimetable] = new SpecificDatumWriter[ClassTimetable](timetable.getSchema)
    val dataFileWriter: DataFileWriter[ClassTimetable] = new DataFileWriter[ClassTimetable](classTimetableDatumWriter)
    dataFileWriter.create(timetable.getSchema(), classTimetableAvroFile)
    dataFileWriter.append(timetable)
    dataFileWriter.close()

    // Deserialize from disk
    val classTimetableDatumReader: DatumReader[ClassTimetable] = new SpecificDatumReader[ClassTimetable](timetable.getSchema)
    val dataFileReader: DataFileReader[ClassTimetable] =
      new DataFileReader[ClassTimetable](new File("classTimetable.avro"), classTimetableDatumReader)

    var deserialisedClassTimetable: ClassTimetable = null
    while (dataFileReader.hasNext) {
      // Reuse user object by passing it to next(). This saves us from  allocating and
      // garbage collecting many objects for files with many items.
      deserialisedClassTimetable = dataFileReader.next(deserialisedClassTimetable)
      println("okay: " + deserialisedClassTimetable + "; dokies")
    }

    classTimetableAvroFile.delete()

    assert(timetable.allSessionsOfTheWeek.size == deserialisedClassTimetable.allSessionsOfTheWeek.size)

  }
}
