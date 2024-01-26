package com.bungii.common.utilities;


import com.bungii.common.enums.CharacterTypeSets;
import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public final class RandomGeneratorUtility {
    private static LogUtility logger = new LogUtility(RandomGeneratorUtility.class);
    
	 static Integer RANDOM_LENGTH=6;
	 public static String getData(String inputString ,Integer ... limit){
		 Object output=null;
		 String strOutput;
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

		 Integer RandomSize=limit.length>0?limit[0]:RANDOM_LENGTH;
		 
		 switch(inputString){
		 case "{RANDOM_STRING}":
			 strOutput=RandomGeneratorUtility.random(RandomSize, CharacterTypeSets.ALPHABETS);
			 break;
		 case "{RANDOM_APLHANUM}":
			 strOutput= RandomGeneratorUtility.random(RandomSize, CharacterTypeSets.ALPHANUMERIC);
			 break;
		 case "{RANDOM_EMAIL}":
			 strOutput= RandomGeneratorUtility.randomEmailAddress(RandomSize);
			 break;
		 case "{RANDOM_PASTDATE}":
			 strOutput=  fmt.print(RandomGeneratorUtility.randomAdultsDOB());
			 break;
		 case "{RANDOM_NUM}":
			 strOutput= RandomGeneratorUtility.random(RandomSize, CharacterTypeSets.NUMERIC);
			 break;
		 case "{RANDOM_PHONE_NUM}":
			 strOutput="999999"+ RandomGeneratorUtility.random(4, CharacterTypeSets.NUMERIC);

		 	break;
		 case "{RANDOM_NUM_IN_Range}":
			 strOutput= RandomGeneratorUtility.randomIntegerInRange(1, RandomSize).toString();
			 break;
		default:
			strOutput =inputString;
			 
		 }
		 return strOutput;
	 }
	 
    private static String random(Integer length, CharacterTypeSets permittedCharacters) {
        String randomString = null;
        if (CharacterTypeSets.ALPHABETS.equals(permittedCharacters)) {
            randomString = randomString(length);
        } else if (CharacterTypeSets.NUMERIC.equals(permittedCharacters)) {
            randomString = randomInteger(length);
        } else if (CharacterTypeSets.ALPHANUMERIC.equals(permittedCharacters)) {
            randomString = randomAlphanumeric(length);
        } else if (CharacterTypeSets.ANY_CHARACTERS.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        } else if (CharacterTypeSets.ANY_CHARACTERS_SUPPORTS_MULTILINGUAL.equals(permittedCharacters)) {
            randomString = randomAsciiCharacters(length);
        }
        logger.detail("Random value generated for type :"+permittedCharacters+" is "+randomString);
        return randomString;
    }

    /**
     * Generates random Number.
     *
     */
    private static String randomInteger(Integer length) {
        return RandomStringUtils.randomNumeric(length);
    }
    public static Integer randomIntegerInRange(Integer startRange,Integer endRange) {
        return RandomUtils.nextInt(startRange, startRange);
    }
    /**
     * Generates random String.
     *
     */
    private static String randomString(Integer length) {
        return RandomStringUtils.random(length, true, false);
    }

    /**
     * Generates random alphanumeric String.
     *
     * @param length length of random alphanumeric characters to be generated
     */
    private static String randomAlphanumeric(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    /**
     * Generates random alphabetic String.
     *
     * @param length length of random alphabetic characters to be generated
     */
    public static String randomAlphabetic(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }


    public static String randomEmailAddress(Integer length) {
        String email = randomAlphanumeric(length);
        return email.toLowerCase();
    }




    public static DateTime randomAdultsDOB() {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
        return dateTime.minusYears((int) (18 + (Math.random() * ((50 - 18) + 1))));
    }

    public static String dateWithNoLeadingZero(String dateWithLeadingZero) {
        String dateWithNoLeadingZero;
        dateWithNoLeadingZero = CharMatcher.is('0').trimLeadingFrom(dateWithLeadingZero);
        return dateWithNoLeadingZero;
    }

    private static String randomAsciiCharacters(Integer characterAmount) {
        return RandomStringUtils.random(characterAmount, 32, 127, false, false);
    }

}