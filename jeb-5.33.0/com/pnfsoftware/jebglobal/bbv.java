package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bbv {
   private static final ILogger A = GlobalLog.getLogger(bbv.class);
   bbx pC;

   bbv(bbx var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bbv.oP pC(int var1, boolean var2, boolean var3) {
      bbu var4 = bbu.pC(var1);
      if (var1 >= bbu.HG.pC() || var1 == bbu.Bc.pC()) {
         return new bbv.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bbv.Sf(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case rl:
               case z:
               case Ek:
                  return new bbv.io(var2, var3, var1);
               case oB:
               case Rq:
               case KK:
                  if (var3) {
                     return new bbv.io(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case rl:
               Assert.a(!var2);
               return new bbv.Pj();
            case z:
               Assert.a(!var2);
               return new bbv.DH();
            case Ek:
               Assert.a(!var2);
               return new bbv.rQ();
            case oB:
            case Rq:
            default:
               return null;
            case KK:
               return new bbv.Kr(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bbv.Sv();
            case ld:
               return new bbv.Sb();
            case Bf:
               return new bbv.m(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bbv.gJ();
            case ys:
               Assert.a(!var2);
               return new bbv.sy();
            case gp:
               Assert.a(!var2);
               return new bbv.K();
            case oT:
               Assert.a(!var2);
               return new bbv.yt();
            case fI:
               Assert.a(!var2);
               return new bbv.RC();
            case WR:
               Assert.a(!var2);
               return new bbv.gb();
            case NS:
               Assert.a(!var2);
               return new bbv.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case Sc:
               Assert.a(!var2);
               return new bbv.cq();
            case Ab:
               Assert.a(!var2);
               return new bbv.ma();
            case Er:
               Assert.a(!var2);
               return new bbv.KD();
            case FE:
               Assert.a(!var2);
               return null;
            case Aj:
               Assert.a(!var2);
               return null;
            case mv:
               Assert.a(!var2);
               return new bbv.B();
            case Cu:
               Assert.a(!var2);
               return null;
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return new bbv.nA();
            case PR:
               Assert.a(!var2);
               return new bbv.uX();
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
               return new bbv.eW(var2, var3);
            case RW:
               return new bbv.HE(var2, var3);
            case e:
               Assert.a(!var2);
               return new bbv.Hv();
            case xM:
               return new bbv.jM(var2, var3);
            case kU:
               return new bbv.Ws(var2);
            case Nq:
               return new bbv.Tb(var2);
            case pg:
               return new bbv.zp(var2);
            case jS:
               Assert.a(!var2);
               return new bbv.qt();
            case FM:
               Assert.a(!var2);
               return null;
            case Wn:
               Assert.a(!var2);
               return null;
            case gy:
               Assert.a(!var2);
               return null;
            case So:
               throw new RuntimeException();
            case tH:
               return new bbv.GK(var2, var1);
            case Gm:
               throw new RuntimeException();
            case Br:
               return new bbv.Mh(var2, var1);
            case IE:
            case AU:
               return new bbv.Av(var2, var1);
         }
      }
   }

   class Av extends bbv.bO {
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
            bbc var2 = bbv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bbv.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bbv.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.mv.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bbv.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbv.this.pC.E());
         var1.pC("argsDescriptor", bbv.this.pC.E());
      }
   }

   class DH extends bbv.bO {
      @Override
      public void pC() {
         this.A(bbu.z.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bbv.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bbv.bO {
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
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bbv.this.pC.E());
         var1.pC("hashMask", bbv.this.pC.E());
         var1.pC("data", bbv.this.pC.E());
         var1.pC("usedData", bbv.this.pC.E());
         var1.pC("deletedKeys", bbv.this.pC.E());
      }
   }

   class HE extends bbv.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bbu.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbv.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbv.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbv.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bbv.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bbv.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbv.this.pC.E());
         var1.pC("typeParameters", bbv.this.pC.E());
         var1.pC("resultType", bbv.this.pC.E());
         var1.pC("parameterTypes", bbv.this.pC.E());
         var1.pC("namedParameterNames", bbv.this.pC.E());
         var1.pC("hash", bbv.this.pC.E());
      }
   }

   class Hv extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.e.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbv.this.pC.E());
         var1.pC("type", bbv.this.pC.E());
      }
   }

   class K extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            if (bbv.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bbv.this.pC.kS());
            }

            var2.pC("parentFunction", bbv.this.pC.kS());
            var2.pC("closure", bbv.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bbv.this.pC.E());
         }
      }
   }

   class KD extends bbv.bO {
      @Override
      public void pC() {
         this.A(bbu.Er.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bbv.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bbv.this.pC.sY());
               var6.put("outerTryIndex", bbv.this.pC.WR());
               var6.put("needsStacktrace", bbv.this.pC.A());
               var6.put("hasCatchAll", bbv.this.pC.A());
               var6.put("isGenerated", bbv.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class Kr extends bbv.bO {
      Kr(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bbv.this.pC.mv;
         long var1 = bbv.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bbv.this.pC.E();
            bbu var6 = (var4 & 1L) != 0L ? bbu.Rq : bbu.oB;
            long var7 = var4 >> 1;
            bbc var9 = bbv.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bbv.this.pC.pC(var9);
         }

         this.sY = bbv.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            long var3 = bbv.this.pC.E();
            long var5 = var3 >> 1;
            bbu var7 = (var3 & 1L) != 0L ? bbu.Rq : bbu.oB;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bbu.oB) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bbv.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bbv.this.pC.gp();
                  var10 |= bbv.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class Mh extends bbv.bO {
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
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bbv.this.pC.E());
         var1.pC("hashMask", bbv.this.pC.E());
         var1.pC("data", bbv.this.pC.E());
         var1.pC("usedData", bbv.this.pC.E());
         var1.pC("deletedKeys", bbv.this.pC.E());
      }
   }

   class Pj extends bbv.bO {
      @Override
      public void pC() {
         this.A(bbu.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bbv.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class RC extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            if (!bbv.this.pC.vP) {
               var2.pC("guardedListLength", bbv.this.pC.kS());
            }

            if (bbv.this.pC.NS) {
               var2.pC("dependentCode", bbv.this.pC.kS());
            }

            if (!bbv.this.pC.vP) {
               var2.pC("tokenPos", bbv.this.pC.UT());
               var2.pC("endTokenPos", bbv.this.pC.UT());
               var2.pC("guardedCid", bbv.this.pC.wS());
               var2.pC("isNullable", bbv.this.pC.wS());
               var2.pC("staticTypeExactnessState", bbv.this.pC.oT());
               if (!bbv.this.pC.ah) {
                  var2.pC("kernelOffset", bbv.this.pC.sY());
               }
            }

            int var3 = bbv.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bbv.this.pC.kS();
            if (bbv.this.pC.UT(var3)) {
               long var6 = bbv.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bbv.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbv.this.pC.E());
         var1.pC("owner", bbv.this.pC.E());
         var1.pC("type", bbv.this.pC.E());
         var1.pC("initializerFunction", bbv.this.pC.E());
      }
   }

   class Sb extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.xM.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bbv.this.pC.E());
         var1.pC("flags", bbv.this.pC.E());
         var1.pC("bounds", bbv.this.pC.E());
         var1.pC("defaults", bbv.this.pC.E());
      }
   }

   class Sf extends bbv.bO {
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
            bbc var2 = bbv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bbv.this.pC.WR(this.pC);
            var2.pC("data", bbv.this.pC.wS(var4));
         }
      }
   }

   class Sv extends bbv.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbv.this.pC.mv;
         long var1 = bbv.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bbv.this.pC.pC(bbu.E.pC());
            var2.pC("id", bbv.this.pC.wS());
            bbv.this.pC.pC(var2);
         });
         this.A = bbv.this.pC.mv;
         this.pC(bbu.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bbv.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bbv.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bbv.this.pC.ah && !bbv.this.pC.vP) {
                  var8.pC("kernelOffset", bbv.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bbv.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bbv.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bbv.this.pC.ys());
               if (!bbv.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bbv.this.pC.ys());
               var8.pC("numNativeFields", bbv.this.pC.E());
               if (!bbv.this.pC.ah) {
                  Assert.a(!bbv.this.pC.vP);
                  var8.pC("tokenPos", bbv.this.pC.UT());
                  var8.pC("endTokenPos", bbv.this.pC.UT());
               }

               var8.pC("stateBits", bbv.this.pC.E());
               if (bbv.this.pC.ah) {
                  if (var7) {
                     bbv.this.pC.E();
                  } else if (!bbv.this.pC.ys.pC(var9)) {
                     bbv.this.pC.os.put(var9, bbv.this.pC.E());
                  }
               }

               bbv.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbv.this.pC.E());
         if (!bbv.this.pC.Sc) {
            var1.pC("userName", bbv.this.pC.E());
         }

         var1.pC("functions", bbv.this.pC.E());
         var1.pC("functionsHashTable", bbv.this.pC.E());
         var1.pC("fields", bbv.this.pC.E());
         var1.pC("offsetInWordsToField", bbv.this.pC.E());
         var1.pC("interfaces", bbv.this.pC.E());
         var1.pC("script", bbv.this.pC.E());
         var1.pC("library", bbv.this.pC.E());
         var1.pC("typeParameters", bbv.this.pC.E());
         var1.pC("superType", bbv.this.pC.E());
         var1.pC("constants", bbv.this.pC.E());
         var1.pC("declarationType", bbv.this.pC.E());
         var1.pC("invocationDispatcherCache", bbv.this.pC.E());
         if (!bbv.this.pC.Sc || !bbv.this.pC.ah) {
            var1.pC("directImplementors", bbv.this.pC.E());
            var1.pC("directSubclasses", bbv.this.pC.E());
         }

         if (!bbv.this.pC.ah) {
            var1.pC("allocationStub", bbv.this.pC.E());
            var1.pC("dependentCode", bbv.this.pC.E());
         }
      }
   }

   class Tb extends bbv.bO {
      Tb(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bbv.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bbv.this.pC.pC(bbu.Nq.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bbv.this.pC.ys());
            bbv.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class Ws extends bbv.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bbu.kU.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bbv.this.pC.E());
         var1.pC("functionTypeArguments", bbv.this.pC.E());
         var1.pC("delayedTypeArguments", bbv.this.pC.E());
         var1.pC("function", bbv.this.pC.E());
         var1.pC("context", bbv.this.pC.E());
         var1.pC("hash", bbv.this.pC.E());
      }
   }

   abstract class bO implements bbv.oP {
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
         this.E = bbv.this.pC.mv;
         long var2 = bbv.this.pC.E();
         bbv.this.pC.pC(var2, var1);
         this.sY = bbv.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bbv.this.pC.mv;
         long var2 = bbv.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bbv.this.pC.pC(var1);
            long var4 = bbv.this.pC.E();
            var3.pC("length", var4);
            bbv.this.pC.pC(var3);
         });
         this.sY = bbv.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bbv.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bbv.this.pC.E();
            long var1 = bbv.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bbv.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bbv.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bbv.this.pC.mv;
         long var1 = bbv.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bbv.this.pC.mv;
         this.pC = bbv.this.pC.mv;
         var1 = bbv.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bbv.this.pC.mv;
      }

      private void wS() {
         int var1 = bbv.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         if (var2) {
            bbc var3 = bbv.this.pC.pC(bbu.Sc.pC());
            var3.pC("unknown", true);
            bbv.this.pC.pC(var3);
         } else {
            bbc var4 = bbv.this.pC.pC(bbu.Sc.pC());
            bbv.this.pC.pC(var4);
            var4.pC("stateBits", var1);
         }
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
         bbc var3 = bbv.this.pC.kS(var1);
         if (bbv.this.pC.ah && Boolean.TRUE.equals(var3.pC("unknown", Boolean.class))) {
            this.pC(var3, var2, true);
         } else {
            this.pC(var3, var2, false);
            if (bbv.this.pC.vP && bbv.this.pC.eP) {
               var3.pC("objectPool", null);
            } else {
               var3.pC("objectPool", bbv.this.pC.kS());
            }

            var3.pC("owner", bbv.this.pC.kS());
            var3.pC("exceptionHandlers", bbv.this.pC.kS(), bbu.Er.toString());
            var3.pC("pcDescriptors", bbv.this.pC.kS());
            var3.pC("catchEntry", bbv.this.pC.kS());
            var3.pC("compressedStackMaps", bbv.this.pC.kS());
            var3.pC("inlinedIdToFunction", bbv.this.pC.kS());
            var3.pC("codeSourceMap", bbv.this.pC.kS());
            if (!bbv.this.pC.ah && bbv.this.pC.NS) {
               var3.pC("deoptInfoArray", bbv.this.pC.kS());
               var3.pC("staticCallsTargetTable", bbv.this.pC.kS());
            }

            if (!bbv.this.pC.Sc) {
               var3.pC("returnAddressMetadata", bbv.this.pC.kS());
               var3.pC("varDescriptors", null);
               var3.pC("comments", bbv.this.pC.rl ? bbv.this.pC.kS() : null);
               var3.pC("compileTimestamp", 0);
            }
         }
      }

      void pC(bbc var1, boolean var2, boolean var3) {
         if (var2) {
            Assert.a(!var3);
            throw new ToDoException();
         } else if (bbv.this.pC.ah && bbv.this.pC.eP) {
            bbv.this.pC.Bc = (int)(bbv.this.pC.Bc + bbv.this.pC.E());
            long var4 = bbv.this.pC.OI + bbv.this.pC.Bc;
            long var6 = bbv.this.pC.E();
            long var8 = var6 >> 1;
            boolean var10 = (var6 & 1L) == 1L;
            long var11 = var10 ? bbv.this.pC.ys.UO : 0L;
            long var13 = var10 ? bbv.this.pC.ys.eP : 0L;
            long var15 = var4 + var11;
            long var17 = var4 + var13;
            if (var3) {
               bbv.this.pC.kS();
            }

            if (!var3) {
               var1.pC("entryPoint", var15);
               var1.pC("uncheckedEntryPoint", var15 + var8);
               var1.pC("monomorphicEntryPoint", var17);
               var1.pC("monomorphicUncheckedEntryPoint", var17 + var8);
            }
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class eW extends bbv.bO {
      eW(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bbu.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeClassId", bbv.this.pC.E());
            long var3 = bbv.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbv.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbv.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbv.this.pC.E());
         var1.pC("arguments", bbv.this.pC.E());
         var1.pC("hash", bbv.this.pC.E());
      }
   }

   class gJ extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            if (!bbv.this.pC.ah && !bbv.this.pC.vP) {
               var2.pC("libraryKernelOffset", bbv.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bbv.this.pC.E());
         var1.pC("originClass", bbv.this.pC.E());
         var1.pC("script", bbv.this.pC.E());
         if (!bbv.this.pC.vP) {
            var1.pC("libraryKernelData", bbv.this.pC.E());
         }
      }
   }

   class gb extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            if (!bbv.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bbv.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bbv.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bbv.this.pC.E());
         if (bbv.this.pC.vP) {
            if (!bbv.this.pC.Sc) {
               var1.pC("resolvedUrl", bbv.this.pC.E());
            }
         } else {
            var1.pC("resolvedUrl", bbv.this.pC.E());
            var1.pC("resolvedUrl", bbv.this.pC.E());
            var1.pC("lineStarts", bbv.this.pC.E());
            var1.pC("constantCoverage", bbv.this.pC.E());
            var1.pC("debugPositions", bbv.this.pC.E());
            var1.pC("kernelProgramInfo", bbv.this.pC.E());
         }
      }
   }

   class io extends bbv.bO {
      int pC;

      io(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bbv.this.pC.mv;
         long var1 = bbv.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bbv.this.pC.E() << (int)bbv.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bbv.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bbv.this.pC.pC(var7);
         }

         this.sY = bbv.this.pC.mv;
         if (this.pC == bbu.KK.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bbu.oB.pC() && this.pC != bbu.KK.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bbv.this.pC.E.position(var1);
            bbv.this.pC.E.i32();
            long var2;
            if (bbv.this.pC.FE) {
               bbv.this.pC.E.i32();
               var2 = bbv.this.pC.E.i64();
            } else {
               var2 = bbv.this.pC.E.i32();
               bbv.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bbv.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class jM extends bbv.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bbu.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bbv.this.pC.vP());
            var2.pC("base", bbv.this.pC.gp());
            var2.pC("index", bbv.this.pC.gp());
            long var3 = bbv.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbv.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbv.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbv.this.pC.E());
         var1.pC("hash", bbv.this.pC.E());
         var1.pC("bound", bbv.this.pC.E());
      }
   }

   class m extends bbv.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bbu.Bf.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bbv.this.pC.ld());
            var2.pC("nullabity", bbv.this.pC.E());
            var2.pC("instantiations", bbv.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bbv.this.pC.kS());
            }
         }
      }
   }

   class ma extends bbv.bO {
      @Override
      public void pC() {
         this.A(bbu.Ab.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bbv.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bbv.this.pC.E(var6);
               if (var8 == bbv.this.pC.ys.Ab) {
                  var7.put("rawObj", bbv.this.pC.kS());
               } else if (var8 == bbv.this.pC.ys.rl) {
                  var7.put("rawValue", bbv.this.pC.ys());
               } else if (var8 == bbv.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bbv.this.pC.ys.Er && var8 != bbv.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bbv.this.pC.ah && bbv.this.pC.eP);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class nA extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.UW.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("cache", bbv.this.pC.kS());
         }
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bbv.this.pC.vP());
            var2.pC("numImports", bbv.this.pC.fI());
            var2.pC("loadState", bbv.this.pC.oT());
            var2.pC("flags", bbv.this.pC.gp());
            if (!bbv.this.pC.ah && !bbv.this.pC.vP) {
               var2.pC("kernelOffset", bbv.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbv.this.pC.E());
         var1.pC("url", bbv.this.pC.E());
         var1.pC("privateKey", bbv.this.pC.E());
         var1.pC("dictionary", bbv.this.pC.E());
         var1.pC("metadata", bbv.this.pC.E());
         var1.pC("toplevelClass", bbv.this.pC.E());
         var1.pC("usedScripts", bbv.this.pC.E());
         var1.pC("loadingUnit", bbv.this.pC.E());
         var1.pC("imports", bbv.this.pC.E());
         var1.pC("exports", bbv.this.pC.E());
         if (!bbv.this.pC.vP) {
            var1.pC("dependencies", bbv.this.pC.E());
            var1.pC("kernelData", bbv.this.pC.E());
         }
      }
   }

   class qt extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.jS.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bbv.this.pC.E());
         var1.pC("length", bbv.this.pC.E());
         var1.pC("data", bbv.this.pC.E());
      }
   }

   class rQ extends bbv.bO {
      @Override
      public void pC() {
         this.A(bbu.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            long var3 = bbv.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bbv.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            if (bbv.this.pC.WR) {
               throw new ToDoException();
            }

            if (!bbv.this.pC.vP) {
               throw new ToDoException();
            }

            var2.pC("codeIndex", bbv.this.pC.E());
            var2.pC("code", var2.kS("codeIndex"));
            if (!bbv.this.pC.ah && !bbv.this.pC.vP) {
               var2.pC("positionalParameterNames", bbv.this.pC.kS());
               var2.pC("tokenPos", bbv.this.pC.UT());
               var2.pC("endTokenPos", bbv.this.pC.UT());
               var2.pC("kernelOffset", bbv.this.pC.E());
            }

            var2.pC("packedFields", bbv.this.pC.E());
            var2.pC("kindTag", bbv.this.pC.E());
            if (!bbv.this.pC.ah) {
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
         var1.pC("name", bbv.this.pC.E());
         var1.pC("owner", bbv.this.pC.E());
         var1.pC("signature", bbv.this.pC.E());
         var1.pC("data", bbv.this.pC.E());
      }
   }

   class uX extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("parent", bbv.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bbv.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class vi extends bbv.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bbv.this.pC.mv;
         long var1 = bbv.this.pC.E();
         this.A = bbv.this.pC.vP();
         this.kS = bbv.this.pC.vP();
         bbv.this.pC.pC(var1, this.pC);
         this.sY = bbv.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bbv.this.pC.ys.ys;
         int var2 = (int)bbv.this.pC.pC(this.kS * bbv.this.pC.ys.sY, bbv.this.pC.ys.ld);
         Long var3 = bbv.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bbv.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bbv.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bbv.this.pC.ys.sY)) {
               if (bbv.this.pC.A(var3, var6 / (int)bbv.this.pC.ys.sY)) {
                  bbv.this.pC.Sc();
               } else {
                  bbv.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bbv.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bbv.bO {
      @Override
      public void pC() {
         this.pC(bbu.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bbv.this.pC.vP ? bbv.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bbv.this.pC.E());
         var1.pC("cSignature", bbv.this.pC.E());
         var1.pC("callbackTarget", bbv.this.pC.E());
         var1.pC("callbackExceptionalReturn", bbv.this.pC.E());
      }
   }

   class zp extends bbv.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bbu.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bbv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bbv.this.pC.ys());
         }
      }
   }
}
