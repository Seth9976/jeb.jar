package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdb {
   private static final ILogger A = GlobalLog.getLogger(bdb.class);
   bdd pC;

   bdb(bdd var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   bdb.oP pC(int var1, boolean var2, boolean var3) {
      bda var4 = bda.pC(var1);
      if (var1 >= bda.pY.pC() || var1 == bda.OI.pC()) {
         return new bdb.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdb.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case z:
               case Ek:
               case hK:
                  return new bdb.Pj(var2, var3, var1);
               case eE:
               case dM:
               case QQ:
                  if (var3) {
                     return new bdb.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case z:
               Assert.a(!var2);
               return new bdb.gJ();
            case Ek:
               Assert.a(!var2);
               return new bdb.DH();
            case hK:
               Assert.a(!var2);
               return new bdb.rQ();
            case eE:
            case dM:
            default:
               return null;
            case QQ:
               return new bdb.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bdb.Sv();
            case ld:
               return new bdb.Sf();
            case Pe:
               return new bdb.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bdb.ma();
            case ys:
               Assert.a(!var2);
               return new bdb.sy();
            case gp:
               Assert.a(!var2);
               return new bdb.K();
            case oT:
               Assert.a(!var2);
               return new bdb.yt();
            case fI:
               Assert.a(!var2);
               return new bdb.RC();
            case WR:
               Assert.a(!var2);
               return new bdb.Kr();
            case NS:
               Assert.a(!var2);
               return new bdb.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bdb.cq();
            case rl:
               Assert.a(!var2);
               return new bdb.Tb();
            case FE:
               Assert.a(!var2);
               return new bdb.KD();
            case Aj:
               Assert.a(!var2);
               return null;
            case EX:
               Assert.a(!var2);
               return null;
            case sO:
               Assert.a(!var2);
               return new bdb.co();
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return new bdb.eW();
            case cX:
               Assert.a(!var2);
               return new bdb.GK();
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
               return new bdb.Sb(var2, var3);
            case e:
               return new bdb.HE(var2, var3);
            case kU:
               return new bdb.Hv(var2, var3);
            case pg:
               return new bdb.Ws(var2);
            case UH:
               return new bdb.uX(var2);
            case VD:
               return new bdb.zp(var2);
            case sz:
               Assert.a(!var2);
               return new bdb.qt();
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
               return new bdb.Mh(var2, var1);
            case LL:
               throw new RuntimeException();
            case rC:
               return new bdb.nA(var2, var1);
            case be:
            case Xh:
               return new bdb.Av(var2, var1);
            case Sc:
               return new bdb.dE(var2, var1);
            case xM:
               return new bdb.gb(var2, var3);
            case wQ:
               return new bdb.io(var2);
         }
      }
   }

   class Av extends bdb.bO {
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
            bbc var2 = bdb.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdb.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdb.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdb.bO {
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
            bbc var2 = bdb.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdb.this.pC.WR(this.pC);
            var2.pC("data", bdb.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdb.bO {
      @Override
      public void pC() {
         this.A(bda.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdb.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("parent", bdb.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bdb.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bdb.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bda.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdb.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdb.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdb.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdb.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdb.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdb.this.pC.kS());
         var1.pC("hash", bdb.this.pC.kS());
         var1.pC("typeParameters", bdb.this.pC.kS());
         var1.pC("resultType", bdb.this.pC.kS());
         var1.pC("parameterTypes", bdb.this.pC.kS());
         var1.pC("namedParameterNames", bdb.this.pC.kS());
      }
   }

   class Hv extends bdb.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bda.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdb.this.pC.fI());
            var2.pC("index", bdb.this.pC.fI());
            long var3 = bdb.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdb.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdb.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdb.this.pC.kS());
         var1.pC("hash", bdb.this.pC.kS());
         var1.pC("owner", bdb.this.pC.kS());
      }
   }

   class K extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            if (bdb.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdb.this.pC.kS());
            }

            var2.pC("parentFunction", bdb.this.pC.kS());
            var2.pC("closure", bdb.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdb.this.pC.E());
         }
      }
   }

   class KD extends bdb.bO {
      @Override
      public void pC() {
         this.A(bda.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            long var3 = bdb.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdb.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdb.this.pC.sY());
               var9.put("outerTryIndex", bdb.this.pC.WR());
               var9.put("needsStacktrace", bdb.this.pC.A());
               var9.put("hasCatchAll", bdb.this.pC.A());
               var9.put("isGenerated", bdb.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            if (!bdb.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdb.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdb.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdb.this.pC.kS());
         if (bdb.this.pC.vP) {
            if (!bdb.this.pC.Sc) {
               var1.pC("resolvedUrl", bdb.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdb.this.pC.kS());
            var1.pC("resolvedUrl", bdb.this.pC.kS());
            var1.pC("lineStarts", bdb.this.pC.kS());
            var1.pC("constantCoverage", bdb.this.pC.kS());
            var1.pC("debugPositions", bdb.this.pC.kS());
            var1.pC("kernelProgramInfo", bdb.this.pC.kS());
         }
      }
   }

   class Mh extends bdb.bO {
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
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdb.this.pC.kS());
         var1.pC("hashMask", bdb.this.pC.kS());
         var1.pC("data", bdb.this.pC.kS());
         var1.pC("usedData", bdb.this.pC.kS());
         var1.pC("deletedKeys", bdb.this.pC.kS());
      }
   }

   class Pj extends bdb.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdb.this.pC.mv;
         long var1 = bdb.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdb.this.pC.E() << (int)bdb.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdb.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdb.this.pC.pC(var7);
         }

         this.sY = bdb.this.pC.mv;
         if (this.pC == bda.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bda.eE.pC() && this.pC != bda.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdb.this.pC.E.position(var1);
            bdb.this.pC.E.i32();
            long var2;
            if (bdb.this.pC.FE) {
               bdb.this.pC.E.i32();
               var2 = bdb.this.pC.E.i64();
            } else {
               bdb.this.pC.E.i32();
               var2 = bdb.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdb.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdb.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdb.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdb.this.pC.kS());
         var1.pC("owner", bdb.this.pC.kS());
         var1.pC("type", bdb.this.pC.kS());
         var1.pC("initializerFunction", bdb.this.pC.kS());
      }
   }

   class Sb extends bdb.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bda.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdb.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdb.this.pC.kS());
         var1.pC("hash", bdb.this.pC.kS());
         var1.pC("arguments", bdb.this.pC.kS());
      }
   }

   class Sf extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdb.this.pC.kS());
         var1.pC("flags", bdb.this.pC.kS());
         var1.pC("bounds", bdb.this.pC.kS());
         var1.pC("defaults", bdb.this.pC.kS());
      }
   }

   class Sv extends bdb.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdb.this.pC.mv;
         long var1 = bdb.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdb.this.pC.pC(bda.E.pC());
            var2.pC("id", bdb.this.pC.wS());
            bdb.this.pC.pC(var2);
         });
         this.A = bdb.this.pC.mv;
         this.pC(bda.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdb.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdb.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdb.this.pC.ah && !bdb.this.pC.vP) {
                  var8.pC("kernelOffset", bdb.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdb.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdb.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdb.this.pC.ys());
               if (!bdb.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdb.this.pC.ys());
               var8.pC("numNativeFields", bdb.this.pC.E());
               if (!bdb.this.pC.ah) {
                  Assert.a(!bdb.this.pC.vP);
                  var8.pC("tokenPos", bdb.this.pC.UT());
                  var8.pC("endTokenPos", bdb.this.pC.UT());
               }

               var8.pC("stateBits", bdb.this.pC.E());
               if (bdb.this.pC.ah) {
                  if (var7) {
                     bdb.this.pC.E();
                  } else if (!bdb.this.pC.ys.pC(var9)) {
                     bdb.this.pC.os.put(var9, bdb.this.pC.E());
                  }
               }

               bdb.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdb.this.pC.kS());
         if (!bdb.this.pC.Sc) {
            var1.pC("userName", bdb.this.pC.kS());
         }

         var1.pC("functions", bdb.this.pC.kS());
         var1.pC("functionsHashTable", bdb.this.pC.kS());
         var1.pC("fields", bdb.this.pC.kS());
         var1.pC("offsetInWordsToField", bdb.this.pC.kS());
         var1.pC("interfaces", bdb.this.pC.kS());
         var1.pC("script", bdb.this.pC.kS());
         var1.pC("library", bdb.this.pC.kS());
         var1.pC("typeParameters", bdb.this.pC.kS());
         var1.pC("superType", bdb.this.pC.kS());
         var1.pC("constants", bdb.this.pC.kS());
         var1.pC("declarationType", bdb.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdb.this.pC.kS());
         if (!bdb.this.pC.Sc || !bdb.this.pC.ah) {
            var1.pC("directImplementors", bdb.this.pC.kS());
            var1.pC("directSubclasses", bdb.this.pC.kS());
         }

         if (!bdb.this.pC.ah) {
            var1.pC("allocationStub", bdb.this.pC.kS());
            var1.pC("dependentCode", bdb.this.pC.kS());
         }
      }
   }

   class Tb extends bdb.bO {
      @Override
      public void pC() {
         this.A(bda.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdb.this.pC.gp();
               Object[] var10000 = new Object[]{var6};
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdb.this.pC.NS(var6);
               Assert.a(var8 != bdb.this.pC.ys.mv);
               if (var8 == bdb.this.pC.ys.LM) {
                  int var9 = bdb.this.pC.E(var6);
                  if (var9 == bdb.this.pC.ys.Ab) {
                     long var10 = bdb.this.pC.kS();
                     var10000 = new Object[]{var10};
                     var7.put("rawObj", var10);
                  } else if (var9 == bdb.this.pC.ys.rl) {
                     long var12 = bdb.this.pC.ys();
                     var10000 = new Object[]{var12};
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdb.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var10000 = new Object[0];
                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdb.this.pC.ys.sO && var8 != bdb.this.pC.ys.os) {
                  if (var8 != bdb.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdb.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bda.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdb.this.pC.kS());
         var1.pC("functionTypeArguments", bdb.this.pC.kS());
         var1.pC("delayedTypeArguments", bdb.this.pC.kS());
         var1.pC("function", bdb.this.pC.kS());
         var1.pC("context", bdb.this.pC.kS());
         var1.pC("hash", bdb.this.pC.kS());
      }
   }

   abstract class bO implements bdb.oP {
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
         this.E = bdb.this.pC.mv;
         long var2 = bdb.this.pC.E();
         bdb.this.pC.pC(var2, var1);
         this.sY = bdb.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdb.this.pC.mv;
         long var2 = bdb.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdb.this.pC.pC(var1);
            long var4 = bdb.this.pC.E();
            var3.pC("length", var4);
            bdb.this.pC.pC(var3);
         });
         this.sY = bdb.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdb.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdb.this.pC.E();
            long var1 = bdb.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdb.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdb.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdb.this.pC.kS());
         var1.pC("argsDescriptor", bdb.this.pC.kS());
      }
   }

   class cq extends bdb.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdb.this.pC.mv;
         bdb.this.pC.Bf = this.E;
         long var1 = bdb.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdb.this.pC.mv;
         this.pC = bdb.this.pC.mv;
         var1 = bdb.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdb.this.pC.mv;
      }

      private void wS() {
         int var1 = bdb.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdb.this.pC.pC(bda.ah.pC());
         bdb.this.pC.pC(var3);
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
         bbc var3 = bdb.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdb.this.pC.vP) {
            var3.pC("objectPool", bdb.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdb.this.pC.kS(), bda.ys.toString(), bda.E.toString(), bda.RW.toString());
         var3.pC("exceptionHandlers", bdb.this.pC.kS(), bda.FE.toString());
         var3.pC("pcDescriptors", bdb.this.pC.kS(), bda.z.toString());
         var3.pC("catchEntry", bdb.this.pC.kS());
         if (bdb.this.pC.NS) {
            var3.pC("compressedStackMaps", bdb.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdb.this.pC.kS(), bda.be.toString());
         var3.pC("codeSourceMap", bdb.this.pC.kS(), bda.Ek.toString());
         if (!bdb.this.pC.ah && bdb.this.pC.NS) {
            var3.pC("deoptInfoArray", bdb.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdb.this.pC.kS());
         }

         if (!bdb.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdb.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdb.this.pC.rl ? bdb.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdb.this.pC.ah) {
            long var3 = bdb.this.pC.pF[2 * bdb.this.pC.OI];
            long var5 = bdb.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdb.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdb.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdb.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdb.bO {
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
            bbc var2 = bdb.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdb.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("cache", bdb.this.pC.kS());
            var2.pC("numInputs", bdb.this.pC.NS());
            var2.pC("numOccupied", bdb.this.pC.NS());
         }
      }
   }

   class gJ extends bdb.bO {
      @Override
      public void pC() {
         this.A(bda.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdb.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdb.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bda.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdb.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdb.this.pC.kS());
         var1.pC("hash", bdb.this.pC.kS());
         var1.pC("shape", bdb.this.pC.kS());
         var1.pC("fieldTypes", bdb.this.pC.kS());
      }
   }

   class io extends bdb.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bda.wQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdb.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdb.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdb.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bda.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdb.this.pC.vP());
            var2.pC("nullabity", bdb.this.pC.E());
            var2.pC("instantiations", bdb.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdb.this.pC.kS());
            }
         }
      }
   }

   class m extends bdb.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdb.this.pC.mv;
         long var1 = bdb.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdb.this.pC.E();
            bda var6 = (var4 & 1L) != 0L ? bda.dM : bda.eE;
            long var7 = var4 >> 1;
            bbc var9 = bdb.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdb.this.pC.pC(var9);
         }

         this.sY = bdb.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            long var3 = bdb.this.pC.E();
            long var5 = var3 >> 1;
            bda var7 = (var3 & 1L) != 0L ? bda.dM : bda.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bda.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdb.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdb.this.pC.gp();
                  var10 |= bdb.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            if (!bdb.this.pC.ah && !bdb.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdb.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdb.this.pC.kS());
         var1.pC("script", bdb.this.pC.kS());
         if (!bdb.this.pC.vP) {
            var1.pC("libraryKernelData", bdb.this.pC.kS());
         }
      }
   }

   class nA extends bdb.bO {
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
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdb.this.pC.kS());
         var1.pC("hashMask", bdb.this.pC.kS());
         var1.pC("data", bdb.this.pC.kS());
         var1.pC("usedData", bdb.this.pC.kS());
         var1.pC("deletedKeys", bdb.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdb.this.pC.vP());
            var2.pC("numImports", bdb.this.pC.fI());
            var2.pC("loadState", bdb.this.pC.oT());
            var2.pC("flags", bdb.this.pC.gp());
            if (!bdb.this.pC.ah && !bdb.this.pC.vP) {
               var2.pC("kernelOffset", bdb.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdb.this.pC.kS());
         var1.pC("url", bdb.this.pC.kS());
         var1.pC("privateKey", bdb.this.pC.kS());
         var1.pC("dictionary", bdb.this.pC.kS());
         var1.pC("metadata", bdb.this.pC.kS());
         var1.pC("toplevelClass", bdb.this.pC.kS());
         var1.pC("usedScripts", bdb.this.pC.kS());
         var1.pC("loadingUnit", bdb.this.pC.kS());
         var1.pC("imports", bdb.this.pC.kS());
         var1.pC("exports", bdb.this.pC.kS());
         if (!bdb.this.pC.vP) {
            var1.pC("dependencies", bdb.this.pC.kS());
            var1.pC("kernelProgramInfo", bdb.this.pC.kS());
         }
      }
   }

   class qt extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdb.this.pC.kS());
         var1.pC("length", bdb.this.pC.kS());
         var1.pC("data", bdb.this.pC.kS());
      }
   }

   class rQ extends bdb.bO {
      @Override
      public void pC() {
         this.A(bda.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            long var3 = bdb.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdb.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            if (!bdb.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdb.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdb.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdb.this.pC.kS(var5);
               if (var6.pC().equals(bda.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdb.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdb.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdb.this.pC.kS());
         var1.pC("owner", bdb.this.pC.kS());
         var1.pC("signature", bdb.this.pC.kS(), bda.e.toString());
         var1.pC("data", bdb.this.pC.kS());
      }
   }

   class uX extends bdb.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdb.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdb.this.pC.pC(bda.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdb.this.pC.xC());
            bdb.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdb.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdb.this.pC.mv;
         long var1 = bdb.this.pC.E();
         this.A = bdb.this.pC.vP();
         this.kS = bdb.this.pC.vP();
         bdb.this.pC.pC(var1, this.pC);
         this.sY = bdb.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdb.this.pC.ys.ys;
         int var2 = (int)bdb.this.pC.pC(this.kS * bdb.this.pC.ys.sY, bdb.this.pC.ys.ld);
         Long var3 = bdb.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdb.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdb.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdb.this.pC.ys.sY)) {
               if (bdb.this.pC.A(var3, var6 / (int)bdb.this.pC.ys.sY)) {
                  bdb.this.pC.Sc();
               } else {
                  bdb.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdb.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdb.bO {
      @Override
      public void pC() {
         this.pC(bda.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdb.this.pC.E());
            var2.pC("callbackKind", bdb.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdb.this.pC.kS());
         var1.pC("cSignature", bdb.this.pC.kS());
         var1.pC("callbackTarget", bdb.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdb.this.pC.kS());
      }
   }

   class zp extends bdb.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bda.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdb.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdb.this.pC.ED());
         }
      }
   }
}
