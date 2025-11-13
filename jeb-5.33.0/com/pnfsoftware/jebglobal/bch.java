package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bch {
   private static final ILogger A = GlobalLog.getLogger(bch.class);
   bcj pC;

   bch(bcj var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bch.oP pC(int var1, boolean var2, boolean var3) {
      bcg var4 = bcg.pC(var1);
      if (var1 >= bcg.Is.pC() || var1 == bcg.Bc.pC()) {
         return new bch.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bch.Sf(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case rl:
               case z:
               case Ek:
                  return new bch.io(var2, var3, var1);
               case sz:
               case QQ:
               case Xh:
                  if (var3) {
                     return new bch.io(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case rl:
               Assert.a(!var2);
               return new bch.Pj();
            case z:
               Assert.a(!var2);
               return new bch.DH();
            case Ek:
               Assert.a(!var2);
               return new bch.rQ();
            case sz:
            case QQ:
            default:
               return null;
            case Xh:
               return new bch.Kr(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bch.Sv();
            case ld:
               return new bch.Sb();
            case Bf:
               return new bch.m(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bch.gJ();
            case ys:
               Assert.a(!var2);
               return new bch.sy();
            case gp:
               Assert.a(!var2);
               return new bch.K();
            case oT:
               Assert.a(!var2);
               return new bch.yt();
            case fI:
               Assert.a(!var2);
               return new bch.RC();
            case WR:
               Assert.a(!var2);
               return new bch.gb();
            case NS:
               Assert.a(!var2);
               return new bch.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case Sc:
               Assert.a(!var2);
               return new bch.cq();
            case Ab:
               Assert.a(!var2);
               return new bch.ma();
            case Er:
               Assert.a(!var2);
               return new bch.KD();
            case FE:
               Assert.a(!var2);
               return null;
            case Aj:
               Assert.a(!var2);
               return null;
            case mv:
               Assert.a(!var2);
               return new bch.B();
            case Cu:
               Assert.a(!var2);
               return null;
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return new bch.nA();
            case PR:
               Assert.a(!var2);
               return new bch.uX();
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
               return new bch.eW(var2, var3);
            case Kq:
               return new bch.HE(var2, var3);
            case go:
               Assert.a(!var2);
               return new bch.Hv();
            case JF:
               return new bch.jM(var2, var3);
            case Nq:
               return new bch.Ws(var2);
            case DL:
               return new bch.Tb(var2);
            case UH:
               return new bch.zp(var2);
            case be:
               Assert.a(!var2);
               return new bch.qt();
            case uE:
               Assert.a(!var2);
               return null;
            case Ta:
               Assert.a(!var2);
               return null;
            case So:
               Assert.a(!var2);
               return null;
            case jS:
               throw new RuntimeException();
            case KK:
               return new bch.GK(var2, var1);
            case oB:
               throw new RuntimeException();
            case Rq:
               return new bch.Mh(var2, var1);
            case LL:
            case rC:
               return new bch.Av(var2, var1);
         }
      }
   }

   class Av extends bch.bO {
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
            bbc var2 = bch.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bch.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bch.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.mv.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bch.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bch.this.pC.kS());
         var1.pC("argsDescriptor", bch.this.pC.kS());
      }
   }

   class DH extends bch.bO {
      @Override
      public void pC() {
         this.A(bcg.z.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bch.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bch.bO {
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
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bch.this.pC.kS());
         var1.pC("hashMask", bch.this.pC.kS());
         var1.pC("data", bch.this.pC.kS());
         var1.pC("usedData", bch.this.pC.kS());
         var1.pC("deletedKeys", bch.this.pC.kS());
      }
   }

   class HE extends bch.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcg.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bch.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bch.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bch.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bch.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bch.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bch.this.pC.kS());
         var1.pC("typeParameters", bch.this.pC.kS());
         var1.pC("resultType", bch.this.pC.kS());
         var1.pC("parameterTypes", bch.this.pC.kS());
         var1.pC("namedParameterNames", bch.this.pC.kS());
         var1.pC("hash", bch.this.pC.kS());
      }
   }

   class Hv extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.go.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bch.this.pC.kS());
         var1.pC("type", bch.this.pC.kS());
      }
   }

   class K extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            if (bch.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bch.this.pC.kS());
            }

            var2.pC("parentFunction", bch.this.pC.kS());
            var2.pC("closure", bch.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bch.this.pC.E());
         }
      }
   }

   class KD extends bch.bO {
      @Override
      public void pC() {
         this.A(bcg.Er.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            long var3 = bch.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bch.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bch.this.pC.sY());
               var9.put("outerTryIndex", bch.this.pC.WR());
               var9.put("needsStacktrace", bch.this.pC.A());
               var9.put("hasCatchAll", bch.this.pC.A());
               var9.put("isGenerated", bch.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bch.bO {
      Kr(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bch.this.pC.mv;
         long var1 = bch.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bch.this.pC.E();
            bcg var6 = (var4 & 1L) != 0L ? bcg.QQ : bcg.sz;
            long var7 = var4 >> 1;
            bbc var9 = bch.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bch.this.pC.pC(var9);
         }

         this.sY = bch.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            long var3 = bch.this.pC.E();
            long var5 = var3 >> 1;
            bcg var7 = (var3 & 1L) != 0L ? bcg.QQ : bcg.sz;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bcg.sz) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bch.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bch.this.pC.gp();
                  var10 |= bch.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class Mh extends bch.bO {
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
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bch.this.pC.kS());
         var1.pC("hashMask", bch.this.pC.kS());
         var1.pC("data", bch.this.pC.kS());
         var1.pC("usedData", bch.this.pC.kS());
         var1.pC("deletedKeys", bch.this.pC.kS());
      }
   }

   class Pj extends bch.bO {
      @Override
      public void pC() {
         this.A(bcg.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bch.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class RC extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bch.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bch.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bch.this.pC.kS());
         var1.pC("owner", bch.this.pC.kS());
         var1.pC("type", bch.this.pC.kS());
         var1.pC("initializerFunction", bch.this.pC.kS());
      }
   }

   class Sb extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.JF.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bch.this.pC.kS());
         var1.pC("flags", bch.this.pC.kS());
         var1.pC("bounds", bch.this.pC.kS());
         var1.pC("defaults", bch.this.pC.kS());
      }
   }

   class Sf extends bch.bO {
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
            bbc var2 = bch.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bch.this.pC.WR(this.pC);
            var2.pC("data", bch.this.pC.wS(var4));
         }
      }
   }

   class Sv extends bch.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bch.this.pC.mv;
         long var1 = bch.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bch.this.pC.pC(bcg.E.pC());
            var2.pC("id", bch.this.pC.wS());
            bch.this.pC.pC(var2);
         });
         this.A = bch.this.pC.mv;
         this.pC(bcg.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bch.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bch.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bch.this.pC.ah && !bch.this.pC.vP) {
                  var8.pC("kernelOffset", bch.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bch.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bch.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bch.this.pC.ys());
               if (!bch.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bch.this.pC.ys());
               var8.pC("numNativeFields", bch.this.pC.E());
               if (!bch.this.pC.ah) {
                  Assert.a(!bch.this.pC.vP);
                  var8.pC("tokenPos", bch.this.pC.UT());
                  var8.pC("endTokenPos", bch.this.pC.UT());
               }

               var8.pC("stateBits", bch.this.pC.E());
               if (bch.this.pC.ah) {
                  if (var7) {
                     bch.this.pC.E();
                  } else if (!bch.this.pC.ys.pC(var9)) {
                     bch.this.pC.os.put(var9, bch.this.pC.E());
                  }
               }

               bch.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bch.this.pC.kS());
         if (!bch.this.pC.Sc) {
            var1.pC("userName", bch.this.pC.kS());
         }

         var1.pC("functions", bch.this.pC.kS());
         var1.pC("functionsHashTable", bch.this.pC.kS());
         var1.pC("fields", bch.this.pC.kS());
         var1.pC("offsetInWordsToField", bch.this.pC.kS());
         var1.pC("interfaces", bch.this.pC.kS());
         var1.pC("script", bch.this.pC.kS());
         var1.pC("library", bch.this.pC.kS());
         var1.pC("typeParameters", bch.this.pC.kS());
         var1.pC("superType", bch.this.pC.kS());
         var1.pC("constants", bch.this.pC.kS());
         var1.pC("declarationType", bch.this.pC.kS());
         var1.pC("invocationDispatcherCache", bch.this.pC.kS());
         if (!bch.this.pC.Sc || !bch.this.pC.ah) {
            var1.pC("directImplementors", bch.this.pC.kS());
            var1.pC("directSubclasses", bch.this.pC.kS());
         }

         if (!bch.this.pC.ah) {
            var1.pC("allocationStub", bch.this.pC.kS());
            var1.pC("dependentCode", bch.this.pC.kS());
         }
      }
   }

   class Tb extends bch.bO {
      Tb(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bch.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bch.this.pC.pC(bcg.DL.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bch.this.pC.ys());
            bch.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class Ws extends bch.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcg.Nq.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bch.this.pC.kS());
         var1.pC("functionTypeArguments", bch.this.pC.kS());
         var1.pC("delayedTypeArguments", bch.this.pC.kS());
         var1.pC("function", bch.this.pC.kS());
         var1.pC("context", bch.this.pC.kS());
         var1.pC("hash", bch.this.pC.kS());
      }
   }

   abstract class bO implements bch.oP {
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
         this.E = bch.this.pC.mv;
         long var2 = bch.this.pC.E();
         bch.this.pC.pC(var2, var1);
         this.sY = bch.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bch.this.pC.mv;
         long var2 = bch.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bch.this.pC.pC(var1);
            long var4 = bch.this.pC.E();
            var3.pC("length", var4);
            bch.this.pC.pC(var3);
         });
         this.sY = bch.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bch.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bch.this.pC.E();
            long var1 = bch.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bch.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bch.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bch.this.pC.mv;
         bch.this.pC.Bf = this.E;
         long var1 = bch.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bch.this.pC.mv;
         this.pC = bch.this.pC.mv;
         var1 = bch.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bch.this.pC.mv;
      }

      private void wS() {
         int var1 = bch.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bch.this.pC.pC(bcg.Sc.pC());
         bch.this.pC.pC(var3);
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
         bbc var3 = bch.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bch.this.pC.vP) {
            var3.pC("objectPool", bch.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bch.this.pC.kS(), bcg.ys.toString(), bcg.E.toString(), bcg.ck.toString());
         var3.pC("exceptionHandlers", bch.this.pC.kS(), bcg.Er.toString());
         var3.pC("pcDescriptors", bch.this.pC.kS(), bcg.rl.toString());
         var3.pC("catchEntry", bch.this.pC.kS());
         if (bch.this.pC.NS) {
            var3.pC("compressedStackMaps", bch.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bch.this.pC.kS(), bcg.LL.toString());
         var3.pC("codeSourceMap", bch.this.pC.kS(), bcg.z.toString());
         if (!bch.this.pC.ah && bch.this.pC.NS) {
            var3.pC("deoptInfoArray", bch.this.pC.kS());
            var3.pC("staticCallsTargetTable", bch.this.pC.kS());
         }

         if (!bch.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bch.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bch.this.pC.rl ? bch.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bch.this.pC.ah) {
            long var3 = bch.this.pC.pF[2 * bch.this.pC.OI];
            long var5 = bch.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bch.this.pC.ys.UO : 0L;
            long var12 = var9 ? bch.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bch.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class eW extends bch.bO {
      eW(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcg.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeClassId", bch.this.pC.E());
            long var3 = bch.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bch.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bch.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bch.this.pC.kS());
         var1.pC("arguments", bch.this.pC.kS());
         var1.pC("hash", bch.this.pC.kS());
      }
   }

   class gJ extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            if (!bch.this.pC.ah && !bch.this.pC.vP) {
               var2.pC("libraryKernelOffset", bch.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bch.this.pC.kS());
         var1.pC("originClass", bch.this.pC.kS());
         var1.pC("script", bch.this.pC.kS());
         if (!bch.this.pC.vP) {
            var1.pC("libraryKernelData", bch.this.pC.kS());
         }
      }
   }

   class gb extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            if (!bch.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bch.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bch.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bch.this.pC.kS());
         if (bch.this.pC.vP) {
            if (!bch.this.pC.Sc) {
               var1.pC("resolvedUrl", bch.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bch.this.pC.kS());
            var1.pC("resolvedUrl", bch.this.pC.kS());
            var1.pC("lineStarts", bch.this.pC.kS());
            var1.pC("constantCoverage", bch.this.pC.kS());
            var1.pC("debugPositions", bch.this.pC.kS());
            var1.pC("kernelProgramInfo", bch.this.pC.kS());
         }
      }
   }

   class io extends bch.bO {
      int pC;

      io(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bch.this.pC.mv;
         long var1 = bch.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bch.this.pC.E() << (int)bch.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bch.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bch.this.pC.pC(var7);
         }

         this.sY = bch.this.pC.mv;
         if (this.pC == bcg.Xh.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bcg.sz.pC() && this.pC != bcg.Xh.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bch.this.pC.E.position(var1);
            bch.this.pC.E.i32();
            long var2;
            if (bch.this.pC.FE) {
               bch.this.pC.E.i32();
               var2 = bch.this.pC.E.i64();
            } else {
               var2 = bch.this.pC.E.i32();
               bch.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bch.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class jM extends bch.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcg.JF.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bch.this.pC.vP());
            var2.pC("base", bch.this.pC.gp());
            var2.pC("index", bch.this.pC.gp());
            long var3 = bch.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bch.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bch.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bch.this.pC.kS());
         var1.pC("hash", bch.this.pC.kS());
         var1.pC("bound", bch.this.pC.kS());
      }
   }

   class m extends bch.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bcg.Bf.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bch.this.pC.ld());
            var2.pC("nullabity", bch.this.pC.E());
            var2.pC("instantiations", bch.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bch.this.pC.kS());
            }
         }
      }
   }

   class ma extends bch.bO {
      @Override
      public void pC() {
         this.A(bcg.Ab.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bch.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bch.this.pC.E(var6);
               if (var8 == bch.this.pC.ys.Ab) {
                  var7.put("rawObj", bch.this.pC.kS());
               } else if (var8 == bch.this.pC.ys.rl) {
                  var7.put("rawValue", bch.this.pC.ys());
               } else if (var8 == bch.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bch.this.pC.ys.Er && var8 != bch.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bch.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class nA extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.UW.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("cache", bch.this.pC.kS());
         }
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bch.this.pC.vP());
            var2.pC("numImports", bch.this.pC.fI());
            var2.pC("loadState", bch.this.pC.oT());
            var2.pC("flags", bch.this.pC.gp());
            if (!bch.this.pC.ah && !bch.this.pC.vP) {
               var2.pC("kernelOffset", bch.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bch.this.pC.kS());
         var1.pC("url", bch.this.pC.kS());
         var1.pC("privateKey", bch.this.pC.kS());
         var1.pC("dictionary", bch.this.pC.kS());
         var1.pC("metadata", bch.this.pC.kS());
         var1.pC("toplevelClass", bch.this.pC.kS());
         var1.pC("usedScripts", bch.this.pC.kS());
         var1.pC("loadingUnit", bch.this.pC.kS());
         var1.pC("imports", bch.this.pC.kS());
         var1.pC("exports", bch.this.pC.kS());
         if (!bch.this.pC.vP) {
            var1.pC("dependencies", bch.this.pC.kS());
            var1.pC("kernelData", bch.this.pC.kS());
         }
      }
   }

   class qt extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.be.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bch.this.pC.kS());
         var1.pC("length", bch.this.pC.kS());
         var1.pC("data", bch.this.pC.kS());
      }
   }

   class rQ extends bch.bO {
      @Override
      public void pC() {
         this.A(bcg.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            long var3 = bch.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bch.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            if (!bch.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bch.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bch.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bch.this.pC.kS(var5);
               if (var6.pC().equals(bcg.Sc.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bch.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bch.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bch.this.pC.kS());
         var1.pC("owner", bch.this.pC.kS());
         var1.pC("signature", bch.this.pC.kS(), bcg.Kq.toString());
         var1.pC("data", bch.this.pC.kS());
      }
   }

   class uX extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("parent", bch.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bch.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class vi extends bch.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bch.this.pC.mv;
         long var1 = bch.this.pC.E();
         this.A = bch.this.pC.vP();
         this.kS = bch.this.pC.vP();
         bch.this.pC.pC(var1, this.pC);
         this.sY = bch.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bch.this.pC.ys.ys;
         int var2 = (int)bch.this.pC.pC(this.kS * bch.this.pC.ys.sY, bch.this.pC.ys.ld);
         Long var3 = bch.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bch.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bch.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bch.this.pC.ys.sY)) {
               if (bch.this.pC.A(var3, var6 / (int)bch.this.pC.ys.sY)) {
                  bch.this.pC.Sc();
               } else {
                  bch.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bch.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bch.bO {
      @Override
      public void pC() {
         this.pC(bcg.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bch.this.pC.vP ? bch.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bch.this.pC.kS());
         var1.pC("cSignature", bch.this.pC.kS());
         var1.pC("callbackTarget", bch.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bch.this.pC.kS());
      }
   }

   class zp extends bch.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcg.UH.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bch.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bch.this.pC.ys());
         }
      }
   }
}
