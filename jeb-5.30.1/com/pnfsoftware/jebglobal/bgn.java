package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgn {
   private static final ILogger RF = GlobalLog.getLogger(bgn.class);
   bgp q;

   bgn(bgp var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgn.Bu q(int var1, boolean var2, boolean var3) {
      bgm var4 = bgm.q(var1);
      if (var1 >= bgm.uz.q() || var1 == bgm.zx.q()) {
         return new bgn.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgn.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Gf:
               case Ef:
               case cC:
                  return new bgn.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bgn.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Gf:
               Assert.a(!var2);
               return new bgn.zJ();
            case Ef:
               Assert.a(!var2);
               return new bgn.iZ();
            case cC:
               Assert.a(!var2);
               return new bgn.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bgn.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgn.CU();
            case gP:
               return new bgn.qx();
            case Ri:
               return new bgn.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgn.vX();
            case nf:
               Assert.a(!var2);
               return new bgn.vb();
            case za:
               Assert.a(!var2);
               return new bgn.nI();
            case lm:
               Assert.a(!var2);
               return new bgn.vn();
            case zz:
               Assert.a(!var2);
               return new bgn.PY();
            case JY:
               Assert.a(!var2);
               return new bgn.LR();
            case HF:
               Assert.a(!var2);
               return new bgn.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bgn.Nt();
            case KT:
               Assert.a(!var2);
               return new bgn.CI();
            case CE:
               Assert.a(!var2);
               return new bgn.qV();
            case wF:
               Assert.a(!var2);
               return null;
            case If:
               Assert.a(!var2);
               return null;
            case jq:
               Assert.a(!var2);
               return new bgn.ry();
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return new bgn.SG();
            case Bu:
               Assert.a(!var2);
               return new bgn.Nz();
            case eJ:
               Assert.a(!var2);
               return null;
            case YN:
               Assert.a(!var2);
               return null;
            case ZT:
               Assert.a(!var2);
               return null;
            case Wx:
               return new bgn.ct(var2, var3);
            case AB:
               return new bgn.oL(var2, var3);
            case WI:
               return new bgn.GA(var2, var3);
            case lF:
               return new bgn.ej(var2);
            case tW:
               return new bgn.Xa(var2);
            case ZA:
               return new bgn.EE(var2);
            case os:
               Assert.a(!var2);
               return new bgn.Vj();
            case IY:
               Assert.a(!var2);
               return null;
            case YA:
               Assert.a(!var2);
               return null;
            case fw:
               Assert.a(!var2);
               return null;
            case ND:
               throw new RuntimeException();
            case Qu:
               return new bgn.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bgn.qa(var2, var1);
            case vC:
            case of:
               return new bgn.eo(var2, var1);
            case Hk:
               return new bgn.Zu(var2, var1);
            case CY:
               return new bgn.HA(var2, var3);
            case PQ:
               return new bgn.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgn.oM {
      @Override
      public void q() {
         this.RF(bgm.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgn.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bgn.this.q.oW(var6);
               if (var8 == bgn.this.q.gP.xW) {
                  var7.put("rawObj", bgn.this.q.Uv());
               } else if (var8 == bgn.this.q.gP.KT) {
                  var7.put("rawValue", bgn.this.q.za());
               } else if (var8 == bgn.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bgn.this.q.gP.sH && var8 != bgn.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bgn.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class CU extends bgn.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgn.this.q.jq;
         long var1 = bgn.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgn.this.q.q(bgm.oW.q());
            var2.q("id", bgn.this.q.oW());
            bgn.this.q.q(var2);
         });
         this.RF = bgn.this.q.jq;
         this.q(bgm.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgn.this.q.xK(var6);
               this.q(var8);
               int var9 = bgn.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgn.this.q.PV && !bgn.this.q.io) {
                  var8.q("kernelOffset", bgn.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgn.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgn.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgn.this.q.za());
               if (!bgn.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgn.this.q.za());
               var8.q("numNativeFields", bgn.this.q.nf());
               if (!bgn.this.q.PV) {
                  Assert.a(!bgn.this.q.io);
                  var8.q("tokenPos", bgn.this.q.gO());
                  var8.q("endTokenPos", bgn.this.q.gO());
               }

               var8.q("stateBits", bgn.this.q.nf());
               if (bgn.this.q.PV) {
                  if (var7) {
                     bgn.this.q.nf();
                  } else if (!bgn.this.q.gP.q(var9)) {
                     bgn.this.q.TX.put(var9, bgn.this.q.nf());
                  }
               }

               bgn.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgn.this.q.Uv());
         if (!bgn.this.q.Me) {
            var1.q("userName", bgn.this.q.Uv());
         }

         var1.q("functions", bgn.this.q.Uv());
         var1.q("functionsHashTable", bgn.this.q.Uv());
         var1.q("fields", bgn.this.q.Uv());
         var1.q("offsetInWordsToField", bgn.this.q.Uv());
         var1.q("interfaces", bgn.this.q.Uv());
         var1.q("script", bgn.this.q.Uv());
         var1.q("library", bgn.this.q.Uv());
         var1.q("typeParameters", bgn.this.q.Uv());
         var1.q("superType", bgn.this.q.Uv());
         var1.q("constants", bgn.this.q.Uv());
         var1.q("declarationType", bgn.this.q.Uv());
         var1.q("invocationDispatcherCache", bgn.this.q.Uv());
         if (!bgn.this.q.Me || !bgn.this.q.PV) {
            var1.q("directImplementors", bgn.this.q.Uv());
            var1.q("directSubclasses", bgn.this.q.Uv());
         }

         if (!bgn.this.q.PV) {
            var1.q("allocationStub", bgn.this.q.Uv());
            var1.q("dependentCode", bgn.this.q.Uv());
         }
      }
   }

   class EE extends bgn.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgm.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgn.this.q.PV());
         }
      }
   }

   class FL extends bgn.oM {
      int q;

      public FL(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgn.this.q.HF(this.q);
            var2.q("data", bgn.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bgn.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bgm.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgn.this.q.qa());
            var2.q("nullabity", bgn.this.q.nf());
            var2.q("instantiations", bgn.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgn.this.q.Uv());
            }
         }
      }
   }

   class GA extends bgn.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgm.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bgn.this.q.HF());
            var2.q("index", bgn.this.q.HF());
            long var3 = bgn.this.q.zz();
            var2.q("typeState", var3 >> (int)bgn.this.q.gP.Rr);
            var2.q("nullability", var3 & bgn.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgn.this.q.Uv());
         var1.q("hash", bgn.this.q.Uv());
         var1.q("owner", bgn.this.q.Uv());
      }
   }

   class HA extends bgn.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgm.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bgn.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bgn.this.q.Uv());
         var1.q("hash", bgn.this.q.Uv());
         var1.q("shape", bgn.this.q.Uv());
         var1.q("fieldTypes", bgn.this.q.Uv());
      }
   }

   class KZ extends bgn.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgn.this.q.jq;
         long var1 = bgn.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgn.this.q.nf() << (int)bgn.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgn.this.q.q(this.q);
            var7.q("data", var6);
            bgn.this.q.q(var7);
         }

         this.gO = bgn.this.q.jq;
         if (this.q == bgm.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bgm.fn.q() && this.q != bgm.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgn.this.q.gO.position(var1);
            bgn.this.q.gO.i32();
            long var2;
            if (bgn.this.q.wF) {
               bgn.this.q.gO.i32();
               var2 = bgn.this.q.gO.i64();
            } else {
               bgn.this.q.gO.i32();
               var2 = bgn.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgn.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            if (!bgn.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgn.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgn.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgn.this.q.Uv());
         if (bgn.this.q.io) {
            if (!bgn.this.q.Me) {
               var1.q("resolvedUrl", bgn.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgn.this.q.Uv());
            var1.q("resolvedUrl", bgn.this.q.Uv());
            var1.q("lineStarts", bgn.this.q.Uv());
            var1.q("constantCoverage", bgn.this.q.Uv());
            var1.q("debugPositions", bgn.this.q.Uv());
            var1.q("kernelProgramInfo", bgn.this.q.Uv());
         }
      }
   }

   class ME extends bgn.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgn.this.q.jq;
         long var1 = bgn.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgn.this.q.nf();
            bgm var6 = (var4 & 1L) != 0L ? bgm.ZU : bgm.fn;
            long var7 = var4 >> 1;
            bew var9 = bgn.this.q.q(var6.q());
            var9.q("length", var7);
            bgn.this.q.q(var9);
         }

         this.gO = bgn.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            long var3 = bgn.this.q.nf();
            long var5 = var3 >> 1;
            bgm var7 = (var3 & 1L) != 0L ? bgm.ZU : bgm.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bgm.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgn.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgn.this.q.zz();
                  var10 |= bgn.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bgn.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgn.this.q.jq;
         bgn.this.q.Ri = this.oW;
         long var1 = bgn.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgn.this.q.jq;
         this.q = bgn.this.q.jq;
         var1 = bgn.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgn.this.q.jq;
      }

      private void Dw() {
         int var1 = bgn.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgn.this.q.q(bgm.Me.q());
         bgn.this.q.q(var3);
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
         bew var3 = bgn.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgn.this.q.io) {
            var3.q("objectPool", bgn.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgn.this.q.Uv(), bgm.nf.toString(), bgm.oW.toString(), bgm.Wx.toString());
         var3.q("exceptionHandlers", bgn.this.q.Uv(), bgm.CE.toString());
         var3.q("pcDescriptors", bgn.this.q.Uv(), bgm.Gf.toString());
         var3.q("catchEntry", bgn.this.q.Uv());
         if (bgn.this.q.LK) {
            var3.q("compressedStackMaps", bgn.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgn.this.q.Uv(), bgm.vC.toString());
         var3.q("codeSourceMap", bgn.this.q.Uv(), bgm.Ef.toString());
         if (!bgn.this.q.PV && bgn.this.q.LK) {
            var3.q("deoptInfoArray", bgn.this.q.Uv());
            var3.q("staticCallsTargetTable", bgn.this.q.Uv());
         }

         if (!bgn.this.q.Me) {
            var3.q("returnAddressMetadata", bgn.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgn.this.q.Gf ? bgn.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgn.this.q.PV) {
            long var3 = bgn.this.q.Rv[2 * bgn.this.q.ZT];
            long var5 = bgn.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgn.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgn.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgn.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("parent", bgn.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgn.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgn.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgn.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgn.this.q.Uv());
         var1.q("owner", bgn.this.q.Uv());
         var1.q("type", bgn.this.q.Uv());
         var1.q("initializerFunction", bgn.this.q.Uv());
      }
   }

   class SG extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("cache", bgn.this.q.Uv());
            var2.q("numInputs", bgn.this.q.io());
            var2.q("numOccupied", bgn.this.q.io());
         }
      }
   }

   class Uz extends bgn.oM {
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
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgn.this.q.Uv());
         var1.q("hashMask", bgn.this.q.Uv());
         var1.q("data", bgn.this.q.Uv());
         var1.q("usedData", bgn.this.q.Uv());
         var1.q("deletedKeys", bgn.this.q.Uv());
      }
   }

   class Vj extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgn.this.q.Uv());
         var1.q("length", bgn.this.q.Uv());
         var1.q("data", bgn.this.q.Uv());
      }
   }

   class Xa extends bgn.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgn.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgn.this.q.q(bgm.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgn.this.q.Hk());
            bgn.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bgn.oM {
      int q;

      Zu(boolean var2, int var3) {
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
            bew var2 = bgn.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgn.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bgn.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgn.this.q.jq;
         long var1 = bgn.this.q.nf();
         this.RF = bgn.this.q.qa();
         this.xK = bgn.this.q.qa();
         bgn.this.q.q(var1, this.q);
         this.gO = bgn.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgn.this.q.gP.nf;
         int var2 = (int)bgn.this.q.q(this.xK * bgn.this.q.gP.gO, bgn.this.q.gP.gP);
         Long var3 = bgn.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgn.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgn.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgn.this.q.gP.gO)) {
               if (bgn.this.q.RF(var3, var6 / (int)bgn.this.q.gP.gO)) {
                  bgn.this.q.oQ();
               } else {
                  bgn.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgn.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgn.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgm.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgn.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgn.this.q.Uv());
         var1.q("hash", bgn.this.q.Uv());
         var1.q("arguments", bgn.this.q.Uv());
      }
   }

   class eM extends bgn.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bgm.PQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bgn.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bgn.this.q.Uv();
            }
         }
      }
   }

   class ej extends bgn.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgm.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgn.this.q.Uv());
         var1.q("functionTypeArguments", bgn.this.q.Uv());
         var1.q("delayedTypeArguments", bgn.this.q.Uv());
         var1.q("function", bgn.this.q.Uv());
         var1.q("context", bgn.this.q.Uv());
         var1.q("hash", bgn.this.q.Uv());
      }
   }

   class eo extends bgn.oM {
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
            bew var2 = bgn.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgn.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgn.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgn.oM {
      @Override
      public void q() {
         this.RF(bgm.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgn.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bgn.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgn.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgn.this.q.Uv());
         var1.q("argsDescriptor", bgn.this.q.Uv());
         var1.q("buckets", bgn.this.q.Uv());
         var1.q("mask", bgn.this.q.Uv());
      }
   }

   class nI extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            if (bgn.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgn.this.q.Uv());
            }

            var2.q("parentFunction", bgn.this.q.Uv());
            var2.q("closure", bgn.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgn.this.q.nf());
         }
      }
   }

   class oL extends bgn.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgm.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgn.this.q.zz();
            var2.q("typeState", var3 >> (int)bgn.this.q.gP.Rr);
            var2.q("nullability", var3 & bgn.this.q.gP.EB);
            var2.q("packedParameterCounts", bgn.this.q.io());
            var2.q("packedTypeParameterCounts", bgn.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgn.this.q.Uv());
         var1.q("hash", bgn.this.q.Uv());
         var1.q("typeParameters", bgn.this.q.Uv());
         var1.q("resultType", bgn.this.q.Uv());
         var1.q("parameterTypes", bgn.this.q.Uv());
         var1.q("namedParameterNames", bgn.this.q.Uv());
      }
   }

   abstract class oM implements bgn.Bu {
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
         this.oW = bgn.this.q.jq;
         long var2 = bgn.this.q.nf();
         bgn.this.q.q(var2, var1);
         this.gO = bgn.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgn.this.q.jq;
         long var2 = bgn.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgn.this.q.q(var1);
            long var4 = bgn.this.q.nf();
            var3.q("length", var4);
            bgn.this.q.q(var3);
         });
         this.gO = bgn.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgn.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgn.this.q.nf();
            long var1 = bgn.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgn.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgn.oM {
      @Override
      public void q() {
         this.RF(bgm.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            long var3 = bgn.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgn.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgn.this.q.gP());
               var9.put("outerTryIndex", bgn.this.q.LK());
               var9.put("needsStacktrace", bgn.this.q.Dw());
               var9.put("hasCatchAll", bgn.this.q.Dw());
               var9.put("isGenerated", bgn.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgn.oM {
      int q;

      qa(boolean var2, int var3) {
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
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgn.this.q.Uv());
         var1.q("hashMask", bgn.this.q.Uv());
         var1.q("data", bgn.this.q.Uv());
         var1.q("usedData", bgn.this.q.Uv());
         var1.q("deletedKeys", bgn.this.q.Uv());
      }
   }

   class qx extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgn.this.q.Uv());
         var1.q("flags", bgn.this.q.Uv());
         var1.q("bounds", bgn.this.q.Uv());
         var1.q("defaults", bgn.this.q.Uv());
      }
   }

   class ry extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgn.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgn.this.q.Uv());
         var1.q("argsDescriptor", bgn.this.q.Uv());
      }
   }

   class tl extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgn.this.q.qa());
            var2.q("numImports", bgn.this.q.HF());
            var2.q("loadState", bgn.this.q.JY());
            var2.q("flags", bgn.this.q.zz());
            if (!bgn.this.q.PV && !bgn.this.q.io) {
               var2.q("kernelOffset", bgn.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgn.this.q.Uv());
         var1.q("url", bgn.this.q.Uv());
         var1.q("privateKey", bgn.this.q.Uv());
         var1.q("dictionary", bgn.this.q.Uv());
         var1.q("metadata", bgn.this.q.Uv());
         var1.q("toplevelClass", bgn.this.q.Uv());
         var1.q("usedScripts", bgn.this.q.Uv());
         var1.q("loadingUnit", bgn.this.q.Uv());
         var1.q("imports", bgn.this.q.Uv());
         var1.q("exports", bgn.this.q.Uv());
         if (!bgn.this.q.io) {
            var1.q("dependencies", bgn.this.q.Uv());
            var1.q("kernelData", bgn.this.q.Uv());
         }
      }
   }

   class tw extends bgn.oM {
      @Override
      public void q() {
         this.RF(bgm.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            long var3 = bgn.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgn.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            if (!bgn.this.q.PV && !bgn.this.q.io) {
               var2.q("libraryKernelOffset", bgn.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bgn.this.q.Uv());
         var1.q("originClass", bgn.this.q.Uv());
         var1.q("script", bgn.this.q.Uv());
         if (!bgn.this.q.io) {
            var1.q("libraryKernelData", bgn.this.q.Uv());
         }
      }
   }

   class vb extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            if (!bgn.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgn.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgn.this.q.Ri + (int)var3 - 1;
               bew var6 = bgn.this.q.xK(var5);
               if (var6.q().equals(bgm.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgn.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgn.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgn.this.q.Uv());
         var1.q("owner", bgn.this.q.Uv());
         var1.q("signature", bgn.this.q.Uv(), bgm.AB.toString());
         var1.q("data", bgn.this.q.Uv());
      }
   }

   class vn extends bgn.oM {
      @Override
      public void q() {
         this.q(bgm.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgn.this.q.nf());
            var2.q("callbackKind", bgn.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgn.this.q.Uv());
         var1.q("cSignature", bgn.this.q.Uv());
         var1.q("callbackTarget", bgn.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgn.this.q.Uv());
      }
   }

   class zJ extends bgn.oM {
      @Override
      public void q() {
         this.RF(bgm.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgn.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgn.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
