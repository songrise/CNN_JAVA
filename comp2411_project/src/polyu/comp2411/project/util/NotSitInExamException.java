package polyu.comp2411.project.util;

@Deprecated
public class NotSitInExamException extends RuntimeException {
    NotSitInExamException() {
        super();
    }

    NotSitInExamException(String msg) {
        super(msg);
    }
}
