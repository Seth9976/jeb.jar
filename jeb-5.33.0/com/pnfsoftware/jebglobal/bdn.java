package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdn {
   private static final ILogger A = GlobalLog.getLogger(bdn.class);
   bdp pC;

   bdn(bdp var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bdn.oP pC(int var1, boolean var2, boolean var3) {
      bdm var4 = bdm.pC(var1);
      if (var1 >= bdm.TV.pC() || var1 == bdm.Bf.pC()) {
         return new bdn.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdn.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case Ek:
               case hK:
               case Er:
                  return new bdn.Pj(var2, var3, var1);
               case dM:
               case EM:
               case eE:
                  if (var3) {
                     return new bdn.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Ek:
               Assert.a(!var2);
               return new bdn.gJ();
            case hK:
               Assert.a(!var2);
               return new bdn.DH();
            case Er:
               Assert.a(!var2);
               return new bdn.rQ();
            case dM:
            case EM:
            default:
               return null;
            case eE:
               return new bdn.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bdn.Sv();
            case ld:
               return new bdn.Sf();
            case ck:
               return new bdn.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bdn.ma();
            case ys:
               Assert.a(!var2);
               return new bdn.sy();
            case gp:
               Assert.a(!var2);
               return new bdn.K();
            case oT:
               Assert.a(!var2);
               return new bdn.yt();
            case fI:
               Assert.a(!var2);
               return new bdn.RC();
            case WR:
               Assert.a(!var2);
               return new bdn.Kr();
            case NS:
               Assert.a(!var2);
               return new bdn.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bdn.cq();
            case z:
               Assert.a(!var2);
               return new bdn.Tb();
            case Aj:
               Assert.a(!var2);
               return new bdn.KD();
            case EX:
               Assert.a(!var2);
               return null;
            case LM:
               Assert.a(!var2);
               return null;
            case os:
               Assert.a(!var2);
               return new bdn.co();
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return null;
            case cX:
               Assert.a(!var2);
               return new bdn.eW();
            case DQ:
               Assert.a(!var2);
               return new bdn.GK();
            case pF:
               Assert.a(!var2);
               return null;
            case Bc:
               Assert.a(!var2);
               return null;
            case Pe:
               Assert.a(!var2);
               return null;
            case e:
               return new bdn.Sb(var2, var3);
            case xM:
               return new bdn.HE(var2, var3);
            case Kq:
               return new bdn.Hv(var2, var3);
            case gj:
               return new bdn.Ws(var2);
            case VD:
               return new bdn.uX(var2);
            case Xs:
               return new bdn.zp(var2);
            case Bi:
            case FK:
            case wQ:
               return null;
            case QQ:
               Assert.a(!var2);
               return new bdn.qt();
            case So:
               Assert.a(!var2);
               return null;
            case Gm:
               Assert.a(!var2);
               return null;
            case Br:
               Assert.a(!var2);
               return null;
            case Rq:
               throw new RuntimeException();
            case LL:
               return new bdn.Mh(var2, var1);
            case rC:
               throw new RuntimeException();
            case be:
               return new bdn.nA(var2, var1);
            case Xh:
            case sz:
               return new bdn.Av(var2, var1);
            case Sc:
               return new bdn.dE(var2, var1);
            case kU:
               return new bdn.gb(var2, var3);
            case PZ:
               return new bdn.io(var2);
         }
      }
   }

   class Av extends bdn.bO {
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
            bbc var2 = bdn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdn.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdn.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdn.bO {
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
            bbc var2 = bdn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdn.this.pC.WR(this.pC);
            var2.pC("data", bdn.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdn.bO {
      @Override
      public void pC() {
         this.A(bdm.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdn.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.DQ.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("parent", bdn.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("instructionsImage", null);
            int var3 = 0 | bdn.this.pC.vP() << 2;
            var2.pC("packedFields", var3);
         }
      }
   }

   class HE extends bdn.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdm.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdn.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdn.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdn.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdn.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdn.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdn.this.pC.kS());
         var1.pC("hash", bdn.this.pC.kS());
         var1.pC("typeParameters", bdn.this.pC.kS());
         var1.pC("resultType", bdn.this.pC.kS());
         var1.pC("parameterTypes", bdn.this.pC.kS());
         var1.pC("namedParameterNames", bdn.this.pC.kS());
      }
   }

   class Hv extends bdn.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdm.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdn.this.pC.fI());
            var2.pC("index", bdn.this.pC.fI());
            long var3 = bdn.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdn.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdn.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdn.this.pC.kS());
         var1.pC("hash", bdn.this.pC.kS());
         var1.pC("owner", bdn.this.pC.kS());
      }
   }

   class K extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            if (bdn.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdn.this.pC.kS());
            }

            var2.pC("parentFunction", bdn.this.pC.kS());
            var2.pC("closure", bdn.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdn.this.pC.E());
         }
      }
   }

   class KD extends bdn.bO {
      @Override
      public void pC() {
         this.A(bdm.Aj.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            long var3 = bdn.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdn.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdn.this.pC.sY());
               var9.put("outerTryIndex", bdn.this.pC.WR());
               var9.put("needsStacktrace", bdn.this.pC.A());
               var9.put("hasCatchAll", bdn.this.pC.A());
               var9.put("isGenerated", bdn.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            if (!bdn.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdn.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdn.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdn.this.pC.kS());
         if (bdn.this.pC.vP) {
            if (!bdn.this.pC.Sc) {
               var1.pC("resolvedUrl", bdn.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdn.this.pC.kS());
            var1.pC("resolvedUrl", bdn.this.pC.kS());
            var1.pC("lineStarts", bdn.this.pC.kS());
            var1.pC("constantCoverage", bdn.this.pC.kS());
            var1.pC("debugPositions", bdn.this.pC.kS());
            var1.pC("kernelProgramInfo", bdn.this.pC.kS());
         }
      }
   }

   class Mh extends bdn.bO {
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
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdn.this.pC.kS());
         var1.pC("hashMask", bdn.this.pC.kS());
         var1.pC("data", bdn.this.pC.kS());
         var1.pC("usedData", bdn.this.pC.kS());
         var1.pC("deletedKeys", bdn.this.pC.kS());
      }
   }

   class Pj extends bdn.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdn.this.pC.mv;
         long var1 = bdn.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdn.this.pC.E() << (int)bdn.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdn.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdn.this.pC.pC(var7);
         }

         this.sY = bdn.this.pC.mv;
         if (this.pC == bdm.eE.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bdm.dM.pC() && this.pC != bdm.eE.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdn.this.pC.E.position(var1);
            bdn.this.pC.E.i32();
            long var2;
            if (bdn.this.pC.FE) {
               bdn.this.pC.E.i32();
               var2 = bdn.this.pC.E.i64();
            } else {
               bdn.this.pC.E.i32();
               var2 = bdn.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdn.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdn.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdn.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdn.this.pC.kS());
         var1.pC("owner", bdn.this.pC.kS());
         var1.pC("type", bdn.this.pC.kS());
         var1.pC("initializerFunction", bdn.this.pC.kS());
      }
   }

   class Sb extends bdn.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdm.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdn.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdn.this.pC.kS());
         var1.pC("hash", bdn.this.pC.kS());
         var1.pC("arguments", bdn.this.pC.kS());
      }
   }

   class Sf extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.Kq.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdn.this.pC.kS());
         var1.pC("flags", bdn.this.pC.kS());
         var1.pC("bounds", bdn.this.pC.kS());
         var1.pC("defaults", bdn.this.pC.kS());
      }
   }

   class Sv extends bdn.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdn.this.pC.mv;
         long var1 = bdn.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdn.this.pC.pC(bdm.E.pC());
            var2.pC("id", bdn.this.pC.wS());
            bdn.this.pC.pC(var2);
         });
         this.A = bdn.this.pC.mv;
         this.pC(bdm.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdn.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdn.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdn.this.pC.ah && !bdn.this.pC.vP) {
                  var8.pC("kernelOffset", bdn.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdn.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdn.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdn.this.pC.ys());
               if (!bdn.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdn.this.pC.ys());
               var8.pC("numNativeFields", bdn.this.pC.E());
               if (!bdn.this.pC.ah) {
                  Assert.a(!bdn.this.pC.vP);
                  var8.pC("tokenPos", bdn.this.pC.UT());
                  var8.pC("endTokenPos", bdn.this.pC.UT());
               }

               var8.pC("stateBits", bdn.this.pC.E());
               if (bdn.this.pC.ah) {
                  if (var7) {
                     bdn.this.pC.E();
                  } else if (!bdn.this.pC.ys.pC(var9)) {
                     bdn.this.pC.os.put(var9, bdn.this.pC.E());
                  }
               }

               bdn.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdn.this.pC.kS());
         if (!bdn.this.pC.Sc) {
            var1.pC("userName", bdn.this.pC.kS());
         }

         var1.pC("functions", bdn.this.pC.kS());
         var1.pC("functionsHashTable", bdn.this.pC.kS());
         var1.pC("fields", bdn.this.pC.kS());
         var1.pC("offsetInWordsToField", bdn.this.pC.kS());
         var1.pC("interfaces", bdn.this.pC.kS());
         var1.pC("script", bdn.this.pC.kS());
         var1.pC("library", bdn.this.pC.kS());
         var1.pC("typeParameters", bdn.this.pC.kS());
         var1.pC("superType", bdn.this.pC.kS());
         var1.pC("constants", bdn.this.pC.kS());
         var1.pC("declarationType", bdn.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdn.this.pC.kS());
         if (!bdn.this.pC.Sc || !bdn.this.pC.ah) {
            var1.pC("directImplementors", bdn.this.pC.kS());
            var1.pC("directSubclasses", bdn.this.pC.kS());
         }

         if (!bdn.this.pC.ah) {
            var1.pC("allocationStub", bdn.this.pC.kS());
            var1.pC("dependentCode", bdn.this.pC.kS());
         }
      }
   }

   class Tb extends bdn.bO {
      @Override
      public void pC() {
         this.A(bdm.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdn.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdn.this.pC.NS(var6);
               Assert.a(var8 != bdn.this.pC.ys.mv);
               if (var8 == bdn.this.pC.ys.LM) {
                  int var9 = bdn.this.pC.E(var6);
                  if (var9 == bdn.this.pC.ys.Ab) {
                     long var10 = bdn.this.pC.kS();
                     var7.put("rawObj", var10);
                  } else if (var9 == bdn.this.pC.ys.rl) {
                     long var12 = bdn.this.pC.ys();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdn.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdn.this.pC.ys.sO && var8 != bdn.this.pC.ys.os) {
                  if (var8 != bdn.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdn.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdm.gj.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdn.this.pC.kS());
         var1.pC("functionTypeArguments", bdn.this.pC.kS());
         var1.pC("delayedTypeArguments", bdn.this.pC.kS());
         var1.pC("function", bdn.this.pC.kS());
         var1.pC("context", bdn.this.pC.kS());
         var1.pC("hash", bdn.this.pC.kS());
      }
   }

   abstract class bO implements bdn.oP {
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
         this.E = bdn.this.pC.mv;
         long var2 = bdn.this.pC.E();
         bdn.this.pC.pC(var2, var1);
         this.sY = bdn.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdn.this.pC.mv;
         long var2 = bdn.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdn.this.pC.pC(var1);
            long var4 = bdn.this.pC.E();
            var3.pC("length", var4);
            bdn.this.pC.pC(var3);
         });
         this.sY = bdn.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdn.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdn.this.pC.E();
            long var1 = bdn.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdn.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.os.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdn.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdn.this.pC.kS());
         var1.pC("argsDescriptor", bdn.this.pC.kS());
      }
   }

   class cq extends bdn.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdn.this.pC.mv;
         bdn.this.pC.Bf = this.E;
         long var1 = bdn.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdn.this.pC.mv;
         this.pC = bdn.this.pC.mv;
         var1 = bdn.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdn.this.pC.mv;
      }

      private void wS() {
         int var1 = bdn.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdn.this.pC.pC(bdm.ah.pC());
         bdn.this.pC.pC(var3);
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
         bbc var3 = bdn.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdn.this.pC.vP) {
            var3.pC("objectPool", bdn.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdn.this.pC.kS(), bdm.ys.toString(), bdm.E.toString(), bdm.e.toString());
         var3.pC("exceptionHandlers", bdn.this.pC.kS(), bdm.Aj.toString());
         var3.pC("pcDescriptors", bdn.this.pC.kS(), bdm.Ek.toString());
         var3.pC("catchEntry", bdn.this.pC.kS());
         if (bdn.this.pC.NS) {
            var3.pC("compressedStackMaps", bdn.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdn.this.pC.kS(), bdm.Xh.toString());
         var3.pC("codeSourceMap", bdn.this.pC.kS(), bdm.hK.toString());
         if (!bdn.this.pC.ah && bdn.this.pC.NS) {
            var3.pC("deoptInfoArray", bdn.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdn.this.pC.kS());
         }

         if (!bdn.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdn.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdn.this.pC.rl ? bdn.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdn.this.pC.ah) {
            long var3 = bdn.this.pC.pF[2 * bdn.this.pC.OI];
            long var5 = bdn.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdn.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdn.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdn.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdn.bO {
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
            bbc var2 = bdn.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdn.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("cache", bdn.this.pC.kS());
            var2.pC("numInputs", bdn.this.pC.NS());
            var2.pC("numOccupied", bdn.this.pC.NS());
         }
      }
   }

   class gJ extends bdn.bO {
      @Override
      public void pC() {
         this.A(bdm.Ek.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdn.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdn.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdm.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdn.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdn.this.pC.kS());
         var1.pC("hash", bdn.this.pC.kS());
         var1.pC("shape", bdn.this.pC.kS());
         var1.pC("fieldTypes", bdn.this.pC.kS());
      }
   }

   class io extends bdn.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bdm.PZ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdn.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdn.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdn.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bdm.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdn.this.pC.vP());
            var2.pC("nullabity", bdn.this.pC.E());
            var2.pC("instantiations", bdn.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdn.this.pC.kS());
            }
         }
      }
   }

   class m extends bdn.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdn.this.pC.mv;
         long var1 = bdn.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdn.this.pC.E();
            bdm var6 = (var4 & 1L) != 0L ? bdm.EM : bdm.dM;
            long var7 = var4 >> 1;
            bbc var9 = bdn.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdn.this.pC.pC(var9);
         }

         this.sY = bdn.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            long var3 = bdn.this.pC.E();
            long var5 = var3 >> 1;
            bdm var7 = (var3 & 1L) != 0L ? bdm.EM : bdm.dM;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bdm.dM) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdn.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdn.this.pC.gp();
                  var10 |= bdn.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            if (!bdn.this.pC.ah && !bdn.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdn.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdn.this.pC.kS());
         var1.pC("script", bdn.this.pC.kS());
         if (!bdn.this.pC.vP) {
            var1.pC("libraryKernelData", bdn.this.pC.kS());
         }
      }
   }

   class nA extends bdn.bO {
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
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdn.this.pC.kS());
         var1.pC("hashMask", bdn.this.pC.kS());
         var1.pC("data", bdn.this.pC.kS());
         var1.pC("usedData", bdn.this.pC.kS());
         var1.pC("deletedKeys", bdn.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdn.this.pC.vP());
            var2.pC("numImports", bdn.this.pC.fI());
            var2.pC("loadState", bdn.this.pC.oT());
            var2.pC("flags", bdn.this.pC.gp());
            if (!bdn.this.pC.ah && !bdn.this.pC.vP) {
               var2.pC("kernelOffset", bdn.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdn.this.pC.kS());
         var1.pC("url", bdn.this.pC.kS());
         var1.pC("privateKey", bdn.this.pC.kS());
         var1.pC("dictionary", bdn.this.pC.kS());
         var1.pC("metadata", bdn.this.pC.kS());
         var1.pC("toplevelClass", bdn.this.pC.kS());
         var1.pC("usedScripts", bdn.this.pC.kS());
         var1.pC("loadingUnit", bdn.this.pC.kS());
         var1.pC("imports", bdn.this.pC.kS());
         var1.pC("exports", bdn.this.pC.kS());
         if (!bdn.this.pC.vP) {
            var1.pC("dependencies", bdn.this.pC.kS());
            var1.pC("kernelProgramInfo", bdn.this.pC.kS());
         }
      }
   }

   class qt extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.QQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdn.this.pC.kS());
         var1.pC("length", bdn.this.pC.kS());
         var1.pC("data", bdn.this.pC.kS());
      }
   }

   class rQ extends bdn.bO {
      @Override
      public void pC() {
         this.A(bdm.Er.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            long var3 = bdn.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdn.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            if (!bdn.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdn.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdn.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdn.this.pC.kS(var5);
               if (var6.pC().equals(bdm.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdn.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdn.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdn.this.pC.kS());
         var1.pC("owner", bdn.this.pC.kS());
         var1.pC("signature", bdn.this.pC.kS(), bdm.xM.toString());
         var1.pC("data", bdn.this.pC.kS());
      }
   }

   class uX extends bdn.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdn.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdn.this.pC.pC(bdm.VD.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdn.this.pC.xC());
            bdn.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdn.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdn.this.pC.mv;
         long var1 = bdn.this.pC.E();
         this.A = bdn.this.pC.vP();
         this.kS = bdn.this.pC.vP();
         bdn.this.pC.pC(var1, this.pC);
         this.sY = bdn.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdn.this.pC.ys.ys;
         int var2 = (int)bdn.this.pC.pC(this.kS * bdn.this.pC.ys.sY, bdn.this.pC.ys.ld);
         Long var3 = bdn.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdn.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdn.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdn.this.pC.ys.sY)) {
               if (bdn.this.pC.A(var3, var6 / (int)bdn.this.pC.ys.sY)) {
                  bdn.this.pC.Sc();
               } else {
                  bdn.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdn.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdn.bO {
      @Override
      public void pC() {
         this.pC(bdm.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdn.this.pC.E());
            var2.pC("callbackKind", bdn.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdn.this.pC.kS());
         var1.pC("cSignature", bdn.this.pC.kS());
         var1.pC("callbackTarget", bdn.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdn.this.pC.kS());
      }
   }

   class zp extends bdn.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdm.Xs.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdn.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdn.this.pC.ED());
         }
      }
   }
}
