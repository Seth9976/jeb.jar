package com.pnfsoftware.jeb.corei.parsers.base64;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import java.nio.ByteBuffer;
import java.util.Map;

public class Base64Identifier extends AbstractUnitIdentifier {
   public static final String TYPE = "base64";

   public Base64Identifier() {
      super("base64", 0.0);
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(S.L("Base64 handler"), S.L("Base64 decoder"), "PNF Software", Version.create(1, 0, 0));
   }

   @Override
   public boolean canIdentify(IInput param1, IUnitCreator param2, String param3, Map param4) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 000: aload 1
      // 001: ifnull 01a
      // 004: aload 1
      // 005: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getHeader ()Ljava/nio/ByteBuffer; 1
      // 00a: ifnull 01a
      // 00d: aload 1
      // 00e: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getCurrentSize ()J 1
      // 013: ldc2_w 4
      // 016: lcmp
      // 017: ifge 01c
      // 01a: bipush 0
      // 01b: ireturn
      // 01c: aload 1
      // 01d: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getHeader ()Ljava/nio/ByteBuffer; 1
      // 022: astore 5
      // 024: aconst_null
      // 025: astore 6
      // 027: bipush 0
      // 028: istore 7
      // 02a: bipush 0
      // 02b: istore 8
      // 02d: bipush 0
      // 02e: istore 9
      // 030: aload 5
      // 032: invokevirtual java/nio/ByteBuffer.hasRemaining ()Z
      // 035: ifeq 0d7
      // 038: aload 5
      // 03a: invokevirtual java/nio/ByteBuffer.get ()B
      // 03d: istore 10
      // 03f: iload 10
      // 041: bipush 48
      // 043: if_icmplt 053
      // 046: iload 10
      // 048: bipush 57
      // 04a: if_icmpgt 053
      // 04d: bipush 1
      // 04e: istore 8
      // 050: goto 0d1
      // 053: iload 10
      // 055: bipush 65
      // 057: if_icmplt 061
      // 05a: iload 10
      // 05c: bipush 90
      // 05e: if_icmple 06f
      // 061: iload 10
      // 063: bipush 97
      // 065: if_icmplt 075
      // 068: iload 10
      // 06a: bipush 122
      // 06c: if_icmpgt 075
      // 06f: bipush 1
      // 070: istore 9
      // 072: goto 0d1
      // 075: iload 10
      // 077: bipush 47
      // 079: if_icmpeq 083
      // 07c: iload 10
      // 07e: bipush 43
      // 080: if_icmpne 09a
      // 083: aload 6
      // 085: ifnonnull 090
      // 088: getstatic java/lang/Boolean.TRUE Ljava/lang/Boolean;
      // 08b: astore 6
      // 08d: goto 0d1
      // 090: aload 6
      // 092: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 095: ifne 0d1
      // 098: bipush 0
      // 099: ireturn
      // 09a: iload 10
      // 09c: bipush 45
      // 09e: if_icmpeq 0a8
      // 0a1: iload 10
      // 0a3: bipush 95
      // 0a5: if_icmpne 0bf
      // 0a8: aload 6
      // 0aa: ifnonnull 0b5
      // 0ad: getstatic java/lang/Boolean.FALSE Ljava/lang/Boolean;
      // 0b0: astore 6
      // 0b2: goto 0d1
      // 0b5: aload 6
      // 0b7: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 0ba: ifeq 0d1
      // 0bd: bipush 0
      // 0be: ireturn
      // 0bf: iload 10
      // 0c1: bipush 61
      // 0c3: if_icmpne 0cf
      // 0c6: aload 0
      // 0c7: aload 5
      // 0c9: iload 7
      // 0cb: invokevirtual com/pnfsoftware/jeb/corei/parsers/base64/Base64Identifier.testEnding (Ljava/nio/ByteBuffer;I)Z
      // 0ce: ireturn
      // 0cf: bipush 0
      // 0d0: ireturn
      // 0d1: iinc 7 1
      // 0d4: goto 030
      // 0d7: iload 8
      // 0d9: ifeq 0ee
      // 0dc: iload 9
      // 0de: ifne 0ee
      // 0e1: getstatic java/lang/Boolean.TRUE Ljava/lang/Boolean;
      // 0e4: aload 6
      // 0e6: invokevirtual java/lang/Boolean.equals (Ljava/lang/Object;)Z
      // 0e9: ifne 0ee
      // 0ec: bipush 0
      // 0ed: ireturn
      // 0ee: iload 9
      // 0f0: ifeq 105
      // 0f3: iload 8
      // 0f5: ifne 105
      // 0f8: getstatic java/lang/Boolean.TRUE Ljava/lang/Boolean;
      // 0fb: aload 6
      // 0fd: invokevirtual java/lang/Boolean.equals (Ljava/lang/Object;)Z
      // 100: ifne 105
      // 103: bipush 0
      // 104: ireturn
      // 105: iload 7
      // 107: i2l
      // 108: aload 1
      // 109: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getCurrentSize ()J 1
      // 10e: lcmp
      // 10f: iflt 114
      // 112: bipush 1
      // 113: ireturn
      // 114: aload 1
      // 115: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getCurrentSize ()J 1
      // 11a: ldc2_w 4
      // 11d: lrem
      // 11e: lconst_0
      // 11f: lcmp
      // 120: ifeq 125
      // 123: bipush 1
      // 124: ireturn
      // 125: aload 1
      // 126: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getStream ()Ljava/io/InputStream; 1
      // 12b: astore 10
      // 12d: aload 1
      // 12e: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getCurrentSize ()J 1
      // 133: ldc2_w -4
      // 136: land
      // 137: ldc2_w 4
      // 13a: lsub
      // 13b: lstore 11
      // 13d: aload 10
      // 13f: lload 11
      // 141: invokevirtual java/io/InputStream.skip (J)J
      // 144: lload 11
      // 146: lcmp
      // 147: ifeq 15a
      // 14a: bipush 0
      // 14b: istore 13
      // 14d: aload 10
      // 14f: ifnull 157
      // 152: aload 10
      // 154: invokevirtual java/io/InputStream.close ()V
      // 157: iload 13
      // 159: ireturn
      // 15a: bipush 4
      // 15b: newarray 8
      // 15d: astore 13
      // 15f: aload 10
      // 161: aload 13
      // 163: invokevirtual java/io/InputStream.read ([B)I
      // 166: bipush 4
      // 167: if_icmpeq 17a
      // 16a: bipush 0
      // 16b: istore 14
      // 16d: aload 10
      // 16f: ifnull 177
      // 172: aload 10
      // 174: invokevirtual java/io/InputStream.close ()V
      // 177: iload 14
      // 179: ireturn
      // 17a: aload 13
      // 17c: invokestatic java/nio/ByteBuffer.wrap ([B)Ljava/nio/ByteBuffer;
      // 17f: astore 14
      // 181: bipush 2
      // 182: istore 7
      // 184: aload 14
      // 186: iload 7
      // 188: invokevirtual java/nio/ByteBuffer.position (I)Ljava/nio/ByteBuffer;
      // 18b: pop
      // 18c: aload 14
      // 18e: invokevirtual java/nio/ByteBuffer.hasRemaining ()Z
      // 191: ifeq 1bf
      // 194: aload 14
      // 196: invokevirtual java/nio/ByteBuffer.get ()B
      // 199: istore 15
      // 19b: iload 15
      // 19d: bipush 61
      // 19f: if_icmpne 1b9
      // 1a2: aload 0
      // 1a3: aload 14
      // 1a5: iload 7
      // 1a7: invokevirtual com/pnfsoftware/jeb/corei/parsers/base64/Base64Identifier.testEnding (Ljava/nio/ByteBuffer;I)Z
      // 1aa: istore 16
      // 1ac: aload 10
      // 1ae: ifnull 1b6
      // 1b1: aload 10
      // 1b3: invokevirtual java/io/InputStream.close ()V
      // 1b6: iload 16
      // 1b8: ireturn
      // 1b9: iinc 7 1
      // 1bc: goto 18c
      // 1bf: bipush 1
      // 1c0: istore 15
      // 1c2: aload 10
      // 1c4: ifnull 1cc
      // 1c7: aload 10
      // 1c9: invokevirtual java/io/InputStream.close ()V
      // 1cc: iload 15
      // 1ce: ireturn
      // 1cf: astore 11
      // 1d1: aload 10
      // 1d3: ifnull 1e7
      // 1d6: aload 10
      // 1d8: invokevirtual java/io/InputStream.close ()V
      // 1db: goto 1e7
      // 1de: astore 12
      // 1e0: aload 11
      // 1e2: aload 12
      // 1e4: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // 1e7: aload 11
      // 1e9: athrow
      // 1ea: pop
      // 1eb: bipush 0
      // 1ec: ireturn
   }

   private boolean testEnding(ByteBuffer var1, int var2) {
      int var3 = var2 % 4;
      if (var3 < 2) {
         return false;
      } else if (var3 == 2) {
         if (!var1.hasRemaining()) {
            return false;
         } else {
            byte var4 = var1.get();
            return !var1.hasRemaining() && var4 == 61;
         }
      } else {
         return var3 == 3 ? !var1.hasRemaining() : false;
      }
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new eo(var1, var2, var3, var4, this.pdm);
   }
}
