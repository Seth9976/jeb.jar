package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bbr {
   private static final ILogger A = GlobalLog.getLogger(bbr.class);
   bbt pC;

   bbr(bbt var1) {
      Assert.a(var1.vP);
      this.pC = var1;
   }

   bbr.HE pC(int var1, boolean var2) {
      bbr.HE var3 = this.pC(var1);
      ((bbr.bO)var3).wS = var2;
      return var3;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bbr.HE pC(int var1) {
      bbq var2 = bbq.pC(var1);
      if (var1 >= bbq.mV.pC() || var1 == bbq.pF.pC()) {
         return new bbr.qt(var1);
      } else if (this.pC.ld(var1)) {
         return new bbr.nA(var2);
      } else {
         if (!this.pC.UO && this.pC.Ab) {
            switch (var2) {
               case Ab:
               case rl:
               case z:
                  return new bbr.uX(var2);
               case AU:
               case jS:
                  return new bbr.uX(var2);
               case IE:
                  return new bbr.uX(var2);
            }
         }

         switch (var2) {
            case UT:
               return new bbr.Sv();
            case E:
               return new bbr.Mh();
            case sY:
               return new bbr.yt();
            case ld:
               return new bbr.K();
            case gp:
               return new bbr.zp();
            case oT:
               return new bbr.KD();
            case fI:
               return new bbr.Tb();
            case WR:
               return new bbr.oP();
            case ED:
               return new bbr.cq();
            case UO:
               return new bbr.GK();
            case JF:
               return new bbr.p();
            case Gm:
            case Br:
               return new bbr.Av(var2);
            case hK:
               return new bbr.rQ();
            case LM:
               return new bbr.m();
            case hZ:
               return new bbr.ma();
            case UW:
               return new bbr.vi();
            case OI:
               return new bbr.gJ();
            case Pe:
               return new bbr.Pj();
            case ck:
               return new bbr.RC();
            case RW:
               return new bbr.Kr();
            case e:
               return new bbr.io();
            case ys:
               return new bbr.gb();
            case xM:
               return new bbr.Ws();
            case Nq:
               return new bbr.DH();
            case gj:
               return new bbr.sy();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Av extends bbr.bO {
      bbq pC;

      Av(bbq var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bbr.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bbr.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class DH extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.Nq);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bbr.this.pC.ys());
         }
      }
   }

   class GK extends bbr.bO {
      @Override
      public void pC() {
         this.A(bbq.UO);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bbr.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bbr.this.pC.E(var6);
               if (var8 == bbr.this.pC.ys.Ab) {
                  var7.put("rawObj", bbr.this.pC.kS());
               } else if (var8 == bbr.this.pC.ys.rl) {
                  var7.put("rawValue", bbr.this.pC.ys());
               } else {
                  if (var8 != bbr.this.pC.ys.z) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   interface HE {
      void pC();

      void A();
   }

   class K extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.ld);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            if (bbr.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bbr.this.pC.kS());
            }

            var2.pC("parentFunction", bbr.this.pC.kS());
            var2.pC("closure", bbr.this.pC.kS());
            var2.pC("defaultTypeArgumentsKind", bbr.this.pC.E());
         }
      }
   }

   class KD extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.oT);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            if (!bbr.this.pC.vP) {
               var2.pC("guardedListLength", bbr.this.pC.kS());
            }

            if (bbr.this.pC.NS) {
               var2.pC("dependentCode", bbr.this.pC.kS());
            }

            if (!bbr.this.pC.vP) {
               var2.pC("tokenPos", bbr.this.pC.UT());
               var2.pC("endTokenPos", bbr.this.pC.UT());
               var2.pC("guardedCid", bbr.this.pC.wS());
               var2.pC("isNullable", bbr.this.pC.wS());
               var2.pC("staticTypeExactnessState", bbr.this.pC.oT());
               if (!bbr.this.pC.ah) {
                  var2.pC("kernelOffset", bbr.this.pC.sY());
               }
            }

            int var3 = bbr.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bbr.this.pC.kS();
            if (bbr.this.pC.UT(var3)) {
               long var6 = bbr.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bbr.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbr.this.pC.E());
         var1.pC("owner", bbr.this.pC.E());
         var1.pC("type", bbr.this.pC.E());
         var1.pC("initializerFunction", bbr.this.pC.E());
      }
   }

   class Kr extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.RW);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbr.this.pC.E());
         var1.pC("type", bbr.this.pC.E());
      }
   }

   class Mh extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.E);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            if (!bbr.this.pC.ah && !bbr.this.pC.vP) {
               var2.pC("libraryKernelOffset", bbr.this.pC.vP());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bbr.this.pC.E());
         var1.pC("originClass", bbr.this.pC.E());
         var1.pC("script", bbr.this.pC.E());
         if (!bbr.this.pC.vP) {
            var1.pC("libraryKernelData", bbr.this.pC.E());
         }
      }
   }

   class Pj extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.Pe);
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbr.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbr.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbr.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbr.this.pC.E());
         var1.pC("typeClassId", bbr.this.pC.E());
         var1.pC("arguments", bbr.this.pC.E());
         var1.pC("hash", bbr.this.pC.E());
      }
   }

   class RC extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.ck);
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbr.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbr.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbr.this.pC.ys.UW);
            var2.pC("packedParameterCounts", bbr.this.pC.NS());
            var2.pC("packedTypeParameterCounts", bbr.this.pC.fI());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbr.this.pC.E());
         var1.pC("typeParameters", bbr.this.pC.E());
         var1.pC("resultType", bbr.this.pC.E());
         var1.pC("parameterTypes", bbr.this.pC.E());
         var1.pC("namedParameterNames", bbr.this.pC.E());
         var1.pC("hash", bbr.this.pC.E());
      }
   }

   class Sv extends bbr.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbr.this.pC.mv;
         long var1 = bbr.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bbr.this.pC.pC(bbq.UT.pC());
            var2.pC("id", bbr.this.pC.wS());
            bbr.this.pC.pC(var2);
         });
         this.A = bbr.this.pC.mv;
         this.pC(bbq.UT);
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.UT, this.E}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bbr.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bbr.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bbr.this.pC.ah && !bbr.this.pC.vP) {
                  var8.pC("kernelOffset", bbr.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bbr.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bbr.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bbr.this.pC.ys());
               if (!bbr.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bbr.this.pC.ys());
               var8.pC("numNativeFields", bbr.this.pC.E());
               if (!bbr.this.pC.ah) {
                  Assert.a(!bbr.this.pC.vP);
                  var8.pC("tokenPos", bbr.this.pC.UT());
                  var8.pC("endTokenPos", bbr.this.pC.UT());
               }

               var8.pC("stateBits", bbr.this.pC.E());
               if (bbr.this.pC.ah) {
                  if (var7) {
                     bbr.this.pC.E();
                  } else if (!bbr.this.pC.ys.pC(var9)) {
                     bbr.this.pC.os.put(var9, bbr.this.pC.E());
                  }
               }

               bbr.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbr.this.pC.E());
         if (!bbr.this.pC.Sc) {
            var1.pC("userName", bbr.this.pC.E());
         }

         var1.pC("functions", bbr.this.pC.E());
         var1.pC("functionsHashTable", bbr.this.pC.E());
         var1.pC("fields", bbr.this.pC.E());
         var1.pC("offsetInWordsToField", bbr.this.pC.E());
         var1.pC("interfaces", bbr.this.pC.E());
         var1.pC("script", bbr.this.pC.E());
         var1.pC("library", bbr.this.pC.E());
         var1.pC("typeParameters", bbr.this.pC.E());
         var1.pC("superType", bbr.this.pC.E());
         var1.pC("constants", bbr.this.pC.E());
         var1.pC("declarationType", bbr.this.pC.E());
         var1.pC("invocationDispatcherCache", bbr.this.pC.E());
         if (!bbr.this.pC.Sc || !bbr.this.pC.ah) {
            var1.pC("directImplementors", bbr.this.pC.E());
            var1.pC("directSubclasses", bbr.this.pC.E());
         }

         if (!bbr.this.pC.ah) {
            var1.pC("allocationStub", bbr.this.pC.E());
            var1.pC("dependentCode", bbr.this.pC.E());
         }
      }
   }

   class Tb extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.fI);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            if (!bbr.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bbr.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bbr.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bbr.this.pC.E());
         if (bbr.this.pC.vP) {
            if (!bbr.this.pC.Sc) {
               var1.pC("resolvedUrl", bbr.this.pC.E());
            }
         } else {
            var1.pC("resolvedUrl", bbr.this.pC.E());
            var1.pC("resolvedUrl", bbr.this.pC.E());
            var1.pC("lineStarts", bbr.this.pC.E());
            var1.pC("constantCoverage", bbr.this.pC.E());
            var1.pC("debugPositions", bbr.this.pC.E());
            var1.pC("kernelProgramInfo", bbr.this.pC.E());
         }
      }
   }

   class Ws extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.xM);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bbr.this.pC.E());
         var1.pC("functionTypeArguments", bbr.this.pC.E());
         var1.pC("delayedTypeArguments", bbr.this.pC.E());
         var1.pC("function", bbr.this.pC.E());
         var1.pC("context", bbr.this.pC.E());
         var1.pC("hash", bbr.this.pC.E());
      }
   }

   abstract class bO implements bbr.HE {
      boolean wS;
      int UT;
      int E;

      @Override
      public void pC() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void A() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void pC(bbq var1) {
         this.UT = bbr.this.pC.mv;
         long var2 = bbr.this.pC.E();
         bbr.this.pC.pC(var2, var1.pC());
         this.E = bbr.this.pC.mv;
      }

      protected final void A(bbq var1) {
         this.UT = bbr.this.pC.mv;
         long var2 = bbr.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bbr.this.pC.pC(var1.pC());
            long var4 = bbr.this.pC.E();
            var3.pC("length", var4);
            bbr.this.pC.pC(var3);
         });
         this.E = bbr.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bbr.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      protected final void kS() {
         if (this.wS) {
            bbr.this.pC.E();
            long var1 = bbr.this.pC.E();

            for (int var3 = this.UT + (int)var1; var3 < this.E; var3++) {
               bbr.this.pC.E();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bbr.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.UT = bbr.this.pC.mv;
         long var1 = bbr.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.wS();
         }

         this.E = bbr.this.pC.mv;
         this.pC = bbr.this.pC.mv;
         var1 = bbr.this.pC.E();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.wS();
         }

         this.A = bbr.this.pC.mv;
      }

      private void wS() {
         int var1 = bbr.this.pC.vP();
         boolean var2 = (var1 >> 3 & 1) == 1;
         if (var2) {
            bbc var3 = bbr.this.pC.pC(bbq.ED.pC());
            var3.pC("unknown", true);
            bbr.this.pC.pC(var3);
         } else {
            bbc var4 = bbr.this.pC.pC(bbq.ED.pC());
            bbr.this.pC.pC(var4);
            var4.pC("stateBits", var1);
         }
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            this.pC(var1, false);
         }

         for (int var2 = this.pC; var2 < this.A; var2++) {
            this.pC(var2, true);
         }
      }

      void pC(int var1, boolean var2) {
         bbc var3 = bbr.this.pC.kS(var1);
         if (bbr.this.pC.ah && Boolean.TRUE.equals(var3.pC("unknown", Boolean.class))) {
            this.pC(var3, var2, true);
         } else {
            this.pC(var3, var2, false);
            if (bbr.this.pC.vP && bbr.this.pC.eP) {
               var3.pC("objectPool", null);
            } else {
               var3.pC("objectPool", bbr.this.pC.kS());
            }

            var3.pC("owner", bbr.this.pC.kS());
            var3.pC("exceptionHandlers", bbr.this.pC.kS(), bbq.hK.toString());
            var3.pC("pcDescriptors", bbr.this.pC.kS());
            var3.pC("catchEntry", bbr.this.pC.kS());
            var3.pC("compressedStackMaps", bbr.this.pC.kS());
            var3.pC("inlinedIdToFunction", bbr.this.pC.kS());
            var3.pC("codeSourceMap", bbr.this.pC.kS());
            if (!bbr.this.pC.ah && bbr.this.pC.NS) {
               var3.pC("deoptInfoArray", bbr.this.pC.kS());
               var3.pC("staticCallsTargetTable", bbr.this.pC.kS());
            }

            if (!bbr.this.pC.Sc) {
               var3.pC("returnAddressMetadata", bbr.this.pC.kS());
               var3.pC("varDescriptors", null);
               var3.pC("comments", bbr.this.pC.rl ? bbr.this.pC.kS() : null);
               var3.pC("compileTimestamp", 0);
            }
         }
      }

      void pC(bbc var1, boolean var2, boolean var3) {
         if (var2) {
            Assert.a(!var3);
            throw new ToDoException();
         } else if (bbr.this.pC.ah && bbr.this.pC.eP) {
            bbr.this.pC.Bc = (int)(bbr.this.pC.Bc + bbr.this.pC.E());
            long var4 = bbr.this.pC.OI + bbr.this.pC.Bc;
            long var6 = bbr.this.pC.E();
            long var8 = var6 >> 1;
            boolean var10 = (var6 & 1L) == 1L;
            long var11 = var10 ? bbr.this.pC.ys.UO : 0L;
            long var13 = var10 ? bbr.this.pC.ys.eP : 0L;
            long var15 = var4 + var11;
            long var17 = var4 + var13;
            if (var3) {
               bbr.this.pC.kS();
            }

            if (!var3) {
               var1.pC("entryPoint", var15);
               var1.pC("uncheckedEntryPoint", var15 + var8);
               var1.pC("monomorphicEntryPoint", var17);
               var1.pC("monomorphicUncheckedEntryPoint", var17 + var8);
            }
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class gJ extends bbr.bO {
      @Override
      public void pC() {
         this.A(bbq.OI);
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bbr.this.pC.ld());
            var2.pC("nullabity", bbr.this.pC.E());
            var2.pC("instantiations", bbr.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bbr.this.pC.kS());
            }
         }
      }
   }

   class gb extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.e);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("names", bbr.this.pC.E());
         var1.pC("flags", bbr.this.pC.E());
         var1.pC("bounds", bbr.this.pC.E());
         var1.pC("defaults", bbr.this.pC.E());
      }
   }

   class io extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.e);
         this.kS();
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bbr.this.pC.vP());
            var2.pC("base", bbr.this.pC.gp());
            var2.pC("index", bbr.this.pC.gp());
            long var3 = bbr.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbr.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbr.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbr.this.pC.E());
         var1.pC("hash", bbr.this.pC.E());
         var1.pC("bound", bbr.this.pC.E());
      }
   }

   class m extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.LM);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bbr.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbr.this.pC.E());
         var1.pC("argsDescriptor", bbr.this.pC.E());
      }
   }

   class ma extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.hZ);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("cache", bbr.this.pC.kS());
         }
      }
   }

   class nA extends bbr.bO {
      bbq pC;

      public nA(bbq var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bbr.this.pC.WR(this.pC.pC());
            var2.pC("data", bbr.this.pC.wS(var4));
         }
      }
   }

   class oP extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.WR);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bbr.this.pC.vP());
            var2.pC("numImports", bbr.this.pC.fI());
            var2.pC("loadState", bbr.this.pC.oT());
            var2.pC("flags", bbr.this.pC.gp());
            if (!bbr.this.pC.ah && !bbr.this.pC.vP) {
               var2.pC("kernelOffset", bbr.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbr.this.pC.E());
         var1.pC("url", bbr.this.pC.E());
         var1.pC("privateKey", bbr.this.pC.E());
         var1.pC("dictionary", bbr.this.pC.E());
         var1.pC("metadata", bbr.this.pC.E());
         var1.pC("toplevelClass", bbr.this.pC.E());
         var1.pC("usedScripts", bbr.this.pC.E());
         var1.pC("loadingUnit", bbr.this.pC.E());
         var1.pC("imports", bbr.this.pC.E());
         var1.pC("exports", bbr.this.pC.E());
         if (!bbr.this.pC.vP) {
            var1.pC("dependencies", bbr.this.pC.E());
            var1.pC("kernelData", bbr.this.pC.E());
         }
      }
   }

   class p extends bbr.bO {
      @Override
      public void pC() {
         long var1 = bbr.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bbr.this.pC.pC(bbq.JF.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bbr.this.pC.ys());
            bbr.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class qt extends bbr.bO {
      int pC;
      int A;
      int kS;

      qt(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.UT = bbr.this.pC.mv;
         long var1 = bbr.this.pC.E();
         this.A = bbr.this.pC.vP();
         this.kS = bbr.this.pC.vP();
         bbr.this.pC.pC(var1, this.pC);
         this.E = bbr.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bbr.this.pC.ys.ys;
         int var2 = (int)bbr.this.pC.pC(this.kS * bbr.this.pC.ys.sY, bbr.this.pC.ys.ld);
         Long var3 = bbr.this.pC.E();

         for (int var4 = this.UT; var4 < this.E; var4++) {
            bbc var5 = bbr.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);
            ArrayList var6 = new ArrayList();
            var5.pC("data", var6);

            int var7;
            for (var7 = bbr.this.pC.FE ? 8 : 4; var7 < var1; var7 = (int)(var7 + bbr.this.pC.ys.sY)) {
               if (bbr.this.pC.A(var3, var7 / (int)bbr.this.pC.ys.sY)) {
                  bbr.this.pC.Sc();
               } else {
                  bbr.this.pC.kS();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bbr.this.pC.ys.sY);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class rQ extends bbr.bO {
      @Override
      public void pC() {
         this.A(bbq.hK);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bbr.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bbr.this.pC.sY());
               var6.put("outerTryIndex", bbr.this.pC.WR());
               var6.put("needsStacktrace", bbr.this.pC.A());
               var6.put("hasCatchAll", bbr.this.pC.A());
               var6.put("isGenerated", bbr.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class sy extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.gj);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bbr.this.pC.E());
         var1.pC("length", bbr.this.pC.E());
         var1.pC("data", bbr.this.pC.E());
      }
   }

   class uX extends bbr.bO {
      bbq pC;

      uX(bbq var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.UT = bbr.this.pC.mv;
         long var1 = bbr.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bbr.this.pC.E() << (int)bbr.this.pC.ys.gp));
            Object var6 = this.pC(var3);
            bbc var7 = bbr.this.pC.pC(this.pC.pC());
            var7.pC("data", var6);
            bbr.this.pC.pC(var7);
         }

         this.E = bbr.this.pC.mv;
         if (this.pC == bbq.IE) {
            this.kS();
         }
      }

      @Override
      public void A() {
      }

      protected Object pC(int var1) {
         if (this.pC != bbq.AU && this.pC != bbq.IE) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bbr.this.pC.E.position(var1);
            bbr.this.pC.E.i32();
            long var2;
            if (bbr.this.pC.FE) {
               bbr.this.pC.E.i32();
               var2 = bbr.this.pC.E.i64();
            } else {
               var2 = bbr.this.pC.E.i32();
               bbr.this.pC.E.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bbr.this.pC.E.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class vi extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.UW);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            var2.pC("parent", bbr.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bbr.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class yt extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.sY);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            if (bbr.this.pC.WR) {
               throw new ToDoException();
            }

            if (!bbr.this.pC.vP) {
               throw new ToDoException();
            }

            var2.pC("codeIndex", bbr.this.pC.E());
            var2.pC("code", var2.kS("codeIndex"));
            if (!bbr.this.pC.ah && !bbr.this.pC.vP) {
               var2.pC("positionalParameterNames", bbr.this.pC.kS());
               var2.pC("tokenPos", bbr.this.pC.UT());
               var2.pC("endTokenPos", bbr.this.pC.UT());
               var2.pC("kernelOffset", bbr.this.pC.E());
            }

            var2.pC("packedFields", bbr.this.pC.E());
            var2.pC("kindTag", bbr.this.pC.E());
            if (!bbr.this.pC.ah) {
               var2.pC("usageCounter", 0);
               var2.pC("optimizedInstructionCount", 0);
               var2.pC("optimizedCallSiteCount", 0);
               var2.pC("deoptimizationCounter", 0);
               var2.pC("stateBits", 0);
               var2.pC("inliningDepth", 0);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbr.this.pC.E());
         var1.pC("owner", bbr.this.pC.E());
         var1.pC("signature", bbr.this.pC.E());
         var1.pC("data", bbr.this.pC.E());
      }
   }

   class zp extends bbr.bO {
      @Override
      public void pC() {
         this.pC(bbq.gp);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbr.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bbr.this.pC.vP ? bbr.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bbr.this.pC.E());
         var1.pC("cSignature", bbr.this.pC.E());
         var1.pC("callbackTarget", bbr.this.pC.E());
         var1.pC("callbackExceptionalReturn", bbr.this.pC.E());
      }
   }
}
