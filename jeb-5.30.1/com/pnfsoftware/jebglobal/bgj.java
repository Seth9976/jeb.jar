package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgj {
   private static final ILogger RF = GlobalLog.getLogger(bgj.class);
   bgl q;

   bgj(bgl var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgj.Bu q(int var1, boolean var2, boolean var3) {
      bgi var4 = bgi.q(var1);
      if (var1 >= bgi.Xz.q() || var1 == bgi.zx.q()) {
         return new bgj.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgj.ry(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (bgk.q[var4.ordinal()]) {
               case 1:
               case 2:
               case 3:
                  return new bgj.KZ(var2, var3, var1);
               case 4:
               case 5:
               case 6:
                  if (var3) {
                     return new bgj.KZ(var2, var3, var1);
                  }
            }
         }

         switch (bgk.q[var4.ordinal()]) {
            case 1:
               Assert.a(!var2);
               return new bgj.zJ();
            case 2:
               Assert.a(!var2);
               return new bgj.iZ();
            case 3:
               Assert.a(!var2);
               return new bgj.tw();
            case 4:
            case 5:
            default:
               return null;
            case 6:
               return new bgj.ME(var2, var3 && this.q.Uv != null);
            case 7:
               Assert.a(!var2);
               return new bgj.CU();
            case 8:
               return new bgj.qx();
            case 9:
               return new bgj.Fw(var2, var3);
            case 10:
               Assert.a(!var2);
               return new bgj.vX();
            case 11:
               Assert.a(!var2);
               return new bgj.vb();
            case 12:
               Assert.a(!var2);
               return new bgj.nI();
            case 13:
               Assert.a(!var2);
               return new bgj.vn();
            case 14:
               Assert.a(!var2);
               return new bgj.PY();
            case 15:
               Assert.a(!var2);
               return new bgj.LR();
            case 16:
               Assert.a(!var2);
               return new bgj.tl();
            case 17:
               Assert.a(!var2);
               throw new ToDoException();
            case 18:
               Assert.a(!var2);
               return new bgj.Nt();
            case 19:
               Assert.a(!var2);
               return new bgj.CI();
            case 20:
               Assert.a(!var2);
               return new bgj.qV();
            case 21:
               Assert.a(!var2);
               return null;
            case 22:
               Assert.a(!var2);
               return null;
            case 23:
               Assert.a(!var2);
               return new bgj.Zu();
            case 24:
               Assert.a(!var2);
               return null;
            case 25:
               Assert.a(!var2);
               return null;
            case 26:
               Assert.a(!var2);
               return new bgj.SG();
            case 27:
               Assert.a(!var2);
               return new bgj.Nz();
            case 28:
               Assert.a(!var2);
               return null;
            case 29:
               Assert.a(!var2);
               return null;
            case 30:
               Assert.a(!var2);
               return null;
            case 31:
               return new bgj.ct(var2, var3);
            case 32:
               return new bgj.oL(var2, var3);
            case 33:
               Assert.a(!var2);
               return new bgj.FL();
            case 34:
               return new bgj.GA(var2, var3);
            case 35:
               return new bgj.ej(var2);
            case 36:
               return new bgj.Xa(var2);
            case 37:
               return new bgj.EE(var2);
            case 38:
               Assert.a(!var2);
               return new bgj.Vj();
            case 39:
               Assert.a(!var2);
               return null;
            case 40:
               Assert.a(!var2);
               return null;
            case 41:
               Assert.a(!var2);
               return null;
            case 42:
               throw new RuntimeException();
            case 43:
               return new bgj.Uz(var2, var1);
            case 44:
               throw new RuntimeException();
            case 45:
               return new bgj.qa(var2, var1);
            case 46:
            case 47:
               return new bgj.eo(var2, var1);
            case 48:
               return new bgj.jx(var2, var1);
            case 49:
               return new bgj.HA(var2, var3);
            case 50:
               return new bgj.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgj.oM {
      @Override
      public void q() {
         this.RF(bgi.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgj.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bgj.this.q.oW(var6);
               if (var8 == bgj.this.q.gP.xW) {
                  var7.put("rawObj", bgj.this.q.Uv());
               } else if (var8 == bgj.this.q.gP.KT) {
                  var7.put("rawValue", bgj.this.q.za());
               } else if (var8 == bgj.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bgj.this.q.gP.sH && var8 != bgj.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bgj.this.q.PV);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class CU extends bgj.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgj.this.q.jq;
         long var1 = bgj.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgj.this.q.q(bgi.oW.q());
            var2.q("id", bgj.this.q.oW());
            bgj.this.q.q(var2);
         });
         this.RF = bgj.this.q.jq;
         this.q(bgi.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgj.this.q.xK(var6);
               this.q(var8);
               int var9 = bgj.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgj.this.q.PV && !bgj.this.q.io) {
                  var8.q("kernelOffset", bgj.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgj.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgj.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgj.this.q.za());
               if (!bgj.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgj.this.q.za());
               var8.q("numNativeFields", bgj.this.q.nf());
               if (!bgj.this.q.PV) {
                  Assert.a(!bgj.this.q.io);
                  var8.q("tokenPos", bgj.this.q.gO());
                  var8.q("endTokenPos", bgj.this.q.gO());
               }

               var8.q("stateBits", bgj.this.q.nf());
               if (bgj.this.q.PV) {
                  if (var7) {
                     bgj.this.q.nf();
                  } else if (!bgj.this.q.gP.q(var9)) {
                     bgj.this.q.TX.put(var9, bgj.this.q.nf());
                  }
               }

               bgj.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgj.this.q.Uv());
         if (!bgj.this.q.Me) {
            var1.q("userName", bgj.this.q.Uv());
         }

         var1.q("functions", bgj.this.q.Uv());
         var1.q("functionsHashTable", bgj.this.q.Uv());
         var1.q("fields", bgj.this.q.Uv());
         var1.q("offsetInWordsToField", bgj.this.q.Uv());
         var1.q("interfaces", bgj.this.q.Uv());
         var1.q("script", bgj.this.q.Uv());
         var1.q("library", bgj.this.q.Uv());
         var1.q("typeParameters", bgj.this.q.Uv());
         var1.q("superType", bgj.this.q.Uv());
         var1.q("constants", bgj.this.q.Uv());
         var1.q("declarationType", bgj.this.q.Uv());
         var1.q("invocationDispatcherCache", bgj.this.q.Uv());
         if (!bgj.this.q.Me || !bgj.this.q.PV) {
            var1.q("directImplementors", bgj.this.q.Uv());
            var1.q("directSubclasses", bgj.this.q.Uv());
         }

         if (!bgj.this.q.PV) {
            var1.q("allocationStub", bgj.this.q.Uv());
            var1.q("dependentCode", bgj.this.q.Uv());
         }
      }
   }

   class EE extends bgj.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgi.Ov.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgj.this.q.za());
         }
      }
   }

   class FL extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.WI.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgj.this.q.Uv());
         var1.q("type", bgj.this.q.Uv());
      }
   }

   class Fw extends bgj.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bgi.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgj.this.q.lm());
            var2.q("nullabity", bgj.this.q.nf());
            var2.q("instantiations", bgj.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgj.this.q.Uv());
            }
         }
      }
   }

   class GA extends bgj.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgi.Tq.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bgj.this.q.qa());
            var2.q("base", bgj.this.q.HF());
            var2.q("index", bgj.this.q.HF());
            long var3 = bgj.this.q.zz();
            var2.q("typeState", var3 >> (int)bgj.this.q.gP.Rr);
            var2.q("nullability", var3 & bgj.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgj.this.q.Uv());
         var1.q("hash", bgj.this.q.Uv());
         var1.q("bound", bgj.this.q.Uv());
      }
   }

   class HA extends bgj.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgi.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bgj.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bgj.this.q.Uv());
         var1.q("shape", bgj.this.q.Uv());
         var1.q("fieldTypes", bgj.this.q.Uv());
         var1.q("hash", bgj.this.q.Uv());
      }
   }

   class KZ extends bgj.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgj.this.q.jq;
         long var1 = bgj.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgj.this.q.nf() << (int)bgj.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgj.this.q.q(this.q);
            var7.q("data", var6);
            bgj.this.q.q(var7);
         }

         this.gO = bgj.this.q.jq;
         if (this.q == bgi.fn.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bgi.ZU.q() && this.q != bgi.fn.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgj.this.q.gO.position(var1);
            bgj.this.q.gO.i32();
            long var2;
            if (bgj.this.q.wF) {
               bgj.this.q.gO.i32();
               var2 = bgj.this.q.gO.i64();
            } else {
               bgj.this.q.gO.i32();
               var2 = bgj.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgj.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            if (!bgj.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgj.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgj.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgj.this.q.Uv());
         if (bgj.this.q.io) {
            if (!bgj.this.q.Me) {
               var1.q("resolvedUrl", bgj.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgj.this.q.Uv());
            var1.q("resolvedUrl", bgj.this.q.Uv());
            var1.q("lineStarts", bgj.this.q.Uv());
            var1.q("constantCoverage", bgj.this.q.Uv());
            var1.q("debugPositions", bgj.this.q.Uv());
            var1.q("kernelProgramInfo", bgj.this.q.Uv());
         }
      }
   }

   class ME extends bgj.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgj.this.q.jq;
         long var1 = bgj.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgj.this.q.nf();
            bgi var6 = (var4 & 1L) != 0L ? bgi.Sz : bgi.ZU;
            long var7 = var4 >> 1;
            bew var9 = bgj.this.q.q(var6.q());
            var9.q("length", var7);
            bgj.this.q.q(var9);
         }

         this.gO = bgj.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            long var3 = bgj.this.q.nf();
            long var5 = var3 >> 1;
            bgi var7 = (var3 & 1L) != 0L ? bgi.Sz : bgi.ZU;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bgi.ZU) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgj.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgj.this.q.zz();
                  var10 |= bgj.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bgj.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgj.this.q.jq;
         bgj.this.q.Ri = this.oW;
         long var1 = bgj.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgj.this.q.jq;
         this.q = bgj.this.q.jq;
         var1 = bgj.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgj.this.q.jq;
      }

      private void Dw() {
         int var1 = bgj.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgj.this.q.q(bgi.Me.q());
         bgj.this.q.q(var3);
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
         bew var3 = bgj.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgj.this.q.io) {
            var3.q("objectPool", bgj.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgj.this.q.Uv(), bgi.nf.toString(), bgi.oW.toString(), bgi.Wx.toString());
         var3.q("exceptionHandlers", bgj.this.q.Uv(), bgi.CE.toString());
         var3.q("pcDescriptors", bgj.this.q.Uv(), bgi.Gf.toString());
         var3.q("catchEntry", bgj.this.q.Uv());
         if (bgj.this.q.LK) {
            var3.q("compressedStackMaps", bgj.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgj.this.q.Uv(), bgi.of.toString());
         var3.q("codeSourceMap", bgj.this.q.Uv(), bgi.Ef.toString());
         if (!bgj.this.q.PV && bgj.this.q.LK) {
            var3.q("deoptInfoArray", bgj.this.q.Uv());
            var3.q("staticCallsTargetTable", bgj.this.q.Uv());
         }

         if (!bgj.this.q.Me) {
            var3.q("returnAddressMetadata", bgj.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgj.this.q.Gf ? bgj.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgj.this.q.PV) {
            long var3 = bgj.this.q.Rv[2 * bgj.this.q.ZT];
            long var5 = bgj.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgj.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgj.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgj.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("parent", bgj.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgj.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgj.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgj.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgj.this.q.Uv());
         var1.q("owner", bgj.this.q.Uv());
         var1.q("type", bgj.this.q.Uv());
         var1.q("initializerFunction", bgj.this.q.Uv());
      }
   }

   class SG extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("cache", bgj.this.q.Uv());
         }
      }
   }

   class ST extends bgj.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class Uz extends bgj.oM {
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
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgj.this.q.Uv());
         var1.q("hashMask", bgj.this.q.Uv());
         var1.q("data", bgj.this.q.Uv());
         var1.q("usedData", bgj.this.q.Uv());
         var1.q("deletedKeys", bgj.this.q.Uv());
      }
   }

   class Vj extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.iu.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgj.this.q.Uv());
         var1.q("length", bgj.this.q.Uv());
         var1.q("data", bgj.this.q.Uv());
      }
   }

   class Xa extends bgj.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgj.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgj.this.q.q(bgi.ZA.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgj.this.q.za());
            bgj.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgj.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgj.this.q.Uv());
         var1.q("argsDescriptor", bgj.this.q.Uv());
      }
   }

   class bK extends bgj.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgj.this.q.jq;
         long var1 = bgj.this.q.nf();
         this.RF = bgj.this.q.qa();
         this.xK = bgj.this.q.qa();
         bgj.this.q.q(var1, this.q);
         this.gO = bgj.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgj.this.q.gP.nf;
         int var2 = (int)bgj.this.q.q(this.xK * bgj.this.q.gP.gO, bgj.this.q.gP.gP);
         Long var3 = bgj.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgj.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgj.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgj.this.q.gP.gO)) {
               if (bgj.this.q.RF(var3, var6 / (int)bgj.this.q.gP.gO)) {
                  bgj.this.q.oQ();
               } else {
                  bgj.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgj.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgj.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgi.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgj.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgj.this.q.Uv());
         var1.q("arguments", bgj.this.q.Uv());
         var1.q("hash", bgj.this.q.Uv());
      }
   }

   class eM extends bgj.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bgi.fQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bgj.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bgj.this.q.Uv();
            }
         }
      }
   }

   class ej extends bgj.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgi.nq.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgj.this.q.Uv());
         var1.q("functionTypeArguments", bgj.this.q.Uv());
         var1.q("delayedTypeArguments", bgj.this.q.Uv());
         var1.q("function", bgj.this.q.Uv());
         var1.q("context", bgj.this.q.Uv());
         var1.q("hash", bgj.this.q.Uv());
      }
   }

   class eo extends bgj.oM {
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
            bew var2 = bgj.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgj.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgj.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgj.oM {
      @Override
      public void q() {
         this.RF(bgi.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgj.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bgj.oM {
      int q;

      jx(boolean var2, int var3) {
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
            bew var2 = bgj.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgj.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class kY extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgj.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgj.this.q.Uv());
         var1.q("argsDescriptor", bgj.this.q.Uv());
         var1.q("buckets", bgj.this.q.Uv());
         var1.q("mask", bgj.this.q.Uv());
      }
   }

   class nI extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            if (bgj.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgj.this.q.Uv());
            }

            var2.q("parentFunction", bgj.this.q.Uv());
            var2.q("closure", bgj.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgj.this.q.nf());
         }
      }
   }

   class oL extends bgj.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgi.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgj.this.q.zz();
            var2.q("typeState", var3 >> (int)bgj.this.q.gP.Rr);
            var2.q("nullability", var3 & bgj.this.q.gP.EB);
            var2.q("packedParameterCounts", bgj.this.q.io());
            var2.q("packedTypeParameterCounts", bgj.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgj.this.q.Uv());
         var1.q("typeParameters", bgj.this.q.Uv());
         var1.q("resultType", bgj.this.q.Uv());
         var1.q("parameterTypes", bgj.this.q.Uv());
         var1.q("namedParameterNames", bgj.this.q.Uv());
         var1.q("hash", bgj.this.q.Uv());
      }
   }

   abstract class oM implements bgj.Bu {
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
         this.oW = bgj.this.q.jq;
         long var2 = bgj.this.q.nf();
         bgj.this.q.q(var2, var1);
         this.gO = bgj.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgj.this.q.jq;
         long var2 = bgj.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgj.this.q.q(var1);
            long var4 = bgj.this.q.nf();
            var3.q("length", var4);
            bgj.this.q.q(var3);
         });
         this.gO = bgj.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgj.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgj.this.q.nf();
            long var1 = bgj.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgj.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgj.oM {
      @Override
      public void q() {
         this.RF(bgi.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            long var3 = bgj.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgj.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgj.this.q.gP());
               var9.put("outerTryIndex", bgj.this.q.LK());
               var9.put("needsStacktrace", bgj.this.q.Dw());
               var9.put("hasCatchAll", bgj.this.q.Dw());
               var9.put("isGenerated", bgj.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgj.oM {
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
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgj.this.q.Uv());
         var1.q("hashMask", bgj.this.q.Uv());
         var1.q("data", bgj.this.q.Uv());
         var1.q("usedData", bgj.this.q.Uv());
         var1.q("deletedKeys", bgj.this.q.Uv());
      }
   }

   class qx extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.Tq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgj.this.q.Uv());
         var1.q("flags", bgj.this.q.Uv());
         var1.q("bounds", bgj.this.q.Uv());
         var1.q("defaults", bgj.this.q.Uv());
      }
   }

   class ry extends bgj.oM {
      int q;

      public ry(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgj.this.q.HF(this.q);
            var2.q("data", bgj.this.q.Dw(var4));
         }
      }
   }

   class tl extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgj.this.q.qa());
            var2.q("numImports", bgj.this.q.HF());
            var2.q("loadState", bgj.this.q.JY());
            var2.q("flags", bgj.this.q.zz());
            if (!bgj.this.q.PV && !bgj.this.q.io) {
               var2.q("kernelOffset", bgj.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgj.this.q.Uv());
         var1.q("url", bgj.this.q.Uv());
         var1.q("privateKey", bgj.this.q.Uv());
         var1.q("dictionary", bgj.this.q.Uv());
         var1.q("metadata", bgj.this.q.Uv());
         var1.q("toplevelClass", bgj.this.q.Uv());
         var1.q("usedScripts", bgj.this.q.Uv());
         var1.q("loadingUnit", bgj.this.q.Uv());
         var1.q("imports", bgj.this.q.Uv());
         var1.q("exports", bgj.this.q.Uv());
         if (!bgj.this.q.io) {
            var1.q("dependencies", bgj.this.q.Uv());
            var1.q("kernelData", bgj.this.q.Uv());
         }
      }
   }

   class tw extends bgj.oM {
      @Override
      public void q() {
         this.RF(bgi.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            long var3 = bgj.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgj.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            if (!bgj.this.q.PV && !bgj.this.q.io) {
               var2.q("libraryKernelOffset", bgj.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bgj.this.q.Uv());
         var1.q("originClass", bgj.this.q.Uv());
         var1.q("script", bgj.this.q.Uv());
         if (!bgj.this.q.io) {
            var1.q("libraryKernelData", bgj.this.q.Uv());
         }
      }
   }

   class vb extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            if (!bgj.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgj.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgj.this.q.Ri + (int)var3 - 1;
               bew var6 = bgj.this.q.xK(var5);
               if (var6.q().equals(bgi.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgj.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgj.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgj.this.q.Uv());
         var1.q("owner", bgj.this.q.Uv());
         var1.q("signature", bgj.this.q.Uv(), bgi.AB.toString());
         var1.q("data", bgj.this.q.Uv());
      }
   }

   class vn extends bgj.oM {
      @Override
      public void q() {
         this.q(bgi.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgj.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgj.this.q.Uv());
         var1.q("cSignature", bgj.this.q.Uv());
         var1.q("callbackTarget", bgj.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgj.this.q.Uv());
      }
   }

   class zJ extends bgj.oM {
      @Override
      public void q() {
         this.RF(bgi.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgj.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgj.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
