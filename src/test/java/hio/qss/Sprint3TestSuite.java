package hio.qss;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({QSSControllerTest.class, GameTest.class, StressTests.class, BoardCellTest.class, CellGroupTest.class})
public class Sprint3TestSuite {
}
