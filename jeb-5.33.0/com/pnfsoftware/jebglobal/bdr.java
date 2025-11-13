package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdr {
   private static final ILogger A = GlobalLog.getLogger(bdr.class);
   bdt pC;

   bdr(bdt var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bdr.oP pC(int var1, boolean var2, boolean var3) {
      bdq var4 = bdq.pC(var1);
      if (var1 >= bdq.TV.pC() || var1 == bdq.Bf.pC()) {
         return new bdr.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdr.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (bds.pC[var4.ordinal()]) {
               case 1:
               case 2:
               case 3:
                  return new bdr.Pj(var2, var3, var1);
               case 4:
               case 5:
               case 6:
                  if (var3) {
                     return new bdr.Pj(var2, var3, var1);
                  }
            }
         }

         switch (bds.pC[var4.ordinal()]) {
            case 1:
               Assert.a(!var2);
               return new bdr.gJ();
            case 2:
               Assert.a(!var2);
               return new bdr.DH();
            case 3:
               Assert.a(!var2);
               return new bdr.rQ();
            case 4:
            case 5:
            default:
               return null;
            case 6:
               return new bdr.m(var2, var3 && this.pC.wS != null);
            case 7:
               Assert.a(!var2);
               return new bdr.Sv();
            case 8:
               return new bdr.Sf();
            case 9:
               return new bdr.jM(var2, var3);
            case 10:
               Assert.a(!var2);
               return new bdr.ma();
            case 11:
               Assert.a(!var2);
               return new bdr.sy();
            case 12:
               Assert.a(!var2);
               return new bdr.K();
            case 13:
               Assert.a(!var2);
               return new bdr.yt();
            case 14:
               Assert.a(!var2);
               return new bdr.RC();
            case 15:
               Assert.a(!var2);
               return new bdr.Kr();
            case 16:
               Assert.a(!var2);
               return new bdr.p();
            case 17:
               Assert.a(!var2);
               throw new ToDoException();
            case 18:
               Assert.a(!var2);
               return new bdr.cq();
            case 19:
               Assert.a(!var2);
               return new bdr.Tb();
            case 20:
               Assert.a(!var2);
               return new bdr.KD();
            case 21:
               Assert.a(!var2);
               return null;
            case 22:
               Assert.a(!var2);
               return null;
            case 23:
               Assert.a(!var2);
               return new bdr.co();
            case 24:
               Assert.a(!var2);
               return null;
            case 25:
               Assert.a(!var2);
               return null;
            case 26:
               Assert.a(!var2);
               return new bdr.eW();
            case 27:
               Assert.a(!var2);
               return new bdr.GK();
            case 28:
               Assert.a(!var2);
               return null;
            case 29:
               Assert.a(!var2);
               return null;
            case 30:
               Assert.a(!var2);
               return null;
            case 31:
               return new bdr.Sb(var2, var3);
            case 32:
               return new bdr.HE(var2, var3);
            case 33:
               return new bdr.Hv(var2, var3);
            case 34:
               return new bdr.Ws(var2);
            case 35:
               return new bdr.uX(var2);
            case 36:
               return new bdr.zp(var2);
            case 37:
            case 38:
            case 39:
               return null;
            case 40:
               Assert.a(!var2);
               return new bdr.qt();
            case 41:
               Assert.a(!var2);
               return null;
            case 42:
               Assert.a(!var2);
               return null;
            case 43:
               Assert.a(!var2);
               return null;
            case 44:
               throw new RuntimeException();
            case 45:
               return new bdr.Mh(var2, var1);
            case 46:
               throw new RuntimeException();
            case 47:
               return new bdr.nA(var2, var1);
            case 48:
            case 49:
               return new bdr.Av(var2, var1);
            case 50:
               return new bdr.dE(var2, var1);
            case 51:
               return new bdr.gb(var2, var3);
            case 52:
               return new bdr.io(var2);
         }
      }
   }

   class Av extends bdr.bO {
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
            bbc var2 = bdr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdr.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdr.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdr.bO {
      int pC;

      public B(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdr.this.pC.WR(this.pC);
            var2.pC("data", bdr.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdr.bO {
      @Override
      public void pC() {
         this.A(bdq.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdr.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.DQ.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("parent", bdr.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("instructionsImage", null);
            int var3 = 0 | bdr.this.pC.vP() << 2;
            var2.pC("packedFields", var3);
         }
      }
   }

   class HE extends bdr.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdq.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdr.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdr.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdr.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdr.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdr.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdr.this.pC.kS());
         var1.pC("hash", bdr.this.pC.kS());
         var1.pC("typeParameters", bdr.this.pC.kS());
         var1.pC("resultType", bdr.this.pC.kS());
         var1.pC("parameterTypes", bdr.this.pC.kS());
         var1.pC("namedParameterNames", bdr.this.pC.kS());
      }
   }

   class Hv extends bdr.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdq.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdr.this.pC.fI());
            var2.pC("index", bdr.this.pC.fI());
            long var3 = bdr.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdr.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdr.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdr.this.pC.kS());
         var1.pC("hash", bdr.this.pC.kS());
         var1.pC("owner", bdr.this.pC.kS());
      }
   }

   class K extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            if (bdr.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdr.this.pC.kS());
            }

            var2.pC("parentFunction", bdr.this.pC.kS());
            var2.pC("closure", bdr.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdr.this.pC.E());
         }
      }
   }

   class KD extends bdr.bO {
      @Override
      public void pC() {
         this.A(bdq.Aj.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            long var3 = bdr.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdr.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdr.this.pC.sY());
               var9.put("outerTryIndex", bdr.this.pC.WR());
               var9.put("needsStacktrace", bdr.this.pC.A());
               var9.put("hasCatchAll", bdr.this.pC.A());
               var9.put("isGenerated", bdr.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            if (!bdr.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdr.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdr.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdr.this.pC.kS());
         if (bdr.this.pC.vP) {
            if (!bdr.this.pC.Sc) {
               var1.pC("resolvedUrl", bdr.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdr.this.pC.kS());
            var1.pC("resolvedUrl", bdr.this.pC.kS());
            var1.pC("lineStarts", bdr.this.pC.kS());
            var1.pC("constantCoverage", bdr.this.pC.kS());
            var1.pC("debugPositions", bdr.this.pC.kS());
            var1.pC("kernelProgramInfo", bdr.this.pC.kS());
         }
      }
   }

   class Mh extends bdr.bO {
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
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdr.this.pC.kS());
         var1.pC("hashMask", bdr.this.pC.kS());
         var1.pC("data", bdr.this.pC.kS());
         var1.pC("usedData", bdr.this.pC.kS());
         var1.pC("deletedKeys", bdr.this.pC.kS());
      }
   }

   class Pj extends bdr.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdr.this.pC.mv;
         long var1 = bdr.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdr.this.pC.E() << (int)bdr.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdr.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdr.this.pC.pC(var7);
         }

         this.sY = bdr.this.pC.mv;
         if (this.pC == bdq.eE.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bdq.dM.pC() && this.pC != bdq.eE.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdr.this.pC.E.position(var1);
            bdr.this.pC.E.i32();
            long var2;
            if (bdr.this.pC.FE) {
               bdr.this.pC.E.i32();
               var2 = bdr.this.pC.E.i64();
            } else {
               bdr.this.pC.E.i32();
               var2 = bdr.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdr.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdr.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdr.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdr.this.pC.kS());
         var1.pC("owner", bdr.this.pC.kS());
         var1.pC("type", bdr.this.pC.kS());
         var1.pC("initializerFunction", bdr.this.pC.kS());
      }
   }

   class Sb extends bdr.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdq.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdr.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdr.this.pC.kS());
         var1.pC("hash", bdr.this.pC.kS());
         var1.pC("arguments", bdr.this.pC.kS());
      }
   }

   class Sf extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.Kq.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdr.this.pC.kS());
         var1.pC("flags", bdr.this.pC.kS());
         var1.pC("bounds", bdr.this.pC.kS());
         var1.pC("defaults", bdr.this.pC.kS());
      }
   }

   class Sv extends bdr.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdr.this.pC.mv;
         long var1 = bdr.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdr.this.pC.pC(bdq.E.pC());
            var2.pC("id", bdr.this.pC.wS());
            bdr.this.pC.pC(var2);
         });
         this.A = bdr.this.pC.mv;
         this.pC(bdq.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdr.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdr.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdr.this.pC.ah && !bdr.this.pC.vP) {
                  var8.pC("kernelOffset", bdr.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdr.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdr.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdr.this.pC.ys());
               if (!bdr.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdr.this.pC.ys());
               var8.pC("numNativeFields", bdr.this.pC.E());
               if (!bdr.this.pC.ah) {
                  Assert.a(!bdr.this.pC.vP);
                  var8.pC("tokenPos", bdr.this.pC.UT());
                  var8.pC("endTokenPos", bdr.this.pC.UT());
               }

               var8.pC("stateBits", bdr.this.pC.E());
               if (bdr.this.pC.ah) {
                  if (var7) {
                     bdr.this.pC.E();
                  } else if (!bdr.this.pC.ys.pC(var9)) {
                     bdr.this.pC.os.put(var9, bdr.this.pC.E());
                  }
               }

               bdr.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdr.this.pC.kS());
         if (!bdr.this.pC.Sc) {
            var1.pC("userName", bdr.this.pC.kS());
         }

         var1.pC("functions", bdr.this.pC.kS());
         var1.pC("functionsHashTable", bdr.this.pC.kS());
         var1.pC("fields", bdr.this.pC.kS());
         var1.pC("offsetInWordsToField", bdr.this.pC.kS());
         var1.pC("interfaces", bdr.this.pC.kS());
         var1.pC("script", bdr.this.pC.kS());
         var1.pC("library", bdr.this.pC.kS());
         var1.pC("typeParameters", bdr.this.pC.kS());
         var1.pC("superType", bdr.this.pC.kS());
         var1.pC("constants", bdr.this.pC.kS());
         var1.pC("declarationType", bdr.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdr.this.pC.kS());
         if (!bdr.this.pC.Sc || !bdr.this.pC.ah) {
            var1.pC("directImplementors", bdr.this.pC.kS());
            var1.pC("directSubclasses", bdr.this.pC.kS());
         }

         if (!bdr.this.pC.ah) {
            var1.pC("allocationStub", bdr.this.pC.kS());
            var1.pC("dependentCode", bdr.this.pC.kS());
         }
      }
   }

   class Tb extends bdr.bO {
      @Override
      public void pC() {
         this.A(bdq.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdr.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdr.this.pC.NS(var6);
               Assert.a(var8 != bdr.this.pC.ys.mv);
               if (var8 == bdr.this.pC.ys.LM) {
                  int var9 = bdr.this.pC.E(var6);
                  if (var9 == bdr.this.pC.ys.Ab) {
                     long var10 = bdr.this.pC.kS();
                     var7.put("rawObj", var10);
                  } else if (var9 == bdr.this.pC.ys.rl) {
                     long var12 = bdr.this.pC.ys();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdr.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdr.this.pC.ys.sO && var8 != bdr.this.pC.ys.os) {
                  if (var8 != bdr.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdr.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdq.gj.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdr.this.pC.kS());
         var1.pC("functionTypeArguments", bdr.this.pC.kS());
         var1.pC("delayedTypeArguments", bdr.this.pC.kS());
         var1.pC("function", bdr.this.pC.kS());
         var1.pC("context", bdr.this.pC.kS());
         var1.pC("hash", bdr.this.pC.kS());
      }
   }

   abstract class bO implements bdr.oP {
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
         this.E = bdr.this.pC.mv;
         long var2 = bdr.this.pC.E();
         bdr.this.pC.pC(var2, var1);
         this.sY = bdr.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdr.this.pC.mv;
         long var2 = bdr.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdr.this.pC.pC(var1);
            long var4 = bdr.this.pC.E();
            var3.pC("length", var4);
            bdr.this.pC.pC(var3);
         });
         this.sY = bdr.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdr.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdr.this.pC.E();
            long var1 = bdr.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdr.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.os.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdr.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdr.this.pC.kS());
         var1.pC("argsDescriptor", bdr.this.pC.kS());
      }
   }

   class cq extends bdr.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdr.this.pC.mv;
         bdr.this.pC.Bf = this.E;
         long var1 = bdr.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdr.this.pC.mv;
         this.pC = bdr.this.pC.mv;
         var1 = bdr.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdr.this.pC.mv;
      }

      private void wS() {
         int var1 = bdr.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdr.this.pC.pC(bdq.ah.pC());
         bdr.this.pC.pC(var3);
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
         bbc var3 = bdr.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdr.this.pC.vP) {
            var3.pC("objectPool", bdr.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdr.this.pC.kS(), bdq.ys.toString(), bdq.E.toString(), bdq.e.toString());
         var3.pC("exceptionHandlers", bdr.this.pC.kS(), bdq.Aj.toString());
         var3.pC("pcDescriptors", bdr.this.pC.kS(), bdq.Ek.toString());
         var3.pC("catchEntry", bdr.this.pC.kS());
         if (bdr.this.pC.NS) {
            var3.pC("compressedStackMaps", bdr.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdr.this.pC.kS(), bdq.Xh.toString());
         var3.pC("codeSourceMap", bdr.this.pC.kS(), bdq.hK.toString());
         if (!bdr.this.pC.ah && bdr.this.pC.NS) {
            var3.pC("deoptInfoArray", bdr.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdr.this.pC.kS());
         }

         if (!bdr.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdr.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdr.this.pC.rl ? bdr.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdr.this.pC.ah) {
            long var3 = bdr.this.pC.pF[2 * bdr.this.pC.OI];
            long var5 = bdr.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdr.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdr.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdr.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdr.bO {
      int pC;

      dE(boolean var2, int var3) {
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
            bbc var2 = bdr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdr.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("cache", bdr.this.pC.kS());
            var2.pC("numInputs", bdr.this.pC.NS());
            var2.pC("numOccupied", bdr.this.pC.NS());
         }
      }
   }

   class gJ extends bdr.bO {
      @Override
      public void pC() {
         this.A(bdq.Ek.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdr.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdr.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdq.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdr.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdr.this.pC.kS());
         var1.pC("hash", bdr.this.pC.kS());
         var1.pC("shape", bdr.this.pC.kS());
         var1.pC("fieldTypes", bdr.this.pC.kS());
      }
   }

   class io extends bdr.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bdq.PZ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdr.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdr.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdr.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bdq.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdr.this.pC.vP());
            var2.pC("nullabity", bdr.this.pC.E());
            var2.pC("instantiations", bdr.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdr.this.pC.kS());
            }
         }
      }
   }

   class m extends bdr.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdr.this.pC.mv;
         long var1 = bdr.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdr.this.pC.E();
            bdq var6 = (var4 & 1L) != 0L ? bdq.EM : bdq.dM;
            long var7 = var4 >> 1;
            bbc var9 = bdr.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdr.this.pC.pC(var9);
         }

         this.sY = bdr.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            long var3 = bdr.this.pC.E();
            long var5 = var3 >> 1;
            bdq var7 = (var3 & 1L) != 0L ? bdq.EM : bdq.dM;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bdq.dM) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdr.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdr.this.pC.gp();
                  var10 |= bdr.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            if (!bdr.this.pC.ah && !bdr.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdr.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdr.this.pC.kS());
         var1.pC("script", bdr.this.pC.kS());
         if (!bdr.this.pC.vP) {
            var1.pC("libraryKernelData", bdr.this.pC.kS());
         }
      }
   }

   class nA extends bdr.bO {
      int pC;

      nA(boolean var2, int var3) {
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
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdr.this.pC.kS());
         var1.pC("hashMask", bdr.this.pC.kS());
         var1.pC("data", bdr.this.pC.kS());
         var1.pC("usedData", bdr.this.pC.kS());
         var1.pC("deletedKeys", bdr.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdr.this.pC.vP());
            var2.pC("numImports", bdr.this.pC.fI());
            var2.pC("loadState", bdr.this.pC.oT());
            var2.pC("flags", bdr.this.pC.gp());
            if (!bdr.this.pC.ah && !bdr.this.pC.vP) {
               var2.pC("kernelOffset", bdr.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdr.this.pC.kS());
         var1.pC("url", bdr.this.pC.kS());
         var1.pC("privateKey", bdr.this.pC.kS());
         var1.pC("dictionary", bdr.this.pC.kS());
         var1.pC("metadata", bdr.this.pC.kS());
         var1.pC("toplevelClass", bdr.this.pC.kS());
         var1.pC("usedScripts", bdr.this.pC.kS());
         var1.pC("loadingUnit", bdr.this.pC.kS());
         var1.pC("imports", bdr.this.pC.kS());
         var1.pC("exports", bdr.this.pC.kS());
         if (!bdr.this.pC.vP) {
            var1.pC("dependencies", bdr.this.pC.kS());
            var1.pC("kernelProgramInfo", bdr.this.pC.kS());
         }
      }
   }

   class qt extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.QQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdr.this.pC.kS());
         var1.pC("length", bdr.this.pC.kS());
         var1.pC("data", bdr.this.pC.kS());
      }
   }

   class rQ extends bdr.bO {
      @Override
      public void pC() {
         this.A(bdq.Er.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            long var3 = bdr.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdr.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            if (!bdr.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdr.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdr.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdr.this.pC.kS(var5);
               if (var6.pC().equals(bdq.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdr.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdr.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdr.this.pC.kS());
         var1.pC("owner", bdr.this.pC.kS());
         var1.pC("signature", bdr.this.pC.kS(), bdq.xM.toString());
         var1.pC("data", bdr.this.pC.kS());
      }
   }

   class uX extends bdr.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdr.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdr.this.pC.pC(bdq.VD.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdr.this.pC.xC());
            bdr.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdr.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdr.this.pC.mv;
         long var1 = bdr.this.pC.E();
         this.A = bdr.this.pC.vP();
         this.kS = bdr.this.pC.vP();
         bdr.this.pC.pC(var1, this.pC);
         this.sY = bdr.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdr.this.pC.ys.ys;
         int var2 = (int)bdr.this.pC.pC(this.kS * bdr.this.pC.ys.sY, bdr.this.pC.ys.ld);
         Long var3 = bdr.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdr.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdr.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdr.this.pC.ys.sY)) {
               if (bdr.this.pC.A(var3, var6 / (int)bdr.this.pC.ys.sY)) {
                  bdr.this.pC.Sc();
               } else {
                  bdr.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdr.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdr.bO {
      @Override
      public void pC() {
         this.pC(bdq.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdr.this.pC.E());
            var2.pC("callbackKind", bdr.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdr.this.pC.kS());
         var1.pC("cSignature", bdr.this.pC.kS());
         var1.pC("callbackTarget", bdr.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdr.this.pC.kS());
      }
   }

   class zp extends bdr.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdq.Xs.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdr.this.pC.ED());
         }
      }
   }
}
