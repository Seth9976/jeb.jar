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
import java.util.TreeSet;
import java.util.stream.Collectors;

@Ser
public class aff extends adm implements ICSwitchStm {
   private static final StructuredLogger A = aco.pC(aff.class);
   @SerId(1)
   private ICExpression kS;
   @SerId(2)
   private List wS = new ArrayList();
   @SerId(3)
   private ICBlock UT;

   aff(ICExpression var1) {
      this.setSwitchedExpression(var1);
   }

   public aff A() {
      aff var1 = new aff(this.kS.duplicate());
      var1.wS = (List)this.wS.stream().map(var0 -> var0.pC()).collect(Collectors.toCollection(ArrayList::new));
      var1.UT = this.UT == null ? null : this.UT.duplicate();
      super.pC(var1);
      return var1;
   }

   @Override
   public void reset() {
      this.kS = null;
      this.wS.clear();
      this.UT = null;
   }

   @Override
   public ICExpression getSwitchedExpression() {
      return this.kS;
   }

   @Override
   public void setSwitchedExpression(ICExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
      }
   }

   private aff.Av pC(ICBlock var1) {
      for (aff.Av var3 : this.wS) {
         if (var3.A == var1) {
            return var3;
         }
      }

      return null;
   }

   private aff.Av pC(BigInteger var1) {
      for (aff.Av var3 : this.wS) {
         if (var3.kS.contains(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public Set getCaseKeys() {
      LinkedHashSet var1 = new LinkedHashSet();

      for (aff.Av var3 : this.wS) {
         var1.addAll(var3.kS);
      }

      return var1;
   }

   @Override
   public List getCaseBodies() {
      return (List)this.wS.stream().map(var0 -> var0.A).collect(Collectors.toList());
   }

   @Override
   public List getConditionalBlocks() {
      return this.getCaseBodies();
   }

   @Override
   public ICBlock getCaseBody(BigInteger var1) {
      aff.Av var2 = this.pC(var1);
      return var2 == null ? null : var2.A;
   }

   @Override
   public ICBlock getDefaultBlock() {
      return this.UT;
   }

   @Override
   public void addCase(Collection var1, ICBlock var2) {
      aff.Av var3 = this.pC(var2);
      if (var3 != null) {
         var3.pC(var1);
      } else {
         var3 = new aff.Av(var1, var2);
         this.wS.add(var3);
      }
   }

   @Override
   public Collection removeCasesFromBlock(ICBlock var1, Collection var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         aff.Av var3 = this.pC(var1);
         if (var3 == null) {
            return null;
         } else if (var2 == null) {
            this.wS.remove(var3);
            return var3.kS;
         } else {
            ArrayList var4 = new ArrayList();

            for (BigInteger var6 : var2) {
               if (var3.kS.remove(var6)) {
                  var4.add(var6);
               }
            }

            if (var3.kS.isEmpty()) {
               this.wS.remove(var3);
            }

            return var4;
         }
      }
   }

   @Override
   public void setDefaultBlock(ICBlock var1) {
      this.UT = var1;
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.UT != null;
   }

   @Override
   public int sizeWithoutDefault() {
      return this.wS.size();
   }

   @Override
   public int size() {
      return this.sizeWithoutDefault() + (this.hasDefaultBlock() ? 1 : 0);
   }

   @Override
   public boolean insertAt(long var1, ICStatement var3) {
      for (aff.Av var5 : this.wS) {
         if (var5.A.insertAt(var1, var3)) {
            return true;
         }
      }

      return this.UT != null ? this.UT.insertAt(var1, var3) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList(this.getCaseBodies());
      if (this.UT != null) {
         var1.add(this.UT);
      }

      return var1;
   }

   @Override
   public List getKeysForBlock(ICBlock var1) {
      aff.Av var2 = this.pC(var1);
      return var2 == null ? null : new ArrayList(var2.kS);
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new age(this, this.kS));

      for (aff.Av var3 : this.wS) {
         var1.add(new agf(var3.kS));
         var1.addAll(var3.A.generateFlatList());
      }

      if (this.UT != null) {
         var1.add(new agg());
         var1.addAll(this.UT.generateFlatList());
      }

      var1.add(new agh());
      return var1;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC(this.kS);
      this.wS.forEach(var1x -> afm.pC(var1, var1x.A));
      return afm.pC(var1, this.UT);
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.kS == var1) {
         if (!(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.kS = (ICExpression)var2;
            return true;
         }
      } else {
         for (aff.Av var4 : this.wS) {
            if (var4.A == var1) {
               if (!(var2 instanceof ICBlock)) {
                  return false;
               }

               throw new RuntimeException("TBI");
            }
         }

         if (this.UT == var1) {
            if (var2 != null && !(var2 instanceof ICBlock)) {
               return false;
            }

            this.UT = (ICBlock)var2;
         }

         return false;
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      var1.appendKeyword(CKeyword.SWITCH);
      var1.paren();
      this.kS.generate(var1);
      var1.parenClose();
      var1.space();
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();
      Object var2 = this.wS;
      boolean var3 = true;

      for (aff.Av var5 : this.wS) {
         if (!var5.A.isEmpty()) {
            ICStatement var6 = var5.A.getLast();
            if (var6 instanceof ICBreak || var6 instanceof ICGoto || var6 instanceof ICReturn) {
               continue;
            }
         }

         var3 = false;
         break;
      }

      if (var3) {
         var2 = new ArrayList(this.wS);
         Collections.sort((List)var2, new afg(this));
      }

      for (aff.Av var12 : var2) {
         TreeSet var13 = var12.kS;
         ICBlock var7 = var12.A;
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

      if (this.UT != null) {
         var1.appendKeyword(CKeyword.DEFAULT);
         var1.append(": ");
         this.UT.generate(var1);
         var1.eol();
      }

      var1.decrementIndentationLevel();
      var1.braceClose();
      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Switch;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         aff var2 = (aff)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
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
   public static class Av {
      @SerId(value = 1, deprecated = true)
      TreeSet pC;
      @SerId(2)
      ICBlock A;
      @SerId(3)
      TreeSet kS = new TreeSet();

      @SerCustomInitPostGraph
      private void A() {
         if (this.pC != null) {
            this.kS = new TreeSet();

            for (Integer var2 : this.pC) {
               this.kS.add(BigInteger.valueOf(var2.longValue()));
            }

            this.pC = null;
         }
      }

      Av(Collection var1, ICBlock var2) {
         if (var1 != null && !var1.isEmpty() && var2 != null) {
            this.kS.addAll(var1);
            this.A = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      void pC(Collection var1) {
         this.kS.addAll(var1);
      }

      aff.Av pC() {
         return new aff.Av(this.kS, this.A.duplicate());
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
         return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
            aff.Av var2 = (aff.Av)var1;
            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            if (this.kS == null) {
               if (var2.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var2.kS)) {
               return false;
            }

            return true;
         }
      }

      @Override
      public String toString() {
         return Strings.ff("%s -> %s", this.kS, this.A);
      }
   }
}
