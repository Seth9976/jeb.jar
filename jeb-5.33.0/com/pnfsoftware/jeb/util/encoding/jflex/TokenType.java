package com.pnfsoftware.jeb.util.encoding.jflex;

public enum TokenType {
   OPERATOR,
   DELIMITER,
   KEYWORD,
   KEYWORD2,
   IDENTIFIER,
   NUMBER,
   STRING,
   STRING2,
   COMMENT,
   COMMENT2,
   REGEX,
   REGEX2,
   TYPE,
   TYPE2,
   TYPE3,
   DEFAULT,
   WARNING,
   ERROR;

   public static boolean isComment(Token var0) {
      return var0 != null && (var0.type == COMMENT || var0.type == COMMENT2);
   }

   public static boolean isKeyword(Token var0) {
      return var0 != null && (var0.type == KEYWORD || var0.type == KEYWORD2);
   }

   public static boolean isString(Token var0) {
      return var0 != null && (var0.type == STRING || var0.type == STRING2);
   }
}
