import dao.implementations.LaunchDAO;
import dao.implementations.TestsDAO;

/**
 * Created with IntelliJ IDEA.
 * User: voipp
 * Date: 28.03.14
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class tests {
    public static void main(String[] args) {
        LaunchDAO launchDAO = new LaunchDAO();

        TestsDAO testsDAO = new TestsDAO();
        testsDAO.getTestsResults(1);
    }
}
