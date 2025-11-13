package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bfh {
   private static final ILogger RF = GlobalLog.getLogger(bfh.class);
   bfj q;

   bfh(bfj var1) {
      Assert.a(var1.io);
      this.q = var1;
   }

   bfh.oL q(int var1, boolean var2) {
      bfh.oL var3 = this.q(var1);
      ((bfh.oM)var3).Dw = var2;
      return var3;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bfh.oL q(int var1) {
      bfg var2 = bfg.q(var1);
      if (var1 >= bfg.cO.q() || var1 == bfg.IN.q()) {
         return new bfh.Vj(var1);
      } else if (this.q.za(var1)) {
         return new bfh.LR(var2);
      } else {
         if (this.q.KT) {
            switch (var2) {
               case PV:
               case oQ:
               case xW:
                  return new bfh.Xa(var2);
               case YA:
               case fw:
                  return new bfh.Xa(var2);
               case qR:
                  return new bfh.Xa(var2);
            }
         }

         switch (var2) {
            case Uv:
               return new bfh.CU();
            case oW:
               return new bfh.kY();
            case gO:
               return new bfh.vn();
            case nf:
               return new bfh.nI();
            case gP:
               return new bfh.EE();
            case za:
               return new bfh.qV();
            case lm:
               return new bfh.CI();
            case zz:
               return new bfh.Bu();
            case io:
               return new bfh.Nt();
            case Me:
               return new bfh.Uz();
            case WI:
               return new bfh.Nz();
            case Yw:
            case IY:
               return new bfh.eo(var2);
            case Gf:
               return new bfh.tw();
            case CE:
               return new bfh.qa();
            case jq:
               return new bfh.vX();
            case ui:
               return new bfh.bK();
            case eJ:
               return new bfh.zJ();
            case Rv:
               return new bfh.KZ();
            case zx:
               return new bfh.PY();
            case ZT:
               return new bfh.HA();
            case Ri:
               return new bfh.eM();
            case GY:
               return new bfh.ej();
            case Tq:
               return new bfh.iZ();
            case Gu:
               return new bfh.vb();
            case LK:
               if (this.q.PV) {
                  return new bfh.ME();
               }
            default:
               throw new ToDoException("Cluster deser. not implemented for ClassId " + var2);
         }
      }
   }

   class Bu extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.zz);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bfh.this.q.qa());
            var2.q("numImports", bfh.this.q.HF());
            var2.q("loadState", bfh.this.q.JY());
            var2.q("flags", bfh.this.q.zz());
            if (!bfh.this.q.PV && !bfh.this.q.io) {
               var2.q("kernelOffset", bfh.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfh.this.q.nf());
         var1.q("url", bfh.this.q.nf());
         var1.q("privateKey", bfh.this.q.nf());
         var1.q("dictionary", bfh.this.q.nf());
         var1.q("metadata", bfh.this.q.nf());
         var1.q("toplevelClass", bfh.this.q.nf());
         var1.q("usedScripts", bfh.this.q.nf());
         var1.q("loadingUnit", bfh.this.q.nf());
         var1.q("imports", bfh.this.q.nf());
         var1.q("exports", bfh.this.q.nf());
         if (!bfh.this.q.io) {
            var1.q("dependencies", bfh.this.q.nf());
            var1.q("kernelData", bfh.this.q.nf());
         }
      }
   }

   class CI extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.lm);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("lineOffset", bfh.this.q.qa());
            var2.q("colOffset", bfh.this.q.qa());
            if (!bfh.this.q.PV) {
               var2.q("flagsAndMaxPosition", bfh.this.q.qa());
            }

            var2.q("kernelScriptIndex", bfh.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bfh.this.q.nf());
         if (!bfh.this.q.io) {
            var1.q("resolvedUrl", bfh.this.q.nf());
            var1.q("compileTimeConstants", bfh.this.q.nf());
            var1.q("lineStarts", bfh.this.q.nf());
            var1.q("debugPositions", bfh.this.q.nf());
            var1.q("kernelProgramInfo", bfh.this.q.nf());
         }
      }
   }

   class CU extends bfh.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bfh.this.q.jq;
         long var1 = bfh.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bfh.this.q.q(bfg.Uv.q());
            var2.q("id", bfh.this.q.oW());
            bfh.this.q.q(var2);
         });
         this.RF = bfh.this.q.jq;
         this.q(bfg.Uv);
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.Uv, this.oW}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bfh.this.q.xK(var6);
               this.q(var8);
               int var9 = bfh.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bfh.this.q.PV && !bfh.this.q.io) {
                  var8.q("kernelOffset", bfh.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bfh.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bfh.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bfh.this.q.za());
               if (!bfh.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bfh.this.q.za());
               var8.q("numNativeFields", bfh.this.q.nf());
               var8.q("tokenPos", bfh.this.q.gO());
               var8.q("endTokenPos", bfh.this.q.gO());
               var8.q("stateBits", bfh.this.q.nf());
               if (bfh.this.q.PV) {
                  if (var7) {
                     bfh.this.q.nf();
                  } else if (!bfh.this.q.gP.q(var9)) {
                     bfh.this.q.TX.put(var9, bfh.this.q.nf());
                  }
               }

               bfh.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfh.this.q.nf());
         var1.q("userName", bfh.this.q.nf());
         var1.q("functions", bfh.this.q.nf());
         var1.q("functionsHashTable", bfh.this.q.nf());
         var1.q("fields", bfh.this.q.nf());
         var1.q("offsetInWordsToField", bfh.this.q.nf());
         var1.q("interfaces", bfh.this.q.nf());
         var1.q("script", bfh.this.q.nf());
         var1.q("library", bfh.this.q.nf());
         var1.q("typeParameters", bfh.this.q.nf());
         var1.q("superType", bfh.this.q.nf());
         var1.q("constants", bfh.this.q.nf());
         var1.q("declarationType", bfh.this.q.nf());
         var1.q("invocationDispatcherCache", bfh.this.q.nf());
         var1.q("allocationStub", bfh.this.q.nf());
         if (!bfh.this.q.io) {
            var1.q("directImplementors", bfh.this.q.nf());
            var1.q("directSubclasses", bfh.this.q.nf());
            if (!bfh.this.q.HF) {
               var1.q("dependentCode", bfh.this.q.nf());
               if (!bfh.this.q.LK) {
                  throw new RuntimeException("Expected kind to be Full, FullJIT, or FullAOT");
               }
            }
         }
      }
   }

   class EE extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.gP);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bfh.this.q.io ? bfh.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bfh.this.q.nf());
         var1.q("cSignature", bfh.this.q.nf());
         var1.q("callbackTarget", bfh.this.q.nf());
         var1.q("callbackExceptionalReturn", bfh.this.q.nf());
      }
   }

   class HA extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.ZT);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfh.this.q.nf());
         var1.q("type", bfh.this.q.nf());
      }
   }

   class KZ extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.Rv);
         this.q(this.Dw, false);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfh.this.q.zz();
            var2.q("typeState", var3 >> (int)bfh.this.q.gP.Rr);
            var2.q("nullability", var3 & bfh.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfh.this.q.nf());
         var1.q("typeClassId", bfh.this.q.nf());
         var1.q("arguments", bfh.this.q.nf());
         var1.q("hash", bfh.this.q.nf());
      }
   }

   class LR extends bfh.oM {
      bfg q;

      public LR(bfg var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bfh.this.q.HF(this.q.q());
            var2.q("data", bfh.this.q.Dw(var4));
         }
      }
   }

   class ME extends bfh.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class Nt extends bfh.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q(bfg.io);
         this.q = bfh.this.q.jq;
         long var1 = bfh.this.q.nf();
         bfh.this.q.q(var1, bfg.io.q());
         this.RF = bfh.this.q.jq;
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
         bew var3 = bfh.this.q.xK(var1);
         this.q(var3, var2);
         var3.q("compressedStackMaps", bfh.this.q.Uv());
         int var4 = bfh.this.q.qa();
         var3.q("stateBits", var4);
         boolean var5 = (var4 >> 3 & 1) == 1;
         if (bfh.this.q.PV) {
            if (var5) {
               return;
            }
         } else {
            Assert.a(!var5);
         }

         if (bfh.this.q.io && bfh.this.q.oQ) {
            var3.q("objectPool", null);
         } else {
            var3.q("objectPool", bfh.this.q.Uv());
         }

         var3.q("owner", bfh.this.q.Uv());
         var3.q("exceptionHandlers", bfh.this.q.Uv(), bfg.Gf.toString());
         var3.q("pcDescriptors", bfh.this.q.Uv());
         var3.q("catchEntry", bfh.this.q.Uv());
         var3.q("inlinedIdToFunction", bfh.this.q.Uv());
         var3.q("codeSourceMap", bfh.this.q.Uv());
         if (!bfh.this.q.PV && bfh.this.q.LK) {
            var3.q("deoptInfoArray", bfh.this.q.Uv());
            var3.q("staticCallsTargetTable", bfh.this.q.Uv());
         }

         if (!bfh.this.q.Me) {
            var3.q("returnAddressMetadata", bfh.this.q.Uv());
            var3.q("varDescriptors", null);
            var3.q("comments", bfh.this.q.Gf ? bfh.this.q.Uv() : null);
            var3.q("compileTimestamp", 0);
         }
      }

      void q(bew var1, boolean var2) {
         if (var2) {
            throw new ToDoException();
         } else if (bfh.this.q.PV && bfh.this.q.oQ) {
            bfh.this.q.ZT = (int)(bfh.this.q.ZT + bfh.this.q.nf());
            long var3 = bfh.this.q.Ri + bfh.this.q.ZT;
            long var5 = bfh.this.q.nf();
            long var7 = var5 >> 1;
            boolean var9 = (var5 & 1L) == 1L;
            long var10 = var9 ? bfh.this.q.gP.oQ : 0L;
            long var12 = var9 ? bfh.this.q.gP.PV : 0L;
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

   class Nz extends bfh.oM {
      @Override
      public void q() {
         long var1 = bfh.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bfh.this.q.q(bfg.WI.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bfh.this.q.za());
            bfh.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class PY extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.zx);
         this.q(this.Dw, true);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfh.this.q.zz();
            var2.q("typeState", var3 >> (int)bfh.this.q.gP.Rr);
            var2.q("nullability", var3 & bfh.this.q.gP.EB);
            var2.q("packedFields", bfh.this.q.io());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfh.this.q.nf());
         var1.q("typeParameters", bfh.this.q.nf());
         var1.q("resultType", bfh.this.q.nf());
         var1.q("parameterTypes", bfh.this.q.nf());
         var1.q("parameterNames", bfh.this.q.nf());
         var1.q("hash", bfh.this.q.nf());
      }
   }

   class Uz extends bfh.oM {
      @Override
      public void q() {
         this.RF(bfg.Me);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bfh.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bfh.this.q.oW(var6);
               if (var8 == bfh.this.q.gP.xW) {
                  var7.put("rawObj", bfh.this.q.Uv());
               } else if (var8 == bfh.this.q.gP.KT) {
                  var7.put("rawValue", bfh.this.q.za());
               } else {
                  if (var8 != bfh.this.q.gP.Gf) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  var7.put("rawValue", "lazy link entry");
               }

               var4.add(var7);
            }
         }
      }
   }

   class Vj extends bfh.oM {
      int q;
      int RF;
      int xK;

      Vj(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Uv = bfh.this.q.jq;
         long var1 = bfh.this.q.nf();
         this.RF = bfh.this.q.qa();
         this.xK = bfh.this.q.qa();
         bfh.this.q.q(var1, this.q);
         this.oW = bfh.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bfh.this.q.gP.oW;
         int var2 = (int)bfh.this.q.q(this.xK * bfh.this.q.gP.Uv, bfh.this.q.gP.gP);
         Long var3 = bfh.this.q.nf();

         for (int var4 = this.Uv; var4 < this.oW; var4++) {
            bew var5 = bfh.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);
            ArrayList var6 = new ArrayList();
            var5.q("data", var6);

            int var7;
            for (var7 = bfh.this.q.wF ? 8 : 4; var7 < var1; var7 = (int)(var7 + bfh.this.q.gP.Uv)) {
               if (bfh.this.q.RF(var3, var7 / (int)bfh.this.q.gP.Uv)) {
                  bfh.this.q.oQ();
               } else {
                  bfh.this.q.Uv();
               }
            }

            if (var7 < var2) {
               var6.add(null);
               var7 = (int)(var7 + bfh.this.q.gP.Uv);
            }

            Assert.a(var7 == var2);
         }
      }
   }

   class Xa extends bfh.oM {
      bfg q;

      Xa(bfg var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.Uv = bfh.this.q.jq;
         long var1 = bfh.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bfh.this.q.nf() << (int)bfh.this.q.gP.za));
            Object var6 = this.q(var3);
            bew var7 = bfh.this.q.q(this.q.q());
            var7.q("data", var6);
            bfh.this.q.q(var7);
         }

         this.oW = bfh.this.q.jq;
         this.q(this.q == bfg.qR, true);
      }

      @Override
      public void RF() {
      }

      protected Object q(int var1) {
         if (this.q != bfg.YA && this.q != bfg.qR) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bfh.this.q.gO.position(var1);
            bfh.this.q.gO.i32();
            long var2;
            if (bfh.this.q.wF) {
               bfh.this.q.gO.i32();
               var2 = bfh.this.q.gO.i64();
            } else {
               var2 = bfh.this.q.gO.i32();
               bfh.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bfh.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class bK extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.ui);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("parent", bfh.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bfh.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class eM extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.Ri);
         this.q(this.Dw, true);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bfh.this.q.qa());
            var2.q("base", bfh.this.q.HF());
            var2.q("index", bfh.this.q.HF());
            long var3 = bfh.this.q.zz();
            var2.q("typeState", var3 >> (int)bfh.this.q.gP.Rr);
            var2.q("nullability", var3 & bfh.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfh.this.q.nf());
         var1.q("name", bfh.this.q.nf());
         var1.q("hash", bfh.this.q.nf());
         var1.q("bound", bfh.this.q.nf());
         var1.q("defaultArgument", bfh.this.q.nf());
      }
   }

   class ej extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.GY);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bfh.this.q.nf());
         var1.q("functionTypeArguments", bfh.this.q.nf());
         var1.q("delayedTypeArguments", bfh.this.q.nf());
         var1.q("function", bfh.this.q.nf());
         var1.q("context", bfh.this.q.nf());
         var1.q("hash", bfh.this.q.nf());
      }
   }

   class eo extends bfh.oM {
      bfg q;

      eo(bfg var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bfh.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bfh.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.Tq);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bfh.this.q.za());
         }
      }
   }

   class kY extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.oW);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            if (!bfh.this.q.PV && !bfh.this.q.io) {
               var2.q("libraryKernelOffset", bfh.this.q.lm());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bfh.this.q.nf());
         var1.q("originClass", bfh.this.q.nf());
         var1.q("script", bfh.this.q.nf());
         if (!bfh.this.q.io) {
            var1.q("libraryKernelData", bfh.this.q.nf());
         }
      }
   }

   class nI extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.nf);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            if (bfh.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bfh.this.q.Uv());
            }

            var2.q("parentFunction", bfh.this.q.Uv());
            var2.q("closure", bfh.this.q.Uv());
            var2.q("defaultTypeArguments", bfh.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bfh.this.q.nf());
         }
      }
   }

   interface oL {
      void q();

      void RF();
   }

   abstract class oM implements bfh.oL {
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

      protected final void q(bfg var1) {
         this.Uv = bfh.this.q.jq;
         long var2 = bfh.this.q.nf();
         bfh.this.q.q(var2, var1.q());
         this.oW = bfh.this.q.jq;
      }

      protected final void RF(bfg var1) {
         this.Uv = bfh.this.q.jq;
         long var2 = bfh.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bfh.this.q.q(var1.q());
            long var4 = bfh.this.q.nf();
            var3.q("length", var4);
            bfh.this.q.q(var3);
         });
         this.oW = bfh.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bfh.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void q(boolean var1, boolean var2) {
         if (var1) {
            bfh.this.q.nf();
            long var3 = var2 ? 0L : bfh.this.q.nf();

            for (int var5 = this.Uv + (int)var3; var5 < this.oW; var5++) {
               bfh.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.za);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            if (!bfh.this.q.io) {
               var2.q("guardedListLength", bfh.this.q.Uv());
            }

            if (bfh.this.q.LK) {
               var2.q("dependentCode", bfh.this.q.Uv());
            }

            if (!bfh.this.q.io) {
               var2.q("tokenPos", bfh.this.q.gO());
               var2.q("endTokenPos", bfh.this.q.gO());
               var2.q("guardedCid", bfh.this.q.oW());
               var2.q("isNullable", bfh.this.q.oW());
               var2.q("staticTypeExactnessState", bfh.this.q.JY());
               if (!bfh.this.q.PV) {
                  var2.q("kernelOffset", bfh.this.q.gP());
               }
            }

            int var3 = bfh.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bfh.this.q.Uv();
            if (bfh.this.q.Uv(var3)) {
               long var6 = bfh.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bfh.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfh.this.q.nf());
         var1.q("owner", bfh.this.q.nf());
         var1.q("type", bfh.this.q.nf());
         var1.q("initializerFunction", bfh.this.q.nf());
      }
   }

   class qa extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.CE);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bfh.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfh.this.q.nf());
         var1.q("argsDescriptor", bfh.this.q.nf());
      }
   }

   class tl extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.mI);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bfh.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfh.this.q.nf());
         var1.q("argsDescriptor", bfh.this.q.nf());
         var1.q("buckets", bfh.this.q.nf());
         var1.q("mask", bfh.this.q.nf());
      }
   }

   class tw extends bfh.oM {
      @Override
      public void q() {
         this.RF(bfg.Gf);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bfh.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bfh.this.q.gP());
               var6.put("outerTryIndex", bfh.this.q.LK());
               var6.put("needsStacktrace", bfh.this.q.Dw());
               var6.put("hasCatchAll", bfh.this.q.Dw());
               var6.put("isGenerated", bfh.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class vX extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.jq);
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("cache", bfh.this.q.Uv());
         }
      }
   }

   class vb extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.Gu);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bfh.this.q.nf());
         var1.q("length", bfh.this.q.nf());
         var1.q("data", bfh.this.q.nf());
      }
   }

   class vn extends bfh.oM {
      @Override
      public void q() {
         this.q(bfg.gO);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.q(var2);
            if (bfh.this.q.HF) {
               throw new ToDoException();
            }

            if (!bfh.this.q.io) {
               throw new ToDoException();
            }

            var2.q("code", bfh.this.q.Uv());
            if (!bfh.this.q.PV && !bfh.this.q.io) {
               var2.q("tokenPos", bfh.this.q.gO());
               var2.q("endTokenPos", bfh.this.q.gO());
               var2.q("kernelOffset", bfh.this.q.nf());
            }

            var2.q("packedFields", bfh.this.q.nf());
            var2.q("kindTag", bfh.this.q.nf());
            if (!bfh.this.q.io && !bfh.this.q.PV) {
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
         var1.q("name", bfh.this.q.nf());
         var1.q("owner", bfh.this.q.nf());
         var1.q("parameterNames", bfh.this.q.nf());
         var1.q("signature", bfh.this.q.nf());
         var1.q("data", bfh.this.q.nf());
      }
   }

   class zJ extends bfh.oM {
      @Override
      public void q() {
         this.RF(bfg.eJ);
         this.q(this.Dw, true);
      }

      @Override
      public void RF() {
         for (int var1 = this.Uv; var1 < this.oW; var1++) {
            bew var2 = bfh.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bfh.this.q.lm());
            var2.q("nullabity", bfh.this.q.nf());
            var2.q("instantiations", bfh.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bfh.this.q.Uv());
            }
         }
      }
   }
}
