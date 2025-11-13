package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgb {
   private static final ILogger RF = GlobalLog.getLogger(bgb.class);
   bgd q;

   bgb(bgd var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgb.Bu q(int var1, boolean var2, boolean var3) {
      bga var4 = bga.q(var1);
      if (var1 >= bga.dS.q() || var1 == bga.Rv.q()) {
         return new bgb.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgb.qx(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case KT:
               case Gf:
               case Ef:
                  return new bgb.eM(var2, var3, var1);
               case os:
               case iu:
               case of:
                  if (var3) {
                     return new bgb.eM(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case KT:
               Assert.a(!var2);
               return new bgb.KZ();
            case Gf:
               Assert.a(!var2);
               return new bgb.iZ();
            case Ef:
               Assert.a(!var2);
               return new bgb.tw();
            case os:
            case iu:
            default:
               return null;
            case of:
               return new bgb.LR(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgb.CU();
            case gP:
               return new bgb.ct();
            case ZT:
               return new bgb.ME(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgb.zJ();
            case nf:
               Assert.a(!var2);
               return new bgb.vb();
            case za:
               Assert.a(!var2);
               return new bgb.nI();
            case lm:
               Assert.a(!var2);
               return new bgb.vn();
            case zz:
               Assert.a(!var2);
               return new bgb.PY();
            case JY:
               Assert.a(!var2);
               return new bgb.HA();
            case HF:
               Assert.a(!var2);
               return new bgb.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Hk:
               Assert.a(!var2);
               return new bgb.Nt();
            case xW:
               Assert.a(!var2);
               return new bgb.vX();
            case sH:
               Assert.a(!var2);
               return new bgb.qV();
            case CE:
               Assert.a(!var2);
               return null;
            case wF:
               Assert.a(!var2);
               return null;
            case mI:
               Assert.a(!var2);
               return new bgb.FL();
            case TX:
               Assert.a(!var2);
               return null;
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return new bgb.qa();
            case Xo:
               Assert.a(!var2);
               return new bgb.kY();
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
               return new bgb.SG(var2, var3);
            case Tq:
               return new bgb.oL(var2, var3);
            case Yp:
               Assert.a(!var2);
               return new bgb.GA();
            case Gu:
               return new bgb.Fw(var2, var3);
            case nY:
               return new bgb.ej(var2);
            case br:
               return new bgb.CI(var2);
            case tW:
               return new bgb.EE(var2);
            case vC:
               Assert.a(!var2);
               return new bgb.Vj();
            case TQ:
               Assert.a(!var2);
               return null;
            case IY:
               Assert.a(!var2);
               return null;
            case qR:
               Assert.a(!var2);
               return null;
            case cR:
               throw new RuntimeException();
            case eC:
               return new bgb.Nz(var2, var1);
            case ND:
               throw new RuntimeException();
            case Qu:
               return new bgb.Uz(var2, var1);
            case jh:
            case Jf:
               return new bgb.eo(var2, var1);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgb.oM {
      CI(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgb.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgb.this.q.q(bga.br.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgb.this.q.za());
            bgb.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class CU extends bgb.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgb.this.q.jq;
         long var1 = bgb.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgb.this.q.q(bga.oW.q());
            var2.q("id", bgb.this.q.oW());
            bgb.this.q.q(var2);
         });
         this.RF = bgb.this.q.jq;
         this.q(bga.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgb.this.q.xK(var6);
               this.q(var8);
               int var9 = bgb.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgb.this.q.PV && !bgb.this.q.io) {
                  var8.q("kernelOffset", bgb.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgb.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgb.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgb.this.q.za());
               if (!bgb.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgb.this.q.za());
               var8.q("numNativeFields", bgb.this.q.nf());
               if (!bgb.this.q.PV) {
                  Assert.a(!bgb.this.q.io);
                  var8.q("tokenPos", bgb.this.q.gO());
                  var8.q("endTokenPos", bgb.this.q.gO());
               }

               var8.q("stateBits", bgb.this.q.nf());
               if (bgb.this.q.PV) {
                  if (var7) {
                     bgb.this.q.nf();
                  } else if (!bgb.this.q.gP.q(var9)) {
                     bgb.this.q.TX.put(var9, bgb.this.q.nf());
                  }
               }

               bgb.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgb.this.q.Uv());
         if (!bgb.this.q.Me) {
            var1.q("userName", bgb.this.q.Uv());
         }

         var1.q("functions", bgb.this.q.Uv());
         var1.q("functionsHashTable", bgb.this.q.Uv());
         var1.q("fields", bgb.this.q.Uv());
         var1.q("offsetInWordsToField", bgb.this.q.Uv());
         var1.q("interfaces", bgb.this.q.Uv());
         var1.q("script", bgb.this.q.Uv());
         var1.q("library", bgb.this.q.Uv());
         var1.q("typeParameters", bgb.this.q.Uv());
         var1.q("superType", bgb.this.q.Uv());
         var1.q("constants", bgb.this.q.Uv());
         var1.q("declarationType", bgb.this.q.Uv());
         var1.q("invocationDispatcherCache", bgb.this.q.Uv());
         if (!bgb.this.q.Me || !bgb.this.q.PV) {
            var1.q("directImplementors", bgb.this.q.Uv());
            var1.q("directSubclasses", bgb.this.q.Uv());
         }

         if (!bgb.this.q.PV) {
            var1.q("allocationStub", bgb.this.q.Uv());
            var1.q("dependentCode", bgb.this.q.Uv());
         }
      }
   }

   class EE extends bgb.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bga.tW.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgb.this.q.za());
         }
      }
   }

   class FL extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.mI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgb.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgb.this.q.Uv());
         var1.q("argsDescriptor", bgb.this.q.Uv());
      }
   }

   class Fw extends bgb.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bga.Gu.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bgb.this.q.qa());
            var2.q("base", bgb.this.q.zz());
            var2.q("index", bgb.this.q.zz());
            long var3 = bgb.this.q.zz();
            var2.q("typeState", var3 >> (int)bgb.this.q.gP.Rr);
            var2.q("nullability", var3 & bgb.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgb.this.q.Uv());
         var1.q("hash", bgb.this.q.Uv());
         var1.q("bound", bgb.this.q.Uv());
      }
   }

   class GA extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.Yp.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgb.this.q.Uv());
         var1.q("type", bgb.this.q.Uv());
      }
   }

   class HA extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            if (!bgb.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgb.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgb.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgb.this.q.Uv());
         if (bgb.this.q.io) {
            if (!bgb.this.q.Me) {
               var1.q("resolvedUrl", bgb.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgb.this.q.Uv());
            var1.q("resolvedUrl", bgb.this.q.Uv());
            var1.q("lineStarts", bgb.this.q.Uv());
            var1.q("constantCoverage", bgb.this.q.Uv());
            var1.q("debugPositions", bgb.this.q.Uv());
            var1.q("kernelProgramInfo", bgb.this.q.Uv());
         }
      }
   }

   class KZ extends bgb.oM {
      @Override
      public void q() {
         this.RF(bga.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgb.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class LR extends bgb.oM {
      LR(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgb.this.q.jq;
         long var1 = bgb.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgb.this.q.nf();
            bga var6 = (var4 & 1L) != 0L ? bga.iu : bga.os;
            long var7 = var4 >> 1;
            bew var9 = bgb.this.q.q(var6.q());
            var9.q("length", var7);
            bgb.this.q.q(var9);
         }

         this.gO = bgb.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            long var3 = bgb.this.q.nf();
            long var5 = var3 >> 1;
            bga var7 = (var3 & 1L) != 0L ? bga.iu : bga.os;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bga.os) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgb.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgb.this.q.zz();
                  var10 |= bgb.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class ME extends bgb.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bga.ZT.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgb.this.q.lm());
            var2.q("nullabity", bgb.this.q.nf());
            var2.q("instantiations", bgb.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgb.this.q.Uv());
            }
         }
      }
   }

   class Nt extends bgb.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgb.this.q.jq;
         bgb.this.q.Ri = this.oW;
         long var1 = bgb.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgb.this.q.jq;
         this.q = bgb.this.q.jq;
         var1 = bgb.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgb.this.q.jq;
      }

      private void Dw() {
         int var1 = bgb.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgb.this.q.q(bga.Hk.q());
         bgb.this.q.q(var3);
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
         bew var3 = bgb.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgb.this.q.io) {
            var3.q("objectPool", bgb.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgb.this.q.Uv(), bga.nf.toString(), bga.oW.toString(), bga.GY.toString());
         var3.q("exceptionHandlers", bgb.this.q.Uv(), bga.sH.toString());
         var3.q("pcDescriptors", bgb.this.q.Uv(), bga.KT.toString());
         var3.q("catchEntry", bgb.this.q.Uv());
         if (bgb.this.q.LK) {
            var3.q("compressedStackMaps", bgb.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgb.this.q.Uv(), bga.jh.toString());
         var3.q("codeSourceMap", bgb.this.q.Uv(), bga.Gf.toString());
         if (!bgb.this.q.PV && bgb.this.q.LK) {
            var3.q("deoptInfoArray", bgb.this.q.Uv());
            var3.q("staticCallsTargetTable", bgb.this.q.Uv());
         }

         if (!bgb.this.q.Me) {
            var3.q("returnAddressMetadata", bgb.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgb.this.q.Gf ? bgb.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgb.this.q.PV) {
            long var3 = bgb.this.q.Rv[2 * bgb.this.q.ZT];
            long var5 = bgb.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgb.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgb.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgb.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgb.oM {
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
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgb.this.q.Uv());
         var1.q("hashMask", bgb.this.q.Uv());
         var1.q("data", bgb.this.q.Uv());
         var1.q("usedData", bgb.this.q.Uv());
         var1.q("deletedKeys", bgb.this.q.Uv());
      }
   }

   class PY extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgb.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgb.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgb.this.q.Uv());
         var1.q("owner", bgb.this.q.Uv());
         var1.q("type", bgb.this.q.Uv());
         var1.q("initializerFunction", bgb.this.q.Uv());
      }
   }

   class SG extends bgb.oM {
      SG(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bga.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeClassId", bgb.this.q.nf());
            long var3 = bgb.this.q.zz();
            var2.q("typeState", var3 >> (int)bgb.this.q.gP.Rr);
            var2.q("nullability", var3 & bgb.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgb.this.q.Uv());
         var1.q("arguments", bgb.this.q.Uv());
         var1.q("hash", bgb.this.q.Uv());
      }
   }

   class Uz extends bgb.oM {
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
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgb.this.q.Uv());
         var1.q("hashMask", bgb.this.q.Uv());
         var1.q("data", bgb.this.q.Uv());
         var1.q("usedData", bgb.this.q.Uv());
         var1.q("deletedKeys", bgb.this.q.Uv());
      }
   }

   class Vj extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.vC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgb.this.q.Uv());
         var1.q("length", bgb.this.q.Uv());
         var1.q("data", bgb.this.q.Uv());
      }
   }

   class Xa extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.Rr.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgb.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgb.this.q.Uv());
         var1.q("argsDescriptor", bgb.this.q.Uv());
         var1.q("buckets", bgb.this.q.Uv());
         var1.q("mask", bgb.this.q.Uv());
      }
   }

   class bK extends bgb.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgb.this.q.jq;
         long var1 = bgb.this.q.nf();
         this.RF = bgb.this.q.qa();
         this.xK = bgb.this.q.qa();
         bgb.this.q.q(var1, this.q);
         this.gO = bgb.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgb.this.q.gP.nf;
         int var2 = (int)bgb.this.q.q(this.xK * bgb.this.q.gP.gO, bgb.this.q.gP.gP);
         Long var3 = bgb.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgb.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgb.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgb.this.q.gP.gO)) {
               if (bgb.this.q.RF(var3, var6 / (int)bgb.this.q.gP.gO)) {
                  bgb.this.q.oQ();
               } else {
                  bgb.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgb.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.Gu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgb.this.q.Uv());
         var1.q("flags", bgb.this.q.Uv());
         var1.q("bounds", bgb.this.q.Uv());
         var1.q("defaults", bgb.this.q.Uv());
      }
   }

   class eM extends bgb.oM {
      int q;

      eM(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgb.this.q.jq;
         long var1 = bgb.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgb.this.q.nf() << (int)bgb.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgb.this.q.q(this.q);
            var7.q("data", var6);
            bgb.this.q.q(var7);
         }

         this.gO = bgb.this.q.jq;
         if (this.q == bga.of.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bga.os.q() && this.q != bga.of.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgb.this.q.gO.position(var1);
            bgb.this.q.gO.i32();
            long var2;
            if (bgb.this.q.wF) {
               bgb.this.q.gO.i32();
               var2 = bgb.this.q.gO.i64();
            } else {
               var2 = bgb.this.q.gO.i32();
               bgb.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgb.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class ej extends bgb.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bga.nY.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgb.this.q.Uv());
         var1.q("functionTypeArguments", bgb.this.q.Uv());
         var1.q("delayedTypeArguments", bgb.this.q.Uv());
         var1.q("function", bgb.this.q.Uv());
         var1.q("context", bgb.this.q.Uv());
         var1.q("hash", bgb.this.q.Uv());
      }
   }

   class eo extends bgb.oM {
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
            bew var2 = bgb.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgb.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgb.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgb.oM {
      @Override
      public void q() {
         this.RF(bga.Gf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgb.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class kY extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("parent", bgb.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgb.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class nI extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            if (bgb.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgb.this.q.Uv());
            }

            var2.q("parentFunction", bgb.this.q.Uv());
            var2.q("closure", bgb.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgb.this.q.nf());
         }
      }
   }

   class oL extends bgb.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bga.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgb.this.q.zz();
            var2.q("typeState", var3 >> (int)bgb.this.q.gP.Rr);
            var2.q("nullability", var3 & bgb.this.q.gP.EB);
            var2.q("packedParameterCounts", bgb.this.q.io());
            var2.q("packedTypeParameterCounts", bgb.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgb.this.q.Uv());
         var1.q("typeParameters", bgb.this.q.Uv());
         var1.q("resultType", bgb.this.q.Uv());
         var1.q("parameterTypes", bgb.this.q.Uv());
         var1.q("namedParameterNames", bgb.this.q.Uv());
         var1.q("hash", bgb.this.q.Uv());
      }
   }

   abstract class oM implements bgb.Bu {
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
         this.oW = bgb.this.q.jq;
         long var2 = bgb.this.q.nf();
         bgb.this.q.q(var2, var1);
         this.gO = bgb.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgb.this.q.jq;
         long var2 = bgb.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgb.this.q.q(var1);
            long var4 = bgb.this.q.nf();
            var3.q("length", var4);
            bgb.this.q.q(var3);
         });
         this.gO = bgb.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgb.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgb.this.q.nf();
            long var1 = bgb.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgb.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgb.oM {
      @Override
      public void q() {
         this.RF(bga.sH.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            long var3 = bgb.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgb.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgb.this.q.gP());
               var9.put("outerTryIndex", bgb.this.q.LK());
               var9.put("needsStacktrace", bgb.this.q.Dw());
               var9.put("hasCatchAll", bgb.this.q.Dw());
               var9.put("isGenerated", bgb.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.EB.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            var2.q("cache", bgb.this.q.Uv());
         }
      }
   }

   class qx extends bgb.oM {
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
            bew var2 = bgb.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgb.this.q.HF(this.q);
            var2.q("data", bgb.this.q.Dw(var4));
         }
      }
   }

   class ry extends bgb.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class tl extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgb.this.q.qa());
            var2.q("numImports", bgb.this.q.HF());
            var2.q("loadState", bgb.this.q.JY());
            var2.q("flags", bgb.this.q.zz());
            if (!bgb.this.q.PV && !bgb.this.q.io) {
               var2.q("kernelOffset", bgb.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgb.this.q.Uv());
         var1.q("url", bgb.this.q.Uv());
         var1.q("privateKey", bgb.this.q.Uv());
         var1.q("dictionary", bgb.this.q.Uv());
         var1.q("metadata", bgb.this.q.Uv());
         var1.q("toplevelClass", bgb.this.q.Uv());
         var1.q("usedScripts", bgb.this.q.Uv());
         var1.q("loadingUnit", bgb.this.q.Uv());
         var1.q("imports", bgb.this.q.Uv());
         var1.q("exports", bgb.this.q.Uv());
         if (!bgb.this.q.io) {
            var1.q("dependencies", bgb.this.q.Uv());
            var1.q("kernelData", bgb.this.q.Uv());
         }
      }
   }

   class tw extends bgb.oM {
      @Override
      public void q() {
         this.RF(bga.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            long var3 = bgb.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgb.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgb.oM {
      @Override
      public void q() {
         this.RF(bga.xW.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgb.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bgb.this.q.oW(var6);
               if (var8 == bgb.this.q.gP.xW) {
                  var7.put("rawObj", bgb.this.q.Uv());
               } else if (var8 == bgb.this.q.gP.KT) {
                  var7.put("rawValue", bgb.this.q.za());
               } else if (var8 == bgb.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bgb.this.q.gP.sH && var8 != bgb.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bgb.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class vb extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            if (!bgb.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgb.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgb.this.q.Ri + (int)var3 - 1;
               bew var6 = bgb.this.q.xK(var5);
               if (var6.q().equals(bga.Hk.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgb.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgb.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgb.this.q.Uv());
         var1.q("owner", bgb.this.q.Uv());
         var1.q("signature", bgb.this.q.Uv(), bga.Tq.toString());
         var1.q("data", bgb.this.q.Uv());
      }
   }

   class vn extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgb.this.q.io ? bgb.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgb.this.q.Uv());
         var1.q("cSignature", bgb.this.q.Uv());
         var1.q("callbackTarget", bgb.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgb.this.q.Uv());
      }
   }

   class zJ extends bgb.oM {
      @Override
      public void q() {
         this.q(bga.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgb.this.q.xK(var1);
            this.q(var2);
            if (!bgb.this.q.PV && !bgb.this.q.io) {
               var2.q("libraryKernelOffset", bgb.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bgb.this.q.Uv());
         var1.q("originClass", bgb.this.q.Uv());
         var1.q("script", bgb.this.q.Uv());
         if (!bgb.this.q.io) {
            var1.q("libraryKernelData", bgb.this.q.Uv());
         }
      }
   }
}
