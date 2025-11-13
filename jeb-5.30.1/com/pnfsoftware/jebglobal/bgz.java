package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bgz {
   private static final ILogger RF = GlobalLog.getLogger(bgz.class);
   bhb q;

   bgz(bhb var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bgz.Bu q(int var1, boolean var2, boolean var3) {
      bgy var4 = bgy.q(var1);
      if (var1 >= bgy.yW.q() || var1 == bgy.zx.q()) {
         return new bgz.bK(var1, var2);
      } else if (this.q.za(var1)) {
         Assert.a(!var2);
         return new bgz.FL(var1);
      } else {
         if (!this.q.xW && this.q.KT) {
            switch (var4) {
               case Gf:
               case Ef:
               case cC:
                  return new bgz.KZ(var2, var3, var1);
               case fn:
               case ZU:
               case iu:
                  if (var3) {
                     return new bgz.KZ(var2, var3, var1);
                  }
            }
         }

         switch (var4) {
            case Gf:
               Assert.a(!var2);
               return new bgz.zJ();
            case Ef:
               Assert.a(!var2);
               return new bgz.iZ();
            case cC:
               Assert.a(!var2);
               return new bgz.tw();
            case fn:
            case ZU:
            default:
               return null;
            case iu:
               return new bgz.ME(var2, var3 && this.q.Uv != null);
            case oW:
               Assert.a(!var2);
               return new bgz.CU();
            case gP:
               return new bgz.qx();
            case Ri:
               return new bgz.Fw(var2, var3);
            case gO:
               Assert.a(!var2);
               return new bgz.vX();
            case nf:
               Assert.a(!var2);
               return new bgz.vb();
            case za:
               Assert.a(!var2);
               return new bgz.nI();
            case lm:
               Assert.a(!var2);
               return new bgz.vn();
            case zz:
               Assert.a(!var2);
               return new bgz.PY();
            case JY:
               Assert.a(!var2);
               return new bgz.LR();
            case HF:
               Assert.a(!var2);
               return new bgz.tl();
            case LK:
               Assert.a(!var2);
               throw new ToDoException();
            case Me:
               Assert.a(!var2);
               return new bgz.Nt();
            case KT:
               Assert.a(!var2);
               return new bgz.CI();
            case CE:
               Assert.a(!var2);
               return new bgz.qV();
            case wF:
               Assert.a(!var2);
               return null;
            case If:
               Assert.a(!var2);
               return null;
            case jq:
               Assert.a(!var2);
               return new bgz.ry();
            case Rr:
               Assert.a(!var2);
               return null;
            case EB:
               Assert.a(!var2);
               return null;
            case Xo:
               Assert.a(!var2);
               return new bgz.SG();
            case Bu:
               Assert.a(!var2);
               return new bgz.Nz();
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
               return new bgz.ct(var2, var3);
            case AB:
               return new bgz.oL(var2, var3);
            case WI:
               return new bgz.GA(var2, var3);
            case lF:
               return new bgz.ej(var2);
            case tW:
               return new bgz.Xa(var2);
            case ZA:
               return new bgz.EE(var2);
            case nv:
            case Lj:
            case LL:
               return null;
            case os:
               Assert.a(!var2);
               return new bgz.Vj();
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
               return new bgz.Uz(var2, var1);
            case jh:
               throw new RuntimeException();
            case Jf:
               return new bgz.qa(var2, var1);
            case vC:
            case of:
               return new bgz.eo(var2, var1);
            case Hk:
               return new bgz.Zu(var2, var1);
            case CY:
               return new bgz.HA(var2, var3);
            case PQ:
               return new bgz.eM(var2);
         }
      }
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bgz.oM {
      @Override
      public void q() {
         this.RF(bgy.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bgz.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               var4.add(var7);
               int var8 = bgz.this.q.LK(var6);
               Assert.a(var8 != bgz.this.q.gP.mI);
               if (var8 == bgz.this.q.gP.Dz) {
                  int var9 = bgz.this.q.oW(var6);
                  if (var9 == bgz.this.q.gP.xW) {
                     long var10 = bgz.this.q.Uv();
                     var7.put("rawObj", var10);
                  } else if (var9 == bgz.this.q.gP.KT) {
                     long var12 = bgz.this.q.za();
                     var7.put("rawValue", var12);
                  } else {
                     if (var9 != bgz.this.q.gP.Gf) {
                        throw new RuntimeException("Unknown typeBits value");
                     }

                     var7.put("rawValue", "lazy link entry");
                  }
               } else if (var8 != bgz.this.q.gP.jq && var8 != bgz.this.q.gP.ui) {
                  if (var8 != bgz.this.q.gP.TX) {
                     throw new RuntimeException("Unknown snapshotBehaviorBits value");
                  }

                  var7.put("rawValue", 0L);
               }
            }
         }
      }
   }

   class CU extends bgz.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bgz.this.q.jq;
         long var1 = bgz.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bgz.this.q.q(bgy.oW.q());
            var2.q("id", bgz.this.q.oW());
            bgz.this.q.q(var2);
         });
         this.RF = bgz.this.q.jq;
         this.q(bgy.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bgz.this.q.xK(var6);
               this.q(var8);
               int var9 = bgz.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bgz.this.q.PV && !bgz.this.q.io) {
                  var8.q("kernelOffset", bgz.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bgz.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bgz.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bgz.this.q.za());
               if (!bgz.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bgz.this.q.za());
               var8.q("numNativeFields", bgz.this.q.nf());
               if (!bgz.this.q.PV) {
                  Assert.a(!bgz.this.q.io);
                  var8.q("tokenPos", bgz.this.q.gO());
                  var8.q("endTokenPos", bgz.this.q.gO());
               }

               var8.q("stateBits", bgz.this.q.nf());
               if (bgz.this.q.PV) {
                  if (var7) {
                     bgz.this.q.nf();
                  } else if (!bgz.this.q.gP.q(var9)) {
                     bgz.this.q.TX.put(var9, bgz.this.q.nf());
                  }
               }

               bgz.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgz.this.q.Uv());
         if (!bgz.this.q.Me) {
            var1.q("userName", bgz.this.q.Uv());
         }

         var1.q("functions", bgz.this.q.Uv());
         var1.q("functionsHashTable", bgz.this.q.Uv());
         var1.q("fields", bgz.this.q.Uv());
         var1.q("offsetInWordsToField", bgz.this.q.Uv());
         var1.q("interfaces", bgz.this.q.Uv());
         var1.q("script", bgz.this.q.Uv());
         var1.q("library", bgz.this.q.Uv());
         var1.q("typeParameters", bgz.this.q.Uv());
         var1.q("superType", bgz.this.q.Uv());
         var1.q("constants", bgz.this.q.Uv());
         var1.q("declarationType", bgz.this.q.Uv());
         var1.q("invocationDispatcherCache", bgz.this.q.Uv());
         if (!bgz.this.q.Me || !bgz.this.q.PV) {
            var1.q("directImplementors", bgz.this.q.Uv());
            var1.q("directSubclasses", bgz.this.q.Uv());
         }

         if (!bgz.this.q.PV) {
            var1.q("allocationStub", bgz.this.q.Uv());
            var1.q("dependentCode", bgz.this.q.Uv());
         }
      }
   }

   class EE extends bgz.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgy.ZA.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bgz.this.q.PV());
         }
      }
   }

   class FL extends bgz.oM {
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
            bew var2 = bgz.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bgz.this.q.HF(this.q);
            var2.q("data", bgz.this.q.Dw(var4));
         }
      }
   }

   class Fw extends bgz.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bgy.Ri.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bgz.this.q.qa());
            var2.q("nullabity", bgz.this.q.nf());
            var2.q("instantiations", bgz.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bgz.this.q.Uv());
            }
         }
      }
   }

   class GA extends bgz.oM {
      GA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgy.WI.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("base", bgz.this.q.HF());
            var2.q("index", bgz.this.q.HF());
            long var3 = bgz.this.q.zz();
            var2.q("typeState", var3 >> (int)bgz.this.q.gP.Rr);
            var2.q("nullability", var3 & bgz.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgz.this.q.Uv());
         var1.q("hash", bgz.this.q.Uv());
         var1.q("owner", bgz.this.q.Uv());
      }
   }

   class HA extends bgz.oM {
      HA(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgy.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("flags", bgz.this.q.zz());
         }
      }

      private void q(bew var1) {
         var1.q("typeTestStub", bgz.this.q.Uv());
         var1.q("hash", bgz.this.q.Uv());
         var1.q("shape", bgz.this.q.Uv());
         var1.q("fieldTypes", bgz.this.q.Uv());
      }
   }

   class KZ extends bgz.oM {
      int q;

      KZ(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bgz.this.q.jq;
         long var1 = bgz.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bgz.this.q.nf() << (int)bgz.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bgz.this.q.q(this.q);
            var7.q("data", var6);
            bgz.this.q.q(var7);
         }

         this.gO = bgz.this.q.jq;
         if (this.q == bgy.iu.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bgy.fn.q() && this.q != bgy.iu.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bgz.this.q.gO.position(var1);
            bgz.this.q.gO.i32();
            long var2;
            if (bgz.this.q.wF) {
               bgz.this.q.gO.i32();
               var2 = bgz.this.q.gO.i64();
            } else {
               bgz.this.q.gO.i32();
               var2 = bgz.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bgz.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class LR extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            if (!bgz.this.q.PV) {
               var2.q("flagsAndMaxPosition", bgz.this.q.qa());
            }

            var2.q("kernelScriptIndex", bgz.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bgz.this.q.Uv());
         if (bgz.this.q.io) {
            if (!bgz.this.q.Me) {
               var1.q("resolvedUrl", bgz.this.q.Uv());
            }
         } else {
            var1.q("resolvedUrl", bgz.this.q.Uv());
            var1.q("resolvedUrl", bgz.this.q.Uv());
            var1.q("lineStarts", bgz.this.q.Uv());
            var1.q("constantCoverage", bgz.this.q.Uv());
            var1.q("debugPositions", bgz.this.q.Uv());
            var1.q("kernelProgramInfo", bgz.this.q.Uv());
         }
      }
   }

   class ME extends bgz.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bgz.this.q.jq;
         long var1 = bgz.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bgz.this.q.nf();
            bgy var6 = (var4 & 1L) != 0L ? bgy.ZU : bgy.fn;
            long var7 = var4 >> 1;
            bew var9 = bgz.this.q.q(var6.q());
            var9.q("length", var7);
            bgz.this.q.q(var9);
         }

         this.gO = bgz.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            long var3 = bgz.this.q.nf();
            long var5 = var3 >> 1;
            bgy var7 = (var3 & 1L) != 0L ? bgy.ZU : bgy.fn;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bgy.fn) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bgz.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bgz.this.q.zz();
                  var10 |= bgz.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class Nt extends bgz.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bgz.this.q.jq;
         bgz.this.q.Ri = this.oW;
         long var1 = bgz.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bgz.this.q.jq;
         this.q = bgz.this.q.jq;
         var1 = bgz.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bgz.this.q.jq;
      }

      private void Dw() {
         int var1 = bgz.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         Assert.a(!var2);
         bew var3 = bgz.this.q.q(bgy.Me.q());
         bgz.this.q.q(var3);
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
         bew var3 = bgz.this.q.xK(var1);
         this.q(var3, var2);
         if (!bgz.this.q.io) {
            var3.q("objectPool", bgz.this.q.Uv());
         } else {
            var3.q("objectPool", null);
         }

         var3.q("owner", bgz.this.q.Uv(), bgy.nf.toString(), bgy.oW.toString(), bgy.Wx.toString());
         var3.q("exceptionHandlers", bgz.this.q.Uv(), bgy.CE.toString());
         var3.q("pcDescriptors", bgz.this.q.Uv(), bgy.Gf.toString());
         var3.q("catchEntry", bgz.this.q.Uv());
         if (bgz.this.q.LK) {
            var3.q("compressedStackMaps", bgz.this.q.Uv());
         } else {
            var3.q("compressedStackMaps", null);
         }

         var3.q("inlinedIdToFunction", bgz.this.q.Uv(), bgy.vC.toString());
         var3.q("codeSourceMap", bgz.this.q.Uv(), bgy.Ef.toString());
         if (!bgz.this.q.PV && bgz.this.q.LK) {
            var3.q("deoptInfoArray", bgz.this.q.Uv());
            var3.q("staticCallsTargetTable", bgz.this.q.Uv());
         }

         if (!bgz.this.q.Me) {
            var3.q("returnAddressMetadata", bgz.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bgz.this.q.Gf ? bgz.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }

         Object[] var10000 = new Object[]{var1, var3.xK("stateBits"), (Long)var3.q("entryPoint", Long.class), var3.q("owner", Long.class)};
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bgz.this.q.PV) {
            long var3 = bgz.this.q.Rv[2 * bgz.this.q.ZT];
            long var5 = bgz.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bgz.this.q.gP.oQ : 0L;
            long var12 = var9 ? bgz.this.q.gP.PV : 0L;
            long var14 = var3 + var10;
            long var16 = var3 + var12;
            bgz.this.q.ZT++;
            var1.q("entryPoint", var14);
            var1.q("uncheckedEntryPoint", var14 + var7);
            var1.q("monomorphicEntryPoint", var16);
            var1.q("monomorphicUncheckedEntryPoint", var16 + var7);
         } else {
            throw new ToDoException();
         }
      }
   }

   class Nz extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.Bu.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("parent", bgz.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bgz.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class PY extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("kindBits", bgz.this.q.gP());
            var2.q("hostOffsetOrFieldId", bgz.this.q.Uv());
         }
      }

      void q(bew var1) {
         var1.q("name", bgz.this.q.Uv());
         var1.q("owner", bgz.this.q.Uv());
         var1.q("type", bgz.this.q.Uv());
         var1.q("initializerFunction", bgz.this.q.Uv());
      }
   }

   class SG extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("cache", bgz.this.q.Uv());
            var2.q("numInputs", bgz.this.q.io());
            var2.q("numOccupied", bgz.this.q.io());
         }
      }
   }

   class Uz extends bgz.oM {
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
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgz.this.q.Uv());
         var1.q("hashMask", bgz.this.q.Uv());
         var1.q("data", bgz.this.q.Uv());
         var1.q("usedData", bgz.this.q.Uv());
         var1.q("deletedKeys", bgz.this.q.Uv());
      }
   }

   class Vj extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.os.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bgz.this.q.Uv());
         var1.q("length", bgz.this.q.Uv());
         var1.q("data", bgz.this.q.Uv());
      }
   }

   class Xa extends bgz.oM {
      Xa(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bgz.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bgz.this.q.q(bgy.tW.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bgz.this.q.Hk());
            bgz.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class Zu extends bgz.oM {
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
            bew var2 = bgz.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgz.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class bK extends bgz.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bgz.this.q.jq;
         long var1 = bgz.this.q.nf();
         this.RF = bgz.this.q.qa();
         this.xK = bgz.this.q.qa();
         bgz.this.q.q(var1, this.q);
         this.gO = bgz.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bgz.this.q.gP.nf;
         int var2 = (int)bgz.this.q.q(this.xK * bgz.this.q.gP.gO, bgz.this.q.gP.gP);
         Long var3 = bgz.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bgz.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bgz.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bgz.this.q.gP.gO)) {
               if (bgz.this.q.RF(var3, var6 / (int)bgz.this.q.gP.gO)) {
                  bgz.this.q.oQ();
               } else {
                  bgz.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bgz.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bgz.oM {
      ct(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgy.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("flags", bgz.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgz.this.q.Uv());
         var1.q("hash", bgz.this.q.Uv());
         var1.q("arguments", bgz.this.q.Uv());
      }
   }

   class eM extends bgz.oM {
      eM(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.RF(bgy.PQ.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            long var3 = var2.RF();
            var2.q("length");
            var2.q("shape", bgz.this.q.nf());
            var2.q("numFields", var3);
            long[] var5 = new long[(int)var3];
            var2.q("data", var5);

            for (int var6 = 0; var6 < (int)var3; var6++) {
               var5[var6] = bgz.this.q.Uv();
            }
         }
      }
   }

   class ej extends bgz.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bgy.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bgz.this.q.Uv());
         var1.q("functionTypeArguments", bgz.this.q.Uv());
         var1.q("delayedTypeArguments", bgz.this.q.Uv());
         var1.q("function", bgz.this.q.Uv());
         var1.q("context", bgz.this.q.Uv());
         var1.q("hash", bgz.this.q.Uv());
      }
   }

   class eo extends bgz.oM {
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
            bew var2 = bgz.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bgz.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bgz.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bgz.oM {
      @Override
      public void q() {
         this.RF(bgy.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgz.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class jx extends bgz.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class kY extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.EB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bgz.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgz.this.q.Uv());
         var1.q("argsDescriptor", bgz.this.q.Uv());
         var1.q("buckets", bgz.this.q.Uv());
         var1.q("mask", bgz.this.q.Uv());
      }
   }

   class nI extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            if (bgz.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bgz.this.q.Uv());
            }

            var2.q("parentFunction", bgz.this.q.Uv());
            var2.q("closure", bgz.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bgz.this.q.nf());
         }
      }
   }

   class oL extends bgz.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bgy.AB.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bgz.this.q.zz();
            var2.q("typeState", var3 >> (int)bgz.this.q.gP.Rr);
            var2.q("nullability", var3 & bgz.this.q.gP.EB);
            var2.q("packedParameterCounts", bgz.this.q.io());
            var2.q("packedTypeParameterCounts", bgz.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bgz.this.q.Uv());
         var1.q("hash", bgz.this.q.Uv());
         var1.q("typeParameters", bgz.this.q.Uv());
         var1.q("resultType", bgz.this.q.Uv());
         var1.q("parameterTypes", bgz.this.q.Uv());
         var1.q("namedParameterNames", bgz.this.q.Uv());
      }
   }

   abstract class oM implements bgz.Bu {
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
         this.oW = bgz.this.q.jq;
         long var2 = bgz.this.q.nf();
         bgz.this.q.q(var2, var1);
         this.gO = bgz.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bgz.this.q.jq;
         long var2 = bgz.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bgz.this.q.q(var1);
            long var4 = bgz.this.q.nf();
            var3.q("length", var4);
            bgz.this.q.q(var3);
         });
         this.gO = bgz.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bgz.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bgz.this.q.nf();
            long var1 = bgz.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bgz.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bgz.oM {
      @Override
      public void q() {
         this.RF(bgy.CE.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            long var3 = bgz.this.q.nf();
            long var5 = var3 >>> 1;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            var2.q("numEntries", var5);
            var2.q("handledTypesData", bgz.this.q.Uv());
            ArrayList var7 = new ArrayList();
            var2.q("data", var7);

            for (int var8 = 0; var8 < var5; var8++) {
               HashMap var9 = new HashMap();
               var9.put("handlerPcOffset", bgz.this.q.gP());
               var9.put("outerTryIndex", bgz.this.q.LK());
               var9.put("needsStacktrace", bgz.this.q.Dw());
               var9.put("hasCatchAll", bgz.this.q.Dw());
               var9.put("isGenerated", bgz.this.q.Dw());
               var7.add(var9);
            }
         }
      }
   }

   class qa extends bgz.oM {
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
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bgz.this.q.Uv());
         var1.q("hashMask", bgz.this.q.Uv());
         var1.q("data", bgz.this.q.Uv());
         var1.q("usedData", bgz.this.q.Uv());
         var1.q("deletedKeys", bgz.this.q.Uv());
      }
   }

   class qx extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.WI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bgz.this.q.Uv());
         var1.q("flags", bgz.this.q.Uv());
         var1.q("bounds", bgz.this.q.Uv());
         var1.q("defaults", bgz.this.q.Uv());
      }
   }

   class ry extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.jq.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bgz.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bgz.this.q.Uv());
         var1.q("argsDescriptor", bgz.this.q.Uv());
      }
   }

   class tl extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bgz.this.q.qa());
            var2.q("numImports", bgz.this.q.HF());
            var2.q("loadState", bgz.this.q.JY());
            var2.q("flags", bgz.this.q.zz());
            if (!bgz.this.q.PV && !bgz.this.q.io) {
               var2.q("kernelOffset", bgz.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bgz.this.q.Uv());
         var1.q("url", bgz.this.q.Uv());
         var1.q("privateKey", bgz.this.q.Uv());
         var1.q("dictionary", bgz.this.q.Uv());
         var1.q("metadata", bgz.this.q.Uv());
         var1.q("toplevelClass", bgz.this.q.Uv());
         var1.q("usedScripts", bgz.this.q.Uv());
         var1.q("loadingUnit", bgz.this.q.Uv());
         var1.q("imports", bgz.this.q.Uv());
         var1.q("exports", bgz.this.q.Uv());
         if (!bgz.this.q.io) {
            var1.q("dependencies", bgz.this.q.Uv());
            var1.q("kernelProgramInfo", bgz.this.q.Uv());
         }
      }
   }

   class tw extends bgz.oM {
      @Override
      public void q() {
         this.RF(bgy.cC.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            long var3 = bgz.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bgz.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            if (!bgz.this.q.PV && !bgz.this.q.io) {
               var2.q("libraryKernelOffset", bgz.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("wrappedClass", bgz.this.q.Uv());
         var1.q("script", bgz.this.q.Uv());
         if (!bgz.this.q.io) {
            var1.q("libraryKernelData", bgz.this.q.Uv());
         }
      }
   }

   class vb extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            if (!bgz.this.q.io) {
               throw new ToDoException();
            }

            long var3 = bgz.this.q.nf();
            var2.q("codeIndex", var3);
            Object[] var10000 = new Object[]{var1, var2.Dw("name").xK("data"), var3};
            if (var3 == 0L) {
               var2.q("code", null);
            } else {
               int var5 = bgz.this.q.Ri + (int)var3 - 1;
               bew var6 = bgz.this.q.xK(var5);
               if (var6.q().equals(bgy.Me.toString())) {
                  var2.q("code", var5);
               }
            }

            if (!bgz.this.q.PV) {
               throw new ToDoException();
            }

            var2.q("kindTag", bgz.this.q.nf());
         }
      }

      void q(bew var1) {
         var1.q("name", bgz.this.q.Uv());
         var1.q("owner", bgz.this.q.Uv());
         var1.q("signature", bgz.this.q.Uv(), bgy.AB.toString());
         var1.q("data", bgz.this.q.Uv());
      }
   }

   class vn extends bgz.oM {
      @Override
      public void q() {
         this.q(bgy.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bgz.this.q.nf());
            var2.q("callbackKind", bgz.this.q.zz());
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bgz.this.q.Uv());
         var1.q("cSignature", bgz.this.q.Uv());
         var1.q("callbackTarget", bgz.this.q.Uv());
         var1.q("callbackExceptionalReturn", bgz.this.q.Uv());
      }
   }

   class zJ extends bgz.oM {
      @Override
      public void q() {
         this.RF(bgy.Gf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bgz.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bgz.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }
}
