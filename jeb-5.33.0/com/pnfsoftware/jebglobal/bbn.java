package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bbn {
   private static final ILogger A = GlobalLog.getLogger(bbn.class);
   bbp pC;

   bbn(bbp var1) {
      Assert.a(var1.vP);
      this.pC = var1;
   }

   bbn.HE pC(int var1, boolean var2) {
      bbn.HE var3 = this.pC(var1);
      ((bbn.bO)var3).wS = var2;
      return var3;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bbn.HE pC(int var1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jebglobal/bbm; with value 28
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:258)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:251)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 000: iload 1
      // 001: invokestatic com/pnfsoftware/jebglobal/bbm.pC (I)Lcom/pnfsoftware/jebglobal/bbm;
      // 004: astore 2
      // 005: iload 1
      // 006: getstatic com/pnfsoftware/jebglobal/bbm.x Lcom/pnfsoftware/jebglobal/bbm;
      // 009: invokevirtual com/pnfsoftware/jebglobal/bbm.pC ()I
      // 00c: if_icmpge 019
      // 00f: iload 1
      // 010: getstatic com/pnfsoftware/jebglobal/bbm.DQ Lcom/pnfsoftware/jebglobal/bbm;
      // 013: invokevirtual com/pnfsoftware/jebglobal/bbm.pC ()I
      // 016: if_icmpne 023
      // 019: new com/pnfsoftware/jebglobal/bbn$qt
      // 01c: dup
      // 01d: aload 0
      // 01e: iload 1
      // 01f: invokespecial com/pnfsoftware/jebglobal/bbn$qt.<init> (Lcom/pnfsoftware/jebglobal/bbn;I)V
      // 022: areturn
      // 023: aload 0
      // 024: getfield com/pnfsoftware/jebglobal/bbn.pC Lcom/pnfsoftware/jebglobal/bbp;
      // 027: iload 1
      // 028: invokevirtual com/pnfsoftware/jebglobal/bbp.ld (I)Z
      // 02b: ifeq 038
      // 02e: new com/pnfsoftware/jebglobal/bbn$Kr
      // 031: dup
      // 032: aload 0
      // 033: aload 2
      // 034: invokespecial com/pnfsoftware/jebglobal/bbn$Kr.<init> (Lcom/pnfsoftware/jebglobal/bbn;Lcom/pnfsoftware/jebglobal/bbm;)V
      // 037: areturn
      // 038: aload 0
      // 039: getfield com/pnfsoftware/jebglobal/bbn.pC Lcom/pnfsoftware/jebglobal/bbp;
      // 03c: getfield com/pnfsoftware/jebglobal/bbp.Ab Z
      // 03f: ifeq 08e
      // 042: getstatic com/pnfsoftware/jebglobal/bbo.pC [I
      // 045: aload 2
      // 046: invokevirtual com/pnfsoftware/jebglobal/bbm.ordinal ()I
      // 049: iaload
      // 04a: tableswitch 68 1 6 38 38 38 48 48 58
      // 070: new com/pnfsoftware/jebglobal/bbn$uX
      // 073: dup
      // 074: aload 0
      // 075: aload 2
      // 076: invokespecial com/pnfsoftware/jebglobal/bbn$uX.<init> (Lcom/pnfsoftware/jebglobal/bbn;Lcom/pnfsoftware/jebglobal/bbm;)V
      // 079: areturn
      // 07a: new com/pnfsoftware/jebglobal/bbn$uX
      // 07d: dup
      // 07e: aload 0
      // 07f: aload 2
      // 080: invokespecial com/pnfsoftware/jebglobal/bbn$uX.<init> (Lcom/pnfsoftware/jebglobal/bbn;Lcom/pnfsoftware/jebglobal/bbm;)V
      // 083: areturn
      // 084: new com/pnfsoftware/jebglobal/bbn$uX
      // 087: dup
      // 088: aload 0
      // 089: aload 2
      // 08a: invokespecial com/pnfsoftware/jebglobal/bbn$uX.<init> (Lcom/pnfsoftware/jebglobal/bbn;Lcom/pnfsoftware/jebglobal/bbm;)V
      // 08d: areturn
      // 08e: getstatic com/pnfsoftware/jebglobal/bbo.pC [I
      // 091: aload 2
      // 092: invokevirtual com/pnfsoftware/jebglobal/bbm.ordinal ()I
      // 095: iaload
      // 096: tableswitch 354 7 32 118 127 136 145 154 163 172 181 190 199 208 217 217 227 236 245 254 263 272 281 290 299 308 317 326 335
      // 10c: new com/pnfsoftware/jebglobal/bbn$Sv
      // 10f: dup
      // 110: aload 0
      // 111: invokespecial com/pnfsoftware/jebglobal/bbn$Sv.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 114: areturn
      // 115: new com/pnfsoftware/jebglobal/bbn$Mh
      // 118: dup
      // 119: aload 0
      // 11a: invokespecial com/pnfsoftware/jebglobal/bbn$Mh.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 11d: areturn
      // 11e: new com/pnfsoftware/jebglobal/bbn$yt
      // 121: dup
      // 122: aload 0
      // 123: invokespecial com/pnfsoftware/jebglobal/bbn$yt.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 126: areturn
      // 127: new com/pnfsoftware/jebglobal/bbn$K
      // 12a: dup
      // 12b: aload 0
      // 12c: invokespecial com/pnfsoftware/jebglobal/bbn$K.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 12f: areturn
      // 130: new com/pnfsoftware/jebglobal/bbn$zp
      // 133: dup
      // 134: aload 0
      // 135: invokespecial com/pnfsoftware/jebglobal/bbn$zp.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 138: areturn
      // 139: new com/pnfsoftware/jebglobal/bbn$KD
      // 13c: dup
      // 13d: aload 0
      // 13e: invokespecial com/pnfsoftware/jebglobal/bbn$KD.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 141: areturn
      // 142: new com/pnfsoftware/jebglobal/bbn$Tb
      // 145: dup
      // 146: aload 0
      // 147: invokespecial com/pnfsoftware/jebglobal/bbn$Tb.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 14a: areturn
      // 14b: new com/pnfsoftware/jebglobal/bbn$oP
      // 14e: dup
      // 14f: aload 0
      // 150: invokespecial com/pnfsoftware/jebglobal/bbn$oP.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 153: areturn
      // 154: new com/pnfsoftware/jebglobal/bbn$cq
      // 157: dup
      // 158: aload 0
      // 159: invokespecial com/pnfsoftware/jebglobal/bbn$cq.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 15c: areturn
      // 15d: new com/pnfsoftware/jebglobal/bbn$GK
      // 160: dup
      // 161: aload 0
      // 162: invokespecial com/pnfsoftware/jebglobal/bbn$GK.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 165: areturn
      // 166: new com/pnfsoftware/jebglobal/bbn$p
      // 169: dup
      // 16a: aload 0
      // 16b: invokespecial com/pnfsoftware/jebglobal/bbn$p.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 16e: areturn
      // 16f: new com/pnfsoftware/jebglobal/bbn$Av
      // 172: dup
      // 173: aload 0
      // 174: aload 2
      // 175: invokespecial com/pnfsoftware/jebglobal/bbn$Av.<init> (Lcom/pnfsoftware/jebglobal/bbn;Lcom/pnfsoftware/jebglobal/bbm;)V
      // 178: areturn
      // 179: new com/pnfsoftware/jebglobal/bbn$rQ
      // 17c: dup
      // 17d: aload 0
      // 17e: invokespecial com/pnfsoftware/jebglobal/bbn$rQ.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 181: areturn
      // 182: new com/pnfsoftware/jebglobal/bbn$nA
      // 185: dup
      // 186: aload 0
      // 187: invokespecial com/pnfsoftware/jebglobal/bbn$nA.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 18a: areturn
      // 18b: new com/pnfsoftware/jebglobal/bbn$ma
      // 18e: dup
      // 18f: aload 0
      // 190: invokespecial com/pnfsoftware/jebglobal/bbn$ma.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 193: areturn
      // 194: new com/pnfsoftware/jebglobal/bbn$vi
      // 197: dup
      // 198: aload 0
      // 199: invokespecial com/pnfsoftware/jebglobal/bbn$vi.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 19c: areturn
      // 19d: new com/pnfsoftware/jebglobal/bbn$gJ
      // 1a0: dup
      // 1a1: aload 0
      // 1a2: invokespecial com/pnfsoftware/jebglobal/bbn$gJ.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1a5: areturn
      // 1a6: new com/pnfsoftware/jebglobal/bbn$Pj
      // 1a9: dup
      // 1aa: aload 0
      // 1ab: invokespecial com/pnfsoftware/jebglobal/bbn$Pj.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1ae: areturn
      // 1af: new com/pnfsoftware/jebglobal/bbn$RC
      // 1b2: dup
      // 1b3: aload 0
      // 1b4: invokespecial com/pnfsoftware/jebglobal/bbn$RC.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1b7: areturn
      // 1b8: new com/pnfsoftware/jebglobal/bbn$gb
      // 1bb: dup
      // 1bc: aload 0
      // 1bd: invokespecial com/pnfsoftware/jebglobal/bbn$gb.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1c0: areturn
      // 1c1: new com/pnfsoftware/jebglobal/bbn$io
      // 1c4: dup
      // 1c5: aload 0
      // 1c6: invokespecial com/pnfsoftware/jebglobal/bbn$io.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1c9: areturn
      // 1ca: new com/pnfsoftware/jebglobal/bbn$Ws
      // 1cd: dup
      // 1ce: aload 0
      // 1cf: invokespecial com/pnfsoftware/jebglobal/bbn$Ws.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1d2: areturn
      // 1d3: new com/pnfsoftware/jebglobal/bbn$DH
      // 1d6: dup
      // 1d7: aload 0
      // 1d8: invokespecial com/pnfsoftware/jebglobal/bbn$DH.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1db: areturn
      // 1dc: new com/pnfsoftware/jebglobal/bbn$sy
      // 1df: dup
      // 1e0: aload 0
      // 1e1: invokespecial com/pnfsoftware/jebglobal/bbn$sy.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1e4: areturn
      // 1e5: aload 0
      // 1e6: getfield com/pnfsoftware/jebglobal/bbn.pC Lcom/pnfsoftware/jebglobal/bbp;
      // 1e9: getfield com/pnfsoftware/jebglobal/bbp.ah Z
      // 1ec: ifeq 1f8
      // 1ef: new com/pnfsoftware/jebglobal/bbn$m
      // 1f2: dup
      // 1f3: aload 0
      // 1f4: invokespecial com/pnfsoftware/jebglobal/bbn$m.<init> (Lcom/pnfsoftware/jebglobal/bbn;)V
      // 1f7: areturn
      // 1f8: new com/pnfsoftware/jeb/core/exceptions/ToDoException
      // 1fb: dup
      // 1fc: aload 2
      // 1fd: invokedynamic makeConcatWithConstants (Lcom/pnfsoftware/jebglobal/bbm;)Ljava/lang/String; bsm=java/lang/invoke/StringConcatFactory.makeConcatWithConstants (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite; args=[ "Cluster deser. not implemented for ClassId \u0001" ]
      // 202: invokespecial com/pnfsoftware/jeb/core/exceptions/ToDoException.<init> (Ljava/lang/String;)V
      // 205: athrow
   }

   class Av extends bbn.bO {
      bbm pC;

      Av(bbm var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bbn.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bbn.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class DH extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.Kq);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bbn.this.pC.ys());
         }
      }
   }

   class GK extends bbn.bO {
      @Override
      public void pC() {
         this.A(bbm.ah);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bbn.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bbn.this.pC.E(var6);
               if (var8 == bbn.this.pC.ys.Ab) {
                  var7.put("rawObj", bbn.this.pC.kS());
               } else if (var8 == bbn.this.pC.ys.rl) {
                  var7.put("rawValue", bbn.this.pC.ys());
               } else {
                  if (var8 != bbn.this.pC.ys.z) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   interface HE {
      void pC();

      void A();
   }

   class K extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.ys);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            if (bbn.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bbn.this.pC.kS());
            }

            var2.pC("parentFunction", bbn.this.pC.kS());
            var2.pC("closure", bbn.this.pC.kS());
            var2.pC("defaultTypeArguments", bbn.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bbn.this.pC.E());
         }
      }
   }

   class KD extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.gp);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            if (!bbn.this.pC.vP) {
               var2.pC("guardedListLength", bbn.this.pC.kS());
            }

            if (bbn.this.pC.NS) {
               var2.pC("dependentCode", bbn.this.pC.kS());
            }

            if (!bbn.this.pC.vP) {
               var2.pC("tokenPos", bbn.this.pC.UT());
               var2.pC("endTokenPos", bbn.this.pC.UT());
               var2.pC("guardedCid", bbn.this.pC.wS());
               var2.pC("isNullable", bbn.this.pC.wS());
               var2.pC("staticTypeExactnessState", bbn.this.pC.oT());
               if (!bbn.this.pC.ah) {
                  var2.pC("kernelOffset", bbn.this.pC.sY());
               }
            }

            int var3 = bbn.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bbn.this.pC.kS();
            if (bbn.this.pC.UT(var3)) {
               long var6 = bbn.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bbn.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbn.this.pC.E());
         var1.pC("owner", bbn.this.pC.E());
         var1.pC("type", bbn.this.pC.E());
         var1.pC("initializerFunction", bbn.this.pC.E());
      }
   }

   class Kr extends bbn.bO {
      bbm pC;

      public Kr(bbm var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bbn.this.pC.WR(this.pC.pC());
            var2.pC("data", bbn.this.pC.wS(var4));
         }
      }
   }

   class Mh extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.E);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            if (!bbn.this.pC.ah && !bbn.this.pC.vP) {
               var2.pC("libraryKernelOffset", bbn.this.pC.ld());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bbn.this.pC.E());
         var1.pC("originClass", bbn.this.pC.E());
         var1.pC("script", bbn.this.pC.E());
         if (!bbn.this.pC.vP) {
            var1.pC("libraryKernelData", bbn.this.pC.E());
         }
      }
   }

   class Pj extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.Bc);
         this.pC(this.wS, false);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbn.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbn.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbn.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbn.this.pC.E());
         var1.pC("typeClassId", bbn.this.pC.E());
         var1.pC("arguments", bbn.this.pC.E());
         var1.pC("hash", bbn.this.pC.E());
      }
   }

   class RC extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.OI);
         this.pC(this.wS, true);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbn.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbn.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbn.this.pC.ys.UW);
            var2.pC("packedFields", bbn.this.pC.NS());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbn.this.pC.E());
         var1.pC("typeParameters", bbn.this.pC.E());
         var1.pC("resultType", bbn.this.pC.E());
         var1.pC("parameterTypes", bbn.this.pC.E());
         var1.pC("parameterNames", bbn.this.pC.E());
         var1.pC("hash", bbn.this.pC.E());
      }
   }

   class Sv extends bbn.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbn.this.pC.mv;
         long var1 = bbn.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bbn.this.pC.pC(bbm.UT.pC());
            var2.pC("id", bbn.this.pC.wS());
            bbn.this.pC.pC(var2);
         });
         this.A = bbn.this.pC.mv;
         this.pC(bbm.UT);
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.UT, this.E}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bbn.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bbn.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bbn.this.pC.ah && !bbn.this.pC.vP) {
                  var8.pC("kernelOffset", bbn.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bbn.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bbn.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bbn.this.pC.ys());
               if (!bbn.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bbn.this.pC.ys());
               var8.pC("numNativeFields", bbn.this.pC.E());
               var8.pC("tokenPos", bbn.this.pC.UT());
               var8.pC("endTokenPos", bbn.this.pC.UT());
               var8.pC("stateBits", bbn.this.pC.E());
               if (bbn.this.pC.ah) {
                  if (var7) {
                     bbn.this.pC.E();
                  } else if (!bbn.this.pC.ys.pC(var9)) {
                     bbn.this.pC.os.put(var9, bbn.this.pC.E());
                  }
               }

               bbn.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbn.this.pC.E());
         var1.pC("userName", bbn.this.pC.E());
         var1.pC("functions", bbn.this.pC.E());
         var1.pC("functionsHashTable", bbn.this.pC.E());
         var1.pC("fields", bbn.this.pC.E());
         var1.pC("offsetInWordsToField", bbn.this.pC.E());
         var1.pC("interfaces", bbn.this.pC.E());
         var1.pC("script", bbn.this.pC.E());
         var1.pC("library", bbn.this.pC.E());
         var1.pC("typeParameters", bbn.this.pC.E());
         var1.pC("superType", bbn.this.pC.E());
         var1.pC("constants", bbn.this.pC.E());
         var1.pC("declarationType", bbn.this.pC.E());
         var1.pC("invocationDispatcherCache", bbn.this.pC.E());
         var1.pC("allocationStub", bbn.this.pC.E());
         if (!bbn.this.pC.vP) {
            var1.pC("directImplementors", bbn.this.pC.E());
            var1.pC("directSubclasses", bbn.this.pC.E());
            if (!bbn.this.pC.WR) {
               var1.pC("dependentCode", bbn.this.pC.E());
               if (!bbn.this.pC.NS) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class Tb extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.oT);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("lineOffset", bbn.this.pC.vP());
            var2.pC("colOffset", bbn.this.pC.vP());
            if (!bbn.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bbn.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bbn.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bbn.this.pC.E());
         if (!bbn.this.pC.vP) {
            var1.pC("resolvedUrl", bbn.this.pC.E());
            var1.pC("compileTimeConstants", bbn.this.pC.E());
            var1.pC("lineStarts", bbn.this.pC.E());
            var1.pC("debugPositions", bbn.this.pC.E());
            var1.pC("kernelProgramInfo", bbn.this.pC.E());
         }
      }
   }

   class Ws extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.ck);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bbn.this.pC.E());
         var1.pC("functionTypeArguments", bbn.this.pC.E());
         var1.pC("delayedTypeArguments", bbn.this.pC.E());
         var1.pC("function", bbn.this.pC.E());
         var1.pC("context", bbn.this.pC.E());
         var1.pC("hash", bbn.this.pC.E());
      }
   }

   abstract class bO implements bbn.HE {
      boolean wS;
      int UT;
      int E;

      @Override
      public void pC() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void A() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void pC(bbm var1) {
         this.UT = bbn.this.pC.mv;
         long var2 = bbn.this.pC.E();
         bbn.this.pC.pC(var2, var1.pC());
         this.E = bbn.this.pC.mv;
      }

      protected final void A(bbm var1) {
         this.UT = bbn.this.pC.mv;
         long var2 = bbn.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bbn.this.pC.pC(var1.pC());
            long var4 = bbn.this.pC.E();
            var3.pC("length", var4);
            bbn.this.pC.pC(var3);
         });
         this.E = bbn.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bbn.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void pC(boolean var1, boolean var2) {
         if (var1) {
            bbn.this.pC.E();
            long var3 = var2 ? 0L : bbn.this.pC.E();

            for (int var5 = this.UT + (int)var3; var5 < this.E; var5++) {
               bbn.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bbn.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC(bbm.xC);
         this.pC = bbn.this.pC.mv;
         long var1 = bbn.this.pC.E();
         bbn.this.pC.pC(var1, bbm.xC.pC());
         this.A = bbn.this.pC.mv;
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            this.pC(var1, false);
         }

         for (int var2 = this.pC; var2 < this.A; var2++) {
            this.pC(var2, true);
         }
      }

      void pC(int var1, boolean var2) {
         bbc var3 = bbn.this.pC.kS(var1);
         this.pC(var3, var2);
         var3.pC("compressedStackMaps", bbn.this.pC.kS());
         int var4 = bbn.this.pC.vP();
         var3.pC("stateBits", var4);
         boolean var5 = (var4 >> 3 & 1) == 1;
         if (bbn.this.pC.ah) {
            if (var5) {
               return;
            }
         } else {
            Assert.a(!var5);
         }

         if (bbn.this.pC.vP && bbn.this.pC.eP) {
            var3.pC("objectPool", null);
         } else {
            var3.pC("objectPool", bbn.this.pC.kS());
         }

         var3.pC("owner", bbn.this.pC.kS());
         var3.pC("exceptionHandlers", bbn.this.pC.kS(), bbm.z.toString());
         var3.pC("pcDescriptors", bbn.this.pC.kS());
         var3.pC("catchEntry", bbn.this.pC.kS());
         var3.pC("inlinedIdToFunction", bbn.this.pC.kS());
         var3.pC("codeSourceMap", bbn.this.pC.kS());
         if (!bbn.this.pC.ah && bbn.this.pC.NS) {
            var3.pC("deoptInfoArray", bbn.this.pC.kS());
            var3.pC("staticCallsTargetTable", bbn.this.pC.kS());
         }

         if (!bbn.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bbn.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bbn.this.pC.rl ? bbn.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bbn.this.pC.ah && bbn.this.pC.eP) {
            bbn.this.pC.OI = (int)(bbn.this.pC.OI + bbn.this.pC.E());
            long var3 = bbn.this.pC.Bf + bbn.this.pC.OI;
            long var5 = bbn.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bbn.this.pC.ys.UO : 0L;
            long var12 = var9 ? bbn.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class gJ extends bbn.bO {
      @Override
      public void pC() {
         this.A(bbm.OB);
         this.pC(this.wS, true);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bbn.this.pC.ld());
            var2.pC("nullabity", bbn.this.pC.E());
            var2.pC("instantiations", bbn.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bbn.this.pC.kS());
            }
         }
      }
   }

   class gb extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.Bf);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbn.this.pC.E());
         var1.pC("type", bbn.this.pC.E());
      }
   }

   class io extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.Pe);
         this.pC(this.wS, true);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bbn.this.pC.vP());
            var2.pC("base", bbn.this.pC.fI());
            var2.pC("index", bbn.this.pC.fI());
            long var3 = bbn.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbn.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbn.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbn.this.pC.E());
         var1.pC("name", bbn.this.pC.E());
         var1.pC("hash", bbn.this.pC.E());
         var1.pC("bound", bbn.this.pC.E());
         var1.pC("defaultArgument", bbn.this.pC.E());
      }
   }

   class m extends bbn.bO {
      @Override
      public void pC() {
      }

      @Override
      public void A() {
      }
   }

   class ma extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.sO);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("cache", bbn.this.pC.kS());
         }
      }
   }

   class nA extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.FE);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bbn.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbn.this.pC.E());
         var1.pC("argsDescriptor", bbn.this.pC.E());
      }
   }

   class oP extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.fI);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bbn.this.pC.vP());
            var2.pC("numImports", bbn.this.pC.fI());
            var2.pC("loadState", bbn.this.pC.oT());
            var2.pC("flags", bbn.this.pC.gp());
            if (!bbn.this.pC.ah && !bbn.this.pC.vP) {
               var2.pC("kernelOffset", bbn.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbn.this.pC.E());
         var1.pC("url", bbn.this.pC.E());
         var1.pC("privateKey", bbn.this.pC.E());
         var1.pC("dictionary", bbn.this.pC.E());
         var1.pC("metadata", bbn.this.pC.E());
         var1.pC("toplevelClass", bbn.this.pC.E());
         var1.pC("usedScripts", bbn.this.pC.E());
         var1.pC("loadingUnit", bbn.this.pC.E());
         var1.pC("imports", bbn.this.pC.E());
         var1.pC("exports", bbn.this.pC.E());
         if (!bbn.this.pC.vP) {
            var1.pC("dependencies", bbn.this.pC.E());
            var1.pC("kernelData", bbn.this.pC.E());
         }
      }
   }

   class p extends bbn.bO {
      @Override
      public void pC() {
         long var1 = bbn.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bbn.this.pC.pC(bbm.kU.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bbn.this.pC.ys());
            bbn.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class qt extends bbn.bO {
      int pC;
      int A;
      int kS;

      qt(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.UT = bbn.this.pC.mv;
         long var1 = bbn.this.pC.E();
         this.A = bbn.this.pC.vP();
         this.kS = bbn.this.pC.vP();
         bbn.this.pC.pC(var1, this.pC);
         this.E = bbn.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bbn.this.pC.ys.E;
         int var2 = (int)bbn.this.pC.pC(this.kS * bbn.this.pC.ys.UT, bbn.this.pC.ys.ld);
         Long var3 = bbn.this.pC.E();

         for (int var4 = this.UT; var4 < this.E; var4++) {
            bbc var5 = bbn.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);
            ArrayList var6 = new ArrayList();
            var5.pC("data", var6);

            int var7;
            for (var7 = bbn.this.pC.FE ? 8 : 4; var7 < var1; var7 = (int)(var7 + bbn.this.pC.ys.UT)) {
               if (bbn.this.pC.A(var3, var7 / (int)bbn.this.pC.ys.UT)) {
                  bbn.this.pC.Sc();
               } else {
                  bbn.this.pC.kS();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bbn.this.pC.ys.UT);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class rQ extends bbn.bO {
      @Override
      public void pC() {
         this.A(bbm.z);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bbn.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bbn.this.pC.sY());
               var6.put("outerTryIndex", bbn.this.pC.WR());
               var6.put("needsStacktrace", bbn.this.pC.A());
               var6.put("hasCatchAll", bbn.this.pC.A());
               var6.put("isGenerated", bbn.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class sy extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.JF);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bbn.this.pC.E());
         var1.pC("length", bbn.this.pC.E());
         var1.pC("data", bbn.this.pC.E());
      }
   }

   class uX extends bbn.bO {
      bbm pC;

      uX(bbm var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.UT = bbn.this.pC.mv;
         long var1 = bbn.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bbn.this.pC.E() << (int)bbn.this.pC.ys.gp));
            Object var6 = this.pC(var3);
            bbc var7 = bbn.this.pC.pC(this.pC.pC());
            var7.pC("data", var6);
            bbn.this.pC.pC(var7);
         }

         this.E = bbn.this.pC.mv;
         this.pC(this.pC == bbm.So, true);
      }

      @Override
      public void A() {
      }

      protected Object pC(int var1) {
         if (this.pC != bbm.tH && this.pC != bbm.So) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bbn.this.pC.E.position(var1);
            bbn.this.pC.E.i32();
            long var2;
            if (bbn.this.pC.FE) {
               bbn.this.pC.E.i32();
               var2 = bbn.this.pC.E.i64();
            } else {
               var2 = bbn.this.pC.E.i32();
               bbn.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bbn.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class vi extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.os);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            var2.pC("parent", bbn.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bbn.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class yt extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.sY);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            if (bbn.this.pC.WR) {
               throw new ToDoException();
            }

            if (!bbn.this.pC.vP) {
               throw new ToDoException();
            }

            var2.pC("code", bbn.this.pC.kS());
            if (!bbn.this.pC.ah && !bbn.this.pC.vP) {
               var2.pC("tokenPos", bbn.this.pC.UT());
               var2.pC("endTokenPos", bbn.this.pC.UT());
               var2.pC("kernelOffset", bbn.this.pC.E());
            }

            var2.pC("packedFields", bbn.this.pC.E());
            var2.pC("kindTag", bbn.this.pC.E());
            if (!bbn.this.pC.vP && !bbn.this.pC.ah) {
               var2.pC("usageCounter", 0);
               var2.pC("optimizedInstructionCount", 0);
               var2.pC("optimizedCallSiteCount", 0);
               var2.pC("deoptimizationCounter", 0);
               var2.pC("stateBits", 0);
               var2.pC("inliningDepth", 0);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbn.this.pC.E());
         var1.pC("owner", bbn.this.pC.E());
         var1.pC("parameterNames", bbn.this.pC.E());
         var1.pC("signature", bbn.this.pC.E());
         var1.pC("data", bbn.this.pC.E());
      }
   }

   class zp extends bbn.bO {
      @Override
      public void pC() {
         this.pC(bbm.ld);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bbn.this.pC.vP ? bbn.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bbn.this.pC.E());
         var1.pC("cSignature", bbn.this.pC.E());
         var1.pC("callbackTarget", bbn.this.pC.E());
         var1.pC("callbackExceptionalReturn", bbn.this.pC.E());
      }
   }
}
