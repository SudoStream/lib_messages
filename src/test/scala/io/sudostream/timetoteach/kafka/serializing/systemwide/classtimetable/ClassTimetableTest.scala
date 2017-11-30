package io.sudostream.timetoteach.kafka.serializing.systemwide.classtimetable

import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.ClassTimetable
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.sessions._
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.subjectdetail.SubjectDetailWrapper
import io.sudostream.timetoteach.messages.systemwide.model.classtimetable.time.{ClassTimetableSchoolTimes, DayOfTheWeek, EndTime, StartTime}
import org.scalatest.FunSuite

class ClassTimetableTest extends FunSuite {

  def createAllSessionsOfTheWeek(): scala.List[SessionOfTheDayWrapper] = {
    SessionOfTheDayWrapper(SessionOfTheDay(
      sessionName = SessionName("EarlyMorningSession"),
      dayOfTheWeek = DayOfTheWeek.MONDAY,
      startTime = StartTime("09","00"),
      endTime = EndTime("10","30"),
      subjects = List(SubjectDetailWrapper(

      ))
    )) :: Nil
  }

  def createSessionBoundaries(): List[SessionBoundaryWrapper] = {
    SessionBoundaryWrapper(SessionBoundary(
      sessionBoundaryName = SessionBoundaryName("SchoolStarts"),
      boundaryStartTime = StartTime("09", "00"),
      boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
      sessionName = Some(SessionName("EarlyMorningSession"))
    )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("MorningBreakStarts"),
        boundaryStartTime = StartTime("10", "30"),
        boundaryType = SessionBoundaryType.END_OF_TEACHING_SESSION,
        sessionName = None)
      ) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("MorningBreakEnds"),
        boundaryStartTime = StartTime("10", "45"),
        boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
        sessionName = Some(SessionName("LateMorningSession"))
      )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("LunchStarts"),
        boundaryStartTime = StartTime("12", "05"),
        boundaryType = SessionBoundaryType.END_OF_TEACHING_SESSION,
        sessionName = None)
      ) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("LunchEnds"),
        boundaryStartTime = StartTime("13", "00"),
        boundaryType = SessionBoundaryType.START_OF_TEACHING_SESSION,
        sessionName = Some(SessionName("AfternoonSession"))
      )) ::
      SessionBoundaryWrapper(SessionBoundary(
        sessionBoundaryName = SessionBoundaryName("SchoolEnds"),
        boundaryStartTime = StartTime("15", "00"),
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
      schoolTimes = theSchoolTimes,
      allSessionsOfTheWeek = theAllSessionsOfTheWeek
    )
  }
}
