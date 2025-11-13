package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bfd {
   private static final ILogger RF = GlobalLog.getLogger(bfd.class);
   bff q;

   bfd(bff var1) {
      Assert.a(var1.io);
      this.q = var1;
   }

   bfd.oL q(int var1, boolean var2) {
      bfd.oL var3 = this.q(var1);
      ((bfd.oM)var3).Dw = var2;
      return var3;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bfd.oL q(int var1) {
      bfc var2 = bfc.q(var1);
      if (var1 >= bfc.cO.q() || var1 == bfc.Bu.q()) {
         return new bfd.Vj(var1);
      } else if (this.q.za(var1)) {
         return new bfd.qa(var2);
      } else {
         if (this.q.KT) {
            switch (var2) {
               case Me:
               case PV:
               case oQ:
               case fw:
                  return new bfd.CI(var2);
               case YA:
                  return new bfd.kY();
            }
         }

         switch (var2) {
            case Uv:
               return new bfd.CU();
            case oW:
               return new bfd.Xa();
            case gO:
               return new bfd.vn();
            case nf:
               return new bfd.nI();
            case gP:
               return new bfd.EE();
            case za:
               return new bfd.qV();
            case lm:
               return new bfd.vX();
            case zz:
               return new bfd.Bu();
            case LK:
               return new bfd.Nt();
            case Hk:
               return new bfd.Uz();
            case CY:
               return new bfd.Nz();
            case Yw:
            case IY:
               return new bfd.eo(var2);
            case KT:
               return new bfd.tw();
            case sH:
               return new bfd.ME();
            case Dz:
               return new bfd.tl();
            case mI:
               return new bfd.zJ();
            case jq:
               return new bfd.bK();
            case rL:
               return new bfd.KZ();
            case YN:
               return new bfd.eM();
            case Rv:
               return new bfd.PY();
            case zx:
               return new bfd.LR();
            case ZT:
               return new bfd.HA();
            case Ri:
               return new bfd.ej();
            case WI:
               return new bfd.iZ();
            case Yp:
               return new bfd.vb();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Bu extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.zz);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bfd.this.q.qa());
            var2.q("numImports", bfd.this.q.HF());
            var2.q("loadState", bfd.this.q.JY());
            var2.q("flags", bfd.this.q.zz());
            if (!bfd.this.q.PV && !bfd.this.q.io) {
               var2.q("kernelOffset", bfd.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfd.this.q.nf());
         var1.q("url", bfd.this.q.nf());
         var1.q("privateKey", bfd.this.q.nf());
         var1.q("dictionary", bfd.this.q.nf());
         var1.q("metadata", bfd.this.q.nf());
         var1.q("toplevelClass", bfd.this.q.nf());
         var1.q("usedScripts", bfd.this.q.nf());
         var1.q("loadingUnit", bfd.this.q.nf());
         var1.q("imports", bfd.this.q.nf());
         var1.q("exports", bfd.this.q.nf());
         if (!bfd.this.q.io) {
            var1.q("dependencies", bfd.this.q.nf());
            var1.q("kernelData", bfd.this.q.nf());
         }
      }
   }

   class CI extends bfd.oM {
      bfc RF;

      CI(bfc var2) {
         this.RF = var2;
      }

      @Override
      public void q() {
         long var1 = bfd.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bfd.this.q.nf() << (int)bfd.this.q.gP.za));
            Object var6 = this.RF(var3);
            bew var7 = bfd.this.q.q(this.RF.q());
            var7.q("data", var6);
            bfd.this.q.q(var7);
         }
      }

      protected Object RF(int var1) {
         return Strings.ff("ROData_object_at_0x%X", var1);
      }

      @Override
      public void RF() {
      }
   }

   class CU extends bfd.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bfd.this.q.jq;
         long var1 = bfd.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bfd.this.q.q(bfc.Uv.q());
            var2.q("id", bfd.this.q.oW());
            bfd.this.q.q(var2);
         });
         this.RF = bfd.this.q.jq;
         this.q(bfc.Uv);
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.Uv, this.oW}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bfd.this.q.xK(var6);
               this.q(var8);
               int var9 = bfd.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bfd.this.q.PV && !bfd.this.q.io) {
                  var8.q("kernelOffset", bfd.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bfd.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bfd.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bfd.this.q.za());
               if (!bfd.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bfd.this.q.za());
               var8.q("numNativeFields", bfd.this.q.nf());
               var8.q("tokenPos", bfd.this.q.gO());
               var8.q("endTokenPos", bfd.this.q.gO());
               var8.q("stateBits", bfd.this.q.nf());
               if (bfd.this.q.PV) {
                  if (var7) {
                     bfd.this.q.nf();
                  } else if (!bfd.this.q.gP.q(var9)) {
                     bfd.this.q.TX.put(var9, bfd.this.q.nf());
                  }
               }

               bfd.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfd.this.q.nf());
         var1.q("userName", bfd.this.q.nf());
         var1.q("functions", bfd.this.q.nf());
         var1.q("functionsHashTable", bfd.this.q.nf());
         var1.q("fields", bfd.this.q.nf());
         var1.q("offsetInWordsToField", bfd.this.q.nf());
         var1.q("interfaces", bfd.this.q.nf());
         var1.q("script", bfd.this.q.nf());
         var1.q("library", bfd.this.q.nf());
         var1.q("typeParameters", bfd.this.q.nf());
         var1.q("superType", bfd.this.q.nf());
         var1.q("constants", bfd.this.q.nf());
         var1.q("declarationType", bfd.this.q.nf());
         var1.q("invocationDispatcherCache", bfd.this.q.nf());
         var1.q("allocationStub", bfd.this.q.nf());
         if (!bfd.this.q.io) {
            var1.q("directImplementors", bfd.this.q.nf());
            var1.q("directSubclasses", bfd.this.q.nf());
            if (!bfd.this.q.HF) {
               var1.q("dependentCode", bfd.this.q.nf());
               if (!bfd.this.q.LK) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class EE extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.gP);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bfd.this.q.io ? bfd.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bfd.this.q.nf());
         var1.q("cSignature", bfd.this.q.nf());
         var1.q("callbackTarget", bfd.this.q.nf());
         var1.q("callbackExceptionalReturn", bfd.this.q.nf());
      }
   }

   class HA extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.ZT);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bfd.this.q.qa());
            var2.q("base", bfd.this.q.HF());
            var2.q("index", bfd.this.q.HF());
            long var3 = bfd.this.q.zz();
            var2.q("typeState", var3 >> (int)bfd.this.q.gP.Rr);
            var2.q("nullability", var3 & bfd.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfd.this.q.nf());
         var1.q("name", bfd.this.q.nf());
         var1.q("hash", bfd.this.q.nf());
         var1.q("bound", bfd.this.q.nf());
         var1.q("defaultArgument", bfd.this.q.nf());
      }
   }

   class KZ extends bfd.oM {
      @Override
      public void q() {
         this.RF(bfc.rL);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bfd.this.q.lm());
            var2.q("nullabity", bfd.this.q.nf());
            var2.q("instantiations", bfd.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bfd.this.q.Uv());
            }
         }
      }
   }

   class LR extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.zx);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfd.this.q.nf());
         var1.q("type", bfd.this.q.nf());
      }
   }

   class ME extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.sH);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bfd.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfd.this.q.nf());
         var1.q("argsDescriptor", bfd.this.q.nf());
      }
   }

   class Nt extends bfd.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q(bfc.LK);
         this.q = bfd.this.q.jq;
         long var1 = bfd.this.q.nf();
         bfd.this.q.q(var1, bfc.LK.q());
         this.RF = bfd.this.q.jq;
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bfd.this.q.xK(var1);
         this.q(var3, var2);
         if (bfd.this.q.io && bfd.this.q.oQ) {
            var3.q("objectPool", null);
         } else {
            var3.q("objectPool", bfd.this.q.Uv());
         }

         var3.q("owner", bfd.this.q.Uv());
         var3.q("exceptionHandlers", bfd.this.q.Uv(), bfc.KT.toString());
         var3.q("pcDescriptors", bfd.this.q.Uv());
         var3.q("catchEntry", bfd.this.q.Uv());
         var3.q("compressedStackMaps", bfd.this.q.Uv());
         var3.q("inlinedIdToFunction", bfd.this.q.Uv());
         var3.q("codeSourceMap", bfd.this.q.Uv());
         if (!bfd.this.q.PV && bfd.this.q.LK) {
            var3.q("deoptInfoArray", bfd.this.q.Uv());
            var3.q("staticCallsTargetTable", bfd.this.q.Uv());
         }

         if (!bfd.this.q.Me) {
            var3.q("returnAddressMetadata", bfd.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bfd.this.q.Gf ? bfd.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         var3.q("stateBits", bfd.this.q.za());
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bfd.this.q.PV && bfd.this.q.oQ) {
            bfd.this.q.ZT = (int)(bfd.this.q.ZT + bfd.this.q.nf());
            long var3 = bfd.this.q.Ri + bfd.this.q.ZT;
            long var5 = bfd.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bfd.this.q.gP.oQ : 0L;
            long var12 = var9 ? bfd.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class Nz extends bfd.oM {
      @Override
      public void q() {
         long var1 = bfd.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bfd.this.q.q(bfc.CY.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bfd.this.q.za());
            bfd.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class PY extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.Rv);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfd.this.q.zz();
            var2.q("typeState", var3 >> (int)bfd.this.q.gP.Rr);
            var2.q("nullability", var3 & bfd.this.q.gP.EB);
            var2.q("packedFields", bfd.this.q.io());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfd.this.q.nf());
         var1.q("typeParameters", bfd.this.q.nf());
         var1.q("resultType", bfd.this.q.nf());
         var1.q("parameterTypes", bfd.this.q.nf());
         var1.q("parameterNames", bfd.this.q.nf());
         var1.q("hash", bfd.this.q.nf());
      }
   }

   class Uz extends bfd.oM {
      @Override
      public void q() {
         this.RF(bfc.Hk);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bfd.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bfd.this.q.oW(var6);
               if (var8 == bfd.this.q.gP.xW) {
                  var7.put("rawObj", bfd.this.q.Uv());
               } else if (var8 == bfd.this.q.gP.KT) {
                  var7.put("rawValue", bfd.this.q.za());
               } else {
                  if (var8 != bfd.this.q.gP.Gf) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Vj extends bfd.oM {
      int q;
      int RF;
      int xK;

      Vj(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Uv = bfd.this.q.jq;
         long var1 = bfd.this.q.nf();
         this.RF = bfd.this.q.lm();
         this.xK = bfd.this.q.lm();
         bfd.this.q.q(var1, this.q);
         this.oW = bfd.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bfd.this.q.gP.oW;
         int var2 = (int)bfd.this.q.q(this.xK * bfd.this.q.gP.Uv, bfd.this.q.gP.gP);
         Long var3 = bfd.this.q.nf();

         for (int var4 = this.Uv; var4 < this.oW; var4++) {
            bew var5 = bfd.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);
            ArrayList var6 = new ArrayList();
            var5.q("data", var6);

            int var7;
            for (var7 = bfd.this.q.wF ? 8 : 4; var7 < var1; var7 = (int)(var7 + bfd.this.q.gP.Uv)) {
               if (bfd.this.q.RF(var3, var7 / (int)bfd.this.q.gP.Uv)) {
                  bfd.this.q.lm();
                  bfd.this.q.lm();
               } else {
                  bfd.this.q.Uv();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bfd.this.q.gP.Uv);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class Xa extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.oW);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            if (!bfd.this.q.PV && !bfd.this.q.io) {
               var2.q("libraryKernelOffset", bfd.this.q.lm());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bfd.this.q.nf());
         var1.q("originClass", bfd.this.q.nf());
         var1.q("script", bfd.this.q.nf());
         if (!bfd.this.q.io) {
            var1.q("libraryKernelData", bfd.this.q.nf());
         }
      }
   }

   class bK extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.jq);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("parent", bfd.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bfd.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class eM extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.YN);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfd.this.q.zz();
            var2.q("typeState", var3 >> (int)bfd.this.q.gP.Rr);
            var2.q("nullability", var3 & bfd.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfd.this.q.nf());
         var1.q("typeClassId", bfd.this.q.nf());
         var1.q("arguments", bfd.this.q.nf());
         var1.q("hash", bfd.this.q.nf());
      }
   }

   class ej extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.Ri);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bfd.this.q.nf());
         var1.q("functionTypeArguments", bfd.this.q.nf());
         var1.q("delayedTypeArguments", bfd.this.q.nf());
         var1.q("function", bfd.this.q.nf());
         var1.q("context", bfd.this.q.nf());
         var1.q("hash", bfd.this.q.nf());
      }
   }

   class eo extends bfd.oM {
      bfc q;

      eo(bfc var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bfd.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bfd.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.WI);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bfd.this.q.za());
         }
      }
   }

   class kY extends bfd.CI {
      kY() {
         super(bfc.YA);
      }

      protected String q(int var1) {
         bfd.this.q.gO.position(var1);
         bfd.this.q.gO.i32();
         long var2;
         if (bfd.this.q.wF) {
            bfd.this.q.gO.i32();
            var2 = bfd.this.q.gO.i64();
         } else {
            var2 = bfd.this.q.gO.i32();
            bfd.this.q.gO.i32();
         }

         Assert.a(var2 <= 2147483647L);
         byte[] var4 = bfd.this.q.gO.get((int)var2 / 2);
         return Strings.decodeASCII(var4);
      }
   }

   class nI extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.nf);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            if (bfd.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bfd.this.q.Uv());
            }

            var2.q("parentFunction", bfd.this.q.Uv());
            var2.q("closure", bfd.this.q.Uv());
            var2.q("defaultTypeArguments", bfd.this.q.Uv());
            var2.q("defaultTypeArgumentsInfo", bfd.this.q.Uv());
         }
      }
   }

   interface oL {
      void q();

      void RF();
   }

   abstract class oM implements bfd.oL {
      boolean Dw;
      int Uv;
      int oW;

      @Override
      public void q() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void RF() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void q(bfc var1) {
         this.Uv = bfd.this.q.jq;
         long var2 = bfd.this.q.nf();
         bfd.this.q.q(var2, var1.q());
         this.oW = bfd.this.q.jq;
      }

      protected final void RF(bfc var1) {
         this.Uv = bfd.this.q.jq;
         long var2 = bfd.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bfd.this.q.q(var1.q());
            long var4 = bfd.this.q.nf();
            var3.q("length", var4);
            bfd.this.q.q(var3);
         });
         this.oW = bfd.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bfd.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.za);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            if (!bfd.this.q.io) {
               var2.q("guardedListLength", bfd.this.q.Uv());
            }

            if (bfd.this.q.LK) {
               var2.q("dependentCode", bfd.this.q.Uv());
            }

            if (!bfd.this.q.io) {
               var2.q("tokenPos", bfd.this.q.gO());
               var2.q("endTokenPos", bfd.this.q.gO());
               var2.q("guardedCid", bfd.this.q.oW());
               var2.q("isNullable", bfd.this.q.oW());
               var2.q("staticTypeExactnessState", bfd.this.q.JY());
               if (!bfd.this.q.PV) {
                  var2.q("kernelOffset", bfd.this.q.gP());
               }
            }

            int var3 = bfd.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bfd.this.q.Uv();
            if (bfd.this.q.Uv(var3)) {
               long var6 = bfd.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bfd.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfd.this.q.nf());
         var1.q("owner", bfd.this.q.nf());
         var1.q("type", bfd.this.q.nf());
         var1.q("initializerFunction", bfd.this.q.nf());
      }
   }

   class qa extends bfd.oM {
      bfc q;

      public qa(bfc var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bfd.this.q.HF(this.q.q());
            var2.q("data", bfd.this.q.Dw(var4));
         }
      }
   }

   class tl extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.Dz);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bfd.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfd.this.q.nf());
         var1.q("argsDescriptor", bfd.this.q.nf());
         var1.q("buckets", bfd.this.q.nf());
         var1.q("mask", bfd.this.q.nf());
      }
   }

   class tw extends bfd.oM {
      @Override
      public void q() {
         this.RF(bfc.KT);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bfd.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bfd.this.q.gP());
               var6.put("outerTryIndex", bfd.this.q.LK());
               var6.put("needsStacktrace", bfd.this.q.Dw());
               var6.put("hasCatchAll", bfd.this.q.Dw());
               var6.put("isGenerated", bfd.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class vX extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.lm);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            var2.q("lineOffset", bfd.this.q.qa());
            var2.q("colOffset", bfd.this.q.qa());
            if (!bfd.this.q.PV) {
               var2.q("flagsAndMaxPosition", bfd.this.q.qa());
            }

            var2.q("kernelScriptIndex", bfd.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bfd.this.q.nf());
         if (!bfd.this.q.io) {
            var1.q("resolvedUrl", bfd.this.q.nf());
            var1.q("compileTimeConstants", bfd.this.q.nf());
            var1.q("lineStarts", bfd.this.q.nf());
            var1.q("debugPositions", bfd.this.q.nf());
            var1.q("kernelProgramInfo", bfd.this.q.nf());
         }
      }
   }

   class vb extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.Yp);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bfd.this.q.nf());
         var1.q("length", bfd.this.q.nf());
         var1.q("data", bfd.this.q.nf());
      }
   }

   class vn extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.gO);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            this.q(var2);
            if (bfd.this.q.HF) {
               throw new ToDoException();
            }

            if (!bfd.this.q.io) {
               throw new ToDoException();
            }

            var2.q("code", bfd.this.q.Uv());
            if (!bfd.this.q.PV && !bfd.this.q.io) {
               var2.q("tokenPos", bfd.this.q.gO());
               var2.q("endTokenPos", bfd.this.q.gO());
               var2.q("kernelOffset", bfd.this.q.nf());
            }

            var2.q("packedFields", bfd.this.q.nf());
            var2.q("kindTag", bfd.this.q.nf());
            if (!bfd.this.q.io && !bfd.this.q.PV) {
               var2.q("usageCounter", 0);
               var2.q("optimizedInstructionCount", 0);
               var2.q("optimizedCallSiteCount", 0);
               var2.q("deoptimizationCounter", 0);
               var2.q("stateBits", 0);
               var2.q("inliningDepth", 0);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfd.this.q.nf());
         var1.q("owner", bfd.this.q.nf());
         var1.q("parameterNames", bfd.this.q.nf());
         var1.q("signature", bfd.this.q.nf());
         var1.q("data", bfd.this.q.nf());
      }
   }

   class zJ extends bfd.oM {
      @Override
      public void q() {
         this.q(bfc.mI);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfd.this.q.xK(var1);
            var2.q("cache", bfd.this.q.Uv());
         }
      }
   }
}
