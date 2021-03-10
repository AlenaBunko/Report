package my.project.report.lib.exception;

public class CostsNotFoundException extends Exception {

    public CostsNotFoundException() {
        super("Данный вид расходов не найден");
    }
}
