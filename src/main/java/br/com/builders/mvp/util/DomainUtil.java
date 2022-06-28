package br.com.builders.mvp.util;
import java.util.Arrays;

public class DomainUtil {

    public static final boolean in(Enum<?> domain, Enum<?>... domains) {
        return Arrays.stream(domains).anyMatch(fileType -> fileType.equals(domain));
    }

    public static final boolean notIn(Enum<?> domain, Enum<?>... domains) {
        return Arrays.stream(domains).noneMatch(fileType -> fileType.equals(domain));
    }
}