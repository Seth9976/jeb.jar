package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bcd {
   private static final ILogger A = GlobalLog.getLogger(bcd.class);
   bcf pC;

   bcd(bcf var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   bcd.oP pC(int var1, boolean var2, boolean var3) {
      bcc var4 = bcc.pC(var1);
      if (var1 >= bcc.HO.pC() || var1 == bcc.Bc.pC()) {
         return new bcd.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bcd.Sf(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case rl:
               case z:
               case Ek:
                  return new bcd.io(var2, var3, var1);
               case Xh:
               case sz:
               case be:
                  if (var3) {
                     return new bcd.io(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case rl:
               Assert.a(!var2);
               return new bcd.Pj();
            case z:
               Assert.a(!var2);
               return new bcd.DH();
            case Ek:
               Assert.a(!var2);
               return new bcd.rQ();
            case Xh:
            case sz:
            default:
               return null;
            case be:
               return new bcd.Kr(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bcd.Sv();
            case ld:
               return new bcd.Sb();
            case Bf:
               return new bcd.m(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bcd.gJ();
            case ys:
               Assert.a(!var2);
               return new bcd.sy();
            case gp:
               Assert.a(!var2);
               return new bcd.K();
            case oT:
               Assert.a(!var2);
               return new bcd.yt();
            case fI:
               Assert.a(!var2);
               return new bcd.RC();
            case WR:
               Assert.a(!var2);
               return new bcd.gb();
            case NS:
               Assert.a(!var2);
               return new bcd.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case Sc:
               Assert.a(!var2);
               return new bcd.cq();
            case Ab:
               Assert.a(!var2);
               return new bcd.ma();
            case Er:
               Assert.a(!var2);
               return new bcd.KD();
            case FE:
               Assert.a(!var2);
               return null;
            case Aj:
               Assert.a(!var2);
               return null;
            case mv:
               Assert.a(!var2);
               return new bcd.B();
            case Cu:
               Assert.a(!var2);
               return null;
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return new bcd.nA();
            case PR:
               Assert.a(!var2);
               return new bcd.uX();
            case ZN:
               Assert.a(!var2);
               return null;
            case OB:
               Assert.a(!var2);
               return null;
            case OI:
               Assert.a(!var2);
               return null;
            case ck:
               return new bcd.eW(var2, var3);
            case Kq:
               return new bcd.HE(var2, var3);
            case go:
               Assert.a(!var2);
               return new bcd.Hv();
            case JF:
               return new bcd.jM(var2, var3);
            case Nq:
               return new bcd.Ws(var2);
            case DL:
               return new bcd.Tb(var2);
            case UH:
               return new bcd.zp(var2);
            case rC:
               Assert.a(!var2);
               return new bcd.qt();
            case uE:
               Assert.a(!var2);
               return null;
            case Um:
               Assert.a(!var2);
               return null;
            case Ta:
               Assert.a(!var2);
               return null;
            case AU:
               throw new RuntimeException();
            case jS:
               return new bcd.GK(var2, var1);
            case KK:
               throw new RuntimeException();
            case oB:
               return new bcd.Mh(var2, var1);
            case Rq:
            case LL:
               return new bcd.Av(var2, var1);
         }
      }
   }

   class Av extends bcd.bO {
      int pC;

      Av(boolean var2, int var3) {
         this.wS = var2;
         this.pC = var3;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bcd.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcd.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.mv.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bcd.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bcd.this.pC.E());
         var1.pC("argsDescriptor", bcd.this.pC.E());
      }
   }

   class DH extends bcd.bO {
      @Override
      public void pC() {
         this.A(bcc.z.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcd.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bcd.bO {
      int pC;

      GK(boolean var2, int var3) {
         this.wS = var2;
         this.pC = var3;
      }

      @Override
      public void pC() {
         this.pC(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcd.this.pC.E());
         var1.pC("hashMask", bcd.this.pC.E());
         var1.pC("data", bcd.this.pC.E());
         var1.pC("usedData", bcd.this.pC.E());
         var1.pC("deletedKeys", bcd.this.pC.E());
      }
   }

   class HE extends bcd.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcc.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bcd.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcd.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcd.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bcd.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bcd.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcd.this.pC.E());
         var1.pC("typeParameters", bcd.this.pC.E());
         var1.pC("resultType", bcd.this.pC.E());
         var1.pC("parameterTypes", bcd.this.pC.E());
         var1.pC("namedParameterNames", bcd.this.pC.E());
         var1.pC("hash", bcd.this.pC.E());
      }
   }

   class Hv extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.go.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcd.this.pC.E());
         var1.pC("type", bcd.this.pC.E());
      }
   }

   class K extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            if (bcd.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bcd.this.pC.kS());
            }

            var2.pC("parentFunction", bcd.this.pC.kS());
            var2.pC("closure", bcd.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bcd.this.pC.E());
         }
      }
   }

   class KD extends bcd.bO {
      @Override
      public void pC() {
         this.A(bcc.Er.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bcd.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bcd.this.pC.sY());
               var6.put("outerTryIndex", bcd.this.pC.WR());
               var6.put("needsStacktrace", bcd.this.pC.A());
               var6.put("hasCatchAll", bcd.this.pC.A());
               var6.put("isGenerated", bcd.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class Kr extends bcd.bO {
      Kr(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bcd.this.pC.mv;
         long var1 = bcd.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bcd.this.pC.E();
            bcc var6 = (var4 & 1L) != 0L ? bcc.sz : bcc.Xh;
            long var7 = var4 >> 1;
            bbc var9 = bcd.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bcd.this.pC.pC(var9);
         }

         this.sY = bcd.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            long var3 = bcd.this.pC.E();
            long var5 = var3 >> 1;
            bcc var7 = (var3 & 1L) != 0L ? bcc.sz : bcc.Xh;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bcc.Xh) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bcd.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bcd.this.pC.gp();
                  var10 |= bcd.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class Mh extends bcd.bO {
      int pC;

      Mh(boolean var2, int var3) {
         this.wS = var2;
         this.pC = var3;
      }

      @Override
      public void pC() {
         this.pC(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcd.this.pC.E());
         var1.pC("hashMask", bcd.this.pC.E());
         var1.pC("data", bcd.this.pC.E());
         var1.pC("usedData", bcd.this.pC.E());
         var1.pC("deletedKeys", bcd.this.pC.E());
      }
   }

   class Pj extends bcd.bO {
      @Override
      public void pC() {
         this.A(bcc.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcd.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class RC extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            if (!bcd.this.pC.vP) {
               var2.pC("guardedListLength", bcd.this.pC.kS());
            }

            if (bcd.this.pC.NS) {
               var2.pC("dependentCode", bcd.this.pC.kS());
            }

            if (!bcd.this.pC.vP) {
               var2.pC("tokenPos", bcd.this.pC.UT());
               var2.pC("endTokenPos", bcd.this.pC.UT());
               var2.pC("guardedCid", bcd.this.pC.wS());
               var2.pC("isNullable", bcd.this.pC.wS());
               var2.pC("staticTypeExactnessState", bcd.this.pC.oT());
               if (!bcd.this.pC.ah) {
                  var2.pC("kernelOffset", bcd.this.pC.sY());
               }
            }

            int var3 = bcd.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bcd.this.pC.kS();
            if (bcd.this.pC.UT(var3)) {
               long var6 = bcd.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bcd.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcd.this.pC.E());
         var1.pC("owner", bcd.this.pC.E());
         var1.pC("type", bcd.this.pC.E());
         var1.pC("initializerFunction", bcd.this.pC.E());
      }
   }

   class Sb extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.JF.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bcd.this.pC.E());
         var1.pC("flags", bcd.this.pC.E());
         var1.pC("bounds", bcd.this.pC.E());
         var1.pC("defaults", bcd.this.pC.E());
      }
   }

   class Sf extends bcd.bO {
      int pC;

      public Sf(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bcd.this.pC.WR(this.pC);
            var2.pC("data", bcd.this.pC.wS(var4));
         }
      }
   }

   class Sv extends bcd.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bcd.this.pC.mv;
         long var1 = bcd.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bcd.this.pC.pC(bcc.E.pC());
            var2.pC("id", bcd.this.pC.wS());
            bcd.this.pC.pC(var2);
         });
         this.A = bcd.this.pC.mv;
         this.pC(bcc.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bcd.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bcd.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bcd.this.pC.ah && !bcd.this.pC.vP) {
                  var8.pC("kernelOffset", bcd.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bcd.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bcd.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bcd.this.pC.ys());
               if (!bcd.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bcd.this.pC.ys());
               var8.pC("numNativeFields", bcd.this.pC.E());
               if (!bcd.this.pC.ah) {
                  Assert.a(!bcd.this.pC.vP);
                  var8.pC("tokenPos", bcd.this.pC.UT());
                  var8.pC("endTokenPos", bcd.this.pC.UT());
               }

               var8.pC("stateBits", bcd.this.pC.E());
               if (bcd.this.pC.ah) {
                  if (var7) {
                     bcd.this.pC.E();
                  } else if (!bcd.this.pC.ys.pC(var9)) {
                     bcd.this.pC.os.put(var9, bcd.this.pC.E());
                  }
               }

               bcd.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcd.this.pC.E());
         if (!bcd.this.pC.Sc) {
            var1.pC("userName", bcd.this.pC.E());
         }

         var1.pC("functions", bcd.this.pC.E());
         var1.pC("functionsHashTable", bcd.this.pC.E());
         var1.pC("fields", bcd.this.pC.E());
         var1.pC("offsetInWordsToField", bcd.this.pC.E());
         var1.pC("interfaces", bcd.this.pC.E());
         var1.pC("script", bcd.this.pC.E());
         var1.pC("library", bcd.this.pC.E());
         var1.pC("typeParameters", bcd.this.pC.E());
         var1.pC("superType", bcd.this.pC.E());
         var1.pC("constants", bcd.this.pC.E());
         var1.pC("declarationType", bcd.this.pC.E());
         var1.pC("invocationDispatcherCache", bcd.this.pC.E());
         if (!bcd.this.pC.Sc || !bcd.this.pC.ah) {
            var1.pC("directImplementors", bcd.this.pC.E());
            var1.pC("directSubclasses", bcd.this.pC.E());
         }

         if (!bcd.this.pC.ah) {
            var1.pC("allocationStub", bcd.this.pC.E());
            var1.pC("dependentCode", bcd.this.pC.E());
         }
      }
   }

   class Tb extends bcd.bO {
      Tb(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bcd.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bcd.this.pC.pC(bcc.DL.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bcd.this.pC.ys());
            bcd.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class Ws extends bcd.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcc.Nq.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bcd.this.pC.E());
         var1.pC("functionTypeArguments", bcd.this.pC.E());
         var1.pC("delayedTypeArguments", bcd.this.pC.E());
         var1.pC("function", bcd.this.pC.E());
         var1.pC("context", bcd.this.pC.E());
         var1.pC("hash", bcd.this.pC.E());
      }
   }

   abstract class bO implements bcd.oP {
      boolean wS;
      boolean UT;
      int E;
      int sY;

      @Override
      public void pC() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void A() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void pC(int var1) {
         this.E = bcd.this.pC.mv;
         long var2 = bcd.this.pC.E();
         bcd.this.pC.pC(var2, var1);
         this.sY = bcd.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bcd.this.pC.mv;
         long var2 = bcd.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bcd.this.pC.pC(var1);
            long var4 = bcd.this.pC.E();
            var3.pC("length", var4);
            bcd.this.pC.pC(var3);
         });
         this.sY = bcd.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bcd.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bcd.this.pC.E();
            long var1 = bcd.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bcd.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bcd.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bcd.this.pC.mv;
         bcd.this.pC.Pe = this.E;
         long var1 = bcd.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bcd.this.pC.mv;
         this.pC = bcd.this.pC.mv;
         var1 = bcd.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bcd.this.pC.mv;
      }

      private void wS() {
         int var1 = bcd.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bcd.this.pC.pC(bcc.Sc.pC());
         bcd.this.pC.pC(var3);
         var3.pC("stateBits", var1);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            this.pC(var1, false);
         }

         for (int var2 = this.pC; var2 < this.A; var2++) {
            this.pC(var2, true);
         }
      }

      void pC(int var1, boolean var2) {
         bbc var3 = bcd.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bcd.this.pC.vP) {
            var3.pC("objectPool", bcd.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bcd.this.pC.kS(), bcc.ys.toString(), bcc.E.toString(), bcc.ck.toString());
         var3.pC("exceptionHandlers", bcd.this.pC.kS(), bcc.Er.toString());
         var3.pC("pcDescriptors", bcd.this.pC.kS(), bcc.rl.toString());
         var3.pC("catchEntry", bcd.this.pC.kS());
         if (bcd.this.pC.NS) {
            var3.pC("compressedStackMaps", bcd.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bcd.this.pC.kS(), bcc.Rq.toString());
         var3.pC("codeSourceMap", bcd.this.pC.kS(), bcc.z.toString());
         if (!bcd.this.pC.ah && bcd.this.pC.NS) {
            var3.pC("deoptInfoArray", bcd.this.pC.kS());
            var3.pC("staticCallsTargetTable", bcd.this.pC.kS());
         }

         if (!bcd.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bcd.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bcd.this.pC.rl ? bcd.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bcd.this.pC.ah) {
            long var3 = bcd.this.pC.Bc[2 * bcd.this.pC.Bf];
            long var5 = bcd.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bcd.this.pC.ys.UO : 0L;
            long var12 = var9 ? bcd.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bcd.this.pC.Bf++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class eW extends bcd.bO {
      eW(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcc.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeClassId", bcd.this.pC.E());
            long var3 = bcd.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcd.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcd.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcd.this.pC.E());
         var1.pC("arguments", bcd.this.pC.E());
         var1.pC("hash", bcd.this.pC.E());
      }
   }

   class gJ extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            if (!bcd.this.pC.ah && !bcd.this.pC.vP) {
               var2.pC("libraryKernelOffset", bcd.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bcd.this.pC.E());
         var1.pC("originClass", bcd.this.pC.E());
         var1.pC("script", bcd.this.pC.E());
         if (!bcd.this.pC.vP) {
            var1.pC("libraryKernelData", bcd.this.pC.E());
         }
      }
   }

   class gb extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            if (!bcd.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bcd.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bcd.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bcd.this.pC.E());
         if (bcd.this.pC.vP) {
            if (!bcd.this.pC.Sc) {
               var1.pC("resolvedUrl", bcd.this.pC.E());
            }
         } else {
            var1.pC("resolvedUrl", bcd.this.pC.E());
            var1.pC("resolvedUrl", bcd.this.pC.E());
            var1.pC("lineStarts", bcd.this.pC.E());
            var1.pC("constantCoverage", bcd.this.pC.E());
            var1.pC("debugPositions", bcd.this.pC.E());
            var1.pC("kernelProgramInfo", bcd.this.pC.E());
         }
      }
   }

   class io extends bcd.bO {
      int pC;

      io(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bcd.this.pC.mv;
         long var1 = bcd.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bcd.this.pC.E() << (int)bcd.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bcd.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bcd.this.pC.pC(var7);
         }

         this.sY = bcd.this.pC.mv;
         if (this.pC == bcc.be.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bcc.Xh.pC() && this.pC != bcc.be.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bcd.this.pC.E.position(var1);
            bcd.this.pC.E.i32();
            long var2;
            if (bcd.this.pC.FE) {
               bcd.this.pC.E.i32();
               var2 = bcd.this.pC.E.i64();
            } else {
               var2 = bcd.this.pC.E.i32();
               bcd.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bcd.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class jM extends bcd.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcc.JF.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bcd.this.pC.vP());
            var2.pC("base", bcd.this.pC.gp());
            var2.pC("index", bcd.this.pC.gp());
            long var3 = bcd.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcd.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcd.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcd.this.pC.E());
         var1.pC("hash", bcd.this.pC.E());
         var1.pC("bound", bcd.this.pC.E());
      }
   }

   class m extends bcd.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bcc.Bf.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bcd.this.pC.ld());
            var2.pC("nullabity", bcd.this.pC.E());
            var2.pC("instantiations", bcd.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bcd.this.pC.kS());
            }
         }
      }
   }

   class ma extends bcd.bO {
      @Override
      public void pC() {
         this.A(bcc.Ab.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bcd.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bcd.this.pC.E(var6);
               if (var8 == bcd.this.pC.ys.Ab) {
                  var7.put("rawObj", bcd.this.pC.kS());
               } else if (var8 == bcd.this.pC.ys.rl) {
                  var7.put("rawValue", bcd.this.pC.ys());
               } else if (var8 == bcd.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bcd.this.pC.ys.Er && var8 != bcd.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bcd.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class nA extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.UW.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("cache", bcd.this.pC.kS());
         }
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bcd.this.pC.vP());
            var2.pC("numImports", bcd.this.pC.fI());
            var2.pC("loadState", bcd.this.pC.oT());
            var2.pC("flags", bcd.this.pC.gp());
            if (!bcd.this.pC.ah && !bcd.this.pC.vP) {
               var2.pC("kernelOffset", bcd.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcd.this.pC.E());
         var1.pC("url", bcd.this.pC.E());
         var1.pC("privateKey", bcd.this.pC.E());
         var1.pC("dictionary", bcd.this.pC.E());
         var1.pC("metadata", bcd.this.pC.E());
         var1.pC("toplevelClass", bcd.this.pC.E());
         var1.pC("usedScripts", bcd.this.pC.E());
         var1.pC("loadingUnit", bcd.this.pC.E());
         var1.pC("imports", bcd.this.pC.E());
         var1.pC("exports", bcd.this.pC.E());
         if (!bcd.this.pC.vP) {
            var1.pC("dependencies", bcd.this.pC.E());
            var1.pC("kernelData", bcd.this.pC.E());
         }
      }
   }

   class qt extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.rC.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bcd.this.pC.E());
         var1.pC("length", bcd.this.pC.E());
         var1.pC("data", bcd.this.pC.E());
      }
   }

   class rQ extends bcd.bO {
      @Override
      public void pC() {
         this.A(bcc.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            long var3 = bcd.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bcd.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            if (!bcd.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bcd.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bcd.this.pC.Pe + (int)var3 - 1;
               bbc var6 = bcd.this.pC.kS(var5);
               if (var6.pC().equals(bcc.Sc.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bcd.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("packedFields", bcd.this.pC.E());
            var2.pC("kindTag", bcd.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcd.this.pC.E());
         var1.pC("owner", bcd.this.pC.E());
         var1.pC("signature", bcd.this.pC.E(), bcc.Kq.toString());
         var1.pC("data", bcd.this.pC.E());
      }
   }

   class uX extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("parent", bcd.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bcd.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class vi extends bcd.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bcd.this.pC.mv;
         long var1 = bcd.this.pC.E();
         this.A = bcd.this.pC.vP();
         this.kS = bcd.this.pC.vP();
         bcd.this.pC.pC(var1, this.pC);
         this.sY = bcd.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bcd.this.pC.ys.ys;
         int var2 = (int)bcd.this.pC.pC(this.kS * bcd.this.pC.ys.sY, bcd.this.pC.ys.ld);
         Long var3 = bcd.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bcd.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bcd.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bcd.this.pC.ys.sY)) {
               if (bcd.this.pC.A(var3, var6 / (int)bcd.this.pC.ys.sY)) {
                  bcd.this.pC.Sc();
               } else {
                  bcd.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bcd.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bcd.bO {
      @Override
      public void pC() {
         this.pC(bcc.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bcd.this.pC.vP ? bcd.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bcd.this.pC.E());
         var1.pC("cSignature", bcd.this.pC.E());
         var1.pC("callbackTarget", bcd.this.pC.E());
         var1.pC("callbackExceptionalReturn", bcd.this.pC.E());
      }
   }

   class zp extends bcd.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcc.UH.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcd.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bcd.this.pC.ys());
         }
      }
   }
}
