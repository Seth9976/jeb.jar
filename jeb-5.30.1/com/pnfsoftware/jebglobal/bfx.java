package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bfx {
   private static final ILogger RF = GlobalLog.getLogger(bfx.class);
   bfz q;

   bfx(bfz var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bfx.Bu q(int var1, boolean var2, boolean var3) {
      bfw var4 = bfw.q(var1);
      if (var1 >= bfw.Dk.q() || var1 == bfw.Rv.q()) {
         return new bfx.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bfx.qx(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case KT:
               case Gf:
               case Ef:
                  return new bfx.eM(var2, var3, var1);
               case of:
               case os:
               case vC:
                  if (var3) {
                     return new bfx.eM(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case KT:
               Assert.a(!var2);
               return new bfx.KZ();
            case Gf:
               Assert.a(!var2);
               return new bfx.iZ();
            case Ef:
               Assert.a(!var2);
               return new bfx.tw();
            case of:
            case os:
            default:
               return null;
            case vC:
               return new bfx.LR(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bfx.CU();
            case gP:
               return new bfx.ct();
            case ZT:
               return new bfx.ME(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bfx.zJ();
            case nf:
               Assert.a(!var2);
               return new bfx.vb();
            case za:
               Assert.a(!var2);
               return new bfx.nI();
            case lm:
               Assert.a(!var2);
               return new bfx.vn();
            case zz:
               Assert.a(!var2);
               return new bfx.PY();
            case JY:
               Assert.a(!var2);
               return new bfx.HA();
            case HF:
               Assert.a(!var2);
               return new bfx.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Hk:
               Assert.a(!var2);
               return new bfx.Nt();
            case xW:
               Assert.a(!var2);
               return new bfx.vX();
            case sH:
               Assert.a(!var2);
               return new bfx.qV();
            case CE:
               Assert.a(!var2);
               return null;
            case wF:
               Assert.a(!var2);
               return null;
            case mI:
               Assert.a(!var2);
               return new bfx.FL();
            case TX:
               Assert.a(!var2);
               return null;
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return new bfx.qa();
            case Xo:
               Assert.a(!var2);
               return new bfx.kY();
            case rL:
               Assert.a(!var2);
               return null;
            case eJ:
               Assert.a(!var2);
               return null;
            case zx:
               Assert.a(!var2);
               return null;
            case GY:
               return new bfx.SG(var2, var3);
            case Tq:
               return new bfx.oL(var2, var3);
            case Yp:
               Assert.a(!var2);
               return new bfx.GA();
            case Gu:
               return new bfx.Fw(var2, var3);
            case nY:
               return new bfx.ej(var2);
            case br:
               return new bfx.CI(var2);
            case tW:
               return new bfx.EE(var2);
            case Jf:
               Assert.a(!var2);
               return new bfx.Vj();
            case TQ:
               Assert.a(!var2);
               return null;
            case Yw:
               Assert.a(!var2);
               return null;
            case IY:
               Assert.a(!var2);
               return null;
            case PY:
               throw new RuntimeException();
            case cR:
               return new bfx.Nz(var2, var1);
            case eC:
               throw new RuntimeException();
            case ND:
               return new bfx.Uz(var2, var1);
            case Qu:
            case jh:
               return new bfx.eo(var2, var1);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bfx.oM {
      CI(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bfx.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bfx.this.q.q(bfw.br.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bfx.this.q.za());
            bfx.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class CU extends bfx.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bfx.this.q.jq;
         long var1 = bfx.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bfx.this.q.q(bfw.oW.q());
            var2.q("id", bfx.this.q.oW());
            bfx.this.q.q(var2);
         });
         this.RF = bfx.this.q.jq;
         this.q(bfw.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bfx.this.q.xK(var6);
               this.q(var8);
               int var9 = bfx.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bfx.this.q.PV && !bfx.this.q.io) {
                  var8.q("kernelOffset", bfx.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bfx.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bfx.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bfx.this.q.za());
               if (!bfx.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bfx.this.q.za());
               var8.q("numNativeFields", bfx.this.q.nf());
               if (!bfx.this.q.PV) {
                  Assert.a(!bfx.this.q.io);
                  var8.q("tokenPos", bfx.this.q.gO());
                  var8.q("endTokenPos", bfx.this.q.gO());
               }

               var8.q("stateBits", bfx.this.q.nf());
               if (bfx.this.q.PV) {
                  if (var7) {
                     bfx.this.q.nf();
                  } else if (!bfx.this.q.gP.q(var9)) {
                     bfx.this.q.TX.put(var9, bfx.this.q.nf());
                  }
               }

               bfx.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfx.this.q.nf());
         if (!bfx.this.q.Me) {
            var1.q("userName", bfx.this.q.nf());
         }

         var1.q("functions", bfx.this.q.nf());
         var1.q("functionsHashTable", bfx.this.q.nf());
         var1.q("fields", bfx.this.q.nf());
         var1.q("offsetInWordsToField", bfx.this.q.nf());
         var1.q("interfaces", bfx.this.q.nf());
         var1.q("script", bfx.this.q.nf());
         var1.q("library", bfx.this.q.nf());
         var1.q("typeParameters", bfx.this.q.nf());
         var1.q("superType", bfx.this.q.nf());
         var1.q("constants", bfx.this.q.nf());
         var1.q("declarationType", bfx.this.q.nf());
         var1.q("invocationDispatcherCache", bfx.this.q.nf());
         if (!bfx.this.q.Me || !bfx.this.q.PV) {
            var1.q("directImplementors", bfx.this.q.nf());
            var1.q("directSubclasses", bfx.this.q.nf());
         }

         if (!bfx.this.q.PV) {
            var1.q("allocationStub", bfx.this.q.nf());
            var1.q("dependentCode", bfx.this.q.nf());
         }
      }
   }

   class EE extends bfx.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfw.tW.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bfx.this.q.za());
         }
      }
   }

   class FL extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.mI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bfx.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfx.this.q.nf());
         var1.q("argsDescriptor", bfx.this.q.nf());
      }
   }

   class Fw extends bfx.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfw.Gu.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bfx.this.q.qa());
            var2.q("base", bfx.this.q.zz());
            var2.q("index", bfx.this.q.zz());
            long var3 = bfx.this.q.zz();
            var2.q("typeState", var3 >> (int)bfx.this.q.gP.Rr);
            var2.q("nullability", var3 & bfx.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfx.this.q.nf());
         var1.q("hash", bfx.this.q.nf());
         var1.q("bound", bfx.this.q.nf());
      }
   }

   class GA extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.Yp.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfx.this.q.nf());
         var1.q("type", bfx.this.q.nf());
      }
   }

   class HA extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            if (!bfx.this.q.PV) {
               var2.q("flagsAndMaxPosition", bfx.this.q.qa());
            }

            var2.q("kernelScriptIndex", bfx.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bfx.this.q.nf());
         if (bfx.this.q.io) {
            if (!bfx.this.q.Me) {
               var1.q("resolvedUrl", bfx.this.q.nf());
            }
         } else {
            var1.q("resolvedUrl", bfx.this.q.nf());
            var1.q("resolvedUrl", bfx.this.q.nf());
            var1.q("lineStarts", bfx.this.q.nf());
            var1.q("constantCoverage", bfx.this.q.nf());
            var1.q("debugPositions", bfx.this.q.nf());
            var1.q("kernelProgramInfo", bfx.this.q.nf());
         }
      }
   }

   class KZ extends bfx.oM {
      @Override
      public void q() {
         this.RF(bfw.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bfx.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class LR extends bfx.oM {
      LR(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bfx.this.q.jq;
         long var1 = bfx.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bfx.this.q.nf();
            bfw var6 = (var4 & 1L) != 0L ? bfw.os : bfw.of;
            long var7 = var4 >> 1;
            bew var9 = bfx.this.q.q(var6.q());
            var9.q("length", var7);
            bfx.this.q.q(var9);
         }

         this.gO = bfx.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            long var3 = bfx.this.q.nf();
            long var5 = var3 >> 1;
            bfw var7 = (var3 & 1L) != 0L ? bfw.os : bfw.of;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bfw.of) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bfx.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bfx.this.q.zz();
                  var10 |= bfx.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class ME extends bfx.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bfw.ZT.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bfx.this.q.lm());
            var2.q("nullabity", bfx.this.q.nf());
            var2.q("instantiations", bfx.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bfx.this.q.Uv());
            }
         }
      }
   }

   class Nt extends bfx.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bfx.this.q.jq;
         bfx.this.q.GY = this.oW;
         long var1 = bfx.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bfx.this.q.jq;
         this.q = bfx.this.q.jq;
         var1 = bfx.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bfx.this.q.jq;
      }

      private void Dw() {
         int var1 = bfx.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bfx.this.q.q(bfw.Hk.q());
         bfx.this.q.q(var3);
         var3.q("stateBits", var1);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bfx.this.q.xK(var1);
         this.q(var3, var2);
         if (!bfx.this.q.io) {
            var3.q("objectPool", bfx.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bfx.this.q.Uv(), bfw.nf.toString(), bfw.oW.toString(), bfw.GY.toString());
         var3.q("exceptionHandlers", bfx.this.q.Uv(), bfw.sH.toString());
         var3.q("pcDescriptors", bfx.this.q.Uv(), bfw.KT.toString());
         var3.q("catchEntry", bfx.this.q.Uv());
         if (bfx.this.q.LK) {
            var3.q("compressedStackMaps", bfx.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bfx.this.q.Uv(), bfw.Qu.toString());
         var3.q("codeSourceMap", bfx.this.q.Uv(), bfw.Gf.toString());
         if (!bfx.this.q.PV && bfx.this.q.LK) {
            var3.q("deoptInfoArray", bfx.this.q.Uv());
            var3.q("staticCallsTargetTable", bfx.this.q.Uv());
         }

         if (!bfx.this.q.Me) {
            var3.q("returnAddressMetadata", bfx.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bfx.this.q.Gf ? bfx.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bfx.this.q.PV) {
            long var3 = bfx.this.q.zx[2 * bfx.this.q.Ri];
            long var5 = bfx.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bfx.this.q.gP.oQ : 0L;
            long var12 = var9 ? bfx.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bfx.this.q.Ri++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bfx.oM {
      int q;

      Nz(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bfx.this.q.nf());
         var1.q("hashMask", bfx.this.q.nf());
         var1.q("data", bfx.this.q.nf());
         var1.q("usedData", bfx.this.q.nf());
         var1.q("deletedKeys", bfx.this.q.nf());
      }
   }

   class PY extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            if (!bfx.this.q.io) {
               var2.q("guardedListLength", bfx.this.q.Uv());
            }

            if (bfx.this.q.LK) {
               var2.q("dependentCode", bfx.this.q.Uv());
            }

            if (!bfx.this.q.io) {
               var2.q("tokenPos", bfx.this.q.gO());
               var2.q("endTokenPos", bfx.this.q.gO());
               var2.q("guardedCid", bfx.this.q.oW());
               var2.q("isNullable", bfx.this.q.oW());
               var2.q("staticTypeExactnessState", bfx.this.q.JY());
               if (!bfx.this.q.PV) {
                  var2.q("kernelOffset", bfx.this.q.gP());
               }
            }

            int var3 = bfx.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bfx.this.q.Uv();
            if (bfx.this.q.Uv(var3)) {
               long var6 = bfx.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bfx.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfx.this.q.nf());
         var1.q("owner", bfx.this.q.nf());
         var1.q("type", bfx.this.q.nf());
         var1.q("initializerFunction", bfx.this.q.nf());
      }
   }

   class SG extends bfx.oM {
      SG(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfw.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeClassId", bfx.this.q.nf());
            long var3 = bfx.this.q.zz();
            var2.q("typeState", var3 >> (int)bfx.this.q.gP.Rr);
            var2.q("nullability", var3 & bfx.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfx.this.q.nf());
         var1.q("arguments", bfx.this.q.nf());
         var1.q("hash", bfx.this.q.nf());
      }
   }

   class Uz extends bfx.oM {
      int q;

      Uz(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bfx.this.q.nf());
         var1.q("hashMask", bfx.this.q.nf());
         var1.q("data", bfx.this.q.nf());
         var1.q("usedData", bfx.this.q.nf());
         var1.q("deletedKeys", bfx.this.q.nf());
      }
   }

   class Vj extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.Jf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bfx.this.q.nf());
         var1.q("length", bfx.this.q.nf());
         var1.q("data", bfx.this.q.nf());
      }
   }

   class Xa extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.Rr.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bfx.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfx.this.q.nf());
         var1.q("argsDescriptor", bfx.this.q.nf());
         var1.q("buckets", bfx.this.q.nf());
         var1.q("mask", bfx.this.q.nf());
      }
   }

   class bK extends bfx.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bfx.this.q.jq;
         long var1 = bfx.this.q.nf();
         this.RF = bfx.this.q.qa();
         this.xK = bfx.this.q.qa();
         bfx.this.q.q(var1, this.q);
         this.gO = bfx.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bfx.this.q.gP.nf;
         int var2 = (int)bfx.this.q.q(this.xK * bfx.this.q.gP.gO, bfx.this.q.gP.gP);
         Long var3 = bfx.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bfx.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bfx.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bfx.this.q.gP.gO)) {
               if (bfx.this.q.RF(var3, var6 / (int)bfx.this.q.gP.gO)) {
                  bfx.this.q.oQ();
               } else {
                  bfx.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bfx.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.Gu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bfx.this.q.nf());
         var1.q("flags", bfx.this.q.nf());
         var1.q("bounds", bfx.this.q.nf());
         var1.q("defaults", bfx.this.q.nf());
      }
   }

   class eM extends bfx.oM {
      int q;

      eM(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bfx.this.q.jq;
         long var1 = bfx.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bfx.this.q.nf() << (int)bfx.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bfx.this.q.q(this.q);
            var7.q("data", var6);
            bfx.this.q.q(var7);
         }

         this.gO = bfx.this.q.jq;
         if (this.q == bfw.vC.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bfw.of.q() && this.q != bfw.vC.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bfx.this.q.gO.position(var1);
            bfx.this.q.gO.i32();
            long var2;
            if (bfx.this.q.wF) {
               bfx.this.q.gO.i32();
               var2 = bfx.this.q.gO.i64();
            } else {
               var2 = bfx.this.q.gO.i32();
               bfx.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bfx.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class ej extends bfx.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfw.nY.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bfx.this.q.nf());
         var1.q("functionTypeArguments", bfx.this.q.nf());
         var1.q("delayedTypeArguments", bfx.this.q.nf());
         var1.q("function", bfx.this.q.nf());
         var1.q("context", bfx.this.q.nf());
         var1.q("hash", bfx.this.q.nf());
      }
   }

   class eo extends bfx.oM {
      int q;

      eo(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bfx.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bfx.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bfx.oM {
      @Override
      public void q() {
         this.RF(bfw.Gf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bfx.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class kY extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("parent", bfx.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bfx.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class nI extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            if (bfx.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bfx.this.q.Uv());
            }

            var2.q("parentFunction", bfx.this.q.Uv());
            var2.q("closure", bfx.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bfx.this.q.nf());
         }
      }
   }

   class oL extends bfx.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfw.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfx.this.q.zz();
            var2.q("typeState", var3 >> (int)bfx.this.q.gP.Rr);
            var2.q("nullability", var3 & bfx.this.q.gP.EB);
            var2.q("packedParameterCounts", bfx.this.q.io());
            var2.q("packedTypeParameterCounts", bfx.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfx.this.q.nf());
         var1.q("typeParameters", bfx.this.q.nf());
         var1.q("resultType", bfx.this.q.nf());
         var1.q("parameterTypes", bfx.this.q.nf());
         var1.q("namedParameterNames", bfx.this.q.nf());
         var1.q("hash", bfx.this.q.nf());
      }
   }

   abstract class oM implements bfx.Bu {
      boolean Dw;
      boolean Uv;
      int oW;
      int gO;

      @Override
      public void q() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void RF() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void q(int var1) {
         this.oW = bfx.this.q.jq;
         long var2 = bfx.this.q.nf();
         bfx.this.q.q(var2, var1);
         this.gO = bfx.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bfx.this.q.jq;
         long var2 = bfx.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bfx.this.q.q(var1);
            long var4 = bfx.this.q.nf();
            var3.q("length", var4);
            bfx.this.q.q(var3);
         });
         this.gO = bfx.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bfx.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bfx.this.q.nf();
            long var1 = bfx.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bfx.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bfx.oM {
      @Override
      public void q() {
         this.RF(bfw.sH.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bfx.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bfx.this.q.gP());
               var6.put("outerTryIndex", bfx.this.q.LK());
               var6.put("needsStacktrace", bfx.this.q.Dw());
               var6.put("hasCatchAll", bfx.this.q.Dw());
               var6.put("isGenerated", bfx.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class qa extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.EB.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            var2.q("cache", bfx.this.q.Uv());
         }
      }
   }

   class qx extends bfx.oM {
      int q;

      public qx(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bfx.this.q.HF(this.q);
            var2.q("data", bfx.this.q.Dw(var4));
         }
      }
   }

   class ry extends bfx.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class tl extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bfx.this.q.qa());
            var2.q("numImports", bfx.this.q.HF());
            var2.q("loadState", bfx.this.q.JY());
            var2.q("flags", bfx.this.q.zz());
            if (!bfx.this.q.PV && !bfx.this.q.io) {
               var2.q("kernelOffset", bfx.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfx.this.q.nf());
         var1.q("url", bfx.this.q.nf());
         var1.q("privateKey", bfx.this.q.nf());
         var1.q("dictionary", bfx.this.q.nf());
         var1.q("metadata", bfx.this.q.nf());
         var1.q("toplevelClass", bfx.this.q.nf());
         var1.q("usedScripts", bfx.this.q.nf());
         var1.q("loadingUnit", bfx.this.q.nf());
         var1.q("imports", bfx.this.q.nf());
         var1.q("exports", bfx.this.q.nf());
         if (!bfx.this.q.io) {
            var1.q("dependencies", bfx.this.q.nf());
            var1.q("kernelData", bfx.this.q.nf());
         }
      }
   }

   class tw extends bfx.oM {
      @Override
      public void q() {
         this.RF(bfw.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            long var3 = bfx.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bfx.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bfx.oM {
      @Override
      public void q() {
         this.RF(bfw.xW.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bfx.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bfx.this.q.oW(var6);
               if (var8 == bfx.this.q.gP.xW) {
                  var7.put("rawObj", bfx.this.q.Uv());
               } else if (var8 == bfx.this.q.gP.KT) {
                  var7.put("rawValue", bfx.this.q.za());
               } else if (var8 == bfx.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bfx.this.q.gP.sH && var8 != bfx.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bfx.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class vb extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            if (!bfx.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bfx.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bfx.this.q.GY + (int)var3 - 1;
               bew var6 = bfx.this.q.xK(var5);
               if (var6.q().equals(bfw.Hk.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bfx.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("packedFields", bfx.this.q.nf());
            var2.q("kindTag", bfx.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bfx.this.q.nf());
         var1.q("owner", bfx.this.q.nf());
         var1.q("signature", bfx.this.q.nf(), bfw.Tq.toString());
         var1.q("data", bfx.this.q.nf());
      }
   }

   class vn extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bfx.this.q.io ? bfx.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bfx.this.q.nf());
         var1.q("cSignature", bfx.this.q.nf());
         var1.q("callbackTarget", bfx.this.q.nf());
         var1.q("callbackExceptionalReturn", bfx.this.q.nf());
      }
   }

   class zJ extends bfx.oM {
      @Override
      public void q() {
         this.q(bfw.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfx.this.q.xK(var1);
            this.q(var2);
            if (!bfx.this.q.PV && !bfx.this.q.io) {
               var2.q("libraryKernelOffset", bfx.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bfx.this.q.nf());
         var1.q("originClass", bfx.this.q.nf());
         var1.q("script", bfx.this.q.nf());
         if (!bfx.this.q.io) {
            var1.q("libraryKernelData", bfx.this.q.nf());
         }
      }
   }
}
