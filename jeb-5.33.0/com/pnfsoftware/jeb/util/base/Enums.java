package com.pnfsoftware.jeb.util.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Enums {
   public static int getValue(Enum param0) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: bipush 0
      // 01: istore 1
      // 02: aload 0
      // 03: invokevirtual java/lang/Object.getClass ()Ljava/lang/Class;
      // 06: invokevirtual java/lang/Class.getDeclaredFields ()[Ljava/lang/reflect/Field;
      // 09: astore 2
      // 0a: aload 2
      // 0b: arraylength
      // 0c: istore 3
      // 0d: bipush 0
      // 0e: istore 4
      // 10: iload 4
      // 12: iload 3
      // 13: if_icmpge 58
      // 16: aload 2
      // 17: iload 4
      // 19: aaload
      // 1a: astore 5
      // 1c: aload 5
      // 1e: invokevirtual java/lang/reflect/Field.isEnumConstant ()Z
      // 21: ifeq 52
      // 24: aload 0
      // 25: invokevirtual java/lang/Enum.ordinal ()I
      // 28: iload 1
      // 29: if_icmpne 4f
      // 2c: aload 5
      // 2e: ldc com/pnfsoftware/jeb/util/base/Enums$Value
      // 30: invokevirtual java/lang/reflect/Field.getAnnotation (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
      // 33: checkcast com/pnfsoftware/jeb/util/base/Enums$Value
      // 36: astore 6
      // 38: aload 6
      // 3a: ifnonnull 47
      // 3d: new java/lang/IllegalArgumentException
      // 40: dup
      // 41: ldc "Enum-constant does not specify a value"
      // 43: invokespecial java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
      // 46: athrow
      // 47: aload 6
      // 49: invokeinterface com/pnfsoftware/jeb/util/base/Enums$Value.value ()I 1
      // 4e: ireturn
      // 4f: iinc 1 1
      // 52: iinc 4 1
      // 55: goto 10
      // 58: goto 60
      // 5b: astore 1
      // 5c: aload 1
      // 5d: invokestatic com/pnfsoftware/jeb/util/base/Throwables.rethrowUnchecked (Ljava/lang/Throwable;)V
      // 60: new java/lang/RuntimeException
      // 63: dup
      // 64: invokespecial java/lang/RuntimeException.<init> ()V
      // 67: athrow
   }

   public static Enum fromValue(Class param0, int param1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: bipush 0
      // 01: istore 2
      // 02: aload 0
      // 03: invokevirtual java/lang/Class.getDeclaredFields ()[Ljava/lang/reflect/Field;
      // 06: astore 3
      // 07: aload 3
      // 08: arraylength
      // 09: istore 4
      // 0b: bipush 0
      // 0c: istore 5
      // 0e: iload 5
      // 10: iload 4
      // 12: if_icmpge 52
      // 15: aload 3
      // 16: iload 5
      // 18: aaload
      // 19: astore 6
      // 1b: aload 6
      // 1d: invokevirtual java/lang/reflect/Field.isEnumConstant ()Z
      // 20: ifeq 4c
      // 23: aload 6
      // 25: ldc com/pnfsoftware/jeb/util/base/Enums$Value
      // 27: invokevirtual java/lang/reflect/Field.getAnnotation (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
      // 2a: checkcast com/pnfsoftware/jeb/util/base/Enums$Value
      // 2d: astore 7
      // 2f: aload 7
      // 31: ifnull 49
      // 34: aload 7
      // 36: invokeinterface com/pnfsoftware/jeb/util/base/Enums$Value.value ()I 1
      // 3b: iload 1
      // 3c: if_icmpne 49
      // 3f: aload 0
      // 40: invokevirtual java/lang/Class.getEnumConstants ()[Ljava/lang/Object;
      // 43: checkcast [Ljava/lang/Enum;
      // 46: iload 2
      // 47: aaload
      // 48: areturn
      // 49: iinc 2 1
      // 4c: iinc 5 1
      // 4f: goto 0e
      // 52: goto 5a
      // 55: astore 2
      // 56: aload 2
      // 57: invokestatic com/pnfsoftware/jeb/util/base/Throwables.rethrowUnchecked (Ljava/lang/Throwable;)V
      // 5a: new java/lang/IllegalArgumentException
      // 5d: dup
      // 5e: ldc "Cannot find enum-constant having value %d in enum %s"
      // 60: bipush 2
      // 61: anewarray 14
      // 64: dup
      // 65: bipush 0
      // 66: iload 1
      // 67: invokestatic java/lang/Integer.valueOf (I)Ljava/lang/Integer;
      // 6a: aastore
      // 6b: dup
      // 6c: bipush 1
      // 6d: aload 0
      // 6e: invokevirtual java/lang/Class.getName ()Ljava/lang/String;
      // 71: aastore
      // 72: invokestatic com/pnfsoftware/jeb/util/format/Strings.ff (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      // 75: invokespecial java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
      // 78: athrow
   }

   @SafeVarargs
   public static boolean isAnyOf(Enum var0, Enum... var1) {
      for (Enum var5 : var1) {
         if (var5 == var0) {
            return true;
         }
      }

      return false;
   }

   @Target(ElementType.FIELD)
   @Retention(RetentionPolicy.RUNTIME)
   public @interface Value {
      int value();
   }
}
