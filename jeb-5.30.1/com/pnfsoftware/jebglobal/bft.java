package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bft {
   private static final ILogger RF = GlobalLog.getLogger(bft.class);
   bfv q;

   bft(bfv var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bft.Bu q(int var1, boolean var2, boolean var3) {
      bfs var4 = bfs.q(var1);
      if (var1 >= bfs.zm.q() || var1 == bfs.Rv.q()) {
         return new bft.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bft.qx(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case KT:
               case Gf:
               case Ef:
                  return new bft.eM(var2, var3, var1);
               case ND:
               case Qu:
               case eC:
                  if (var3) {
                     return new bft.eM(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case KT:
               Assert.a(!var2);
               return new bft.KZ();
            case Gf:
               Assert.a(!var2);
               return new bft.iZ();
            case Ef:
               Assert.a(!var2);
               return new bft.tw();
            case ND:
            case Qu:
            default:
               return null;
            case eC:
               return new bft.LR(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bft.CU();
            case gP:
               return new bft.ct();
            case ZT:
               return new bft.ME(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bft.zJ();
            case nf:
               Assert.a(!var2);
               return new bft.vb();
            case za:
               Assert.a(!var2);
               return new bft.nI();
            case lm:
               Assert.a(!var2);
               return new bft.vn();
            case zz:
               Assert.a(!var2);
               return new bft.PY();
            case JY:
               Assert.a(!var2);
               return new bft.HA();
            case HF:
               Assert.a(!var2);
               return new bft.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Hk:
               Assert.a(!var2);
               return new bft.Nt();
            case xW:
               Assert.a(!var2);
               return new bft.vX();
            case sH:
               Assert.a(!var2);
               return new bft.qV();
            case CE:
               Assert.a(!var2);
               return null;
            case wF:
               Assert.a(!var2);
               return null;
            case mI:
               Assert.a(!var2);
               return new bft.FL();
            case TX:
               Assert.a(!var2);
               return null;
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return new bft.qa();
            case Xo:
               Assert.a(!var2);
               return new bft.kY();
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
               return new bft.SG(var2, var3);
            case Wx:
               return new bft.oL(var2, var3);
            case AB:
               Assert.a(!var2);
               return new bft.GA();
            case CY:
               return new bft.Fw(var2, var3);
            case WI:
               return new bft.ej(var2);
            case nY:
               return new bft.CI(var2);
            case lF:
               return new bft.EE(var2);
            case cR:
               Assert.a(!var2);
               return new bft.Vj();
            case jb:
               Assert.a(!var2);
               return null;
            case pQ:
               Assert.a(!var2);
               return null;
            case kf:
               Assert.a(!var2);
               return null;
            case qR:
               throw new RuntimeException();
            case YA:
               return new bft.Nz(var2, var1);
            case fw:
               throw new RuntimeException();
            case Wp:
               return new bft.Uz(var2, var1);
            case cY:
            case PY:
               return new bft.eo(var2, var1);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bft.oM {
      CI(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bft.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bft.this.q.q(bfs.nY.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bft.this.q.za());
            bft.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class CU extends bft.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bft.this.q.jq;
         long var1 = bft.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bft.this.q.q(bfs.oW.q());
            var2.q("id", bft.this.q.oW());
            bft.this.q.q(var2);
         });
         this.RF = bft.this.q.jq;
         this.q(bfs.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bft.this.q.xK(var6);
               this.q(var8);
               int var9 = bft.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bft.this.q.PV && !bft.this.q.io) {
                  var8.q("kernelOffset", bft.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bft.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bft.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bft.this.q.za());
               if (!bft.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bft.this.q.za());
               var8.q("numNativeFields", bft.this.q.nf());
               if (!bft.this.q.PV) {
                  Assert.a(!bft.this.q.io);
                  var8.q("tokenPos", bft.this.q.gO());
                  var8.q("endTokenPos", bft.this.q.gO());
               }

               var8.q("stateBits", bft.this.q.nf());
               if (bft.this.q.PV) {
                  if (var7) {
                     bft.this.q.nf();
                  } else if (!bft.this.q.gP.q(var9)) {
                     bft.this.q.TX.put(var9, bft.this.q.nf());
                  }
               }

               bft.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bft.this.q.nf());
         if (!bft.this.q.Me) {
            var1.q("userName", bft.this.q.nf());
         }

         var1.q("functions", bft.this.q.nf());
         var1.q("functionsHashTable", bft.this.q.nf());
         var1.q("fields", bft.this.q.nf());
         var1.q("offsetInWordsToField", bft.this.q.nf());
         var1.q("interfaces", bft.this.q.nf());
         var1.q("script", bft.this.q.nf());
         var1.q("library", bft.this.q.nf());
         var1.q("typeParameters", bft.this.q.nf());
         var1.q("superType", bft.this.q.nf());
         var1.q("constants", bft.this.q.nf());
         var1.q("declarationType", bft.this.q.nf());
         var1.q("invocationDispatcherCache", bft.this.q.nf());
         if (!bft.this.q.Me || !bft.this.q.PV) {
            var1.q("directImplementors", bft.this.q.nf());
            var1.q("directSubclasses", bft.this.q.nf());
         }

         if (!bft.this.q.PV) {
            var1.q("allocationStub", bft.this.q.nf());
            var1.q("dependentCode", bft.this.q.nf());
         }
      }
   }

   class EE extends bft.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfs.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bft.this.q.za());
         }
      }
   }

   class FL extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.mI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bft.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bft.this.q.nf());
         var1.q("argsDescriptor", bft.this.q.nf());
      }
   }

   class Fw extends bft.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfs.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bft.this.q.qa());
            var2.q("base", bft.this.q.zz());
            var2.q("index", bft.this.q.zz());
            long var3 = bft.this.q.zz();
            var2.q("typeState", var3 >> (int)bft.this.q.gP.Rr);
            var2.q("nullability", var3 & bft.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bft.this.q.nf());
         var1.q("hash", bft.this.q.nf());
         var1.q("bound", bft.this.q.nf());
      }
   }

   class GA extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.AB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bft.this.q.nf());
         var1.q("type", bft.this.q.nf());
      }
   }

   class HA extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            if (!bft.this.q.PV) {
               var2.q("flagsAndMaxPosition", bft.this.q.qa());
            }

            var2.q("kernelScriptIndex", bft.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bft.this.q.nf());
         if (bft.this.q.io) {
            if (!bft.this.q.Me) {
               var1.q("resolvedUrl", bft.this.q.nf());
            }
         } else {
            var1.q("resolvedUrl", bft.this.q.nf());
            var1.q("resolvedUrl", bft.this.q.nf());
            var1.q("lineStarts", bft.this.q.nf());
            var1.q("constantCoverage", bft.this.q.nf());
            var1.q("debugPositions", bft.this.q.nf());
            var1.q("kernelProgramInfo", bft.this.q.nf());
         }
      }
   }

   class KZ extends bft.oM {
      @Override
      public void q() {
         this.RF(bfs.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bft.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class LR extends bft.oM {
      LR(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bft.this.q.jq;
         long var1 = bft.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bft.this.q.nf();
            bfs var6 = (var4 & 1L) != 0L ? bfs.Qu : bfs.ND;
            long var7 = var4 >> 1;
            bew var9 = bft.this.q.q(var6.q());
            var9.q("length", var7);
            bft.this.q.q(var9);
         }

         this.gO = bft.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            long var3 = bft.this.q.nf();
            long var5 = var3 >> 1;
            bfs var7 = (var3 & 1L) != 0L ? bfs.Qu : bfs.ND;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bfs.ND) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bft.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bft.this.q.zz();
                  var10 |= bft.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class ME extends bft.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bfs.ZT.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bft.this.q.lm());
            var2.q("nullabity", bft.this.q.nf());
            var2.q("instantiations", bft.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bft.this.q.Uv());
            }
         }
      }
   }

   class Nt extends bft.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bft.this.q.jq;
         bft.this.q.GY = this.oW;
         long var1 = bft.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bft.this.q.jq;
         this.q = bft.this.q.jq;
         var1 = bft.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bft.this.q.jq;
      }

      private void Dw() {
         int var1 = bft.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bft.this.q.q(bfs.Hk.q());
         bft.this.q.q(var3);
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
         bew var3 = bft.this.q.xK(var1);
         this.q(var3, var2);
         if (!bft.this.q.io) {
            var3.q("objectPool", bft.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bft.this.q.Uv(), bfs.nf.toString(), bfs.oW.toString(), bfs.GY.toString());
         var3.q("exceptionHandlers", bft.this.q.Uv(), bfs.sH.toString());
         var3.q("pcDescriptors", bft.this.q.Uv(), bfs.KT.toString());
         var3.q("catchEntry", bft.this.q.Uv());
         if (bft.this.q.LK) {
            var3.q("compressedStackMaps", bft.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bft.this.q.Uv(), bfs.cY.toString());
         var3.q("codeSourceMap", bft.this.q.Uv(), bfs.Gf.toString());
         if (!bft.this.q.PV && bft.this.q.LK) {
            var3.q("deoptInfoArray", bft.this.q.Uv());
            var3.q("staticCallsTargetTable", bft.this.q.Uv());
         }

         if (!bft.this.q.Me) {
            var3.q("returnAddressMetadata", bft.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bft.this.q.Gf ? bft.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bft.this.q.PV) {
            long var3 = bft.this.q.zx[2 * bft.this.q.Ri];
            long var5 = bft.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bft.this.q.gP.oQ : 0L;
            long var12 = var9 ? bft.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bft.this.q.Ri++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bft.oM {
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
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bft.this.q.nf());
         var1.q("hashMask", bft.this.q.nf());
         var1.q("data", bft.this.q.nf());
         var1.q("usedData", bft.this.q.nf());
         var1.q("deletedKeys", bft.this.q.nf());
      }
   }

   class PY extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            if (!bft.this.q.io) {
               var2.q("guardedListLength", bft.this.q.Uv());
            }

            if (bft.this.q.LK) {
               var2.q("dependentCode", bft.this.q.Uv());
            }

            if (!bft.this.q.io) {
               var2.q("tokenPos", bft.this.q.gO());
               var2.q("endTokenPos", bft.this.q.gO());
               var2.q("guardedCid", bft.this.q.oW());
               var2.q("isNullable", bft.this.q.oW());
               var2.q("staticTypeExactnessState", bft.this.q.JY());
               if (!bft.this.q.PV) {
                  var2.q("kernelOffset", bft.this.q.gP());
               }
            }

            int var3 = bft.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bft.this.q.Uv();
            if (bft.this.q.Uv(var3)) {
               long var6 = bft.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bft.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bft.this.q.nf());
         var1.q("owner", bft.this.q.nf());
         var1.q("type", bft.this.q.nf());
         var1.q("initializerFunction", bft.this.q.nf());
      }
   }

   class SG extends bft.oM {
      SG(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfs.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeClassId", bft.this.q.nf());
            long var3 = bft.this.q.zz();
            var2.q("typeState", var3 >> (int)bft.this.q.gP.Rr);
            var2.q("nullability", var3 & bft.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bft.this.q.nf());
         var1.q("arguments", bft.this.q.nf());
         var1.q("hash", bft.this.q.nf());
      }
   }

   class Uz extends bft.oM {
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
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bft.this.q.nf());
         var1.q("hashMask", bft.this.q.nf());
         var1.q("data", bft.this.q.nf());
         var1.q("usedData", bft.this.q.nf());
         var1.q("deletedKeys", bft.this.q.nf());
      }
   }

   class Vj extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.cR.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bft.this.q.nf());
         var1.q("length", bft.this.q.nf());
         var1.q("data", bft.this.q.nf());
      }
   }

   class Xa extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.Rr.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bft.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bft.this.q.nf());
         var1.q("argsDescriptor", bft.this.q.nf());
         var1.q("buckets", bft.this.q.nf());
         var1.q("mask", bft.this.q.nf());
      }
   }

   class bK extends bft.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bft.this.q.jq;
         long var1 = bft.this.q.nf();
         this.RF = bft.this.q.qa();
         this.xK = bft.this.q.qa();
         bft.this.q.q(var1, this.q);
         this.gO = bft.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bft.this.q.gP.nf;
         int var2 = (int)bft.this.q.q(this.xK * bft.this.q.gP.gO, bft.this.q.gP.gP);
         Long var3 = bft.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bft.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bft.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bft.this.q.gP.gO)) {
               if (bft.this.q.RF(var3, var6 / (int)bft.this.q.gP.gO)) {
                  bft.this.q.oQ();
               } else {
                  bft.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bft.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.CY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bft.this.q.nf());
         var1.q("flags", bft.this.q.nf());
         var1.q("bounds", bft.this.q.nf());
         var1.q("defaults", bft.this.q.nf());
      }
   }

   class eM extends bft.oM {
      int q;

      eM(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bft.this.q.jq;
         long var1 = bft.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bft.this.q.nf() << (int)bft.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bft.this.q.q(this.q);
            var7.q("data", var6);
            bft.this.q.q(var7);
         }

         this.gO = bft.this.q.jq;
         if (this.q == bfs.eC.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bfs.ND.q() && this.q != bfs.eC.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bft.this.q.gO.position(var1);
            bft.this.q.gO.i32();
            long var2;
            if (bft.this.q.wF) {
               bft.this.q.gO.i32();
               var2 = bft.this.q.gO.i64();
            } else {
               var2 = bft.this.q.gO.i32();
               bft.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bft.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class ej extends bft.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfs.WI.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bft.this.q.nf());
         var1.q("functionTypeArguments", bft.this.q.nf());
         var1.q("delayedTypeArguments", bft.this.q.nf());
         var1.q("function", bft.this.q.nf());
         var1.q("context", bft.this.q.nf());
         var1.q("hash", bft.this.q.nf());
      }
   }

   class eo extends bft.oM {
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
            bew var2 = bft.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bft.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bft.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bft.oM {
      @Override
      public void q() {
         this.RF(bfs.Gf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bft.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class kY extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("parent", bft.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bft.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class nI extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            if (bft.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bft.this.q.Uv());
            }

            var2.q("parentFunction", bft.this.q.Uv());
            var2.q("closure", bft.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bft.this.q.nf());
         }
      }
   }

   class oL extends bft.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfs.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bft.this.q.zz();
            var2.q("typeState", var3 >> (int)bft.this.q.gP.Rr);
            var2.q("nullability", var3 & bft.this.q.gP.EB);
            var2.q("packedParameterCounts", bft.this.q.io());
            var2.q("packedTypeParameterCounts", bft.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bft.this.q.nf());
         var1.q("typeParameters", bft.this.q.nf());
         var1.q("resultType", bft.this.q.nf());
         var1.q("parameterTypes", bft.this.q.nf());
         var1.q("namedParameterNames", bft.this.q.nf());
         var1.q("hash", bft.this.q.nf());
      }
   }

   abstract class oM implements bft.Bu {
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
         this.oW = bft.this.q.jq;
         long var2 = bft.this.q.nf();
         bft.this.q.q(var2, var1);
         this.gO = bft.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bft.this.q.jq;
         long var2 = bft.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bft.this.q.q(var1);
            long var4 = bft.this.q.nf();
            var3.q("length", var4);
            bft.this.q.q(var3);
         });
         this.gO = bft.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bft.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bft.this.q.nf();
            long var1 = bft.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bft.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bft.oM {
      @Override
      public void q() {
         this.RF(bfs.sH.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bft.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bft.this.q.gP());
               var6.put("outerTryIndex", bft.this.q.LK());
               var6.put("needsStacktrace", bft.this.q.Dw());
               var6.put("hasCatchAll", bft.this.q.Dw());
               var6.put("isGenerated", bft.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class qa extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.EB.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            var2.q("cache", bft.this.q.Uv());
         }
      }
   }

   class qx extends bft.oM {
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
            bew var2 = bft.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bft.this.q.HF(this.q);
            var2.q("data", bft.this.q.Dw(var4));
         }
      }
   }

   class ry extends bft.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class tl extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bft.this.q.qa());
            var2.q("numImports", bft.this.q.HF());
            var2.q("loadState", bft.this.q.JY());
            var2.q("flags", bft.this.q.zz());
            if (!bft.this.q.PV && !bft.this.q.io) {
               var2.q("kernelOffset", bft.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bft.this.q.nf());
         var1.q("url", bft.this.q.nf());
         var1.q("privateKey", bft.this.q.nf());
         var1.q("dictionary", bft.this.q.nf());
         var1.q("metadata", bft.this.q.nf());
         var1.q("toplevelClass", bft.this.q.nf());
         var1.q("usedScripts", bft.this.q.nf());
         var1.q("loadingUnit", bft.this.q.nf());
         var1.q("imports", bft.this.q.nf());
         var1.q("exports", bft.this.q.nf());
         if (!bft.this.q.io) {
            var1.q("dependencies", bft.this.q.nf());
            var1.q("kernelData", bft.this.q.nf());
         }
      }
   }

   class tw extends bft.oM {
      @Override
      public void q() {
         this.RF(bfs.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            long var3 = bft.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bft.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bft.oM {
      @Override
      public void q() {
         this.RF(bfs.xW.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bft.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bft.this.q.oW(var6);
               if (var8 == bft.this.q.gP.xW) {
                  var7.put("rawObj", bft.this.q.Uv());
               } else if (var8 == bft.this.q.gP.KT) {
                  var7.put("rawValue", bft.this.q.za());
               } else if (var8 == bft.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bft.this.q.gP.sH && var8 != bft.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bft.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class vb extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            if (!bft.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bft.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bft.this.q.GY + (int)var3 - 1;
               bew var6 = bft.this.q.xK(var5);
               if (var6.q().equals(bfs.Hk.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bft.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("packedFields", bft.this.q.nf());
            var2.q("kindTag", bft.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bft.this.q.nf());
         var1.q("owner", bft.this.q.nf());
         var1.q("signature", bft.this.q.nf(), bfs.Wx.toString());
         var1.q("data", bft.this.q.nf());
      }
   }

   class vn extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bft.this.q.io ? bft.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bft.this.q.nf());
         var1.q("cSignature", bft.this.q.nf());
         var1.q("callbackTarget", bft.this.q.nf());
         var1.q("callbackExceptionalReturn", bft.this.q.nf());
      }
   }

   class zJ extends bft.oM {
      @Override
      public void q() {
         this.q(bfs.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bft.this.q.xK(var1);
            this.q(var2);
            if (!bft.this.q.PV && !bft.this.q.io) {
               var2.q("libraryKernelOffset", bft.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bft.this.q.nf());
         var1.q("originClass", bft.this.q.nf());
         var1.q("script", bft.this.q.nf());
         if (!bft.this.q.io) {
            var1.q("libraryKernelData", bft.this.q.nf());
         }
      }
   }
}
