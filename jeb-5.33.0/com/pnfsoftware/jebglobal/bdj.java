package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdj {
   private static final ILogger A = GlobalLog.getLogger(bdj.class);
   bdl pC;

   bdj(bdl var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bdj.oP pC(int var1, boolean var2, boolean var3) {
      bdi var4 = bdi.pC(var1);
      if (var1 >= bdi.Ig.pC() || var1 == bdi.OI.pC()) {
         return new bdj.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdj.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (bdk.pC[var4.ordinal()]) {
               case 1:
               case 2:
               case 3:
                  return new bdj.Pj(var2, var3, var1);
               case 4:
               case 5:
               case 6:
                  if (var3) {
                     return new bdj.Pj(var2, var3, var1);
                  }
            }
         }

         switch (bdk.pC[var4.ordinal()]) {
            case 1:
               Assert.a(!var2);
               return new bdj.gJ();
            case 2:
               Assert.a(!var2);
               return new bdj.DH();
            case 3:
               Assert.a(!var2);
               return new bdj.rQ();
            case 4:
            case 5:
            default:
               return null;
            case 6:
               return new bdj.m(var2, var3 && this.pC.wS != null);
            case 7:
               Assert.a(!var2);
               return new bdj.Sv();
            case 8:
               return new bdj.Sf();
            case 9:
               return new bdj.jM(var2, var3);
            case 10:
               Assert.a(!var2);
               return new bdj.ma();
            case 11:
               Assert.a(!var2);
               return new bdj.sy();
            case 12:
               Assert.a(!var2);
               return new bdj.K();
            case 13:
               Assert.a(!var2);
               return new bdj.yt();
            case 14:
               Assert.a(!var2);
               return new bdj.RC();
            case 15:
               Assert.a(!var2);
               return new bdj.Kr();
            case 16:
               Assert.a(!var2);
               return new bdj.p();
            case 17:
               Assert.a(!var2);
               throw new ToDoException();
            case 18:
               Assert.a(!var2);
               return new bdj.cq();
            case 19:
               Assert.a(!var2);
               return new bdj.Tb();
            case 20:
               Assert.a(!var2);
               return new bdj.KD();
            case 21:
               Assert.a(!var2);
               return null;
            case 22:
               Assert.a(!var2);
               return null;
            case 23:
               Assert.a(!var2);
               return new bdj.co();
            case 24:
               Assert.a(!var2);
               return null;
            case 25:
               Assert.a(!var2);
               return null;
            case 26:
               Assert.a(!var2);
               return new bdj.eW();
            case 27:
               Assert.a(!var2);
               return new bdj.GK();
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
               return new bdj.Sb(var2, var3);
            case 32:
               return new bdj.HE(var2, var3);
            case 33:
               return new bdj.Hv(var2, var3);
            case 34:
               return new bdj.Ws(var2);
            case 35:
               return new bdj.uX(var2);
            case 36:
               return new bdj.zp(var2);
            case 37:
            case 38:
            case 39:
               return null;
            case 40:
               Assert.a(!var2);
               return new bdj.qt();
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
               return new bdj.Mh(var2, var1);
            case 46:
               throw new RuntimeException();
            case 47:
               return new bdj.nA(var2, var1);
            case 48:
            case 49:
               return new bdj.Av(var2, var1);
            case 50:
               return new bdj.dE(var2, var1);
            case 51:
               return new bdj.gb(var2, var3);
            case 52:
               return new bdj.io(var2);
         }
      }
   }

   class Av extends bdj.bO {
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
            bbc var2 = bdj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdj.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdj.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdj.bO {
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
            bbc var2 = bdj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdj.this.pC.WR(this.pC);
            var2.pC("data", bdj.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdj.bO {
      @Override
      public void pC() {
         this.A(bdi.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdj.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("parent", bdj.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("instructionsImage", null);
            int var3 = 0 | bdj.this.pC.vP() << 2;
            var2.pC("packedFields", var3);
         }
      }
   }

   class HE extends bdj.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdi.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdj.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdj.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdj.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdj.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdj.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdj.this.pC.kS());
         var1.pC("hash", bdj.this.pC.kS());
         var1.pC("typeParameters", bdj.this.pC.kS());
         var1.pC("resultType", bdj.this.pC.kS());
         var1.pC("parameterTypes", bdj.this.pC.kS());
         var1.pC("namedParameterNames", bdj.this.pC.kS());
      }
   }

   class Hv extends bdj.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdi.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdj.this.pC.fI());
            var2.pC("index", bdj.this.pC.fI());
            long var3 = bdj.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdj.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdj.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdj.this.pC.kS());
         var1.pC("hash", bdj.this.pC.kS());
         var1.pC("owner", bdj.this.pC.kS());
      }
   }

   class K extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            if (bdj.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdj.this.pC.kS());
            }

            var2.pC("parentFunction", bdj.this.pC.kS());
            var2.pC("closure", bdj.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdj.this.pC.E());
         }
      }
   }

   class KD extends bdj.bO {
      @Override
      public void pC() {
         this.A(bdi.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            long var3 = bdj.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdj.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdj.this.pC.sY());
               var9.put("outerTryIndex", bdj.this.pC.WR());
               var9.put("needsStacktrace", bdj.this.pC.A());
               var9.put("hasCatchAll", bdj.this.pC.A());
               var9.put("isGenerated", bdj.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            if (!bdj.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdj.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdj.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdj.this.pC.kS());
         if (bdj.this.pC.vP) {
            if (!bdj.this.pC.Sc) {
               var1.pC("resolvedUrl", bdj.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdj.this.pC.kS());
            var1.pC("resolvedUrl", bdj.this.pC.kS());
            var1.pC("lineStarts", bdj.this.pC.kS());
            var1.pC("constantCoverage", bdj.this.pC.kS());
            var1.pC("debugPositions", bdj.this.pC.kS());
            var1.pC("kernelProgramInfo", bdj.this.pC.kS());
         }
      }
   }

   class Mh extends bdj.bO {
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
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdj.this.pC.kS());
         var1.pC("hashMask", bdj.this.pC.kS());
         var1.pC("data", bdj.this.pC.kS());
         var1.pC("usedData", bdj.this.pC.kS());
         var1.pC("deletedKeys", bdj.this.pC.kS());
      }
   }

   class Pj extends bdj.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdj.this.pC.mv;
         long var1 = bdj.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdj.this.pC.E() << (int)bdj.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdj.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdj.this.pC.pC(var7);
         }

         this.sY = bdj.this.pC.mv;
         if (this.pC == bdi.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bdi.eE.pC() && this.pC != bdi.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdj.this.pC.E.position(var1);
            bdj.this.pC.E.i32();
            long var2;
            if (bdj.this.pC.FE) {
               bdj.this.pC.E.i32();
               var2 = bdj.this.pC.E.i64();
            } else {
               bdj.this.pC.E.i32();
               var2 = bdj.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdj.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdj.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdj.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdj.this.pC.kS());
         var1.pC("owner", bdj.this.pC.kS());
         var1.pC("type", bdj.this.pC.kS());
         var1.pC("initializerFunction", bdj.this.pC.kS());
      }
   }

   class Sb extends bdj.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdi.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdj.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdj.this.pC.kS());
         var1.pC("hash", bdj.this.pC.kS());
         var1.pC("arguments", bdj.this.pC.kS());
      }
   }

   class Sf extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdj.this.pC.kS());
         var1.pC("flags", bdj.this.pC.kS());
         var1.pC("bounds", bdj.this.pC.kS());
         var1.pC("defaults", bdj.this.pC.kS());
      }
   }

   class Sv extends bdj.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdj.this.pC.mv;
         long var1 = bdj.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdj.this.pC.pC(bdi.E.pC());
            var2.pC("id", bdj.this.pC.wS());
            bdj.this.pC.pC(var2);
         });
         this.A = bdj.this.pC.mv;
         this.pC(bdi.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdj.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdj.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdj.this.pC.ah && !bdj.this.pC.vP) {
                  var8.pC("kernelOffset", bdj.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdj.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdj.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdj.this.pC.ys());
               if (!bdj.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdj.this.pC.ys());
               var8.pC("numNativeFields", bdj.this.pC.E());
               if (!bdj.this.pC.ah) {
                  Assert.a(!bdj.this.pC.vP);
                  var8.pC("tokenPos", bdj.this.pC.UT());
                  var8.pC("endTokenPos", bdj.this.pC.UT());
               }

               var8.pC("stateBits", bdj.this.pC.E());
               if (bdj.this.pC.ah) {
                  if (var7) {
                     bdj.this.pC.E();
                  } else if (!bdj.this.pC.ys.pC(var9)) {
                     bdj.this.pC.os.put(var9, bdj.this.pC.E());
                  }
               }

               bdj.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdj.this.pC.kS());
         if (!bdj.this.pC.Sc) {
            var1.pC("userName", bdj.this.pC.kS());
         }

         var1.pC("functions", bdj.this.pC.kS());
         var1.pC("functionsHashTable", bdj.this.pC.kS());
         var1.pC("fields", bdj.this.pC.kS());
         var1.pC("offsetInWordsToField", bdj.this.pC.kS());
         var1.pC("interfaces", bdj.this.pC.kS());
         var1.pC("script", bdj.this.pC.kS());
         var1.pC("library", bdj.this.pC.kS());
         var1.pC("typeParameters", bdj.this.pC.kS());
         var1.pC("superType", bdj.this.pC.kS());
         var1.pC("constants", bdj.this.pC.kS());
         var1.pC("declarationType", bdj.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdj.this.pC.kS());
         if (!bdj.this.pC.Sc || !bdj.this.pC.ah) {
            var1.pC("directImplementors", bdj.this.pC.kS());
            var1.pC("directSubclasses", bdj.this.pC.kS());
         }

         if (!bdj.this.pC.ah) {
            var1.pC("allocationStub", bdj.this.pC.kS());
            var1.pC("dependentCode", bdj.this.pC.kS());
         }
      }
   }

   class Tb extends bdj.bO {
      @Override
      public void pC() {
         this.A(bdi.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdj.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdj.this.pC.NS(var6);
               Assert.a(var8 != bdj.this.pC.ys.mv);
               if (var8 == bdj.this.pC.ys.LM) {
                  int var9 = bdj.this.pC.E(var6);
                  if (var9 == bdj.this.pC.ys.Ab) {
                     long var10 = bdj.this.pC.kS();
                     var7.put("rawObj", var10);
                  } else if (var9 == bdj.this.pC.ys.rl) {
                     long var12 = bdj.this.pC.ys();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdj.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdj.this.pC.ys.sO && var8 != bdj.this.pC.ys.os) {
                  if (var8 != bdj.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdj.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdi.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdj.this.pC.kS());
         var1.pC("functionTypeArguments", bdj.this.pC.kS());
         var1.pC("delayedTypeArguments", bdj.this.pC.kS());
         var1.pC("function", bdj.this.pC.kS());
         var1.pC("context", bdj.this.pC.kS());
         var1.pC("hash", bdj.this.pC.kS());
      }
   }

   abstract class bO implements bdj.oP {
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
         this.E = bdj.this.pC.mv;
         long var2 = bdj.this.pC.E();
         bdj.this.pC.pC(var2, var1);
         this.sY = bdj.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdj.this.pC.mv;
         long var2 = bdj.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdj.this.pC.pC(var1);
            long var4 = bdj.this.pC.E();
            var3.pC("length", var4);
            bdj.this.pC.pC(var3);
         });
         this.sY = bdj.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdj.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdj.this.pC.E();
            long var1 = bdj.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdj.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdj.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdj.this.pC.kS());
         var1.pC("argsDescriptor", bdj.this.pC.kS());
      }
   }

   class cq extends bdj.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdj.this.pC.mv;
         bdj.this.pC.Bf = this.E;
         long var1 = bdj.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdj.this.pC.mv;
         this.pC = bdj.this.pC.mv;
         var1 = bdj.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdj.this.pC.mv;
      }

      private void wS() {
         int var1 = bdj.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdj.this.pC.pC(bdi.ah.pC());
         bdj.this.pC.pC(var3);
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
         bbc var3 = bdj.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdj.this.pC.vP) {
            var3.pC("objectPool", bdj.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdj.this.pC.kS(), bdi.ys.toString(), bdi.E.toString(), bdi.RW.toString());
         var3.pC("exceptionHandlers", bdj.this.pC.kS(), bdi.FE.toString());
         var3.pC("pcDescriptors", bdj.this.pC.kS(), bdi.z.toString());
         var3.pC("catchEntry", bdj.this.pC.kS());
         if (bdj.this.pC.NS) {
            var3.pC("compressedStackMaps", bdj.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdj.this.pC.kS(), bdi.be.toString());
         var3.pC("codeSourceMap", bdj.this.pC.kS(), bdi.Ek.toString());
         if (!bdj.this.pC.ah && bdj.this.pC.NS) {
            var3.pC("deoptInfoArray", bdj.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdj.this.pC.kS());
         }

         if (!bdj.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdj.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdj.this.pC.rl ? bdj.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdj.this.pC.ah) {
            long var3 = bdj.this.pC.pF[2 * bdj.this.pC.OI];
            long var5 = bdj.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdj.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdj.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdj.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdj.bO {
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
            bbc var2 = bdj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdj.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("cache", bdj.this.pC.kS());
            var2.pC("numInputs", bdj.this.pC.NS());
            var2.pC("numOccupied", bdj.this.pC.NS());
         }
      }
   }

   class gJ extends bdj.bO {
      @Override
      public void pC() {
         this.A(bdi.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdj.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdj.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdi.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdj.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdj.this.pC.kS());
         var1.pC("hash", bdj.this.pC.kS());
         var1.pC("shape", bdj.this.pC.kS());
         var1.pC("fieldTypes", bdj.this.pC.kS());
      }
   }

   class io extends bdj.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bdi.wQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdj.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdj.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdj.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bdi.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdj.this.pC.vP());
            var2.pC("nullabity", bdj.this.pC.E());
            var2.pC("instantiations", bdj.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdj.this.pC.kS());
            }
         }
      }
   }

   class m extends bdj.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdj.this.pC.mv;
         long var1 = bdj.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdj.this.pC.E();
            bdi var6 = (var4 & 1L) != 0L ? bdi.dM : bdi.eE;
            long var7 = var4 >> 1;
            bbc var9 = bdj.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdj.this.pC.pC(var9);
         }

         this.sY = bdj.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            long var3 = bdj.this.pC.E();
            long var5 = var3 >> 1;
            bdi var7 = (var3 & 1L) != 0L ? bdi.dM : bdi.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bdi.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdj.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdj.this.pC.gp();
                  var10 |= bdj.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            if (!bdj.this.pC.ah && !bdj.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdj.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdj.this.pC.kS());
         var1.pC("script", bdj.this.pC.kS());
         if (!bdj.this.pC.vP) {
            var1.pC("libraryKernelData", bdj.this.pC.kS());
         }
      }
   }

   class nA extends bdj.bO {
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
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdj.this.pC.kS());
         var1.pC("hashMask", bdj.this.pC.kS());
         var1.pC("data", bdj.this.pC.kS());
         var1.pC("usedData", bdj.this.pC.kS());
         var1.pC("deletedKeys", bdj.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdj.this.pC.vP());
            var2.pC("numImports", bdj.this.pC.fI());
            var2.pC("loadState", bdj.this.pC.oT());
            var2.pC("flags", bdj.this.pC.gp());
            if (!bdj.this.pC.ah && !bdj.this.pC.vP) {
               var2.pC("kernelOffset", bdj.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdj.this.pC.kS());
         var1.pC("url", bdj.this.pC.kS());
         var1.pC("privateKey", bdj.this.pC.kS());
         var1.pC("dictionary", bdj.this.pC.kS());
         var1.pC("metadata", bdj.this.pC.kS());
         var1.pC("toplevelClass", bdj.this.pC.kS());
         var1.pC("usedScripts", bdj.this.pC.kS());
         var1.pC("loadingUnit", bdj.this.pC.kS());
         var1.pC("imports", bdj.this.pC.kS());
         var1.pC("exports", bdj.this.pC.kS());
         if (!bdj.this.pC.vP) {
            var1.pC("dependencies", bdj.this.pC.kS());
            var1.pC("kernelProgramInfo", bdj.this.pC.kS());
         }
      }
   }

   class qt extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdj.this.pC.kS());
         var1.pC("length", bdj.this.pC.kS());
         var1.pC("data", bdj.this.pC.kS());
      }
   }

   class rQ extends bdj.bO {
      @Override
      public void pC() {
         this.A(bdi.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            long var3 = bdj.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdj.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            if (!bdj.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdj.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdj.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdj.this.pC.kS(var5);
               if (var6.pC().equals(bdi.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdj.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdj.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdj.this.pC.kS());
         var1.pC("owner", bdj.this.pC.kS());
         var1.pC("signature", bdj.this.pC.kS(), bdi.e.toString());
         var1.pC("data", bdj.this.pC.kS());
      }
   }

   class uX extends bdj.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdj.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdj.this.pC.pC(bdi.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdj.this.pC.xC());
            bdj.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdj.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdj.this.pC.mv;
         long var1 = bdj.this.pC.E();
         this.A = bdj.this.pC.vP();
         this.kS = bdj.this.pC.vP();
         bdj.this.pC.pC(var1, this.pC);
         this.sY = bdj.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdj.this.pC.ys.ys;
         int var2 = (int)bdj.this.pC.pC(this.kS * bdj.this.pC.ys.sY, bdj.this.pC.ys.ld);
         Long var3 = bdj.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdj.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdj.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdj.this.pC.ys.sY)) {
               if (bdj.this.pC.A(var3, var6 / (int)bdj.this.pC.ys.sY)) {
                  bdj.this.pC.Sc();
               } else {
                  bdj.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdj.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdj.bO {
      @Override
      public void pC() {
         this.pC(bdi.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdj.this.pC.E());
            var2.pC("callbackKind", bdj.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdj.this.pC.kS());
         var1.pC("cSignature", bdj.this.pC.kS());
         var1.pC("callbackTarget", bdj.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdj.this.pC.kS());
      }
   }

   class zp extends bdj.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdi.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdj.this.pC.ED());
         }
      }
   }
}
