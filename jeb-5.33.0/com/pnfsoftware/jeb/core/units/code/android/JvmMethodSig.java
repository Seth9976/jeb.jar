package com.pnfsoftware.jeb.core.units.code.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JvmMethodSig {
   public String csig;
   public String mname;
   public String rettype;
   public List partypes = new ArrayList();

   private JvmMethodSig() {
   }

   public JvmMethodSig(String var1, String var2, String var3, String... var4) {
      this.csig = var1;
      this.mname = var2;
      this.rettype = var3;
      this.partypes = new ArrayList(Arrays.asList(var4));
   }

   public JvmMethodSig(String var1, String var2, String var3, Collection var4) {
      this.csig = var1;
      this.mname = var2;
      this.rettype = var3;
      this.partypes = new ArrayList(var4);
   }

   public String getType() {
      return this.csig;
   }

   public String getMethodName() {
      return this.mname;
   }

   public String getReturnType() {
      return this.rettype;
   }

   public List getParameterTypes() {
      return Collections.unmodifiableList(this.partypes);
   }

   public String generate() {
      StringBuilder var1 = new StringBuilder(this.csig);
      var1.append("->");
      var1.append(this.mname);
      var1.append("(");
      this.partypes.forEach(var1x -> var1.append(var1x));
      var1.append(")");
      var1.append(this.rettype);
      return var1.toString();
   }

   public static JvmMethodSig parse(String var0) {
      JvmMethodSig var1 = parseSafe(var0);
      if (var1 == null) {
         throw new IllegalArgumentException("Illegal method signature: " + var0);
      } else {
         return var1;
      }
   }

   public static JvmMethodSig parseSafe(String param0) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 000: new com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig
      // 003: dup
      // 004: invokespecial com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig.<init> ()V
      // 007: astore 1
      // 008: aload 0
      // 009: ldc "->"
      // 00b: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 00e: istore 2
      // 00f: iload 2
      // 010: ifge 015
      // 013: aconst_null
      // 014: areturn
      // 015: aload 1
      // 016: aload 0
      // 017: bipush 0
      // 018: iload 2
      // 019: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 01c: putfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig.csig Ljava/lang/String;
      // 01f: aload 0
      // 020: iload 2
      // 021: bipush 2
      // 022: iadd
      // 023: invokevirtual java/lang/String.substring (I)Ljava/lang/String;
      // 026: astore 3
      // 027: aload 3
      // 028: ldc "("
      // 02a: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 02d: istore 2
      // 02e: iload 2
      // 02f: ifge 034
      // 032: aconst_null
      // 033: areturn
      // 034: aload 1
      // 035: aload 3
      // 036: bipush 0
      // 037: iload 2
      // 038: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 03b: putfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig.mname Ljava/lang/String;
      // 03e: aload 3
      // 03f: iload 2
      // 040: invokevirtual java/lang/String.substring (I)Ljava/lang/String;
      // 043: astore 3
      // 044: aload 3
      // 045: ldc ")"
      // 047: invokevirtual java/lang/String.indexOf (Ljava/lang/String;)I
      // 04a: istore 2
      // 04b: iload 2
      // 04c: ifge 051
      // 04f: aconst_null
      // 050: areturn
      // 051: aload 1
      // 052: aload 3
      // 053: iload 2
      // 054: bipush 1
      // 055: iadd
      // 056: invokevirtual java/lang/String.substring (I)Ljava/lang/String;
      // 059: putfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig.rettype Ljava/lang/String;
      // 05c: aload 3
      // 05d: bipush 1
      // 05e: iload 2
      // 05f: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 062: astore 3
      // 063: bipush 0
      // 064: istore 4
      // 066: iload 4
      // 068: aload 3
      // 069: invokevirtual java/lang/String.length ()I
      // 06c: if_icmpge 146
      // 06f: iload 4
      // 071: istore 5
      // 073: aload 3
      // 074: iload 4
      // 076: invokevirtual java/lang/String.charAt (I)C
      // 079: istore 6
      // 07b: iload 6
      // 07d: bipush 91
      // 07f: if_icmpne 090
      // 082: iinc 4 1
      // 085: aload 3
      // 086: iload 4
      // 088: invokevirtual java/lang/String.charAt (I)C
      // 08b: istore 6
      // 08d: goto 07b
      // 090: iload 6
      // 092: tableswitch 149 66 90 114 114 114 149 114 149 149 114 114 149 123 149 149 149 149 149 149 114 149 149 149 149 149 149 114
      // 104: iload 4
      // 106: bipush 1
      // 107: iadd
      // 108: istore 7
      // 10a: goto 129
      // 10d: aload 3
      // 10e: bipush 59
      // 110: iload 4
      // 112: invokevirtual java/lang/String.indexOf (II)I
      // 115: istore 8
      // 117: iload 8
      // 119: ifge 11e
      // 11c: aconst_null
      // 11d: areturn
      // 11e: iload 8
      // 120: bipush 1
      // 121: iadd
      // 122: istore 7
      // 124: goto 129
      // 127: aconst_null
      // 128: areturn
      // 129: aload 3
      // 12a: iload 5
      // 12c: iload 7
      // 12e: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 131: astore 8
      // 133: aload 1
      // 134: getfield com/pnfsoftware/jeb/core/units/code/android/JvmMethodSig.partypes Ljava/util/List;
      // 137: aload 8
      // 139: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 13e: pop
      // 13f: iload 7
      // 141: istore 4
      // 143: goto 066
      // 146: aload 1
      // 147: areturn
      // 148: pop
      // 149: aconst_null
      // 14a: areturn
   }

   public static String nameAndParams(String var0) {
      int var1 = var0.indexOf("->");
      if (var1 < 0) {
         return null;
      } else {
         var1 += 2;
         int var2 = var0.indexOf(")", var1);
         return var2 < 0 ? null : var0.substring(var1, ++var2);
      }
   }
}
