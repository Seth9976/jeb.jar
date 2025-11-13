package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

@SerDisabled
public class bqe implements IDSwitchData {
   private TreeMap pC = new TreeMap();

   @Override
   public void clear() {
      this.pC.clear();
   }

   @Override
   public boolean isEmptySwitch() {
      return this.pC.isEmpty();
   }

   @Override
   public boolean isRegularSwitch() {
      return this.pC.isEmpty() || this.pC.keySet().iterator().next() instanceof Integer;
   }

   @Override
   public boolean isStringSwitch() {
      return !this.pC.isEmpty() && this.pC.keySet().iterator().next() instanceof String;
   }

   @Override
   public boolean convertToStringKeys(Map var1) {
      if (!this.isRegularSwitch()) {
         throw new IllegalStateException();
      } else {
         TreeMap var2 = new TreeMap();

         for (Object var4 : this.pC.keySet()) {
            String var5 = (String)var1.get(var4);
            if (var5 == null) {
               return false;
            }

            var2.put(var5, (IDTarget)this.pC.get(var4));
         }

         this.pC.clear();
         this.pC.putAll(var2);
         return true;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, false);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bqe var3 = (bqe)var1;
         if (this.pC == null) {
            if (var3.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var3.pC)) {
            return false;
         }

         return true;
      }
   }

   public bqe pC(DCopyOptions var1) {
      return this.pC();
   }

   public bqe pC() {
      bqe var1 = new bqe();
      TreeMap var2 = new TreeMap();

      for (Entry var4 : this.pC.entrySet()) {
         var2.put(var4.getKey(), var4.getValue() == null ? null : ((IDTarget)var4.getValue()).duplicate());
      }

      var1.pC = var2;
      return var1;
   }

   @Override
   public IDTarget addCase(Object var1, IDTarget var2, boolean var3) {
      if (this.pC.isEmpty()) {
         if (!(var1 instanceof Integer) && !(var1 instanceof String)) {
            throw new IllegalArgumentException("Expected an integer or string key");
         }
      } else {
         if (this.isRegularSwitch() && !(var1 instanceof Integer)) {
            throw new IllegalArgumentException("Expected an integer key");
         }

         if (this.isStringSwitch() && !(var1 instanceof String)) {
            throw new IllegalArgumentException("Expected a string key");
         }

         if (!var3 && this.pC.containsKey(var1)) {
            throw new IllegalStateException("Duplicate key in switch table: " + var1);
         }
      }

      return (IDTarget)this.pC.put(var1, var2);
   }

   @Override
   public int getCaseCount() {
      return this.pC.size();
   }

   public TreeSet A() {
      return new TreeSet(this.pC.keySet());
   }

   public TreeSet kS() {
      if (!this.isRegularSwitch()) {
         throw new IllegalStateException();
      } else {
         TreeSet var1 = new TreeSet();

         for (Object var3 : this.pC.keySet()) {
            var1.add((Integer)var3);
         }

         return var1;
      }
   }

   @Override
   public IDTarget getTargetForCase(Object var1) {
      return (IDTarget)this.pC.get(var1);
   }

   @Override
   public int getTargetCount() {
      return new HashSet(this.pC.values()).size();
   }

   private List wS() {
      return new ArrayList(new LinkedHashSet(this.pC.values()));
   }

   @Override
   public Collection getTargets(boolean var1) {
      return (Collection)(var1 ? this.wS() : this.pC.values());
   }

   @Override
   public List getKeysForTargets(IDTarget var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.pC.keySet()) {
         if (((IDTarget)this.pC.get(var4)).equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   @Override
   public IDTarget deleteCase(Object var1) {
      return (IDTarget)this.pC.remove(var1);
   }

   @Override
   public int deleteCasesToTarget(IDTarget var1) {
      return this.deleteCasesTo(var1.getOffset());
   }

   @Override
   public int deleteCasesTo(int var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.pC.keySet()) {
         if (((IDTarget)this.pC.get(var4)).getOffset() == var1) {
            var2.add(var4);
         }
      }

      for (Object var6 : var2) {
         this.pC.remove(var6);
      }

      return var2.size();
   }

   @Override
   public int updateCases(int var1, int var2) {
      int var3 = 0;

      for (Object var5 : this.pC.keySet()) {
         if (((IDTarget)this.pC.get(var5)).getOffset() == var1) {
            ((IDTarget)this.pC.get(var5)).setOffset(var2);
            var3++;
         }
      }

      return var3;
   }

   @Override
   public int updateCases(Map var1, boolean var2) {
      int var3 = 0;

      for (Object var5 : this.pC.keySet()) {
         int var6 = ((IDTarget)this.pC.get(var5)).getOffset();
         Integer var7 = (Integer)var1.get(var6);
         if (var7 == null) {
            if (var2) {
               throw new IllegalArgumentException(Strings.ff("Missed entry for old offset 0x%X", var6));
            }
         } else {
            ((IDTarget)this.pC.get(var5)).setOffset(var7);
            var3++;
         }
      }

      return var3;
   }

   @Override
   public String toString(IDMethodContext var1) {
      DFormattingContext var2 = new DFormattingContext(var1);
      this.format(var2);
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.toString(null);
   }

   @Override
   public String format(IInstruction var1, long var2) {
      return this.toString();
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append('(');
      int var2 = 0;

      for (Object var4 : this.pC.keySet()) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         if (var4 instanceof String) {
            var1.append('"');
            var1.append(Formatter.escapeString((String)var4));
            var1.append('"');
         } else if (var4 instanceof Integer) {
            var1.append(Integer.toHexString((Integer)var4).toUpperCase());
         } else {
            var1.append(var4);
         }

         var1.append(':');
         IDTarget var5 = (IDTarget)this.pC.get(var4);
         var5.format(var1);
         var2++;
      }

      var1.append(')');
   }
}
