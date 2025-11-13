package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.DataStringUtil;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.StringEntry;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.render.GenericCodeFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.primitives.Characters;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class bar implements bax {
   byte[] q;
   int RF;
   int xK;
   axq Dw;
   bat Uv;
   GenericCodeFormatter oW;
   bay gO;
   Deque nf;
   String gP;
   int za;
   int lm;
   bax zz;
   bav JY;
   List HF;
   int LK;
   int io;
   private boolean Hk;
   private boolean Me;
   int qa;

   public bar(byte[] var1, int var2, axq var3, bat var4, GenericCodeFormatter var5, bay var6, Deque var7) {
      this.q = var1;
      this.RF = var2;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.oW = var5;
      this.gO = var6;
      this.nf = var7;
      if (var3 instanceof axw) {
         this.LK = 1;
      } else {
         this.gP = var5.getDataSeparator();
         this.za = Math.max(1, var5.getArrayElementPerLine());
         this.HF = var3.cC();
         if (this.HF.isEmpty()) {
            this.io = var3.wF().getSize();
            this.Hk = true;
            return;
         }

         if (this.HF.get(0) instanceof axq) {
            this.HF = q(var3);
         }

         this.LK = this.HF.size() - 1;
         this.io = (int)((INativeDataItem)this.HF.get(0)).getMemorySize();
         if (this.HF.get(0) instanceof axv) {
            this.Me = true;
         } else {
            this.Me = false;
            this.za = 1;
         }
      }
   }

   @Override
   public boolean q() {
      return this.lm < 0 ? false : this.Hk || this.lm <= this.LK;
   }

   @Override
   public boolean RF() {
      if (!this.Hk && !(this.Dw instanceof axw) && this.lm <= this.LK) {
         INativeDataItem var1 = (INativeDataItem)this.HF.get(this.lm);
         if (var1 instanceof axv) {
            return true;
         } else if (this.lm != this.LK) {
            return false;
         } else if (this.zz != null) {
            return this.zz.RF();
         } else {
            ArrayDeque var2 = new ArrayDeque(this.nf);
            var2.push(new bav(this.Dw, this.xK - this.RF));
            bax var3 = this.gO.q(this.q, this.xK, var1, this.Uv, var2);
            return var3.RF();
         }
      } else {
         return true;
      }
   }

   @Override
   public int xK() {
      if (this.Dw instanceof axw) {
         return (int)this.Dw.getMemorySize();
      } else {
         int var1 = this.HF.size() - this.lm;
         return this.io * Math.min(var1, this.za);
      }
   }

   @Override
   public int Dw() {
      return this.zz != null && this.zz.q() ? this.zz.Dw() : this.xK;
   }

   @Override
   public int Uv() {
      if (!this.q()) {
         throw new IllegalStateException();
      } else if (this.Hk) {
         this.oW.formatDataDeclarator(1, this.Uv);
         this.Uv.append("?");
         this.Uv.q("[?]");
         this.lm = -1;
         return 0;
      } else {
         int var1 = -1;
         StringEncoding var2 = null;
         String var3 = null;
         StringEntry var4 = null;
         if (this.Dw instanceof axw) {
            var1 = (int)this.Dw.getMemorySize();
            var2 = ((axw)this.Dw).getStringType();
            var3 = DataStringUtil.determineValue(var2, this.q, this.xK, var1, false);
         } else if ("char".equals(this.Dw.wF().getName()) && this.Dw.getMemorySize() > 3L) {
            var1 = (int)this.Dw.getMemorySize();
            var2 = this.Dw.wF().getSize() == 1 ? StringEncoding.ASCII_ZERO : StringEncoding.UTF16_ZERO;
            var4 = DataStringUtil.getStringAt(this.oW.getMemory(), this.Dw.getMemoryAddress(), this.Dw.getMemoryAddress() + var1, 3, var1);
            if (var4 != null && var4.getEncoding().getBasicCharSize() == var2.getBasicCharSize()) {
               boolean var5 = true;

               for (int var6 = var4.getMemorySize(); var6 < var1; var6++) {
                  if (this.q[this.xK + var6] != 0) {
                     var5 = false;
                     break;
                  }
               }

               if (var5) {
                  var3 = var4.getString();
                  var2 = var4.getEncoding();
               }
            }
         }

         if (var3 != null) {
            this.oW.formatDataDeclarator(var2.getBasicCharSize(), this.Uv);
            boolean var12 = var3.length() <= 500;
            String var16 = var2.getEncodingPrefix() + "\"" + Formatter.escapeString(var3, var12) + "\"";
            this.Uv.appendAndRecord(var16, ItemClassIdentifiers.STRING, this.Dw.getItemId());
            if (var2.isZeroTerminated()) {
               this.Uv.append(",0");
               if (var4 != null && var1 > var4.getMemorySize()) {
                  this.Uv.append(",0{" + (var1 - var4.getMemorySize()) + "}");
               }
            }

            this.lm = -1;
            return var1;
         } else {
            int var11 = this.za;
            if (this.Dw.cC().get(0) instanceof axq) {
               var11 = this.HF.size() / this.Dw.CE();
            } else if (TypeUtil.getNonAlias(this.Dw.wF()) instanceof bbt) {
               var11 = 1;
            } else {
               DataHints var13 = this.Dw.getHints(false);
               if (var13 != null && var13.getAddressCalculationHint() == 2) {
                  var11 = 1;
               }
            }

            if (this.zz == null && this.Me && ((axh)this.HF.get(0)).q(false) == null && !this.oW()) {
               Boolean var14 = null;

               for (byte var10 : this.q) {
                  if (Characters.isAsciiCharOrCommonFormat(var10)) {
                     var14 = Boolean.TRUE;
                  } else if (var10 != 0) {
                     var14 = Boolean.FALSE;
                     break;
                  }
               }

               NumberFormatter var17 = ((axh)this.HF.get(0)).nf();
               if (Booleans.isTrue(var14)) {
                  var17.setBase(NumberFormatter.NumberBase.ASCII);
               }
            }

            int var15 = 0;

            while (this.lm <= this.LK) {
               INativeDataItem var18 = (INativeDataItem)this.HF.get(this.lm);
               boolean var19 = var11 == 0 || this.lm % var11 == 0;
               boolean var20 = var11 == 0 || (this.lm + 1) % var11 == 0;
               if (this.Me) {
                  if (!var19) {
                     if (this.gP != null) {
                        this.Uv.append(this.gP);
                     }

                     this.Uv.space();
                  } else if (this.qa >= 1) {
                     this.Uv.space(4);
                  }
               }

               if (this.zz == null) {
                  ArrayDeque var21 = new ArrayDeque(this.nf);
                  this.JY = new bav(this.Dw, this.xK - this.RF);
                  var21.push(this.JY);
                  this.zz = this.gO.q(this.q, this.xK, var18, this.Uv, var21);
               }

               this.JY.Dw = this.lm + 1 > this.LK || var20 && this.Me;
               if (this.zz.q()) {
                  var15 += this.zz.Uv();
                  if (!(var18 instanceof axv)) {
                     if (!this.zz.q()) {
                        this.zz = null;
                        this.xK = this.xK + (int)var18.getMemorySize();
                        this.lm++;
                     }
                     break;
                  }

                  this.JY.RF = this.JY.RF + (int)var18.getMemorySize();
               }

               if (this.zz instanceof bbb) {
                  ((bbb)this.zz).q((int)var18.getMemorySize());
               } else {
                  this.zz = null;
               }

               this.xK = this.xK + (int)var18.getMemorySize();
               this.lm++;
               if (var20 && this.Me) {
                  this.qa++;
                  break;
               }
            }

            return var15;
         }
      }
   }

   private boolean oW() {
      NativeCommentManager var1 = this.gO.getUnit().getCommentManager();
      if (var1 != null) {
         Comment var2 = var1.getComment(this.Dw.getAddress());
         if (var2 != null) {
            for (MetaComment var4 : var2.getMetaComments()) {
               if (var4.getValue().startsWith("jump table") || Strings.containsAt(var4.getValue(), 10, "jump table")) {
                  return true;
               }
            }
         }
      }

      if (this.Dw.wF().getSize() == this.gO.getUnit().getTypeManager().getPointerSize()) {
         for (INativeDataItem var8 : this.HF) {
            try {
               long var9 = this.gO.getUnit().getMemory().readPointer(var8.getMemoryAddress());
               if (this.gO.getUnit().getCodeAnalyzer().getAnalysisRanges().contains(var9)) {
                  return true;
               }
            } catch (MemoryException var6) {
               return true;
            }
         }
      }

      return false;
   }

   private static List q(axq var0) {
      ArrayList var1 = new ArrayList();
      q(var0, var1);
      return var1;
   }

   private static void q(axq var0, List var1) {
      if (var0.cC().get(0) instanceof axq) {
         for (INativeDataItem var3 : var0.cC()) {
            q((axq)var3, var1);
         }
      } else {
         var1.addAll(var0.cC());
      }
   }
}
