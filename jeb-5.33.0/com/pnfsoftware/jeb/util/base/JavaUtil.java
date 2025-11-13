package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JavaUtil {
   private static final HashSet keywords = new HashSet();
   private static HashMap primToWrapper;
   private static HashMap wrapperToPrim;
   private static HashMap primToLetter;
   private static HashMap letterToPrim;

   public static boolean isKeyword(String var0) {
      return keywords.contains(var0);
   }

   public static boolean isReservedLiteral(String var0) {
      return isKeyword(var0) || var0.equals("null") || var0.equals("true") || var0.equals("false");
   }

   public static boolean isValidIdentifierName(String var0) {
      if (var0.length() != 0 && !isReservedLiteral(var0)) {
         if (!Character.isJavaIdentifierStart(var0.charAt(0))) {
            return false;
         } else {
            for (int var1 = 1; var1 < var0.length(); var1++) {
               if (!Character.isJavaIdentifierPart(var0.charAt(var1))) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static String extractSimpleName(String var0, boolean var1) {
      ArrayList var2 = new ArrayList();
      return !isInternalClassname(var0, var1, var2) ? null : (String)var2.get(var2.size() - 1);
   }

   public static String extractFullName(String var0, boolean var1) {
      ArrayList var2 = new ArrayList();
      return !isInternalClassname(var0, var1, var2) ? null : Strings.join(".", var2);
   }

   public static boolean isInternalClassname(String var0, boolean var1, List var2) {
      if (var0.length() >= 3 && var0.startsWith("L") && var0.endsWith(";")) {
         String[] var3 = var0.substring(1, var0.length() - 1).split("/", -1);
         if (var3.length == 0) {
            return false;
         } else {
            if (var1) {
               for (String var7 : var3) {
                  if (!isValidIdentifierName(var7)) {
                     return false;
                  }
               }
            }

            if (var2 != null) {
               var2.clear();

               for (String var11 : var3) {
                  var2.add(var11);
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public static boolean isValidInternalClassname(String var0, List var1) {
      return isInternalClassname(var0, true, var1);
   }

   public static boolean isValidInternalClassname(String var0) {
      return isValidInternalClassname(var0, null);
   }

   public static boolean isClassname(String var0, boolean var1, List var2) {
      String[] var3 = var0.split("\\.", -1);
      if (var3.length == 0) {
         return false;
      } else {
         if (var1) {
            for (String var7 : var3) {
               if (!isValidIdentifierName(var7)) {
                  return false;
               }
            }
         }

         if (var2 != null) {
            var2.clear();

            for (String var11 : var3) {
               var2.add(var11);
            }
         }

         return true;
      }
   }

   public static boolean isValidClassname(String var0, List var1) {
      return isClassname(var0, true, var1);
   }

   public static boolean isValidClassname(String var0) {
      return isValidClassname(var0, null);
   }

   public static String primitiveToWrapper(String var0) {
      return (String)primToWrapper.get(var0);
   }

   public static String wrapperToPrimitive(String var0) {
      return (String)wrapperToPrim.get(var0);
   }

   public static String primitiveToLetter(String var0) {
      return (String)primToLetter.get(var0);
   }

   public static String letterToPrimitive(String var0) {
      return (String)letterToPrim.get(var0);
   }

   public static boolean isReservedMethodName(String var0) {
      return Strings.isContainedIn(var0, "<init>", "<clinit>");
   }

   public static String toJvmName(String var0) {
      if (var0.startsWith("[")) {
         return var0.replace('.', '/');
      } else {
         switch (var0) {
            case "boolean":
            case "byte":
            case "char":
            case "short":
            case "int":
            case "long":
            case "float":
            case "double":
               return (String)primToLetter.get(var0);
            case "void":
               return "V";
            default:
               return "L" + var0.replace('.', '/') + ";";
         }
      }
   }

   public static String toJvmName(Class var0) {
      if (var0.isPrimitive()) {
         return var0.getName().equals("void") ? "V" : (String)primToLetter.get(var0.getName());
      } else {
         return var0.isArray() ? var0.getName().replace('.', '/') : "L" + var0.getName().replace('.', '/') + ";";
      }
   }

   static {
      for (JavaUtil.Keyword var3 : JavaUtil.Keyword.values()) {
         keywords.add(var3.toString().toLowerCase());
      }

      primToWrapper = new HashMap();
      primToWrapper.put("boolean", "Ljava/lang/Boolean;");
      primToWrapper.put("byte", "Ljava/lang/Byte;");
      primToWrapper.put("char", "Ljava/lang/Character;");
      primToWrapper.put("short", "Ljava/lang/Short;");
      primToWrapper.put("int", "Ljava/lang/Integer;");
      primToWrapper.put("long", "Ljava/lang/Long;");
      primToWrapper.put("float", "Ljava/lang/Float;");
      primToWrapper.put("double", "Ljava/lang/Double;");
      wrapperToPrim = new HashMap();
      wrapperToPrim.put("Ljava/lang/Boolean;", "boolean");
      wrapperToPrim.put("Ljava/lang/Byte;", "byte");
      wrapperToPrim.put("Ljava/lang/Character;", "char");
      wrapperToPrim.put("Ljava/lang/Short;", "short");
      wrapperToPrim.put("Ljava/lang/Integer;", "int");
      wrapperToPrim.put("Ljava/lang/Long;", "long");
      wrapperToPrim.put("Ljava/lang/Float;", "float");
      wrapperToPrim.put("Ljava/lang/Double;", "double");
      primToLetter = new HashMap();
      primToLetter.put("boolean", "Z");
      primToLetter.put("byte", "B");
      primToLetter.put("char", "C");
      primToLetter.put("short", "S");
      primToLetter.put("int", "I");
      primToLetter.put("long", "J");
      primToLetter.put("float", "F");
      primToLetter.put("double", "D");
      letterToPrim = new HashMap();
      letterToPrim.put("Z", "boolean");
      letterToPrim.put("B", "byte");
      letterToPrim.put("C", "char");
      letterToPrim.put("S", "short");
      letterToPrim.put("I", "int");
      letterToPrim.put("J", "long");
      letterToPrim.put("F", "float");
      letterToPrim.put("D", "double");
   }

   private static enum Keyword {
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
      WHILE;
   }
}
