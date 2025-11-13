package com.pnfsoftware.jeb.client.mcp;

import java.util.regex.Pattern;

public class McpUtil {
   private static final Pattern tokenPattern = Pattern.compile("[\\s\\p{Punct}]+");

   public static int countTokens(String var0) {
      return tokenPattern.split(var0).length;
   }
}
