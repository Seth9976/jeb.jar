package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bfl {
   private static final ILogger RF = GlobalLog.getLogger(bfl.class);
   bfn q;

   bfl(bfn var1) {
      Assert.a(var1.io);
      this.q = var1;
   }

   bfl.oL q(int var1, boolean var2) {
      bfl.oL var3 = this.q(var1);
      ((bfl.oM)var3).Dw = var2;
      return var3;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bfl.oL q(int var1) {
      bfk var2 = bfk.q(var1);
      if (var1 >= bfk.CK.q() || var1 == bfk.YN.q()) {
         return new bfl.Vj(var1);
      } else if (this.q.za(var1)) {
         return new bfl.qa(var2);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var2) {
               case xW:
               case KT:
               case Gf:
                  return new bfl.Xa(var2);
               case PY:
               case cR:
                  return new bfl.Xa(var2);
               case cY:
                  return new bfl.Xa(var2);
            }
         }

         switch (var2) {
            case Uv:
               return new bfl.CU();
            case oW:
               return new bfl.kY();
            case gO:
               return new bfl.vn();
            case gP:
               return new bfl.nI();
            case za:
               return new bfl.EE();
            case lm:
               return new bfl.qV();
            case zz:
               return new bfl.CI();
            case JY:
               return new bfl.Bu();
            case qa:
               return new bfl.Nt();
            case oQ:
               return new bfl.Uz();
            case Gu:
               return new bfl.Nz();
            case fw:
            case Wp:
               return new bfl.eo(var2);
            case cC:
               return new bfl.tw();
            case Dz:
               return new bfl.ME();
            case Rr:
               return new bfl.vX();
            case EB:
               return new bfl.bK();
            case zx:
               return new bfl.zJ();
            case Ri:
               return new bfl.KZ();
            case GY:
               return new bfl.PY();
            case Wx:
               return new bfl.LR();
            case AB:
               return new bfl.eM();
            case nf:
               return new bfl.HA();
            case CY:
               return new bfl.ej();
            case nY:
               return new bfl.iZ();
            case nq:
               return new bfl.vb();
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Bu extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.JY);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bfl.this.q.qa());
            var2.q("numImports", bfl.this.q.HF());
            var2.q("loadState", bfl.this.q.JY());
            var2.q("flags", bfl.this.q.zz());
            if (!bfl.this.q.PV && !bfl.this.q.io) {
               var2.q("kernelOffset", bfl.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfl.this.q.nf());
         var1.q("url", bfl.this.q.nf());
         var1.q("privateKey", bfl.this.q.nf());
         var1.q("dictionary", bfl.this.q.nf());
         var1.q("metadata", bfl.this.q.nf());
         var1.q("toplevelClass", bfl.this.q.nf());
         var1.q("usedScripts", bfl.this.q.nf());
         var1.q("loadingUnit", bfl.this.q.nf());
         var1.q("imports", bfl.this.q.nf());
         var1.q("exports", bfl.this.q.nf());
         if (!bfl.this.q.io) {
            var1.q("dependencies", bfl.this.q.nf());
            var1.q("kernelData", bfl.this.q.nf());
         }
      }
   }

   class CI extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.zz);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            if (!bfl.this.q.PV) {
               var2.q("flagsAndMaxPosition", bfl.this.q.qa());
            }

            var2.q("kernelScriptIndex", bfl.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bfl.this.q.nf());
         if (bfl.this.q.io) {
            if (!bfl.this.q.Me) {
               var1.q("resolvedUrl", bfl.this.q.nf());
            }
         } else {
            var1.q("resolvedUrl", bfl.this.q.nf());
            var1.q("resolvedUrl", bfl.this.q.nf());
            var1.q("lineStarts", bfl.this.q.nf());
            var1.q("constantCoverage", bfl.this.q.nf());
            var1.q("debugPositions", bfl.this.q.nf());
            var1.q("kernelProgramInfo", bfl.this.q.nf());
         }
      }
   }

   class CU extends bfl.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bfl.this.q.jq;
         long var1 = bfl.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bfl.this.q.q(bfk.Uv.q());
            var2.q("id", bfl.this.q.oW());
            bfl.this.q.q(var2);
         });
         this.RF = bfl.this.q.jq;
         this.q(bfk.Uv);
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.Uv, this.oW}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bfl.this.q.xK(var6);
               this.q(var8);
               int var9 = bfl.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bfl.this.q.PV && !bfl.this.q.io) {
                  var8.q("kernelOffset", bfl.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bfl.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bfl.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bfl.this.q.za());
               if (!bfl.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bfl.this.q.za());
               var8.q("numNativeFields", bfl.this.q.nf());
               if (!bfl.this.q.PV) {
                  Assert.a(!bfl.this.q.io);
                  var8.q("tokenPos", bfl.this.q.gO());
                  var8.q("endTokenPos", bfl.this.q.gO());
               }

               var8.q("stateBits", bfl.this.q.nf());
               if (bfl.this.q.PV) {
                  if (var7) {
                     bfl.this.q.nf();
                  } else if (!bfl.this.q.gP.q(var9)) {
                     bfl.this.q.TX.put(var9, bfl.this.q.nf());
                  }
               }

               bfl.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfl.this.q.nf());
         if (!bfl.this.q.Me) {
            var1.q("userName", bfl.this.q.nf());
         }

         var1.q("functions", bfl.this.q.nf());
         var1.q("functionsHashTable", bfl.this.q.nf());
         var1.q("fields", bfl.this.q.nf());
         var1.q("offsetInWordsToField", bfl.this.q.nf());
         var1.q("interfaces", bfl.this.q.nf());
         var1.q("script", bfl.this.q.nf());
         var1.q("library", bfl.this.q.nf());
         var1.q("typeParameters", bfl.this.q.nf());
         var1.q("superType", bfl.this.q.nf());
         var1.q("constants", bfl.this.q.nf());
         var1.q("declarationType", bfl.this.q.nf());
         var1.q("invocationDispatcherCache", bfl.this.q.nf());
         if (!bfl.this.q.Me || !bfl.this.q.PV) {
            var1.q("directImplementors", bfl.this.q.nf());
            var1.q("directSubclasses", bfl.this.q.nf());
         }

         if (!bfl.this.q.PV) {
            var1.q("allocationStub", bfl.this.q.nf());
            var1.q("dependentCode", bfl.this.q.nf());
         }
      }
   }

   class EE extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.za);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bfl.this.q.io ? bfl.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bfl.this.q.nf());
         var1.q("cSignature", bfl.this.q.nf());
         var1.q("callbackTarget", bfl.this.q.nf());
         var1.q("callbackExceptionalReturn", bfl.this.q.nf());
      }
   }

   class HA extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.AB);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bfl.this.q.nf());
         var1.q("flags", bfl.this.q.nf());
         var1.q("bounds", bfl.this.q.nf());
         var1.q("defaults", bfl.this.q.nf());
      }
   }

   class KZ extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.Ri);
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfl.this.q.zz();
            var2.q("typeState", var3 >> (int)bfl.this.q.gP.Rr);
            var2.q("nullability", var3 & bfl.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfl.this.q.nf());
         var1.q("typeClassId", bfl.this.q.nf());
         var1.q("arguments", bfl.this.q.nf());
         var1.q("hash", bfl.this.q.nf());
      }
   }

   class LR extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.Wx);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfl.this.q.nf());
         var1.q("type", bfl.this.q.nf());
      }
   }

   class ME extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.Dz);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bfl.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfl.this.q.nf());
         var1.q("argsDescriptor", bfl.this.q.nf());
      }
   }

   class Nt extends bfl.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.Uv = bfl.this.q.jq;
         long var1 = bfl.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.oW = bfl.this.q.jq;
         this.q = bfl.this.q.jq;
         var1 = bfl.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bfl.this.q.jq;
      }

      private void Dw() {
         int var1 = bfl.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         if (var2) {
            bew var3 = bfl.this.q.q(bfk.qa.q());
            var3.q("unknown", true);
            bfl.this.q.q(var3);
         } else {
            bew var4 = bfl.this.q.q(bfk.qa.q());
            bfl.this.q.q(var4);
            var4.q("stateBits", var1);
         }
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bfl.this.q.xK(var1);
         if (bfl.this.q.PV && Boolean.TRUE.equals(var3.q("unknown", Boolean.class))) {
            this.q(var3, var2, true);
         } else {
            this.q(var3, var2, false);
            if (bfl.this.q.io && bfl.this.q.oQ) {
               var3.q("objectPool", null);
            } else {
               var3.q("objectPool", bfl.this.q.Uv());
            }

            var3.q("owner", bfl.this.q.Uv());
            var3.q("exceptionHandlers", bfl.this.q.Uv(), bfk.cC.toString());
            var3.q("pcDescriptors", bfl.this.q.Uv());
            var3.q("catchEntry", bfl.this.q.Uv());
            var3.q("compressedStackMaps", bfl.this.q.Uv());
            var3.q("inlinedIdToFunction", bfl.this.q.Uv());
            var3.q("codeSourceMap", bfl.this.q.Uv());
            if (!bfl.this.q.PV && bfl.this.q.LK) {
               var3.q("deoptInfoArray", bfl.this.q.Uv());
               var3.q("staticCallsTargetTable", bfl.this.q.Uv());
            }

            if (!bfl.this.q.Me) {
               var3.q("returnAddressMetadata", bfl.this.q.Uv());
               var3.q("varDescriptors", null);
               var3.q("comments", bfl.this.q.Gf ? bfl.this.q.Uv() : null);
               var3.q("compileTimestamp", 0);
            }
         }
      }

      void q(bew var1, boolean var2, boolean var3) {
         if (var2) {
            Assert.a(!var3);
            throw new ToDoException();
         } else if (bfl.this.q.PV && bfl.this.q.oQ) {
            bfl.this.q.zx = (int)(bfl.this.q.zx + bfl.this.q.nf());
            long var4 = bfl.this.q.ZT + bfl.this.q.zx;
            long var6 = bfl.this.q.nf();
            long var8 = var6 >> 1;
            boolean var10 = (var6 & 1L) == 1L;
            long var11 = var10 ? bfl.this.q.gP.oQ : 0L;
            long var13 = var10 ? bfl.this.q.gP.PV : 0L;
            long var15 = var4 + var11;
            long var17 = var4 + var13;
            if (var3) {
               bfl.this.q.Uv();
            }

            if (!var3) {
               var1.q("entryPoint", var15);
               var1.q("uncheckedEntryPoint", var15 + var8);
               var1.q("monomorphicEntryPoint", var17);
               var1.q("monomorphicUncheckedEntryPoint", var17 + var8);
            }
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class Nz extends bfl.oM {
      @Override
      public void q() {
         long var1 = bfl.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bfl.this.q.q(bfk.Gu.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bfl.this.q.za());
            bfl.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class PY extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.GY);
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfl.this.q.zz();
            var2.q("typeState", var3 >> (int)bfl.this.q.gP.Rr);
            var2.q("nullability", var3 & bfl.this.q.gP.EB);
            var2.q("packedParameterCounts", bfl.this.q.io());
            var2.q("packedTypeParameterCounts", bfl.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfl.this.q.nf());
         var1.q("typeParameters", bfl.this.q.nf());
         var1.q("resultType", bfl.this.q.nf());
         var1.q("parameterTypes", bfl.this.q.nf());
         var1.q("namedParameterNames", bfl.this.q.nf());
         var1.q("hash", bfl.this.q.nf());
      }
   }

   class SG extends bfl.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class Uz extends bfl.oM {
      @Override
      public void q() {
         this.RF(bfk.oQ);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bfl.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bfl.this.q.oW(var6);
               if (var8 == bfl.this.q.gP.xW) {
                  var7.put("rawObj", bfl.this.q.Uv());
               } else if (var8 == bfl.this.q.gP.KT) {
                  var7.put("rawValue", bfl.this.q.za());
               } else {
                  if (var8 != bfl.this.q.gP.Gf) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Vj extends bfl.oM {
      int q;
      int RF;
      int xK;

      Vj(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Uv = bfl.this.q.jq;
         long var1 = bfl.this.q.nf();
         this.RF = bfl.this.q.qa();
         this.xK = bfl.this.q.qa();
         bfl.this.q.q(var1, this.q);
         this.oW = bfl.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bfl.this.q.gP.nf;
         int var2 = (int)bfl.this.q.q(this.xK * bfl.this.q.gP.gO, bfl.this.q.gP.gP);
         Long var3 = bfl.this.q.nf();

         for (int var4 = this.Uv; var4 < this.oW; var4++) {
            bew var5 = bfl.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);
            ArrayList var6 = new ArrayList();
            var5.q("data", var6);

            int var7;
            for (var7 = bfl.this.q.wF ? 8 : 4; var7 < var1; var7 = (int)(var7 + bfl.this.q.gP.gO)) {
               if (bfl.this.q.RF(var3, var7 / (int)bfl.this.q.gP.gO)) {
                  bfl.this.q.oQ();
               } else {
                  bfl.this.q.Uv();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bfl.this.q.gP.gO);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class Xa extends bfl.oM {
      bfk q;

      Xa(bfk var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Uv = bfl.this.q.jq;
         long var1 = bfl.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bfl.this.q.nf() << (int)bfl.this.q.gP.za));
            Object var6 = this.q(var3);
            bew var7 = bfl.this.q.q(this.q.q());
            var7.q("data", var6);
            bfl.this.q.q(var7);
         }

         this.oW = bfl.this.q.jq;
         if (this.q == bfk.cY) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object q(int var1) {
         if (this.q != bfk.PY && this.q != bfk.cY) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bfl.this.q.gO.position(var1);
            bfl.this.q.gO.i32();
            long var2;
            if (bfl.this.q.wF) {
               bfl.this.q.gO.i32();
               var2 = bfl.this.q.gO.i64();
            } else {
               var2 = bfl.this.q.gO.i32();
               bfl.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bfl.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class bK extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.EB);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("parent", bfl.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bfl.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class eM extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.AB);
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bfl.this.q.qa());
            var2.q("base", bfl.this.q.zz());
            var2.q("index", bfl.this.q.zz());
            long var3 = bfl.this.q.zz();
            var2.q("typeState", var3 >> (int)bfl.this.q.gP.Rr);
            var2.q("nullability", var3 & bfl.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfl.this.q.nf());
         var1.q("hash", bfl.this.q.nf());
         var1.q("bound", bfl.this.q.nf());
      }
   }

   class ej extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.CY);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bfl.this.q.nf());
         var1.q("functionTypeArguments", bfl.this.q.nf());
         var1.q("delayedTypeArguments", bfl.this.q.nf());
         var1.q("function", bfl.this.q.nf());
         var1.q("context", bfl.this.q.nf());
         var1.q("hash", bfl.this.q.nf());
      }
   }

   class eo extends bfl.oM {
      bfk q;

      eo(bfk var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bfl.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bfl.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.nY);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bfl.this.q.za());
         }
      }
   }

   class kY extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.oW);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            if (!bfl.this.q.PV && !bfl.this.q.io) {
               var2.q("libraryKernelOffset", bfl.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bfl.this.q.nf());
         var1.q("originClass", bfl.this.q.nf());
         var1.q("script", bfl.this.q.nf());
         if (!bfl.this.q.io) {
            var1.q("libraryKernelData", bfl.this.q.nf());
         }
      }
   }

   class nI extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.gP);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            if (bfl.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bfl.this.q.Uv());
            }

            var2.q("parentFunction", bfl.this.q.Uv());
            var2.q("closure", bfl.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bfl.this.q.nf());
         }
      }
   }

   interface oL {
      void q();

      void RF();
   }

   abstract class oM implements bfl.oL {
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

      protected final void q(bfk var1) {
         this.Uv = bfl.this.q.jq;
         long var2 = bfl.this.q.nf();
         bfl.this.q.q(var2, var1.q());
         this.oW = bfl.this.q.jq;
      }

      protected final void RF(bfk var1) {
         this.Uv = bfl.this.q.jq;
         long var2 = bfl.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bfl.this.q.q(var1.q());
            long var4 = bfl.this.q.nf();
            var3.q("length", var4);
            bfl.this.q.q(var3);
         });
         this.oW = bfl.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bfl.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Dw) {
            bfl.this.q.nf();
            long var1 = bfl.this.q.nf();

            for (int var3 = this.Uv + (int)var1; var3 < this.oW; var3++) {
               bfl.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.lm);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            if (!bfl.this.q.io) {
               var2.q("guardedListLength", bfl.this.q.Uv());
            }

            if (bfl.this.q.LK) {
               var2.q("dependentCode", bfl.this.q.Uv());
            }

            if (!bfl.this.q.io) {
               var2.q("tokenPos", bfl.this.q.gO());
               var2.q("endTokenPos", bfl.this.q.gO());
               var2.q("guardedCid", bfl.this.q.oW());
               var2.q("isNullable", bfl.this.q.oW());
               var2.q("staticTypeExactnessState", bfl.this.q.JY());
               if (!bfl.this.q.PV) {
                  var2.q("kernelOffset", bfl.this.q.gP());
               }
            }

            int var3 = bfl.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bfl.this.q.Uv();
            if (bfl.this.q.Uv(var3)) {
               long var6 = bfl.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bfl.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfl.this.q.nf());
         var1.q("owner", bfl.this.q.nf());
         var1.q("type", bfl.this.q.nf());
         var1.q("initializerFunction", bfl.this.q.nf());
      }
   }

   class qa extends bfl.oM {
      bfk q;

      public qa(bfk var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bfl.this.q.HF(this.q.q());
            var2.q("data", bfl.this.q.Dw(var4));
         }
      }
   }

   class tl extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.TX);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bfl.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfl.this.q.nf());
         var1.q("argsDescriptor", bfl.this.q.nf());
         var1.q("buckets", bfl.this.q.nf());
         var1.q("mask", bfl.this.q.nf());
      }
   }

   class tw extends bfl.oM {
      @Override
      public void q() {
         this.RF(bfk.cC);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bfl.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bfl.this.q.gP());
               var6.put("outerTryIndex", bfl.this.q.LK());
               var6.put("needsStacktrace", bfl.this.q.Dw());
               var6.put("hasCatchAll", bfl.this.q.Dw());
               var6.put("isGenerated", bfl.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class vX extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.Rr);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("cache", bfl.this.q.Uv());
         }
      }
   }

   class vb extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.nq);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bfl.this.q.nf());
         var1.q("length", bfl.this.q.nf());
         var1.q("data", bfl.this.q.nf());
      }
   }

   class vn extends bfl.oM {
      @Override
      public void q() {
         this.q(bfk.gO);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.q(var2);
            if (bfl.this.q.HF) {
               throw new ToDoException();
            }

            if (!bfl.this.q.io) {
               throw new ToDoException();
            }

            var2.q("codeIndex", bfl.this.q.nf());
            var2.q("code", var2.xK("codeIndex"));
            if (!bfl.this.q.PV && !bfl.this.q.io) {
               var2.q("positionalParameterNames", bfl.this.q.Uv());
               var2.q("tokenPos", bfl.this.q.gO());
               var2.q("endTokenPos", bfl.this.q.gO());
               var2.q("kernelOffset", bfl.this.q.nf());
            }

            var2.q("packedFields", bfl.this.q.nf());
            var2.q("kindTag", bfl.this.q.nf());
            if (!bfl.this.q.PV) {
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
         var1.q("name", bfl.this.q.nf());
         var1.q("owner", bfl.this.q.nf());
         var1.q("signature", bfl.this.q.nf());
         var1.q("data", bfl.this.q.nf());
      }
   }

   class zJ extends bfl.oM {
      @Override
      public void q() {
         this.RF(bfk.zx);
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfl.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bfl.this.q.lm());
            var2.q("nullabity", bfl.this.q.nf());
            var2.q("instantiations", bfl.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bfl.this.q.Uv());
            }
         }
      }
   }
}
