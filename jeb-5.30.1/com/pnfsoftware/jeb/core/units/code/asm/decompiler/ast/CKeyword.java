package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.aeg;

@Ser
public enum CKeyword {
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
   NULL,
   VIRTUAL;

   private static final StructuredLogger logger = aeg.q(CKeyword.class);

   public static void generateClassAccessFlags(COutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, CEntityType.CLASS);
   }

   public static void generateMethodAccessFlags(COutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, CEntityType.METHOD);
   }

   public static void generateFieldAccessFlags(COutputSink var0, int var1, int var2) {
      generateAccessFlags(var0, var1, var2, CEntityType.FIELD);
   }

   public static void generateAccessFlags(COutputSink var0, int var1, int var2, CEntityType var3) {
      int[] var4 = new int[]{0};

      var1 = switch (var3) {
         case CLASS -> var1 & 30239;
         case METHOD -> var1 & 204287;
         case FIELD -> var1 & 20703;
         default -> throw new RuntimeException();
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
      if (var3 == CEntityType.FIELD) {
         appendAccessKeyword(var0, var4, var1, 64, VOLATILE);
      } else if (var3 == CEntityType.METHOD) {
         appendAccessKeyword(var0, var4, var1, 128, null);
      }

      appendAccessKeyword(var0, var4, var1, 4096, null);
      appendAccessKeyword(var0, var4, var1, 65536, null);
      appendAccessKeyword(var0, var4, var1, 536870912, null);
      appendAccessKeyword(var0, var4, var1, 268435456, null);
      if (var2 == 1 || var2 == -1 && var4[0] > 0) {
         var0.append(" ");
      }
   }

   private static void appendAccessKeyword(COutputSink var0, int[] var1, int var2, int var3, CKeyword var4) {
      if (var4 != null && (var2 & var3) != 0) {
         if (var1[0] > 0) {
            var0.append(" ");
         }

         var0.appendKeyword(var4);
         var1[0]++;
      }
   }

   public static void appendAccessKeyword(COutputSink var0, CKeyword var1) {
      var0.appendKeyword(var1);
   }
}
