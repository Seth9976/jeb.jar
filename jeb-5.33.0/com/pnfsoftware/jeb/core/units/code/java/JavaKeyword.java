package com.pnfsoftware.jeb.core.units.code.java;

public enum JavaKeyword {
   ABSTRACT,
   ASSERT,
   BOOLEAN,
   BREAK,
   BYTE,
   CASE,
   CATCH,
   CHAR,
   CLASS,
   CONST,
   CONTINUE,
   DEFAULT,
   DO,
   DOUBLE,
   ELSE,
   ENUM,
   EXTENDS,
   FINAL,
   FINALLY,
   FLOAT,
   FOR,
   GOTO,
   IF,
   IMPLEMENTS,
   IMPORT,
   INSTANCEOF,
   INT,
   INTERFACE,
   LONG,
   NATIVE,
   NEW,
   PACKAGE,
   PRIVATE,
   PROTECTED,
   PUBLIC,
   RETURN,
   SHORT,
   STATIC,
   STRICTFP,
   SUPER,
   SWITCH,
   SYNCHRONIZED,
   THIS,
   THROW,
   THROWS,
   TRANSIENT,
   TRY,
   VOID,
   VOLATILE,
   WHILE,
   TRUE,
   FALSE,
   NULL;

   public static void generateClassAccessFlags(JavaOutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, 0);
   }

   public static void generateFieldAccessFlags(JavaOutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, 1);
   }

   public static void generateMethodAccessFlags(JavaOutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, 2);
   }

   public static void generateAccessFlags(JavaOutputSink var0, int var1, int var2, int var3) {
      int[] var4 = new int[]{0};

      var1 = switch (var3) {
         case 0 -> var1 & 30239;
         case 1 -> var1 & 20703;
         case 2 -> var1 & 204287;
         default -> throw new RuntimeException("Illegal entity type: " + var3);
      };
      appendAccessKeyword(var0, var4, var1, 1, PUBLIC);
      appendAccessKeyword(var0, var4, var1, 2, PRIVATE);
      appendAccessKeyword(var0, var4, var1, 4, PROTECTED);
      appendAccessKeyword(var0, var4, var1, 8, STATIC);
      appendAccessKeyword(var0, var4, var1, 16, FINAL);
      appendAccessKeyword(var0, var4, var1, 1024, ABSTRACT);
      appendAccessKeyword(var0, var4, var1, 512, INTERFACE);
      appendAccessKeyword(var0, var4, var1, 16384, ENUM);
      appendAccessKeyword(var0, var4, var1, 32, SYNCHRONIZED);
      if (var3 == 1) {
         appendAccessKeyword(var0, var4, var1, 64, VOLATILE);
         appendAccessKeyword(var0, var4, var1, 128, TRANSIENT);
      } else if (var3 == 2) {
         appendAccessKeyword(var0, var4, var1, 64, null);
         appendAccessKeyword(var0, var4, var1, 128, null);
      }

      appendAccessKeyword(var0, var4, var1, 256, NATIVE);
      appendAccessKeyword(var0, var4, var1, 2048, STRICTFP);
      appendAccessKeyword(var0, var4, var1, 4096, null);
      appendAccessKeyword(var0, var4, var1, 8192, null);
      appendAccessKeyword(var0, var4, var1, 65536, null);
      appendAccessKeyword(var0, var4, var1, 131072, null);
      if (var2 == 1 || var2 == -1 && var4[0] > 0) {
         var0.append(" ");
      }
   }

   private static void appendAccessKeyword(JavaOutputSink var0, int[] var1, int var2, int var3, JavaKeyword var4) {
      if (var4 != null && (var2 & var3) != 0) {
         if (var1[0] > 0) {
            var0.append(" ");
         }

         if (var3 == 512 && (var2 & 8192) != 0) {
            var0.append("@");
         }

         var0.appendKeyword(var4);
         var1[0]++;
      }
   }

   public static void appendAccessKeyword(JavaOutputSink var0, JavaKeyword var1) {
      var0.appendKeyword(var1);
   }
}
