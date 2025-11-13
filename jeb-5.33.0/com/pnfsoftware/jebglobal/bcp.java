package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bcp {
   private static final ILogger A = GlobalLog.getLogger(bcp.class);
   bcr pC;

   bcp(bcr var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bcp.oP pC(int var1, boolean var2, boolean var3) {
      bco var4 = bco.pC(var1);
      if (var1 >= bco.l.pC() || var1 == bco.OI.pC()) {
         return new bcp.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bcp.co(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case z:
               case Ek:
               case hK:
                  return new bcp.Pj(var2, var3, var1);
               case dM:
               case EM:
               case eE:
                  if (var3) {
                     return new bcp.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case z:
               Assert.a(!var2);
               return new bcp.gJ();
            case Ek:
               Assert.a(!var2);
               return new bcp.DH();
            case hK:
               Assert.a(!var2);
               return new bcp.rQ();
            case dM:
            case EM:
            default:
               return null;
            case eE:
               return new bcp.m(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bcp.Sv();
            case ld:
               return new bcp.Sf();
            case Pe:
               return new bcp.jM(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bcp.ma();
            case ys:
               Assert.a(!var2);
               return new bcp.sy();
            case gp:
               Assert.a(!var2);
               return new bcp.K();
            case oT:
               Assert.a(!var2);
               return new bcp.yt();
            case fI:
               Assert.a(!var2);
               return new bcp.RC();
            case WR:
               Assert.a(!var2);
               return new bcp.Kr();
            case NS:
               Assert.a(!var2);
               return new bcp.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case ah:
               Assert.a(!var2);
               return new bcp.cq();
            case rl:
               Assert.a(!var2);
               return new bcp.Tb();
            case FE:
               Assert.a(!var2);
               return new bcp.KD();
            case Aj:
               Assert.a(!var2);
               return null;
            case EX:
               Assert.a(!var2);
               return null;
            case sO:
               Assert.a(!var2);
               return new bcp.dE();
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return null;
            case PR:
               Assert.a(!var2);
               return new bcp.eW();
            case cX:
               Assert.a(!var2);
               return new bcp.GK();
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
               return new bcp.Sb(var2, var3);
            case e:
               return new bcp.HE(var2, var3);
            case kU:
               Assert.a(!var2);
               return new bcp.B();
            case Kq:
               return new bcp.Hv(var2, var3);
            case gj:
               return new bcp.Ws(var2);
            case VD:
               return new bcp.uX(var2);
            case Xs:
               return new bcp.zp(var2);
            case QQ:
               Assert.a(!var2);
               return new bcp.qt();
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
               return new bcp.Mh(var2, var1);
            case rC:
               throw new RuntimeException();
            case be:
               return new bcp.nA(var2, var1);
            case Xh:
            case sz:
               return new bcp.Av(var2, var1);
            case Sc:
               return new bcp.Yd(var2, var1);
            case xM:
               return new bcp.gb(var2, var3);
            case PZ:
               return new bcp.io(var2);
         }
      }
   }

   class Av extends bcp.bO {
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
            bbc var2 = bcp.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bcp.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcp.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.kU.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcp.this.pC.kS());
         var1.pC("type", bcp.this.pC.kS());
      }
   }

   class DH extends bcp.bO {
      @Override
      public void pC() {
         this.A(bco.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcp.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.cX.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("parent", bcp.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bcp.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bcp.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bco.e.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bcp.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcp.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcp.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bcp.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bcp.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcp.this.pC.kS());
         var1.pC("typeParameters", bcp.this.pC.kS());
         var1.pC("resultType", bcp.this.pC.kS());
         var1.pC("parameterTypes", bcp.this.pC.kS());
         var1.pC("namedParameterNames", bcp.this.pC.kS());
         var1.pC("hash", bcp.this.pC.kS());
      }
   }

   class Hv extends bcp.bO {
      Hv(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bco.Kq.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bcp.this.pC.vP());
            var2.pC("base", bcp.this.pC.fI());
            var2.pC("index", bcp.this.pC.fI());
            long var3 = bcp.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcp.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcp.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcp.this.pC.kS());
         var1.pC("hash", bcp.this.pC.kS());
         var1.pC("bound", bcp.this.pC.kS());
      }
   }

   class K extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            if (bcp.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bcp.this.pC.kS());
            }

            var2.pC("parentFunction", bcp.this.pC.kS());
            var2.pC("closure", bcp.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bcp.this.pC.E());
         }
      }
   }

   class KD extends bcp.bO {
      @Override
      public void pC() {
         this.A(bco.FE.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            long var3 = bcp.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bcp.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bcp.this.pC.sY());
               var9.put("outerTryIndex", bcp.this.pC.WR());
               var9.put("needsStacktrace", bcp.this.pC.A());
               var9.put("hasCatchAll", bcp.this.pC.A());
               var9.put("isGenerated", bcp.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            if (!bcp.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bcp.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bcp.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bcp.this.pC.kS());
         if (bcp.this.pC.vP) {
            if (!bcp.this.pC.Sc) {
               var1.pC("resolvedUrl", bcp.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bcp.this.pC.kS());
            var1.pC("resolvedUrl", bcp.this.pC.kS());
            var1.pC("lineStarts", bcp.this.pC.kS());
            var1.pC("constantCoverage", bcp.this.pC.kS());
            var1.pC("debugPositions", bcp.this.pC.kS());
            var1.pC("kernelProgramInfo", bcp.this.pC.kS());
         }
      }
   }

   class Mh extends bcp.bO {
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
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcp.this.pC.kS());
         var1.pC("hashMask", bcp.this.pC.kS());
         var1.pC("data", bcp.this.pC.kS());
         var1.pC("usedData", bcp.this.pC.kS());
         var1.pC("deletedKeys", bcp.this.pC.kS());
      }
   }

   class Pj extends bcp.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bcp.this.pC.mv;
         long var1 = bcp.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bcp.this.pC.E() << (int)bcp.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bcp.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bcp.this.pC.pC(var7);
         }

         this.sY = bcp.this.pC.mv;
         if (this.pC == bco.eE.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bco.dM.pC() && this.pC != bco.eE.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bcp.this.pC.E.position(var1);
            bcp.this.pC.E.i32();
            long var2;
            if (bcp.this.pC.FE) {
               bcp.this.pC.E.i32();
               var2 = bcp.this.pC.E.i64();
            } else {
               bcp.this.pC.E.i32();
               var2 = bcp.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bcp.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bcp.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bcp.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcp.this.pC.kS());
         var1.pC("owner", bcp.this.pC.kS());
         var1.pC("type", bcp.this.pC.kS());
         var1.pC("initializerFunction", bcp.this.pC.kS());
      }
   }

   class Sb extends bcp.bO {
      Sb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bco.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bcp.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcp.this.pC.kS());
         var1.pC("arguments", bcp.this.pC.kS());
         var1.pC("hash", bcp.this.pC.kS());
      }
   }

   class Sf extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.Kq.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bcp.this.pC.kS());
         var1.pC("flags", bcp.this.pC.kS());
         var1.pC("bounds", bcp.this.pC.kS());
         var1.pC("defaults", bcp.this.pC.kS());
      }
   }

   class Sv extends bcp.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bcp.this.pC.mv;
         long var1 = bcp.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bcp.this.pC.pC(bco.E.pC());
            var2.pC("id", bcp.this.pC.wS());
            bcp.this.pC.pC(var2);
         });
         this.A = bcp.this.pC.mv;
         this.pC(bco.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bcp.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bcp.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bcp.this.pC.ah && !bcp.this.pC.vP) {
                  var8.pC("kernelOffset", bcp.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bcp.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bcp.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bcp.this.pC.ys());
               if (!bcp.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bcp.this.pC.ys());
               var8.pC("numNativeFields", bcp.this.pC.E());
               if (!bcp.this.pC.ah) {
                  Assert.a(!bcp.this.pC.vP);
                  var8.pC("tokenPos", bcp.this.pC.UT());
                  var8.pC("endTokenPos", bcp.this.pC.UT());
               }

               var8.pC("stateBits", bcp.this.pC.E());
               if (bcp.this.pC.ah) {
                  if (var7) {
                     bcp.this.pC.E();
                  } else if (!bcp.this.pC.ys.pC(var9)) {
                     bcp.this.pC.os.put(var9, bcp.this.pC.E());
                  }
               }

               bcp.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcp.this.pC.kS());
         if (!bcp.this.pC.Sc) {
            var1.pC("userName", bcp.this.pC.kS());
         }

         var1.pC("functions", bcp.this.pC.kS());
         var1.pC("functionsHashTable", bcp.this.pC.kS());
         var1.pC("fields", bcp.this.pC.kS());
         var1.pC("offsetInWordsToField", bcp.this.pC.kS());
         var1.pC("interfaces", bcp.this.pC.kS());
         var1.pC("script", bcp.this.pC.kS());
         var1.pC("library", bcp.this.pC.kS());
         var1.pC("typeParameters", bcp.this.pC.kS());
         var1.pC("superType", bcp.this.pC.kS());
         var1.pC("constants", bcp.this.pC.kS());
         var1.pC("declarationType", bcp.this.pC.kS());
         var1.pC("invocationDispatcherCache", bcp.this.pC.kS());
         if (!bcp.this.pC.Sc || !bcp.this.pC.ah) {
            var1.pC("directImplementors", bcp.this.pC.kS());
            var1.pC("directSubclasses", bcp.this.pC.kS());
         }

         if (!bcp.this.pC.ah) {
            var1.pC("allocationStub", bcp.this.pC.kS());
            var1.pC("dependentCode", bcp.this.pC.kS());
         }
      }
   }

   class Tb extends bcp.bO {
      @Override
      public void pC() {
         this.A(bco.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bcp.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bcp.this.pC.E(var6);
               if (var8 == bcp.this.pC.ys.Ab) {
                  var7.put("rawObj", bcp.this.pC.kS());
               } else if (var8 == bcp.this.pC.ys.rl) {
                  var7.put("rawValue", bcp.this.pC.ys());
               } else if (var8 == bcp.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bcp.this.pC.ys.Er && var8 != bcp.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bcp.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Ws extends bcp.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bco.gj.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bcp.this.pC.kS());
         var1.pC("functionTypeArguments", bcp.this.pC.kS());
         var1.pC("delayedTypeArguments", bcp.this.pC.kS());
         var1.pC("function", bcp.this.pC.kS());
         var1.pC("context", bcp.this.pC.kS());
         var1.pC("hash", bcp.this.pC.kS());
      }
   }

   class Yd extends bcp.bO {
      int pC;

      Yd(boolean var2, int var3) {
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
            bbc var2 = bcp.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcp.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   abstract class bO implements bcp.oP {
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
         this.E = bcp.this.pC.mv;
         long var2 = bcp.this.pC.E();
         bcp.this.pC.pC(var2, var1);
         this.sY = bcp.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bcp.this.pC.mv;
         long var2 = bcp.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bcp.this.pC.pC(var1);
            long var4 = bcp.this.pC.E();
            var3.pC("length", var4);
            bcp.this.pC.pC(var3);
         });
         this.sY = bcp.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bcp.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bcp.this.pC.E();
            long var1 = bcp.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bcp.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class co extends bcp.bO {
      int pC;

      public co(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bcp.this.pC.WR(this.pC);
            var2.pC("data", bcp.this.pC.wS(var4));
         }
      }
   }

   class cq extends bcp.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bcp.this.pC.mv;
         bcp.this.pC.Bf = this.E;
         long var1 = bcp.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bcp.this.pC.mv;
         this.pC = bcp.this.pC.mv;
         var1 = bcp.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bcp.this.pC.mv;
      }

      private void wS() {
         int var1 = bcp.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bcp.this.pC.pC(bco.ah.pC());
         bcp.this.pC.pC(var3);
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
         bbc var3 = bcp.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bcp.this.pC.vP) {
            var3.pC("objectPool", bcp.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bcp.this.pC.kS(), bco.ys.toString(), bco.E.toString(), bco.RW.toString());
         var3.pC("exceptionHandlers", bcp.this.pC.kS(), bco.FE.toString());
         var3.pC("pcDescriptors", bcp.this.pC.kS(), bco.z.toString());
         var3.pC("catchEntry", bcp.this.pC.kS());
         if (bcp.this.pC.NS) {
            var3.pC("compressedStackMaps", bcp.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bcp.this.pC.kS(), bco.Xh.toString());
         var3.pC("codeSourceMap", bcp.this.pC.kS(), bco.Ek.toString());
         if (!bcp.this.pC.ah && bcp.this.pC.NS) {
            var3.pC("deoptInfoArray", bcp.this.pC.kS());
            var3.pC("staticCallsTargetTable", bcp.this.pC.kS());
         }

         if (!bcp.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bcp.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bcp.this.pC.rl ? bcp.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bcp.this.pC.ah) {
            long var3 = bcp.this.pC.pF[2 * bcp.this.pC.OI];
            long var5 = bcp.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bcp.this.pC.ys.UO : 0L;
            long var12 = var9 ? bcp.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bcp.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class dE extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.sO.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bcp.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bcp.this.pC.kS());
         var1.pC("argsDescriptor", bcp.this.pC.kS());
      }
   }

   class eW extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("cache", bcp.this.pC.kS());
         }
      }
   }

   class gJ extends bcp.bO {
      @Override
      public void pC() {
         this.A(bco.z.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcp.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bcp.bO {
      gb(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bco.xM.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("flags", bcp.this.pC.gp());
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeTestStub", bcp.this.pC.kS());
         var1.pC("shape", bcp.this.pC.kS());
         var1.pC("fieldTypes", bcp.this.pC.kS());
         var1.pC("hash", bcp.this.pC.kS());
      }
   }

   class io extends bcp.bO {
      io(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.A(bco.PZ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            long var3 = var2.A();
            var2.pC("length");
            var2.pC("shape", bcp.this.pC.E());
            var2.pC("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.pC("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bcp.this.pC.kS();
            }
         }
      }
   }

   class jM extends bcp.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bco.Pe.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bcp.this.pC.ld());
            var2.pC("nullabity", bcp.this.pC.E());
            var2.pC("instantiations", bcp.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bcp.this.pC.kS());
            }
         }
      }
   }

   class m extends bcp.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bcp.this.pC.mv;
         long var1 = bcp.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bcp.this.pC.E();
            bco var6 = (var4 & 1L) != 0L ? bco.EM : bco.dM;
            long var7 = var4 >> 1;
            bbc var9 = bcp.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bcp.this.pC.pC(var9);
         }

         this.sY = bcp.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            long var3 = bcp.this.pC.E();
            long var5 = var3 >> 1;
            bco var7 = (var3 & 1L) != 0L ? bco.EM : bco.dM;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bco.dM) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bcp.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bcp.this.pC.gp();
                  var10 |= bcp.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class ma extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            if (!bcp.this.pC.ah && !bcp.this.pC.vP) {
               var2.pC("libraryKernelOffset", bcp.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bcp.this.pC.kS());
         var1.pC("originClass", bcp.this.pC.kS());
         var1.pC("script", bcp.this.pC.kS());
         if (!bcp.this.pC.vP) {
            var1.pC("libraryKernelData", bcp.this.pC.kS());
         }
      }
   }

   class nA extends bcp.bO {
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
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcp.this.pC.kS());
         var1.pC("hashMask", bcp.this.pC.kS());
         var1.pC("data", bcp.this.pC.kS());
         var1.pC("usedData", bcp.this.pC.kS());
         var1.pC("deletedKeys", bcp.this.pC.kS());
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bcp.this.pC.vP());
            var2.pC("numImports", bcp.this.pC.fI());
            var2.pC("loadState", bcp.this.pC.oT());
            var2.pC("flags", bcp.this.pC.gp());
            if (!bcp.this.pC.ah && !bcp.this.pC.vP) {
               var2.pC("kernelOffset", bcp.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcp.this.pC.kS());
         var1.pC("url", bcp.this.pC.kS());
         var1.pC("privateKey", bcp.this.pC.kS());
         var1.pC("dictionary", bcp.this.pC.kS());
         var1.pC("metadata", bcp.this.pC.kS());
         var1.pC("toplevelClass", bcp.this.pC.kS());
         var1.pC("usedScripts", bcp.this.pC.kS());
         var1.pC("loadingUnit", bcp.this.pC.kS());
         var1.pC("imports", bcp.this.pC.kS());
         var1.pC("exports", bcp.this.pC.kS());
         if (!bcp.this.pC.vP) {
            var1.pC("dependencies", bcp.this.pC.kS());
            var1.pC("kernelData", bcp.this.pC.kS());
         }
      }
   }

   class qt extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.QQ.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bcp.this.pC.kS());
         var1.pC("length", bcp.this.pC.kS());
         var1.pC("data", bcp.this.pC.kS());
      }
   }

   class rQ extends bcp.bO {
      @Override
      public void pC() {
         this.A(bco.hK.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            long var3 = bcp.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bcp.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            if (!bcp.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bcp.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bcp.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bcp.this.pC.kS(var5);
               if (var6.pC().equals(bco.ah.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bcp.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bcp.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcp.this.pC.kS());
         var1.pC("owner", bcp.this.pC.kS());
         var1.pC("signature", bcp.this.pC.kS(), bco.e.toString());
         var1.pC("data", bcp.this.pC.kS());
      }
   }

   class uX extends bcp.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bcp.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bcp.this.pC.pC(bco.VD.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bcp.this.pC.ys());
            bcp.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bcp.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bcp.this.pC.mv;
         long var1 = bcp.this.pC.E();
         this.A = bcp.this.pC.vP();
         this.kS = bcp.this.pC.vP();
         bcp.this.pC.pC(var1, this.pC);
         this.sY = bcp.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bcp.this.pC.ys.ys;
         int var2 = (int)bcp.this.pC.pC(this.kS * bcp.this.pC.ys.sY, bcp.this.pC.ys.ld);
         Long var3 = bcp.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bcp.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bcp.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bcp.this.pC.ys.sY)) {
               if (bcp.this.pC.A(var3, var6 / (int)bcp.this.pC.ys.sY)) {
                  bcp.this.pC.Sc();
               } else {
                  bcp.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bcp.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bcp.bO {
      @Override
      public void pC() {
         this.pC(bco.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bcp.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bcp.this.pC.kS());
         var1.pC("cSignature", bcp.this.pC.kS());
         var1.pC("callbackTarget", bcp.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bcp.this.pC.kS());
      }
   }

   class zp extends bcp.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bco.Xs.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcp.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bcp.this.pC.ys());
         }
      }
   }
}
