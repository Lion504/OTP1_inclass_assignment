public class TemperatureConverter {

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    public boolean isExtremeTemperature(double celsius) {
        return celsius < -40 || celsius > 50;
    }

    /**
     * Converts Kelvin to Celsius.
     * Formula: °C = K - 273.15
     * 
     * @param kelvin the temperature in Kelvin
     * @return the temperature in Celsius
     */
    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
