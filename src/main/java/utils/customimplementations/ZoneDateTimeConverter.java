package utils.customimplementations;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZoneDateTimeConverter extends AbstractBeanField {


    // Variables


    // Constructors


    // Getters and Setters


    // Initialisation methods


    // Other methods
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd K:mm:ss a O", Locale.US);
        return ZonedDateTime.parse(value, formatter);
    }


}
