package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgr {
   private static final ILogger RF = GlobalLog.getLogger(bgr.class);
   bgt q;

   bgr(bgt var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgr.Bu q(int var1, boolean var2, boolean var3) {
      bgq var4 = bgq.q(var1);
      if (var1 >= bgq.uz.q() || var1 == bgq.zx.q()) {
         return new bgr.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgr.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Gf:
               case Ef:
               case cC:
                  return new bgr.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bgr.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Gf:
               Assert.a(!var2);
               return new bgr.zJ();
            case Ef:
               Assert.a(!var2);
               return new bgr.iZ();
            case cC:
               Assert.a(!var2);
               return new bgr.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bgr.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgr.CU();
            case gP:
               return new bgr.qx();
            case Ri:
               return new bgr.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgr.vX();
            case nf:
               Assert.a(!var2);
               return new bgr.vb();
            case za:
               Assert.a(!var2);
               return new bgr.nI();
            case lm:
               Assert.a(!var2);
               return new bgr.vn();
            case zz:
               Assert.a(!var2);
               return new bgr.PY();
            case JY:
               Assert.a(!var2);
               return new bgr.LR();
            case HF:
               Assert.a(!var2);
               return new bgr.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bgr.Nt();
            case KT:
               Assert.a(!var2);
               return new bgr.CI();
            case CE:
               Assert.a(!var2);
               return new bgr.qV();
            case wF:
               Assert.a(!var2);
               return null;
            case If:
               Assert.a(!var2);
               return null;
            case jq:
               Assert.a(!var2);
               return new bgr.ry();
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return new bgr.SG();
            case Bu:
               Assert.a(!var2);
               return new bgr.Nz();
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
               return new bgr.ct(var2, var3);
            case AB:
               return new bgr.oL(var2, var3);
            case WI:
               return new bgr.GA(var2, var3);
            case lF:
               return new bgr.ej(var2);
            case tW:
               return new bgr.Xa(var2);
            case ZA:
               return new bgr.EE(var2);
            case os:
               Assert.a(!var2);
               return new bgr.Vj();
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
               return new bgr.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bgr.qa(var2, var1);
            case vC:
            case of:
               return new bgr.eo(var2, var1);
            case Hk:
               return new bgr.Zu(var2, var1);
            case CY:
               return new bgr.HA(var2, var3);
            case PQ:
               return new bgr.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgr.oM {
      @Override
      public void q() {
         this.RF(bgq.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgr.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bgr.this.q.oW(var6);
               if (var8 == bgr.this.q.gP.xW) {
                  var7.put("rawObj", bgr.this.q.Uv());
               } else if (var8 == bgr.this.q.gP.KT) {
                  var7.put("rawValue", bgr.this.q.za());
               } else if (var8 == bgr.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bgr.this.q.gP.sH && var8 != bgr.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bgr.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class CU extends bgr.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgr.this.q.jq;
         long var1 = bgr.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgr.this.q.q(bgq.oW.q());
            var2.q("id", bgr.this.q.oW());
            bgr.this.q.q(var2);
         });
         this.RF = bgr.this.q.jq;
         this.q(bgq.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgr.this.q.xK(var6);
               this.q(var8);
               int var9 = bgr.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgr.this.q.PV && !bgr.this.q.io) {
                  var8.q("kernelOffset", bgr.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgr.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgr.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgr.this.q.za());
               if (!bgr.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgr.this.q.za());
               var8.q("numNativeFields", bgr.this.q.nf());
               if (!bgr.this.q.PV) {
                  Assert.a(!bgr.this.q.io);
                  var8.q("tokenPos", bgr.this.q.gO());
                  var8.q("endTokenPos", bgr.this.q.gO());
               }

               var8.q("stateBits", bgr.this.q.nf());
               if (bgr.this.q.PV) {
                  if (var7) {
                     bgr.this.q.nf();
                  } else if (!bgr.this.q.gP.q(var9)) {
                     bgr.this.q.TX.put(var9, bgr.this.q.nf());
                  }
               }

               bgr.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgr.this.q.Uv());
         if (!bgr.this.q.Me) {
            var1.q("userName", bgr.this.q.Uv());
         }

         var1.q("functions", bgr.this.q.Uv());
         var1.q("functionsHashTable", bgr.this.q.Uv());
         var1.q("fields", bgr.this.q.Uv());
         var1.q("offsetInWordsToField", bgr.this.q.Uv());
         var1.q("interfaces", bgr.this.q.Uv());
         var1.q("script", bgr.this.q.Uv());
         var1.q("library", bgr.this.q.Uv());
         var1.q("typeParameters", bgr.this.q.Uv());
         var1.q("superType", bgr.this.q.Uv());
         var1.q("constants", bgr.this.q.Uv());
         var1.q("declarationType", bgr.this.q.Uv());
         var1.q("invocationDispatcherCache", bgr.this.q.Uv());
         if (!bgr.this.q.Me || !bgr.this.q.PV) {
            var1.q("directImplementors", bgr.this.q.Uv());
            var1.q("directSubclasses", bgr.this.q.Uv());
         }

         if (!bgr.this.q.PV) {
            var1.q("allocationStub", bgr.this.q.Uv());
            var1.q("dependentCode", bgr.this.q.Uv());
         }
      }
   }

   class EE extends bgr.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgq.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgr.this.q.PV());
         }
      }
   }

   class FL extends bgr.oM {
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
            bew var2 = bgr.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgr.this.q.HF(this.q);
            var2.q("data", bgr.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bgr.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bgq.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgr.this.q.qa());
            var2.q("nullabity", bgr.this.q.nf());
            var2.q("instantiations", bgr.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgr.this.q.Uv());
            }
         }
      }
   }

   class GA extends bgr.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgq.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bgr.this.q.HF());
            var2.q("index", bgr.this.q.HF());
            long var3 = bgr.this.q.zz();
            var2.q("typeState", var3 >> (int)bgr.this.q.gP.Rr);
            var2.q("nullability", var3 & bgr.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgr.this.q.Uv());
         var1.q("hash", bgr.this.q.Uv());
         var1.q("owner", bgr.this.q.Uv());
      }
   }

   class HA extends bgr.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgq.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bgr.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bgr.this.q.Uv());
         var1.q("hash", bgr.this.q.Uv());
         var1.q("shape", bgr.this.q.Uv());
         var1.q("fieldTypes", bgr.this.q.Uv());
      }
   }

   class KZ extends bgr.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgr.this.q.jq;
         long var1 = bgr.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgr.this.q.nf() << (int)bgr.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgr.this.q.q(this.q);
            var7.q("data", var6);
            bgr.this.q.q(var7);
         }

         this.gO = bgr.this.q.jq;
         if (this.q == bgq.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bgq.fn.q() && this.q != bgq.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgr.this.q.gO.position(var1);
            bgr.this.q.gO.i32();
            long var2;
            if (bgr.this.q.wF) {
               bgr.this.q.gO.i32();
               var2 = bgr.this.q.gO.i64();
            } else {
               bgr.this.q.gO.i32();
               var2 = bgr.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgr.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            if (!bgr.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgr.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgr.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgr.this.q.Uv());
         if (bgr.this.q.io) {
            if (!bgr.this.q.Me) {
               var1.q("resolvedUrl", bgr.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgr.this.q.Uv());
            var1.q("resolvedUrl", bgr.this.q.Uv());
            var1.q("lineStarts", bgr.this.q.Uv());
            var1.q("constantCoverage", bgr.this.q.Uv());
            var1.q("debugPositions", bgr.this.q.Uv());
            var1.q("kernelProgramInfo", bgr.this.q.Uv());
         }
      }
   }

   class ME extends bgr.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgr.this.q.jq;
         long var1 = bgr.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgr.this.q.nf();
            bgq var6 = (var4 & 1L) != 0L ? bgq.ZU : bgq.fn;
            long var7 = var4 >> 1;
            bew var9 = bgr.this.q.q(var6.q());
            var9.q("length", var7);
            bgr.this.q.q(var9);
         }

         this.gO = bgr.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            long var3 = bgr.this.q.nf();
            long var5 = var3 >> 1;
            bgq var7 = (var3 & 1L) != 0L ? bgq.ZU : bgq.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bgq.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgr.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgr.this.q.zz();
                  var10 |= bgr.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bgr.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgr.this.q.jq;
         bgr.this.q.Ri = this.oW;
         long var1 = bgr.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgr.this.q.jq;
         this.q = bgr.this.q.jq;
         var1 = bgr.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgr.this.q.jq;
      }

      private void Dw() {
         int var1 = bgr.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgr.this.q.q(bgq.Me.q());
         bgr.this.q.q(var3);
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
         bew var3 = bgr.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgr.this.q.io) {
            var3.q("objectPool", bgr.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgr.this.q.Uv(), bgq.nf.toString(), bgq.oW.toString(), bgq.Wx.toString());
         var3.q("exceptionHandlers", bgr.this.q.Uv(), bgq.CE.toString());
         var3.q("pcDescriptors", bgr.this.q.Uv(), bgq.Gf.toString());
         var3.q("catchEntry", bgr.this.q.Uv());
         if (bgr.this.q.LK) {
            var3.q("compressedStackMaps", bgr.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgr.this.q.Uv(), bgq.vC.toString());
         var3.q("codeSourceMap", bgr.this.q.Uv(), bgq.Ef.toString());
         if (!bgr.this.q.PV && bgr.this.q.LK) {
            var3.q("deoptInfoArray", bgr.this.q.Uv());
            var3.q("staticCallsTargetTable", bgr.this.q.Uv());
         }

         if (!bgr.this.q.Me) {
            var3.q("returnAddressMetadata", bgr.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgr.this.q.Gf ? bgr.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgr.this.q.PV) {
            long var3 = bgr.this.q.Rv[2 * bgr.this.q.ZT];
            long var5 = bgr.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgr.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgr.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgr.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("parent", bgr.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgr.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgr.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgr.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgr.this.q.Uv());
         var1.q("owner", bgr.this.q.Uv());
         var1.q("type", bgr.this.q.Uv());
         var1.q("initializerFunction", bgr.this.q.Uv());
      }
   }

   class SG extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("cache", bgr.this.q.Uv());
            var2.q("numInputs", bgr.this.q.io());
            var2.q("numOccupied", bgr.this.q.io());
         }
      }
   }

   class Uz extends bgr.oM {
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
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgr.this.q.Uv());
         var1.q("hashMask", bgr.this.q.Uv());
         var1.q("data", bgr.this.q.Uv());
         var1.q("usedData", bgr.this.q.Uv());
         var1.q("deletedKeys", bgr.this.q.Uv());
      }
   }

   class Vj extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgr.this.q.Uv());
         var1.q("length", bgr.this.q.Uv());
         var1.q("data", bgr.this.q.Uv());
      }
   }

   class Xa extends bgr.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgr.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgr.this.q.q(bgq.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgr.this.q.Hk());
            bgr.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bgr.oM {
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
            bew var2 = bgr.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgr.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bgr.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgr.this.q.jq;
         long var1 = bgr.this.q.nf();
         this.RF = bgr.this.q.qa();
         this.xK = bgr.this.q.qa();
         bgr.this.q.q(var1, this.q);
         this.gO = bgr.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgr.this.q.gP.nf;
         int var2 = (int)bgr.this.q.q(this.xK * bgr.this.q.gP.gO, bgr.this.q.gP.gP);
         Long var3 = bgr.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgr.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgr.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgr.this.q.gP.gO)) {
               if (bgr.this.q.RF(var3, var6 / (int)bgr.this.q.gP.gO)) {
                  bgr.this.q.oQ();
               } else {
                  bgr.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgr.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgr.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgq.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgr.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgr.this.q.Uv());
         var1.q("hash", bgr.this.q.Uv());
         var1.q("arguments", bgr.this.q.Uv());
      }
   }

   class eM extends bgr.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bgq.PQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bgr.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bgr.this.q.Uv();
            }
         }
      }
   }

   class ej extends bgr.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgq.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgr.this.q.Uv());
         var1.q("functionTypeArguments", bgr.this.q.Uv());
         var1.q("delayedTypeArguments", bgr.this.q.Uv());
         var1.q("function", bgr.this.q.Uv());
         var1.q("context", bgr.this.q.Uv());
         var1.q("hash", bgr.this.q.Uv());
      }
   }

   class eo extends bgr.oM {
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
            bew var2 = bgr.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgr.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgr.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgr.oM {
      @Override
      public void q() {
         this.RF(bgq.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgr.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bgr.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgr.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgr.this.q.Uv());
         var1.q("argsDescriptor", bgr.this.q.Uv());
         var1.q("buckets", bgr.this.q.Uv());
         var1.q("mask", bgr.this.q.Uv());
      }
   }

   class nI extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            if (bgr.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgr.this.q.Uv());
            }

            var2.q("parentFunction", bgr.this.q.Uv());
            var2.q("closure", bgr.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgr.this.q.nf());
         }
      }
   }

   class oL extends bgr.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgq.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgr.this.q.zz();
            var2.q("typeState", var3 >> (int)bgr.this.q.gP.Rr);
            var2.q("nullability", var3 & bgr.this.q.gP.EB);
            var2.q("packedParameterCounts", bgr.this.q.io());
            var2.q("packedTypeParameterCounts", bgr.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgr.this.q.Uv());
         var1.q("hash", bgr.this.q.Uv());
         var1.q("typeParameters", bgr.this.q.Uv());
         var1.q("resultType", bgr.this.q.Uv());
         var1.q("parameterTypes", bgr.this.q.Uv());
         var1.q("namedParameterNames", bgr.this.q.Uv());
      }
   }

   abstract class oM implements bgr.Bu {
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
         this.oW = bgr.this.q.jq;
         long var2 = bgr.this.q.nf();
         bgr.this.q.q(var2, var1);
         this.gO = bgr.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgr.this.q.jq;
         long var2 = bgr.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgr.this.q.q(var1);
            long var4 = bgr.this.q.nf();
            var3.q("length", var4);
            bgr.this.q.q(var3);
         });
         this.gO = bgr.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgr.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgr.this.q.nf();
            long var1 = bgr.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgr.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgr.oM {
      @Override
      public void q() {
         this.RF(bgq.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            long var3 = bgr.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgr.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgr.this.q.gP());
               var9.put("outerTryIndex", bgr.this.q.LK());
               var9.put("needsStacktrace", bgr.this.q.Dw());
               var9.put("hasCatchAll", bgr.this.q.Dw());
               var9.put("isGenerated", bgr.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgr.oM {
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
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgr.this.q.Uv());
         var1.q("hashMask", bgr.this.q.Uv());
         var1.q("data", bgr.this.q.Uv());
         var1.q("usedData", bgr.this.q.Uv());
         var1.q("deletedKeys", bgr.this.q.Uv());
      }
   }

   class qx extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgr.this.q.Uv());
         var1.q("flags", bgr.this.q.Uv());
         var1.q("bounds", bgr.this.q.Uv());
         var1.q("defaults", bgr.this.q.Uv());
      }
   }

   class ry extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgr.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgr.this.q.Uv());
         var1.q("argsDescriptor", bgr.this.q.Uv());
      }
   }

   class tl extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgr.this.q.qa());
            var2.q("numImports", bgr.this.q.HF());
            var2.q("loadState", bgr.this.q.JY());
            var2.q("flags", bgr.this.q.zz());
            if (!bgr.this.q.PV && !bgr.this.q.io) {
               var2.q("kernelOffset", bgr.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgr.this.q.Uv());
         var1.q("url", bgr.this.q.Uv());
         var1.q("privateKey", bgr.this.q.Uv());
         var1.q("dictionary", bgr.this.q.Uv());
         var1.q("metadata", bgr.this.q.Uv());
         var1.q("toplevelClass", bgr.this.q.Uv());
         var1.q("usedScripts", bgr.this.q.Uv());
         var1.q("loadingUnit", bgr.this.q.Uv());
         var1.q("imports", bgr.this.q.Uv());
         var1.q("exports", bgr.this.q.Uv());
         if (!bgr.this.q.io) {
            var1.q("dependencies", bgr.this.q.Uv());
            var1.q("kernelProgramInfo", bgr.this.q.Uv());
         }
      }
   }

   class tw extends bgr.oM {
      @Override
      public void q() {
         this.RF(bgq.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            long var3 = bgr.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgr.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            if (!bgr.this.q.PV && !bgr.this.q.io) {
               var2.q("libraryKernelOffset", bgr.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bgr.this.q.Uv());
         var1.q("script", bgr.this.q.Uv());
         if (!bgr.this.q.io) {
            var1.q("libraryKernelData", bgr.this.q.Uv());
         }
      }
   }

   class vb extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            if (!bgr.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgr.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgr.this.q.Ri + (int)var3 - 1;
               bew var6 = bgr.this.q.xK(var5);
               if (var6.q().equals(bgq.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgr.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgr.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgr.this.q.Uv());
         var1.q("owner", bgr.this.q.Uv());
         var1.q("signature", bgr.this.q.Uv(), bgq.AB.toString());
         var1.q("data", bgr.this.q.Uv());
      }
   }

   class vn extends bgr.oM {
      @Override
      public void q() {
         this.q(bgq.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgr.this.q.nf());
            var2.q("callbackKind", bgr.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgr.this.q.Uv());
         var1.q("cSignature", bgr.this.q.Uv());
         var1.q("callbackTarget", bgr.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgr.this.q.Uv());
      }
   }

   class zJ extends bgr.oM {
      @Override
      public void q() {
         this.RF(bgq.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgr.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgr.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
