package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

class bbf {
   private static final ILogger A = GlobalLog.getLogger(bbf.class);
   bbh pC;

   bbf(bbh var1) {
      this.pC = var1;
   }

   bbf.RC pC(int var1) {
      bbe var2 = bbe.pC(var1);
      if (var1 >= bbe.uu.pC() || var1 == bbe.pF.pC()) {
         return new bbf.sy(var1);
      } else if (this.pC.ld(var1)) {
         return new bbf.nA(var2);
      } else {
         if (this.pC.Ab) {
            switch (var2) {
               case Ab:
               case rl:
               case z:
               case AU:
                  return new bbf.uX(var2);
               case IE:
                  return new bbf.GK();
            }
         }

         switch (var2) {
            case UT:
               return new bbf.Sv();
            case E:
               return new bbf.Mh();
            case sY:
               return new bbf.KD();
            case ys:
               return new bbf.K();
            case ld:
               return new bbf.ma();
            case fI:
               return new bbf.zp();
            case WR:
               return new bbf.Tb();
            case NS:
               return new bbf.HE();
            case ED:
               return new bbf.cq();
            case UO:
               return new bbf.p();
            case go:
               return new bbf.vi();
            case tH:
            case Gm:
               return new bbf.Av(var2);
            case hK:
               return new bbf.rQ();
            case LM:
               return new bbf.m();
            case Cu:
               return new bbf.oP();
            case hZ:
               return new bbf.gJ();
            case UW:
               return new bbf.qt();
            case OI:
               return new bbf.Pj();
            case Pe:
               return new bbf.io();
            case ck:
               return new bbf.Kr();
            case RW:
               return new bbf.gb();
            case e:
               return new bbf.Ws();
            case JF:
               return new bbf.DH();
            case pg:
               return new bbf.yt();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Av extends bbf.bO {
      bbe pC;

      Av(bbe var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", bbf.this.pC.A());
            var2.pC("typeArguments", bbf.this.pC.kS());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bbf.this.pC.kS();
            }

            var2.pC("data", var4);
         }
      }
   }

   class DH extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.JF);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("isCanonical", bbf.this.pC.A());
            var2.pC("value", bbf.this.pC.ys());
         }
      }
   }

   class GK extends bbf.uX {
      GK() {
         super(bbe.IE);
      }

      protected String pC(int var1) {
         bbf.this.pC.E.position(var1);
         bbf.this.pC.E.i32();
         long var2;
         if (bbf.this.pC.FE) {
            bbf.this.pC.E.i32();
            var2 = bbf.this.pC.E.i64();
         } else {
            var2 = bbf.this.pC.E.i32();
            bbf.this.pC.E.i32();
         }

         Assert.a(var2 <= 2147483647L);
         byte[] var4 = bbf.this.pC.E.get((int)var2 / 2);
         return Strings.decodeASCII(var4);
      }
   }

   class HE extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.NS);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("nativeEntryResolver", null);
            var2.pC("nativeEntrySymbolResolver", null);
            var2.pC("index", bbf.this.pC.vP());
            var2.pC("numImports", bbf.this.pC.fI());
            var2.pC("loadState", bbf.this.pC.oT());
            var2.pC("flags", bbf.this.pC.gp());
            if (!bbf.this.pC.ah && !bbf.this.pC.vP) {
               var2.pC("binaryDeclaration", bbf.this.pC.NS());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbf.this.pC.E());
         var1.pC("url", bbf.this.pC.E());
         var1.pC("privateKey", bbf.this.pC.E());
         var1.pC("dictionary", bbf.this.pC.E());
         var1.pC("metadata", bbf.this.pC.E());
         var1.pC("toplevelClass", bbf.this.pC.E());
         var1.pC("usedScripts", bbf.this.pC.E());
         var1.pC("loadingUnit", bbf.this.pC.E());
         var1.pC("imports", bbf.this.pC.E());
         var1.pC("exports", bbf.this.pC.E());
         if (bbf.this.pC.WR || bbf.this.pC.NS) {
            var1.pC("dependencies", bbf.this.pC.E());
            var1.pC("kernelData", bbf.this.pC.E());
         }
      }
   }

   class K extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.ys);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            if (bbf.this.pC.vP) {
               var2.pC("contextScope", null);
            } else {
               var2.pC("contextScope", bbf.this.pC.kS());
            }

            var2.pC("parentFunction", bbf.this.pC.kS());
            var2.pC("signatureType", bbf.this.pC.kS());
            var2.pC("closure", bbf.this.pC.kS());
         }
      }
   }

   class KD extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.sY);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            if (bbf.this.pC.WR) {
               throw new ToDoException();
            }

            if (!bbf.this.pC.vP) {
               throw new ToDoException();
            }

            var2.pC("code", bbf.this.pC.kS());
            if (!bbf.this.pC.ah && !bbf.this.pC.vP) {
               var2.pC("tokenPos", bbf.this.pC.UT());
               var2.pC("endTokenPos", bbf.this.pC.UT());
               var2.pC("binaryDeclaration", bbf.this.pC.E());
            }

            var2.pC("packedFields", bbf.this.pC.E());
            var2.pC("kindTag", bbf.this.pC.E());
            if (!bbf.this.pC.vP && !bbf.this.pC.ah) {
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
         var1.pC("name", bbf.this.pC.E());
         var1.pC("owner", bbf.this.pC.E());
         var1.pC("resultType", bbf.this.pC.E());
         var1.pC("parameterTypes", bbf.this.pC.E());
         var1.pC("parameterNames", bbf.this.pC.E());
         var1.pC("typeParameters", bbf.this.pC.E());
         var1.pC("data", bbf.this.pC.E());
      }
   }

   class Kr extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.ck);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("typeTestStub", bbf.this.pC.E());
            var2.pC("type", bbf.this.pC.E());
         }
      }
   }

   class Mh extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.E);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            if (!bbf.this.pC.ah && !bbf.this.pC.vP) {
               var2.pC("libraryKernelOffset", bbf.this.pC.ld());
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("patchedClass", bbf.this.pC.E());
         var1.pC("originClass", bbf.this.pC.E());
         var1.pC("script", bbf.this.pC.E());
         if (!bbf.this.pC.vP) {
            var1.pC("libraryKernelData", bbf.this.pC.E());
         }
      }
   }

   class Pj extends bbf.bO {
      @Override
      public void pC() {
         this.A(bbe.OI);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.A(var2);
            var2.pC("isCanonical", bbf.this.pC.A());
            var2.pC("hash", bbf.this.pC.ld());
            var2.pC("nullabity", bbf.this.pC.E());
            var2.pC("instantiations", bbf.this.pC.kS());
            ArrayList var3 = new ArrayList();
            var2.pC("types", var3);

            for (int var4 = 0; var4 < var2.A(); var4++) {
               var3.add(bbf.this.pC.kS());
            }
         }
      }
   }

   interface RC {
      void pC();

      void A();
   }

   class Sv extends bbf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbf.this.pC.mv;
         long var1 = bbf.this.pC.E();
         Longs.range(var1).forEach(var1x -> {
            bbc var2 = bbf.this.pC.pC(bbe.UT.pC());
            var2.pC("id", bbf.this.pC.wS());
            bbf.this.pC.pC(var2);
         });
         this.A = bbf.this.pC.mv;
         this.pC(bbe.UT);
      }

      @Override
      public void A() {
         int[][] var1 = new int[][]{{this.pC, this.A}, {this.wS, this.UT}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.A;
               bbc var8 = bbf.this.pC.kS(var6);
               this.pC(var8);
               int var9 = bbf.this.pC.wS();
               if (var7) {
                  Assert.a(var8.kS("id").equals(var9));
               } else {
                  var8.pC("id", var9);
               }

               if (!bbf.this.pC.ah && !bbf.this.pC.vP) {
                  var8.pC("binaryDeclaration", bbf.this.pC.sY());
               }

               var8.pC("hostInstanceSizeInWords", bbf.this.pC.ys());
               var8.pC("hostNextFieldOffsetInWords", bbf.this.pC.ys());
               var8.pC("hostTypeArgumentsFieldOffsetInWords", bbf.this.pC.ys());
               if (!bbf.this.pC.ah) {
                  var8.pC("targetInstanceSizeInWords", var8.kS("hostInstanceSizeInWords"));
                  var8.pC("targetNextFieldOffsetInWords", var8.kS("hostNextFieldOffsetInWords"));
                  var8.pC("targetTypeArgumentsFieldOffsetInWords", var8.kS("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.pC("numTypeArguments", bbf.this.pC.ys());
               var8.pC("numNativeFields", bbf.this.pC.E());
               var8.pC("tokenPos", bbf.this.pC.UT());
               var8.pC("endTokenPos", bbf.this.pC.UT());
               var8.pC("stateBits", bbf.this.pC.E());
               if (bbf.this.pC.ah) {
                  if (var7) {
                     bbf.this.pC.E();
                  } else if (!bbf.this.pC.ys.pC(var9)) {
                     bbf.this.pC.os.put(var9, bbf.this.pC.E());
                  }
               }

               bbf.this.pC.sO.put(var9, var8);
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbf.this.pC.E());
         var1.pC("userName", bbf.this.pC.E());
         var1.pC("functions", bbf.this.pC.E());
         var1.pC("functionsHashTable", bbf.this.pC.E());
         var1.pC("fields", bbf.this.pC.E());
         var1.pC("offsetInWordsToField", bbf.this.pC.E());
         var1.pC("interfaces", bbf.this.pC.E());
         var1.pC("script", bbf.this.pC.E());
         var1.pC("library", bbf.this.pC.E());
         var1.pC("typeParameters", bbf.this.pC.E());
         var1.pC("superType", bbf.this.pC.E());
         var1.pC("signatureFunction", bbf.this.pC.E());
         var1.pC("constants", bbf.this.pC.E());
         var1.pC("declarationType", bbf.this.pC.E());
         var1.pC("invocationDispatcherCache", bbf.this.pC.E());
         var1.pC("allocationStub", bbf.this.pC.E());
         if (!bbf.this.pC.vP) {
            var1.pC("directImplementors", bbf.this.pC.E());
            var1.pC("directSubclasses", bbf.this.pC.E());
            if (!bbf.this.pC.WR) {
               var1.pC("dependentCode", bbf.this.pC.E());
               if (!bbf.this.pC.NS) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class Tb extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.WR);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("lineOffset", bbf.this.pC.vP());
            var2.pC("colOffset", bbf.this.pC.vP());
            var2.pC("flags", bbf.this.pC.gp());
            var2.pC("kernelScriptIndex", bbf.this.pC.vP());
            var2.pC("loadTimestamp", 0);
         }
      }

      void pC(bbc var1) {
         var1.pC("url", bbf.this.pC.E());
         if (bbf.this.pC.WR || bbf.this.pC.NS) {
            var1.pC("resolvedUrl", bbf.this.pC.E());
            var1.pC("compileTimeConstants", bbf.this.pC.E());
            var1.pC("lineStarts", bbf.this.pC.E());
            var1.pC("debugPositions", bbf.this.pC.E());
            var1.pC("kernelProgramInfo", bbf.this.pC.E());
         }
      }
   }

   class Ws extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.e);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("isCanonical", bbf.this.pC.A());
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("instantiatorTypeArguments", bbf.this.pC.E());
         var1.pC("functionTypeArguments", bbf.this.pC.E());
         var1.pC("delayedTypeArguments", bbf.this.pC.E());
         var1.pC("function", bbf.this.pC.E());
         var1.pC("context", bbf.this.pC.E());
         var1.pC("hash", bbf.this.pC.E());
      }
   }

   abstract class bO implements bbf.RC {
      int wS;
      int UT;

      @Override
      public void pC() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void A() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void pC(bbe var1) {
         this.wS = bbf.this.pC.mv;
         long var2 = bbf.this.pC.E();
         bbf.this.pC.pC(var2, var1.pC());
         this.UT = bbf.this.pC.mv;
      }

      protected final void A(bbe var1) {
         this.wS = bbf.this.pC.mv;
         long var2 = bbf.this.pC.E();
         Longs.range(var2).forEach(var2x -> {
            bbc var3 = bbf.this.pC.pC(var1.pC());
            long var4 = bbf.this.pC.E();
            var3.pC("length", var4);
            bbf.this.pC.pC(var3);
         });
         this.UT = bbf.this.pC.mv;
      }

      protected final int A(bbc var1) {
         long var2 = bbf.this.pC.E();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.A() == (int)var2);
         return (int)var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class cq extends bbf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC(bbe.ED);
         this.pC = bbf.this.pC.mv;
         long var1 = bbf.this.pC.E();
         bbf.this.pC.pC(var1, bbe.ED.pC());
         this.A = bbf.this.pC.mv;
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            this.pC(var1, false);
         }

         for (int var2 = this.pC; var2 < this.A; var2++) {
            this.pC(var2, true);
         }
      }

      void pC(int var1, boolean var2) {
         bbc var3 = bbf.this.pC.kS(var1);
         this.pC(var3, var2);
         if (bbf.this.pC.vP && bbf.this.pC.eP) {
            var3.pC("objectPool", null);
         } else {
            var3.pC("objectPool", bbf.this.pC.kS());
         }

         var3.pC("owner", bbf.this.pC.kS());
         var3.pC("exceptionHandlers", bbf.this.pC.kS(), bbe.hK.toString());
         var3.pC("pcDescriptors", bbf.this.pC.kS());
         var3.pC("catchEntry", bbf.this.pC.kS());
         var3.pC("compressedStackMaps", bbf.this.pC.kS());
         var3.pC("inlinedIdToFunction", bbf.this.pC.kS());
         var3.pC("codeSourceMap", bbf.this.pC.kS());
         if (!bbf.this.pC.ah && bbf.this.pC.NS) {
            var3.pC("deoptInfoArray", bbf.this.pC.kS());
            var3.pC("staticCallsTargetTable", bbf.this.pC.kS());
         }

         if (!bbf.this.pC.Sc) {
            var3.pC("returnAddressMetadata", bbf.this.pC.kS());
            var3.pC("varDescriptors", null);
            var3.pC("comments", bbf.this.pC.rl ? bbf.this.pC.kS() : null);
            var3.pC("compileTimestamp", 0);
         }

         var3.pC("stateBits", bbf.this.pC.ys());
      }

      void pC(bbc var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bbf.this.pC.ah && bbf.this.pC.eP) {
            bbf.this.pC.pF = (int)(bbf.this.pC.pF + bbf.this.pC.E());
            long var3 = bbf.this.pC.Bc + bbf.this.pC.pF;
            long var5 = bbf.this.pC.E();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bbf.this.pC.ys.UO : 0L;
            long var12 = var9 ? bbf.this.pC.ys.eP : 0L;
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

   class gJ extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.hZ);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("cache", bbf.this.pC.kS());
         }
      }
   }

   class gb extends bbf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbf.this.pC.mv;
         long var1 = bbf.this.pC.E();
         bbf.this.pC.pC(var1, bbe.RW.pC());
         this.A = bbf.this.pC.mv;
         this.pC(bbe.RW);
      }

      @Override
      public void A() {
         for (int var1 = this.pC; var1 < this.A; var1++) {
            this.pC(bbf.this.pC.kS(var1), true);
         }

         for (int var2 = this.wS; var2 < this.UT; var2++) {
            this.pC(bbf.this.pC.kS(var2), false);
         }
      }

      void pC(bbc var1, boolean var2) {
         this.pC(var1);
         var1.pC("isCanonical", var2);
         var1.pC("parameterizedClassId", bbf.this.pC.vP());
         var1.pC("tokenPos", bbf.this.pC.UT());
         var1.pC("index", bbf.this.pC.WR());
         long var3 = bbf.this.pC.gp();
         var1.pC("typeState", var3 >> (int)bbf.this.pC.ys.hZ);
         var1.pC("nullability", var3 & bbf.this.pC.ys.UW);
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbf.this.pC.E());
         var1.pC("name", bbf.this.pC.E());
         var1.pC("hash", bbf.this.pC.E());
         var1.pC("bound", bbf.this.pC.E());
         var1.pC("parameterizedFunction", bbf.this.pC.E());
      }
   }

   class io extends bbf.bO {
      int pC;
      int A;

      @Override
      public void pC() {
         this.pC = bbf.this.pC.mv;
         long var1 = bbf.this.pC.E();
         bbf.this.pC.pC(var1, bbe.Pe.pC());
         this.A = bbf.this.pC.mv;
         this.pC(bbe.Pe);
      }

      @Override
      public void A() {
         for (int var1 = this.pC; var1 < this.A; var1++) {
            this.pC(bbf.this.pC.kS(var1), true);
         }

         for (int var2 = this.wS; var2 < this.UT; var2++) {
            this.pC(bbf.this.pC.kS(var2), false);
         }
      }

      void pC(bbc var1, boolean var2) {
         this.pC(var1);
         var1.pC("isCanonical", var2);
         var1.pC("tokenPos", bbf.this.pC.UT());
         long var3 = bbf.this.pC.gp();
         var1.pC("typeState", var3 >> (int)bbf.this.pC.ys.hZ);
         var1.pC("nullability", var3 & bbf.this.pC.ys.UW);
      }

      void pC(bbc var1) {
         var1.pC("typeTestStub", bbf.this.pC.E());
         var1.pC("typeClassId", bbf.this.pC.E());
         var1.pC("arguments", bbf.this.pC.E());
         var1.pC("hash", bbf.this.pC.E());
         var1.pC("signature", bbf.this.pC.E());
      }
   }

   class m extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.LM);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("canPatchToMonomorphic", bbf.this.pC.A());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbf.this.pC.E());
         var1.pC("argsDescriptor", bbf.this.pC.E());
      }
   }

   class ma extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.ld);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("parentFunction", bbf.this.pC.E());
         var1.pC("signatureType", bbf.this.pC.E());
      }
   }

   class nA extends bbf.bO {
      bbe pC;

      public nA(bbe var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.A(this.pC);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("isCanonical", bbf.this.pC.A());
            int var4 = var3 * bbf.this.pC.WR(this.pC.pC());
            var2.pC("data", bbf.this.pC.wS(var4));
         }
      }
   }

   class oP extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.Cu);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            var2.pC("filledEntryCount", bbf.this.pC.ld());
         }
      }

      void pC(bbc var1) {
         var1.pC("targetName", bbf.this.pC.E());
         var1.pC("argsDescriptor", bbf.this.pC.E());
         var1.pC("buckets", bbf.this.pC.E());
         var1.pC("mask", bbf.this.pC.E());
      }
   }

   class p extends bbf.bO {
      @Override
      public void pC() {
         this.A(bbe.UO);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.A(var2);
            ArrayList var3 = new ArrayList();
            var2.pC("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var2.A(); var5++) {
               int var6 = bbf.this.pC.gp();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bbf.this.pC.E(var6);
               if (var8 == bbf.this.pC.ys.hK || var8 == bbf.this.pC.ys.Ab) {
                  var7.put("rawObj", bbf.this.pC.kS());
               } else if (var8 == bbf.this.pC.ys.rl) {
                  var7.put("rawValue", bbf.this.pC.ys());
               } else {
                  if (var8 != bbf.this.pC.ys.z) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class qt extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.UW);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("parent", bbf.this.pC.kS());
            var2.pC("baseObjects", null);
            var2.pC("id", bbf.this.pC.vP());
            var2.pC("loaded", false);
            var2.pC("loadOutstanding", false);
         }
      }
   }

   class rQ extends bbf.bO {
      @Override
      public void pC() {
         this.A(bbe.hK);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            int var3 = this.A(var2);
            var2.pC("numEntries", var3);
            var2.pC("handledTypesData", bbf.this.pC.kS());
            ArrayList var4 = new ArrayList();
            var2.pC("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bbf.this.pC.sY());
               var6.put("outerTryIndex", bbf.this.pC.WR());
               var6.put("needsStacktrace", bbf.this.pC.A());
               var6.put("hasCatchAll", bbf.this.pC.A());
               var6.put("isGenerated", bbf.this.pC.A());
               var4.add(var6);
            }
         }
      }
   }

   class sy extends bbf.bO {
      int pC;
      int A;
      int kS;

      sy(int var2) {
         this.pC = var2;
      }

      @Override
      public void pC() {
         this.wS = bbf.this.pC.mv;
         long var1 = bbf.this.pC.E();
         this.A = bbf.this.pC.ld();
         this.kS = bbf.this.pC.ld();
         bbf.this.pC.pC(var1, this.pC);
         this.UT = bbf.this.pC.mv;
      }

      @Override
      public void A() {
         int var1 = this.A << (int)bbf.this.pC.ys.E;
         int var2 = (int)bbf.this.pC.pC(this.kS * bbf.this.pC.ys.UT, bbf.this.pC.ys.ld);
         Long var3 = (Long)bbf.this.pC.os.get(this.pC);

         for (int var4 = this.wS; var4 < this.UT; var4++) {
            bbc var5 = bbf.this.pC.kS(var4);
            var5.pC("isCanonical", bbf.this.pC.A());
            ArrayList var6 = new ArrayList();
            var5.pC("data", var6);

            int var7;
            for (var7 = bbf.this.pC.FE ? 8 : 4; var7 < var1; var7 = (int)(var7 + bbf.this.pC.ys.UT)) {
               if (bbf.this.pC.A(var3, var7 / (int)bbf.this.pC.ys.UT)) {
                  bbf.this.pC.ld();
                  bbf.this.pC.ld();
               } else {
                  bbf.this.pC.kS();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bbf.this.pC.ys.UT);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class uX extends bbf.bO {
      bbe A;

      uX(bbe var2) {
         this.A = var2;
      }

      @Override
      public void pC() {
         long var1 = bbf.this.pC.E();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bbf.this.pC.E() << (int)bbf.this.pC.ys.gp));
            Object var6 = this.A(var3);
            bbc var7 = bbf.this.pC.pC(this.A.pC());
            var7.pC("data", var6);
            bbf.this.pC.pC(var7);
         }
      }

      protected Object A(int var1) {
         return Strings.ff("ROData_object_at_0x%X", var1);
      }

      @Override
      public void A() {
      }
   }

   class vi extends bbf.bO {
      @Override
      public void pC() {
         long var1 = bbf.this.pC.E();

         for (long var3 = 0L; var3 < var1; var3++) {
            bbc var5 = bbf.this.pC.pC(bbe.go.pC());
            var5.pC("isCanonical", bbf.this.pC.A());
            var5.pC("value", bbf.this.pC.ys());
            bbf.this.pC.pC(var5);
         }
      }

      @Override
      public void A() {
      }
   }

   class yt extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.pg);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            var2.pC("isCanonical", bbf.this.pC.A());
            this.pC(var2);
         }
      }

      void pC(bbc var1) {
         var1.pC("typeArguments", bbf.this.pC.E());
         var1.pC("length", bbf.this.pC.E());
         var1.pC("data", bbf.this.pC.E());
      }
   }

   class zp extends bbf.bO {
      @Override
      public void pC() {
         this.pC(bbe.fI);
      }

      @Override
      public void A() {
         for (int var1 = this.wS; var1 < this.UT; var1++) {
            bbc var2 = bbf.this.pC.kS(var1);
            this.pC(var2);
            if (!bbf.this.pC.vP) {
               if (!bbf.this.pC.ah) {
                  var2.pC("savedInitialValue", bbf.this.pC.kS());
               }

               var2.pC("guardedListLength", bbf.this.pC.kS());
            }

            if (bbf.this.pC.NS) {
               var2.pC("dependentCode", bbf.this.pC.kS());
            }

            if (!bbf.this.pC.vP) {
               var2.pC("tokenPos", bbf.this.pC.UT());
               var2.pC("endTokenPos", bbf.this.pC.UT());
               var2.pC("guardedCid", bbf.this.pC.wS());
               var2.pC("isNullable", bbf.this.pC.wS());
               var2.pC("staticTypeExactnessState", bbf.this.pC.oT());
               if (!bbf.this.pC.ah) {
                  var2.pC("binaryDeclaration", bbf.this.pC.sY());
               }
            }

            int var3 = bbf.this.pC.sY();
            var2.pC("kindBits", var3);
            long var4 = bbf.this.pC.kS();
            if (bbf.this.pC.UT(var3)) {
               long var6 = bbf.this.pC.E();
               var2.pC("hostOffsetOrFieldId", var6);
            } else {
               var2.pC("hostOffsetOrFieldId", var4);
               if (!bbf.this.pC.ah) {
                  var2.pC("targetOffset", var2.kS("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void pC(bbc var1) {
         var1.pC("name", bbf.this.pC.E());
         var1.pC("owner", bbf.this.pC.E());
         var1.pC("type", bbf.this.pC.E());
         var1.pC("initializerFunction", bbf.this.pC.E());
      }
   }
}
