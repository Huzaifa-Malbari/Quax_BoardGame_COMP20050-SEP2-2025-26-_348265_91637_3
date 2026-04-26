package hio.qss;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// The job of this is to group specific test classes together so they can be run as one suite.
@Suite
@SelectClasses({ BotTest.class, ShortestPathStrategyTest.class, QSSControllerTest.class})
public class Sprint4TestSuite {
}
