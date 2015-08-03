package org.learnj.common.text;

public class Strings {
	
	public static void main(String[] args) {
		String s = "\\u82b1\\u8272";
		System.out.println(decodeUnicode(s));
	}
	
	public static String getUnicodeString(byte[] ascBytes) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = ascBytes.length; i < len;) {
			char charAtI = (char) ascBytes[i++];
			if (charAtI == '\\') {
				char charNext = (char) ascBytes[i++];
				if (charNext == 'u') {
					if (len - i < 4) {
						throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
					}

					int codePoint = 0;
					for (int p = 0; p < 4; p++) {
						char charAtP = (char) ascBytes[i++];
						if (charAtP >= '0' && charAtP <= '9') {
							codePoint = (codePoint << 4) + charAtP - '0';
						} else if (charAtP >= 'a' && charAtP <= 'z') {
							codePoint = (codePoint << 4) + 10 + charAtP - 'a';
						} else if (charAtP >= 'A' && charAtP <= 'Z') {
							codePoint = (codePoint << 4) + 10 + charAtP - 'Z';
						} else {
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					buffer.append((char)codePoint);
				} else {
					buffer.append(charAtI).append(charNext);
				}

			} else {
				buffer.append(charAtI);
			}
		}
		return buffer.toString();
	}

	public static String decodeUnicode(String unicodeString) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0, len = unicodeString.length(); i < len;) {
			char charAtI = unicodeString.charAt(i++);
			if (charAtI == '\\') {
				char charNext = unicodeString.charAt(i++);
				if (charNext == 'u') {
					if (len - i < 4) {
						throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
					}

					int codePoint = 0;
					for (int p = 0; p < 4; p++) {
						char charAtP = unicodeString.charAt(i++);
						if (charAtP >= '0' && charAtP <= '9') {
							codePoint = (codePoint << 4) + charAtP - '0';
						} else if (charAtP >= 'a' && charAtP <= 'z') {
							codePoint = (codePoint << 4) + 10 + charAtP - 'a';
						} else if (charAtP >= 'A' && charAtP <= 'Z') {
							codePoint = (codePoint << 4) + 10 + charAtP - 'Z';
						} else {
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					buffer.append((char)codePoint);
				} else {
					buffer.append(charAtI).append(charNext);
				}

			} else {
				buffer.append(charAtI);
			}
		}
		return buffer.toString();
	}
}
