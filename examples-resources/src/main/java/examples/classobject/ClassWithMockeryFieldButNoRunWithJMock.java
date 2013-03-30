package examples.classobject;

import org.jmock.Mockery;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public final class ClassWithMockeryFieldButNoRunWithJMock {

    private Mockery mockery;
}
