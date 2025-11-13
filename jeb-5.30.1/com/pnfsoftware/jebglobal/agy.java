package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Ser
public class agy extends aff implements ICSwitchStm {
   private static final StructuredLogger RF = aeg.q(agy.class);
   @SerId(1)
   private ICExpression xK;
   @SerId(2)
   private List Dw = new ArrayList();
   @SerId(3)
   private ICBlock Uv;

   agy(ICExpression var1) {
      this.setSwitchedExpression(var1);
   }

   public agy RF() {
      agy var1 = new agy(this.xK.duplicate());
      var1.Dw = (List)this.Dw.stream().map(var0 -> var0.xK()).collect(Collectors.toCollection(ArrayList::new));
      var1.Uv = this.Uv == null ? null : this.Uv.duplicate();
      super.q(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.xK = null;
      this.Dw.clear();
      this.Uv = null;
   }

   @Override
   public ICExpression getSwitchedExpression() {
      return this.xK;
   }

   @Override
   public void setSwitchedExpression(ICExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.xK = var1;
      }
   }

   private agy.eo q(ICBlock var1) {
      for (agy.eo var3 : this.Dw) {
         if (var3.RF == var1) {
            return var3;
         }
      }

      return null;
   }

   private agy.eo q(BigInteger var1) {
      for (agy.eo var3 : this.Dw) {
         if (var3.xK.contains(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public Set getCaseKeys() {
      LinkedHashSet var1 = new LinkedHashSet();

      for (agy.eo var3 : this.Dw) {
         var1.addAll(var3.xK);
      }

      return var1;
   }

   @Override
   public List getCaseBodies() {
      return (List)this.Dw.stream().map(var0 -> var0.RF).collect(Collectors.toList());
   }

   @Override
   public List getConditionalBlocks() {
      return this.getCaseBodies();
   }

   @Override
   public ICBlock getCaseBody(BigInteger var1) {
      agy.eo var2 = this.q(var1);
      return var2 == null ? null : var2.RF;
   }

   @Override
   public ICBlock getDefaultBlock() {
      return this.Uv;
   }

   @Override
   public void addCase(Collection var1, ICBlock var2) {
      agy.eo var3 = this.q(var2);
      if (var3 != null) {
         var3.q(var1);
      } else {
         var3 = new agy.eo(var1, var2);
         this.Dw.add(var3);
      }
   }

   @Override
   public Collection removeCasesFromBlock(ICBlock var1, Collection var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         agy.eo var3 = this.q(var1);
         if (var3 == null) {
            return null;
         } else if (var2 == null) {
            this.Dw.remove(var3);
            return var3.xK;
         } else {
            ArrayList var4 = new ArrayList();

            for (BigInteger var6 : var2) {
               if (var3.xK.remove(var6)) {
                  var4.add(var6);
               }
            }

            if (var3.xK.isEmpty()) {
               this.Dw.remove(var3);
            }

            return var4;
         }
      }
   }

   @Override
   public void setDefaultBlock(ICBlock var1) {
      this.Uv = var1;
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.Uv != null;
   }

   @Override
   public int sizeWithoutDefault() {
      return this.Dw.size();
   }

   @Override
   public int size() {
      return this.sizeWithoutDefault() + (this.hasDefaultBlock() ? 1 : 0);
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      for (agy.eo var5 : this.Dw) {
         if (var5.RF.insertAt(var1, var3)) {
            return true;
         }
      }

      return this.Uv != null ? this.Uv.insertAt(var1, var3) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList(this.getCaseBodies());
      if (this.Uv != null) {
         var1.add(this.Uv);
      }

      return var1;
   }

   @Override
   public List getKeysForBlock(ICBlock var1) {
      agy.eo var2 = this.q(var1);
      return var2 == null ? null : new ArrayList(var2.xK);
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new ahx(this, this.xK));

      for (agy.eo var3 : this.Dw) {
         var1.add(new ahy(var3.xK));
         var1.addAll(var3.RF.generateFlatList());
      }

      if (this.Uv != null) {
         var1.add(new ahz());
         var1.addAll(this.Uv.generateFlatList());
      }

      var1.add(new aia());
      return var1;
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q(this.xK);
      this.Dw.forEach(var1x -> ahf.q(var1, var1x.RF));
      return ahf.q(var1, this.Uv);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.xK == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.xK = (ICExpression)var2;
            return true;
         }
      } else {
         for (agy.eo var4 : this.Dw) {
            if (var4.RF == var1) {
               if (!(var2 instanceof ICBlock)) {
                  return false;
               }

               throw new RuntimeException("TBI");
            }
         }

         if (this.Uv == var1) {
            if (var2 != null && !(var2 instanceof ICBlock)) {
               return false;
            }

            this.Uv = (ICBlock)var2;
         }

         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      var1.appendKeyword(CKeyword.SWITCH);
      var1.paren();
      this.xK.generate(var1);
      var1.parenClose();
      var1.space();
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();
      Object var2 = this.Dw;
      boolean var3 = true;

      for (agy.eo var5 : this.Dw) {
         if (!var5.RF.isEmpty()) {
            ICStatement var6 = var5.RF.getLast();
            if (var6 instanceof ICBreak || var6 instanceof ICGoto || var6 instanceof ICReturn) {
               continue;
            }
         }

         var3 = false;
         break;
      }

      if (var3) {
         var2 = new ArrayList(this.Dw);
         Collections.sort((List)var2, new agz(this));
      }

      for (agy.eo var12 : var2) {
         TreeSet var13 = var12.xK;
         ICBlock var7 = var12.RF;
         int var8 = 0;

         for (BigInteger var10 : var13) {
            if (var8 >= 1) {
               var1.eol();
            }

            var1.appendKeyword(CKeyword.CASE);
            var1.space();
            var1.appendAndRecord(var10.toString(), ItemClassIdentifiers.NUMBER);
            var1.append(": ");
            var8++;
         }

         var7.generate(var1);
         var1.eol();
      }

      if (this.Uv != null) {
         var1.appendKeyword(CKeyword.DEFAULT);
         var1.append(": ");
         this.Uv.generate(var1);
         var1.eol();
      }

      var1.decrementIndentationLevel();
      var1.braceClose();
      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Switch;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         agy var2 = (agy)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.Uv == null) {
            if (var2.Uv != null) {
               return false;
            }
         } else if (!this.Uv.equals(var2.Uv)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("switch(%s) {...}", this.getSwitchedExpression());
   }

   @Ser
   public static class eo {
      @SerId(value = 1, deprecated = true)
      TreeSet q;
      @SerId(2)
      ICBlock RF;
      @SerId(3)
      TreeSet xK = new TreeSet();

      @SerCustomInitPostGraph
      private void Dw() {
         if (this.q != null) {
            this.xK = new TreeSet();

            for (Integer var2 : this.q) {
               this.xK.add(BigInteger.valueOf(var2.longValue()));
            }

            this.q = null;
         }
      }

      eo(Collection var1, ICBlock var2) {
         if (var1 != null && !var1.isEmpty() && var2 != null) {
            this.xK.addAll(var1);
            this.RF = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      SortedSet q() {
         return this.xK;
      }

      ICBlock RF() {
         return this.RF;
      }

      void q(Collection var1) {
         this.xK.addAll(var1);
      }

      agy.eo xK() {
         return new agy.eo(this.xK, this.RF.duplicate());
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
         return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            agy.eo var2 = (agy.eo)var1;
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            if (this.xK == null) {
               if (var2.xK != null) {
                  return false;
               }
            } else if (!this.xK.equals(var2.xK)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%s -> %s", this.xK, this.RF);
      }
   }
}
