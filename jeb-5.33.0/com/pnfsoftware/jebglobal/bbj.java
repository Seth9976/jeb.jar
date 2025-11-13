package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bbj {
   private static final ILogger A = GlobalLog.getLogger(bbj.class);
   bbl pC;

   bbj(bbl var1) {
      Assert.a(var1.vP);
      this.pC = var1;
   }

   bbj.HE pC(int var1, boolean var2) {
      bbj.HE var3 = this.pC(var1);
      ((bbj.bO)var3).wS = var2;
      return var3;
   }

   bbj.HE pC(int var1) {
      bbi var2 = bbi.pC(var1);
      if (var1 >= bbi.x.pC() || var1 == bbi.cX.pC()) {
         return new bbj.qt(var1);
      } else if (this.pC.ld(var1)) {
         return new bbj.m(var2);
      } else {
         if (this.pC.Ab) {
            switch (var2) {
               case ah:
               case eP:
               case UO:
               case Gm:
                  return new bbj.ma(var2);
               case tH:
                  return new bbj.uX();
            }
         }

         switch (var2) {
            case UT:
               return new bbj.Sv();
            case E:
               return new bbj.Tb();
            case sY:
               return new bbj.yt();
            case ys:
               return new bbj.K();
            case ld:
               return new bbj.zp();
            case gp:
               return new bbj.KD();
            case oT:
               return new bbj.gJ();
            case fI:
               return new bbj.oP();
            case vP:
               return new bbj.cq();
            case Sc:
               return new bbj.Mh();
            case xM:
               return new bbj.GK();
            case Um:
            case Ta:
               return new bbj.Av(var2);
            case rl:
               return new bbj.rQ();
            case Er:
               return new bbj.eW();
            case LM:
               return new bbj.p();
            case mv:
               return new bbj.Pj();
            case sO:
               return new bbj.vi();
            case ZN:
               return new bbj.io();
            case pF:
               return new bbj.gb();
            case Bc:
               return new bbj.RC();
            case OI:
               return new bbj.nA();
            case Bf:
               return new bbj.Kr();
            case Pe:
               return new bbj.Ws();
            case kU:
               return new bbj.DH();
            case go:
               return new bbj.sy();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Av extends bbj.bO {
      bbi pC;

      Av(bbi var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("typeArguments", bbj.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bbj.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class DH extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.kU);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            var2.pC("value", bbj.this.pC.ys());
         }
      }
   }

   class GK extends bbj.bO {
      @Override
      public void pC() {
         long var1 = bbj.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bbj.this.pC.pC(bbi.xM.pC());
            var5.pC("isCanonical", this.wS);
            var5.pC("value", bbj.this.pC.ys());
            bbj.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   interface HE {
      void pC();

      void A();
   }

   class K extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.ys);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            if (bbj.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bbj.this.pC.kS());
            }

            var2.pC("parentFunction", bbj.this.pC.kS());
            var2.pC("closure", bbj.this.pC.kS());
            var2.pC("defaultTypeArguments", bbj.this.pC.kS());
            var2.pC("defaultTypeArgumentsInfo", bbj.this.pC.kS());
         }
      }
   }

   class KD extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.gp);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            if (!bbj.this.pC.vP) {
               var2.pC("guardedListLength", bbj.this.pC.kS());
            }

            if (bbj.this.pC.NS) {
               var2.pC("dependentCode", bbj.this.pC.kS());
            }

            if (!bbj.this.pC.vP) {
               var2.pC("tokenPos", bbj.this.pC.UT());
               var2.pC("endTokenPos", bbj.this.pC.UT());
               var2.pC("guardedCid", bbj.this.pC.wS());
               var2.pC("isNullable", bbj.this.pC.wS());
               var2.pC("staticTypeExactnessState", bbj.this.pC.oT());
               if (!bbj.this.pC.ah) {
                  var2.pC("kernelOffset", bbj.this.pC.sY());
               }
            }

            int var3 = bbj.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bbj.this.pC.kS();
            if (bbj.this.pC.UT(var3)) {
               long var6 = bbj.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bbj.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbj.this.pC.E());
         var1.pC("owner", bbj.this.pC.E());
         var1.pC("type", bbj.this.pC.E());
         var1.pC("initializerFunction", bbj.this.pC.E());
      }
   }

   class Kr extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.Bf);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
            var2.pC("parameterizedClassId", bbj.this.pC.vP());
            var2.pC("base", bbj.this.pC.fI());
            var2.pC("index", bbj.this.pC.fI());
            long var3 = bbj.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbj.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbj.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbj.this.pC.E());
         var1.pC("name", bbj.this.pC.E());
         var1.pC("hash", bbj.this.pC.E());
         var1.pC("bound", bbj.this.pC.E());
         var1.pC("defaultArgument", bbj.this.pC.E());
      }
   }

   class Mh extends bbj.bO {
      @Override
      public void pC() {
         this.A(bbi.Sc);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bbj.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bbj.this.pC.E(var6);
               if (var8 == bbj.this.pC.ys.Ab) {
                  var7.put("rawObj", bbj.this.pC.kS());
               } else if (var8 == bbj.this.pC.ys.rl) {
                  var7.put("rawValue", bbj.this.pC.ys());
               } else {
                  if (var8 != bbj.this.pC.ys.z) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Pj extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.mv);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("cache", bbj.this.pC.kS());
         }
      }
   }

   class RC extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.Bc);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbj.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbj.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbj.this.pC.ys.UW);
            var2.pC("packedFields", bbj.this.pC.NS());
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbj.this.pC.E());
         var1.pC("typeParameters", bbj.this.pC.E());
         var1.pC("resultType", bbj.this.pC.E());
         var1.pC("parameterTypes", bbj.this.pC.E());
         var1.pC("parameterNames", bbj.this.pC.E());
         var1.pC("hash", bbj.this.pC.E());
      }
   }

   class Sv extends bbj.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbj.this.pC.mv;
         long var1 = bbj.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bbj.this.pC.pC(bbi.UT.pC());
            var2.pC("id", bbj.this.pC.wS());
            bbj.this.pC.pC(var2);
         });
         this.A = bbj.this.pC.mv;
         this.pC(bbi.UT);
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.UT, this.E}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bbj.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bbj.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bbj.this.pC.ah && !bbj.this.pC.vP) {
                  var8.pC("kernelOffset", bbj.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bbj.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bbj.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bbj.this.pC.ys());
               if (!bbj.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bbj.this.pC.ys());
               var8.pC("numNativeFields", bbj.this.pC.E());
               var8.pC("tokenPos", bbj.this.pC.UT());
               var8.pC("endTokenPos", bbj.this.pC.UT());
               var8.pC("stateBits", bbj.this.pC.E());
               if (bbj.this.pC.ah) {
                  if (var7) {
                     bbj.this.pC.E();
                  } else if (!bbj.this.pC.ys.pC(var9)) {
                     bbj.this.pC.os.put(var9, bbj.this.pC.E());
                  }
               }

               bbj.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbj.this.pC.E());
         var1.pC("userName", bbj.this.pC.E());
         var1.pC("functions", bbj.this.pC.E());
         var1.pC("functionsHashTable", bbj.this.pC.E());
         var1.pC("fields", bbj.this.pC.E());
         var1.pC("offsetInWordsToField", bbj.this.pC.E());
         var1.pC("interfaces", bbj.this.pC.E());
         var1.pC("script", bbj.this.pC.E());
         var1.pC("library", bbj.this.pC.E());
         var1.pC("typeParameters", bbj.this.pC.E());
         var1.pC("superType", bbj.this.pC.E());
         var1.pC("constants", bbj.this.pC.E());
         var1.pC("declarationType", bbj.this.pC.E());
         var1.pC("invocationDispatcherCache", bbj.this.pC.E());
         var1.pC("allocationStub", bbj.this.pC.E());
         if (!bbj.this.pC.vP) {
            var1.pC("directImplementors", bbj.this.pC.E());
            var1.pC("directSubclasses", bbj.this.pC.E());
            if (!bbj.this.pC.WR) {
               var1.pC("dependentCode", bbj.this.pC.E());
               if (!bbj.this.pC.NS) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class Tb extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.E);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            if (!bbj.this.pC.ah && !bbj.this.pC.vP) {
               var2.pC("libraryKernelOffset", bbj.this.pC.ld());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bbj.this.pC.E());
         var1.pC("originClass", bbj.this.pC.E());
         var1.pC("script", bbj.this.pC.E());
         if (!bbj.this.pC.vP) {
            var1.pC("libraryKernelData", bbj.this.pC.E());
         }
      }
   }

   class Ws extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.Pe);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bbj.this.pC.E());
         var1.pC("functionTypeArguments", bbj.this.pC.E());
         var1.pC("delayedTypeArguments", bbj.this.pC.E());
         var1.pC("function", bbj.this.pC.E());
         var1.pC("context", bbj.this.pC.E());
         var1.pC("hash", bbj.this.pC.E());
      }
   }

   abstract class bO implements bbj.HE {
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

      protected final void pC(bbi var1) {
         this.UT = bbj.this.pC.mv;
         long var2 = bbj.this.pC.E();
         bbj.this.pC.pC(var2, var1.pC());
         this.E = bbj.this.pC.mv;
      }

      protected final void A(bbi var1) {
         this.UT = bbj.this.pC.mv;
         long var2 = bbj.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bbj.this.pC.pC(var1.pC());
            long var4 = bbj.this.pC.E();
            var3.pC("length", var4);
            bbj.this.pC.pC(var3);
         });
         this.E = bbj.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bbj.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bbj.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC(bbi.vP);
         this.pC = bbj.this.pC.mv;
         long var1 = bbj.this.pC.E();
         bbj.this.pC.pC(var1, bbi.vP.pC());
         this.A = bbj.this.pC.mv;
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            this.pC(var1, false);
         }

         for (int var2 = this.pC; var2 < this.A; var2++) {
            this.pC(var2, true);
         }
      }

      void pC(int var1, boolean var2) {
         bbc var3 = bbj.this.pC.kS(var1);
         this.pC(var3, var2);
         if (bbj.this.pC.vP && bbj.this.pC.eP) {
            var3.pC("objectPool", null);
         } else {
            var3.pC("objectPool", bbj.this.pC.kS());
         }

         var3.pC("owner", bbj.this.pC.kS());
         var3.pC("exceptionHandlers", bbj.this.pC.kS(), bbi.rl.toString());
         var3.pC("pcDescriptors", bbj.this.pC.kS());
         var3.pC("catchEntry", bbj.this.pC.kS());
         var3.pC("compressedStackMaps", bbj.this.pC.kS());
         var3.pC("inlinedIdToFunction", bbj.this.pC.kS());
         var3.pC("codeSourceMap", bbj.this.pC.kS());
         if (!bbj.this.pC.ah && bbj.this.pC.NS) {
            var3.pC("deoptInfoArray", bbj.this.pC.kS());
            var3.pC("staticCallsTargetTable", bbj.this.pC.kS());
         }

         if (!bbj.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bbj.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bbj.this.pC.rl ? bbj.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         var3.pC("stateBits", bbj.this.pC.ys());
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bbj.this.pC.ah && bbj.this.pC.eP) {
            bbj.this.pC.OI = (int)(bbj.this.pC.OI + bbj.this.pC.E());
            long var3 = bbj.this.pC.Bf + bbj.this.pC.OI;
            long var5 = bbj.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bbj.this.pC.ys.UO : 0L;
            long var12 = var9 ? bbj.this.pC.ys.eP : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            var1.pC("entryPoint", var14);
            var1.pC("uncheckedEntryPoint", var14 + var7);
            var1.pC("monomorphicEntryPoint", var16);
            var1.pC("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class eW extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.Er);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bbj.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbj.this.pC.E());
         var1.pC("argsDescriptor", bbj.this.pC.E());
      }
   }

   class gJ extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.oT);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("lineOffset", bbj.this.pC.vP());
            var2.pC("colOffset", bbj.this.pC.vP());
            if (!bbj.this.pC.ah) {
               var2.pC("flagsAndMaxPosition", bbj.this.pC.vP());
            }

            var2.pC("kernelScriptIndex", bbj.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bbj.this.pC.E());
         if (!bbj.this.pC.vP) {
            var1.pC("resolvedUrl", bbj.this.pC.E());
            var1.pC("compileTimeConstants", bbj.this.pC.E());
            var1.pC("lineStarts", bbj.this.pC.E());
            var1.pC("debugPositions", bbj.this.pC.E());
            var1.pC("kernelProgramInfo", bbj.this.pC.E());
         }
      }
   }

   class gb extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.pF);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("isCanonical", this.wS);
            long var3 = bbj.this.pC.gp();
            var2.pC("typeState", var3 >> (int)bbj.this.pC.ys.hZ);
            var2.pC("nullability", var3 & bbj.this.pC.ys.UW);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbj.this.pC.E());
         var1.pC("typeClassId", bbj.this.pC.E());
         var1.pC("arguments", bbj.this.pC.E());
         var1.pC("hash", bbj.this.pC.E());
      }
   }

   class io extends bbj.bO {
      @Override
      public void pC() {
         this.A(bbi.ZN);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", this.wS);
            var2.pC("hash", bbj.this.pC.ld());
            var2.pC("nullabity", bbj.this.pC.E());
            var2.pC("instantiations", bbj.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bbj.this.pC.kS());
            }
         }
      }
   }

   class m extends bbj.bO {
      bbi pC;

      public m(bbi var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", this.wS);
            int var4 = var3 * bbj.this.pC.WR(this.pC.pC());
            var2.pC("data", bbj.this.pC.wS(var4));
         }
      }
   }

   class ma extends bbj.bO {
      bbi A;

      ma(bbi var2) {
         this.A = var2;
      }

      @Override
      public void pC() {
         long var1 = bbj.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bbj.this.pC.E() << (int)bbj.this.pC.ys.gp));
            Object var6 = this.A(var3);
            bbc var7 = bbj.this.pC.pC(this.A.pC());
            var7.pC("data", var6);
            bbj.this.pC.pC(var7);
         }
      }

      protected Object A(int var1) {
         return Strings.ff("ROData_object_at_0x%X", var1);
      }

      @Override
      public void A() {
      }
   }

   class nA extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.OI);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbj.this.pC.E());
         var1.pC("type", bbj.this.pC.E());
      }
   }

   class oP extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.fI);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bbj.this.pC.vP());
            var2.pC("numImports", bbj.this.pC.fI());
            var2.pC("loadState", bbj.this.pC.oT());
            var2.pC("flags", bbj.this.pC.gp());
            if (!bbj.this.pC.ah && !bbj.this.pC.vP) {
               var2.pC("kernelOffset", bbj.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbj.this.pC.E());
         var1.pC("url", bbj.this.pC.E());
         var1.pC("privateKey", bbj.this.pC.E());
         var1.pC("dictionary", bbj.this.pC.E());
         var1.pC("metadata", bbj.this.pC.E());
         var1.pC("toplevelClass", bbj.this.pC.E());
         var1.pC("usedScripts", bbj.this.pC.E());
         var1.pC("loadingUnit", bbj.this.pC.E());
         var1.pC("imports", bbj.this.pC.E());
         var1.pC("exports", bbj.this.pC.E());
         if (!bbj.this.pC.vP) {
            var1.pC("dependencies", bbj.this.pC.E());
            var1.pC("kernelData", bbj.this.pC.E());
         }
      }
   }

   class p extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.LM);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("filledEntryCount", bbj.this.pC.ld());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbj.this.pC.E());
         var1.pC("argsDescriptor", bbj.this.pC.E());
         var1.pC("buckets", bbj.this.pC.E());
         var1.pC("mask", bbj.this.pC.E());
      }
   }

   class qt extends bbj.bO {
      int pC;
      int A;
      int kS;

      qt(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.UT = bbj.this.pC.mv;
         long var1 = bbj.this.pC.E();
         this.A = bbj.this.pC.ld();
         this.kS = bbj.this.pC.ld();
         bbj.this.pC.pC(var1, this.pC);
         this.E = bbj.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bbj.this.pC.ys.E;
         int var2 = (int)bbj.this.pC.pC(this.kS * bbj.this.pC.ys.UT, bbj.this.pC.ys.ld);
         Long var3 = bbj.this.pC.E();

         for (int var4 = this.UT; var4 < this.E; var4++) {
            bbc var5 = bbj.this.pC.kS(var4);
            var5.pC("isCanonical", this.wS);
            ArrayList var6 = new ArrayList();
            var5.pC("data", var6);

            int var7;
            for (var7 = bbj.this.pC.FE ? 8 : 4; var7 < var1; var7 = (int)(var7 + bbj.this.pC.ys.UT)) {
               if (bbj.this.pC.A(var3, var7 / (int)bbj.this.pC.ys.UT)) {
                  bbj.this.pC.ld();
                  bbj.this.pC.ld();
               } else {
                  bbj.this.pC.kS();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bbj.this.pC.ys.UT);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class rQ extends bbj.bO {
      @Override
      public void pC() {
         this.A(bbi.rl);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bbj.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bbj.this.pC.sY());
               var6.put("outerTryIndex", bbj.this.pC.WR());
               var6.put("needsStacktrace", bbj.this.pC.A());
               var6.put("hasCatchAll", bbj.this.pC.A());
               var6.put("isGenerated", bbj.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class sy extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.go);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("isCanonical", this.wS);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bbj.this.pC.E());
         var1.pC("length", bbj.this.pC.E());
         var1.pC("data", bbj.this.pC.E());
      }
   }

   class uX extends bbj.ma {
      uX() {
         super(bbi.tH);
      }

      protected String pC(int var1) {
         bbj.this.pC.E.position(var1);
         bbj.this.pC.E.i32();
         long var2;
         if (bbj.this.pC.FE) {
            bbj.this.pC.E.i32();
            var2 = bbj.this.pC.E.i64();
         } else {
            var2 = bbj.this.pC.E.i32();
            bbj.this.pC.E.i32();
         }

         Assert.a(var2 <= 2147483647L);
         byte[] var4 = bbj.this.pC.E.get((int)var2 / 2);
         return Strings.decodeASCII(var4);
      }
   }

   class vi extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.sO);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            var2.pC("parent", bbj.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bbj.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class yt extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.sY);
      }

      @Override
      public void A() {
         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            if (bbj.this.pC.WR) {
               throw new ToDoException();
            }

            if (!bbj.this.pC.vP) {
               throw new ToDoException();
            }

            var2.pC("code", bbj.this.pC.kS());
            if (!bbj.this.pC.ah && !bbj.this.pC.vP) {
               var2.pC("tokenPos", bbj.this.pC.UT());
               var2.pC("endTokenPos", bbj.this.pC.UT());
               var2.pC("kernelOffset", bbj.this.pC.E());
            }

            var2.pC("packedFields", bbj.this.pC.E());
            var2.pC("kindTag", bbj.this.pC.E());
            if (!bbj.this.pC.vP && !bbj.this.pC.ah) {
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
         var1.pC("name", bbj.this.pC.E());
         var1.pC("owner", bbj.this.pC.E());
         var1.pC("parameterNames", bbj.this.pC.E());
         var1.pC("signature", bbj.this.pC.E());
         var1.pC("data", bbj.this.pC.E());
      }
   }

   class zp extends bbj.bO {
      @Override
      public void pC() {
         this.pC(bbi.ld);
      }

      @Override
      public void A() {
         Assert.a(!this.wS);

         for (int var1 = this.UT; var1 < this.E; var1++) {
            bbc var2 = bbj.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("callbackId", bbj.this.pC.vP ? bbj.this.pC.E() : 0L);
         }
      }

      void pC(bbc var1) {
         var1.pC("signatureType", bbj.this.pC.E());
         var1.pC("cSignature", bbj.this.pC.E());
         var1.pC("callbackTarget", bbj.this.pC.E());
         var1.pC("callbackExceptionalReturn", bbj.this.pC.E());
      }
   }
}
