package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bcl {
   private static final ILogger A = GlobalLog.getLogger(bcl.class);
   bcn pC;

   bcl(bcn var1) {
      Assert.a(var1.vP, "Limited to AOT snapshots");
      this.pC = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bcl.oP pC(int var1, boolean var2, boolean var3) {
      bck var4 = bck.pC(var1);
      if (var1 >= bck.pY.pC() || var1 == bck.Bc.pC()) {
         return new bcl.vi(var1, var2);
      } else if (this.pC.ld(var1)) {
         Assert.a(!var2);
         return new bcl.Sf(var1);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var4) {
               case rl:
               case z:
               case Ek:
                  return new bcl.Pj(var2, var3, var1);
               case eE:
               case dM:
               case QQ:
                  if (var3) {
                     return new bcl.Pj(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case rl:
               Assert.a(!var2);
               return new bcl.gJ();
            case z:
               Assert.a(!var2);
               return new bcl.DH();
            case Ek:
               Assert.a(!var2);
               return new bcl.rQ();
            case eE:
            case dM:
            default:
               return null;
            case QQ:
               return new bcl.Kr(var2, var3 && this.pC.wS != null);
            case E:
               Assert.a(!var2);
               return new bcl.Sv();
            case ld:
               return new bcl.Sb();
            case Bf:
               return new bcl.m(var2, var3);
            case sY:
               Assert.a(!var2);
               return new bcl.ma();
            case ys:
               Assert.a(!var2);
               return new bcl.sy();
            case gp:
               Assert.a(!var2);
               return new bcl.K();
            case oT:
               Assert.a(!var2);
               return new bcl.yt();
            case fI:
               Assert.a(!var2);
               return new bcl.RC();
            case WR:
               Assert.a(!var2);
               return new bcl.io();
            case NS:
               Assert.a(!var2);
               return new bcl.p();
            case vP:
               Assert.a(!var2);
               throw new ToDoException();
            case Sc:
               Assert.a(!var2);
               return new bcl.cq();
            case Ab:
               Assert.a(!var2);
               return new bcl.Tb();
            case Er:
               Assert.a(!var2);
               return new bcl.KD();
            case FE:
               Assert.a(!var2);
               return null;
            case Aj:
               Assert.a(!var2);
               return null;
            case mv:
               Assert.a(!var2);
               return new bcl.B();
            case Cu:
               Assert.a(!var2);
               return null;
            case hZ:
               Assert.a(!var2);
               return null;
            case UW:
               Assert.a(!var2);
               return new bcl.nA();
            case PR:
               Assert.a(!var2);
               return new bcl.GK();
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
               return new bcl.eW(var2, var3);
            case RW:
               return new bcl.HE(var2, var3);
            case xM:
               Assert.a(!var2);
               return new bcl.Hv();
            case kU:
               return new bcl.jM(var2, var3);
            case pg:
               return new bcl.Ws(var2);
            case UH:
               return new bcl.uX(var2);
            case VD:
               return new bcl.zp(var2);
            case sz:
               Assert.a(!var2);
               return new bcl.qt();
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
               return new bcl.Mh(var2, var1);
            case LL:
               throw new RuntimeException();
            case rC:
               return new bcl.gb(var2, var1);
            case be:
            case Xh:
               return new bcl.Av(var2, var1);
         }
      }
   }

   class Av extends bcl.bO {
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
            bbc var2 = bcl.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bcl.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bcl.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class B extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.mv.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bcl.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bcl.this.pC.kS());
         var1.pC("argsDescriptor", bcl.this.pC.kS());
      }
   }

   class DH extends bcl.bO {
      @Override
      public void pC() {
         this.A(bck.z.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcl.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class GK extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.PR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("parent", bcl.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bcl.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class HE extends bcl.bO {
      HE(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bck.RW.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bcl.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcl.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcl.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bcl.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bcl.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcl.this.pC.kS());
         var1.pC("typeParameters", bcl.this.pC.kS());
         var1.pC("resultType", bcl.this.pC.kS());
         var1.pC("parameterTypes", bcl.this.pC.kS());
         var1.pC("namedParameterNames", bcl.this.pC.kS());
         var1.pC("hash", bcl.this.pC.kS());
      }
   }

   class Hv extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.xM.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcl.this.pC.kS());
         var1.pC("type", bcl.this.pC.kS());
      }
   }

   class K extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.gp.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            if (bcl.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bcl.this.pC.kS());
            }

            var2.pC("parentFunction", bcl.this.pC.kS());
            var2.pC("closure", bcl.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bcl.this.pC.E());
         }
      }
   }

   class KD extends bcl.bO {
      @Override
      public void pC() {
         this.A(bck.Er.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            long var3 = bcl.this.pC.E();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            var2.pC("numEntries", var5);
            var2.pC("handledTypesData", bcl.this.pC.kS());
            ArrayList var7 = new ArrayList();
            var2.pC("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bcl.this.pC.sY());
               var9.put("outerTryIndex", bcl.this.pC.WR());
               var9.put("needsStacktrace", bcl.this.pC.A());
               var9.put("hasCatchAll", bcl.this.pC.A());
               var9.put("isGenerated", bcl.this.pC.A());
               var7.add(var9);
            }
         }
      }
   }

   class Kr extends bcl.bO {
      Kr(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.E = bcl.this.pC.mv;
         long var1 = bcl.this.pC.E();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bcl.this.pC.E();
            bck var6 = (var4 & 1L) != 0L ? bck.dM : bck.eE;
            long var7 = var4 >> 1;
            bbc var9 = bcl.this.pC.pC(var6.pC());
            var9.pC("length", var7);
            bcl.this.pC.pC(var9);
         }

         this.sY = bcl.this.pC.mv;
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            long var3 = bcl.this.pC.E();
            long var5 = var3 >> 1;
            bck var7 = (var3 & 1L) != 0L ? bck.dM : bck.eE;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.A() == (int)var5);
            Assert.a(var2.A == var7.pC());
            if (var7 == bck.eE) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bcl.this.pC.gp();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.pC("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bcl.this.pC.gp();
                  var10 |= bcl.this.pC.gp() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.pC("data", var12);
            }
         }
      }
   }

   class Mh extends bcl.bO {
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
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcl.this.pC.kS());
         var1.pC("hashMask", bcl.this.pC.kS());
         var1.pC("data", bcl.this.pC.kS());
         var1.pC("usedData", bcl.this.pC.kS());
         var1.pC("deletedKeys", bcl.this.pC.kS());
      }
   }

   class Pj extends bcl.bO {
      int pC;

      Pj(boolean var2, boolean var3, int var4) {
         this.wS = var2;
         this.UT = var3;
         this.pC = var4;
      }

      @Override
      public void pC() {
         this.E = bcl.this.pC.mv;
         long var1 = bcl.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bcl.this.pC.E() << (int)bcl.this.pC.ys.gp));
            Object var6 = this.kS(var3);
            bbc var7 = bcl.this.pC.pC(this.pC);
            var7.pC("data", var6);
            bcl.this.pC.pC(var7);
         }

         this.sY = bcl.this.pC.mv;
         if (this.pC == bck.QQ.pC()) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object kS(int var1) {
         if (this.pC != bck.eE.pC() && this.pC != bck.QQ.pC()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bcl.this.pC.E.position(var1);
            bcl.this.pC.E.i32();
            long var2;
            if (bcl.this.pC.FE) {
               bcl.this.pC.E.i32();
               var2 = bcl.this.pC.E.i64();
            } else {
               bcl.this.pC.E.i32();
               var2 = bcl.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bcl.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class RC extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.fI.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("kindBits", bcl.this.pC.sY());
            var2.pC("hostOffsetOrFieldId", bcl.this.pC.kS());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcl.this.pC.kS());
         var1.pC("owner", bcl.this.pC.kS());
         var1.pC("type", bcl.this.pC.kS());
         var1.pC("initializerFunction", bcl.this.pC.kS());
      }
   }

   class Sb extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.kU.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bcl.this.pC.kS());
         var1.pC("flags", bcl.this.pC.kS());
         var1.pC("bounds", bcl.this.pC.kS());
         var1.pC("defaults", bcl.this.pC.kS());
      }
   }

   class Sf extends bcl.bO {
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
            bbc var2 = bcl.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bcl.this.pC.WR(this.pC);
            var2.pC("data", bcl.this.pC.wS(var4));
         }
      }
   }

   class Sv extends bcl.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bcl.this.pC.mv;
         long var1 = bcl.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bcl.this.pC.pC(bck.E.pC());
            var2.pC("id", bcl.this.pC.wS());
            bcl.this.pC.pC(var2);
         });
         this.A = bcl.this.pC.mv;
         this.pC(bck.E.pC());
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.E, this.sY}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bcl.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bcl.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bcl.this.pC.ah && !bcl.this.pC.vP) {
                  var8.pC("kernelOffset", bcl.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bcl.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bcl.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bcl.this.pC.ys());
               if (!bcl.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bcl.this.pC.ys());
               var8.pC("numNativeFields", bcl.this.pC.E());
               if (!bcl.this.pC.ah) {
                  Assert.a(!bcl.this.pC.vP);
                  var8.pC("tokenPos", bcl.this.pC.UT());
                  var8.pC("endTokenPos", bcl.this.pC.UT());
               }

               var8.pC("stateBits", bcl.this.pC.E());
               if (bcl.this.pC.ah) {
                  if (var7) {
                     bcl.this.pC.E();
                  } else if (!bcl.this.pC.ys.pC(var9)) {
                     bcl.this.pC.os.put(var9, bcl.this.pC.E());
                  }
               }

               bcl.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcl.this.pC.kS());
         if (!bcl.this.pC.Sc) {
            var1.pC("userName", bcl.this.pC.kS());
         }

         var1.pC("functions", bcl.this.pC.kS());
         var1.pC("functionsHashTable", bcl.this.pC.kS());
         var1.pC("fields", bcl.this.pC.kS());
         var1.pC("offsetInWordsToField", bcl.this.pC.kS());
         var1.pC("interfaces", bcl.this.pC.kS());
         var1.pC("script", bcl.this.pC.kS());
         var1.pC("library", bcl.this.pC.kS());
         var1.pC("typeParameters", bcl.this.pC.kS());
         var1.pC("superType", bcl.this.pC.kS());
         var1.pC("constants", bcl.this.pC.kS());
         var1.pC("declarationType", bcl.this.pC.kS());
         var1.pC("invocationDispatcherCache", bcl.this.pC.kS());
         if (!bcl.this.pC.Sc || !bcl.this.pC.ah) {
            var1.pC("directImplementors", bcl.this.pC.kS());
            var1.pC("directSubclasses", bcl.this.pC.kS());
         }

         if (!bcl.this.pC.ah) {
            var1.pC("allocationStub", bcl.this.pC.kS());
            var1.pC("dependentCode", bcl.this.pC.kS());
         }
      }
   }

   class Tb extends bcl.bO {
      @Override
      public void pC() {
         this.A(bck.Ab.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bcl.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bcl.this.pC.E(var6);
               if (var8 == bcl.this.pC.ys.Ab) {
                  var7.put("rawObj", bcl.this.pC.kS());
               } else if (var8 == bcl.this.pC.ys.rl) {
                  var7.put("rawValue", bcl.this.pC.ys());
               } else if (var8 == bcl.this.pC.ys.z) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bcl.this.pC.ys.Er && var8 != bcl.this.pC.ys.FE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bcl.this.pC.ah);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Ws extends bcl.bO {
      Ws(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bck.pg.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bcl.this.pC.kS());
         var1.pC("functionTypeArguments", bcl.this.pC.kS());
         var1.pC("delayedTypeArguments", bcl.this.pC.kS());
         var1.pC("function", bcl.this.pC.kS());
         var1.pC("context", bcl.this.pC.kS());
         var1.pC("hash", bcl.this.pC.kS());
      }
   }

   abstract class bO implements bcl.oP {
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
         this.E = bcl.this.pC.mv;
         long var2 = bcl.this.pC.E();
         bcl.this.pC.pC(var2, var1);
         this.sY = bcl.this.pC.mv;
      }

      protected final void A(int var1) {
         this.E = bcl.this.pC.mv;
         long var2 = bcl.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bcl.this.pC.pC(var1);
            long var4 = bcl.this.pC.E();
            var3.pC("length", var4);
            bcl.this.pC.pC(var3);
         });
         this.sY = bcl.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bcl.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.UT && this.wS) {
            bcl.this.pC.E();
            long var1 = bcl.this.pC.E();

            for (int var3 = this.E + (int)var1; var3 < this.sY; var3++) {
               bcl.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bcl.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.E = bcl.this.pC.mv;
         bcl.this.pC.Bf = this.E;
         long var1 = bcl.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.sY = bcl.this.pC.mv;
         this.pC = bcl.this.pC.mv;
         var1 = bcl.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bcl.this.pC.mv;
      }

      private void wS() {
         int var1 = bcl.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bbc var3 = bcl.this.pC.pC(bck.Sc.pC());
         bcl.this.pC.pC(var3);
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
         bbc var3 = bcl.this.pC.kS(var1);
         this.pC(var3, var2);
         if (!bcl.this.pC.vP) {
            var3.pC("objectPool", bcl.this.pC.kS());
         } else {
            var3.pC("objectPool", null);
         }

         var3.pC("owner", bcl.this.pC.kS(), bck.ys.toString(), bck.E.toString(), bck.ck.toString());
         var3.pC("exceptionHandlers", bcl.this.pC.kS(), bck.Er.toString());
         var3.pC("pcDescriptors", bcl.this.pC.kS(), bck.rl.toString());
         var3.pC("catchEntry", bcl.this.pC.kS());
         if (bcl.this.pC.NS) {
            var3.pC("compressedStackMaps", bcl.this.pC.kS());
         } else {
            var3.pC("compressedStackMaps", null);
         }

         var3.pC("inlinedIdToFunction", bcl.this.pC.kS(), bck.be.toString());
         var3.pC("codeSourceMap", bcl.this.pC.kS(), bck.z.toString());
         if (!bcl.this.pC.ah && bcl.this.pC.NS) {
            var3.pC("deoptInfoArray", bcl.this.pC.kS());
            var3.pC("staticCallsTargetTable", bcl.this.pC.kS());
         }

         if (!bcl.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bcl.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bcl.this.pC.rl ? bcl.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.kS("stateBits"), (Long)var3.pC("entryPoint", Long.class), var3.pC("owner", Long.class)};
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bcl.this.pC.ah) {
            long var3 = bcl.this.pC.pF[2 * bcl.this.pC.OI];
            long var5 = bcl.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bcl.this.pC.ys.UO : 0L;
            long var12 = var9 ? bcl.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bcl.this.pC.OI++;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class eW extends bcl.bO {
      eW(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bck.ck.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("flags", bcl.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcl.this.pC.kS());
         var1.pC("arguments", bcl.this.pC.kS());
         var1.pC("hash", bcl.this.pC.kS());
      }
   }

   class gJ extends bcl.bO {
      @Override
      public void pC() {
         this.A(bck.rl.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            int var3 = this.A(var2);
            byte[] var4 = bcl.this.pC.wS(var3);
            var2.pC("data", var4);
         }
      }
   }

   class gb extends bcl.bO {
      int pC;

      gb(boolean var2, int var3) {
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
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      private void pC(bbc var1) {
         var1.pC("typeArguments", bcl.this.pC.kS());
         var1.pC("hashMask", bcl.this.pC.kS());
         var1.pC("data", bcl.this.pC.kS());
         var1.pC("usedData", bcl.this.pC.kS());
         var1.pC("deletedKeys", bcl.this.pC.kS());
      }
   }

   class io extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.WR.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            if (!bcl.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bcl.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bcl.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bcl.this.pC.kS());
         if (bcl.this.pC.vP) {
            if (!bcl.this.pC.Sc) {
               var1.pC("resolvedUrl", bcl.this.pC.kS());
            }
         } else {
            var1.pC("resolvedUrl", bcl.this.pC.kS());
            var1.pC("resolvedUrl", bcl.this.pC.kS());
            var1.pC("lineStarts", bcl.this.pC.kS());
            var1.pC("constantCoverage", bcl.this.pC.kS());
            var1.pC("debugPositions", bcl.this.pC.kS());
            var1.pC("kernelProgramInfo", bcl.this.pC.kS());
         }
      }
   }

   class jM extends bcl.bO {
      jM(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.pC(bck.kU.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bcl.this.pC.vP());
            var2.pC("base", bcl.this.pC.gp());
            var2.pC("index", bcl.this.pC.gp());
            long var3 = bcl.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bcl.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bcl.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bcl.this.pC.kS());
         var1.pC("hash", bcl.this.pC.kS());
         var1.pC("bound", bcl.this.pC.kS());
      }
   }

   class m extends bcl.bO {
      m(boolean var2, boolean var3) {
         this.wS = var2;
         this.UT = var3;
      }

      @Override
      public void pC() {
         this.A(bck.Bf.pC());
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bcl.this.pC.ld());
            var2.pC("nullabity", bcl.this.pC.E());
            var2.pC("instantiations", bcl.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bcl.this.pC.kS());
            }
         }
      }
   }

   class ma extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.sY.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            if (!bcl.this.pC.ah && !bcl.this.pC.vP) {
               var2.pC("libraryKernelOffset", bcl.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bcl.this.pC.kS());
         var1.pC("originClass", bcl.this.pC.kS());
         var1.pC("script", bcl.this.pC.kS());
         if (!bcl.this.pC.vP) {
            var1.pC("libraryKernelData", bcl.this.pC.kS());
         }
      }
   }

   class nA extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.UW.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("cache", bcl.this.pC.kS());
         }
      }
   }

   interface oP {
      void pC();

      void A();
   }

   class p extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.NS.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bcl.this.pC.vP());
            var2.pC("numImports", bcl.this.pC.fI());
            var2.pC("loadState", bcl.this.pC.oT());
            var2.pC("flags", bcl.this.pC.gp());
            if (!bcl.this.pC.ah && !bcl.this.pC.vP) {
               var2.pC("kernelOffset", bcl.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcl.this.pC.kS());
         var1.pC("url", bcl.this.pC.kS());
         var1.pC("privateKey", bcl.this.pC.kS());
         var1.pC("dictionary", bcl.this.pC.kS());
         var1.pC("metadata", bcl.this.pC.kS());
         var1.pC("toplevelClass", bcl.this.pC.kS());
         var1.pC("usedScripts", bcl.this.pC.kS());
         var1.pC("loadingUnit", bcl.this.pC.kS());
         var1.pC("imports", bcl.this.pC.kS());
         var1.pC("exports", bcl.this.pC.kS());
         if (!bcl.this.pC.vP) {
            var1.pC("dependencies", bcl.this.pC.kS());
            var1.pC("kernelData", bcl.this.pC.kS());
         }
      }
   }

   class qt extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.sz.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bcl.this.pC.kS());
         var1.pC("length", bcl.this.pC.kS());
         var1.pC("data", bcl.this.pC.kS());
      }
   }

   class rQ extends bcl.bO {
      @Override
      public void pC() {
         this.A(bck.Ek.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            long var3 = bcl.this.pC.E();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.A());
            byte[] var6 = bcl.this.pC.wS(var5);
            var2.pC("data", var6);
         }
      }
   }

   class sy extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.ys.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            if (!bcl.this.pC.vP) {
               throw new ToDoException();
            }

            long var3 = bcl.this.pC.E();
            var2.pC("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.wS("name").kS("data"), var3};
            if (var3 == 0L) {
               var2.pC("code", null);
            } else {
               int var5 = bcl.this.pC.Bf + (int)var3 - 1;
               bbc var6 = bcl.this.pC.kS(var5);
               if (var6.pC().equals(bck.Sc.toString())) {
                  var2.pC("code", var5);
               }
            }

            if (!bcl.this.pC.ah) {
               throw new ToDoException();
            }

            var2.pC("kindTag", bcl.this.pC.E());
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bcl.this.pC.kS());
         var1.pC("owner", bcl.this.pC.kS());
         var1.pC("signature", bcl.this.pC.kS(), bck.RW.toString());
         var1.pC("data", bcl.this.pC.kS());
      }
   }

   class uX extends bcl.bO {
      uX(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         long var1 = bcl.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bcl.this.pC.pC(bck.UH.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bcl.this.pC.ys());
            bcl.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class vi extends bcl.bO {
      int pC;
      int A;
      int kS;

      vi(int var2, boolean var3) {
         this.pC = var2;
         this.wS = var3;
      }

      @Override
      public void pC() {
         this.E = bcl.this.pC.mv;
         long var1 = bcl.this.pC.E();
         this.A = bcl.this.pC.vP();
         this.kS = bcl.this.pC.vP();
         bcl.this.pC.pC(var1, this.pC);
         this.sY = bcl.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bcl.this.pC.ys.ys;
         int var2 = (int)bcl.this.pC.pC(this.kS * bcl.this.pC.ys.sY, bcl.this.pC.ys.ld);
         Long var3 = bcl.this.pC.E();

         for (int var4 = this.E; var4 < this.sY; var4++) {
            bbc var5 = bcl.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);

            int var6;
            for (var6 = bcl.this.pC.FE ? 8 : 4; var6 < var1; var6 = (int)(var6 + bcl.this.pC.ys.sY)) {
               if (bcl.this.pC.A(var3, var6 / (int)bcl.this.pC.ys.sY)) {
                  bcl.this.pC.Sc();
               } else {
                  bcl.this.pC.kS();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bcl.this.pC.ys.sY);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class yt extends bcl.bO {
      @Override
      public void pC() {
         this.pC(bck.oT.pC());
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bcl.this.pC.vP ? bcl.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bcl.this.pC.kS());
         var1.pC("cSignature", bcl.this.pC.kS());
         var1.pC("callbackTarget", bcl.this.pC.kS());
         var1.pC("callbackExceptionalReturn", bcl.this.pC.kS());
      }
   }

   class zp extends bcl.bO {
      zp(boolean var2) {
         this.wS = var2;
      }

      @Override
      public void pC() {
         this.pC(bck.VD.pC());
      }

      @Override
      public void A() {
         for (int var1 = this.E; var1 < this.sY; var1++) {
            bbc var2 = bcl.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bcl.this.pC.ys());
         }
      }
   }
}
