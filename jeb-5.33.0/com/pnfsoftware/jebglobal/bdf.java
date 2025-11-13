package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdf {
   private static final ILogger A = GlobalLog.getLogger(bdf.class);
   bdh pC;

   bdf(bdh var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bdf.oP pC(int var1, boolean var2, boolean var3) {
      bde var4 = bde.pC(var1);
      if (var1 >= bde.Ig.pC() || var1 == bde.OI.pC()) {
         return new bdf.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdf.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case z:
               case Ek:
               case hK:
                  return new bdf.Pj(var2, var3, var1);
               case eE:
               case dM:
               case QQ:
                  if (var3) {
                     return new bdf.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case z:
               Assert.a(!var2);
               return new bdf.gJ();
            case Ek:
               Assert.a(!var2);
               return new bdf.DH();
            case hK:
               Assert.a(!var2);
               return new bdf.rQ();
            case eE:
            case dM:
            default:
               return null;
            case QQ:
               return new bdf.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bdf.Sv();
            case ld:
               return new bdf.Sf();
            case Pe:
               return new bdf.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bdf.ma();
            case ys:
               Assert.a(!var2);
               return new bdf.sy();
            case gp:
               Assert.a(!var2);
               return new bdf.K();
            case oT:
               Assert.a(!var2);
               return new bdf.yt();
            case fI:
               Assert.a(!var2);
               return new bdf.RC();
            case WR:
               Assert.a(!var2);
               return new bdf.Kr();
            case NS:
               Assert.a(!var2);
               return new bdf.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bdf.cq();
            case rl:
               Assert.a(!var2);
               return new bdf.Tb();
            case FE:
               Assert.a(!var2);
               return new bdf.KD();
            case Aj:
               Assert.a(!var2);
               return null;
            case EX:
               Assert.a(!var2);
               return null;
            case sO:
               Assert.a(!var2);
               return new bdf.co();
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return new bdf.eW();
            case cX:
               Assert.a(!var2);
               return new bdf.GK();
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
               return new bdf.Sb(var2, var3);
            case e:
               return new bdf.HE(var2, var3);
            case kU:
               return new bdf.Hv(var2, var3);
            case pg:
               return new bdf.Ws(var2);
            case UH:
               return new bdf.uX(var2);
            case VD:
               return new bdf.zp(var2);
            case FK:
            case KV:
            case Bi:
               return null;
            case sz:
               Assert.a(!var2);
               return new bdf.qt();
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
               return new bdf.Mh(var2, var1);
            case LL:
               throw new RuntimeException();
            case rC:
               return new bdf.nA(var2, var1);
            case be:
            case Xh:
               return new bdf.Av(var2, var1);
            case Sc:
               return new bdf.dE(var2, var1);
            case xM:
               return new bdf.gb(var2, var3);
            case wQ:
               return new bdf.io(var2);
         }
      }
   }

   class Av extends bdf.bO {
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
            bbc var2 = bdf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdf.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdf.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdf.bO {
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
            bbc var2 = bdf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdf.this.pC.WR(this.pC);
            var2.pC("data", bdf.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdf.bO {
      @Override
      public void pC() {
         this.A(bde.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdf.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("parent", bdf.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bdf.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bdf.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bde.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdf.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdf.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdf.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdf.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdf.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdf.this.pC.kS());
         var1.pC("hash", bdf.this.pC.kS());
         var1.pC("typeParameters", bdf.this.pC.kS());
         var1.pC("resultType", bdf.this.pC.kS());
         var1.pC("parameterTypes", bdf.this.pC.kS());
         var1.pC("namedParameterNames", bdf.this.pC.kS());
      }
   }

   class Hv extends bdf.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bde.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdf.this.pC.fI());
            var2.pC("index", bdf.this.pC.fI());
            long var3 = bdf.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdf.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdf.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdf.this.pC.kS());
         var1.pC("hash", bdf.this.pC.kS());
         var1.pC("owner", bdf.this.pC.kS());
      }
   }

   class K extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            if (bdf.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdf.this.pC.kS());
            }

            var2.pC("parentFunction", bdf.this.pC.kS());
            var2.pC("closure", bdf.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdf.this.pC.E());
         }
      }
   }

   class KD extends bdf.bO {
      @Override
      public void pC() {
         this.A(bde.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            long var3 = bdf.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdf.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdf.this.pC.sY());
               var9.put("outerTryIndex", bdf.this.pC.WR());
               var9.put("needsStacktrace", bdf.this.pC.A());
               var9.put("hasCatchAll", bdf.this.pC.A());
               var9.put("isGenerated", bdf.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            if (!bdf.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdf.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdf.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdf.this.pC.kS());
         if (bdf.this.pC.vP) {
            if (!bdf.this.pC.Sc) {
               var1.pC("resolvedUrl", bdf.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdf.this.pC.kS());
            var1.pC("resolvedUrl", bdf.this.pC.kS());
            var1.pC("lineStarts", bdf.this.pC.kS());
            var1.pC("constantCoverage", bdf.this.pC.kS());
            var1.pC("debugPositions", bdf.this.pC.kS());
            var1.pC("kernelProgramInfo", bdf.this.pC.kS());
         }
      }
   }

   class Mh extends bdf.bO {
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
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdf.this.pC.kS());
         var1.pC("hashMask", bdf.this.pC.kS());
         var1.pC("data", bdf.this.pC.kS());
         var1.pC("usedData", bdf.this.pC.kS());
         var1.pC("deletedKeys", bdf.this.pC.kS());
      }
   }

   class Pj extends bdf.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdf.this.pC.mv;
         long var1 = bdf.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdf.this.pC.E() << (int)bdf.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdf.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdf.this.pC.pC(var7);
         }

         this.sY = bdf.this.pC.mv;
         if (this.pC == bde.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bde.eE.pC() && this.pC != bde.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdf.this.pC.E.position(var1);
            bdf.this.pC.E.i32();
            long var2;
            if (bdf.this.pC.FE) {
               bdf.this.pC.E.i32();
               var2 = bdf.this.pC.E.i64();
            } else {
               bdf.this.pC.E.i32();
               var2 = bdf.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdf.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdf.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdf.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdf.this.pC.kS());
         var1.pC("owner", bdf.this.pC.kS());
         var1.pC("type", bdf.this.pC.kS());
         var1.pC("initializerFunction", bdf.this.pC.kS());
      }
   }

   class Sb extends bdf.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bde.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdf.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdf.this.pC.kS());
         var1.pC("hash", bdf.this.pC.kS());
         var1.pC("arguments", bdf.this.pC.kS());
      }
   }

   class Sf extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdf.this.pC.kS());
         var1.pC("flags", bdf.this.pC.kS());
         var1.pC("bounds", bdf.this.pC.kS());
         var1.pC("defaults", bdf.this.pC.kS());
      }
   }

   class Sv extends bdf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdf.this.pC.mv;
         long var1 = bdf.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdf.this.pC.pC(bde.E.pC());
            var2.pC("id", bdf.this.pC.wS());
            bdf.this.pC.pC(var2);
         });
         this.A = bdf.this.pC.mv;
         this.pC(bde.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdf.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdf.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdf.this.pC.ah && !bdf.this.pC.vP) {
                  var8.pC("kernelOffset", bdf.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdf.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdf.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdf.this.pC.ys());
               if (!bdf.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdf.this.pC.ys());
               var8.pC("numNativeFields", bdf.this.pC.E());
               if (!bdf.this.pC.ah) {
                  Assert.a(!bdf.this.pC.vP);
                  var8.pC("tokenPos", bdf.this.pC.UT());
                  var8.pC("endTokenPos", bdf.this.pC.UT());
               }

               var8.pC("stateBits", bdf.this.pC.E());
               if (bdf.this.pC.ah) {
                  if (var7) {
                     bdf.this.pC.E();
                  } else if (!bdf.this.pC.ys.pC(var9)) {
                     bdf.this.pC.os.put(var9, bdf.this.pC.E());
                  }
               }

               bdf.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdf.this.pC.kS());
         if (!bdf.this.pC.Sc) {
            var1.pC("userName", bdf.this.pC.kS());
         }

         var1.pC("functions", bdf.this.pC.kS());
         var1.pC("functionsHashTable", bdf.this.pC.kS());
         var1.pC("fields", bdf.this.pC.kS());
         var1.pC("offsetInWordsToField", bdf.this.pC.kS());
         var1.pC("interfaces", bdf.this.pC.kS());
         var1.pC("script", bdf.this.pC.kS());
         var1.pC("library", bdf.this.pC.kS());
         var1.pC("typeParameters", bdf.this.pC.kS());
         var1.pC("superType", bdf.this.pC.kS());
         var1.pC("constants", bdf.this.pC.kS());
         var1.pC("declarationType", bdf.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdf.this.pC.kS());
         if (!bdf.this.pC.Sc || !bdf.this.pC.ah) {
            var1.pC("directImplementors", bdf.this.pC.kS());
            var1.pC("directSubclasses", bdf.this.pC.kS());
         }

         if (!bdf.this.pC.ah) {
            var1.pC("allocationStub", bdf.this.pC.kS());
            var1.pC("dependentCode", bdf.this.pC.kS());
         }
      }
   }

   class Tb extends bdf.bO {
      @Override
      public void pC() {
         this.A(bde.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdf.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdf.this.pC.NS(var6);
               Assert.a(var8 != bdf.this.pC.ys.mv);
               if (var8 == bdf.this.pC.ys.LM) {
                  int var9 = bdf.this.pC.E(var6);
                  if (var9 == bdf.this.pC.ys.Ab) {
                     long var10 = bdf.this.pC.kS();
                     var7.put("rawObj", var10);
                  } else if (var9 == bdf.this.pC.ys.rl) {
                     long var12 = bdf.this.pC.ys();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdf.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdf.this.pC.ys.sO && var8 != bdf.this.pC.ys.os) {
                  if (var8 != bdf.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdf.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bde.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdf.this.pC.kS());
         var1.pC("functionTypeArguments", bdf.this.pC.kS());
         var1.pC("delayedTypeArguments", bdf.this.pC.kS());
         var1.pC("function", bdf.this.pC.kS());
         var1.pC("context", bdf.this.pC.kS());
         var1.pC("hash", bdf.this.pC.kS());
      }
   }

   abstract class bO implements bdf.oP {
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
         this.E = bdf.this.pC.mv;
         long var2 = bdf.this.pC.E();
         bdf.this.pC.pC(var2, var1);
         this.sY = bdf.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdf.this.pC.mv;
         long var2 = bdf.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdf.this.pC.pC(var1);
            long var4 = bdf.this.pC.E();
            var3.pC("length", var4);
            bdf.this.pC.pC(var3);
         });
         this.sY = bdf.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdf.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdf.this.pC.E();
            long var1 = bdf.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdf.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdf.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdf.this.pC.kS());
         var1.pC("argsDescriptor", bdf.this.pC.kS());
      }
   }

   class cq extends bdf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdf.this.pC.mv;
         bdf.this.pC.Bf = this.E;
         long var1 = bdf.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdf.this.pC.mv;
         this.pC = bdf.this.pC.mv;
         var1 = bdf.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdf.this.pC.mv;
      }

      private void wS() {
         int var1 = bdf.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdf.this.pC.pC(bde.ah.pC());
         bdf.this.pC.pC(var3);
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
         bbc var3 = bdf.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdf.this.pC.vP) {
            var3.pC("objectPool", bdf.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdf.this.pC.kS(), bde.ys.toString(), bde.E.toString(), bde.RW.toString());
         var3.pC("exceptionHandlers", bdf.this.pC.kS(), bde.FE.toString());
         var3.pC("pcDescriptors", bdf.this.pC.kS(), bde.z.toString());
         var3.pC("catchEntry", bdf.this.pC.kS());
         if (bdf.this.pC.NS) {
            var3.pC("compressedStackMaps", bdf.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdf.this.pC.kS(), bde.be.toString());
         var3.pC("codeSourceMap", bdf.this.pC.kS(), bde.Ek.toString());
         if (!bdf.this.pC.ah && bdf.this.pC.NS) {
            var3.pC("deoptInfoArray", bdf.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdf.this.pC.kS());
         }

         if (!bdf.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdf.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdf.this.pC.rl ? bdf.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdf.this.pC.ah) {
            long var3 = bdf.this.pC.pF[2 * bdf.this.pC.OI];
            long var5 = bdf.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdf.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdf.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdf.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdf.bO {
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
            bbc var2 = bdf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdf.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("cache", bdf.this.pC.kS());
            var2.pC("numInputs", bdf.this.pC.NS());
            var2.pC("numOccupied", bdf.this.pC.NS());
         }
      }
   }

   class gJ extends bdf.bO {
      @Override
      public void pC() {
         this.A(bde.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdf.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdf.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bde.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdf.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdf.this.pC.kS());
         var1.pC("hash", bdf.this.pC.kS());
         var1.pC("shape", bdf.this.pC.kS());
         var1.pC("fieldTypes", bdf.this.pC.kS());
      }
   }

   class io extends bdf.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bde.wQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdf.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdf.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdf.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bde.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdf.this.pC.vP());
            var2.pC("nullabity", bdf.this.pC.E());
            var2.pC("instantiations", bdf.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdf.this.pC.kS());
            }
         }
      }
   }

   class m extends bdf.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdf.this.pC.mv;
         long var1 = bdf.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdf.this.pC.E();
            bde var6 = (var4 & 1L) != 0L ? bde.dM : bde.eE;
            long var7 = var4 >> 1;
            bbc var9 = bdf.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdf.this.pC.pC(var9);
         }

         this.sY = bdf.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            long var3 = bdf.this.pC.E();
            long var5 = var3 >> 1;
            bde var7 = (var3 & 1L) != 0L ? bde.dM : bde.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bde.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdf.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdf.this.pC.gp();
                  var10 |= bdf.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            if (!bdf.this.pC.ah && !bdf.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdf.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdf.this.pC.kS());
         var1.pC("script", bdf.this.pC.kS());
         if (!bdf.this.pC.vP) {
            var1.pC("libraryKernelData", bdf.this.pC.kS());
         }
      }
   }

   class nA extends bdf.bO {
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
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdf.this.pC.kS());
         var1.pC("hashMask", bdf.this.pC.kS());
         var1.pC("data", bdf.this.pC.kS());
         var1.pC("usedData", bdf.this.pC.kS());
         var1.pC("deletedKeys", bdf.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdf.this.pC.vP());
            var2.pC("numImports", bdf.this.pC.fI());
            var2.pC("loadState", bdf.this.pC.oT());
            var2.pC("flags", bdf.this.pC.gp());
            if (!bdf.this.pC.ah && !bdf.this.pC.vP) {
               var2.pC("kernelOffset", bdf.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdf.this.pC.kS());
         var1.pC("url", bdf.this.pC.kS());
         var1.pC("privateKey", bdf.this.pC.kS());
         var1.pC("dictionary", bdf.this.pC.kS());
         var1.pC("metadata", bdf.this.pC.kS());
         var1.pC("toplevelClass", bdf.this.pC.kS());
         var1.pC("usedScripts", bdf.this.pC.kS());
         var1.pC("loadingUnit", bdf.this.pC.kS());
         var1.pC("imports", bdf.this.pC.kS());
         var1.pC("exports", bdf.this.pC.kS());
         if (!bdf.this.pC.vP) {
            var1.pC("dependencies", bdf.this.pC.kS());
            var1.pC("kernelProgramInfo", bdf.this.pC.kS());
         }
      }
   }

   class qt extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdf.this.pC.kS());
         var1.pC("length", bdf.this.pC.kS());
         var1.pC("data", bdf.this.pC.kS());
      }
   }

   class rQ extends bdf.bO {
      @Override
      public void pC() {
         this.A(bde.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            long var3 = bdf.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdf.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            if (!bdf.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdf.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdf.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdf.this.pC.kS(var5);
               if (var6.pC().equals(bde.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdf.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdf.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdf.this.pC.kS());
         var1.pC("owner", bdf.this.pC.kS());
         var1.pC("signature", bdf.this.pC.kS(), bde.e.toString());
         var1.pC("data", bdf.this.pC.kS());
      }
   }

   class uX extends bdf.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdf.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdf.this.pC.pC(bde.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdf.this.pC.xC());
            bdf.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdf.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdf.this.pC.mv;
         long var1 = bdf.this.pC.E();
         this.A = bdf.this.pC.vP();
         this.kS = bdf.this.pC.vP();
         bdf.this.pC.pC(var1, this.pC);
         this.sY = bdf.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdf.this.pC.ys.ys;
         int var2 = (int)bdf.this.pC.pC(this.kS * bdf.this.pC.ys.sY, bdf.this.pC.ys.ld);
         Long var3 = bdf.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdf.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdf.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdf.this.pC.ys.sY)) {
               if (bdf.this.pC.A(var3, var6 / (int)bdf.this.pC.ys.sY)) {
                  bdf.this.pC.Sc();
               } else {
                  bdf.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdf.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdf.bO {
      @Override
      public void pC() {
         this.pC(bde.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdf.this.pC.E());
            var2.pC("callbackKind", bdf.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdf.this.pC.kS());
         var1.pC("cSignature", bdf.this.pC.kS());
         var1.pC("callbackTarget", bdf.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdf.this.pC.kS());
      }
   }

   class zp extends bdf.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bde.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdf.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdf.this.pC.ED());
         }
      }
   }
}
