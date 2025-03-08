package com.quiz.question_services.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.env.Environment;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
public final class Utils {

    private Utils() {
    }

    public static int checkPositive(int i, String name) {
        if (i <= 0) {
            throw new IllegalArgumentException(name + ": " + i + " (expected: > 0)");
        } else {
            return i;
        }
    }

    public static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(CompletableFuture::join).
                        collect(Collectors.<T>toList())
        );
    }

    public static String createSHA3224Hash(String input) throws NoSuchAlgorithmException {
        String hashText;
        MessageDigest md = MessageDigest.getInstance("SHA3-224");
        byte[] messageDigest =
                md.digest(input.getBytes(StandardCharsets.UTF_8));
        hashText = convertToHex(messageDigest);
        return hashText;
    }

    public static String createSHA1Hash(String input) throws NoSuchAlgorithmException {
        String hashText;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest =
                md.digest(input.getBytes(StandardCharsets.UTF_8));
        hashText = convertToHex(messageDigest);
        return hashText;
    }

    public static String getMD5Hash(String content) {
        return DigestUtils.md5Hex(content).toUpperCase();
    }


    private static String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }


    public static boolean isTestProfile(Environment environment) {
        for (String en : environment.getActiveProfiles()) {
            if (en.equalsIgnoreCase("test"))
                return true;
        }
        return false;
    }

    public static boolean isDebugProfile(Environment environment) {
        boolean debug = false;
        for (String en : environment.getActiveProfiles()) {
            if (en.equalsIgnoreCase("test")
                    || en.equalsIgnoreCase("dev")
                    || en.equalsIgnoreCase("staging")) {
                debug = true;
                break;
            }
        }
        log.info("Debug Profile is {}", debug);
        return debug;
    }

    public static boolean isDefaultProfile(Environment environment) {
        for (String en : environment.getActiveProfiles()) {
            if (en.equalsIgnoreCase("default"))
                return true;
        }
        return false;
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }

    public static Date toDate(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar.toGregorianCalendar().getTime();
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    public static Date toDate(String date, String format) {
        try {
            if (format == null)
                format = "yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
            formatter.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
            return formatter.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String fromDate(Date date, String format) {
        if (format == null)
            format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        return formatter.format(date);
    }

    public static LocalDate toLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneOffset.UTC);
    }

    public static boolean isValidUrl(String url) {
        try {
            new URI(url).toURL().toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    public static long getAge(Date birthDate) {
        LocalDate now = LocalDate.now();
        LocalDate bDate = Utils.toLocalDate(birthDate);
        return ChronoUnit.YEARS.between(bDate, now);
    }

    public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

    public static <T> List<T> addToList(List<T> list, Iterable<T> iterable) {
        if (iterable != null && list != null) {
            Iterator<T> iterator = iterable.iterator();
            boolean modified = false;
            while (iterator.hasNext()) {
                modified |= list.add(iterator.next());
            }
            return list;
        }
        return list;
    }

    public static boolean containsSubstring(Set<String> set, String subString) {
        boolean hasIt = false;
        for (String s : set) {
            if (s.contains(subString)) {
                hasIt = true;
                break;
            }
        }
        return hasIt;
    }
}

