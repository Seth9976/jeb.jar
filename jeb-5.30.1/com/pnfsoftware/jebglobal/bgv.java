package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgv {
   private static final ILogger RF = GlobalLog.getLogger(bgv.class);
   bgx q;

   bgv(bgx var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgv.Bu q(int var1, boolean var2, boolean var3) {
      bgu var4 = bgu.q(var1);
      if (var1 >= bgu.uz.q() || var1 == bgu.zx.q()) {
         return new bgv.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgv.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Gf:
               case Ef:
               case cC:
                  return new bgv.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bgv.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Gf:
               Assert.a(!var2);
               return new bgv.zJ();
            case Ef:
               Assert.a(!var2);
               return new bgv.iZ();
            case cC:
               Assert.a(!var2);
               return new bgv.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bgv.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgv.CU();
            case gP:
               return new bgv.qx();
            case Ri:
               return new bgv.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgv.vX();
            case nf:
               Assert.a(!var2);
               return new bgv.vb();
            case za:
               Assert.a(!var2);
               return new bgv.nI();
            case lm:
               Assert.a(!var2);
               return new bgv.vn();
            case zz:
               Assert.a(!var2);
               return new bgv.PY();
            case JY:
               Assert.a(!var2);
               return new bgv.LR();
            case HF:
               Assert.a(!var2);
               return new bgv.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bgv.Nt();
            case KT:
               Assert.a(!var2);
               return new bgv.CI();
            case CE:
               Assert.a(!var2);
               return new bgv.qV();
            case wF:
               Assert.a(!var2);
               return null;
            case If:
               Assert.a(!var2);
               return null;
            case jq:
               Assert.a(!var2);
               return new bgv.ry();
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return new bgv.SG();
            case Bu:
               Assert.a(!var2);
               return new bgv.Nz();
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
               return new bgv.ct(var2, var3);
            case AB:
               return new bgv.oL(var2, var3);
            case WI:
               return new bgv.GA(var2, var3);
            case lF:
               return new bgv.ej(var2);
            case tW:
               return new bgv.Xa(var2);
            case ZA:
               return new bgv.EE(var2);
            case os:
               Assert.a(!var2);
               return new bgv.Vj();
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
               return new bgv.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bgv.qa(var2, var1);
            case vC:
            case of:
               return new bgv.eo(var2, var1);
            case Hk:
               return new bgv.Zu(var2, var1);
            case CY:
               return new bgv.HA(var2, var3);
            case PQ:
               return new bgv.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgv.oM {
      @Override
      public void q() {
         this.RF(bgu.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgv.this.q.zz();
               Object[] var10000 = new Object[]{var6};
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bgv.this.q.LK(var6);
               Assert.a(var8 != bgv.this.q.gP.mI);
               if (var8 == bgv.this.q.gP.Dz) {
                  int var9 = bgv.this.q.oW(var6);
                  if (var9 == bgv.this.q.gP.xW) {
                     long var10 = bgv.this.q.Uv();
                     var10000 = new Object[]{var10};
                     var7.put("rawObj", var10);
                  } else if (var9 == bgv.this.q.gP.KT) {
                     long var12 = bgv.this.q.za();
                     var10000 = new Object[]{var12};
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bgv.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var10000 = new Object[0];
                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bgv.this.q.gP.jq && var8 != bgv.this.q.gP.ui) {
                  if (var8 != bgv.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bgv.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgv.this.q.jq;
         long var1 = bgv.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgv.this.q.q(bgu.oW.q());
            var2.q("id", bgv.this.q.oW());
            bgv.this.q.q(var2);
         });
         this.RF = bgv.this.q.jq;
         this.q(bgu.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgv.this.q.xK(var6);
               this.q(var8);
               int var9 = bgv.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgv.this.q.PV && !bgv.this.q.io) {
                  var8.q("kernelOffset", bgv.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgv.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgv.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgv.this.q.za());
               if (!bgv.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgv.this.q.za());
               var8.q("numNativeFields", bgv.this.q.nf());
               if (!bgv.this.q.PV) {
                  Assert.a(!bgv.this.q.io);
                  var8.q("tokenPos", bgv.this.q.gO());
                  var8.q("endTokenPos", bgv.this.q.gO());
               }

               var8.q("stateBits", bgv.this.q.nf());
               if (bgv.this.q.PV) {
                  if (var7) {
                     bgv.this.q.nf();
                  } else if (!bgv.this.q.gP.q(var9)) {
                     bgv.this.q.TX.put(var9, bgv.this.q.nf());
                  }
               }

               bgv.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgv.this.q.Uv());
         if (!bgv.this.q.Me) {
            var1.q("userName", bgv.this.q.Uv());
         }

         var1.q("functions", bgv.this.q.Uv());
         var1.q("functionsHashTable", bgv.this.q.Uv());
         var1.q("fields", bgv.this.q.Uv());
         var1.q("offsetInWordsToField", bgv.this.q.Uv());
         var1.q("interfaces", bgv.this.q.Uv());
         var1.q("script", bgv.this.q.Uv());
         var1.q("library", bgv.this.q.Uv());
         var1.q("typeParameters", bgv.this.q.Uv());
         var1.q("superType", bgv.this.q.Uv());
         var1.q("constants", bgv.this.q.Uv());
         var1.q("declarationType", bgv.this.q.Uv());
         var1.q("invocationDispatcherCache", bgv.this.q.Uv());
         if (!bgv.this.q.Me || !bgv.this.q.PV) {
            var1.q("directImplementors", bgv.this.q.Uv());
            var1.q("directSubclasses", bgv.this.q.Uv());
         }

         if (!bgv.this.q.PV) {
            var1.q("allocationStub", bgv.this.q.Uv());
            var1.q("dependentCode", bgv.this.q.Uv());
         }
      }
   }

   class EE extends bgv.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgu.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgv.this.q.PV());
         }
      }
   }

   class FL extends bgv.oM {
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
            bew var2 = bgv.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgv.this.q.HF(this.q);
            var2.q("data", bgv.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bgv.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bgu.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgv.this.q.qa());
            var2.q("nullabity", bgv.this.q.nf());
            var2.q("instantiations", bgv.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgv.this.q.Uv());
            }
         }
      }
   }

   class GA extends bgv.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgu.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bgv.this.q.HF());
            var2.q("index", bgv.this.q.HF());
            long var3 = bgv.this.q.zz();
            var2.q("typeState", var3 >> (int)bgv.this.q.gP.Rr);
            var2.q("nullability", var3 & bgv.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgv.this.q.Uv());
         var1.q("hash", bgv.this.q.Uv());
         var1.q("owner", bgv.this.q.Uv());
      }
   }

   class HA extends bgv.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgu.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bgv.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bgv.this.q.Uv());
         var1.q("hash", bgv.this.q.Uv());
         var1.q("shape", bgv.this.q.Uv());
         var1.q("fieldTypes", bgv.this.q.Uv());
      }
   }

   class KZ extends bgv.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgv.this.q.jq;
         long var1 = bgv.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgv.this.q.nf() << (int)bgv.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgv.this.q.q(this.q);
            var7.q("data", var6);
            bgv.this.q.q(var7);
         }

         this.gO = bgv.this.q.jq;
         if (this.q == bgu.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bgu.fn.q() && this.q != bgu.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgv.this.q.gO.position(var1);
            bgv.this.q.gO.i32();
            long var2;
            if (bgv.this.q.wF) {
               bgv.this.q.gO.i32();
               var2 = bgv.this.q.gO.i64();
            } else {
               bgv.this.q.gO.i32();
               var2 = bgv.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgv.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            if (!bgv.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgv.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgv.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgv.this.q.Uv());
         if (bgv.this.q.io) {
            if (!bgv.this.q.Me) {
               var1.q("resolvedUrl", bgv.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgv.this.q.Uv());
            var1.q("resolvedUrl", bgv.this.q.Uv());
            var1.q("lineStarts", bgv.this.q.Uv());
            var1.q("constantCoverage", bgv.this.q.Uv());
            var1.q("debugPositions", bgv.this.q.Uv());
            var1.q("kernelProgramInfo", bgv.this.q.Uv());
         }
      }
   }

   class ME extends bgv.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgv.this.q.jq;
         long var1 = bgv.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgv.this.q.nf();
            bgu var6 = (var4 & 1L) != 0L ? bgu.ZU : bgu.fn;
            long var7 = var4 >> 1;
            bew var9 = bgv.this.q.q(var6.q());
            var9.q("length", var7);
            bgv.this.q.q(var9);
         }

         this.gO = bgv.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            long var3 = bgv.this.q.nf();
            long var5 = var3 >> 1;
            bgu var7 = (var3 & 1L) != 0L ? bgu.ZU : bgu.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bgu.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgv.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgv.this.q.zz();
                  var10 |= bgv.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bgv.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgv.this.q.jq;
         bgv.this.q.Ri = this.oW;
         long var1 = bgv.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgv.this.q.jq;
         this.q = bgv.this.q.jq;
         var1 = bgv.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgv.this.q.jq;
      }

      private void Dw() {
         int var1 = bgv.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgv.this.q.q(bgu.Me.q());
         bgv.this.q.q(var3);
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
         bew var3 = bgv.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgv.this.q.io) {
            var3.q("objectPool", bgv.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgv.this.q.Uv(), bgu.nf.toString(), bgu.oW.toString(), bgu.Wx.toString());
         var3.q("exceptionHandlers", bgv.this.q.Uv(), bgu.CE.toString());
         var3.q("pcDescriptors", bgv.this.q.Uv(), bgu.Gf.toString());
         var3.q("catchEntry", bgv.this.q.Uv());
         if (bgv.this.q.LK) {
            var3.q("compressedStackMaps", bgv.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgv.this.q.Uv(), bgu.vC.toString());
         var3.q("codeSourceMap", bgv.this.q.Uv(), bgu.Ef.toString());
         if (!bgv.this.q.PV && bgv.this.q.LK) {
            var3.q("deoptInfoArray", bgv.this.q.Uv());
            var3.q("staticCallsTargetTable", bgv.this.q.Uv());
         }

         if (!bgv.this.q.Me) {
            var3.q("returnAddressMetadata", bgv.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgv.this.q.Gf ? bgv.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgv.this.q.PV) {
            long var3 = bgv.this.q.Rv[2 * bgv.this.q.ZT];
            long var5 = bgv.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgv.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgv.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgv.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("parent", bgv.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgv.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgv.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgv.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgv.this.q.Uv());
         var1.q("owner", bgv.this.q.Uv());
         var1.q("type", bgv.this.q.Uv());
         var1.q("initializerFunction", bgv.this.q.Uv());
      }
   }

   class SG extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("cache", bgv.this.q.Uv());
            var2.q("numInputs", bgv.this.q.io());
            var2.q("numOccupied", bgv.this.q.io());
         }
      }
   }

   class Uz extends bgv.oM {
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
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgv.this.q.Uv());
         var1.q("hashMask", bgv.this.q.Uv());
         var1.q("data", bgv.this.q.Uv());
         var1.q("usedData", bgv.this.q.Uv());
         var1.q("deletedKeys", bgv.this.q.Uv());
      }
   }

   class Vj extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgv.this.q.Uv());
         var1.q("length", bgv.this.q.Uv());
         var1.q("data", bgv.this.q.Uv());
      }
   }

   class Xa extends bgv.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgv.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgv.this.q.q(bgu.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgv.this.q.Hk());
            bgv.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bgv.oM {
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
            bew var2 = bgv.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgv.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bgv.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgv.this.q.jq;
         long var1 = bgv.this.q.nf();
         this.RF = bgv.this.q.qa();
         this.xK = bgv.this.q.qa();
         bgv.this.q.q(var1, this.q);
         this.gO = bgv.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgv.this.q.gP.nf;
         int var2 = (int)bgv.this.q.q(this.xK * bgv.this.q.gP.gO, bgv.this.q.gP.gP);
         Long var3 = bgv.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgv.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgv.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgv.this.q.gP.gO)) {
               if (bgv.this.q.RF(var3, var6 / (int)bgv.this.q.gP.gO)) {
                  bgv.this.q.oQ();
               } else {
                  bgv.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgv.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgv.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgu.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgv.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgv.this.q.Uv());
         var1.q("hash", bgv.this.q.Uv());
         var1.q("arguments", bgv.this.q.Uv());
      }
   }

   class eM extends bgv.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bgu.PQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bgv.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bgv.this.q.Uv();
            }
         }
      }
   }

   class ej extends bgv.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgu.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgv.this.q.Uv());
         var1.q("functionTypeArguments", bgv.this.q.Uv());
         var1.q("delayedTypeArguments", bgv.this.q.Uv());
         var1.q("function", bgv.this.q.Uv());
         var1.q("context", bgv.this.q.Uv());
         var1.q("hash", bgv.this.q.Uv());
      }
   }

   class eo extends bgv.oM {
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
            bew var2 = bgv.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgv.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgv.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgv.oM {
      @Override
      public void q() {
         this.RF(bgu.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgv.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bgv.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgv.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgv.this.q.Uv());
         var1.q("argsDescriptor", bgv.this.q.Uv());
         var1.q("buckets", bgv.this.q.Uv());
         var1.q("mask", bgv.this.q.Uv());
      }
   }

   class nI extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            if (bgv.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgv.this.q.Uv());
            }

            var2.q("parentFunction", bgv.this.q.Uv());
            var2.q("closure", bgv.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgv.this.q.nf());
         }
      }
   }

   class oL extends bgv.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgu.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgv.this.q.zz();
            var2.q("typeState", var3 >> (int)bgv.this.q.gP.Rr);
            var2.q("nullability", var3 & bgv.this.q.gP.EB);
            var2.q("packedParameterCounts", bgv.this.q.io());
            var2.q("packedTypeParameterCounts", bgv.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgv.this.q.Uv());
         var1.q("hash", bgv.this.q.Uv());
         var1.q("typeParameters", bgv.this.q.Uv());
         var1.q("resultType", bgv.this.q.Uv());
         var1.q("parameterTypes", bgv.this.q.Uv());
         var1.q("namedParameterNames", bgv.this.q.Uv());
      }
   }

   abstract class oM implements bgv.Bu {
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
         this.oW = bgv.this.q.jq;
         long var2 = bgv.this.q.nf();
         bgv.this.q.q(var2, var1);
         this.gO = bgv.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgv.this.q.jq;
         long var2 = bgv.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgv.this.q.q(var1);
            long var4 = bgv.this.q.nf();
            var3.q("length", var4);
            bgv.this.q.q(var3);
         });
         this.gO = bgv.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgv.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgv.this.q.nf();
            long var1 = bgv.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgv.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgv.oM {
      @Override
      public void q() {
         this.RF(bgu.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            long var3 = bgv.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgv.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgv.this.q.gP());
               var9.put("outerTryIndex", bgv.this.q.LK());
               var9.put("needsStacktrace", bgv.this.q.Dw());
               var9.put("hasCatchAll", bgv.this.q.Dw());
               var9.put("isGenerated", bgv.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgv.oM {
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
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgv.this.q.Uv());
         var1.q("hashMask", bgv.this.q.Uv());
         var1.q("data", bgv.this.q.Uv());
         var1.q("usedData", bgv.this.q.Uv());
         var1.q("deletedKeys", bgv.this.q.Uv());
      }
   }

   class qx extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgv.this.q.Uv());
         var1.q("flags", bgv.this.q.Uv());
         var1.q("bounds", bgv.this.q.Uv());
         var1.q("defaults", bgv.this.q.Uv());
      }
   }

   class ry extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgv.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgv.this.q.Uv());
         var1.q("argsDescriptor", bgv.this.q.Uv());
      }
   }

   class tl extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgv.this.q.qa());
            var2.q("numImports", bgv.this.q.HF());
            var2.q("loadState", bgv.this.q.JY());
            var2.q("flags", bgv.this.q.zz());
            if (!bgv.this.q.PV && !bgv.this.q.io) {
               var2.q("kernelOffset", bgv.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgv.this.q.Uv());
         var1.q("url", bgv.this.q.Uv());
         var1.q("privateKey", bgv.this.q.Uv());
         var1.q("dictionary", bgv.this.q.Uv());
         var1.q("metadata", bgv.this.q.Uv());
         var1.q("toplevelClass", bgv.this.q.Uv());
         var1.q("usedScripts", bgv.this.q.Uv());
         var1.q("loadingUnit", bgv.this.q.Uv());
         var1.q("imports", bgv.this.q.Uv());
         var1.q("exports", bgv.this.q.Uv());
         if (!bgv.this.q.io) {
            var1.q("dependencies", bgv.this.q.Uv());
            var1.q("kernelProgramInfo", bgv.this.q.Uv());
         }
      }
   }

   class tw extends bgv.oM {
      @Override
      public void q() {
         this.RF(bgu.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            long var3 = bgv.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgv.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            if (!bgv.this.q.PV && !bgv.this.q.io) {
               var2.q("libraryKernelOffset", bgv.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bgv.this.q.Uv());
         var1.q("script", bgv.this.q.Uv());
         if (!bgv.this.q.io) {
            var1.q("libraryKernelData", bgv.this.q.Uv());
         }
      }
   }

   class vb extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            if (!bgv.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgv.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgv.this.q.Ri + (int)var3 - 1;
               bew var6 = bgv.this.q.xK(var5);
               if (var6.q().equals(bgu.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgv.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgv.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgv.this.q.Uv());
         var1.q("owner", bgv.this.q.Uv());
         var1.q("signature", bgv.this.q.Uv(), bgu.AB.toString());
         var1.q("data", bgv.this.q.Uv());
      }
   }

   class vn extends bgv.oM {
      @Override
      public void q() {
         this.q(bgu.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgv.this.q.nf());
            var2.q("callbackKind", bgv.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgv.this.q.Uv());
         var1.q("cSignature", bgv.this.q.Uv());
         var1.q("callbackTarget", bgv.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgv.this.q.Uv());
      }
   }

   class zJ extends bgv.oM {
      @Override
      public void q() {
         this.RF(bgu.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgv.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgv.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
