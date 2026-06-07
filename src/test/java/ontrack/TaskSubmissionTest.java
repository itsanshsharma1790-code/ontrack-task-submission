package ontrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskSubmissionTest {

    @Test void testValidStudentId() {
        assertTrue(new TaskSubmission("s123456","T1.1","report.pdf",1024)
            .isStudentIdValid());
    }

    @Test void testNullStudentId() {
        assertFalse(new TaskSubmission(null,"T1.1","report.pdf",1024)
            .isStudentIdValid());
    }

    @Test void testBlankStudentId() {
        assertFalse(new TaskSubmission("   ","T1.1","report.pdf",1024)
            .isStudentIdValid());
    }

    @Test void testValidTaskId() {
        assertTrue(new TaskSubmission("s123456","T1.1","report.pdf",1024)
            .isTaskIdValid());
    }

    @Test void testNullTaskId() {
        assertFalse(new TaskSubmission("s123456",null,"report.pdf",1024)
            .isTaskIdValid());
    }

    @Test void testValidFileNamePdf() {
        assertTrue(new TaskSubmission("s123456","T1.1","report.pdf",1024)
            .isFileNameValid());
    }

    @Test void testInvalidFileExtension() {
        assertFalse(new TaskSubmission("s123456","T1.1","virus.exe",1024)
            .isFileNameValid());
    }

    @Test void testNullFileName() {
        assertFalse(new TaskSubmission("s123456","T1.1",null,1024)
            .isFileNameValid());
    }

    @Test void testValidFileSize() {
        assertTrue(new TaskSubmission("s123456","T1.1","report.pdf",5_000_000L)
            .isFileSizeValid());
    }

    @Test void testFileSizeZero() {
        assertFalse(new TaskSubmission("s123456","T1.1","report.pdf",0)
            .isFileSizeValid());
    }

    @Test void testFileSizeExceedsLimit() {
        assertFalse(new TaskSubmission("s123456","T1.1","report.pdf",11_000_000L)
            .isFileSizeValid());
    }

    @Test void testFileSizeAtLimit() {
        assertTrue(new TaskSubmission("s123456","T1.1","report.pdf",10*1024*1024L)
            .isFileSizeValid());
    }

    @Test void testSuccessfulSubmission() {
        TaskSubmission ts = new TaskSubmission("s123456","T1.1","report.pdf",1024);
        assertTrue(ts.submit());
        assertEquals("SUBMITTED", ts.getStatus());
    }

    @Test void testFailedSubmissionInvalidFile() {
        TaskSubmission ts = new TaskSubmission("s123456","T1.1","virus.exe",1024);
        assertFalse(ts.submit());
        assertEquals("FAILED", ts.getStatus());
    }

    @Test void testInitialStatusIsPending() {
        assertEquals("PENDING",
            new TaskSubmission("s123456","T1.1","report.pdf",1024).getStatus());
    }
}
