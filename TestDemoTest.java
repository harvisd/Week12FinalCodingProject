
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoTest {
private TestDemo testDemo; 

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		//Given : two integers that are positive 
		
		//When : method is called
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} 
		else {
			assertThatThrownBy(
					() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
		
		//Then : return the sum of the positive numbers
	}
	
	static Stream<Arguments> argumentsForAddPositive() {
		return (Stream.of(
				arguments(7, 8, 15, false),
				arguments(43, 0, 16, true),
				arguments(-55, -1, -16, true),
				arguments(0, 0, 0, true),
				arguments(462, 10, 472, false)
			));
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() { 
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
		
	}
	

}
