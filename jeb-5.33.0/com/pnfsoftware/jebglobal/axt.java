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

public class axt implements axy {
   byte[] pC;
   int A;
   int kS;
   auv wS;
   axv UT;
   GenericCodeFormatter E;
   axz sY;
   Deque ys;
   String ld;
   int gp;
   int oT;
   axy fI;
   axx WR;
   List NS;
   int vP;
   int xC;
   private boolean Sc;
   private boolean ah;
   int ED;

   public axt(byte[] var1, int var2, auv var3, axv var4, GenericCodeFormatter var5, axz var6, Deque var7) {
      this.pC = var1;
      this.A = var2;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = var5;
      this.sY = var6;
      this.ys = var7;
      if (var3 instanceof avb) {
         this.vP = 1;
      } else {
         this.ld = var5.getDataSeparator();
         this.gp = Math.max(1, var5.getArrayElementPerLine());
         this.NS = var3.UO();
         if (this.NS.isEmpty()) {
            this.xC = var3.z().getSize();
            this.Sc = true;
            return;
         }

         if (this.NS.get(0) instanceof auv) {
            this.NS = pC(var3);
         }

         this.vP = this.NS.size() - 1;
         this.xC = (int)((INativeDataItem)this.NS.get(0)).getMemorySize();
         if (this.NS.get(0) instanceof ava) {
            this.ah = true;
         } else {
            this.ah = false;
            this.gp = 1;
         }
      }
   }

   @Override
   public boolean pC() {
      return this.oT < 0 ? false : this.Sc || this.oT <= this.vP;
   }

   @Override
   public boolean A() {
      if (!this.Sc && !(this.wS instanceof avb) && this.oT <= this.vP) {
         INativeDataItem var1 = (INativeDataItem)this.NS.get(this.oT);
         if (var1 instanceof ava) {
            return true;
         } else if (this.oT != this.vP) {
            return false;
         } else if (this.fI != null) {
            return this.fI.A();
         } else {
            ArrayDeque var2 = new ArrayDeque(this.ys);
            var2.push(new axx(this.wS, this.kS - this.A));
            axy var3 = this.sY.pC(this.pC, this.kS, var1, this.UT, var2);
            return var3.A();
         }
      } else {
         return true;
      }
   }

   @Override
   public int kS() {
      if (this.wS instanceof avb) {
         return (int)this.wS.getMemorySize();
      } else {
         int var1 = this.NS.size() - this.oT;
         return this.xC * Math.min(var1, this.gp);
      }
   }

   @Override
   public int wS() {
      return this.fI != null && this.fI.pC() ? this.fI.wS() : this.kS;
   }

   @Override
   public int UT() {
      if (!this.pC()) {
         throw new IllegalStateException();
      } else if (this.Sc) {
         this.E.formatDataDeclarator(1, this.UT);
         this.UT.append("?");
         this.UT.pC("[?]");
         this.oT = -1;
         return 0;
      } else {
         int var1 = -1;
         StringEncoding var2 = null;
         String var3 = null;
         StringEntry var4 = null;
         if (this.wS instanceof avb) {
            var1 = (int)this.wS.getMemorySize();
            var2 = ((avb)this.wS).getStringType();
            var3 = DataStringUtil.determineValue(var2, this.pC, this.kS, var1, false);
         } else if ("char".equals(this.wS.z().getName()) && this.wS.getMemorySize() > 3L) {
            var1 = (int)this.wS.getMemorySize();
            var2 = this.wS.z().getSize() == 1 ? StringEncoding.ASCII_ZERO : StringEncoding.UTF16_ZERO;
            var4 = DataStringUtil.getStringAt(this.E.getMemory(), this.wS.getMemoryAddress(), this.wS.getMemoryAddress() + var1, 3, var1);
            if (var4 != null && var4.getEncoding().getBasicCharSize() == var2.getBasicCharSize()) {
               boolean var5 = true;

               for (int var6 = var4.getMemorySize(); var6 < var1; var6++) {
                  if (this.pC[this.kS + var6] != 0) {
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
            this.E.formatDataDeclarator(var2.getBasicCharSize(), this.UT);
            boolean var12 = var3.length() <= 500;
            String var16 = var2.getEncodingPrefix() + "\"" + Formatter.escapeString(var3, var12) + "\"";
            this.UT.appendAndRecord(var16, ItemClassIdentifiers.STRING, this.wS.getItemId());
            if (var2.isZeroTerminated()) {
               this.UT.append(",0");
               if (var4 != null && var1 > var4.getMemorySize()) {
                  this.UT.append(",0{" + (var1 - var4.getMemorySize()) + "}");
               }
            }

            this.oT = -1;
            return var1;
         } else {
            int var11 = this.gp;
            if (this.wS.UO().get(0) instanceof auv) {
               var11 = this.NS.size() / this.wS.rl();
            } else if (TypeUtil.getNonAlias(this.wS.z()) instanceof ayt) {
               var11 = 1;
            } else {
               DataHints var13 = this.wS.getHints(false);
               if (var13 != null && var13.getAddressCalculationHint() == 2) {
                  var11 = 1;
               }
            }

            if (this.fI == null && this.ah && ((aum)this.NS.get(0)).pC(false) == null && !this.E()) {
               Boolean var14 = null;

               for (byte var10 : this.pC) {
                  if (Characters.isAsciiCharOrCommonFormat(var10)) {
                     var14 = Boolean.TRUE;
                  } else if (var10 != 0) {
                     var14 = Boolean.FALSE;
                     break;
                  }
               }

               NumberFormatter var17 = ((aum)this.NS.get(0)).ys();
               if (Booleans.isTrue(var14)) {
                  var17.setBase(NumberFormatter.NumberBase.ASCII);
               }
            }

            int var15 = 0;

            while (this.oT <= this.vP) {
               INativeDataItem var18 = (INativeDataItem)this.NS.get(this.oT);
               boolean var19 = var11 == 0 || this.oT % var11 == 0;
               boolean var20 = var11 == 0 || (this.oT + 1) % var11 == 0;
               if (this.ah) {
                  if (!var19) {
                     if (this.ld != null) {
                        this.UT.append(this.ld);
                     }

                     this.UT.space();
                  } else if (this.ED >= 1) {
                     this.UT.space(4);
                  }
               }

               if (this.fI == null) {
                  ArrayDeque var21 = new ArrayDeque(this.ys);
                  this.WR = new axx(this.wS, this.kS - this.A);
                  var21.push(this.WR);
                  this.fI = this.sY.pC(this.pC, this.kS, var18, this.UT, var21);
               }

               this.WR.wS = this.oT + 1 > this.vP || var20 && this.ah;
               if (this.fI.pC()) {
                  var15 += this.fI.UT();
                  if (!(var18 instanceof ava)) {
                     if (!this.fI.pC()) {
                        this.fI = null;
                        this.kS = this.kS + (int)var18.getMemorySize();
                        this.oT++;
                     }
                     break;
                  }

                  this.WR.A = this.WR.A + (int)var18.getMemorySize();
               }

               if (this.fI instanceof ayc) {
                  ((ayc)this.fI).pC((int)var18.getMemorySize());
               } else {
                  this.fI = null;
               }

               this.kS = this.kS + (int)var18.getMemorySize();
               this.oT++;
               if (var20 && this.ah) {
                  this.ED++;
                  break;
               }
            }

            return var15;
         }
      }
   }

   private boolean E() {
      NativeCommentManager var1 = this.sY.getUnit().getCommentManager();
      if (var1 != null) {
         Comment var2 = var1.getComment(this.wS.getAddress());
         if (var2 != null) {
            for (MetaComment var4 : var2.getMetaComments()) {
               if (var4.getValue().startsWith("jump table") || Strings.containsAt(var4.getValue(), 10, "jump table")) {
                  return true;
               }
            }
         }
      }

      if (this.wS.z().getSize() == this.sY.getUnit().getTypeManager().getPointerSize()) {
         for (INativeDataItem var8 : this.NS) {
            try {
               long var9 = this.sY.getUnit().getMemory().readPointer(var8.getMemoryAddress());
               if (this.sY.getUnit().getCodeAnalyzer().getAnalysisRanges().contains(var9)) {
                  return true;
               }
            } catch (MemoryException var6) {
               return true;
            }
         }
      }

      return false;
   }

   private static List pC(auv var0) {
      ArrayList var1 = new ArrayList();
      pC(var0, var1);
      return var1;
   }

   private static void pC(auv var0, List var1) {
      if (var0.UO().get(0) instanceof auv) {
         for (INativeDataItem var3 : var0.UO()) {
            pC((auv)var3, var1);
         }
      } else {
         var1.addAll(var0.UO());
      }
   }
}
