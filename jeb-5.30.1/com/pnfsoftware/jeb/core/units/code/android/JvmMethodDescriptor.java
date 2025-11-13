package com.pnfsoftware.jeb.core.units.code.android;

import java.util.ArrayList;
import java.util.List;

public class JvmMethodDescriptor {
   public String rettype;
   public List partypes = new ArrayList();

   public static JvmMethodDescriptor parse(String var0) {
      JvmMethodDescriptor var1 = parseSafe(var0);
      if (var1 == null) {
         throw new IllegalArgumentException("Illegal descriptor: " + var0);
      } else {
         return var1;
      }
   }

   private static JvmMethodDescriptor parseSafe(String param0) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 000: new com/pnfsoftware/jeb/core/units/code/android/JvmMethodDescriptor
      // 003: dup
      // 004: invokespecial com/pnfsoftware/jeb/core/units/code/android/JvmMethodDescriptor.<init> ()V
      // 007: astore 1
      // 008: aload 0
      // 009: ldc "("
      // 00b: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 00e: ifne 013
      // 011: aconst_null
      // 012: areturn
      // 013: aload 0
      // 014: ldc ")"
      // 016: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 019: istore 2
      // 01a: iload 2
      // 01b: ifge 020
      // 01e: aconst_null
      // 01f: areturn
      // 020: aload 1
      // 021: aload 0
      // 022: iload 2
      // 023: bipush 1
      // 024: iadd
      // 025: invokevirtual java/lang/String.substring (I)Ljava/lang/String;
      // 028: putfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodDescriptor.rettype Ljava/lang/String;
      // 02b: aload 0
      // 02c: bipush 1
      // 02d: iload 2
      // 02e: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 031: astore 3
      // 032: bipush 0
      // 033: istore 4
      // 035: iload 4
      // 037: aload 3
      // 038: invokevirtual java/lang/String.length ()I
      // 03b: if_icmpge 116
      // 03e: iload 4
      // 040: istore 5
      // 042: aload 3
      // 043: iload 4
      // 045: invokevirtual java/lang/String.charAt (I)C
      // 048: istore 6
      // 04a: iload 6
      // 04c: bipush 91
      // 04e: if_icmpne 05f
      // 051: iinc 4 1
      // 054: aload 3
      // 055: iload 4
      // 057: invokevirtual java/lang/String.charAt (I)C
      // 05a: istore 6
      // 05c: goto 04a
      // 05f: iload 6
      // 061: tableswitch 150 66 90 115 115 115 150 115 150 150 115 115 150 124 150 150 150 150 150 150 115 150 150 150 150 150 150 115
      // 0d4: iload 4
      // 0d6: bipush 1
      // 0d7: iadd
      // 0d8: istore 7
      // 0da: goto 0f9
      // 0dd: aload 3
      // 0de: bipush 59
      // 0e0: iload 4
      // 0e2: invokevirtual java/lang/String.indexOf (II)I
      // 0e5: istore 8
      // 0e7: iload 8
      // 0e9: ifge 0ee
      // 0ec: aconst_null
      // 0ed: areturn
      // 0ee: iload 8
      // 0f0: bipush 1
      // 0f1: iadd
      // 0f2: istore 7
      // 0f4: goto 0f9
      // 0f7: aconst_null
      // 0f8: areturn
      // 0f9: aload 3
      // 0fa: iload 5
      // 0fc: iload 7
      // 0fe: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 101: astore 8
      // 103: aload 1
      // 104: getfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodDescriptor.partypes Ljava/util/List;
      // 107: aload 8
      // 109: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 10e: pop
      // 10f: iload 7
      // 111: istore 4
      // 113: goto 035
      // 116: aload 1
      // 117: areturn
      // 118: pop
      // 119: aconst_null
      // 11a: areturn
   }
}
