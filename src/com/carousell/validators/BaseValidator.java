package com.carousell.validators;

    import java.util.Map;

public interface BaseValidator<K, V> {

  void validate(Map<K, V> userProperties) throws Exception;

}
