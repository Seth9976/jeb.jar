package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bcx {
   private static final ILogger A = GlobalLog.getLogger(bcx.class);
   bcz pC;

   bcx(bcz var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   bcx.oP pC(int var1, boolean var2, boolean var3) {
      bcw var4 = bcw.pC(var1);
      if (var1 >= bcw.pY.pC() || var1 == bcw.OI.pC()) {
         return new bcx.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bcx.B(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case z:
               case Ek:
               case hK:
                  return new bcx.Pj(var2, var3, var1);
               case eE:
               case dM:
               case QQ:
                  if (var3) {
                     return new bcx.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case z:
               Assert.a(!var2);
               return new bcx.gJ();
            case Ek:
               Assert.a(!var2);
               return new bcx.DH();
            case hK:
               Assert.a(!var2);
               return new bcx.rQ();
            case eE:
            case dM:
            default:
               return null;
            case QQ:
               return new bcx.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bcx.Sv();
            case ld:
               return new bcx.Sf();
            case Pe:
               return new bcx.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bcx.ma();
            case ys:
               Assert.a(!var2);
               return new bcx.sy();
            case gp:
               Assert.a(!var2);
               return new bcx.K();
            case oT:
               Assert.a(!var2);
               return new bcx.yt();
            case fI:
               Assert.a(!var2);
               return new bcx.RC();
            case WR:
               Assert.a(!var2);
               return new bcx.Kr();
            case NS:
               Assert.a(!var2);
               return new bcx.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bcx.cq();
            case rl:
               Assert.a(!var2);
               return new bcx.Tb();
            case FE:
               Assert.a(!var2);
               return new bcx.KD();
            case Aj:
               Assert.a(!var2);
               return null;
            case EX:
               Assert.a(!var2);
               return null;
            case sO:
               Assert.a(!var2);
               return new bcx.co();
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return new bcx.eW();
            case cX:
               Assert.a(!var2);
               return new bcx.GK();
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
               return new bcx.Sb(var2, var3);
            case e:
               return new bcx.HE(var2, var3);
            case kU:
               return new bcx.Hv(var2, var3);
            case pg:
               return new bcx.Ws(var2);
            case UH:
               return new bcx.uX(var2);
            case VD:
               return new bcx.zp(var2);
            case sz:
               Assert.a(!var2);
               return new bcx.qt();
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
               return new bcx.Mh(var2, var1);
            case LL:
               throw new RuntimeException();
            case rC:
               return new bcx.nA(var2, var1);
            case be:
            case Xh:
               return new bcx.Av(var2, var1);
            case Sc:
               return new bcx.dE(var2, var1);
            case xM:
               return new bcx.gb(var2, var3);
            case wQ:
               return new bcx.io(var2);
         }
      }
   }

   class Av extends bcx.bO {
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
            bbc var2 = bcx.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bcx.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcx.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bcx.bO {
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
            bbc var2 = bcx.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bcx.this.pC.WR(this.pC);
            var2.pC("data", bcx.this.pC.wS(var4));
         }
      }
   }

   class DH extends bcx.bO {
      @Override
      public void pC() {
         this.A(bcw.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcx.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("parent", bcx.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bcx.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bcx.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcw.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bcx.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcx.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcx.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bcx.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bcx.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcx.this.pC.kS());
         var1.pC("hash", bcx.this.pC.kS());
         var1.pC("typeParameters", bcx.this.pC.kS());
         var1.pC("resultType", bcx.this.pC.kS());
         var1.pC("parameterTypes", bcx.this.pC.kS());
         var1.pC("namedParameterNames", bcx.this.pC.kS());
      }
   }

   class Hv extends bcx.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcw.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("base", bcx.this.pC.fI());
            var2.pC("index", bcx.this.pC.fI());
            long var3 = bcx.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcx.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcx.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcx.this.pC.kS());
         var1.pC("hash", bcx.this.pC.kS());
         var1.pC("owner", bcx.this.pC.kS());
      }
   }

   class K extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            if (bcx.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bcx.this.pC.kS());
            }

            var2.pC("parentFunction", bcx.this.pC.kS());
            var2.pC("closure", bcx.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bcx.this.pC.E());
         }
      }
   }

   class KD extends bcx.bO {
      @Override
      public void pC() {
         this.A(bcw.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            long var3 = bcx.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bcx.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bcx.this.pC.sY());
               var9.put("outerTryIndex", bcx.this.pC.WR());
               var9.put("needsStacktrace", bcx.this.pC.A());
               var9.put("hasCatchAll", bcx.this.pC.A());
               var9.put("isGenerated", bcx.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            if (!bcx.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bcx.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bcx.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bcx.this.pC.kS());
         if (bcx.this.pC.vP) {
            if (!bcx.this.pC.Sc) {
               var1.pC("resolvedUrl", bcx.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bcx.this.pC.kS());
            var1.pC("resolvedUrl", bcx.this.pC.kS());
            var1.pC("lineStarts", bcx.this.pC.kS());
            var1.pC("constantCoverage", bcx.this.pC.kS());
            var1.pC("debugPositions", bcx.this.pC.kS());
            var1.pC("kernelProgramInfo", bcx.this.pC.kS());
         }
      }
   }

   class Mh extends bcx.bO {
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
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcx.this.pC.kS());
         var1.pC("hashMask", bcx.this.pC.kS());
         var1.pC("data", bcx.this.pC.kS());
         var1.pC("usedData", bcx.this.pC.kS());
         var1.pC("deletedKeys", bcx.this.pC.kS());
      }
   }

   class Pj extends bcx.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bcx.this.pC.mv;
         long var1 = bcx.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bcx.this.pC.E() << (int)bcx.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bcx.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bcx.this.pC.pC(var7);
         }

         this.sY = bcx.this.pC.mv;
         if (this.pC == bcw.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bcw.eE.pC() && this.pC != bcw.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bcx.this.pC.E.position(var1);
            bcx.this.pC.E.i32();
            long var2;
            if (bcx.this.pC.FE) {
               bcx.this.pC.E.i32();
               var2 = bcx.this.pC.E.i64();
            } else {
               bcx.this.pC.E.i32();
               var2 = bcx.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bcx.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bcx.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bcx.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcx.this.pC.kS());
         var1.pC("owner", bcx.this.pC.kS());
         var1.pC("type", bcx.this.pC.kS());
         var1.pC("initializerFunction", bcx.this.pC.kS());
      }
   }

   class Sb extends bcx.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcw.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bcx.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcx.this.pC.kS());
         var1.pC("hash", bcx.this.pC.kS());
         var1.pC("arguments", bcx.this.pC.kS());
      }
   }

   class Sf extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bcx.this.pC.kS());
         var1.pC("flags", bcx.this.pC.kS());
         var1.pC("bounds", bcx.this.pC.kS());
         var1.pC("defaults", bcx.this.pC.kS());
      }
   }

   class Sv extends bcx.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bcx.this.pC.mv;
         long var1 = bcx.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bcx.this.pC.pC(bcw.E.pC());
            var2.pC("id", bcx.this.pC.wS());
            bcx.this.pC.pC(var2);
         });
         this.A = bcx.this.pC.mv;
         this.pC(bcw.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bcx.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bcx.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bcx.this.pC.ah && !bcx.this.pC.vP) {
                  var8.pC("kernelOffset", bcx.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bcx.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bcx.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bcx.this.pC.ys());
               if (!bcx.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bcx.this.pC.ys());
               var8.pC("numNativeFields", bcx.this.pC.E());
               if (!bcx.this.pC.ah) {
                  Assert.a(!bcx.this.pC.vP);
                  var8.pC("tokenPos", bcx.this.pC.UT());
                  var8.pC("endTokenPos", bcx.this.pC.UT());
               }

               var8.pC("stateBits", bcx.this.pC.E());
               if (bcx.this.pC.ah) {
                  if (var7) {
                     bcx.this.pC.E();
                  } else if (!bcx.this.pC.ys.pC(var9)) {
                     bcx.this.pC.os.put(var9, bcx.this.pC.E());
                  }
               }

               bcx.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcx.this.pC.kS());
         if (!bcx.this.pC.Sc) {
            var1.pC("userName", bcx.this.pC.kS());
         }

         var1.pC("functions", bcx.this.pC.kS());
         var1.pC("functionsHashTable", bcx.this.pC.kS());
         var1.pC("fields", bcx.this.pC.kS());
         var1.pC("offsetInWordsToField", bcx.this.pC.kS());
         var1.pC("interfaces", bcx.this.pC.kS());
         var1.pC("script", bcx.this.pC.kS());
         var1.pC("library", bcx.this.pC.kS());
         var1.pC("typeParameters", bcx.this.pC.kS());
         var1.pC("superType", bcx.this.pC.kS());
         var1.pC("constants", bcx.this.pC.kS());
         var1.pC("declarationType", bcx.this.pC.kS());
         var1.pC("invocationDispatcherCache", bcx.this.pC.kS());
         if (!bcx.this.pC.Sc || !bcx.this.pC.ah) {
            var1.pC("directImplementors", bcx.this.pC.kS());
            var1.pC("directSubclasses", bcx.this.pC.kS());
         }

         if (!bcx.this.pC.ah) {
            var1.pC("allocationStub", bcx.this.pC.kS());
            var1.pC("dependentCode", bcx.this.pC.kS());
         }
      }
   }

   class Tb extends bcx.bO {
      @Override
      public void pC() {
         this.A(bcw.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bcx.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bcx.this.pC.E(var6);
               if (var8 == bcx.this.pC.ys.Ab) {
                  var7.put("rawObj", bcx.this.pC.kS());
               } else if (var8 == bcx.this.pC.ys.rl) {
                  var7.put("rawValue", bcx.this.pC.ys());
               } else if (var8 == bcx.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bcx.this.pC.ys.Er && var8 != bcx.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bcx.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Ws extends bcx.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcw.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bcx.this.pC.kS());
         var1.pC("functionTypeArguments", bcx.this.pC.kS());
         var1.pC("delayedTypeArguments", bcx.this.pC.kS());
         var1.pC("function", bcx.this.pC.kS());
         var1.pC("context", bcx.this.pC.kS());
         var1.pC("hash", bcx.this.pC.kS());
      }
   }

   abstract class bO implements bcx.oP {
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
         this.E = bcx.this.pC.mv;
         long var2 = bcx.this.pC.E();
         bcx.this.pC.pC(var2, var1);
         this.sY = bcx.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bcx.this.pC.mv;
         long var2 = bcx.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bcx.this.pC.pC(var1);
            long var4 = bcx.this.pC.E();
            var3.pC("length", var4);
            bcx.this.pC.pC(var3);
         });
         this.sY = bcx.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bcx.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bcx.this.pC.E();
            long var1 = bcx.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bcx.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bcx.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bcx.this.pC.kS());
         var1.pC("argsDescriptor", bcx.this.pC.kS());
      }
   }

   class cq extends bcx.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bcx.this.pC.mv;
         bcx.this.pC.Bf = this.E;
         long var1 = bcx.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bcx.this.pC.mv;
         this.pC = bcx.this.pC.mv;
         var1 = bcx.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bcx.this.pC.mv;
      }

      private void wS() {
         int var1 = bcx.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bcx.this.pC.pC(bcw.ah.pC());
         bcx.this.pC.pC(var3);
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
         bbc var3 = bcx.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bcx.this.pC.vP) {
            var3.pC("objectPool", bcx.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bcx.this.pC.kS(), bcw.ys.toString(), bcw.E.toString(), bcw.RW.toString());
         var3.pC("exceptionHandlers", bcx.this.pC.kS(), bcw.FE.toString());
         var3.pC("pcDescriptors", bcx.this.pC.kS(), bcw.z.toString());
         var3.pC("catchEntry", bcx.this.pC.kS());
         if (bcx.this.pC.NS) {
            var3.pC("compressedStackMaps", bcx.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bcx.this.pC.kS(), bcw.be.toString());
         var3.pC("codeSourceMap", bcx.this.pC.kS(), bcw.Ek.toString());
         if (!bcx.this.pC.ah && bcx.this.pC.NS) {
            var3.pC("deoptInfoArray", bcx.this.pC.kS());
            var3.pC("staticCallsTargetTable", bcx.this.pC.kS());
         }

         if (!bcx.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bcx.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bcx.this.pC.rl ? bcx.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bcx.this.pC.ah) {
            long var3 = bcx.this.pC.pF[2 * bcx.this.pC.OI];
            long var5 = bcx.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bcx.this.pC.ys.UO : 0L;
            long var12 = var9 ? bcx.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bcx.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bcx.bO {
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
            bbc var2 = bcx.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcx.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class eW extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("cache", bcx.this.pC.kS());
            var2.pC("numInputs", bcx.this.pC.NS());
            var2.pC("numOccupied", bcx.this.pC.NS());
         }
      }
   }

   class gJ extends bcx.bO {
      @Override
      public void pC() {
         this.A(bcw.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcx.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bcx.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bcw.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bcx.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bcx.this.pC.kS());
         var1.pC("hash", bcx.this.pC.kS());
         var1.pC("shape", bcx.this.pC.kS());
         var1.pC("fieldTypes", bcx.this.pC.kS());
      }
   }

   class io extends bcx.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bcw.wQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bcx.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bcx.this.pC.kS();
            }
         }
      }
   }

   class jM extends bcx.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bcw.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bcx.this.pC.vP());
            var2.pC("nullabity", bcx.this.pC.E());
            var2.pC("instantiations", bcx.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bcx.this.pC.kS());
            }
         }
      }
   }

   class m extends bcx.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bcx.this.pC.mv;
         long var1 = bcx.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bcx.this.pC.E();
            bcw var6 = (var4 & 1L) != 0L ? bcw.dM : bcw.eE;
            long var7 = var4 >> 1;
            bbc var9 = bcx.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bcx.this.pC.pC(var9);
         }

         this.sY = bcx.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            long var3 = bcx.this.pC.E();
            long var5 = var3 >> 1;
            bcw var7 = (var3 & 1L) != 0L ? bcw.dM : bcw.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bcw.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bcx.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bcx.this.pC.gp();
                  var10 |= bcx.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            if (!bcx.this.pC.ah && !bcx.this.pC.vP) {
               var2.pC("libraryKernelOffset", bcx.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("wrappedClass", bcx.this.pC.kS());
         var1.pC("script", bcx.this.pC.kS());
         if (!bcx.this.pC.vP) {
            var1.pC("libraryKernelData", bcx.this.pC.kS());
         }
      }
   }

   class nA extends bcx.bO {
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
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcx.this.pC.kS());
         var1.pC("hashMask", bcx.this.pC.kS());
         var1.pC("data", bcx.this.pC.kS());
         var1.pC("usedData", bcx.this.pC.kS());
         var1.pC("deletedKeys", bcx.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bcx.this.pC.vP());
            var2.pC("numImports", bcx.this.pC.fI());
            var2.pC("loadState", bcx.this.pC.oT());
            var2.pC("flags", bcx.this.pC.gp());
            if (!bcx.this.pC.ah && !bcx.this.pC.vP) {
               var2.pC("kernelOffset", bcx.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcx.this.pC.kS());
         var1.pC("url", bcx.this.pC.kS());
         var1.pC("privateKey", bcx.this.pC.kS());
         var1.pC("dictionary", bcx.this.pC.kS());
         var1.pC("metadata", bcx.this.pC.kS());
         var1.pC("toplevelClass", bcx.this.pC.kS());
         var1.pC("usedScripts", bcx.this.pC.kS());
         var1.pC("loadingUnit", bcx.this.pC.kS());
         var1.pC("imports", bcx.this.pC.kS());
         var1.pC("exports", bcx.this.pC.kS());
         if (!bcx.this.pC.vP) {
            var1.pC("dependencies", bcx.this.pC.kS());
            var1.pC("kernelProgramInfo", bcx.this.pC.kS());
         }
      }
   }

   class qt extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bcx.this.pC.kS());
         var1.pC("length", bcx.this.pC.kS());
         var1.pC("data", bcx.this.pC.kS());
      }
   }

   class rQ extends bcx.bO {
      @Override
      public void pC() {
         this.A(bcw.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            long var3 = bcx.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bcx.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            if (!bcx.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bcx.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bcx.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bcx.this.pC.kS(var5);
               if (var6.pC().equals(bcw.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bcx.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bcx.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcx.this.pC.kS());
         var1.pC("owner", bcx.this.pC.kS());
         var1.pC("signature", bcx.this.pC.kS(), bcw.e.toString());
         var1.pC("data", bcx.this.pC.kS());
      }
   }

   class uX extends bcx.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bcx.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bcx.this.pC.pC(bcw.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bcx.this.pC.xC());
            bcx.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bcx.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bcx.this.pC.mv;
         long var1 = bcx.this.pC.E();
         this.A = bcx.this.pC.vP();
         this.kS = bcx.this.pC.vP();
         bcx.this.pC.pC(var1, this.pC);
         this.sY = bcx.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bcx.this.pC.ys.ys;
         int var2 = (int)bcx.this.pC.pC(this.kS * bcx.this.pC.ys.sY, bcx.this.pC.ys.ld);
         Long var3 = bcx.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bcx.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bcx.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bcx.this.pC.ys.sY)) {
               if (bcx.this.pC.A(var3, var6 / (int)bcx.this.pC.ys.sY)) {
                  bcx.this.pC.Sc();
               } else {
                  bcx.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bcx.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bcx.bO {
      @Override
      public void pC() {
         this.pC(bcw.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bcx.this.pC.E());
            var2.pC("callbackKind", bcx.this.pC.gp());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bcx.this.pC.kS());
         var1.pC("cSignature", bcx.this.pC.kS());
         var1.pC("callbackTarget", bcx.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bcx.this.pC.kS());
      }
   }

   class zp extends bcx.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bcw.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcx.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bcx.this.pC.ED());
         }
      }
   }
}
