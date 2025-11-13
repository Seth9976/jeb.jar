package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bdv {
   private static final ILogger A = GlobalLog.getLogger(bdv.class);
   bdx pC;

   bdv(bdx var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   bdv.oP pC(int var1, boolean var2, boolean var3) {
      bdu var4 = bdu.pC(var1);
      if (var1 >= bdu.TV.pC() || var1 == bdu.Bf.pC()) {
         return new bdv.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bdv.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case Ek:
               case hK:
               case Er:
                  return new bdv.Pj(var2, var3, var1);
               case dM:
               case EM:
               case eE:
                  if (var3) {
                     return new bdv.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Ek:
               Assert.a(!var2);
               return new bdv.gJ();
            case hK:
               Assert.a(!var2);
               return new bdv.DH();
            case Er:
               Assert.a(!var2);
               return new bdv.rQ();
            case dM:
            case EM:
            default:
               return null;
            case eE:
               return new bdv.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bdv.Sv();
            case ld:
               return new bdv.Sf();
            case ck:
               return new bdv.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bdv.ma();
            case ys:
               Assert.a(!var2);
               return new bdv.sy();
            case gp:
               Assert.a(!var2);
               return new bdv.K();
            case oT:
               Assert.a(!var2);
               return new bdv.yt();
            case fI:
               Assert.a(!var2);
               return new bdv.RC();
            case WR:
               Assert.a(!var2);
               return new bdv.Kr();
            case NS:
               Assert.a(!var2);
               return new bdv.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bdv.cq();
            case z:
               Assert.a(!var2);
               return new bdv.Tb();
            case Aj:
               Assert.a(!var2);
               return new bdv.KD();
            case EX:
               Assert.a(!var2);
               return null;
            case LM:
               Assert.a(!var2);
               return null;
            case os:
               Assert.a(!var2);
               return new bdv.co();
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return null;
            case cX:
               Assert.a(!var2);
               return new bdv.eW();
            case DQ:
               Assert.a(!var2);
               return new bdv.GK();
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
               return new bdv.Sb(var2, var3);
            case xM:
               return new bdv.HE(var2, var3);
            case Kq:
               return new bdv.Hv(var2, var3);
            case gj:
               return new bdv.Ws(var2);
            case VD:
               return new bdv.uX(var2);
            case Xs:
               return new bdv.zp(var2);
            case Bi:
            case FK:
            case wQ:
               return null;
            case QQ:
               Assert.a(!var2);
               return new bdv.qt();
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
               return new bdv.Mh(var2, var1);
            case rC:
               throw new RuntimeException();
            case be:
               return new bdv.nA(var2, var1);
            case Xh:
            case sz:
               return new bdv.Av(var2, var1);
            case Sc:
               return new bdv.dE(var2, var1);
            case kU:
               return new bdv.gb(var2, var3);
            case PZ:
               return new bdv.io(var2);
         }
      }
   }

   class Av extends bdv.bO {
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
            bbc var2 = bdv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bdv.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdv.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bdv.bO {
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
            bbc var2 = bdv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bdv.this.pC.WR(this.pC);
            var2.pC("data", bdv.this.pC.wS(var4));
         }
      }
   }

   class DH extends bdv.bO {
      @Override
      public void pC() {
         this.A(bdu.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdv.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.DQ.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("parent", bdv.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("instructionsImage", null);
            int var3 = 0 | bdv.this.pC.vP() << 2;
            var2.pC("packedFields", var3);
         }
      }
   }

   class HE extends bdv.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdu.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bdv.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdv.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdv.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bdv.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bdv.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdv.this.pC.kS());
         var1.pC("hash", bdv.this.pC.kS());
         var1.pC("typeParameters", bdv.this.pC.kS());
         var1.pC("resultType", bdv.this.pC.kS());
         var1.pC("parameterTypes", bdv.this.pC.kS());
         var1.pC("namedParameterNames", bdv.this.pC.kS());
      }
   }

   class Hv extends bdv.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdu.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bdv.this.pC.fI());
            var2.pC("index", bdv.this.pC.fI());
            long var3 = bdv.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bdv.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bdv.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdv.this.pC.kS());
         var1.pC("hash", bdv.this.pC.kS());
         var1.pC("owner", bdv.this.pC.kS());
      }
   }

   class K extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            if (bdv.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bdv.this.pC.kS());
            }

            var2.pC("parentFunction", bdv.this.pC.kS());
            var2.pC("closure", bdv.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bdv.this.pC.E());
         }
      }
   }

   class KD extends bdv.bO {
      @Override
      public void pC() {
         this.A(bdu.Aj.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            long var3 = bdv.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bdv.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bdv.this.pC.sY());
               var9.put("outerTryIndex", bdv.this.pC.WR());
               var9.put("needsStacktrace", bdv.this.pC.A());
               var9.put("hasCatchAll", bdv.this.pC.A());
               var9.put("isGenerated", bdv.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            if (!bdv.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bdv.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bdv.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bdv.this.pC.kS());
         if (bdv.this.pC.vP) {
            if (!bdv.this.pC.Sc) {
               var1.pC("resolvedUrl", bdv.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bdv.this.pC.kS());
            var1.pC("resolvedUrl", bdv.this.pC.kS());
            var1.pC("lineStarts", bdv.this.pC.kS());
            var1.pC("constantCoverage", bdv.this.pC.kS());
            var1.pC("debugPositions", bdv.this.pC.kS());
            var1.pC("kernelProgramInfo", bdv.this.pC.kS());
         }
      }
   }

   class Mh extends bdv.bO {
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
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdv.this.pC.kS());
         var1.pC("hashMask", bdv.this.pC.kS());
         var1.pC("data", bdv.this.pC.kS());
         var1.pC("usedData", bdv.this.pC.kS());
         var1.pC("deletedKeys", bdv.this.pC.kS());
      }
   }

   class Pj extends bdv.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bdv.this.pC.mv;
         long var1 = bdv.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bdv.this.pC.E() << (int)bdv.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bdv.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bdv.this.pC.pC(var7);
         }

         this.sY = bdv.this.pC.mv;
         if (this.pC == bdu.eE.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bdu.dM.pC() && this.pC != bdu.eE.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bdv.this.pC.E.position(var1);
            bdv.this.pC.E.i32();
            long var2;
            if (bdv.this.pC.FE) {
               bdv.this.pC.E.i32();
               var2 = bdv.this.pC.E.i64();
            } else {
               bdv.this.pC.E.i32();
               var2 = bdv.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bdv.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bdv.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bdv.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdv.this.pC.kS());
         var1.pC("owner", bdv.this.pC.kS());
         var1.pC("type", bdv.this.pC.kS());
         var1.pC("initializerFunction", bdv.this.pC.kS());
      }
   }

   class Sb extends bdv.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdu.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bdv.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bdv.this.pC.kS());
         var1.pC("hash", bdv.this.pC.kS());
         var1.pC("arguments", bdv.this.pC.kS());
      }
   }

   class Sf extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.Kq.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bdv.this.pC.kS());
         var1.pC("flags", bdv.this.pC.kS());
         var1.pC("bounds", bdv.this.pC.kS());
         var1.pC("defaults", bdv.this.pC.kS());
      }
   }

   class Sv extends bdv.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bdv.this.pC.mv;
         long var1 = bdv.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bdv.this.pC.pC(bdu.E.pC());
            var2.pC("id", bdv.this.pC.wS());
            bdv.this.pC.pC(var2);
         });
         this.A = bdv.this.pC.mv;
         this.pC(bdu.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bdv.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bdv.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bdv.this.pC.ah && !bdv.this.pC.vP) {
                  var8.pC("kernelOffset", bdv.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bdv.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bdv.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bdv.this.pC.ys());
               if (!bdv.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bdv.this.pC.ys());
               var8.pC("numNativeFields", bdv.this.pC.E());
               if (!bdv.this.pC.ah) {
                  Assert.a(!bdv.this.pC.vP);
                  var8.pC("tokenPos", bdv.this.pC.UT());
                  var8.pC("endTokenPos", bdv.this.pC.UT());
               }

               var8.pC("stateBits", bdv.this.pC.E());
               if (bdv.this.pC.ah) {
                  if (var7) {
                     bdv.this.pC.E();
                  } else if (!bdv.this.pC.ys.pC(var9)) {
                     bdv.this.pC.os.put(var9, bdv.this.pC.E());
                  }
               }

               bdv.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdv.this.pC.kS());
         if (!bdv.this.pC.Sc) {
            var1.pC("userName", bdv.this.pC.kS());
         }

         var1.pC("functions", bdv.this.pC.kS());
         var1.pC("functionsHashTable", bdv.this.pC.kS());
         var1.pC("fields", bdv.this.pC.kS());
         var1.pC("offsetInWordsToField", bdv.this.pC.kS());
         var1.pC("interfaces", bdv.this.pC.kS());
         var1.pC("script", bdv.this.pC.kS());
         var1.pC("library", bdv.this.pC.kS());
         var1.pC("typeParameters", bdv.this.pC.kS());
         var1.pC("superType", bdv.this.pC.kS());
         var1.pC("constants", bdv.this.pC.kS());
         var1.pC("declarationType", bdv.this.pC.kS());
         var1.pC("invocationDispatcherCache", bdv.this.pC.kS());
         if (!bdv.this.pC.Sc || !bdv.this.pC.ah) {
            var1.pC("directImplementors", bdv.this.pC.kS());
            var1.pC("directSubclasses", bdv.this.pC.kS());
         }

         if (!bdv.this.pC.ah) {
            var1.pC("allocationStub", bdv.this.pC.kS());
            var1.pC("dependentCode", bdv.this.pC.kS());
         }
      }
   }

   class Tb extends bdv.bO {
      @Override
      public void pC() {
         this.A(bdu.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bdv.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bdv.this.pC.NS(var6);
               Assert.a(var8 != bdv.this.pC.ys.mv);
               if (var8 == bdv.this.pC.ys.LM) {
                  int var9 = bdv.this.pC.E(var6);
                  if (var9 == bdv.this.pC.ys.Ab) {
                     long var10 = bdv.this.pC.kS();
                     var7.put("rawObj", var10);
                  } else if (var9 == bdv.this.pC.ys.rl) {
                     long var12 = bdv.this.pC.ys();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bdv.this.pC.ys.z) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bdv.this.pC.ys.sO && var8 != bdv.this.pC.ys.os) {
                  if (var8 != bdv.this.pC.ys.Cu) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class Ws extends bdv.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdu.gj.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bdv.this.pC.kS());
         var1.pC("functionTypeArguments", bdv.this.pC.kS());
         var1.pC("delayedTypeArguments", bdv.this.pC.kS());
         var1.pC("function", bdv.this.pC.kS());
         var1.pC("context", bdv.this.pC.kS());
         var1.pC("hash", bdv.this.pC.kS());
      }
   }

   abstract class bO implements bdv.oP {
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
         this.E = bdv.this.pC.mv;
         long var2 = bdv.this.pC.E();
         bdv.this.pC.pC(var2, var1);
         this.sY = bdv.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bdv.this.pC.mv;
         long var2 = bdv.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bdv.this.pC.pC(var1);
            long var4 = bdv.this.pC.E();
            var3.pC("length", var4);
            bdv.this.pC.pC(var3);
         });
         this.sY = bdv.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bdv.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bdv.this.pC.E();
            long var1 = bdv.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bdv.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.os.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bdv.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bdv.this.pC.kS());
         var1.pC("argsDescriptor", bdv.this.pC.kS());
      }
   }

   class cq extends bdv.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bdv.this.pC.mv;
         bdv.this.pC.Bf = this.E;
         long var1 = bdv.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bdv.this.pC.mv;
         this.pC = bdv.this.pC.mv;
         var1 = bdv.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bdv.this.pC.mv;
      }

      private void wS() {
         int var1 = bdv.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bdv.this.pC.pC(bdu.ah.pC());
         bdv.this.pC.pC(var3);
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
         bbc var3 = bdv.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bdv.this.pC.vP) {
            var3.pC("objectPool", bdv.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bdv.this.pC.kS(), bdu.ys.toString(), bdu.E.toString(), bdu.e.toString());
         var3.pC("exceptionHandlers", bdv.this.pC.kS(), bdu.Aj.toString());
         var3.pC("pcDescriptors", bdv.this.pC.kS(), bdu.Ek.toString());
         var3.pC("catchEntry", bdv.this.pC.kS());
         if (bdv.this.pC.NS) {
            var3.pC("compressedStackMaps", bdv.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bdv.this.pC.kS(), bdu.Xh.toString());
         var3.pC("codeSourceMap", bdv.this.pC.kS(), bdu.hK.toString());
         if (!bdv.this.pC.ah && bdv.this.pC.NS) {
            var3.pC("deoptInfoArray", bdv.this.pC.kS());
            var3.pC("staticCallsTargetTable", bdv.this.pC.kS());
         }

         if (!bdv.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bdv.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bdv.this.pC.rl ? bdv.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bdv.this.pC.ah) {
            long var3 = bdv.this.pC.pF[2 * bdv.this.pC.OI];
            long var5 = bdv.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bdv.this.pC.ys.UO : 0L;
            long var12 = var9 ? bdv.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bdv.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bdv.bO {
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
            bbc var2 = bdv.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bdv.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("cache", bdv.this.pC.kS());
            var2.pC("numInputs", bdv.this.pC.NS());
            var2.pC("numOccupied", bdv.this.pC.NS());
         }
      }
   }

   class gJ extends bdv.bO {
      @Override
      public void pC() {
         this.A(bdu.Ek.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bdv.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bdv.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bdu.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bdv.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bdv.this.pC.kS());
         var1.pC("hash", bdv.this.pC.kS());
         var1.pC("shape", bdv.this.pC.kS());
         var1.pC("fieldTypes", bdv.this.pC.kS());
      }
   }

   class io extends bdv.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bdu.PZ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bdv.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bdv.this.pC.kS();
            }
         }
      }
   }

   class jM extends bdv.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bdu.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bdv.this.pC.vP());
            var2.pC("nullabity", bdv.this.pC.E());
            var2.pC("instantiations", bdv.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bdv.this.pC.kS());
            }
         }
      }
   }

   class m extends bdv.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bdv.this.pC.mv;
         long var1 = bdv.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bdv.this.pC.E();
            bdu var6 = (var4 & 1L) != 0L ? bdu.EM : bdu.dM;
            long var7 = var4 >> 1;
            bbc var9 = bdv.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bdv.this.pC.pC(var9);
         }

         this.sY = bdv.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            long var3 = bdv.this.pC.E();
            long var5 = var3 >> 1;
            bdu var7 = (var3 & 1L) != 0L ? bdu.EM : bdu.dM;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bdu.dM) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bdv.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bdv.this.pC.gp();
                  var10 |= bdv.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            if (!bdv.this.pC.ah && !bdv.this.pC.vP) {
               var2.pC("libraryKernelOffset", bdv.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bdv.this.pC.kS());
         var1.pC("script", bdv.this.pC.kS());
         if (!bdv.this.pC.vP) {
            var1.pC("libraryKernelData", bdv.this.pC.kS());
         }
      }
   }

   class nA extends bdv.bO {
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
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bdv.this.pC.kS());
         var1.pC("hashMask", bdv.this.pC.kS());
         var1.pC("data", bdv.this.pC.kS());
         var1.pC("usedData", bdv.this.pC.kS());
         var1.pC("deletedKeys", bdv.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bdv.this.pC.vP());
            var2.pC("numImports", bdv.this.pC.fI());
            var2.pC("loadState", bdv.this.pC.oT());
            var2.pC("flags", bdv.this.pC.gp());
            if (!bdv.this.pC.ah && !bdv.this.pC.vP) {
               var2.pC("kernelOffset", bdv.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdv.this.pC.kS());
         var1.pC("url", bdv.this.pC.kS());
         var1.pC("privateKey", bdv.this.pC.kS());
         var1.pC("dictionary", bdv.this.pC.kS());
         var1.pC("metadata", bdv.this.pC.kS());
         var1.pC("toplevelClass", bdv.this.pC.kS());
         var1.pC("usedScripts", bdv.this.pC.kS());
         var1.pC("loadingUnit", bdv.this.pC.kS());
         var1.pC("imports", bdv.this.pC.kS());
         var1.pC("exports", bdv.this.pC.kS());
         if (!bdv.this.pC.vP) {
            var1.pC("dependencies", bdv.this.pC.kS());
            var1.pC("kernelProgramInfo", bdv.this.pC.kS());
         }
      }
   }

   class qt extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.QQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bdv.this.pC.kS());
         var1.pC("length", bdv.this.pC.kS());
         var1.pC("data", bdv.this.pC.kS());
      }
   }

   class rQ extends bdv.bO {
      @Override
      public void pC() {
         this.A(bdu.Er.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            long var3 = bdv.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bdv.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            if (!bdv.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bdv.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bdv.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bdv.this.pC.kS(var5);
               if (var6.pC().equals(bdu.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bdv.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bdv.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bdv.this.pC.kS());
         var1.pC("owner", bdv.this.pC.kS());
         var1.pC("signature", bdv.this.pC.kS(), bdu.xM.toString());
         var1.pC("data", bdv.this.pC.kS());
      }
   }

   class uX extends bdv.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bdv.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bdv.this.pC.pC(bdu.VD.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bdv.this.pC.xC());
            bdv.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bdv.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bdv.this.pC.mv;
         long var1 = bdv.this.pC.E();
         this.A = bdv.this.pC.vP();
         this.kS = bdv.this.pC.vP();
         bdv.this.pC.pC(var1, this.pC);
         this.sY = bdv.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bdv.this.pC.ys.ys;
         int var2 = (int)bdv.this.pC.pC(this.kS * bdv.this.pC.ys.sY, bdv.this.pC.ys.ld);
         Long var3 = bdv.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bdv.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bdv.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bdv.this.pC.ys.sY)) {
               if (bdv.this.pC.A(var3, var6 / (int)bdv.this.pC.ys.sY)) {
                  bdv.this.pC.Sc();
               } else {
                  bdv.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bdv.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bdv.bO {
      @Override
      public void pC() {
         this.pC(bdu.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bdv.this.pC.E());
            var2.pC("callbackKind", bdv.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bdv.this.pC.kS());
         var1.pC("cSignature", bdv.this.pC.kS());
         var1.pC("callbackTarget", bdv.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bdv.this.pC.kS());
      }
   }

   class zp extends bdv.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bdu.Xs.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bdv.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bdv.this.pC.ED());
         }
      }
   }
}
