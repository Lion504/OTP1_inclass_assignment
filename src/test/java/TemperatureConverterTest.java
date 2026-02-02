import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.RepeatedTest;
import java.util.Random;

class TemperatureConverterTest {

    // 1. Initialize the class instance
    TemperatureConverter converter = new TemperatureConverter();
    Random random = new Random();
    @Test
    void fahrenheitToCelsius() {
        // Freezing point: 32F -> 0C
        assertEquals(0.0, converter.fahrenheitToCelsius(32), 0.001);
        // Boiling point: 212F -> 100C
        assertEquals(100.0, converter.fahrenheitToCelsius(212), 0.001);
        // Arbitrary: 50F -> 10C
        assertEquals(10.0, converter.fahrenheitToCelsius(50), 0.001);
    }

    @Test
    void celsiusToFahrenheit() {
        // Freezing point: 0C -> 32F
        assertEquals(32.0, converter.celsiusToFahrenheit(0), 0.001);
        // Boiling point: 100C -> 212F
        assertEquals(212.0, converter.celsiusToFahrenheit(100), 0.001);
        // Negative: -40C -> -40F (The point where scales meet)
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40), 0.001);
    }

    @Test
    void isExtremeTemperature() {
        // Check normal range (should be false)
        assertFalse(converter.isExtremeTemperature(25), "25C is normal");
        assertFalse(converter.isExtremeTemperature(0), "0C is normal");

        // Check boundaries (strictly below -40 or above 50)
        // -40 is NOT below -40, so it is NOT extreme
        assertFalse(converter.isExtremeTemperature(-40), "-40C is on the edge, not extreme");
        // 50 is NOT above 50, so it is NOT extreme
        assertFalse(converter.isExtremeTemperature(50), "50C is on the edge, not extreme");

        // Check extreme values (should be true)
        assertTrue(converter.isExtremeTemperature(-40.1), "-40.1C is extreme");
        assertTrue(converter.isExtremeTemperature(50.1), "50.1C is extreme");
        assertTrue(converter.isExtremeTemperature(-100), "-100C is extreme");
    }

    @RepeatedTest(5) // Runs this test 5 times with different values
    void testRoundTripConversion() {
        // 1. Generate a random Fahrenheit value between -100 and 100
        double originalFahrenheit = -100 + (200 * random.nextDouble());

        // 2. Convert F -> C
        double celsius = converter.fahrenheitToCelsius(originalFahrenheit);

        // 3. Convert C -> F (back to start)
        double resultFahrenheit = converter.celsiusToFahrenheit(celsius);

        // 4. Verify we are back where we started (allowing small error)
        assertEquals(originalFahrenheit, resultFahrenheit, 0.001,
                "Round trip failed for input: " + originalFahrenheit);
    }

    @Test
    void testRandomExtremeTemperatures() {
        // Generate a random 'Extreme' heat value (51 to 150)
        double randomHot = 51 + (100 * random.nextDouble());
        assertTrue(converter.isExtremeTemperature(randomHot),
                "Should be extreme: " + randomHot);

        // Generate a random 'Normal' value (-39 to 49)
        double randomNormal = -39 + (88 * random.nextDouble());
        assertFalse(converter.isExtremeTemperature(randomNormal),
                "Should be normal: " + randomNormal);
    }
}
