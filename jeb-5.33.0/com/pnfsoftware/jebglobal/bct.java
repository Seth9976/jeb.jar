package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bct {
   private static final ILogger A = GlobalLog.getLogger(bct.class);
   bcv pC;

   bct(bcv var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bct.oP pC(int var1, boolean var2, boolean var3) {
      bcs var4 = bcs.pC(var1);
      if (var1 >= bcs.pY.pC() || var1 == bcs.OI.pC()) {
         return new bct.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bct.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case z:
               case Ek:
               case hK:
                  return new bct.Pj(var2, var3, var1);
               case eE:
               case dM:
               case QQ:
                  if (var3) {
                     return new bct.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case z:
               Assert.a(!var2);
               return new bct.gJ();
            case Ek:
               Assert.a(!var2);
               return new bct.DH();
            case hK:
               Assert.a(!var2);
               return new bct.rQ();
            case eE:
            case dM:
            default:
               return null;
            case QQ:
               return new bct.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bct.Sv();
            case ld:
               return new bct.Sf();
            case Pe:
               return new bct.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bct.ma();
            case ys:
               Assert.a(!var2);
               return new bct.sy();
            case gp:
               Assert.a(!var2);
               return new bct.K();
            case oT:
               Assert.a(!var2);
               return new bct.yt();
            case fI:
               Assert.a(!var2);
               return new bct.RC();
            case WR:
               Assert.a(!var2);
               return new bct.Kr();
            case NS:
               Assert.a(!var2);
               return new bct.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bct.cq();
            case rl:
               Assert.a(!var2);
               return new bct.Tb();
            case FE:
               Assert.a(!var2);
               return new bct.KD();
            case Aj:
               Assert.a(!var2);
               return null;
            case EX:
               Assert.a(!var2);
               return null;
            case sO:
               Assert.a(!var2);
               return new bct.co();
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return new bct.eW();
            case cX:
               Assert.a(!var2);
               return new bct.GK();
            case OB:
               Assert.a(!var2);
               return null;
            case pF:
               Assert.a(!var2);
               return null;
            case Bf:
               Assert.a(!var2);
               return null;
            case RW:
               return new bct.Sb(var2, var3);
            case e:
               return new bct.HE(var2, var3);
            case kU:
               return new bct.Hv(var2, var3);
            case pg:
               return new bct.Ws(var2);
            case UH:
               return new bct.uX(var2);
            case VD:
               return new bct.zp(var2);
            case sz:
               Assert.a(!var2);
               return new bct.qt();
            case Ta:
               Assert.a(!var2);
               return null;
            case tH:
               Assert.a(!var2);
               return null;
            case Gm:
               Assert.a(!var2);
               return null;
            case oB:
               throw new RuntimeException();
            case Rq:
               return new bct.Mh(var2, var1);
            case LL:
               throw new RuntimeException();
            case rC:
               return new bct.nA(var2, var1);
            case be:
            case Xh:
               return new bct.Av(var2, var1);
            case Sc:
               return new bct.dE(var2, var1);
            case xM:
               return new bct.gb(var2, var3);
            case wQ:
               return new bct.io(var2);
         }
      }
   }

   class Av extends bct.bO {
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
            bbc var2 = bct.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bct.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bct.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bct.bO {
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
            bbc var2 = bct.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bct.this.pC.WR(this.pC);
            var2.pC("data", bct.this.pC.wS(var4));
         }
      }
   }

   class DH extends bct.bO {
      @Override
      public void pC() {
         this.A(bcs.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bct.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("parent", bct.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bct.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bct.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcs.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bct.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bct.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bct.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bct.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bct.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bct.this.pC.kS());
         var1.pC("hash", bct.this.pC.kS());
         var1.pC("typeParameters", bct.this.pC.kS());
         var1.pC("resultType", bct.this.pC.kS());
         var1.pC("parameterTypes", bct.this.pC.kS());
         var1.pC("namedParameterNames", bct.this.pC.kS());
      }
   }

   class Hv extends bct.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcs.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bct.this.pC.fI());
            var2.pC("index", bct.this.pC.fI());
            long var3 = bct.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bct.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bct.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bct.this.pC.kS());
         var1.pC("hash", bct.this.pC.kS());
         var1.pC("owner", bct.this.pC.kS());
      }
   }

   class K extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            if (bct.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bct.this.pC.kS());
            }

            var2.pC("parentFunction", bct.this.pC.kS());
            var2.pC("closure", bct.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bct.this.pC.E());
         }
      }
   }

   class KD extends bct.bO {
      @Override
      public void pC() {
         this.A(bcs.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            long var3 = bct.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bct.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bct.this.pC.sY());
               var9.put("outerTryIndex", bct.this.pC.WR());
               var9.put("needsStacktrace", bct.this.pC.A());
               var9.put("hasCatchAll", bct.this.pC.A());
               var9.put("isGenerated", bct.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            if (!bct.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bct.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bct.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bct.this.pC.kS());
         if (bct.this.pC.vP) {
            if (!bct.this.pC.Sc) {
               var1.pC("resolvedUrl", bct.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bct.this.pC.kS());
            var1.pC("resolvedUrl", bct.this.pC.kS());
            var1.pC("lineStarts", bct.this.pC.kS());
            var1.pC("constantCoverage", bct.this.pC.kS());
            var1.pC("debugPositions", bct.this.pC.kS());
            var1.pC("kernelProgramInfo", bct.this.pC.kS());
         }
      }
   }

   class Mh extends bct.bO {
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
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bct.this.pC.kS());
         var1.pC("hashMask", bct.this.pC.kS());
         var1.pC("data", bct.this.pC.kS());
         var1.pC("usedData", bct.this.pC.kS());
         var1.pC("deletedKeys", bct.this.pC.kS());
      }
   }

   class Pj extends bct.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bct.this.pC.mv;
         long var1 = bct.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bct.this.pC.E() << (int)bct.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bct.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bct.this.pC.pC(var7);
         }

         this.sY = bct.this.pC.mv;
         if (this.pC == bcs.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bcs.eE.pC() && this.pC != bcs.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bct.this.pC.E.position(var1);
            bct.this.pC.E.i32();
            long var2;
            if (bct.this.pC.FE) {
               bct.this.pC.E.i32();
               var2 = bct.this.pC.E.i64();
            } else {
               bct.this.pC.E.i32();
               var2 = bct.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bct.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bct.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bct.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bct.this.pC.kS());
         var1.pC("owner", bct.this.pC.kS());
         var1.pC("type", bct.this.pC.kS());
         var1.pC("initializerFunction", bct.this.pC.kS());
      }
   }

   class Sb extends bct.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcs.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bct.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bct.this.pC.kS());
         var1.pC("hash", bct.this.pC.kS());
         var1.pC("arguments", bct.this.pC.kS());
      }
   }

   class Sf extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bct.this.pC.kS());
         var1.pC("flags", bct.this.pC.kS());
         var1.pC("bounds", bct.this.pC.kS());
         var1.pC("defaults", bct.this.pC.kS());
      }
   }

   class Sv extends bct.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bct.this.pC.mv;
         long var1 = bct.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bct.this.pC.pC(bcs.E.pC());
            var2.pC("id", bct.this.pC.wS());
            bct.this.pC.pC(var2);
         });
         this.A = bct.this.pC.mv;
         this.pC(bcs.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bct.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bct.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bct.this.pC.ah && !bct.this.pC.vP) {
                  var8.pC("kernelOffset", bct.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bct.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bct.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bct.this.pC.ys());
               if (!bct.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bct.this.pC.ys());
               var8.pC("numNativeFields", bct.this.pC.E());
               if (!bct.this.pC.ah) {
                  Assert.a(!bct.this.pC.vP);
                  var8.pC("tokenPos", bct.this.pC.UT());
                  var8.pC("endTokenPos", bct.this.pC.UT());
               }

               var8.pC("stateBits", bct.this.pC.E());
               if (bct.this.pC.ah) {
                  if (var7) {
                     bct.this.pC.E();
                  } else if (!bct.this.pC.ys.pC(var9)) {
                     bct.this.pC.os.put(var9, bct.this.pC.E());
                  }
               }

               bct.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bct.this.pC.kS());
         if (!bct.this.pC.Sc) {
            var1.pC("userName", bct.this.pC.kS());
         }

         var1.pC("functions", bct.this.pC.kS());
         var1.pC("functionsHashTable", bct.this.pC.kS());
         var1.pC("fields", bct.this.pC.kS());
         var1.pC("offsetInWordsToField", bct.this.pC.kS());
         var1.pC("interfaces", bct.this.pC.kS());
         var1.pC("script", bct.this.pC.kS());
         var1.pC("library", bct.this.pC.kS());
         var1.pC("typeParameters", bct.this.pC.kS());
         var1.pC("superType", bct.this.pC.kS());
         var1.pC("constants", bct.this.pC.kS());
         var1.pC("declarationType", bct.this.pC.kS());
         var1.pC("invocationDispatcherCache", bct.this.pC.kS());
         if (!bct.this.pC.Sc || !bct.this.pC.ah) {
            var1.pC("directImplementors", bct.this.pC.kS());
            var1.pC("directSubclasses", bct.this.pC.kS());
         }

         if (!bct.this.pC.ah) {
            var1.pC("allocationStub", bct.this.pC.kS());
            var1.pC("dependentCode", bct.this.pC.kS());
         }
      }
   }

   class Tb extends bct.bO {
      @Override
      public void pC() {
         this.A(bcs.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bct.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bct.this.pC.E(var6);
               if (var8 == bct.this.pC.ys.Ab) {
                  var7.put("rawObj", bct.this.pC.kS());
               } else if (var8 == bct.this.pC.ys.rl) {
                  var7.put("rawValue", bct.this.pC.ys());
               } else if (var8 == bct.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bct.this.pC.ys.Er && var8 != bct.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bct.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Ws extends bct.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcs.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bct.this.pC.kS());
         var1.pC("functionTypeArguments", bct.this.pC.kS());
         var1.pC("delayedTypeArguments", bct.this.pC.kS());
         var1.pC("function", bct.this.pC.kS());
         var1.pC("context", bct.this.pC.kS());
         var1.pC("hash", bct.this.pC.kS());
      }
   }

   abstract class bO implements bct.oP {
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
         this.E = bct.this.pC.mv;
         long var2 = bct.this.pC.E();
         bct.this.pC.pC(var2, var1);
         this.sY = bct.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bct.this.pC.mv;
         long var2 = bct.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bct.this.pC.pC(var1);
            long var4 = bct.this.pC.E();
            var3.pC("length", var4);
            bct.this.pC.pC(var3);
         });
         this.sY = bct.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bct.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bct.this.pC.E();
            long var1 = bct.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bct.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bct.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bct.this.pC.kS());
         var1.pC("argsDescriptor", bct.this.pC.kS());
      }
   }

   class cq extends bct.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bct.this.pC.mv;
         bct.this.pC.Bf = this.E;
         long var1 = bct.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bct.this.pC.mv;
         this.pC = bct.this.pC.mv;
         var1 = bct.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bct.this.pC.mv;
      }

      private void wS() {
         int var1 = bct.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bct.this.pC.pC(bcs.ah.pC());
         bct.this.pC.pC(var3);
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
         bbc var3 = bct.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bct.this.pC.vP) {
            var3.pC("objectPool", bct.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bct.this.pC.kS(), bcs.ys.toString(), bcs.E.toString(), bcs.RW.toString());
         var3.pC("exceptionHandlers", bct.this.pC.kS(), bcs.FE.toString());
         var3.pC("pcDescriptors", bct.this.pC.kS(), bcs.z.toString());
         var3.pC("catchEntry", bct.this.pC.kS());
         if (bct.this.pC.NS) {
            var3.pC("compressedStackMaps", bct.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bct.this.pC.kS(), bcs.be.toString());
         var3.pC("codeSourceMap", bct.this.pC.kS(), bcs.Ek.toString());
         if (!bct.this.pC.ah && bct.this.pC.NS) {
            var3.pC("deoptInfoArray", bct.this.pC.kS());
            var3.pC("staticCallsTargetTable", bct.this.pC.kS());
         }

         if (!bct.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bct.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bct.this.pC.rl ? bct.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bct.this.pC.ah) {
            long var3 = bct.this.pC.pF[2 * bct.this.pC.OI];
            long var5 = bct.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bct.this.pC.ys.UO : 0L;
            long var12 = var9 ? bct.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bct.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bct.bO {
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
            bbc var2 = bct.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bct.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("cache", bct.this.pC.kS());
            var2.pC("numInputs", bct.this.pC.NS());
            var2.pC("numOccupied", bct.this.pC.NS());
         }
      }
   }

   class gJ extends bct.bO {
      @Override
      public void pC() {
         this.A(bcs.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bct.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bct.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcs.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bct.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bct.this.pC.kS());
         var1.pC("hash", bct.this.pC.kS());
         var1.pC("shape", bct.this.pC.kS());
         var1.pC("fieldTypes", bct.this.pC.kS());
      }
   }

   class io extends bct.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bcs.wQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bct.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bct.this.pC.kS();
            }
         }
      }
   }

   class jM extends bct.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bcs.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bct.this.pC.vP());
            var2.pC("nullabity", bct.this.pC.E());
            var2.pC("instantiations", bct.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bct.this.pC.kS());
            }
         }
      }
   }

   class m extends bct.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bct.this.pC.mv;
         long var1 = bct.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bct.this.pC.E();
            bcs var6 = (var4 & 1L) != 0L ? bcs.dM : bcs.eE;
            long var7 = var4 >> 1;
            bbc var9 = bct.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bct.this.pC.pC(var9);
         }

         this.sY = bct.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            long var3 = bct.this.pC.E();
            long var5 = var3 >> 1;
            bcs var7 = (var3 & 1L) != 0L ? bcs.dM : bcs.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bcs.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bct.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bct.this.pC.gp();
                  var10 |= bct.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            if (!bct.this.pC.ah && !bct.this.pC.vP) {
               var2.pC("libraryKernelOffset", bct.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bct.this.pC.kS());
         var1.pC("originClass", bct.this.pC.kS());
         var1.pC("script", bct.this.pC.kS());
         if (!bct.this.pC.vP) {
            var1.pC("libraryKernelData", bct.this.pC.kS());
         }
      }
   }

   class nA extends bct.bO {
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
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bct.this.pC.kS());
         var1.pC("hashMask", bct.this.pC.kS());
         var1.pC("data", bct.this.pC.kS());
         var1.pC("usedData", bct.this.pC.kS());
         var1.pC("deletedKeys", bct.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bct.this.pC.vP());
            var2.pC("numImports", bct.this.pC.fI());
            var2.pC("loadState", bct.this.pC.oT());
            var2.pC("flags", bct.this.pC.gp());
            if (!bct.this.pC.ah && !bct.this.pC.vP) {
               var2.pC("kernelOffset", bct.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bct.this.pC.kS());
         var1.pC("url", bct.this.pC.kS());
         var1.pC("privateKey", bct.this.pC.kS());
         var1.pC("dictionary", bct.this.pC.kS());
         var1.pC("metadata", bct.this.pC.kS());
         var1.pC("toplevelClass", bct.this.pC.kS());
         var1.pC("usedScripts", bct.this.pC.kS());
         var1.pC("loadingUnit", bct.this.pC.kS());
         var1.pC("imports", bct.this.pC.kS());
         var1.pC("exports", bct.this.pC.kS());
         if (!bct.this.pC.vP) {
            var1.pC("dependencies", bct.this.pC.kS());
            var1.pC("kernelData", bct.this.pC.kS());
         }
      }
   }

   class qt extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bct.this.pC.kS());
         var1.pC("length", bct.this.pC.kS());
         var1.pC("data", bct.this.pC.kS());
      }
   }

   class rQ extends bct.bO {
      @Override
      public void pC() {
         this.A(bcs.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            long var3 = bct.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bct.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            if (!bct.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bct.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bct.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bct.this.pC.kS(var5);
               if (var6.pC().equals(bcs.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bct.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bct.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bct.this.pC.kS());
         var1.pC("owner", bct.this.pC.kS());
         var1.pC("signature", bct.this.pC.kS(), bcs.e.toString());
         var1.pC("data", bct.this.pC.kS());
      }
   }

   class uX extends bct.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bct.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bct.this.pC.pC(bcs.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bct.this.pC.xC());
            bct.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bct.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bct.this.pC.mv;
         long var1 = bct.this.pC.E();
         this.A = bct.this.pC.vP();
         this.kS = bct.this.pC.vP();
         bct.this.pC.pC(var1, this.pC);
         this.sY = bct.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bct.this.pC.ys.ys;
         int var2 = (int)bct.this.pC.pC(this.kS * bct.this.pC.ys.sY, bct.this.pC.ys.ld);
         Long var3 = bct.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bct.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bct.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bct.this.pC.ys.sY)) {
               if (bct.this.pC.A(var3, var6 / (int)bct.this.pC.ys.sY)) {
                  bct.this.pC.Sc();
               } else {
                  bct.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bct.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bct.bO {
      @Override
      public void pC() {
         this.pC(bcs.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bct.this.pC.E());
            var2.pC("callbackKind", bct.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bct.this.pC.kS());
         var1.pC("cSignature", bct.this.pC.kS());
         var1.pC("callbackTarget", bct.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bct.this.pC.kS());
      }
   }

   class zp extends bct.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcs.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bct.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bct.this.pC.ED());
         }
      }
   }
}
