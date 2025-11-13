package com.pnfsoftware.jeb.core.units.code.android;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class JvmUtil {
   private static HashMap primToLetter = new HashMap();

   public static String generateTypeSig(Class var0) {
      if (var0.isPrimitive()) {
         return (String)primToLetter.get(var0.getName());
      } else {
         return var0.isArray() ? var0.getName().replace('.', '/') : "L" + var0.getName().replace('.', '/') + ";";
      }
   }

   public static String generateConstructorSig(Constructor var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(generateTypeSig(var0.getDeclaringClass()));
      var1.append("->");
      var1.append("<init>");
      var1.append("(");

      for (Class var5 : var0.getParameterTypes()) {
         var1.append(generateTypeSig(var5));
      }

      var1.append(")");
      var1.append("V");
      return var1.toString();
   }

   public static String generateMethodSig(Method var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(generateTypeSig(var0.getDeclaringClass()));
      var1.append("->");
      var1.append(var0.getName());
      var1.append("(");

      for (Class var5 : var0.getParameterTypes()) {
         var1.append(generateTypeSig(var5));
      }

      var1.append(")");
      var1.append(generateTypeSig(var0.getReturnType()));
      return var1.toString();
   }

   static String generateMethodOrConstructorSig(Executable var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(generateTypeSig(var0.getDeclaringClass()));
      var1.append("->");
      if (var0 instanceof Method) {
         var1.append(var0.getName());
      } else {
         if (!(var0 instanceof Constructor)) {
            throw new RuntimeException();
         }

         var1.append("<init>");
      }

      var1.append("(");

      for (Class var5 : var0.getParameterTypes()) {
         var1.append(generateTypeSig(var5));
      }

      var1.append(")");
      if (var0 instanceof Method) {
         var1.append(generateTypeSig(((Method)var0).getReturnType()));
      } else {
         var1.append("V");
      }

      return var1.toString();
   }

   public static String generateFieldSig(Field var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(generateTypeSig(var0.getDeclaringClass()));
      var1.append("->");
      var1.append(var0.getName());
      var1.append(":");
      var1.append(generateTypeSig(var0.getType()));
      return var1.toString();
   }

   static {
      primToLetter.put("boolean", "Z");
      primToLetter.put("byte", "B");
      primToLetter.put("char", "C");
      primToLetter.put("short", "S");
      primToLetter.put("int", "I");
      primToLetter.put("long", "J");
      primToLetter.put("float", "F");
      primToLetter.put("double", "D");
      primToLetter.put("void", "V");
   }
}
